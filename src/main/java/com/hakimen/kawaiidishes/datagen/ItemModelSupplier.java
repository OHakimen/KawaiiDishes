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
        block(BlockRegister.mug.get());
        simpleItem(ItemRegister.coffeeFruit.get());
        simpleItem(ItemRegister.driedCoffeeBeans.get());
        simpleItem(ItemRegister.roastedCoffeeBeans.get());
    }

    private ItemModelBuilder simpleItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(KawaiiDishes.modId,"item/" + item.getRegistryName().getPath()));
    }

    private ItemModelBuilder block(Block block){
        return withExistingParent(block.getRegistryName().getPath(),new ResourceLocation(KawaiiDishes.modId,"block/"+block.getRegistryName().getPath()));
    }
}
