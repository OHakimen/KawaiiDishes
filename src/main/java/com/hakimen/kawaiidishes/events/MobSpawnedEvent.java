package com.hakimen.kawaiidishes.events;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.aromas.PacifyAroma;
import com.hakimen.kawaiidishes.block.IncenseBlock;
import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.config.ServerConfig;
import com.hakimen.kawaiidishes.item.IDyeableItem;
import com.hakimen.kawaiidishes.item.IFourColorDyeableItem;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid = KawaiiDishes.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MobSpawnedEvent {
    public static List<ItemStack> makeSet(RandomSource source, ArmorSets set) {

        ItemStack headSlot = new ItemStack(set.head);
        ItemStack chestSlot = new ItemStack(set.chest);
        ItemStack legSlot = new ItemStack(ItemRegister.THIGH_HIGHS.get());
        ItemStack feetSlot = ItemStack.EMPTY;


        //Pick a base color
        int base = source.nextInt(1, 15);

        //Pick an overlay color
        int overlay = 0;


        if (ArmorSets.isQuadColor(set)) {
            int base2 = source.nextInt(0, 15);
            int overlay2 = source.nextFloat() < 0.25f ? source.nextInt(0, 15) : -1;

            headSlot = dyePiece(headSlot, base, overlay, base2, overlay2);
            headSlot.getOrCreateTag().putBoolean("HasPrimaryOverlay", true);
            headSlot.getOrCreateTag().putBoolean("HasSecondaryOverlay", overlay2 != -1);

            chestSlot = dyePiece(chestSlot, base, overlay, base2, overlay2);
            chestSlot.getOrCreateTag().putBoolean("HasPrimaryOverlay", true);
            chestSlot.getOrCreateTag().putBoolean("HasSecondaryOverlay", true);

            feetSlot = new ItemStack(ItemRegister.SHOES.get());
            feetSlot = dyePiece(feetSlot, base, overlay);
            feetSlot.getOrCreateTag().putBoolean("HasOverlay", true);
        } else {
            headSlot = dyePiece(headSlot, base, overlay);
            headSlot.getOrCreateTag().putBoolean("HasOverlay", true);

            chestSlot = dyePiece(chestSlot, base, overlay);
            chestSlot.getOrCreateTag().putBoolean("HasOverlay", true);
            if (set.equals(ArmorSets.Maid)) {
                feetSlot = new ItemStack(ItemRegister.SHOES.get());
                feetSlot = dyePiece(feetSlot, base, overlay);
                feetSlot.getOrCreateTag().putBoolean("HasOverlay", true);
            }
        }
        legSlot = dyePiece(legSlot, base, overlay);
        legSlot.getOrCreateTag().putInt("Decoration", source.nextInt(0, 5));

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

    @SubscribeEvent
    public static void finalizeSpawnEvent(MobSpawnEvent.FinalizeSpawn event) {
        Entity entity = event.getEntity();
        RandomSource source = event.getLevel().getRandom();
        if (!entity.isAddedToWorld() && entity instanceof Monster monster &&
                (monster instanceof Skeleton
                        || monster instanceof WitherSkeleton
                        || monster instanceof Stray
                        || monster instanceof Zombie
                        || monster instanceof Piglin
                        || monster instanceof PiglinBrute) && source.nextFloat() < ServerConfig.dressedMobsSpawnRate.get()) {

            int set = source.nextInt(0, ArmorSets.values().length);

            List<ItemStack> armor = makeSet(source, ArmorSets.values()[set]);

            monster.setItemSlot(EquipmentSlot.HEAD, armor.get(0));
            monster.setItemSlot(EquipmentSlot.CHEST, armor.get(1));
            monster.setItemSlot(EquipmentSlot.LEGS, armor.get(2));
            monster.setItemSlot(EquipmentSlot.FEET, armor.get(3));

            monster.setDropChance(EquipmentSlot.HEAD, ServerConfig.dressedDropRate.get().floatValue());
            monster.setDropChance(EquipmentSlot.CHEST, ServerConfig.dressedDropRate.get().floatValue());
            monster.setDropChance(EquipmentSlot.LEGS, ServerConfig.dressedDropRate.get().floatValue());
            monster.setDropChance(EquipmentSlot.FEET, ServerConfig.dressedDropRate.get().floatValue());
        }
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
