package com.hakimen.kawaiidishes.block_entities;

import com.hakimen.kawaiidishes.block.IncenseBlock;
import com.hakimen.kawaiidishes.datagen.ItemTagDataGen;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import com.hakimen.kawaiidishes.registry.ParticleRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class IncenseBlockEntity extends BlockEntity {

    private final ItemStackHandler inventory = createHandler();
    private int aroma = Aromas.values().length - 1;
    public IncenseBlockEntity(BlockPos pPos, BlockState pState) {
        super(BlockEntityRegister.INCENSE.get(), pPos, pState);
    }

    public Aromas getAroma() {
        return aroma != -1 ? Aromas.values()[aroma] : Aromas.INVALID;
    }

    public IncenseBlockEntity setAroma(int aroma) {
        this.aroma = aroma;
        return this;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.merge(this.inventory.serializeNBT());
        pTag.putInt("Aroma", aroma);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        inventory.deserializeNBT(pTag);
        aroma = pTag.getInt("Aroma");
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState, IncenseBlockEntity entity) {
        if (pLevel.isClientSide) {
            // On client
            if (pState.getValue(IncenseBlock.LIT) && pLevel.random.nextFloat() < 0.025f) {

                pLevel.addParticle(ParticleRegister.INCENSE_PARTICLES.get(),
                        pPos.getX() + 0.5f,
                        pPos.getY() + 0.45f,
                        pPos.getZ() + 0.5f,
                        0,
                        0.01f,
                        0
                );
            }
        }else {
            // On server
            AABB actuationRange =  AABB.ofSize(pPos.getCenter(), 1,1,1).inflate(8);

            if(!this.getBlockState().getValue(IncenseBlock.LIT)){
                return;
            }

            switch (entity.getAroma()){
                case PacifyAroma -> {
                    List<Entity> entities = level.getEntities(null, actuationRange);
                    for (Entity mob:entities) {
                        if(mob instanceof Monster){
                            mob.remove(Entity.RemovalReason.DISCARDED);
                        }
                    }
                }
                case HastyAroma -> {
                    List<Player> entities = level.getEntities(EntityType.PLAYER, actuationRange, player -> true);
                    for (Player player:entities) {
                        player.forceAddEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 15 * 20, 1, false ,false, false), player);
                        player.forceAddEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 15 * 20, 0, false ,false, false), player);
                    }
                }

                case PowerfulAroma -> {
                    List<Player> entities = level.getEntities(EntityType.PLAYER, actuationRange, player -> true);
                    for (Player player:entities) {
                        player.forceAddEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 15 * 20, 1, false ,false, false), player);
                    }
                }

                case StimulatingAroma -> {
                    List<Player> entities = level.getEntities(EntityType.PLAYER, actuationRange, player -> true);
                    for (Player player:entities) {
                        player.forceAddEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 15 * 20, 0, false ,false, false), player);
                        player.forceAddEffect(new MobEffectInstance(MobEffects.LUCK, 15 * 20, 0, false ,false, false), player);
                    }
                }

                case CursedAroma -> {
                    List<Entity> entities = level.getEntities(null, actuationRange);
                    for (Entity mob:entities) {
                        if(mob instanceof Pig || (mob instanceof Creeper creeper && !creeper.isPowered())|| mob instanceof Villager || (mob instanceof MushroomCow cow  && cow.getVariant().equals(MushroomCow.MushroomType.RED))){
                            mob.thunderHit((ServerLevel) pLevel, new LightningBolt(EntityType.LIGHTNING_BOLT, pLevel));
                        }
                    }
                }

                case PotionAroma -> {
                    List<Entity> entities = level.getEntities(null, actuationRange);
                    ItemStack stack = entity.getInventory().getStackInSlot(0);
                    for (Entity mob:entities) {
                        if(stack.getItem() instanceof PotionItem && mob instanceof LivingEntity livingEntity){
                            List<MobEffectInstance> effects = PotionUtils.getMobEffects(stack);
                            for (MobEffectInstance i:effects) {
                                MobEffectInstance cloned = new MobEffectInstance(i.getEffect(), 15 * 20, i.getAmplifier());
                                livingEntity.addEffect(cloned);
                            }
                        }
                    }
                }
            }
        }
    }


    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithFullMetadata();
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        // Will get tag from #getUpdateTag
        return ClientboundBlockEntityDataPacket.create(this, BlockEntity::saveWithFullMetadata);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
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
                if (!isItemValid(slot, stack)) {
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    public enum Aromas {
        CalmingAroma(
                ItemTagDataGen.CALMING_AROMA_INCENSE,
                0x6f96d9
        ),
        PacifyAroma(
                ItemTagDataGen.PACIFY_AROMA_INCENSE,
                0xb775f0
        ),
        HastyAroma(
                ItemTagDataGen.HASTY_AROMA_INCENSE,
                0xff974d
        ),

        PowerfulAroma(
                ItemTagDataGen.POWERFUL_AROMA_INCENSE,
                0xf75145
        ),
        StimulatingAroma(
                ItemTagDataGen.STIMULATING_AROMA_INCENSE,
                0xe85f96
        ),

        CursedAroma(
                ItemTagDataGen.CURSED_AROMA_INCENSE,
                0x303030
        ),

        PotionAroma(
                ItemTagDataGen.POTION_AROMA_INCENSE,
                0
        ),
        DecorativeAroma(
                Tags.Items.DYES,
                0
        ),


        INVALID(ItemTagDataGen.INVALID_INCENSE, 0xffffff);

        public TagKey<Item> items;
        public int color;

        Aromas(TagKey<Item> items, int color) {
            this.items = items;
            this.color = color;
        }

        public static boolean isStackValid(ItemStack stack) {

            for (Aromas aroma : values()) {
                if (stack.is(aroma.items)) {
                    return true;
                }
            }
            return false;
        }

        public static int getAromaId(ItemStack stack) {
            for (Aromas aroma : values()) {
                if (stack.is(aroma.items)) {
                    return Arrays.stream(values()).toList().indexOf(aroma);
                }
            }
            return -1;
        }

    }
}
