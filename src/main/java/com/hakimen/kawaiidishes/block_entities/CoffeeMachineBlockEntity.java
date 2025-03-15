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
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import java.util.Optional;

public class CoffeeMachineBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, BlockEntityTicker<CoffeeMachineBlockEntity> {

    SimpleInventory inventory = new SimpleInventory(7){
        @Override
        public boolean isValid(int i, ItemStack itemStack) {
            return super.isValid(i, itemStack);
        }
    };
    private PropertyDelegate data;

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
            markDirty();
            if (!world.isClient()) {
                PacketByteBuf data = PacketByteBufs.create();
                waterTank.variant.toPacket(data);
                data.writeLong(waterTank.amount);
                data.writeBlockPos(getPos());

                for (ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) world, getPos())) {
                    ServerPlayNetworking.send(player, PacketRegister.FLUID_SYNC, data);
                }
            }
        }
    };
    private int recipeTicks = 0;
    private boolean isCrafting = false;

    public CoffeeMachineBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegister.COFFEE_MACHINE.get(), pPos, pBlockState);
        this.data = new PropertyDelegate() {
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

            public int size() {
                return 2;
            }
        };
    }


    public static boolean hasRecipe(CoffeeMachineBlockEntity entity) {
        World level = entity.world;
        CoffeeMachineDataContainer coffeeMachineContainer = new CoffeeMachineDataContainer(entity);
        Optional<CoffeeMachineRecipe> match = level.getRecipeManager()
                .getFirstMatch(CoffeeMachineRecipe.Type.INSTANCE, coffeeMachineContainer, level);
        return match.isPresent();
    }


    private static void transferItemFluidToFluidTank(CoffeeMachineBlockEntity pEntity) {
        try (Transaction tx = Transaction.openOuter()) {
            if (ItemStack.areItemsEqual(pEntity.inventory.getStack(0), Items.WATER_BUCKET.getDefaultStack())) {
                int drainAmount = (int) FluidConstants.BUCKET;

                if (pEntity.getWaterTank().amount + drainAmount <= pEntity.getWaterTank().getCapacity()) {
                    pEntity.waterTank.insert(FluidVariant.of(Fluids.WATER), drainAmount, tx);

                    pEntity.inventory.removeStack(0, 1);
                    pEntity.inventory.setStack(0, Items.BUCKET.getDefaultStack());
                    pEntity.markDirty();
                }
                tx.commit();
            }
        }
    }

    public SimpleInventory getInventory() {
        return inventory;
    }

    private static void transferFluidToItemFluid(CoffeeMachineBlockEntity pEntity) {

        try (Transaction tx = Transaction.openOuter()) {
            if (ItemStack.areItemsEqual(pEntity.inventory.getStack(1), Items.BUCKET.getDefaultStack())) {
                int drainAmount = (int) (FluidConstants.BUCKET);

                if (pEntity.waterTank.getAmount() >= drainAmount) {
                    FluidVariant fluidVariant = FluidVariant.of(Fluids.WATER);
                    pEntity.waterTank.extract(fluidVariant, drainAmount, tx);


                    pEntity.inventory.removeStack(1, 1);
                    pEntity.inventory.setStack(1, Items.WATER_BUCKET.getDefaultStack());


                    pEntity.markDirty();
                }
                tx.commit();
            }
        }
    }

    public SlottedStorage<ItemVariant> getStorage() {
        return InventoryStorage.of(inventory, null);
    }

    @Override
    public void markDirty() {
        super.markDirty();
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

    public PropertyDelegate getData() {
        return data;
    }

    public boolean isCrafting() {
        return isCrafting;
    }

    @Override
    protected void writeNbt(NbtCompound pTag) {

        NbtList listTag = new NbtList();

        for(int i = 0; i < this.getInventory().size(); ++i) {
            ItemStack itemStack = this.getInventory().getStack(i);
            NbtCompound tag = new NbtCompound();
            tag.putInt("Slot", i);
            tag.put("Item", itemStack.writeNbt(new NbtCompound()));
            listTag.add(tag);
        }

        pTag.put("Items", listTag);
        waterTank.writeNbt(pTag);
        pTag.putInt("Progress", progress);
        pTag.putInt("RecipeTicks", recipeTicks);
        pTag.putBoolean("IsCrafting", isCrafting);
        super.writeNbt(pTag);
    }

    @Override
    public void readNbt(NbtCompound pTag) {
        super.readNbt(pTag);
        progress = pTag.getInt("Progress");
        recipeTicks = pTag.getInt("RecipeTicks");
        isCrafting = pTag.getBoolean("IsCrafting");

        NbtList listTag = pTag.getList("Items", NbtElement.COMPOUND_TYPE);
        this.getInventory().clear();

        for(int i = 0; i < listTag.size(); ++i) {
            NbtCompound tag = listTag.getCompound(i);
            int slot = tag.getInt("Slot");
            ItemStack stack = ItemStack.fromNbt(tag.getCompound("Item"));
            this.getInventory().setStack(slot, stack);
        }
        waterTank.variant = FluidVariant.fromNbt(pTag.getCompound("variant"));
        waterTank.amount = pTag.getLong("amount");
    }

    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this, BlockEntity::createNbtWithIdentifyingData);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbtWithIdentifyingData();
    }

    @Override
    public void tick(World pLevel, BlockPos pPos, BlockState pState, CoffeeMachineBlockEntity pBlockEntity) {
        if (hasRecipe(pBlockEntity)) {
            CoffeeMachineDataContainer coffeeMachineContainer = new CoffeeMachineDataContainer(pBlockEntity);
            Optional<CoffeeMachineRecipe> match = world.getRecipeManager()
                    .getFirstMatch(CoffeeMachineRecipe.Type.INSTANCE, coffeeMachineContainer, world);
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
                            ItemStack inventoryStack = pBlockEntity.inventory.getStack(i);
                            var stack = pBlockEntity.inventory.getStack(i).getItem().getRecipeRemainder();
                            boolean hasRemainder = stack != null;
                            if (inventoryStack.getCount() > 0 && !hasRemainder) {
                                pBlockEntity.inventory.removeStack(i, 1);
                            } else if (hasRemainder) {
                                pBlockEntity.inventory.setStack(i, stack == null ? ItemStack.EMPTY : stack.getDefaultStack());
                            }
                        }
                        ItemStack inventoryStack = pBlockEntity.inventory.getStack(6);
                        if (inventoryStack.isEmpty()) {
                            pBlockEntity.inventory.setStack(6, recipe.getOutput(null));
                        } else if (inventoryStack.getItem().equals(recipe.getOutput(null).getItem())
                                && inventoryStack.getCount() <= inventoryStack.getMaxCount()) {
                            pBlockEntity.inventory.getStack(6).increment(1);
                        }
                    }
                }
            }
        } else {
            if (progress > 0) {
                progress--;
            }
        }
        markDirty();


        if (hasFluidInSlot(pBlockEntity)) {
            transferItemFluidToFluidTank(pBlockEntity);
        }

        if (hasTankInExtractSlot(pBlockEntity)) {
            transferFluidToItemFluid(pBlockEntity);
        }
    }

    public boolean hasFluidInSlot(CoffeeMachineBlockEntity entity) {
        return entity.inventory.getStack(0).getCount() > 0;
    }

    public boolean hasTankInExtractSlot(CoffeeMachineBlockEntity entity) {
        return entity.inventory.getStack(1).getCount() > 0;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("gui.kawaiidishes.coffee_machine");
    }

    @Override
    public ScreenHandler createMenu(int pContainerId, PlayerInventory pInventory, PlayerEntity pPlayer) {
        return new CoffeeMachineContainer(pContainerId, pInventory, this, this.getData());
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(getPos());
    }
}
