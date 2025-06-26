package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import java.util.concurrent.CompletableFuture;

public class ItemTagDataGen extends  FabricTagProvider.ItemTagProvider {

    public static TagKey<Item> CAN_IGNITE_INCENSE = TagKey.create(Registries.ITEM, new ResourceLocation(KawaiiDishes.MODID, "can_ignite_incense"));
    public static TagKey<Item> CAN_EXTINGUISH_INCENSE = TagKey.create(Registries.ITEM, new ResourceLocation(KawaiiDishes.MODID, "can_extinguish_incense"));
    public static TagKey<Item> CALMING_AROMA_INCENSE = TagKey.create(Registries.ITEM, new ResourceLocation(KawaiiDishes.MODID, "calming_aroma_incense"));
    public static TagKey<Item> PACIFY_AROMA_INCENSE = TagKey.create(Registries.ITEM, new ResourceLocation(KawaiiDishes.MODID, "pacify_aroma_incense"));
    public static TagKey<Item> HASTY_AROMA_INCENSE = TagKey.create(Registries.ITEM, new ResourceLocation(KawaiiDishes.MODID, "hasty_aroma_incense"));
    public static TagKey<Item> POWERFUL_AROMA_INCENSE = TagKey.create(Registries.ITEM, new ResourceLocation(KawaiiDishes.MODID, "powerful_aroma_incense"));
    public static TagKey<Item> STIMULATING_AROMA_INCENSE = TagKey.create(Registries.ITEM, new ResourceLocation(KawaiiDishes.MODID, "stimulating_aroma_incense"));
    public static TagKey<Item> CURSED_AROMA_INCENSE = TagKey.create(Registries.ITEM, new ResourceLocation(KawaiiDishes.MODID, "cursed_aroma_incense"));
    public static TagKey<Item> POTION_AROMA_INCENSE = TagKey.create(Registries.ITEM, new ResourceLocation(KawaiiDishes.MODID, "potion_aroma_incense"));
    public static TagKey<Item> COOKIES = TagKey.create(Registries.ITEM, new ResourceLocation(KawaiiDishes.MODID, "cookies"));
    public ItemTagDataGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(CAN_IGNITE_INCENSE)
                .add(
                        Items.FIRE_CHARGE,
                        Items.FLINT_AND_STEEL
                );

        tag(CAN_EXTINGUISH_INCENSE)
                .add(
                        Items.WATER_BUCKET
                );


        tag(CALMING_AROMA_INCENSE)
                .add(
                        Items.CORNFLOWER,
                        Items.BLUE_ORCHID
                );

        tag(PACIFY_AROMA_INCENSE)
                .add(
                        Items.LILAC,
                        Items.ALLIUM,
                        Items.PEONY
                );

        tag(HASTY_AROMA_INCENSE)
                .add(
                        Items.DANDELION,
                        Items.ORANGE_TULIP,
                        Items.SUNFLOWER
                );

        tag(POWERFUL_AROMA_INCENSE)
                .add(
                        Items.POPPY,
                        Items.RED_TULIP,
                        Items.ROSE_BUSH
                );

        tag(STIMULATING_AROMA_INCENSE)
                .add(
                        Items.OXEYE_DAISY,
                        Items.WHITE_TULIP,
                        Items.PINK_TULIP,
                        Items.PINK_PETALS,
                        Items.SPORE_BLOSSOM
                );

        tag(CURSED_AROMA_INCENSE)
                .add(
                        Items.WITHER_ROSE
                );

        tag(POTION_AROMA_INCENSE)
                .add(
                        Items.LINGERING_POTION
                );

        tag(COOKIES)
                .add(
                        Items.COOKIE,
                        ItemRegister.GLOW_BERRY_COOKIE.get(),
                        ItemRegister.CHOCOLATE_COOKIE.get(),
                        ItemRegister.SWEET_BERRY_COOKIE.get()
                );

    }
}
