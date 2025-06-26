package com.hakimen.kawaiidishes.datagen.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.block.CakeBlock;
import com.hakimen.kawaiidishes.block.DirectionalBlockWithEntity;
import com.hakimen.kawaiidishes.block.IncenseBlock;
import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import com.ibm.icu.impl.ValidIdentifiers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;

public class BlockStateModelDataGen {
    public static void gen(BlockModelGenerators blockGen) {

        cubeAllBlock(BlockRegister.KITCHEN_TILES.get(), blockGen);

        cakeBlock(BlockRegister.CHEESE_CAKE.get(), blockGen);
        cakeBlock(BlockRegister.CHOCOLATE_CHEESE_CAKE.get(), blockGen);
        cakeBlock(BlockRegister.HONEY_CHEESE_CAKE.get(), blockGen);

        pieBlock(BlockRegister.APPLE_PIE.get(), blockGen);
        pieBlock(BlockRegister.SWEET_BERRY_PIE.get(), blockGen);
        pieBlock(BlockRegister.GLOW_BERRY_PIE.get(), blockGen);
        pieBlock(BlockRegister.CHERRY_PIE.get(), blockGen);

        directionalBlock(BlockRegister.BLENDER.get(), blockGen);
        directionalBlock(BlockRegister.DISPLAY_CASE.get(), blockGen);
        directionalBlock(BlockRegister.ICE_CREAM_MAKER.get(), blockGen);
        incenseBlock(BlockRegister.INCENSE_GLASS.get(), blockGen);


    }

    private static void cubeAllBlock(Block block, BlockModelGenerators blockGen){
        var model = new JsonObject();
        model.addProperty("parent", new ResourceLocation( "block/cube_all").toString());

        var textureData = new JsonObject();

        textureData.addProperty("all", new ResourceLocation(KawaiiDishes.MODID, "block/%s".formatted(BuiltInRegistries.BLOCK.getKey(block).getPath())).toString());
        model.add("textures",textureData);

        blockGen.modelOutput.accept(new ResourceLocation(KawaiiDishes.MODID, "block/"+BuiltInRegistries.BLOCK.getKey(block).getPath()),() -> model);

        var variant= MultiVariantGenerator.multiVariant(block,Variant.variant().with(VariantProperties.MODEL, new ResourceLocation(KawaiiDishes.MODID, "block/"+BuiltInRegistries.BLOCK.getKey(block).getPath())));
        blockGen.blockStateOutput.accept(variant);
    }

    private static void cakeBlock(CakeBlock block, BlockModelGenerators blockGen) {
        for (int i = 1; i < 5; i++) {
            var model = new JsonObject();
            model.addProperty("parent", new ResourceLocation(KawaiiDishes.MODID, "block/cake/cake_slice_%d".formatted(i)).toString());

            var textureData = new JsonObject();

            textureData.addProperty("0", new ResourceLocation(KawaiiDishes.MODID, "block/cake/%s".formatted(BuiltInRegistries.BLOCK.getKey(block).getPath())).toString());
            textureData.addProperty("particle", new ResourceLocation(KawaiiDishes.MODID, "block/cake/%s".formatted(BuiltInRegistries.BLOCK.getKey(block).getPath())).toString());


            model.add("textures", textureData);

            blockGen.modelOutput.accept(new ResourceLocation(KawaiiDishes.MODID, "block/cake/%s_slice_%s".formatted(BuiltInRegistries.BLOCK.getKey(block).getPath(), i)), () -> model);
        }

        MultiVariantGenerator variants = MultiVariantGenerator.multiVariant(block)
                .with(PropertyDispatch.property(CakeBlock.SLICES)
                        .generate((i) -> Variant.variant().with(VariantProperties.MODEL, new ResourceLocation(KawaiiDishes.MODID, "block/cake/%s_slice_%s".formatted(BuiltInRegistries.BLOCK.getKey(block).getPath(), i))))
                );

        blockGen.blockStateOutput.accept(variants);
    }


    private static void pieBlock(CakeBlock block, BlockModelGenerators blockGen) {
        for (int i = 1; i < 5; i++) {
            var model = new JsonObject();
            model.addProperty("parent", new ResourceLocation(KawaiiDishes.MODID, "block/cake/cake_slice_%d".formatted(i)).toString());

            var textureData = new JsonObject();

            textureData.addProperty("0", new ResourceLocation(KawaiiDishes.MODID, "block/pie/%s".formatted(BuiltInRegistries.BLOCK.getKey(block).getPath())).toString());
            textureData.addProperty("particle", new ResourceLocation(KawaiiDishes.MODID, "block/pie/%s".formatted(BuiltInRegistries.BLOCK.getKey(block).getPath())).toString());


            model.add("textures", textureData);

            blockGen.modelOutput.accept(new ResourceLocation(KawaiiDishes.MODID, "block/pie/%s_slice_%s".formatted(BuiltInRegistries.BLOCK.getKey(block).getPath(), i)), () -> model);
        }

        MultiVariantGenerator variants = MultiVariantGenerator.multiVariant(block)
                .with(PropertyDispatch.property(CakeBlock.SLICES)
                        .generate((i) -> Variant.variant().with(VariantProperties.MODEL, new ResourceLocation(KawaiiDishes.MODID, "block/pie/%s_slice_%s".formatted(BuiltInRegistries.BLOCK.getKey(block).getPath(), i))))
                );

        blockGen.blockStateOutput.accept(variants);
    }

    public static void incenseBlock(IncenseBlock block, BlockModelGenerators blockGen) {
        MultiVariantGenerator variants = MultiVariantGenerator.multiVariant(block)
                .with(PropertyDispatch.properties(IncenseBlock.LIT, IncenseBlock.FACING)
                        .generate((lit, dir) -> {
                                    if (!lit) {
                                        return Variant.variant()
                                                .with(VariantProperties.MODEL, new ResourceLocation(KawaiiDishes.MODID, "block/incense_glass"))
                                                .with(VariantProperties.Y_ROT, switch (dir) {
                                                    case SOUTH -> VariantProperties.Rotation.R180;
                                                    case WEST -> VariantProperties.Rotation.R270;
                                                    case EAST -> VariantProperties.Rotation.R90;
                                                    default -> VariantProperties.Rotation.R0;
                                                });
                                    } else {
                                        return Variant.variant()
                                                .with(VariantProperties.MODEL, new ResourceLocation(KawaiiDishes.MODID, "block/incense_glass_lit"))
                                                .with(VariantProperties.Y_ROT, switch (dir) {
                                                    case SOUTH -> VariantProperties.Rotation.R180;
                                                    case WEST -> VariantProperties.Rotation.R270;
                                                    case EAST -> VariantProperties.Rotation.R90;
                                                    default -> VariantProperties.Rotation.R0;
                                                });
                                    }
                                }
                        )
                );
        blockGen.blockStateOutput.accept(variants);
    }

    public static void directionalBlock(HorizontalDirectionalBlock block, BlockModelGenerators blockGen){
        MultiVariantGenerator variantGenerators = MultiVariantGenerator.multiVariant(block).with(PropertyDispatch.property(HorizontalDirectionalBlock.FACING)
                .generate(dir -> {
                    return Variant.variant()
                            .with(VariantProperties.MODEL, new ResourceLocation(KawaiiDishes.MODID, "block/" + BuiltInRegistries.BLOCK.getKey(block).getPath()))
                            .with(VariantProperties.Y_ROT, switch (dir) {
                                case SOUTH -> VariantProperties.Rotation.R180;
                                case WEST -> VariantProperties.Rotation.R270;
                                case EAST -> VariantProperties.Rotation.R90;
                                default -> VariantProperties.Rotation.R0;
                            });
                }));
        blockGen.blockStateOutput.accept(variantGenerators);
    }
}
