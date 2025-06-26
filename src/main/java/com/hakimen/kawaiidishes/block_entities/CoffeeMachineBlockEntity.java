package com.hakimen.kawaiidishes.block_entities;

import com.hakimen.kawaiidishes.containers.CoffeeMachineContainer;
import com.hakimen.kawaiidishes.containers.CoffeeMachineDataContainer;
import com.hakimen.kawaiidishes.recipes.CoffeeMachineRecipe;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import com.hakimen.kawaiidishes.registry.PacketRegister;
import com.hakimen.kawaiidishes.utils.FluidStack;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.SlottedStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import java.util.Optional;

public class CoffeeMachineBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, BlockEntityTicker<CoffeeMachineBlockEntity> {

    SimpleContainer inventory = new SimpleContainer(7){
        @Override
        public boolean canPlaceItem(int i, ItemStack itemStack) {
            return super.canPlaceItem(i, itemStack);
        }
    };
    private ContainerData data;

    private int progress = 0;    SingleVariantStorage<FluidVariant> waterTank = new SingleVariantStorage<FluidVariant>() {
        @Override
        protected FluidVariant getBlankVariant() {
            return FluidVariant.blank();
        }

        @Override
        protected long getCapacity(FluidVariant variant) {
            return (FluidConstants.BUCKET) * 4; // 4 Buckets of Water
        }

        @Override
        public boolean supportsInsertion() {
            return true;
        }

        @Override
        public boolean supportsExtraction() {
            return true;
        }

        @Override
        protected boolean canInsert(FluidVariant variant) {
            return variant.isOf(Fluids.WATER);
        }

        @Override
        protected void onFinalCommit() {
            setChanged();
            if (!level.isClientSide()) {
                FriendlyByteBuf data = PacketByteBufs.create();
                waterTank.variant.toPacket(data);
                data.writeLong(waterTank.amount);
                data.writeBlockPos(getBlockPos());

                for (ServerPlayer player : PlayerLookup.tracking((ServerLevel) level, getBlockPos())) {
                    ServerPlayNetworking.send(player, PacketRegister.FLUID_SYNC, data);
                }
            }
        }
    };
    private int recipeTicks = 0;
    private boolean isCrafting = false;

    public CoffeeMachineBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegister.COFFEE_MACHINE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0:
                        return CoffeeMachineBlockEntity.this.progress;
                    case 1:
                        return CoffeeMachineBlockEntity.this.recipeTicks;
                    default:
                        return 0;
                }
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        CoffeeMachineBlockEntity.this.progress = value;
                        break;
                    case 1:
                        CoffeeMachineBlockEntity.this.recipeTicks = value;
                        break;
                }
            }

            public int getCount() {
                return 2;
            }
        };
    }


    public static boolean hasRecipe(CoffeeMachineBlockEntity entity) {
        Level level = entity.level;
        CoffeeMachineDataContainer coffeeMachineContainer = new CoffeeMachineDataContainer(entity);
        Optional<CoffeeMachineRecipe> match = level.getRecipeManager()
                .getRecipeFor(CoffeeMachineRecipe.Type.INSTANCE, coffeeMachineContainer, level);
        return match.isPresent();
    }


    private static void transferItemFluidToFluidTank(CoffeeMachineBlockEntity pEntity) {
        try (Transaction tx = Transaction.openOuter()) {
            if (ItemStack.isSameItem(pEntity.inventory.getItem(0), Items.WATER_BUCKET.getDefaultInstance())) {
                int drainAmount = (int) FluidConstants.BUCKET;

                if (pEntity.getWaterTank().amount + drainAmount <= pEntity.getWaterTank().getCapacity()) {
                    pEntity.waterTank.insert(FluidVariant.of(Fluids.WATER), drainAmount, tx);

                    pEntity.inventory.removeItem(0, 1);
                    pEntity.inventory.setItem(0, Items.BUCKET.getDefaultInstance());
                    pEntity.setChanged();
                }
                tx.commit();
            }
        }
    }

    public SimpleContainer getInventory() {
        return inventory;
    }

    private static void transferFluidToItemFluid(CoffeeMachineBlockEntity pEntity) {

        try (Transaction tx = Transaction.openOuter()) {
            if (ItemStack.isSameItem(pEntity.inventory.getItem(1), Items.BUCKET.getDefaultInstance())) {
                int drainAmount = (int) (FluidConstants.BUCKET);

                if (pEntity.waterTank.getAmount() >= drainAmount) {
                    FluidVariant fluidVariant = FluidVariant.of(Fluids.WATER);
                    pEntity.waterTank.extract(fluidVariant, drainAmount, tx);


                    pEntity.inventory.removeItem(1, 1);
                    pEntity.inventory.setItem(1, Items.WATER_BUCKET.getDefaultInstance());


                    pEntity.setChanged();
                }
                tx.commit();
            }
        }
    }

    public SlottedStorage<ItemVariant> getStorage() {
        return InventoryStorage.of(inventory, null);
    }

    @Override
    public void setChanged() {
        super.setChanged();
    }

    public SingleVariantStorage<FluidVariant> getWaterTank() {
        return waterTank;
    }

    public CoffeeMachineBlockEntity setWaterTank(SingleVariantStorage<FluidVariant> waterTank) {
        this.waterTank = waterTank;
        return this;
    }

    public int getProgress() {
        return progress;
    }

    public int getRecipeTicks() {
        return recipeTicks;
    }

    public ContainerData getData() {
        return data;
    }

    public boolean isCrafting() {
        return isCrafting;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {

        ListTag listTag = new ListTag();

        for(int i = 0; i < this.getInventory().getContainerSize(); ++i) {
            ItemStack itemStack = this.getInventory().getItem(i);
            CompoundTag tag = new CompoundTag();
            tag.putInt("Slot", i);
            tag.put("Item", itemStack.save(new CompoundTag()));
            listTag.add(tag);
        }

        pTag.put("Items", listTag);
        waterTank.writeNbt(pTag);
        pTag.putInt("Progress", progress);
        pTag.putInt("RecipeTicks", recipeTicks);
        pTag.putBoolean("IsCrafting", isCrafting);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        progress = pTag.getInt("Progress");
        recipeTicks = pTag.getInt("RecipeTicks");
        isCrafting = pTag.getBoolean("IsCrafting");

        ListTag listTag = pTag.getList("Items", Tag.TAG_COMPOUND);
        this.getInventory().clearContent();

        for(int i = 0; i < listTag.size(); ++i) {
            CompoundTag tag = listTag.getCompound(i);
            int slot = tag.getInt("Slot");
            ItemStack stack = ItemStack.of(tag.getCompound("Item"));
            this.getInventory().setItem(slot, stack);
        }
        waterTank.variant = FluidVariant.fromNbt(pTag.getCompound("variant"));
        waterTank.amount = pTag.getLong("amount");
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this, BlockEntity::saveWithFullMetadata);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithFullMetadata();
    }

    @Override
    public void tick(Level pLevel, BlockPos pPos, BlockState pState, CoffeeMachineBlockEntity pBlockEntity) {
        if (hasRecipe(pBlockEntity)) {
            CoffeeMachineDataContainer coffeeMachineContainer = new CoffeeMachineDataContainer(pBlockEntity);
            Optional<CoffeeMachineRecipe> match = level.getRecipeManager()
                    .getRecipeFor(CoffeeMachineRecipe.Type.INSTANCE, coffeeMachineContainer, level);
            if (match.isPresent()) {
                CoffeeMachineRecipe recipe = match.get();
                if (!isCrafting) {
                    isCrafting = true;
                    recipeTicks = recipe.getTicks();
                } else {
                    this.progress++;
                    if (progress >= recipeTicks) {
                        isCrafting = false;
                        progress = 0;
                        try(Transaction tx = Transaction.openOuter()){
                            getWaterTank().extract(FluidVariant.of(Fluids.WATER), FluidStack.convertMbToDroplets(recipe.getWaterNeeded()),tx);
                            tx.commit();
                        }
                        for (int i = 2; i < 6; i++) {
                            ItemStack inventoryStack = pBlockEntity.inventory.getItem(i);
                            var stack = pBlockEntity.inventory.getItem(i).getItem().getCraftingRemainingItem();
                            boolean hasRemainder = stack != null;
                            if (inventoryStack.getCount() > 0 && !hasRemainder) {
                                pBlockEntity.inventory.removeItem(i, 1);
                            } else if (hasRemainder) {
                                pBlockEntity.inventory.setItem(i, stack == null ? ItemStack.EMPTY : stack.getDefaultInstance());
                            }
                        }
                        ItemStack inventoryStack = pBlockEntity.inventory.getItem(6);
                        if (inventoryStack.isEmpty()) {
                            pBlockEntity.inventory.setItem(6, recipe.getResultItem(null));
                        } else if (inventoryStack.getItem().equals(recipe.getResultItem(null).getItem())
                                && inventoryStack.getCount() <= inventoryStack.getMaxStackSize()) {
                            pBlockEntity.inventory.getItem(6).grow(1);
                        }
                    }
                }
            }
        } else {
            if (progress > 0) {
                progress--;
            }
        }
        setChanged();


        if (hasFluidInSlot(pBlockEntity)) {
            transferItemFluidToFluidTank(pBlockEntity);
        }

        if (hasTankInExtractSlot(pBlockEntity)) {
            transferFluidToItemFluid(pBlockEntity);
        }
    }

    public boolean hasFluidInSlot(CoffeeMachineBlockEntity entity) {
        return entity.inventory.getItem(0).getCount() > 0;
    }

    public boolean hasTankInExtractSlot(CoffeeMachineBlockEntity entity) {
        return entity.inventory.getItem(1).getCount() > 0;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("gui.kawaiidishes.coffee_machine");
    }

    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new CoffeeMachineContainer(pContainerId, pInventory, this, this.getData());
    }

    @Override
    public void writeScreenOpeningData(ServerPlayer player, FriendlyByteBuf buf) {
        buf.writeBlockPos(getBlockPos());
    }
}
