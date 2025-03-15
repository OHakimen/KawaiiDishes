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
import net.minecraft.block.Block;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.BlockStateVariantMap;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class BlockStateModelDataGen {
    public static void gen(BlockStateModelGenerator blockGen) {

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

    private static void cubeAllBlock(Block block, BlockStateModelGenerator blockGen){
        var model = new JsonObject();
        model.addProperty("parent", new Identifier( "block/cube_all").toString());

        var textureData = new JsonObject();

        textureData.addProperty("all", new Identifier(KawaiiDishes.MODID, "block/%s".formatted(Registries.BLOCK.getId(block).getPath())).toString());
        model.add("textures",textureData);

        blockGen.modelCollector.accept(new Identifier(KawaiiDishes.MODID, "block/"+Registries.BLOCK.getId(block).getPath()),() -> model);

        var variant= VariantsBlockStateSupplier.create(block,BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(KawaiiDishes.MODID, "block/"+Registries.BLOCK.getId(block).getPath())));
        blockGen.blockStateCollector.accept(variant);
    }

    private static void cakeBlock(CakeBlock block, BlockStateModelGenerator blockGen) {
        for (int i = 1; i < 5; i++) {
            var model = new JsonObject();
            model.addProperty("parent", new Identifier(KawaiiDishes.MODID, "block/cake/cake_slice_%d".formatted(i)).toString());

            var textureData = new JsonObject();

            textureData.addProperty("0", new Identifier(KawaiiDishes.MODID, "block/cake/%s".formatted(Registries.BLOCK.getId(block).getPath())).toString());
            textureData.addProperty("particle", new Identifier(KawaiiDishes.MODID, "block/cake/%s".formatted(Registries.BLOCK.getId(block).getPath())).toString());


            model.add("textures", textureData);

            blockGen.modelCollector.accept(new Identifier(KawaiiDishes.MODID, "block/cake/%s_slice_%s".formatted(Registries.BLOCK.getId(block).getPath(), i)), () -> model);
        }

        VariantsBlockStateSupplier variants = VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(CakeBlock.SLICES)
                        .register((i) -> BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(KawaiiDishes.MODID, "block/cake/%s_slice_%s".formatted(Registries.BLOCK.getId(block).getPath(), i))))
                );

        blockGen.blockStateCollector.accept(variants);
    }


    private static void pieBlock(CakeBlock block, BlockStateModelGenerator blockGen) {
        for (int i = 1; i < 5; i++) {
            var model = new JsonObject();
            model.addProperty("parent", new Identifier(KawaiiDishes.MODID, "block/cake/cake_slice_%d".formatted(i)).toString());

            var textureData = new JsonObject();

            textureData.addProperty("0", new Identifier(KawaiiDishes.MODID, "block/pie/%s".formatted(Registries.BLOCK.getId(block).getPath())).toString());
            textureData.addProperty("particle", new Identifier(KawaiiDishes.MODID, "block/pie/%s".formatted(Registries.BLOCK.getId(block).getPath())).toString());


            model.add("textures", textureData);

            blockGen.modelCollector.accept(new Identifier(KawaiiDishes.MODID, "block/pie/%s_slice_%s".formatted(Registries.BLOCK.getId(block).getPath(), i)), () -> model);
        }

        VariantsBlockStateSupplier variants = VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(CakeBlock.SLICES)
                        .register((i) -> BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(KawaiiDishes.MODID, "block/pie/%s_slice_%s".formatted(Registries.BLOCK.getId(block).getPath(), i))))
                );

        blockGen.blockStateCollector.accept(variants);
    }

    public static void incenseBlock(IncenseBlock block, BlockStateModelGenerator blockGen) {
        VariantsBlockStateSupplier variants = VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(IncenseBlock.LIT, IncenseBlock.FACING)
                        .register((lit, dir) -> {
                                    if (!lit) {
                                        return BlockStateVariant.create()
                                                .put(VariantSettings.MODEL, new Identifier(KawaiiDishes.MODID, "block/incense_glass"))
                                                .put(VariantSettings.Y, switch (dir) {
                                                    case SOUTH -> VariantSettings.Rotation.R180;
                                                    case WEST -> VariantSettings.Rotation.R270;
                                                    case EAST -> VariantSettings.Rotation.R90;
                                                    default -> VariantSettings.Rotation.R0;
                                                });
                                    } else {
                                        return BlockStateVariant.create()
                                                .put(VariantSettings.MODEL, new Identifier(KawaiiDishes.MODID, "block/incense_glass_lit"))
                                                .put(VariantSettings.Y, switch (dir) {
                                                    case SOUTH -> VariantSettings.Rotation.R180;
                                                    case WEST -> VariantSettings.Rotation.R270;
                                                    case EAST -> VariantSettings.Rotation.R90;
                                                    default -> VariantSettings.Rotation.R0;
                                                });
                                    }
                                }
                        )
                );
        blockGen.blockStateCollector.accept(variants);
    }

    public static void directionalBlock(HorizontalFacingBlock block, BlockStateModelGenerator blockGen){
        VariantsBlockStateSupplier variantGenerators = VariantsBlockStateSupplier.create(block).coordinate(BlockStateVariantMap.create(HorizontalFacingBlock.FACING)
                .register(dir -> {
                    return BlockStateVariant.create()
                            .put(VariantSettings.MODEL, new Identifier(KawaiiDishes.MODID, "block/" + Registries.BLOCK.getId(block).getPath()))
                            .put(VariantSettings.Y, switch (dir) {
                                case SOUTH -> VariantSettings.Rotation.R180;
                                case WEST -> VariantSettings.Rotation.R270;
                                case EAST -> VariantSettings.Rotation.R90;
                                default -> VariantSettings.Rotation.R0;
                            });
                }));
        blockGen.blockStateCollector.accept(variantGenerators);
    }
}
