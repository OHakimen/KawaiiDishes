//package com.hakimen.kawaiidishes.block_entities;
//
//import com.hakimen.kawaiidishes.containers.DisplayCaseContainer;
//import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.HolderLookup;
//import net.minecraft.core.component.DataComponents;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.network.chat.Component;
//import net.minecraft.network.protocol.Packet;
//import net.minecraft.network.protocol.game.ClientGamePacketListener;
//import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
//import net.minecraft.world.MenuProvider;
//import net.minecraft.world.entity.player.Inventory;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.inventory.AbstractContainerMenu;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.PotionItem;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.entity.BlockEntityTicker;
//import net.minecraft.world.level.block.state.BlockState;
//import net.neoforged.neoforge.items.ItemStackHandler;
//import org.jetbrains.annotations.Nullable;
//
//import javax.annotation.Nonnull;
//
//public class DisplayCaseBlockEntity extends BlockEntity implements MenuProvider, BlockEntityTicker<DisplayCaseBlockEntity> {
//
//
//    private final ItemStackHandler inventory = createHandler();
//
//    public DisplayCaseBlockEntity(BlockPos pPos, BlockState pState) {
//        super(BlockEntityRegister.DISPLAY_CASE.get(), pPos, pState);
//    }
//
//    @Override
//    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
//        return this.saveWithFullMetadata(pRegistries);
//    }
//
//    @Override
//    public Packet<ClientGamePacketListener> getUpdatePacket() {
//        // Will get tag from #getUpdateTag
//        return ClientboundBlockEntityDataPacket.create(this,BlockEntity::saveWithFullMetadata);
//    }
//
//    @Override
//    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider lookupProvider) {
//        super.handleUpdateTag(tag, lookupProvider);
//        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
//    }
//
//    @Override
//    public Component getDisplayName() {
//        return Component.translatable("gui.kawaiidishes.display_case");
//    }
//
//    @Nullable
//    @Override
//    public AbstractContainerMenu createMenu(int windowId, Inventory inventory, Player player) {
//        return new DisplayCaseContainer(windowId,inventory,this);
//    }
//
//    @Override
//    public void tick(Level pLevel, BlockPos pPos, BlockState pState, DisplayCaseBlockEntity entity) {
//    }
//
//    @Override
//    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
//        pTag.merge(this.inventory.serializeNBT(pRegistries));
//        super.saveAdditional(pTag,pRegistries);
//    }
//
//    @Override
//    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
//        inventory.deserializeNBT(pRegistries,pTag);
//        super.loadAdditional(pTag, pRegistries);
//    }
//
//    private ItemStackHandler createHandler() {
//        return new ItemStackHandler(8) {
//            @Override
//            protected void onContentsChanged(int slot) {
//                setChanged();
//                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
//            }
//
//            @Override
//            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
//                return stack.has(DataComponents.FOOD) || stack.getItem() instanceof PotionItem;
//            }
//
//
//            @Override
//            public int getSlotLimit(int slot) {
//                return 64;
//            }
//
//            @Nonnull
//            @Override
//            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
//                if (!isItemValid(slot, stack)) {
//                    return stack;
//                }
//
//                return super.insertItem(slot, stack, simulate);
//            }
//        };
//    }
//
//    public ItemStackHandler getInventory() {
//        return inventory;
//    }
//
//}

// TODO: display case