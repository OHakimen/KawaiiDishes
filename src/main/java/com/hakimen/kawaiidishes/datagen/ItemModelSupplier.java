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
        block(BlockRegister.expressoMug.get());
        block(BlockRegister.americanMug.get());
        block(BlockRegister.latteMug.get());
        block(BlockRegister.mochaMug.get());
        block(BlockRegister.cappuccinoMug.get());
        block(BlockRegister.doppioMug.get());
        block(BlockRegister.cafeAuLaitMug.get());
        block(BlockRegister.macchiatoMug.get());

        block(BlockRegister.coffeePress.get());
        block(BlockRegister.coffeeMachine.get());

        simpleItem(ItemRegister.coffeeFruit.get());
        simpleItem(ItemRegister.driedCoffeeBeans.get());
        simpleItem(ItemRegister.roastedCoffeeBeans.get());
        simpleItem(ItemRegister.coffeePowder.get());

        simpleItem(ItemRegister.blackMaidDress.get());
        simpleItem(ItemRegister.blackMaidDressCatTailBlack.get());
        simpleItem(ItemRegister.blackMaidDressCatTailCaramel.get());
        simpleItem(ItemRegister.blackMaidDressCatTailWhite.get());

        simpleItem(ItemRegister.whiteMaidDress.get());
        simpleItem(ItemRegister.whiteMaidDressCatTailBlack.get());
        simpleItem(ItemRegister.whiteMaidDressCatTailCaramel.get());
        simpleItem(ItemRegister.whiteMaidDressCatTailWhite.get());

        simpleItem(ItemRegister.grayMaidDress.get());
        simpleItem(ItemRegister.grayMaidDressCatTailBlack.get());
        simpleItem(ItemRegister.grayMaidDressCatTailCaramel.get());
        simpleItem(ItemRegister.grayMaidDressCatTailWhite.get());

        simpleItem(ItemRegister.light_grayMaidDress.get());
        simpleItem(ItemRegister.light_grayMaidDressCatTailBlack.get());
        simpleItem(ItemRegister.light_grayMaidDressCatTailCaramel.get());
        simpleItem(ItemRegister.light_grayMaidDressCatTailWhite.get());

        simpleItem(ItemRegister.light_blueMaidDress.get());
        simpleItem(ItemRegister.light_blueMaidDressCatTailBlack.get());
        simpleItem(ItemRegister.light_blueMaidDressCatTailCaramel.get());
        simpleItem(ItemRegister.light_blueMaidDressCatTailWhite.get());

        simpleItem(ItemRegister.blueMaidDress.get());
        simpleItem(ItemRegister.blueMaidDressCatTailBlack.get());
        simpleItem(ItemRegister.blueMaidDressCatTailCaramel.get());
        simpleItem(ItemRegister.blueMaidDressCatTailWhite.get());

        simpleItem(ItemRegister.limeMaidDress.get());
        simpleItem(ItemRegister.limeMaidDressCatTailBlack.get());
        simpleItem(ItemRegister.limeMaidDressCatTailCaramel.get());
        simpleItem(ItemRegister.limeMaidDressCatTailWhite.get());

        simpleItem(ItemRegister.greenMaidDress.get());
        simpleItem(ItemRegister.greenMaidDressCatTailBlack.get());
        simpleItem(ItemRegister.greenMaidDressCatTailCaramel.get());
        simpleItem(ItemRegister.greenMaidDressCatTailWhite.get());

        simpleItem(ItemRegister.orangeMaidDress.get());
        simpleItem(ItemRegister.orangeMaidDressCatTailBlack.get());
        simpleItem(ItemRegister.orangeMaidDressCatTailCaramel.get());
        simpleItem(ItemRegister.orangeMaidDressCatTailWhite.get());

        simpleItem(ItemRegister.redMaidDress.get());
        simpleItem(ItemRegister.redMaidDressCatTailBlack.get());
        simpleItem(ItemRegister.redMaidDressCatTailCaramel.get());
        simpleItem(ItemRegister.redMaidDressCatTailWhite.get());

        simpleItem(ItemRegister.pinkMaidDress.get());
        simpleItem(ItemRegister.pinkMaidDressCatTailBlack.get());
        simpleItem(ItemRegister.pinkMaidDressCatTailCaramel.get());
        simpleItem(ItemRegister.pinkMaidDressCatTailWhite.get());

        simpleItem(ItemRegister.magentaMaidDress.get());
        simpleItem(ItemRegister.magentaMaidDressCatTailBlack.get());
        simpleItem(ItemRegister.magentaMaidDressCatTailCaramel.get());
        simpleItem(ItemRegister.magentaMaidDressCatTailWhite.get());

        simpleItem(ItemRegister.magentaMaidDress.get());
        simpleItem(ItemRegister.magentaMaidDressCatTailBlack.get());
        simpleItem(ItemRegister.magentaMaidDressCatTailCaramel.get());
        simpleItem(ItemRegister.magentaMaidDressCatTailWhite.get());

        simpleItem(ItemRegister.purpleMaidDress.get());
        simpleItem(ItemRegister.purpleMaidDressCatTailBlack.get());
        simpleItem(ItemRegister.purpleMaidDressCatTailCaramel.get());
        simpleItem(ItemRegister.purpleMaidDressCatTailWhite.get());

        simpleItem(ItemRegister.brownMaidDress.get());
        simpleItem(ItemRegister.brownMaidDressCatTailBlack.get());
        simpleItem(ItemRegister.brownMaidDressCatTailCaramel.get());
        simpleItem(ItemRegister.brownMaidDressCatTailWhite.get());

        simpleItem(ItemRegister.yellowMaidDress.get());
        simpleItem(ItemRegister.yellowMaidDressCatTailBlack.get());
        simpleItem(ItemRegister.yellowMaidDressCatTailCaramel.get());
        simpleItem(ItemRegister.yellowMaidDressCatTailWhite.get());

        simpleItem(ItemRegister.cyanMaidDress.get());
        simpleItem(ItemRegister.cyanMaidDressCatTailBlack.get());
        simpleItem(ItemRegister.cyanMaidDressCatTailCaramel.get());
        simpleItem(ItemRegister.cyanMaidDressCatTailWhite.get());

        simpleItem(ItemRegister.blackThighHighs.get());
        simpleItem(ItemRegister.whiteThighHighs.get());
        simpleItem(ItemRegister.blackThighHighsShoes.get());
        simpleItem(ItemRegister.whiteThighHighsShoes.get());



        simpleItem(ItemRegister.blackMaidDressFoxTailBlack.get());
        simpleItem(ItemRegister.blackMaidDressFoxTailRed.get());
        simpleItem(ItemRegister.blackMaidDressFoxTailWhite.get());

        simpleItem(ItemRegister.whiteMaidDressFoxTailBlack.get());
        simpleItem(ItemRegister.whiteMaidDressFoxTailRed.get());
        simpleItem(ItemRegister.whiteMaidDressFoxTailWhite.get());

        simpleItem(ItemRegister.grayMaidDressFoxTailBlack.get());
        simpleItem(ItemRegister.grayMaidDressFoxTailRed.get());
        simpleItem(ItemRegister.grayMaidDressFoxTailWhite.get());

        simpleItem(ItemRegister.light_grayMaidDressFoxTailBlack.get());
        simpleItem(ItemRegister.light_grayMaidDressFoxTailRed.get());
        simpleItem(ItemRegister.light_grayMaidDressFoxTailWhite.get());

        simpleItem(ItemRegister.light_blueMaidDressFoxTailBlack.get());
        simpleItem(ItemRegister.light_blueMaidDressFoxTailRed.get());
        simpleItem(ItemRegister.light_blueMaidDressFoxTailWhite.get());

        simpleItem(ItemRegister.blueMaidDressFoxTailBlack.get());
        simpleItem(ItemRegister.blueMaidDressFoxTailRed.get());
        simpleItem(ItemRegister.blueMaidDressFoxTailWhite.get());

        simpleItem(ItemRegister.limeMaidDressFoxTailBlack.get());
        simpleItem(ItemRegister.limeMaidDressFoxTailRed.get());
        simpleItem(ItemRegister.limeMaidDressFoxTailWhite.get());

        simpleItem(ItemRegister.greenMaidDressFoxTailBlack.get());
        simpleItem(ItemRegister.greenMaidDressFoxTailRed.get());
        simpleItem(ItemRegister.greenMaidDressFoxTailWhite.get());

        simpleItem(ItemRegister.orangeMaidDressFoxTailBlack.get());
        simpleItem(ItemRegister.orangeMaidDressFoxTailRed.get());
        simpleItem(ItemRegister.orangeMaidDressFoxTailWhite.get());

        simpleItem(ItemRegister.redMaidDressFoxTailBlack.get());
        simpleItem(ItemRegister.redMaidDressFoxTailRed.get());
        simpleItem(ItemRegister.redMaidDressFoxTailWhite.get());

        simpleItem(ItemRegister.pinkMaidDressFoxTailBlack.get());
        simpleItem(ItemRegister.pinkMaidDressFoxTailRed.get());
        simpleItem(ItemRegister.pinkMaidDressFoxTailWhite.get());

        simpleItem(ItemRegister.magentaMaidDressFoxTailBlack.get());
        simpleItem(ItemRegister.magentaMaidDressFoxTailRed.get());
        simpleItem(ItemRegister.magentaMaidDressFoxTailWhite.get());

        simpleItem(ItemRegister.magentaMaidDressFoxTailBlack.get());
        simpleItem(ItemRegister.magentaMaidDressFoxTailRed.get());
        simpleItem(ItemRegister.magentaMaidDressFoxTailWhite.get());

        simpleItem(ItemRegister.purpleMaidDressFoxTailBlack.get());
        simpleItem(ItemRegister.purpleMaidDressFoxTailRed.get());
        simpleItem(ItemRegister.purpleMaidDressFoxTailWhite.get());

        simpleItem(ItemRegister.brownMaidDressFoxTailBlack.get());
        simpleItem(ItemRegister.brownMaidDressFoxTailRed.get());
        simpleItem(ItemRegister.brownMaidDressFoxTailWhite.get());

        simpleItem(ItemRegister.yellowMaidDressFoxTailBlack.get());
        simpleItem(ItemRegister.yellowMaidDressFoxTailRed.get());
        simpleItem(ItemRegister.yellowMaidDressFoxTailWhite.get());

        simpleItem(ItemRegister.cyanMaidDressFoxTailBlack.get());
        simpleItem(ItemRegister.cyanMaidDressFoxTailRed.get());
        simpleItem(ItemRegister.cyanMaidDressFoxTailWhite.get());


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
