package com.hakimen.kawaiidishes.block_entities;

import com.hakimen.kawaiidishes.containers.DisplayCaseContainer;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.SlottedStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;


public class DisplayCaseBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, BlockEntityTicker<DisplayCaseBlockEntity> {


    private final SimpleContainer inventory = new SimpleContainer(8){
        @Override
        public boolean canPlaceItem(int i, ItemStack itemStack) {
            return itemStack.isEdible() || itemStack.getItem() instanceof PotionItem;
        }

        @Override
        public boolean canAddItem(ItemStack itemStack) {
            return itemStack.isEdible() || itemStack.getItem() instanceof PotionItem;
        }
    };
    public SlottedStorage<ItemVariant> getStorage() {
        return InventoryStorage.of(inventory, null);
    }


    public SimpleContainer getInventory() {
        return inventory;
    }

    public DisplayCaseBlockEntity(BlockPos pPos, BlockState pState) {
        super(BlockEntityRegister.DISPLAY_CASE.get(), pPos, pState);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithFullMetadata();
    }
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        // Will get tag from #getUpdateTag
        return ClientboundBlockEntityDataPacket.create(this,BlockEntity::saveWithFullMetadata);
    }


    @Override
    public Component getDisplayName() {
        return Component.translatable("gui.kawaiidishes.display_case");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory inventory, Player player) {
        return new DisplayCaseContainer(windowId,inventory,this);
    }

    @Override
    public void tick(Level pLevel, BlockPos pPos, BlockState pState, DisplayCaseBlockEntity entity) {
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
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        ListTag listTag = pTag.getList("Items", Tag.TAG_COMPOUND);
        this.getInventory().clearContent();

        for(int i = 0; i < listTag.size(); ++i) {
            CompoundTag tag = listTag.getCompound(i);
            int slot = tag.getInt("Slot");
            ItemStack stack = ItemStack.of(tag.getCompound("Item"));
            this.getInventory().setItem(slot, stack);
        }
    }

    @Override
    public void writeScreenOpeningData(ServerPlayer player, FriendlyByteBuf buf) {
        buf.writeBlockPos(getBlockPos());
    }
}
