package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import java.util.concurrent.CompletableFuture;

public class ItemTagDataGen extends  FabricTagProvider.ItemTagProvider {

    public static TagKey<Item> CAN_IGNITE_INCENSE = TagKey.of(RegistryKeys.ITEM, new Identifier(KawaiiDishes.MODID, "can_ignite_incense"));
    public static TagKey<Item> CAN_EXTINGUISH_INCENSE = TagKey.of(RegistryKeys.ITEM, new Identifier(KawaiiDishes.MODID, "can_extinguish_incense"));
    public static TagKey<Item> CALMING_AROMA_INCENSE = TagKey.of(RegistryKeys.ITEM, new Identifier(KawaiiDishes.MODID, "calming_aroma_incense"));
    public static TagKey<Item> PACIFY_AROMA_INCENSE = TagKey.of(RegistryKeys.ITEM, new Identifier(KawaiiDishes.MODID, "pacify_aroma_incense"));
    public static TagKey<Item> HASTY_AROMA_INCENSE = TagKey.of(RegistryKeys.ITEM, new Identifier(KawaiiDishes.MODID, "hasty_aroma_incense"));
    public static TagKey<Item> POWERFUL_AROMA_INCENSE = TagKey.of(RegistryKeys.ITEM, new Identifier(KawaiiDishes.MODID, "powerful_aroma_incense"));
    public static TagKey<Item> STIMULATING_AROMA_INCENSE = TagKey.of(RegistryKeys.ITEM, new Identifier(KawaiiDishes.MODID, "stimulating_aroma_incense"));
    public static TagKey<Item> CURSED_AROMA_INCENSE = TagKey.of(RegistryKeys.ITEM, new Identifier(KawaiiDishes.MODID, "cursed_aroma_incense"));
    public static TagKey<Item> POTION_AROMA_INCENSE = TagKey.of(RegistryKeys.ITEM, new Identifier(KawaiiDishes.MODID, "potion_aroma_incense"));
    public static TagKey<Item> COOKIES = TagKey.of(RegistryKeys.ITEM, new Identifier(KawaiiDishes.MODID, "cookies"));
    public ItemTagDataGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup provider) {
        getOrCreateTagBuilder(CAN_IGNITE_INCENSE)
                .add(
                        Items.FIRE_CHARGE,
                        Items.FLINT_AND_STEEL
                );

        getOrCreateTagBuilder(CAN_EXTINGUISH_INCENSE)
                .add(
                        Items.WATER_BUCKET
                );


        getOrCreateTagBuilder(CALMING_AROMA_INCENSE)
                .add(
                        Items.CORNFLOWER,
                        Items.BLUE_ORCHID
                );

        getOrCreateTagBuilder(PACIFY_AROMA_INCENSE)
                .add(
                        Items.LILAC,
                        Items.ALLIUM,
                        Items.PEONY
                );

        getOrCreateTagBuilder(HASTY_AROMA_INCENSE)
                .add(
                        Items.DANDELION,
                        Items.ORANGE_TULIP,
                        Items.SUNFLOWER
                );

        getOrCreateTagBuilder(POWERFUL_AROMA_INCENSE)
                .add(
                        Items.POPPY,
                        Items.RED_TULIP,
                        Items.ROSE_BUSH
                );

        getOrCreateTagBuilder(STIMULATING_AROMA_INCENSE)
                .add(
                        Items.OXEYE_DAISY,
                        Items.WHITE_TULIP,
                        Items.PINK_TULIP,
                        Items.PINK_PETALS,
                        Items.SPORE_BLOSSOM
                );

        getOrCreateTagBuilder(CURSED_AROMA_INCENSE)
                .add(
                        Items.WITHER_ROSE
                );

        getOrCreateTagBuilder(POTION_AROMA_INCENSE)
                .add(
                        Items.LINGERING_POTION
                );

        getOrCreateTagBuilder(COOKIES)
                .add(
                        Items.COOKIE,
                        ItemRegister.GLOW_BERRY_COOKIE.get(),
                        ItemRegister.CHOCOLATE_COOKIE.get(),
                        ItemRegister.SWEET_BERRY_COOKIE.get()
                );

    }
}
