package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModelSupplier extends ItemModelProvider {
    public ItemModelSupplier(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, KawaiiDishes.modId, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (var block:BlockRegister.BLOCKS.getEntries().stream().toList()) {
            if(block.get().getRegistryName().toString().contains("_stool")){
                stool(block.get());
            }else if(block.get().getRegistryName().toString().contains("coffee_bush") || block.get().getRegistryName().toString().contains("mortar_and_pestle")){
                continue;
            }else
                block(block.get());
        }
        for(var item : ItemRegister.ITEMS.getEntries().stream().toList()){
            if(item.get().getRegistryName().toString().contains("_headband")){
                if(item.get().getRegistryName().toString().endsWith("_cat_ears_black")){
                    catBandItem(item.get(),"cat_ears_black");
                }
                else if(item.get().getRegistryName().toString().endsWith("_cat_ears_white")){
                    catBandItem(item.get(),"cat_ears_white");
                }
                else if(item.get().getRegistryName().toString().endsWith("_cat_ears_caramel")) {
                    catBandItem(item.get(),"cat_ears_caramel");
                }
                else if(item.get().getRegistryName().toString().endsWith("_fox_ears_black")){
                    foxBandItem(item.get(),"cat_ears_black","fox_ears_black");
                }
                else if(item.get().getRegistryName().toString().endsWith("_fox_ears_white")){
                    foxBandItem(item.get(),"cat_ears_white","fox_ears_white");
                }
                else if(item.get().getRegistryName().toString().endsWith("_fox_ears_red")) {
                    foxBandItem(item.get(),"fox_ears_red","fox_ears_red");
                }
                else{
                    headBandItem(item.get());
                }
            }else if(item.get().getRegistryName().toString().contains("_maid_dress")){
                maidDressItem(item.get());
            }
        }


        simpleItem(ItemRegister.whiteChocolateBar.get());
        simpleItem(ItemRegister.darkChocolateBar.get());
        simpleItem(ItemRegister.milkChocolateBar.get());

        simpleItem(ItemRegister.condensedMilk.get());
        simpleItem(ItemRegister.brigadeiroMix.get());

        simpleItem(ItemRegister.driedCocoaBeans.get());
        simpleItem(ItemRegister.roastedCocoaBeans.get());
        simpleItem(ItemRegister.cocoaPowder.get());

        simpleItem(ItemRegister.coffeeFruit.get());
        simpleItem(ItemRegister.driedCoffeeBeans.get());
        simpleItem(ItemRegister.roastedCoffeeBeans.get());
        simpleItem(ItemRegister.coffeePowder.get());

        simpleItem(ItemRegister.blackThighHighs.get());
        simpleItem(ItemRegister.whiteThighHighs.get());
        simpleItem(ItemRegister.blackThighHighsShoes.get());
        simpleItem(ItemRegister.whiteThighHighsShoes.get());


    }

    private ItemModelBuilder simpleItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(KawaiiDishes.modId,"item/" + item.getRegistryName().getPath()));
    }
    private ItemModelBuilder maidDressItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(KawaiiDishes.modId,"item/maid_dresses/" + item.getRegistryName().getPath()));
    }
    private ItemModelBuilder catBandItem(Item item,String type) {
        return withExistingParent(
                item.getRegistryName().getPath(),
                new ResourceLocation(KawaiiDishes.modId,"item/cat_headband"))
                .texture("0", new ResourceLocation(KawaiiDishes.modId,"item/" + type))
                .texture("1", new ResourceLocation(KawaiiDishes.modId,"item/headbands/" + item.getRegistryName().getPath().replaceAll("_"+type,"")));

    }
    private ItemModelBuilder foxBandItem(Item item,String type,String name) {
        return withExistingParent(
                item.getRegistryName().getPath(),
                new ResourceLocation(KawaiiDishes.modId,"item/fox_headband"))
                .texture("0", new ResourceLocation(KawaiiDishes.modId,"item/" + type))
                .texture("1", new ResourceLocation(KawaiiDishes.modId,"item/headbands/" + item.getRegistryName().getPath().replaceAll("_"+name,"")));

    }
    private ItemModelBuilder headBandItem(Item item) {
        return withExistingParent(
                item.getRegistryName().getPath(),
                new ResourceLocation(KawaiiDishes.modId,"item/headband"))
                .texture("0", new ResourceLocation(KawaiiDishes.modId,"item/headbands/" + item.getRegistryName().getPath()));

    }
    private ItemModelBuilder block(Block block){
        return withExistingParent(block.getRegistryName().getPath(),new ResourceLocation(KawaiiDishes.modId,"block/"+block.getRegistryName().getPath()));
    }
    private ItemModelBuilder stool(Block block){
        return withExistingParent(block.getRegistryName().getPath(),new ResourceLocation(KawaiiDishes.modId,"block/stools/"+block.getRegistryName().getPath()));
    }
}
