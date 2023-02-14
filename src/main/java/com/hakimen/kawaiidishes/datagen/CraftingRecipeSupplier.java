package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.datagen.recipebuilder.BlenderRecipeBuilder;
import com.hakimen.kawaiidishes.datagen.recipebuilder.CoffeeMachineRecipeBuilder;
import com.hakimen.kawaiidishes.datagen.recipebuilder.CoffeePressRecipeBuilder;
import com.hakimen.kawaiidishes.datagen.recipebuilder.IceCreamMachineRecipeBuilder;
import com.hakimen.kawaiidishes.registry.EffectRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import java.util.function.Consumer;

public class CraftingRecipeSupplier extends RecipeProvider implements IConditionBuilder {
    public CraftingRecipeSupplier(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {

        roasting(pFinishedRecipeConsumer, ItemRegister.coffeeFruit.get(), ItemRegister.driedCoffeeBeans.get(), ItemRegister.roastedCoffeeBeans.get());
        roasting(pFinishedRecipeConsumer, Items.COCOA_BEANS, ItemRegister.driedCocoaBeans.get(), ItemRegister.roastedCocoaBeans.get());


        ShapedRecipeBuilder.shaped(ItemRegister.mug.get())
                .pattern("x x")
                .pattern("xxx")
                .define('x', Items.BRICK)
                .unlockedBy(getHasName(Items.BRICK), has(Items.BRICK))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ItemRegister.glassCup.get())
                .pattern("x x")
                .pattern("xxx")
                .define('x', Tags.Items.GLASS_PANES)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Items.GLASS_PANE))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ItemRegister.milkshakeCup.get())
                .pattern("xgx")
                .pattern("xxx")
                .define('g', ItemRegister.glassCup.get())
                .define('x', Items.PAPER)
                .unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ItemRegister.coffeeMachine.get())
                .pattern("xxx")
                .pattern(".pi")
                .pattern("iri")
                .define('x', Items.STONE_SLAB)
                .define('r', Items.REDSTONE)
                .define('.', Items.IRON_NUGGET)
                .define('i', Items.IRON_INGOT)
                .define('p', ItemRegister.coffeePress.get())
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ItemRegister.iceCreamMachine.get())
                .pattern("xxx")
                .pattern("i.i")
                .pattern("iri")
                .define('x', Tags.Items.GLASS)
                .define('r', Items.REDSTONE)
                .define('.', Items.IRON_NUGGET)
                .define('i', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ItemRegister.coffeePress.get())
                .pattern("xxx")
                .pattern("g g")
                .pattern("xxx")
                .define('g',Tags.Items.GLASS_PANES)
                .define('x',Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pFinishedRecipeConsumer);


        ShapedRecipeBuilder.shaped(ItemRegister.blender.get())
                .pattern("xxx")
                .pattern("g g")
                .pattern("xrx")
                .define('g',Tags.Items.GLASS_PANES)
                .define('r',Items.REDSTONE)
                .define('x',Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pFinishedRecipeConsumer);


        ShapelessRecipeBuilder.shapeless(ItemRegister.darkChocolateBar.get())
                .requires(ItemRegister.cocoaPowder.get())
                .requires(ItemRegister.cocoaPowder.get())
                .requires(Items.SUGAR)
                .requires(ItemRegister.cocoaPowder.get())
                .unlockedBy(getHasName(ItemRegister.cocoaPowder.get()), has(ItemRegister.cocoaPowder.get()))
                .save(pFinishedRecipeConsumer);


        ShapelessRecipeBuilder.shapeless(ItemRegister.condensedMilk.get())
                .requires(Items.MILK_BUCKET)
                .requires(Items.SUGAR)
                .unlockedBy(getHasName(Items.MILK_BUCKET), has(Items.MILK_BUCKET))
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(ItemRegister.beijinho.get(),8)
                .requires(ItemRegister.condensedMilk.get())
                .unlockedBy(getHasName(ItemRegister.condensedMilk.get()), has(ItemRegister.condensedMilk.get()))
                .save(pFinishedRecipeConsumer);


        ShapelessRecipeBuilder.shapeless(ItemRegister.brigadeiro.get(),8)
                .requires(ItemRegister.brigadeiroMix.get())
                .unlockedBy(getHasName(ItemRegister.brigadeiroMix.get()), has(ItemRegister.brigadeiroMix.get()))
                .save(pFinishedRecipeConsumer);


        ShapelessRecipeBuilder.shapeless(ItemRegister.brigadeiroMix.get())
                .requires(ItemRegister.condensedMilk.get())
                .requires(ItemRegister.cocoaPowder.get())
                .unlockedBy(getHasName(ItemRegister.condensedMilk.get()), has(ItemRegister.condensedMilk.get()))
                .unlockedBy(getHasName(ItemRegister.cocoaPowder.get()), has(ItemRegister.cocoaPowder.get()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(ItemRegister.milkChocolateBar.get())
                .requires(ItemRegister.cocoaPowder.get())
                .requires(ItemRegister.cocoaPowder.get())
                .requires(Items.SUGAR)
                .requires(Items.MILK_BUCKET)
                .unlockedBy(getHasName(ItemRegister.cocoaPowder.get()), has(ItemRegister.cocoaPowder.get()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(ItemRegister.whiteChocolateBar.get())
                .requires(ItemRegister.cocoaPowder.get())
                .requires(Items.SUGAR)
                .requires(Items.SUGAR)
                .requires(Items.MILK_BUCKET)
                .unlockedBy(getHasName(ItemRegister.cocoaPowder.get()), has(ItemRegister.cocoaPowder.get()))
                .save(pFinishedRecipeConsumer);


        ShapedRecipeBuilder.shaped(ItemRegister.cheeseCake.get())
                .pattern("xxx")
                .pattern("www")
                .define('x',ItemRegister.creamCheese.get())
                .define('w',Items.WHEAT)
                .unlockedBy(getHasName(ItemRegister.creamCheese.get()), has(ItemRegister.creamCheese.get()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ItemRegister.chocolateCheeseCake.get())
                .pattern("xex")
                .pattern("www")
                .define('x',ItemRegister.creamCheese.get())
                .define('e',ItemRegister.cocoaPowder.get())
                .define('w',Items.WHEAT)
                .unlockedBy(getHasName(ItemRegister.creamCheese.get()), has(ItemRegister.creamCheese.get()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ItemRegister.honeyCheeseCake.get())
                .pattern("xex")
                .pattern("www")
                .define('x',ItemRegister.creamCheese.get())
                .define('e',Items.HONEY_BOTTLE)
                .define('w',Items.WHEAT)
                .unlockedBy(getHasName(ItemRegister.creamCheese.get()), has(ItemRegister.creamCheese.get()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(ItemRegister.cakePiece.get(),8)
                .requires(Items.CAKE)
                .unlockedBy(getHasName(Items.CAKE), has(Items.CAKE))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(ItemRegister.cheeseCakePiece.get(),8)
                .requires(ItemRegister.cheeseCake.get())
                .unlockedBy(getHasName(ItemRegister.cheeseCake.get()), has(ItemRegister.cheeseCake.get()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(ItemRegister.chocolateCheeseCakePiece.get(),8)
                .requires(ItemRegister.chocolateCheeseCake.get())
                .unlockedBy(getHasName(ItemRegister.chocolateCheeseCake.get()), has(ItemRegister.chocolateCheeseCake.get()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(ItemRegister.honeyCheeseCakePiece.get(),8)
                .requires(ItemRegister.honeyCheeseCake.get())
                .unlockedBy(getHasName(ItemRegister.honeyCheeseCake.get()), has(ItemRegister.honeyCheeseCake.get()))
                .save(pFinishedRecipeConsumer);

        cookie(pFinishedRecipeConsumer,ItemRegister.honeyCookie.get(),8,Items.HONEY_BOTTLE);
        cookie(pFinishedRecipeConsumer,ItemRegister.sweetBerryCookie.get(),8,Items.SWEET_BERRIES);
        cookie(pFinishedRecipeConsumer,ItemRegister.chocolateCookie.get(),8,ItemRegister.cocoaPowder.get());
        cookieGolden(pFinishedRecipeConsumer,ItemRegister.goldenCookie.get(),1, new TagKey<Item>(Registry.ITEM_REGISTRY,new ResourceLocation(KawaiiDishes.modId,"cookies")));
        cookieOfUnbinding(pFinishedRecipeConsumer);

        cosmetics(pFinishedRecipeConsumer);
        decor(pFinishedRecipeConsumer);
        coffees(pFinishedRecipeConsumer);
        iceCreams(pFinishedRecipeConsumer);
        blendings(pFinishedRecipeConsumer);
    }

    public void cookie(Consumer<FinishedRecipe> pFinishedRecipeConsumer,Item output, int count,Item middle){
        ShapedRecipeBuilder.shaped(output,count)
                .pattern("#x#")
                .define('#',Items.WHEAT)
                .define('x',middle)
                .unlockedBy(getHasName(middle), has(middle))
                .save(pFinishedRecipeConsumer);
    }
    public void cookieGolden(Consumer<FinishedRecipe> pFinishedRecipeConsumer,Item output, int count,TagKey<Item> middle){
        ShapedRecipeBuilder.shaped(output,count)
                .pattern(" # ")
                .pattern("#x#")
                .pattern(" # ")
                .define('#',Items.GOLD_NUGGET)
                .define('x',Ingredient.of(middle))
                .unlockedBy(getHasName(Items.GOLD_NUGGET), has(Items.GOLD_NUGGET))
                .save(pFinishedRecipeConsumer);
    }
    public void cookieOfUnbinding(Consumer<FinishedRecipe> pFinishedRecipeConsumer){
        ShapedRecipeBuilder.shaped(ItemRegister.unbindingCookie.get())
                .pattern(" a ")
                .pattern("bxc")
                .pattern(" d ")
                .define('a',Items.GHAST_TEAR)
                .define('b',Items.WITHER_SKELETON_SKULL)
                .define('c',Items.FERMENTED_SPIDER_EYE)
                .define('d',Items.EXPERIENCE_BOTTLE)
                .define('x',Ingredient.of(new TagKey<Item>(Registry.ITEM_REGISTRY,new ResourceLocation(KawaiiDishes.modId,"cookies"))))
                .unlockedBy(getHasName(Items.COOKIE), has(Items.COOKIE))
                .save(pFinishedRecipeConsumer);
    }
    public void blendings(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        blending(pFinishedRecipeConsumer,
                ItemRegister.roastedCoffeeBeans.get(),
                ItemRegister.coffeePowder.get().getDefaultInstance(),
                100,1);
        blending(pFinishedRecipeConsumer,
                ItemRegister.roastedCocoaBeans.get(),
                ItemRegister.cocoaPowder.get().getDefaultInstance(),
                100,1);
        blending(pFinishedRecipeConsumer,
                ItemRegister.cocoaPowder.get(),
                ItemRegister.condensedMilk.get(),
                ItemRegister.brigadeiroMix.get().getDefaultInstance(),
                100,4);
        blending(pFinishedRecipeConsumer,
                Items.MILK_BUCKET,
                Items.SUGAR,
                ItemRegister.condensedMilk.get().getDefaultInstance(),
                100,4);

        blending(pFinishedRecipeConsumer,
                Items.MILK_BUCKET,
                ItemRegister.creamCheese.get().getDefaultInstance(),
                100,4);

        blending(pFinishedRecipeConsumer,
                Items.MILK_BUCKET,
                ItemRegister.sweetBerryIceCream.get(),
                ItemRegister.sweetBerryMilkshake.get().getDefaultInstance(),
                ItemRegister.milkshakeCup.get().getDefaultInstance(),
                new MobEffectInstance(MobEffects.FIRE_RESISTANCE,30*20),
                null,
                100,
                1);
        blending(pFinishedRecipeConsumer,
                Items.MILK_BUCKET,
                ItemRegister.glowBerryIceCream.get(),
                ItemRegister.glowBerryMilkshake.get().getDefaultInstance(),
                ItemRegister.milkshakeCup.get().getDefaultInstance(),
                new MobEffectInstance(MobEffects.FIRE_RESISTANCE,30*20),
                new MobEffectInstance(MobEffects.NIGHT_VISION,60*20),
                100,
                1);
        blending(pFinishedRecipeConsumer,
                Items.MILK_BUCKET,
                ItemRegister.napolitanoIceCream.get(),
                ItemRegister.napolitanoMilkshake.get().getDefaultInstance(),
                ItemRegister.milkshakeCup.get().getDefaultInstance(),
                new MobEffectInstance(MobEffects.FIRE_RESISTANCE,30*20),
                null,
                100,
                1);
        blending(pFinishedRecipeConsumer,
                Items.MILK_BUCKET,
                ItemRegister.chocolateIceCream.get(),
                ItemRegister.chocolateMilkshake.get().getDefaultInstance(),
                ItemRegister.milkshakeCup.get().getDefaultInstance(),
                new MobEffectInstance(MobEffects.FIRE_RESISTANCE,30*20),
                null,
                100,
                1);
        blending(pFinishedRecipeConsumer,
                Items.MILK_BUCKET,
                ItemRegister.coffeeIceCream.get(),
                ItemRegister.coffeeMilkshake.get().getDefaultInstance(),
                ItemRegister.milkshakeCup.get().getDefaultInstance(),
                new MobEffectInstance(MobEffects.FIRE_RESISTANCE,30*20),
                null,
                100,
                1);
        blending(pFinishedRecipeConsumer,
                Items.MILK_BUCKET,
                ItemRegister.mochaIceCream.get(),
                ItemRegister.mochaMilkshake.get().getDefaultInstance(),
                ItemRegister.milkshakeCup.get().getDefaultInstance(),
                new MobEffectInstance(MobEffects.FIRE_RESISTANCE,30*20),
                null,
                100,
                1);
        blending(pFinishedRecipeConsumer,
                Items.MILK_BUCKET,
                ItemRegister.creamIceCream.get(),
                ItemRegister.creamMilkshake.get().getDefaultInstance(),
                ItemRegister.milkshakeCup.get().getDefaultInstance(),
                new MobEffectInstance(MobEffects.FIRE_RESISTANCE,30*20),
                null,
                100,
                1);
    }

    public void blending(Consumer<FinishedRecipe> consumer,Item ingredient, ItemStack result,int tick,int count) {
        BlenderRecipeBuilder builder = new BlenderRecipeBuilder(
            ingredient,result,ItemStack.EMPTY,tick,count
        );
        builder.unlockedBy(getHasName(ingredient), has(ingredient));
        builder.save(consumer);
    }
    public void blending(Consumer<FinishedRecipe> consumer,Item ingredient,Item ingredient2, ItemStack result,int tick,int count) {
        BlenderRecipeBuilder builder = new BlenderRecipeBuilder(
                ingredient,ingredient2,result,ItemStack.EMPTY,tick,count
        );
        builder.unlockedBy(getHasName(ingredient), has(ingredient));
        builder.save(consumer);
    }
    public void blending(Consumer<FinishedRecipe> consumer,Item ingredient,Item ingredient2,ItemStack result,ItemStack onOut,
                         MobEffectInstance mainEffect,MobEffectInstance secondaryEffect,int tick,int count) {
        BlenderRecipeBuilder builder = new BlenderRecipeBuilder(
                ingredient,ingredient2,result,onOut,tick,mainEffect,secondaryEffect,count
        );
        builder.unlockedBy(getHasName(ingredient), has(ingredient));
        builder.save(consumer);
    }


    public void iceCreams(Consumer<FinishedRecipe> consumer){
        iceCream(consumer,
                Items.SWEET_BERRIES,
                ItemRegister.sweetBerryIceCream.get().getDefaultInstance(),
                100,
                ItemRegister.glassCup.get().getDefaultInstance(),
                new MobEffectInstance(MobEffects.FIRE_RESISTANCE,15*20)
                ,null);

        iceCream(consumer,
                Items.GLOW_BERRIES,
                ItemRegister.glowBerryIceCream.get().getDefaultInstance(),
                100,
                ItemRegister.glassCup.get().getDefaultInstance(),
                new MobEffectInstance(MobEffects.FIRE_RESISTANCE,15*20),
                new MobEffectInstance(MobEffects.NIGHT_VISION,30*20));

        iceCream(consumer,
                Items.SWEET_BERRIES,
                Items.MILK_BUCKET,
                ItemRegister.cocoaPowder.get(),
                ItemRegister.napolitanoIceCream.get().getDefaultInstance(),
                100,
                ItemRegister.glassCup.get().getDefaultInstance(),
                new MobEffectInstance(MobEffects.FIRE_RESISTANCE,15*20)
                ,null);

        iceCream(consumer,
                ItemRegister.cocoaPowder.get(),
                ItemRegister.chocolateIceCream.get().getDefaultInstance(),
                100,
                ItemRegister.glassCup.get().getDefaultInstance(),
                new MobEffectInstance(MobEffects.FIRE_RESISTANCE,15*20)
                ,null);

        iceCream(consumer,
                ItemRegister.coffeePowder.get(),
                ItemRegister.coffeeIceCream.get().getDefaultInstance(),
                100,
                ItemRegister.glassCup.get().getDefaultInstance(),
                new MobEffectInstance(MobEffects.FIRE_RESISTANCE,15*20)
                ,null);

        iceCream(consumer,
                Items.MILK_BUCKET,
                Items.SUGAR,
                ItemRegister.creamIceCream.get().getDefaultInstance(),
                100,
                ItemRegister.glassCup.get().getDefaultInstance(),
                new MobEffectInstance(MobEffects.FIRE_RESISTANCE,15*20)
                ,null);

        iceCream(consumer,
                ItemRegister.coffeePowder.get(),
                ItemRegister.cocoaPowder.get(),
                Items.MILK_BUCKET,
                ItemRegister.mochaIceCream.get().getDefaultInstance(),
                100,
                ItemRegister.glassCup.get().getDefaultInstance(),
                new MobEffectInstance(MobEffects.FIRE_RESISTANCE,15*20)
                ,null);

    }


    public void iceCream(Consumer<FinishedRecipe> consumer, Item item, Item item1, Item item2, ItemStack result, int ticks, ItemStack onOutput, MobEffectInstance mainEffect, MobEffectInstance secondaryEffect){
        IceCreamMachineRecipeBuilder builder = new IceCreamMachineRecipeBuilder(
                Items.SNOWBALL,
                item,
                item1,
                item2,
                result,
                ticks,
                onOutput,
                mainEffect,
                secondaryEffect
        );
        builder.unlockedBy(getHasName(item), has(item));
        builder.save(consumer);
    }

    public void iceCream(Consumer<FinishedRecipe> consumer, Item item, Item item1, ItemStack result, int ticks, ItemStack onOutput, MobEffectInstance mainEffect, MobEffectInstance secondaryEffect){
        IceCreamMachineRecipeBuilder builder = new IceCreamMachineRecipeBuilder(
                Items.SNOWBALL,
                item,
                item1,
                result,
                ticks,
                onOutput,
                mainEffect,
                secondaryEffect
        );
        builder.unlockedBy(getHasName(item), has(item));
        builder.save(consumer);
    }

    public void iceCream(Consumer<FinishedRecipe> consumer, Item item, ItemStack result, int ticks, ItemStack onOutput, MobEffectInstance mainEffect, MobEffectInstance secondaryEffect){
        IceCreamMachineRecipeBuilder builder = new IceCreamMachineRecipeBuilder(
                Items.SNOWBALL,
                item,
                result,
                ticks,
                onOutput,
                mainEffect,
                secondaryEffect
        );
        builder.unlockedBy(getHasName(item), has(item));
        builder.save(consumer);
    }

    public void decor(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        stool(pFinishedRecipeConsumer, ItemRegister.blackStool.get(), Items.BLACK_WOOL);
        stool(pFinishedRecipeConsumer, ItemRegister.blueStool.get(), Items.BLUE_WOOL);
        stool(pFinishedRecipeConsumer, ItemRegister.brownStool.get(), Items.BROWN_WOOL);
        stool(pFinishedRecipeConsumer, ItemRegister.cyanStool.get(), Items.CYAN_WOOL);
        stool(pFinishedRecipeConsumer, ItemRegister.grayStool.get(), Items.GRAY_WOOL);
        stool(pFinishedRecipeConsumer, ItemRegister.greenStool.get(), Items.GREEN_WOOL);
        stool(pFinishedRecipeConsumer, ItemRegister.lightBlueStool.get(), Items.LIGHT_BLUE_WOOL);
        stool(pFinishedRecipeConsumer, ItemRegister.lightGrayStool.get(), Items.LIGHT_GRAY_WOOL);
        stool(pFinishedRecipeConsumer, ItemRegister.limeStool.get(), Items.LIME_WOOL);
        stool(pFinishedRecipeConsumer, ItemRegister.magentaStool.get(), Items.MAGENTA_WOOL);
        stool(pFinishedRecipeConsumer, ItemRegister.orangeStool.get(), Items.ORANGE_WOOL);
        stool(pFinishedRecipeConsumer, ItemRegister.pinkStool.get(), Items.PINK_WOOL);
        stool(pFinishedRecipeConsumer, ItemRegister.purpleStool.get(), Items.PURPLE_WOOL);
        stool(pFinishedRecipeConsumer, ItemRegister.redStool.get(), Items.RED_WOOL);
        stool(pFinishedRecipeConsumer, ItemRegister.whiteStool.get(), Items.WHITE_WOOL);
        stool(pFinishedRecipeConsumer, ItemRegister.yellowStool.get(), Items.YELLOW_WOOL);
    }

    public void cosmetics(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {

        maidOutfit(pFinishedRecipeConsumer, ItemRegister.dresses.get("black").get(), Items.BLACK_WOOL, Items.WHITE_WOOL);
        maidOutfit(pFinishedRecipeConsumer, ItemRegister.dresses.get("white").get(), Items.WHITE_WOOL, Items.BLACK_WOOL);
        maidOutfit(pFinishedRecipeConsumer, ItemRegister.dresses.get("gray").get(), Items.GRAY_WOOL, Items.WHITE_WOOL);
        maidOutfit(pFinishedRecipeConsumer, ItemRegister.dresses.get("light_gray").get(), Items.LIGHT_GRAY_WOOL, Items.WHITE_WOOL);
        maidOutfit(pFinishedRecipeConsumer, ItemRegister.dresses.get("light_blue").get(), Items.LIGHT_BLUE_WOOL, Items.WHITE_WOOL);
        maidOutfit(pFinishedRecipeConsumer, ItemRegister.dresses.get("red").get(), Items.RED_WOOL, Items.WHITE_WOOL);
        maidOutfit(pFinishedRecipeConsumer, ItemRegister.dresses.get("pink").get(), Items.PINK_WOOL, Items.WHITE_WOOL);
        maidOutfit(pFinishedRecipeConsumer, ItemRegister.dresses.get("magenta").get(), Items.MAGENTA_WOOL, Items.WHITE_WOOL);
        maidOutfit(pFinishedRecipeConsumer, ItemRegister.dresses.get("purple").get(), Items.PURPLE_WOOL, Items.WHITE_WOOL);
        maidOutfit(pFinishedRecipeConsumer, ItemRegister.dresses.get("green").get(), Items.GREEN_WOOL, Items.WHITE_WOOL);
        maidOutfit(pFinishedRecipeConsumer, ItemRegister.dresses.get("lime").get(), Items.LIME_WOOL, Items.WHITE_WOOL);
        maidOutfit(pFinishedRecipeConsumer, ItemRegister.dresses.get("blue").get(), Items.BLUE_WOOL, Items.WHITE_WOOL);
        maidOutfit(pFinishedRecipeConsumer, ItemRegister.dresses.get("cyan").get(), Items.CYAN_WOOL, Items.WHITE_WOOL);
        maidOutfit(pFinishedRecipeConsumer, ItemRegister.dresses.get("yellow").get(), Items.YELLOW_WOOL, Items.WHITE_WOOL);
        maidOutfit(pFinishedRecipeConsumer, ItemRegister.dresses.get("orange").get(), Items.ORANGE_WOOL, Items.WHITE_WOOL);
        maidOutfit(pFinishedRecipeConsumer, ItemRegister.dresses.get("brown").get(), Items.BROWN_WOOL, Items.WHITE_WOOL);


        maidOutfitVariations(pFinishedRecipeConsumer, ItemRegister.dresses.get("black").get());
        maidOutfitVariations(pFinishedRecipeConsumer, ItemRegister.dresses.get("white").get());
        maidOutfitVariations(pFinishedRecipeConsumer, ItemRegister.dresses.get("gray").get());
        maidOutfitVariations(pFinishedRecipeConsumer, ItemRegister.dresses.get("light_gray").get());
        maidOutfitVariations(pFinishedRecipeConsumer, ItemRegister.dresses.get("light_blue").get());
        maidOutfitVariations(pFinishedRecipeConsumer, ItemRegister.dresses.get("red").get());
        maidOutfitVariations(pFinishedRecipeConsumer, ItemRegister.dresses.get("pink").get());
        maidOutfitVariations(pFinishedRecipeConsumer, ItemRegister.dresses.get("magenta").get());
        maidOutfitVariations(pFinishedRecipeConsumer, ItemRegister.dresses.get("purple").get());
        maidOutfitVariations(pFinishedRecipeConsumer, ItemRegister.dresses.get("green").get());
        maidOutfitVariations(pFinishedRecipeConsumer, ItemRegister.dresses.get("lime").get());
        maidOutfitVariations(pFinishedRecipeConsumer, ItemRegister.dresses.get("blue").get());
        maidOutfitVariations(pFinishedRecipeConsumer, ItemRegister.dresses.get("orange").get());
        maidOutfitVariations(pFinishedRecipeConsumer, ItemRegister.dresses.get("brown").get());
        maidOutfitVariations(pFinishedRecipeConsumer, ItemRegister.dresses.get("cyan").get());
        maidOutfitVariations(pFinishedRecipeConsumer, ItemRegister.dresses.get("yellow").get());

        maidOutfitUncrafts(pFinishedRecipeConsumer,"black");
        maidOutfitUncrafts(pFinishedRecipeConsumer,"white");
        maidOutfitUncrafts(pFinishedRecipeConsumer,"gray");
        maidOutfitUncrafts(pFinishedRecipeConsumer,"light_gray");
        maidOutfitUncrafts(pFinishedRecipeConsumer,"light_blue");
        maidOutfitUncrafts(pFinishedRecipeConsumer,"red");
        maidOutfitUncrafts(pFinishedRecipeConsumer,"pink");
        maidOutfitUncrafts(pFinishedRecipeConsumer,"magenta");
        maidOutfitUncrafts(pFinishedRecipeConsumer,"purple");
        maidOutfitUncrafts(pFinishedRecipeConsumer,"green");
        maidOutfitUncrafts(pFinishedRecipeConsumer,"lime");
        maidOutfitUncrafts(pFinishedRecipeConsumer,"blue");
        maidOutfitUncrafts(pFinishedRecipeConsumer,"orange");
        maidOutfitUncrafts(pFinishedRecipeConsumer,"brown");
        maidOutfitUncrafts(pFinishedRecipeConsumer,"cyan");
        maidOutfitUncrafts(pFinishedRecipeConsumer,"yellow");



        shoes(pFinishedRecipeConsumer, ItemRegister.blackThighHighsShoes.get(), Items.LEATHER, Items.BLACK_WOOL);
        shoes(pFinishedRecipeConsumer, ItemRegister.whiteThighHighsShoes.get(), Items.LEATHER, Items.GRAY_WOOL);

        thighHighs(pFinishedRecipeConsumer, ItemRegister.blackThighHighs.get(), Items.BLACK_WOOL);
        thighHighs(pFinishedRecipeConsumer, ItemRegister.whiteThighHighs.get(), Items.WHITE_WOOL);

        catEars(pFinishedRecipeConsumer, ItemRegister.blackCatEars.get(), Items.BLACK_WOOL);
        catEars(pFinishedRecipeConsumer, ItemRegister.whiteCatEars.get(), Items.WHITE_WOOL);
        catEars(pFinishedRecipeConsumer, ItemRegister.caramelCatEars.get(), Items.YELLOW_WOOL);

        foxEars(pFinishedRecipeConsumer, ItemRegister.blackFoxEars.get(), Items.BLACK_WOOL);
        foxEars(pFinishedRecipeConsumer, ItemRegister.whiteFoxEars.get(), Items.WHITE_WOOL);
        foxEars(pFinishedRecipeConsumer, ItemRegister.redFoxEars.get(), Items.ORANGE_WOOL);
        foxEars(pFinishedRecipeConsumer, ItemRegister.brownFoxEars.get(), Items.BROWN_WOOL);

        bunnyEars(pFinishedRecipeConsumer, ItemRegister.blackBunnyEars.get(), Items.BLACK_WOOL);
        bunnyEars(pFinishedRecipeConsumer, ItemRegister.whiteBunnyEars.get(), Items.WHITE_WOOL);
        bunnyEars(pFinishedRecipeConsumer, ItemRegister.caramelBunnyEars.get(), Items.YELLOW_WOOL);

        smallHorns(pFinishedRecipeConsumer, ItemRegister.purpleHorns.get(), Items.PURPLE_WOOL);
        smallHorns(pFinishedRecipeConsumer, ItemRegister.redHorns.get(), Items.RED_WOOL);
        smallHorns(pFinishedRecipeConsumer, ItemRegister.blackHorns.get(), Items.BLACK_WOOL);

        bigHorn(pFinishedRecipeConsumer, ItemRegister.lightGrayHorns.get(), Items.LIGHT_GRAY_WOOL);
        bigHorn(pFinishedRecipeConsumer, ItemRegister.grayHorns.get(), Items.GRAY_WOOL);
        bigHorn(pFinishedRecipeConsumer, ItemRegister.whiteHorns.get(), Items.WHITE_WOOL);


        catTails(pFinishedRecipeConsumer, ItemRegister.blackCatTail.get(), Items.BLACK_WOOL);
        catTails(pFinishedRecipeConsumer, ItemRegister.whiteCatTail.get(), Items.WHITE_WOOL);
        catTails(pFinishedRecipeConsumer, ItemRegister.caramelCatTail.get(), Items.YELLOW_WOOL);

        foxTails(pFinishedRecipeConsumer, ItemRegister.blackFoxTail.get(), Items.BLACK_WOOL, Items.WHITE_WOOL);
        foxTails(pFinishedRecipeConsumer, ItemRegister.redFoxTail.get(), Items.ORANGE_WOOL, Items.WHITE_WOOL);
        foxTails(pFinishedRecipeConsumer, ItemRegister.whiteFoxTail.get(), Items.WHITE_WOOL, Items.WHITE_WOOL);
        foxTails(pFinishedRecipeConsumer, ItemRegister.brownFoxTail.get(), Items.BROWN_WOOL, Items.WHITE_WOOL);

        bunnyTails(pFinishedRecipeConsumer, ItemRegister.blackBunnyTail.get(), Items.BLACK_WOOL);
        bunnyTails(pFinishedRecipeConsumer, ItemRegister.caramelBunnyTail.get(), Items.YELLOW_WOOL);
        bunnyTails(pFinishedRecipeConsumer, ItemRegister.whiteBunnyTail.get(), Items.WHITE_WOOL);

        devilTails(pFinishedRecipeConsumer, ItemRegister.blackDevilTail.get(), Items.BLACK_WOOL);
        devilTails(pFinishedRecipeConsumer, ItemRegister.redDevilTail.get(), Items.RED_WOOL);
        devilTails(pFinishedRecipeConsumer, ItemRegister.purpleDevilTail.get(), Items.PURPLE_WOOL);


        headBands(pFinishedRecipeConsumer, ItemRegister.headbands.get("black").get(), Items.BLACK_WOOL, Items.WHITE_WOOL);
        headBands(pFinishedRecipeConsumer, ItemRegister.headbands.get("white").get(), Items.WHITE_WOOL, Items.BLACK_WOOL);
        headBands(pFinishedRecipeConsumer, ItemRegister.headbands.get("gray").get(), Items.GRAY_WOOL, Items.WHITE_WOOL);
        headBands(pFinishedRecipeConsumer, ItemRegister.headbands.get("light_gray").get(), Items.LIGHT_GRAY_WOOL, Items.WHITE_WOOL);
        headBands(pFinishedRecipeConsumer, ItemRegister.headbands.get("light_blue").get(), Items.LIGHT_BLUE_WOOL, Items.WHITE_WOOL);
        headBands(pFinishedRecipeConsumer, ItemRegister.headbands.get("red").get(), Items.RED_WOOL, Items.WHITE_WOOL);
        headBands(pFinishedRecipeConsumer, ItemRegister.headbands.get("pink").get(), Items.PINK_WOOL, Items.WHITE_WOOL);
        headBands(pFinishedRecipeConsumer, ItemRegister.headbands.get("magenta").get(), Items.MAGENTA_WOOL, Items.WHITE_WOOL);
        headBands(pFinishedRecipeConsumer, ItemRegister.headbands.get("purple").get(), Items.PURPLE_WOOL, Items.WHITE_WOOL);
        headBands(pFinishedRecipeConsumer, ItemRegister.headbands.get("green").get(), Items.GREEN_WOOL, Items.WHITE_WOOL);
        headBands(pFinishedRecipeConsumer, ItemRegister.headbands.get("lime").get(), Items.LIME_WOOL, Items.WHITE_WOOL);
        headBands(pFinishedRecipeConsumer, ItemRegister.headbands.get("blue").get(), Items.BLUE_WOOL, Items.WHITE_WOOL);
        headBands(pFinishedRecipeConsumer, ItemRegister.headbands.get("cyan").get(), Items.CYAN_WOOL, Items.WHITE_WOOL);
        headBands(pFinishedRecipeConsumer, ItemRegister.headbands.get("yellow").get(), Items.YELLOW_WOOL, Items.WHITE_WOOL);
        headBands(pFinishedRecipeConsumer, ItemRegister.headbands.get("orange").get(), Items.ORANGE_WOOL, Items.WHITE_WOOL);
        headBands(pFinishedRecipeConsumer, ItemRegister.headbands.get("brown").get(), Items.BROWN_WOOL, Items.WHITE_WOOL);


        headBandsUncraft(pFinishedRecipeConsumer,"black");
        headBandsUncraft(pFinishedRecipeConsumer,"white");
        headBandsUncraft(pFinishedRecipeConsumer,"gray");
        headBandsUncraft(pFinishedRecipeConsumer,"light_gray");
        headBandsUncraft(pFinishedRecipeConsumer,"light_blue");
        headBandsUncraft(pFinishedRecipeConsumer,"red");
        headBandsUncraft(pFinishedRecipeConsumer,"pink");
        headBandsUncraft(pFinishedRecipeConsumer,"magenta");
        headBandsUncraft(pFinishedRecipeConsumer,"purple");
        headBandsUncraft(pFinishedRecipeConsumer,"green");
        headBandsUncraft(pFinishedRecipeConsumer,"lime");
        headBandsUncraft(pFinishedRecipeConsumer,"blue");
        headBandsUncraft(pFinishedRecipeConsumer,"orange");
        headBandsUncraft(pFinishedRecipeConsumer,"brown");
        headBandsUncraft(pFinishedRecipeConsumer,"cyan");
        headBandsUncraft(pFinishedRecipeConsumer,"yellow");
    }

    public void coffees(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {

        machineRecipe(pFinishedRecipeConsumer,
                ItemRegister.coffeePowder.get(),
                ItemRegister.coffeePowder.get(),
                Items.SUGAR,
                ItemRegister.latteCoffee.get().getDefaultInstance(),
                100,
                false,
                true,
                ItemRegister.mug.get().getDefaultInstance(),
                new MobEffectInstance(EffectRegister.kawaiiEffect.get(), 15 * 20),
                new MobEffectInstance(MobEffects.DIG_SPEED, 30 * 20));

        machineRecipe(pFinishedRecipeConsumer,
                ItemRegister.coffeePowder.get(),
                Items.SUGAR,
                ItemRegister.cocoaPowder.get(),
                ItemRegister.mochaCoffee.get().getDefaultInstance(),
                100,
                false,
                true,
                ItemRegister.mug.get().getDefaultInstance(),
                new MobEffectInstance(EffectRegister.kawaiiEffect.get(), 15 * 20),
                new MobEffectInstance(MobEffects.REGENERATION, 30 * 20, 1));

        machineRecipe(pFinishedRecipeConsumer,
                ItemRegister.coffeePowder.get(),
                ItemRegister.coffeePowder.get(),
                Items.SUGAR,
                ItemRegister.macchiatoCoffee.get().getDefaultInstance(),
                100,
                true,
                true,
                ItemRegister.mug.get().getDefaultInstance(),
                new MobEffectInstance(EffectRegister.kawaiiEffect.get(), 15 * 20),
                new MobEffectInstance(MobEffects.ABSORPTION, 30 * 20, 1));

        machineRecipe(pFinishedRecipeConsumer,
                ItemRegister.coffeePowder.get(),
                ItemRegister.expressoCoffee.get().getDefaultInstance(),
                100,
                true,
                false,
                ItemRegister.mug.get().getDefaultInstance(),
                new MobEffectInstance(EffectRegister.kawaiiEffect.get(), 15 * 20),
                new MobEffectInstance(MobEffects.NIGHT_VISION, 15 * 20));

        machineRecipe(pFinishedRecipeConsumer,
                ItemRegister.coffeePowder.get(),
                ItemRegister.coffeePowder.get(),
                ItemRegister.doppioCoffee.get().getDefaultInstance(),
                100,
                true,
                false,
                ItemRegister.mug.get().getDefaultInstance(),
                new MobEffectInstance(EffectRegister.kawaiiEffect.get(), 15 * 20),
                new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 30 * 20, 1));

        machineRecipe(pFinishedRecipeConsumer,
                ItemRegister.coffeePowder.get(),
                Items.SUGAR,
                ItemRegister.americanCoffee.get().getDefaultInstance(),
                100,
                true,
                false,
                ItemRegister.mug.get().getDefaultInstance(),
                new MobEffectInstance(EffectRegister.kawaiiEffect.get(), 15 * 20),
                new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 30 * 20));

        machineRecipe(pFinishedRecipeConsumer,
                ItemRegister.coffeePowder.get(),
                ItemRegister.cocoaPowder.get(),
                Items.SUGAR,
                ItemRegister.cappuccinoCoffee.get().getDefaultInstance(),
                100,
                true,
                true,
                ItemRegister.mug.get().getDefaultInstance(),
                new MobEffectInstance(EffectRegister.kawaiiEffect.get(), 15 * 20),
                new MobEffectInstance(MobEffects.DIG_SPEED, 60 * 20, 1));


        pressRecipe(pFinishedRecipeConsumer,
                ItemRegister.coffeePowder.get(),
                Items.WATER_BUCKET,
                ItemRegister.americanCoffee.get().getDefaultInstance(),
                new MobEffectInstance(EffectRegister.kawaiiEffect.get(), 15 * 20),
                new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 15 * 20)
        );

        pressRecipe(pFinishedRecipeConsumer,
                ItemRegister.coffeePowder.get(),
                ItemRegister.americanCoffee.get(),
                ItemRegister.expressoCoffee.get().getDefaultInstance(),
                new MobEffectInstance(EffectRegister.kawaiiEffect.get(), 15 * 20),
                new MobEffectInstance(MobEffects.DIG_SPEED, 15 * 20)
        );

        pressRecipe(pFinishedRecipeConsumer,
                ItemRegister.coffeePowder.get(),
                ItemRegister.coffeePowder.get(),
                Items.WATER_BUCKET,
                ItemRegister.doppioCoffee.get().getDefaultInstance(),
                new MobEffectInstance(EffectRegister.kawaiiEffect.get(), 15 * 20),
                new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 15 * 20, 1)
        );

        pressRecipe(pFinishedRecipeConsumer,
                ItemRegister.doppioCoffee.get(),
                Items.MILK_BUCKET,
                Items.SUGAR,
                ItemRegister.macchiatoCoffee.get().getDefaultInstance(),
                new MobEffectInstance(EffectRegister.kawaiiEffect.get(), 15 * 20),
                new MobEffectInstance(MobEffects.ABSORPTION, 15 * 20, 1)
        );

        pressRecipe(pFinishedRecipeConsumer,
                ItemRegister.expressoCoffee.get(),
                Items.MILK_BUCKET,
                ItemRegister.cocoaPowder.get(),
                ItemRegister.mochaCoffee.get().getDefaultInstance(),
                new MobEffectInstance(EffectRegister.kawaiiEffect.get(), 15 * 20),
                new MobEffectInstance(MobEffects.REGENERATION, 15 * 20, 1)
        );

        pressRecipe(pFinishedRecipeConsumer,
                ItemRegister.expressoCoffee.get(),
                Items.MILK_BUCKET,
                Items.SUGAR,
                ItemRegister.latteCoffee.get().getDefaultInstance(),
                new MobEffectInstance(EffectRegister.kawaiiEffect.get(), 15 * 20),
                new MobEffectInstance(MobEffects.DIG_SPEED, 15 * 20)
        );

        pressRecipe(pFinishedRecipeConsumer,
                ItemRegister.americanCoffee.get(),
                Items.MILK_BUCKET,
                ItemRegister.cocoaPowder.get(),
                ItemRegister.cappuccinoCoffee.get().getDefaultInstance(),
                new MobEffectInstance(EffectRegister.kawaiiEffect.get(), 15 * 20),
                new MobEffectInstance(MobEffects.DIG_SPEED, 30 * 20, 1)
        );
    }

    public void machineRecipe(Consumer<FinishedRecipe> consumer, Item item, Item item1, Item item2, ItemStack result, int ticks, boolean needWater, boolean needMilk, ItemStack onOutput, MobEffectInstance mainEffect, MobEffectInstance secondaryEffect) {
        CoffeeMachineRecipeBuilder builder = new CoffeeMachineRecipeBuilder(
                item,
                item1,
                item2,
                result,
                ticks,
                needWater,
                needMilk,
                onOutput,
                mainEffect,
                secondaryEffect
        );
        builder.unlockedBy(getHasName(item), has(item));
        builder.save(consumer);
    }

    public void machineRecipe(Consumer<FinishedRecipe> consumer, Item item, Item item1, ItemStack result, int ticks, boolean needWater, boolean needMilk, ItemStack onOutput, MobEffectInstance mainEffect, MobEffectInstance secondaryEffect) {
        CoffeeMachineRecipeBuilder builder = new CoffeeMachineRecipeBuilder(
                item,
                item1,
                result,
                ticks,
                needWater,
                needMilk,
                onOutput,
                mainEffect,
                secondaryEffect
        );
        builder.unlockedBy(getHasName(item), has(item));
        builder.save(consumer);
    }

    public void machineRecipe(Consumer<FinishedRecipe> consumer, Item item, ItemStack result, int ticks, boolean needWater, boolean needMilk, ItemStack onOutput, MobEffectInstance mainEffect, MobEffectInstance secondaryEffect) {
        CoffeeMachineRecipeBuilder builder = new CoffeeMachineRecipeBuilder(
                item,
                result,
                ticks,
                needWater,
                needMilk,
                onOutput,
                mainEffect,
                secondaryEffect
        );
        builder.unlockedBy(getHasName(item), has(item));

        builder.save(consumer);
    }


    public void pressRecipe(Consumer<FinishedRecipe> consumer, Item item, Item item1, Item item2, ItemStack result, MobEffectInstance mainEffect, MobEffectInstance secondaryEffect) {
        CoffeePressRecipeBuilder builder = new CoffeePressRecipeBuilder(
                item,
                item1,
                item2,
                result,
                mainEffect,
                secondaryEffect
        );
        builder.unlockedBy(getHasName(item), has(item));
        builder.save(consumer);
    }

    public void pressRecipe(Consumer<FinishedRecipe> consumer, Item item, Item item1, ItemStack result, MobEffectInstance mainEffect, MobEffectInstance secondaryEffect) {
        CoffeePressRecipeBuilder builder = new CoffeePressRecipeBuilder(
                item,
                item1,
                result,
                mainEffect,
                secondaryEffect
        );
        builder.unlockedBy(getHasName(item), has(item));
        builder.save(consumer);
    }

    public void pressRecipe(Consumer<FinishedRecipe> consumer, Item item, ItemStack result, MobEffectInstance mainEffect, MobEffectInstance secondaryEffect) {
        CoffeePressRecipeBuilder builder = new CoffeePressRecipeBuilder(
                item,
                result,
                mainEffect,
                secondaryEffect
        );
        builder.unlockedBy(getHasName(item), has(item));

        builder.save(consumer);
    }
    public void maidOutfitUncrafts(Consumer<FinishedRecipe> consumer, String color) {
        for (var i : ItemRegister.ITEMS.getEntries().stream().toList()) {
            var path = Registry.ITEM.getKey(i.get()).toString().replaceAll(KawaiiDishes.modId+":","");
            if(path.equals(color+"_maid_dress_cat_tail_black")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.blackCatTail.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path+"_uncraft");
            }
            if(path.equals(color+"_maid_dress_cat_tail_white")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.whiteCatTail.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path+"_uncraft");
            }
            if(path.equals(color+"_maid_dress_cat_tail_caramel")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.caramelCatTail.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path+"_uncraft");
            }
            if(path.equals(color+"_maid_dress_bunny_tail_black")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.blackBunnyTail.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path+"_uncraft");
            }
            if(path.equals(color+"_maid_dress_bunny_tail_white")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.whiteBunnyTail.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path+"_uncraft");
            }
            if(path.equals(color+"_maid_dress_bunny_tail_caramel")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.caramelBunnyTail.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path+"_uncraft");
            }
            if(path.equals(color+"_maid_dress_fox_tail_black")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.blackFoxTail.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path+"_uncraft");
            }
            if(path.equals(color+"_maid_dress_fox_tail_brown")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.brownFoxTail.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path+"_uncraft");
            }
            if(path.equals(color+"_maid_dress_fox_tail_white")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.whiteFoxTail.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path+"_uncraft");
            }
            if(path.equals(color+"_maid_dress_fox_tail_red")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.redFoxTail.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path+"_uncraft");
            }
            if(path.equals(color+"_maid_dress_devil_tail_black")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.blackDevilTail.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path+"_uncraft");
            }
            if(path.equals(color+"_maid_dress_devil_tail_purple")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.purpleDevilTail.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path+"_uncraft");
            }
            if(path.equals(color+"_maid_dress_devil_tail_red")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.redDevilTail.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path+"_uncraft");
            }
        }

    }

    public void roasting(Consumer<FinishedRecipe> consumer, Item stage1, Item stage2, Item stage3) {
        simpleCookingRecipe(consumer, "smelting", SimpleCookingSerializer.SMELTING_RECIPE, 200, stage1, stage2, 0.12f);
        simpleCookingRecipe(consumer, "smoking", SimpleCookingSerializer.SMOKING_RECIPE, 100, stage1, stage2, 0.12f);

        simpleCookingRecipe(consumer, "smelting", SimpleCookingSerializer.SMELTING_RECIPE, 200, stage2, stage3, 0.12f);
        simpleCookingRecipe(consumer, "smoking", SimpleCookingSerializer.SMOKING_RECIPE, 100, stage2, stage3, 0.12f);

    }

    public void catTails(Consumer<FinishedRecipe> consumer, Item result, Item item) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("  #")
                .pattern("s##")
                .pattern("ss ")
                .define('#', Ingredient.of(item.getDefaultInstance()))
                .define('s', Ingredient.of(Tags.Items.STRING))
                .unlockedBy(getHasName(item), has(item))
                .save(consumer);
    }

    public void devilTails(Consumer<FinishedRecipe> consumer, Item result, Item item) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("  #")
                .pattern("#s#")
                .pattern("s# ")
                .define('#', Ingredient.of(item.getDefaultInstance()))
                .define('s', Ingredient.of(Tags.Items.STRING))
                .unlockedBy(getHasName(item), has(item))
                .save(consumer);
    }
    public void smallHorns(Consumer<FinishedRecipe> consumer, Item result, Item item) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("# #")
                .define('#', Ingredient.of(item.getDefaultInstance()))
                .unlockedBy(getHasName(item), has(item))
                .save(consumer);
    }

    public void bigHorn(Consumer<FinishedRecipe> consumer, Item result, Item item) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("h h")
                .pattern("# #")
                .define('#', Ingredient.of(item.getDefaultInstance()))
                .define('h', Items.GOAT_HORN)
                .unlockedBy(getHasName(item), has(item))
                .save(consumer);
    }

    public void bunnyTails(Consumer<FinishedRecipe> consumer, Item result, Item item) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("   ")
                .pattern("s##")
                .pattern("ss ")
                .define('#', Ingredient.of(item.getDefaultInstance()))
                .define('s', Ingredient.of(Tags.Items.STRING))
                .unlockedBy(getHasName(item), has(item))
                .save(consumer);
    }

    public void foxTails(Consumer<FinishedRecipe> consumer, Item result, Item item, Item item2) {
        ShapedRecipeBuilder.shaped(result)
                .pattern(" #x")
                .pattern("s##")
                .pattern("ss ")
                .define('#', Ingredient.of(item.getDefaultInstance()))
                .define('x', Ingredient.of(item2.getDefaultInstance()))
                .define('s', Ingredient.of(Tags.Items.STRING))
                .unlockedBy(getHasName(item), has(item))
                .save(consumer);
    }

    public void thighHighs(Consumer<FinishedRecipe> consumer, Item result, Item item) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("# #")
                .pattern("# #")
                .pattern("# #")
                .define('#', Ingredient.of(item.getDefaultInstance()))
                .unlockedBy(getHasName(item), has(item))
                .save(consumer);
    }


    public void shoes(Consumer<FinishedRecipe> consumer, Item result, Item item, Item item2) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("# #")
                .pattern("s s")
                .define('#', Ingredient.of(item.getDefaultInstance()))
                .define('s', Ingredient.of(item2.getDefaultInstance()))
                .unlockedBy(getHasName(item), has(item))
                .unlockedBy(getHasName(item2), has(item2))
                .save(consumer);
    }

    public void catEars(Consumer<FinishedRecipe> consumer, Item result, Item item) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("#s#")
                .pattern("s s")
                .define('#', Ingredient.of(item.getDefaultInstance()))
                .define('s', Ingredient.of(Tags.Items.STRING))
                .unlockedBy(getHasName(item), has(item))
                .save(consumer);
    }
    public void bunnyEars(Consumer<FinishedRecipe> consumer, Item result, Item item) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("# #")
                .pattern("xsx")
                .pattern("s s")
                .define('#', Ingredient.of(item.getDefaultInstance()))
                .define('x', Ingredient.of(Items.WHITE_WOOL))
                .define('s', Ingredient.of(Tags.Items.STRING))
                .unlockedBy(getHasName(item), has(item))
                .save(consumer);
    }
    public void foxEars(Consumer<FinishedRecipe> consumer, Item result, Item item) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("# #")
                .pattern("#s#")
                .pattern("s s")
                .define('#', Ingredient.of(item.getDefaultInstance()))
                .define('s', Ingredient.of(Tags.Items.STRING))
                .unlockedBy(getHasName(item), has(item))
                .save(consumer);
    }

    public void maidOutfit(Consumer<FinishedRecipe> consumer, Item result, Item mainColor, Item secondaryColor) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("# #")
                .pattern("s#s")
                .pattern("@@@")
                .define('#', Ingredient.of(mainColor.getDefaultInstance()))
                .define('@', Ingredient.of(secondaryColor.getDefaultInstance()))
                .define('s', Ingredient.of(Tags.Items.STRING))
                .unlockedBy(getHasName(mainColor), has(mainColor))
                .unlockedBy(getHasName(secondaryColor), has(secondaryColor))
                .save(consumer);
    }

    public void headBands(Consumer<FinishedRecipe> consumer, Item result, Item mainColor, Item secondaryColor) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("###")
                .pattern("@ @")
                .define('#', Ingredient.of(mainColor.getDefaultInstance()))
                .define('@', Ingredient.of(secondaryColor.getDefaultInstance()))
                .unlockedBy(getHasName(mainColor), has(mainColor))
                .unlockedBy(getHasName(secondaryColor), has(secondaryColor))
                .save(consumer);
        for (var i : ItemRegister.ITEMS.getEntries().stream().toList()) {
            var path = Registry.ITEM.getKey(i.get()).toString().replaceAll(KawaiiDishes.modId+":","");
            if (path.equals(result.toString() + "_cat_ears_black")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.blackCatEars.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.toString()+ "_cat_ears_white")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.whiteCatEars.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.getDescription() + "_cat_ears_caramel")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.caramelCatEars.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.toString() + "_bunny_ears_black")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.blackBunnyEars.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.toString()+ "_bunny_ears_white")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.whiteBunnyEars.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.getDescription() + "_bunny_ears_caramel")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.caramelBunnyEars.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.toString() + "_fox_ears_black")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.blackFoxEars.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.toString() + "_fox_ears_brown")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.brownFoxEars.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.toString() + "_fox_ears_red")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.redFoxEars.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.toString() + "_horns_light_gray")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.lightGrayHorns.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.toString() + "_horns_gray")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.grayHorns.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.toString() + "_horns_white")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.whiteHorns.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.toString() + "_horns_red")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.redHorns.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.toString() + "_horns_purple")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.purpleHorns.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.toString() + "_horns_black")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.blackHorns.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }

        }
    }

    public void headBandsUncraft(Consumer<FinishedRecipe> consumer, String color) {
        for (var i : ItemRegister.ITEMS.getEntries().stream().toList()) {
            var path = Registry.ITEM.getKey(i.get()).toString().replaceAll(KawaiiDishes.modId+":","");
            if(path.equals(color+"_headband_cat_ears_black")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.blackCatEars.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path + "_uncraft");
            }
            if(path.equals(color+"_headband_cat_ears_white")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.whiteCatEars.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path + "_uncraft");
            }
            if(path.equals(color+"_headband_cat_ears_caramel")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.caramelCatEars.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path + "_uncraft");
            }
            if(path.equals(color+"_headband_bunny_ears_black")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.blackBunnyEars.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path + "_uncraft");
            }
            if(path.equals(color+"_headband_bunny_ears_white")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.whiteBunnyEars.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path + "_uncraft");
            }
            if(path.equals(color+"_headband_bunny_ears_caramel")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.caramelBunnyEars.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path + "_uncraft");
            }
            if(path.equals(color+"_headband_fox_ears_black")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.blackFoxEars.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path + "_uncraft");
            }
            if(path.equals(color+"_headband_fox_ears_brown")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.brownFoxEars.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path + "_uncraft");
            }
            if(path.equals(color+"_headband_fox_ears_white")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.whiteFoxEars.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path + "_uncraft");
            }
            if(path.equals(color+"_headband_fox_ears_red")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.redFoxEars.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path + "_uncraft");
            }
            if(path.equals(color+"_headband_horns_light_gray")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.lightGrayHorns.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path + "_uncraft");
            }
            if(path.equals(color+"_headband_horns_gray")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.grayHorns.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path + "_uncraft");
            }
            if(path.equals(color+"_headband_horns_white")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.whiteHorns.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path + "_uncraft");
            }
            if(path.equals(color+"_headband_horns_red")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.redHorns.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path + "_uncraft");
            }
            if(path.equals(color+"_headband_horns_purple")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.purpleHorns.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path + "_uncraft");
            }
            if(path.equals(color+"_headband_horns_black")){
                ShapelessRecipeBuilder.shapeless(ItemRegister.blackHorns.get())
                        .requires(Ingredient.of(i.get().getDefaultInstance()))
                        .unlockedBy(getHasName(i.get()), has(i.get()))
                        .save(consumer,path + "_uncraft");
            }
        }
    }

    public void maidOutfitVariations(Consumer<FinishedRecipe> consumer, Item result) {
        for (var i : ItemRegister.ITEMS.getEntries().stream().toList()) {
            var path = Registry.ITEM.getKey(i.get()).toString().replaceAll(KawaiiDishes.modId+":","");
            if (path.equals(result.toString() + "_cat_tail_black")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.blackCatTail.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.toString() + "_cat_tail_white")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.whiteCatTail.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.toString() + "_cat_tail_caramel")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.caramelCatTail.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.toString() + "_bunny_tail_black")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.blackBunnyTail.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.toString() + "_bunny_tail_white")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.whiteBunnyTail.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.toString() + "_bunny_tail_caramel")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.caramelBunnyTail.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.toString() + "_fox_tail_black")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.blackFoxTail.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.toString() + "_fox_tail_red")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.redFoxTail.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.toString() + "_fox_tail_brown")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.brownFoxTail.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.toString() + "_fox_tail_white")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.whiteFoxTail.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.toString() + "_devil_tail_black")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.blackDevilTail.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.toString() + "_devil_tail_red")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.redDevilTail.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if (path.equals(result.toString() + "_devil_tail_purple")) {
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.purpleDevilTail.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }

        }
    }

    public void stool(Consumer<FinishedRecipe> consumer, Item result, Item mainColor) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("#s#")
                .pattern("| |")
                .define('s', Ingredient.of(mainColor.getDefaultInstance()))
                .define('#', Ingredient.of(ItemTags.LOGS))
                .define('|', Ingredient.of(Items.STICK))
                .unlockedBy(getHasName(mainColor), has(mainColor))
                .save(consumer);
    }
}
