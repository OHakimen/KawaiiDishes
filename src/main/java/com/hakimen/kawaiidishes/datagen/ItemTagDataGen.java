package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ItemTagDataGen extends ItemTagsProvider {

    public static TagKey<Item> CAN_IGNITE_INCENSE = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "can_ignite_incense"));
    public static TagKey<Item> CAN_EXTINGUISH_INCENSE = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "can_extinguish_incense"));
    public static TagKey<Item> CALMING_AROMA_INCENSE = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "calming_aroma_incense"));
    public static TagKey<Item> PACIFY_AROMA_INCENSE = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "pacify_aroma_incense"));
    public static TagKey<Item> HASTY_AROMA_INCENSE = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "hasty_aroma_incense"));
    public static TagKey<Item> POWERFUL_AROMA_INCENSE = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "powerful_aroma_incense"));
    public static TagKey<Item> STIMULATING_AROMA_INCENSE = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "stimulating_aroma_incense"));
    public static TagKey<Item> CURSED_AROMA_INCENSE = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "cursed_aroma_incense"));
    public static TagKey<Item> POTION_AROMA_INCENSE = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "potion_aroma_incense"));
    public static TagKey<Item> COOKIES = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "cookies"));

    public static TagKey<Item> OVERLAYABLE = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "overlayable"));
    public static TagKey<Item> THIGH_HIGH_DECOR = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "thigh_high_decorations"));


    public ItemTagDataGen(DataGenerator generator, CompletableFuture<HolderLookup.Provider> lookupProvider, BlockTagsProvider provider) {
        super(generator.getPackOutput(), lookupProvider, provider.contentsGetter());
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

        tag(OVERLAYABLE)
                .add(ItemRegister.CAT_EARS.get())
                .add(ItemRegister.BUNNY_EARS.get())
                .add(ItemRegister.FOX_EARS.get())
                .add(ItemRegister.CAT_TAIL.get())
                .add(ItemRegister.BUNNY_TAIL.get())
                .add(ItemRegister.FOX_TAIL.get())
                .add(ItemRegister.SHOES.get())
                .add(ItemRegister.HEAD_BAND.get());

        tag(THIGH_HIGH_DECOR)
                .add(ItemRegister.BOW.get())
                .add(ItemRegister.LEG_CLIP.get())
                .add(ItemRegister.DOUBLE_BANDS.get())
                .add(ItemRegister.FULL_BANDS.get());
    }
}
