package com.hakimen.kawaiidishes.block_entities;

import com.hakimen.kawaiidishes.containers.DisplayCaseContainer;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.SlottedStorage;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class DisplayCaseBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, BlockEntityTicker<DisplayCaseBlockEntity> {


    private final SimpleInventory inventory = new SimpleInventory(8){
        @Override
        public boolean isValid(int i, ItemStack itemStack) {
            return itemStack.isFood() || itemStack.getItem() instanceof PotionItem;
        }

        @Override
        public boolean canInsert(ItemStack itemStack) {
            return itemStack.isFood() || itemStack.getItem() instanceof PotionItem;
        }
    };
    public SlottedStorage<ItemVariant> getStorage() {
        return InventoryStorage.of(inventory, null);
    }


    public SimpleInventory getInventory() {
        return inventory;
    }

    public DisplayCaseBlockEntity(BlockPos pPos, BlockState pState) {
        super(BlockEntityRegister.DISPLAY_CASE.get(), pPos, pState);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbtWithIdentifyingData();
    }
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        // Will get tag from #getUpdateTag
        return BlockEntityUpdateS2CPacket.create(this,BlockEntity::createNbtWithIdentifyingData);
    }


    @Override
    public Text getDisplayName() {
        return Text.translatable("gui.kawaiidishes.display_case");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int windowId, PlayerInventory inventory, PlayerEntity player) {
        return new DisplayCaseContainer(windowId,inventory,this);
    }

    @Override
    public void tick(World pLevel, BlockPos pPos, BlockState pState, DisplayCaseBlockEntity entity) {
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
        super.writeNbt(pTag);
    }

    @Override
    public void readNbt(NbtCompound pTag) {
        super.readNbt(pTag);
        NbtList listTag = pTag.getList("Items", NbtElement.COMPOUND_TYPE);
        this.getInventory().clear();

        for(int i = 0; i < listTag.size(); ++i) {
            NbtCompound tag = listTag.getCompound(i);
            int slot = tag.getInt("Slot");
            ItemStack stack = ItemStack.fromNbt(tag.getCompound("Item"));
            this.getInventory().setStack(slot, stack);
        }
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(getPos());
    }
}
