package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.block.CakeBlock;
import com.hakimen.kawaiidishes.block.DirectionalBlockWithEntity;
import com.hakimen.kawaiidishes.block.IncenseBlock;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BlockStateDataGen extends BlockStateProvider {
    public BlockStateDataGen(DataGenerator generator, ExistingFileHelper exFileHelper) {
        super(generator.getPackOutput(), KawaiiDishes.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(BlockRegister.KITCHEN_TILES.get(), cubeAll(BlockRegister.KITCHEN_TILES.get()));

        cakeBlock(BlockRegister.CHEESE_CAKE.get());
        cakeBlock(BlockRegister.CHOCOLATE_CHEESE_CAKE.get());
        cakeBlock(BlockRegister.HONEY_CHEESE_CAKE.get());

        pieBlock(BlockRegister.APPLE_PIE.get());
        pieBlock(BlockRegister.SWEET_BERRY_PIE.get());
        pieBlock(BlockRegister.GLOW_BERRY_PIE.get());
        pieBlock(BlockRegister.CHERRY_PIE.get());

        directionalBlock(BlockRegister.BLENDER.get());
        directionalBlock(BlockRegister.DISPLAY_CASE.get());
        directionalBlock(BlockRegister.ICE_CREAM_MAKER.get());
        incenseBlock(BlockRegister.INCENSE_GLASS.get());
    }

    private void cakeBlock(CakeBlock block) {
        ModelFile[] models = new ModelFile[4];
        for (int i = 1; i < 5; i++) {
           models[i-1] = models()
                   .withExistingParent(
                           BuiltInRegistries.BLOCK.getKey(block).toString().replaceAll(":", ":block/cake/") + "_slice_%d".formatted(i),
                           new ResourceLocation(KawaiiDishes.MODID, "block/cake/cake_slice_%d".formatted(i)))
                   .texture("0", new ResourceLocation(KawaiiDishes.MODID, "block/cake/%s".formatted(BuiltInRegistries.BLOCK.getKey(block).getPath())))
                   .texture("particle", new ResourceLocation(KawaiiDishes.MODID, "block/cake/%s".formatted(BuiltInRegistries.BLOCK.getKey(block).getPath())));
        }

        getVariantBuilder(block).forAllStates(pState -> ConfiguredModel.builder().modelFile(models[pState.getValue(CakeBlock.SLICES)-1]).build());
    }

    private void pieBlock(CakeBlock block) {
        ModelFile[] models = new ModelFile[4];
        for (int i = 1; i < 5; i++) {
            models[i-1] = models()
                    .withExistingParent(
                            BuiltInRegistries.BLOCK.getKey(block).toString().replaceAll(":", ":block/pie/") + "_slice_%d".formatted(i),
                            new ResourceLocation(KawaiiDishes.MODID, "block/cake/cake_slice_%d".formatted(i)))
                    .texture("0", new ResourceLocation(KawaiiDishes.MODID, "block/pie/%s".formatted(BuiltInRegistries.BLOCK.getKey(block).getPath())))
                    .texture("particle", new ResourceLocation(KawaiiDishes.MODID, "block/pie/%s".formatted(BuiltInRegistries.BLOCK.getKey(block).getPath())));
        }

        getVariantBuilder(block).forAllStates(pState -> ConfiguredModel.builder().modelFile(models[pState.getValue(CakeBlock.SLICES)-1]).build());
    }

    public void directionalBlock(DirectionalBlockWithEntity block){
        ModelFile model = models().getExistingFile(BuiltInRegistries.BLOCK.getKey(block));

        getVariantBuilder(block).forAllStates(pState -> ConfiguredModel.builder().modelFile(model).rotationY(
                switch (pState.getValue(DirectionalBlockWithEntity.FACING)){
                    case SOUTH -> 180;
                    case WEST -> 270;
                    case EAST -> 90;
                    default -> 0;
                }
        ).build());
    }

    public void incenseBlock(IncenseBlock block){
        ModelFile model = models().getExistingFile(BuiltInRegistries.BLOCK.getKey(block));
        ModelFile modelLit = models().getExistingFile(BuiltInRegistries.BLOCK.getKey(block).withSuffix("_lit"));

        getVariantBuilder(block).forAllStates(pState -> {
            if(pState.getValue(IncenseBlock.LIT)) {
                return ConfiguredModel.builder().modelFile(modelLit).rotationY(
                        switch (pState.getValue(DirectionalBlockWithEntity.FACING)){
                            case SOUTH -> 180;
                            case WEST -> 270;
                            case EAST -> 90;
                            default -> 0;
                        }
                ).build();
            }else{
                return ConfiguredModel.builder().modelFile(model).rotationY(
                        switch (pState.getValue(DirectionalBlockWithEntity.FACING)){
                            case SOUTH -> 180;
                            case WEST -> 270;
                            case EAST -> 90;
                            default -> 0;
                        }
                ).build();
            }
        });
    }

}
