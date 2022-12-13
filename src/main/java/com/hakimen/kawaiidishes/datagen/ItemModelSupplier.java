package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.core.Registry;
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
            String path = Registry.BLOCK.getKey(block.get()).toString().replaceAll(KawaiiDishes.modId+":","");
            if(path.contains("_stool")){
                stool(block.get());
            }else if(path.endsWith("milkshake")){
                milkshake(block.get());
            }else if(path.endsWith("coffee")){
                coffee(block.get());
            }else if(path.endsWith("ice_cream")){
                iceCream(block.get());
            } else if(path.contains("coffee_bush") || path.contains("mortar_and_pestle")){
                continue;
            }else
                block(block.get());
        }
        for(var item : ItemRegister.ITEMS.getEntries().stream().toList()){
            String path = Registry.ITEM.getKey(item.get()).toString().replaceAll(KawaiiDishes.modId+":","");
            if(path.contains("_headband")){
                if(path.endsWith("_cat_ears_black")){
                    catBandItem(item.get(),"cat_ears_black");
                }
                else if(path.endsWith("_cat_ears_white")){
                    catBandItem(item.get(),"cat_ears_white");
                }
                else if(path.endsWith("_cat_ears_caramel")) {
                    catBandItem(item.get(),"cat_ears_caramel");
                }
                else if(path.endsWith("_fox_ears_black")){
                    foxBandItem(item.get(),"cat_ears_black","fox_ears_black");
                }
                else if(path.endsWith("_fox_ears_white")){
                    foxBandItem(item.get(),"cat_ears_white","fox_ears_white");
                }
                else if(path.endsWith("_fox_ears_red")) {
                    foxBandItem(item.get(),"fox_ears_red","fox_ears_red");
                }
                else{
                    headBandItem(item.get());
                }
            }else if(path.contains("_maid_dress")){
                maidDressItem(item.get());
            }
        }


        cookieItem(ItemRegister.sweetBerryCookie.get());
        cookieItem(ItemRegister.chocolateCookie.get());
        cookieItem(ItemRegister.honeyCookie.get());
        cookieItem(ItemRegister.goldenCookie.get());
        cookieItem(ItemRegister.unbindingCookie.get());

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
        return withExistingParent(Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":",""),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(KawaiiDishes.modId,"item/" + Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":","")));
    }
    private ItemModelBuilder maidDressItem(Item item) {
        return withExistingParent(Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":",""),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(KawaiiDishes.modId,"item/maid_dresses/" + Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":","")));
    }

    private ItemModelBuilder cookieItem(Item item) {
        return withExistingParent(Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":",""),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(KawaiiDishes.modId,"item/cookie/" + Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":","")));
    }
    private ItemModelBuilder catBandItem(Item item,String type) {
        return withExistingParent(
                Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":",""),
                new ResourceLocation(KawaiiDishes.modId,"item/cat_headband"))
                .texture("0", new ResourceLocation(KawaiiDishes.modId,"item/" + type))
                .texture("1", new ResourceLocation(KawaiiDishes.modId,"item/headbands/" + Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":","").replaceAll("_"+type,"")));

    }
    private ItemModelBuilder foxBandItem(Item item,String type,String name) {
        return withExistingParent(
                Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":",""),
                new ResourceLocation(KawaiiDishes.modId,"item/fox_headband"))
                .texture("0", new ResourceLocation(KawaiiDishes.modId,"item/" + type))
                .texture("1", new ResourceLocation(KawaiiDishes.modId,"item/headbands/" + Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":","").replaceAll("_"+name,"")));

    }
    private ItemModelBuilder headBandItem(Item item) {
        return withExistingParent(
                Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":",""),
                new ResourceLocation(KawaiiDishes.modId,"item/headband"))
                .texture("0", new ResourceLocation(KawaiiDishes.modId,"item/headbands/" + Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":","")));

    }
    private ItemModelBuilder block(Block block){
        return withExistingParent(Registry.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId+":",""),new ResourceLocation(KawaiiDishes.modId,"block/"+Registry.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId+":","")));
    }

    private ItemModelBuilder milkshake(Block block){
        return withExistingParent(Registry.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId+":",""),new ResourceLocation(KawaiiDishes.modId,"block/milk_shakes/"+Registry.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId+":","")));
    }
    private ItemModelBuilder coffee(Block block){
        return withExistingParent(Registry.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId+":",""),new ResourceLocation(KawaiiDishes.modId,"block/coffees/"+Registry.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId+":","")));
    }
    private ItemModelBuilder iceCream(Block block){
        return withExistingParent(Registry.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId+":",""),new ResourceLocation(KawaiiDishes.modId,"block/ice_creams/"+Registry.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId+":","")));
    }

    private ItemModelBuilder stool(Block block){
        return withExistingParent(Registry.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId+":",""),new ResourceLocation(KawaiiDishes.modId,"block/stools/"+Registry.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId+":","")));
    }
}
