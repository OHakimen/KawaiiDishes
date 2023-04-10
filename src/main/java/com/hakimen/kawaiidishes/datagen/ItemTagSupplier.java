package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ItemTagSupplier extends ItemTagsProvider {
    public ItemTagSupplier(DataGenerator pGenerator, CompletableFuture<HolderLookup.Provider> lookup, BlockTagsProvider pBlockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator.getPackOutput(), lookup, pBlockTagsProvider, KawaiiDishes.modId ,existingFileHelper);
    }
    TagKey<Item> bunny_suits = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(),new ResourceLocation(KawaiiDishes.modId,"bunny_suits"));

    TagKey<Item> maid_dresses = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(),new ResourceLocation(KawaiiDishes.modId,"maid_dresses"));
    TagKey<Item> tailed_maid_dresses = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(),new ResourceLocation(KawaiiDishes.modId,"tailed_maid_dresses"));
    TagKey<Item> tails = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(),new ResourceLocation(KawaiiDishes.modId,"tails"));
    TagKey<Item> ears = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(),new ResourceLocation(KawaiiDishes.modId,"ears"));
    TagKey<Item> headbands = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(),new ResourceLocation(KawaiiDishes.modId,"headbands"));
    TagKey<Item> eared_headbands = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(),new ResourceLocation(KawaiiDishes.modId,"eared_headbands"));


    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        for(var item: ItemRegister.ITEMS.getEntries()) {
            String name = item.get().toString();
            if (name.contains("maid_dress")) {
                if (name.contains("tail")) {
                    this.tag(tailed_maid_dresses).add(item.get());
                } else {
                    this.tag(maid_dresses).add(item.get());
                }
            }else if (name.contains("bunny_suit")) {
                this.tag(bunny_suits).add(item.get());
            } else if (name.contains("headband")) {
                if (name.contains("ears") || name.contains("horns")) {
                    this.tag(eared_headbands).add(item.get());
                } else {
                    this.tag(headbands).add(item.get());
                }
            } else if (name.contains("tail")) {
                this.tag(tails).add(item.get());
            } else if (name.contains("ears") || name.contains("horns")) {
                this.tag(ears).add(item.get());
            }
        }
        this.tag(maid_dresses).addTags(tailed_maid_dresses);
        this.tag(tails).addTags(tailed_maid_dresses);
        this.tag(tails).addTags(bunny_suits);
        this.tag(headbands).addTags(eared_headbands);
    }
}
