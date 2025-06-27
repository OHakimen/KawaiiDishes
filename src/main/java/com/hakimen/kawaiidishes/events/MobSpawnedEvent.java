package com.hakimen.kawaiidishes.events;

import com.hakimen.kawaiidishes.block.IncenseBlock;
import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.configs.ServerConfig;
import com.hakimen.kawaiidishes.item.IDyeableItem;
import com.hakimen.kawaiidishes.item.IFourColorDyeableItem;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.StrayEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.random.Random;
import java.util.List;

public class MobSpawnedEvent {

    public static void handle(){
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            Random source = world.getRandom();
            if (entity instanceof HostileEntity monster &&
                    (monster instanceof SkeletonEntity
                            || monster instanceof WitherSkeletonEntity
                            || monster instanceof StrayEntity
                            || monster instanceof ZombieEntity
                            || monster instanceof PiglinEntity
                            || monster instanceof PiglinBruteEntity) && source.nextFloat() < ServerConfig.dressedMobsSpawnRate.get()) {

                int set = source.nextBetweenExclusive(0, ArmorSets.values().length);

                List<ItemStack> armor = makeSet(source, ArmorSets.values()[set]);

                monster.equipStack(EquipmentSlot.HEAD, armor.get(0));
                monster.equipStack(EquipmentSlot.CHEST, armor.get(1));
                monster.equipStack(EquipmentSlot.LEGS, armor.get(2));
                monster.equipStack(EquipmentSlot.FEET, armor.get(3));

                monster.setEquipmentDropChance(EquipmentSlot.HEAD, ServerConfig.dressedDropRate.get().floatValue());
                monster.setEquipmentDropChance(EquipmentSlot.CHEST, ServerConfig.dressedDropRate.get().floatValue());
                monster.setEquipmentDropChance(EquipmentSlot.LEGS, ServerConfig.dressedDropRate.get().floatValue());
                monster.setEquipmentDropChance(EquipmentSlot.FEET, ServerConfig.dressedDropRate.get().floatValue());
            }
        });
    }

    public static List<ItemStack> makeSet(Random source, ArmorSets set) {

        ItemStack headSlot = new ItemStack(set.head);
        ItemStack chestSlot = new ItemStack(set.chest);
        ItemStack legSlot = new ItemStack(ItemRegister.THIGH_HIGHS.get());
        ItemStack feetSlot = ItemStack.EMPTY;


        //Pick a base color
        int base = source.nextBetweenExclusive(1, 15);

        //Pick an overlay color
        int overlay = 0;


        if (ArmorSets.isQuadColor(set)) {
            int base2 = source.nextBetweenExclusive(0, 15);
            int overlay2 = source.nextFloat() < 0.25f ? source.nextBetweenExclusive(0, 15) : -1;

            headSlot = dyePiece(headSlot, base, overlay, base2, overlay2);
            headSlot.getOrCreateNbt().putBoolean("HasPrimaryOverlay", true);
            headSlot.getOrCreateNbt().putBoolean("HasSecondaryOverlay", overlay2 != -1);

            chestSlot = dyePiece(chestSlot, base, overlay, base2, overlay2);
            chestSlot.getOrCreateNbt().putBoolean("HasPrimaryOverlay", true);
            chestSlot.getOrCreateNbt().putBoolean("HasSecondaryOverlay", true);

            feetSlot = new ItemStack(ItemRegister.SHOES.get());
            feetSlot = dyePiece(feetSlot, base, overlay);
            feetSlot.getOrCreateNbt().putBoolean("HasOverlay", true);
        } else {
            headSlot = dyePiece(headSlot, base, overlay);
            headSlot.getOrCreateNbt().putBoolean("HasOverlay", true);

            chestSlot = dyePiece(chestSlot, base, overlay);
            chestSlot.getOrCreateNbt().putBoolean("HasOverlay", true);
            if (set.equals(ArmorSets.Maid)) {
                feetSlot = new ItemStack(ItemRegister.SHOES.get());
                feetSlot = dyePiece(feetSlot, base, overlay);
                feetSlot.getOrCreateNbt().putBoolean("HasOverlay", true);
            }
        }
        legSlot = dyePiece(legSlot, base, overlay);
        legSlot.getOrCreateNbt().putInt("Decoration", source.nextBetweenExclusive(0, 5));

        return List.of(headSlot, chestSlot, legSlot, feetSlot);
    }

    public static ItemStack dyePiece(ItemStack stacc, int base, int overlay, int base2, int overlay2) {
        ItemStack stack = stacc.copy();
        stack = IFourColorDyeableItem.dyePrimaryBase(stack, List.of(DyeItem.byColor(DyeColor.byId(base))));
        stack = IFourColorDyeableItem.dyePrimaryOverlay(stack, List.of(DyeItem.byColor(DyeColor.byId(overlay))));
        stack = IFourColorDyeableItem.dyeSecondaryBase(stack, List.of(DyeItem.byColor(DyeColor.byId(base2))));
        if (overlay2 != -1) {
            stack = IFourColorDyeableItem.dyeSecondaryOverlay(stack, List.of(DyeItem.byColor(DyeColor.byId(overlay2))));
        }
        return stack;
    }

    public static ItemStack dyePiece(ItemStack stacc, int base, int overlay) {
        ItemStack stack = stacc.copy();
        stack = IDyeableItem.dyeBase(stack, List.of(DyeItem.byColor(DyeColor.byId(base))));
        stack = IDyeableItem.dyeOverlay(stack, List.of(DyeItem.byColor(DyeColor.byId(overlay))));
        return stack;
    }


    enum ArmorSets {
        Bunny(ItemRegister.BUNNY_EARS.get(), ItemRegister.BUNNY_TAIL.get()),
        Cat(ItemRegister.CAT_EARS.get(), ItemRegister.CAT_TAIL.get()),
        Fox(ItemRegister.FOX_EARS.get(), ItemRegister.FOX_TAIL.get()),
        MaidBunny(ItemRegister.HEAD_BAND_BUNNY_EARS.get(), ItemRegister.MAID_DRESS_BUNNY_TAIL.get()),
        MaidCat(ItemRegister.HEAD_BAND_CAT_EARS.get(), ItemRegister.MAID_DRESS_CAT_TAIL.get()),
        MaidFox(ItemRegister.HEAD_BAND_FOX_EARS.get(), ItemRegister.MAID_DRESS_FOX_TAIL.get()),
        Maid(ItemRegister.HEAD_BAND.get(), ItemRegister.MAID_DRESS.get());
        public final Item head, chest;

        ArmorSets(Item head, Item chest) {
            this.head = head;
            this.chest = chest;
        }

        public static boolean isQuadColor(ArmorSets set) {
            return set.equals(MaidCat) ||
                    set.equals(MaidFox) ||
                    set.equals(MaidBunny);
        }
    }
}
