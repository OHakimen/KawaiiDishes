package com.hakimen.kawaiidishes.blocks.block_entities;

import com.hakimen.kawaiidishes.recipes.MortarAndPestleRecipe;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import java.util.Optional;

public class MortarAndPestleBlockEntity extends BlockEntity implements IAnimatable, BlockEntityTicker<MortarAndPestleBlockEntity> {
    private AnimationFactory factory = new AnimationFactory(this);

    public boolean isUsing = false;
    public int usingTime = 0;
    public boolean isCrafting = false;
    public final ItemStackHandler inventory = createHandler();
    private final LazyOptional<IItemHandler> invHandler = LazyOptional.of(() -> inventory);
    public int progress = 0;
    public int recipeTime = 0;

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.merge(this.inventory.serializeNBT());
        pTag.putInt("progress",progress);
        pTag.putBoolean("isCrafting",isCrafting);
        pTag.putInt("recipeTime",recipeTime);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        progress = pTag.getInt("progress");
        recipeTime = pTag.getInt("recipeTime");
        isCrafting = pTag.getBoolean("isCrafting");
        this.inventory.deserializeNBT(pTag);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this,BlockEntity::saveWithFullMetadata);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithFullMetadata();
    }

    public MortarAndPestleBlockEntity( BlockPos pWorldPosition, BlockState pBlockState) {
        super(BlockEntityRegister.mortarAndPestle.get(),pWorldPosition, pBlockState);
    }

    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this,"controller",20, this::predicate));
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event){
        if(usingTime > 0){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("use", true));
        }else{
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        }

        return PlayState.CONTINUE;
    }



    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public void tick(Level pLevel, BlockPos pPos, BlockState pState, MortarAndPestleBlockEntity pBlockEntity) {
        if(!isUsing){
            this.usingTime = 0;
        }
        if(this.usingTime < 198){
            this.isUsing = false;
        }else{
            this.usingTime--;
        }
        if(isUsing){
            if(inventory.getStackInSlot(0) != ItemStack.EMPTY){
                if(hasRecipe(this)){
                    SimpleContainer inventory = new SimpleContainer(pBlockEntity.inventory.getSlots());
                    inventory.setItem(0, this.inventory.getStackInSlot(0));

                    Optional<MortarAndPestleRecipe> match = level.getRecipeManager()
                            .getRecipeFor(MortarAndPestleRecipe.Type.INSTANCE, inventory, level);
                    if(isCrafting){
                        progress++;
                        pLevel.addAlwaysVisibleParticle(new ItemParticleOption(ParticleTypes.ITEM,pBlockEntity.inventory.getStackInSlot(0)),pPos.getX()+0.5f,pPos.getY()+0.25f,pPos.getZ()+0.5f,this.level.random.nextFloat(-1,1)/10f,0.1f,this.level.random.nextFloat(-1,1)/10f);
                        if(progress >= recipeTime){
                            isCrafting = false;
                            progress = 0;
                            this.inventory.setStackInSlot(0,match.get().getResultItem());
                        }
                    }else if(match.isPresent()) {
                        this.recipeTime = match.get().getTicks();
                        isCrafting = true;
                    }


                }
            }
        }
    }
    public static boolean hasRecipe(MortarAndPestleBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.inventory.getSlots());
        inventory.setItem(0, entity.inventory.getStackInSlot(0));

        Optional<MortarAndPestleRecipe> match = level.getRecipeManager()
                .getRecipeFor(MortarAndPestleRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent();
    }
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return (LazyOptional<T>) invHandler;
        }else{
            return super.getCapability(cap);
        }
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
                level.sendBlockUpdated(getBlockPos(),getBlockState(),getBlockState(), Block.UPDATE_ALL);
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return true;
            }



            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack)) {
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

}
