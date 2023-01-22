package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.items.*;
import com.hakimen.kawaiidishes.items.armor.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.hakimen.kawaiidishes.KawaiiDishes.modId;

public class ItemRegister {

    public static CreativeModeTab blocks = new CreativeModeTab("kawaiidishes.blocks") {
        @Override
        public ItemStack makeIcon() {
            return coffeeMachine.get().getDefaultInstance();
        }
    };
    public static CreativeModeTab foods = new CreativeModeTab("kawaiidishes.foods") {
        @Override
        public ItemStack makeIcon() {
            return cappuccinoCoffee.get().getDefaultInstance();
        }
    };
    public static CreativeModeTab cosmetics = new CreativeModeTab("kawaiidishes.cosmetics") {
        @Override
        public ItemStack makeIcon() {
            return blackMaidDress.get().getDefaultInstance();
        }
    };
    public static CreativeModeTab decoration = new CreativeModeTab("kawaiidishes.decoration") {
        @Override
        public ItemStack makeIcon() {
            return whiteStool.get().getDefaultInstance();
        }
    };

    public static FoodProperties cake = new FoodProperties.Builder().nutrition(2).saturationMod(0.5f).build();

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, modId);

    public static final RegistryObject<Item> mug = ITEMS.register("mug", () -> new BlockItem(BlockRegister.mug.get(), new Item.Properties().tab(blocks)));
    public static final RegistryObject<Item> glassCup = ITEMS.register("glass_cup", () -> new BlockItem(BlockRegister.glassCup.get(), new Item.Properties().tab(blocks)));
    public static final RegistryObject<Item> milkshakeCup = ITEMS.register("milkshake_cup", () -> new BlockItem(BlockRegister.milkshakeCup.get(), new Item.Properties().tab(blocks)));


    public static final RegistryObject<Item> coffeePress = ITEMS.register("coffee_press", () -> new BlockItem(BlockRegister.coffeePress.get(), new Item.Properties().tab(blocks)));
    public static final RegistryObject<Item> coffeeMachine = ITEMS.register("coffee_machine", () -> new BlockItem(BlockRegister.coffeeMachine.get(), new Item.Properties().tab(blocks)));
    public static final RegistryObject<Item> blender = ITEMS.register("blender", () -> new BlockItem(BlockRegister.blender.get(), new Item.Properties().tab(blocks)));

    public static final RegistryObject<Item> iceCreamMachine = ITEMS.register("ice_cream_machine", () -> new BlockItem(BlockRegister.iceCreamMachine.get(), new Item.Properties().tab(blocks)));

    public static final RegistryObject<CatEars> blackCatEars = ITEMS.register("black_cat_ears", CatEars::new);
    public static final RegistryObject<CatEars> caramelCatEars = ITEMS.register("caramel_cat_ears", CatEars::new);
    public static final RegistryObject<CatEars> whiteCatEars = ITEMS.register("white_cat_ears", CatEars::new);


    public static final RegistryObject<BunnyEars> blackBunnyEars = ITEMS.register("black_bunny_ears", BunnyEars::new);
    public static final RegistryObject<BunnyEars> caramelBunnyEars = ITEMS.register("caramel_bunny_ears", BunnyEars::new);
    public static final RegistryObject<BunnyEars> whiteBunnyEars = ITEMS.register("white_bunny_ears", BunnyEars::new);

    public static final RegistryObject<FoxEars> blackFoxEars = ITEMS.register("black_fox_ears", FoxEars::new);
    public static final RegistryObject<FoxEars> redFoxEars = ITEMS.register("red_fox_ears", FoxEars::new);
    public static final RegistryObject<FoxEars> whiteFoxEars = ITEMS.register("white_fox_ears", FoxEars::new);

    public static final RegistryObject<Horns> lightGrayHorns = ITEMS.register("light_gray_horns", Horns::new);
    public static final RegistryObject<Horns> grayHorns = ITEMS.register("gray_horns", Horns::new);
    public static final RegistryObject<Horns> whiteHorns = ITEMS.register("white_horns", Horns::new);


    public static final RegistryObject<Horns> redHorns = ITEMS.register("red_horns", Horns::new);
    public static final RegistryObject<Horns> purpleHorns = ITEMS.register("purple_horns", Horns::new);
    public static final RegistryObject<Horns> blackHorns = ITEMS.register("black_horns", Horns::new);



    public static final RegistryObject<Item> blackCatTail = ITEMS.register("black_cat_tail",
            () -> new CatTailArmorItem("black_cat_tail.png"));
    public static final RegistryObject<Item> caramelCatTail = ITEMS.register("caramel_cat_tail",
            () -> new CatTailArmorItem("caramel_cat_tail.png"));
    public static final RegistryObject<Item> whiteCatTail = ITEMS.register("white_cat_tail",
            () -> new CatTailArmorItem("white_cat_tail.png"));

    public static final RegistryObject<Item> blackBunnyTail = ITEMS.register("black_bunny_tail",
            () -> new BunnyTailArmorItem("black_bunny_tail.png"));
    public static final RegistryObject<Item> caramelBunnyTail = ITEMS.register("caramel_bunny_tail",
            () -> new BunnyTailArmorItem("caramel_bunny_tail.png"));
    public static final RegistryObject<Item> whiteBunnyTail = ITEMS.register("white_bunny_tail",
            () -> new BunnyTailArmorItem("white_bunny_tail.png"));

    public static final RegistryObject<Item> blackFoxTail = ITEMS.register("black_fox_tail",
            () -> new FoxTailArmorItem("black_fox_tail.png"));
    public static final RegistryObject<Item> redFoxTail = ITEMS.register("red_fox_tail",
            () -> new FoxTailArmorItem("red_fox_tail.png"));
    public static final RegistryObject<Item> whiteFoxTail = ITEMS.register("white_fox_tail",
            () -> new FoxTailArmorItem("white_fox_tail.png"));

    public static final RegistryObject<Item> blackDevilTail = ITEMS.register("black_devil_tail",
            () -> new DevilTailArmorItem("black_devil_tail.png"));
    public static final RegistryObject<Item> redDevilTail = ITEMS.register("red_devil_tail",
            () -> new DevilTailArmorItem("red_devil_tail.png"));
    public static final RegistryObject<Item> purpleDevilTail = ITEMS.register("purple_devil_tail",
            () -> new DevilTailArmorItem("purple_devil_tail.png"));

    public static final RegistryObject<Item> blueMaidDress = ITEMS.register("blue_maid_dress",
            () -> new MaidDressArmorItem("blue_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> blueMaidDressCatTailBlack = ITEMS.register("blue_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("blue_maid_dress_cat_tail_black.png", blueMaidDress.get()));
    public static final RegistryObject<Item> blueMaidDressCatTailCaramel = ITEMS.register("blue_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("blue_maid_dress_cat_tail_caramel.png", blueMaidDress.get()));
    public static final RegistryObject<Item> blueMaidDressCatTailWhite = ITEMS.register("blue_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("blue_maid_dress_cat_tail_white.png", blueMaidDress.get()));
    public static final RegistryObject<Item> blueMaidDressFoxTailBlack = ITEMS.register("blue_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("blue_maid_dress_fox_tail_black.png", blueMaidDress.get()));
    public static final RegistryObject<Item> blueMaidDressFoxTailRed = ITEMS.register("blue_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("blue_maid_dress_fox_tail_red.png", blueMaidDress.get()));
    public static final RegistryObject<Item> blueMaidDressFoxTailWhite = ITEMS.register("blue_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("blue_maid_dress_fox_tail_white.png", blueMaidDress.get()));
    public static final RegistryObject<Item> blueMaidDressBunnyTailBlack = ITEMS.register("blue_maid_dress_bunny_tail_black",
            () -> new BunnyMaidArmorItem("blue_maid_dress_bunny_tail_black.png", blueMaidDress.get()));
    public static final RegistryObject<Item> blueMaidDressBunnyTailCaramel = ITEMS.register("blue_maid_dress_bunny_tail_caramel",
            () -> new BunnyMaidArmorItem("blue_maid_dress_bunny_tail_caramel.png", blueMaidDress.get()));
    public static final RegistryObject<Item> blueMaidDressBunnyTailWhite = ITEMS.register("blue_maid_dress_bunny_tail_white",
            () -> new BunnyMaidArmorItem("blue_maid_dress_bunny_tail_white.png", blueMaidDress.get()));
    public static final RegistryObject<Item> blueMaidDressDevilTailBlack = ITEMS.register("blue_maid_dress_devil_tail_black",
            () -> new DevilMaidArmorItem("blue_maid_dress_devil_tail_black.png", blueMaidDress.get()));
    public static final RegistryObject<Item> blueMaidDressDevilTailPurple = ITEMS.register("blue_maid_dress_devil_tail_purple",
            () -> new DevilMaidArmorItem("blue_maid_dress_devil_tail_purple.png", blueMaidDress.get()));
    public static final RegistryObject<Item> blueMaidDressDevilTailRed = ITEMS.register("blue_maid_dress_devil_tail_red",
            () -> new DevilMaidArmorItem("blue_maid_dress_devil_tail_red.png", blueMaidDress.get()));


    public static final RegistryObject<Item> brownMaidDress = ITEMS.register("brown_maid_dress",
            () -> new MaidDressArmorItem("brown_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> brownMaidDressCatTailBlack = ITEMS.register("brown_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("brown_maid_dress_cat_tail_black.png", brownMaidDress.get()));
    public static final RegistryObject<Item> brownMaidDressCatTailCaramel = ITEMS.register("brown_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("brown_maid_dress_cat_tail_caramel.png", brownMaidDress.get()));
    public static final RegistryObject<Item> brownMaidDressCatTailWhite = ITEMS.register("brown_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("brown_maid_dress_cat_tail_white.png", brownMaidDress.get()));
    public static final RegistryObject<Item> brownMaidDressFoxTailBlack = ITEMS.register("brown_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("brown_maid_dress_fox_tail_black.png", brownMaidDress.get()));
    public static final RegistryObject<Item> brownMaidDressFoxTailRed = ITEMS.register("brown_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("brown_maid_dress_fox_tail_red.png", brownMaidDress.get()));
    public static final RegistryObject<Item> brownMaidDressFoxTailWhite = ITEMS.register("brown_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("brown_maid_dress_fox_tail_white.png", brownMaidDress.get()));
    public static final RegistryObject<Item> brownMaidDressBunnyTailBlack = ITEMS.register("brown_maid_dress_bunny_tail_black",
            () -> new BunnyMaidArmorItem("brown_maid_dress_bunny_tail_black.png", brownMaidDress.get()));
    public static final RegistryObject<Item> brownMaidDressBunnyTailCaramel = ITEMS.register("brown_maid_dress_bunny_tail_caramel",
            () -> new BunnyMaidArmorItem("brown_maid_dress_bunny_tail_caramel.png", brownMaidDress.get()));
    public static final RegistryObject<Item> brownMaidDressBunnyTailWhite = ITEMS.register("brown_maid_dress_bunny_tail_white",
            () -> new BunnyMaidArmorItem("brown_maid_dress_bunny_tail_white.png", brownMaidDress.get()));
    public static final RegistryObject<Item> brownMaidDressDevilTailBlack = ITEMS.register("brown_maid_dress_devil_tail_black",
            () -> new DevilMaidArmorItem("brown_maid_dress_devil_tail_black.png", brownMaidDress.get()));
    public static final RegistryObject<Item> brownMaidDressDevilTailPurple = ITEMS.register("brown_maid_dress_devil_tail_purple",
            () -> new DevilMaidArmorItem("brown_maid_dress_devil_tail_purple.png", brownMaidDress.get()));
    public static final RegistryObject<Item> brownMaidDressDevilTailRed = ITEMS.register("brown_maid_dress_devil_tail_red",
            () -> new DevilMaidArmorItem("brown_maid_dress_devil_tail_red.png", brownMaidDress.get()));


    public static final RegistryObject<Item> cyanMaidDress = ITEMS.register("cyan_maid_dress",
            () -> new MaidDressArmorItem("cyan_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> cyanMaidDressCatTailBlack = ITEMS.register("cyan_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("cyan_maid_dress_cat_tail_black.png", cyanMaidDress.get()));
    public static final RegistryObject<Item> cyanMaidDressCatTailCaramel = ITEMS.register("cyan_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("cyan_maid_dress_cat_tail_caramel.png", cyanMaidDress.get()));
    public static final RegistryObject<Item> cyanMaidDressCatTailWhite = ITEMS.register("cyan_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("cyan_maid_dress_cat_tail_white.png", cyanMaidDress.get()));
    public static final RegistryObject<Item> cyanMaidDressFoxTailBlack = ITEMS.register("cyan_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("cyan_maid_dress_fox_tail_black.png", cyanMaidDress.get()));
    public static final RegistryObject<Item> cyanMaidDressFoxTailRed = ITEMS.register("cyan_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("cyan_maid_dress_fox_tail_red.png", cyanMaidDress.get()));
    public static final RegistryObject<Item> cyanMaidDressFoxTailWhite = ITEMS.register("cyan_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("cyan_maid_dress_fox_tail_white.png", cyanMaidDress.get()));
    public static final RegistryObject<Item> cyanMaidDressBunnyTailBlack = ITEMS.register("cyan_maid_dress_bunny_tail_black",
            () -> new BunnyMaidArmorItem("cyan_maid_dress_bunny_tail_black.png", cyanMaidDress.get()));
    public static final RegistryObject<Item> cyanMaidDressBunnyTailCaramel = ITEMS.register("cyan_maid_dress_bunny_tail_caramel",
            () -> new BunnyMaidArmorItem("cyan_maid_dress_bunny_tail_caramel.png", cyanMaidDress.get()));
    public static final RegistryObject<Item> cyanMaidDressBunnyTailWhite = ITEMS.register("cyan_maid_dress_bunny_tail_white",
            () -> new BunnyMaidArmorItem("cyan_maid_dress_bunny_tail_white.png", cyanMaidDress.get()));
    public static final RegistryObject<Item> cyanMaidDressDevilTailBlack = ITEMS.register("cyan_maid_dress_devil_tail_black",
            () -> new DevilMaidArmorItem("cyan_maid_dress_devil_tail_black.png", cyanMaidDress.get()));
    public static final RegistryObject<Item> cyanMaidDressDevilTailPurple = ITEMS.register("cyan_maid_dress_devil_tail_purple",
            () -> new DevilMaidArmorItem("cyan_maid_dress_devil_tail_purple.png", cyanMaidDress.get()));
    public static final RegistryObject<Item> cyanMaidDressDevilTailRed = ITEMS.register("cyan_maid_dress_devil_tail_red",
            () -> new DevilMaidArmorItem("cyan_maid_dress_devil_tail_red.png", cyanMaidDress.get()));


    public static final RegistryObject<Item> grayMaidDress = ITEMS.register("gray_maid_dress",
            () -> new MaidDressArmorItem("gray_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> grayMaidDressCatTailBlack = ITEMS.register("gray_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("gray_maid_dress_cat_tail_black.png", grayMaidDress.get()));
    public static final RegistryObject<Item> grayMaidDressCatTailCaramel = ITEMS.register("gray_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("gray_maid_dress_cat_tail_caramel.png", grayMaidDress.get()));
    public static final RegistryObject<Item> grayMaidDressCatTailWhite = ITEMS.register("gray_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("gray_maid_dress_cat_tail_white.png", grayMaidDress.get()));
    public static final RegistryObject<Item> grayMaidDressFoxTailBlack = ITEMS.register("gray_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("gray_maid_dress_fox_tail_black.png", grayMaidDress.get()));
    public static final RegistryObject<Item> grayMaidDressFoxTailRed = ITEMS.register("gray_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("gray_maid_dress_fox_tail_red.png", grayMaidDress.get()));
    public static final RegistryObject<Item> grayMaidDressFoxTailWhite = ITEMS.register("gray_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("gray_maid_dress_fox_tail_white.png", grayMaidDress.get()));
    public static final RegistryObject<Item> grayMaidDressBunnyTailBlack = ITEMS.register("gray_maid_dress_bunny_tail_black",
            () -> new BunnyMaidArmorItem("gray_maid_dress_bunny_tail_black.png", grayMaidDress.get()));
    public static final RegistryObject<Item> grayMaidDressBunnyTailCaramel = ITEMS.register("gray_maid_dress_bunny_tail_caramel",
            () -> new BunnyMaidArmorItem("gray_maid_dress_bunny_tail_caramel.png", grayMaidDress.get()));
    public static final RegistryObject<Item> grayMaidDressBunnyTailWhite = ITEMS.register("gray_maid_dress_bunny_tail_white",
            () -> new BunnyMaidArmorItem("gray_maid_dress_bunny_tail_white.png", grayMaidDress.get()));
    public static final RegistryObject<Item> grayMaidDressDevilTailBlack = ITEMS.register("gray_maid_dress_devil_tail_black",
            () -> new DevilMaidArmorItem("gray_maid_dress_devil_tail_black.png", grayMaidDress.get()));
    public static final RegistryObject<Item> grayMaidDressDevilTailPurple = ITEMS.register("gray_maid_dress_devil_tail_purple",
            () -> new DevilMaidArmorItem("gray_maid_dress_devil_tail_purple.png", grayMaidDress.get()));
    public static final RegistryObject<Item> grayMaidDressDevilTailRed = ITEMS.register("gray_maid_dress_devil_tail_red",
            () -> new DevilMaidArmorItem("gray_maid_dress_devil_tail_red.png", grayMaidDress.get()));


    public static final RegistryObject<Item> greenMaidDress = ITEMS.register("green_maid_dress",
            () -> new MaidDressArmorItem("green_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> greenMaidDressCatTailBlack = ITEMS.register("green_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("green_maid_dress_cat_tail_black.png", greenMaidDress.get()));
    public static final RegistryObject<Item> greenMaidDressCatTailCaramel = ITEMS.register("green_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("green_maid_dress_cat_tail_caramel.png", greenMaidDress.get()));
    public static final RegistryObject<Item> greenMaidDressCatTailWhite = ITEMS.register("green_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("green_maid_dress_cat_tail_white.png", greenMaidDress.get()));
    public static final RegistryObject<Item> greenMaidDressFoxTailBlack = ITEMS.register("green_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("green_maid_dress_fox_tail_black.png", greenMaidDress.get()));
    public static final RegistryObject<Item> greenMaidDressFoxTailRed = ITEMS.register("green_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("green_maid_dress_fox_tail_red.png", greenMaidDress.get()));
    public static final RegistryObject<Item> greenMaidDressFoxTailWhite = ITEMS.register("green_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("green_maid_dress_fox_tail_white.png", greenMaidDress.get()));
    public static final RegistryObject<Item> greenMaidDressBunnyTailBlack = ITEMS.register("green_maid_dress_bunny_tail_black",
            () -> new BunnyMaidArmorItem("green_maid_dress_bunny_tail_black.png", greenMaidDress.get()));
    public static final RegistryObject<Item> greenMaidDressBunnyTailCaramel = ITEMS.register("green_maid_dress_bunny_tail_caramel",
            () -> new BunnyMaidArmorItem("green_maid_dress_bunny_tail_caramel.png", greenMaidDress.get()));
    public static final RegistryObject<Item> greenMaidDressBunnyTailWhite = ITEMS.register("green_maid_dress_bunny_tail_white",
            () -> new BunnyMaidArmorItem("green_maid_dress_bunny_tail_white.png", greenMaidDress.get()));
    public static final RegistryObject<Item> greenMaidDressDevilTailBlack = ITEMS.register("green_maid_dress_devil_tail_black",
            () -> new DevilMaidArmorItem("green_maid_dress_devil_tail_black.png", greenMaidDress.get()));
    public static final RegistryObject<Item> greenMaidDressDevilTailPurple = ITEMS.register("green_maid_dress_devil_tail_purple",
            () -> new DevilMaidArmorItem("green_maid_dress_devil_tail_purple.png", greenMaidDress.get()));
    public static final RegistryObject<Item> greenMaidDressDevilTailRed = ITEMS.register("green_maid_dress_devil_tail_red",
            () -> new DevilMaidArmorItem("green_maid_dress_devil_tail_red.png", greenMaidDress.get()));


    public static final RegistryObject<Item> light_blueMaidDress = ITEMS.register("light_blue_maid_dress",
            () -> new MaidDressArmorItem("light_blue_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> light_blueMaidDressCatTailBlack = ITEMS.register("light_blue_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("light_blue_maid_dress_cat_tail_black.png", light_blueMaidDress.get()));
    public static final RegistryObject<Item> light_blueMaidDressCatTailCaramel = ITEMS.register("light_blue_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("light_blue_maid_dress_cat_tail_caramel.png", light_blueMaidDress.get()));
    public static final RegistryObject<Item> light_blueMaidDressCatTailWhite = ITEMS.register("light_blue_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("light_blue_maid_dress_cat_tail_white.png", light_blueMaidDress.get()));
    public static final RegistryObject<Item> light_blueMaidDressFoxTailBlack = ITEMS.register("light_blue_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("light_blue_maid_dress_fox_tail_black.png", light_blueMaidDress.get()));
    public static final RegistryObject<Item> light_blueMaidDressFoxTailRed = ITEMS.register("light_blue_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("light_blue_maid_dress_fox_tail_red.png", light_blueMaidDress.get()));
    public static final RegistryObject<Item> light_blueMaidDressFoxTailWhite = ITEMS.register("light_blue_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("light_blue_maid_dress_fox_tail_white.png", light_blueMaidDress.get()));
    public static final RegistryObject<Item> light_blueMaidDressBunnyTailBlack = ITEMS.register("light_blue_maid_dress_bunny_tail_black",
            () -> new BunnyMaidArmorItem("light_blue_maid_dress_bunny_tail_black.png", light_blueMaidDress.get()));
    public static final RegistryObject<Item> light_blueMaidDressBunnyTailCaramel = ITEMS.register("light_blue_maid_dress_bunny_tail_caramel",
            () -> new BunnyMaidArmorItem("light_blue_maid_dress_bunny_tail_caramel.png", light_blueMaidDress.get()));
    public static final RegistryObject<Item> light_blueMaidDressBunnyTailWhite = ITEMS.register("light_blue_maid_dress_bunny_tail_white",
            () -> new BunnyMaidArmorItem("light_blue_maid_dress_bunny_tail_white.png", light_blueMaidDress.get()));
    public static final RegistryObject<Item> light_blueMaidDressDevilTailBlack = ITEMS.register("light_blue_maid_dress_devil_tail_black",
            () -> new DevilMaidArmorItem("light_blue_maid_dress_devil_tail_black.png", light_blueMaidDress.get()));
    public static final RegistryObject<Item> light_blueMaidDressDevilTailPurple = ITEMS.register("light_blue_maid_dress_devil_tail_purple",
            () -> new DevilMaidArmorItem("light_blue_maid_dress_devil_tail_purple.png", light_blueMaidDress.get()));
    public static final RegistryObject<Item> light_blueMaidDressDevilTailRed = ITEMS.register("light_blue_maid_dress_devil_tail_red",
            () -> new DevilMaidArmorItem("light_blue_maid_dress_devil_tail_red.png", light_blueMaidDress.get()));


    public static final RegistryObject<Item> light_grayMaidDress = ITEMS.register("light_gray_maid_dress",
            () -> new MaidDressArmorItem("light_gray_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> light_grayMaidDressCatTailBlack = ITEMS.register("light_gray_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("light_gray_maid_dress_cat_tail_black.png", light_grayMaidDress.get()));
    public static final RegistryObject<Item> light_grayMaidDressCatTailCaramel = ITEMS.register("light_gray_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("light_gray_maid_dress_cat_tail_caramel.png", light_grayMaidDress.get()));
    public static final RegistryObject<Item> light_grayMaidDressCatTailWhite = ITEMS.register("light_gray_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("light_gray_maid_dress_cat_tail_white.png", light_grayMaidDress.get()));
    public static final RegistryObject<Item> light_grayMaidDressFoxTailBlack = ITEMS.register("light_gray_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("light_gray_maid_dress_fox_tail_black.png", light_grayMaidDress.get()));
    public static final RegistryObject<Item> light_grayMaidDressFoxTailRed = ITEMS.register("light_gray_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("light_gray_maid_dress_fox_tail_red.png", light_grayMaidDress.get()));
    public static final RegistryObject<Item> light_grayMaidDressFoxTailWhite = ITEMS.register("light_gray_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("light_gray_maid_dress_fox_tail_white.png", light_grayMaidDress.get()));
    public static final RegistryObject<Item> light_grayMaidDressBunnyTailBlack = ITEMS.register("light_gray_maid_dress_bunny_tail_black",
            () -> new BunnyMaidArmorItem("light_gray_maid_dress_bunny_tail_black.png", light_grayMaidDress.get()));
    public static final RegistryObject<Item> light_grayMaidDressBunnyTailCaramel = ITEMS.register("light_gray_maid_dress_bunny_tail_caramel",
            () -> new BunnyMaidArmorItem("light_gray_maid_dress_bunny_tail_caramel.png", light_grayMaidDress.get()));
    public static final RegistryObject<Item> light_grayMaidDressBunnyTailWhite = ITEMS.register("light_gray_maid_dress_bunny_tail_white",
            () -> new BunnyMaidArmorItem("light_gray_maid_dress_bunny_tail_white.png", light_grayMaidDress.get()));
    public static final RegistryObject<Item> light_grayMaidDressDevilTailBlack = ITEMS.register("light_gray_maid_dress_devil_tail_black",
            () -> new DevilMaidArmorItem("light_gray_maid_dress_devil_tail_black.png", light_grayMaidDress.get()));
    public static final RegistryObject<Item> light_grayMaidDressDevilTailPurple = ITEMS.register("light_gray_maid_dress_devil_tail_purple",
            () -> new DevilMaidArmorItem("light_gray_maid_dress_devil_tail_purple.png", light_grayMaidDress.get()));
    public static final RegistryObject<Item> light_grayMaidDressDevilTailRed = ITEMS.register("light_gray_maid_dress_devil_tail_red",
            () -> new DevilMaidArmorItem("light_gray_maid_dress_devil_tail_red.png", light_grayMaidDress.get()));


    public static final RegistryObject<Item> limeMaidDress = ITEMS.register("lime_maid_dress",
            () -> new MaidDressArmorItem("lime_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> limeMaidDressCatTailBlack = ITEMS.register("lime_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("lime_maid_dress_cat_tail_black.png", limeMaidDress.get()));
    public static final RegistryObject<Item> limeMaidDressCatTailCaramel = ITEMS.register("lime_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("lime_maid_dress_cat_tail_caramel.png", limeMaidDress.get()));
    public static final RegistryObject<Item> limeMaidDressCatTailWhite = ITEMS.register("lime_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("lime_maid_dress_cat_tail_white.png", limeMaidDress.get()));
    public static final RegistryObject<Item> limeMaidDressFoxTailBlack = ITEMS.register("lime_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("lime_maid_dress_fox_tail_black.png", limeMaidDress.get()));
    public static final RegistryObject<Item> limeMaidDressFoxTailRed = ITEMS.register("lime_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("lime_maid_dress_fox_tail_red.png", limeMaidDress.get()));
    public static final RegistryObject<Item> limeMaidDressFoxTailWhite = ITEMS.register("lime_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("lime_maid_dress_fox_tail_white.png", limeMaidDress.get()));
    public static final RegistryObject<Item> limeMaidDressBunnyTailBlack = ITEMS.register("lime_maid_dress_bunny_tail_black",
            () -> new BunnyMaidArmorItem("lime_maid_dress_bunny_tail_black.png", limeMaidDress.get()));
    public static final RegistryObject<Item> limeMaidDressBunnyTailCaramel = ITEMS.register("lime_maid_dress_bunny_tail_caramel",
            () -> new BunnyMaidArmorItem("lime_maid_dress_bunny_tail_caramel.png", limeMaidDress.get()));
    public static final RegistryObject<Item> limeMaidDressBunnyTailWhite = ITEMS.register("lime_maid_dress_bunny_tail_white",
            () -> new BunnyMaidArmorItem("lime_maid_dress_bunny_tail_white.png", limeMaidDress.get()));
    public static final RegistryObject<Item> limeMaidDressDevilTailBlack = ITEMS.register("lime_maid_dress_devil_tail_black",
            () -> new DevilMaidArmorItem("lime_maid_dress_devil_tail_black.png", limeMaidDress.get()));
    public static final RegistryObject<Item> limeMaidDressDevilTailPurple = ITEMS.register("lime_maid_dress_devil_tail_purple",
            () -> new DevilMaidArmorItem("lime_maid_dress_devil_tail_purple.png", limeMaidDress.get()));
    public static final RegistryObject<Item> limeMaidDressDevilTailRed = ITEMS.register("lime_maid_dress_devil_tail_red",
            () -> new DevilMaidArmorItem("lime_maid_dress_devil_tail_red.png", limeMaidDress.get()));


    public static final RegistryObject<Item> magentaMaidDress = ITEMS.register("magenta_maid_dress",
            () -> new MaidDressArmorItem("magenta_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> magentaMaidDressCatTailBlack = ITEMS.register("magenta_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("magenta_maid_dress_cat_tail_black.png", magentaMaidDress.get()));
    public static final RegistryObject<Item> magentaMaidDressCatTailCaramel = ITEMS.register("magenta_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("magenta_maid_dress_cat_tail_caramel.png", magentaMaidDress.get()));
    public static final RegistryObject<Item> magentaMaidDressCatTailWhite = ITEMS.register("magenta_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("magenta_maid_dress_cat_tail_white.png", magentaMaidDress.get()));
    public static final RegistryObject<Item> magentaMaidDressFoxTailBlack = ITEMS.register("magenta_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("magenta_maid_dress_fox_tail_black.png", magentaMaidDress.get()));
    public static final RegistryObject<Item> magentaMaidDressFoxTailRed = ITEMS.register("magenta_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("magenta_maid_dress_fox_tail_red.png", magentaMaidDress.get()));
    public static final RegistryObject<Item> magentaMaidDressFoxTailWhite = ITEMS.register("magenta_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("magenta_maid_dress_fox_tail_white.png", magentaMaidDress.get()));
    public static final RegistryObject<Item> magentaMaidDressBunnyTailBlack = ITEMS.register("magenta_maid_dress_bunny_tail_black",
            () -> new BunnyMaidArmorItem("magenta_maid_dress_bunny_tail_black.png", magentaMaidDress.get()));
    public static final RegistryObject<Item> magentaMaidDressBunnyTailCaramel = ITEMS.register("magenta_maid_dress_bunny_tail_caramel",
            () -> new BunnyMaidArmorItem("magenta_maid_dress_bunny_tail_caramel.png", magentaMaidDress.get()));
    public static final RegistryObject<Item> magentaMaidDressBunnyTailWhite = ITEMS.register("magenta_maid_dress_bunny_tail_white",
            () -> new BunnyMaidArmorItem("magenta_maid_dress_bunny_tail_white.png", magentaMaidDress.get()));
    public static final RegistryObject<Item> magentaMaidDressDevilTailBlack = ITEMS.register("magenta_maid_dress_devil_tail_black",
            () -> new DevilMaidArmorItem("magenta_maid_dress_devil_tail_black.png", magentaMaidDress.get()));
    public static final RegistryObject<Item> magentaMaidDressDevilTailPurple = ITEMS.register("magenta_maid_dress_devil_tail_purple",
            () -> new DevilMaidArmorItem("magenta_maid_dress_devil_tail_purple.png", magentaMaidDress.get()));
    public static final RegistryObject<Item> magentaMaidDressDevilTailRed = ITEMS.register("magenta_maid_dress_devil_tail_red",
            () -> new DevilMaidArmorItem("magenta_maid_dress_devil_tail_red.png", magentaMaidDress.get()));


    public static final RegistryObject<Item> orangeMaidDress = ITEMS.register("orange_maid_dress",
            () -> new MaidDressArmorItem("orange_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> orangeMaidDressCatTailBlack = ITEMS.register("orange_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("orange_maid_dress_cat_tail_black.png", orangeMaidDress.get()));
    public static final RegistryObject<Item> orangeMaidDressCatTailCaramel = ITEMS.register("orange_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("orange_maid_dress_cat_tail_caramel.png", orangeMaidDress.get()));
    public static final RegistryObject<Item> orangeMaidDressCatTailWhite = ITEMS.register("orange_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("orange_maid_dress_cat_tail_white.png", orangeMaidDress.get()));
    public static final RegistryObject<Item> orangeMaidDressFoxTailBlack = ITEMS.register("orange_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("orange_maid_dress_fox_tail_black.png", orangeMaidDress.get()));
    public static final RegistryObject<Item> orangeMaidDressFoxTailRed = ITEMS.register("orange_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("orange_maid_dress_fox_tail_red.png", orangeMaidDress.get()));
    public static final RegistryObject<Item> orangeMaidDressFoxTailWhite = ITEMS.register("orange_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("orange_maid_dress_fox_tail_white.png", orangeMaidDress.get()));
    public static final RegistryObject<Item> orangeMaidDressBunnyTailBlack = ITEMS.register("orange_maid_dress_bunny_tail_black",
            () -> new BunnyMaidArmorItem("orange_maid_dress_bunny_tail_black.png", orangeMaidDress.get()));
    public static final RegistryObject<Item> orangeMaidDressBunnyTailCaramel = ITEMS.register("orange_maid_dress_bunny_tail_caramel",
            () -> new BunnyMaidArmorItem("orange_maid_dress_bunny_tail_caramel.png", orangeMaidDress.get()));
    public static final RegistryObject<Item> orangeMaidDressBunnyTailWhite = ITEMS.register("orange_maid_dress_bunny_tail_white",
            () -> new BunnyMaidArmorItem("orange_maid_dress_bunny_tail_white.png", orangeMaidDress.get()));
    public static final RegistryObject<Item> orangeMaidDressDevilTailBlack = ITEMS.register("orange_maid_dress_devil_tail_black",
            () -> new DevilMaidArmorItem("orange_maid_dress_devil_tail_black.png", orangeMaidDress.get()));
    public static final RegistryObject<Item> orangeMaidDressDevilTailPurple = ITEMS.register("orange_maid_dress_devil_tail_purple",
            () -> new DevilMaidArmorItem("orange_maid_dress_devil_tail_purple.png", orangeMaidDress.get()));
    public static final RegistryObject<Item> orangeMaidDressDevilTailRed = ITEMS.register("orange_maid_dress_devil_tail_red",
            () -> new DevilMaidArmorItem("orange_maid_dress_devil_tail_red.png", orangeMaidDress.get()));


    public static final RegistryObject<Item> pinkMaidDress = ITEMS.register("pink_maid_dress",
            () -> new MaidDressArmorItem("pink_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> pinkMaidDressCatTailBlack = ITEMS.register("pink_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("pink_maid_dress_cat_tail_black.png", pinkMaidDress.get()));
    public static final RegistryObject<Item> pinkMaidDressCatTailCaramel = ITEMS.register("pink_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("pink_maid_dress_cat_tail_caramel.png", pinkMaidDress.get()));
    public static final RegistryObject<Item> pinkMaidDressCatTailWhite = ITEMS.register("pink_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("pink_maid_dress_cat_tail_white.png", pinkMaidDress.get()));
    public static final RegistryObject<Item> pinkMaidDressFoxTailBlack = ITEMS.register("pink_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("pink_maid_dress_fox_tail_black.png", pinkMaidDress.get()));
    public static final RegistryObject<Item> pinkMaidDressFoxTailRed = ITEMS.register("pink_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("pink_maid_dress_fox_tail_red.png", pinkMaidDress.get()));
    public static final RegistryObject<Item> pinkMaidDressFoxTailWhite = ITEMS.register("pink_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("pink_maid_dress_fox_tail_white.png", pinkMaidDress.get()));
    public static final RegistryObject<Item> pinkMaidDressBunnyTailBlack = ITEMS.register("pink_maid_dress_bunny_tail_black",
            () -> new BunnyMaidArmorItem("pink_maid_dress_bunny_tail_black.png", pinkMaidDress.get()));
    public static final RegistryObject<Item> pinkMaidDressBunnyTailCaramel = ITEMS.register("pink_maid_dress_bunny_tail_caramel",
            () -> new BunnyMaidArmorItem("pink_maid_dress_bunny_tail_caramel.png", pinkMaidDress.get()));
    public static final RegistryObject<Item> pinkMaidDressBunnyTailWhite = ITEMS.register("pink_maid_dress_bunny_tail_white",
            () -> new BunnyMaidArmorItem("pink_maid_dress_bunny_tail_white.png", pinkMaidDress.get()));
    public static final RegistryObject<Item> pinkMaidDressDevilTailBlack = ITEMS.register("pink_maid_dress_devil_tail_black",
            () -> new DevilMaidArmorItem("pink_maid_dress_devil_tail_black.png", pinkMaidDress.get()));
    public static final RegistryObject<Item> pinkMaidDressDevilTailPurple = ITEMS.register("pink_maid_dress_devil_tail_purple",
            () -> new DevilMaidArmorItem("pink_maid_dress_devil_tail_purple.png", pinkMaidDress.get()));
    public static final RegistryObject<Item> pinkMaidDressDevilTailRed = ITEMS.register("pink_maid_dress_devil_tail_red",
            () -> new DevilMaidArmorItem("pink_maid_dress_devil_tail_red.png", pinkMaidDress.get()));


    public static final RegistryObject<Item> redMaidDress = ITEMS.register("red_maid_dress",
            () -> new MaidDressArmorItem("red_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> redMaidDressCatTailBlack = ITEMS.register("red_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("red_maid_dress_cat_tail_black.png", redMaidDress.get()));
    public static final RegistryObject<Item> redMaidDressCatTailCaramel = ITEMS.register("red_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("red_maid_dress_cat_tail_caramel.png", redMaidDress.get()));
    public static final RegistryObject<Item> redMaidDressCatTailWhite = ITEMS.register("red_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("red_maid_dress_cat_tail_white.png", redMaidDress.get()));
    public static final RegistryObject<Item> redMaidDressFoxTailBlack = ITEMS.register("red_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("red_maid_dress_fox_tail_black.png", redMaidDress.get()));
    public static final RegistryObject<Item> redMaidDressFoxTailRed = ITEMS.register("red_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("red_maid_dress_fox_tail_red.png", redMaidDress.get()));
    public static final RegistryObject<Item> redMaidDressFoxTailWhite = ITEMS.register("red_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("red_maid_dress_fox_tail_white.png", redMaidDress.get()));
    public static final RegistryObject<Item> redMaidDressBunnyTailBlack = ITEMS.register("red_maid_dress_bunny_tail_black",
            () -> new BunnyMaidArmorItem("red_maid_dress_bunny_tail_black.png", redMaidDress.get()));
    public static final RegistryObject<Item> redMaidDressBunnyTailCaramel = ITEMS.register("red_maid_dress_bunny_tail_caramel",
            () -> new BunnyMaidArmorItem("red_maid_dress_bunny_tail_caramel.png", redMaidDress.get()));
    public static final RegistryObject<Item> redMaidDressBunnyTailWhite = ITEMS.register("red_maid_dress_bunny_tail_white",
            () -> new BunnyMaidArmorItem("red_maid_dress_bunny_tail_white.png", redMaidDress.get()));
    public static final RegistryObject<Item> redMaidDressDevilTailBlack = ITEMS.register("red_maid_dress_devil_tail_black",
            () -> new DevilMaidArmorItem("red_maid_dress_devil_tail_black.png", redMaidDress.get()));
    public static final RegistryObject<Item> redMaidDressDevilTailPurple = ITEMS.register("red_maid_dress_devil_tail_purple",
            () -> new DevilMaidArmorItem("red_maid_dress_devil_tail_purple.png", redMaidDress.get()));
    public static final RegistryObject<Item> redMaidDressDevilTailRed = ITEMS.register("red_maid_dress_devil_tail_red",
            () -> new DevilMaidArmorItem("red_maid_dress_devil_tail_red.png", redMaidDress.get()));


    public static final RegistryObject<Item> whiteMaidDress = ITEMS.register("white_maid_dress",
            () -> new MaidDressArmorItem("white_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> whiteMaidDressCatTailBlack = ITEMS.register("white_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("white_maid_dress_cat_tail_black.png", whiteMaidDress.get()));
    public static final RegistryObject<Item> whiteMaidDressCatTailCaramel = ITEMS.register("white_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("white_maid_dress_cat_tail_caramel.png", whiteMaidDress.get()));
    public static final RegistryObject<Item> whiteMaidDressCatTailWhite = ITEMS.register("white_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("white_maid_dress_cat_tail_white.png", whiteMaidDress.get()));
    public static final RegistryObject<Item> whiteMaidDressFoxTailBlack = ITEMS.register("white_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("white_maid_dress_fox_tail_black.png", whiteMaidDress.get()));
    public static final RegistryObject<Item> whiteMaidDressFoxTailRed = ITEMS.register("white_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("white_maid_dress_fox_tail_red.png", whiteMaidDress.get()));
    public static final RegistryObject<Item> whiteMaidDressFoxTailWhite = ITEMS.register("white_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("white_maid_dress_fox_tail_white.png", whiteMaidDress.get()));
    public static final RegistryObject<Item> whiteMaidDressBunnyTailBlack = ITEMS.register("white_maid_dress_bunny_tail_black",
            () -> new BunnyMaidArmorItem("white_maid_dress_bunny_tail_black.png", whiteMaidDress.get()));
    public static final RegistryObject<Item> whiteMaidDressBunnyTailCaramel = ITEMS.register("white_maid_dress_bunny_tail_caramel",
            () -> new BunnyMaidArmorItem("white_maid_dress_bunny_tail_caramel.png", whiteMaidDress.get()));
    public static final RegistryObject<Item> whiteMaidDressBunnyTailWhite = ITEMS.register("white_maid_dress_bunny_tail_white",
            () -> new BunnyMaidArmorItem("white_maid_dress_bunny_tail_white.png", whiteMaidDress.get()));
    public static final RegistryObject<Item> whiteMaidDressDevilTailBlack = ITEMS.register("white_maid_dress_devil_tail_black",
            () -> new DevilMaidArmorItem("white_maid_dress_devil_tail_black.png", whiteMaidDress.get()));
    public static final RegistryObject<Item> whiteMaidDressDevilTailPurple = ITEMS.register("white_maid_dress_devil_tail_purple",
            () -> new DevilMaidArmorItem("white_maid_dress_devil_tail_purple.png", whiteMaidDress.get()));
    public static final RegistryObject<Item> whiteMaidDressDevilTailRed = ITEMS.register("white_maid_dress_devil_tail_red",
            () -> new DevilMaidArmorItem("white_maid_dress_devil_tail_red.png", whiteMaidDress.get()));


    public static final RegistryObject<Item> blackMaidDress = ITEMS.register("black_maid_dress",
            () -> new MaidDressArmorItem("black_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> blackMaidDressCatTailBlack = ITEMS.register("black_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("black_maid_dress_cat_tail_black.png", blackMaidDress.get()));
    public static final RegistryObject<Item> blackMaidDressCatTailCaramel = ITEMS.register("black_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("black_maid_dress_cat_tail_caramel.png", blackMaidDress.get()));
    public static final RegistryObject<Item> blackMaidDressCatTailWhite = ITEMS.register("black_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("black_maid_dress_cat_tail_white.png", blackMaidDress.get()));
    public static final RegistryObject<Item> blackMaidDressFoxTailBlack = ITEMS.register("black_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("black_maid_dress_fox_tail_black.png", blackMaidDress.get()));
    public static final RegistryObject<Item> blackMaidDressFoxTailRed = ITEMS.register("black_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("black_maid_dress_fox_tail_red.png", blackMaidDress.get()));
    public static final RegistryObject<Item> blackMaidDressFoxTailWhite = ITEMS.register("black_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("black_maid_dress_fox_tail_white.png", blackMaidDress.get()));
    public static final RegistryObject<Item> blackMaidDressBunnyTailBlack = ITEMS.register("black_maid_dress_bunny_tail_black",
            () -> new BunnyMaidArmorItem("black_maid_dress_bunny_tail_black.png", blackMaidDress.get()));
    public static final RegistryObject<Item> blackMaidDressBunnyTailCaramel = ITEMS.register("black_maid_dress_bunny_tail_caramel",
            () -> new BunnyMaidArmorItem("black_maid_dress_bunny_tail_caramel.png", blackMaidDress.get()));
    public static final RegistryObject<Item> blackMaidDressBunnyTailWhite = ITEMS.register("black_maid_dress_bunny_tail_white",
            () -> new BunnyMaidArmorItem("black_maid_dress_bunny_tail_white.png", blackMaidDress.get()));
    public static final RegistryObject<Item> blackMaidDressDevilTailBlack = ITEMS.register("black_maid_dress_devil_tail_black",
            () -> new DevilMaidArmorItem("black_maid_dress_devil_tail_black.png", blackMaidDress.get()));
    public static final RegistryObject<Item> blackMaidDressDevilTailPurple = ITEMS.register("black_maid_dress_devil_tail_purple",
            () -> new DevilMaidArmorItem("black_maid_dress_devil_tail_purple.png", blackMaidDress.get()));
    public static final RegistryObject<Item> blackMaidDressDevilTailRed = ITEMS.register("black_maid_dress_devil_tail_red",
            () -> new DevilMaidArmorItem("black_maid_dress_devil_tail_red.png", blackMaidDress.get()));


    public static final RegistryObject<Item> yellowMaidDress = ITEMS.register("yellow_maid_dress",
            () -> new MaidDressArmorItem("yellow_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> yellowMaidDressCatTailBlack = ITEMS.register("yellow_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("yellow_maid_dress_cat_tail_black.png", yellowMaidDress.get()));
    public static final RegistryObject<Item> yellowMaidDressCatTailCaramel = ITEMS.register("yellow_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("yellow_maid_dress_cat_tail_caramel.png", yellowMaidDress.get()));
    public static final RegistryObject<Item> yellowMaidDressCatTailWhite = ITEMS.register("yellow_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("yellow_maid_dress_cat_tail_white.png", yellowMaidDress.get()));
    public static final RegistryObject<Item> yellowMaidDressFoxTailBlack = ITEMS.register("yellow_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("yellow_maid_dress_fox_tail_black.png", yellowMaidDress.get()));
    public static final RegistryObject<Item> yellowMaidDressFoxTailRed = ITEMS.register("yellow_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("yellow_maid_dress_fox_tail_red.png", yellowMaidDress.get()));
    public static final RegistryObject<Item> yellowMaidDressFoxTailWhite = ITEMS.register("yellow_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("yellow_maid_dress_fox_tail_white.png", yellowMaidDress.get()));
    public static final RegistryObject<Item> yellowMaidDressBunnyTailBlack = ITEMS.register("yellow_maid_dress_bunny_tail_black",
            () -> new BunnyMaidArmorItem("yellow_maid_dress_bunny_tail_black.png", yellowMaidDress.get()));
    public static final RegistryObject<Item> yellowMaidDressBunnyTailCaramel = ITEMS.register("yellow_maid_dress_bunny_tail_caramel",
            () -> new BunnyMaidArmorItem("yellow_maid_dress_bunny_tail_caramel.png", yellowMaidDress.get()));
    public static final RegistryObject<Item> yellowMaidDressBunnyTailWhite = ITEMS.register("yellow_maid_dress_bunny_tail_white",
            () -> new BunnyMaidArmorItem("yellow_maid_dress_bunny_tail_white.png", yellowMaidDress.get()));
    public static final RegistryObject<Item> yellowMaidDressDevilTailBlack = ITEMS.register("yellow_maid_dress_devil_tail_black",
            () -> new DevilMaidArmorItem("yellow_maid_dress_devil_tail_black.png", yellowMaidDress.get()));
    public static final RegistryObject<Item> yellowMaidDressDevilTailPurple = ITEMS.register("yellow_maid_dress_devil_tail_purple",
            () -> new DevilMaidArmorItem("yellow_maid_dress_devil_tail_purple.png", yellowMaidDress.get()));
    public static final RegistryObject<Item> yellowMaidDressDevilTailRed = ITEMS.register("yellow_maid_dress_devil_tail_red",
            () -> new DevilMaidArmorItem("yellow_maid_dress_devil_tail_red.png", yellowMaidDress.get()));


    public static final RegistryObject<Item> purpleMaidDress = ITEMS.register("purple_maid_dress",
            () -> new MaidDressArmorItem("purple_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> purpleMaidDressCatTailBlack = ITEMS.register("purple_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("purple_maid_dress_cat_tail_black.png", purpleMaidDress.get()));
    public static final RegistryObject<Item> purpleMaidDressCatTailCaramel = ITEMS.register("purple_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("purple_maid_dress_cat_tail_caramel.png", purpleMaidDress.get()));
    public static final RegistryObject<Item> purpleMaidDressCatTailWhite = ITEMS.register("purple_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("purple_maid_dress_cat_tail_white.png", purpleMaidDress.get()));
    public static final RegistryObject<Item> purpleMaidDressFoxTailBlack = ITEMS.register("purple_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("purple_maid_dress_fox_tail_black.png", purpleMaidDress.get()));
    public static final RegistryObject<Item> purpleMaidDressFoxTailRed = ITEMS.register("purple_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("purple_maid_dress_fox_tail_red.png", purpleMaidDress.get()));
    public static final RegistryObject<Item> purpleMaidDressFoxTailWhite = ITEMS.register("purple_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("purple_maid_dress_fox_tail_white.png", purpleMaidDress.get()));
    public static final RegistryObject<Item> purpleMaidDressBunnyTailBlack = ITEMS.register("purple_maid_dress_bunny_tail_black",
            () -> new BunnyMaidArmorItem("purple_maid_dress_bunny_tail_black.png", purpleMaidDress.get()));
    public static final RegistryObject<Item> purpleMaidDressBunnyTailCaramel = ITEMS.register("purple_maid_dress_bunny_tail_caramel",
            () -> new BunnyMaidArmorItem("purple_maid_dress_bunny_tail_caramel.png", purpleMaidDress.get()));
    public static final RegistryObject<Item> purpleMaidDressBunnyTailWhite = ITEMS.register("purple_maid_dress_bunny_tail_white",
            () -> new BunnyMaidArmorItem("purple_maid_dress_bunny_tail_white.png", purpleMaidDress.get()));
    public static final RegistryObject<Item> purpleMaidDressDevilTailBlack = ITEMS.register("purple_maid_dress_devil_tail_black",
            () -> new DevilMaidArmorItem("purple_maid_dress_devil_tail_black.png", purpleMaidDress.get()));
    public static final RegistryObject<Item> purpleMaidDressDevilTailPurple = ITEMS.register("purple_maid_dress_devil_tail_purple",
            () -> new DevilMaidArmorItem("purple_maid_dress_devil_tail_purple.png", purpleMaidDress.get()));
    public static final RegistryObject<Item> purpleMaidDressDevilTailRed = ITEMS.register("purple_maid_dress_devil_tail_red",
            () -> new DevilMaidArmorItem("purple_maid_dress_devil_tail_red.png", purpleMaidDress.get()));

    public static final RegistryObject<Item> whiteThighHighs = ITEMS.register("white_thigh_highs",
            () -> new ThighHighsArmorItem("white_thigh_highs.png", EquipmentSlot.LEGS));
    public static final RegistryObject<Item> blackThighHighs = ITEMS.register("black_thigh_highs",
            () -> new ThighHighsArmorItem("black_thigh_highs.png", EquipmentSlot.LEGS));

    public static final RegistryObject<Item> whiteThighHighsShoes = ITEMS.register("brown_shoes",
            () -> new ThighHighsArmorItem("white_thigh_highs.png", EquipmentSlot.FEET));
    public static final RegistryObject<Item> blackThighHighsShoes = ITEMS.register("dark_brown_shoes",
            () -> new ThighHighsArmorItem("black_thigh_highs.png", EquipmentSlot.FEET));




    public static final RegistryObject<Headband> blueHeadBand = ITEMS.register("blue_headband", () -> new Headband(Items.AIR));
    public static final RegistryObject<CatHeadband> blueHeadBandCatEarsBlack = ITEMS.register("blue_headband_cat_ears_black", () -> new CatHeadband(blueHeadBand.get()));
    public static final RegistryObject<CatHeadband> blueHeadBandCatEarsWhite = ITEMS.register("blue_headband_cat_ears_white", () -> new CatHeadband(blueHeadBand.get()));
    public static final RegistryObject<CatHeadband> blueHeadBandCatEarsCaramel = ITEMS.register("blue_headband_cat_ears_caramel", () -> new CatHeadband(blueHeadBand.get()));
    public static final RegistryObject<Headband> blueHeadBandFoxEarsBlack = ITEMS.register("blue_headband_fox_ears_black", () -> new Headband(blueHeadBand.get()));
    public static final RegistryObject<Headband> blueHeadBandFoxEarsWhite = ITEMS.register("blue_headband_fox_ears_white", () -> new Headband(blueHeadBand.get()));
    public static final RegistryObject<Headband> blueHeadBandFoxEarsRed = ITEMS.register("blue_headband_fox_ears_red", () -> new Headband(blueHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> blueHeadBandBunnyEarsBlack = ITEMS.register("blue_headband_bunny_ears_black", () -> new BunnyHeadband(blueHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> blueHeadBandBunnyEarsWhite = ITEMS.register("blue_headband_bunny_ears_white", () -> new BunnyHeadband(blueHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> blueHeadBandBunnyEarsCaramel = ITEMS.register("blue_headband_bunny_ears_caramel", () -> new BunnyHeadband(blueHeadBand.get()));
    public static final RegistryObject<HornHeadband> blueHeadBandHornsLightGray = ITEMS.register("blue_headband_horns_light_gray", () -> new HornHeadband(blueHeadBand.get()));
    public static final RegistryObject<HornHeadband> blueHeadBandHornsGray = ITEMS.register("blue_headband_horns_gray", () -> new HornHeadband(blueHeadBand.get()));
    public static final RegistryObject<HornHeadband> blueHeadBandHornsWhite = ITEMS.register("blue_headband_horns_white", () -> new HornHeadband(blueHeadBand.get()));
    public static final RegistryObject<HornHeadband> blueHeadBandHornsRed = ITEMS.register("blue_headband_horns_red", () -> new HornHeadband(blueHeadBand.get()));
    public static final RegistryObject<HornHeadband> blueHeadBandHornsPurple = ITEMS.register("blue_headband_horns_purple", () -> new HornHeadband(blueHeadBand.get()));
    public static final RegistryObject<HornHeadband> blueHeadBandHornsBlack = ITEMS.register("blue_headband_horns_black", () -> new HornHeadband(blueHeadBand.get()));


    public static final RegistryObject<Headband> brownHeadBand = ITEMS.register("brown_headband", () -> new Headband(Items.AIR));
    public static final RegistryObject<CatHeadband> brownHeadBandCatEarsBlack = ITEMS.register("brown_headband_cat_ears_black", () -> new CatHeadband(brownHeadBand.get()));
    public static final RegistryObject<CatHeadband> brownHeadBandCatEarsWhite = ITEMS.register("brown_headband_cat_ears_white", () -> new CatHeadband(brownHeadBand.get()));
    public static final RegistryObject<CatHeadband> brownHeadBandCatEarsCaramel = ITEMS.register("brown_headband_cat_ears_caramel", () -> new CatHeadband(brownHeadBand.get()));
    public static final RegistryObject<Headband> brownHeadBandFoxEarsBlack = ITEMS.register("brown_headband_fox_ears_black", () -> new Headband(brownHeadBand.get()));
    public static final RegistryObject<Headband> brownHeadBandFoxEarsWhite = ITEMS.register("brown_headband_fox_ears_white", () -> new Headband(brownHeadBand.get()));
    public static final RegistryObject<Headband> brownHeadBandFoxEarsRed = ITEMS.register("brown_headband_fox_ears_red", () -> new Headband(brownHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> brownHeadBandBunnyEarsBlack = ITEMS.register("brown_headband_bunny_ears_black", () -> new BunnyHeadband(brownHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> brownHeadBandBunnyEarsWhite = ITEMS.register("brown_headband_bunny_ears_white", () -> new BunnyHeadband(brownHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> brownHeadBandBunnyEarsCaramel = ITEMS.register("brown_headband_bunny_ears_caramel", () -> new BunnyHeadband(brownHeadBand.get()));
    public static final RegistryObject<HornHeadband> brownHeadBandHornsLightGray = ITEMS.register("brown_headband_horns_light_gray", () -> new HornHeadband(brownHeadBand.get()));
    public static final RegistryObject<HornHeadband> brownHeadBandHornsGray = ITEMS.register("brown_headband_horns_gray", () -> new HornHeadband(brownHeadBand.get()));
    public static final RegistryObject<HornHeadband> brownHeadBandHornsWhite = ITEMS.register("brown_headband_horns_white", () -> new HornHeadband(brownHeadBand.get()));
    public static final RegistryObject<HornHeadband> brownHeadBandHornsRed = ITEMS.register("brown_headband_horns_red", () -> new HornHeadband(brownHeadBand.get()));
    public static final RegistryObject<HornHeadband> brownHeadBandHornsPurple = ITEMS.register("brown_headband_horns_purple", () -> new HornHeadband(brownHeadBand.get()));
    public static final RegistryObject<HornHeadband> brownHeadBandHornsBlack = ITEMS.register("brown_headband_horns_black", () -> new HornHeadband(brownHeadBand.get()));


    public static final RegistryObject<Headband> cyanHeadBand = ITEMS.register("cyan_headband", () -> new Headband(Items.AIR));
    public static final RegistryObject<CatHeadband> cyanHeadBandCatEarsBlack = ITEMS.register("cyan_headband_cat_ears_black", () -> new CatHeadband(cyanHeadBand.get()));
    public static final RegistryObject<CatHeadband> cyanHeadBandCatEarsWhite = ITEMS.register("cyan_headband_cat_ears_white", () -> new CatHeadband(cyanHeadBand.get()));
    public static final RegistryObject<CatHeadband> cyanHeadBandCatEarsCaramel = ITEMS.register("cyan_headband_cat_ears_caramel", () -> new CatHeadband(cyanHeadBand.get()));
    public static final RegistryObject<Headband> cyanHeadBandFoxEarsBlack = ITEMS.register("cyan_headband_fox_ears_black", () -> new Headband(cyanHeadBand.get()));
    public static final RegistryObject<Headband> cyanHeadBandFoxEarsWhite = ITEMS.register("cyan_headband_fox_ears_white", () -> new Headband(cyanHeadBand.get()));
    public static final RegistryObject<Headband> cyanHeadBandFoxEarsRed = ITEMS.register("cyan_headband_fox_ears_red", () -> new Headband(cyanHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> cyanHeadBandBunnyEarsBlack = ITEMS.register("cyan_headband_bunny_ears_black", () -> new BunnyHeadband(cyanHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> cyanHeadBandBunnyEarsWhite = ITEMS.register("cyan_headband_bunny_ears_white", () -> new BunnyHeadband(cyanHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> cyanHeadBandBunnyEarsCaramel = ITEMS.register("cyan_headband_bunny_ears_caramel", () -> new BunnyHeadband(cyanHeadBand.get()));
    public static final RegistryObject<HornHeadband> cyanHeadBandHornsLightGray = ITEMS.register("cyan_headband_horns_light_gray", () -> new HornHeadband(cyanHeadBand.get()));
    public static final RegistryObject<HornHeadband> cyanHeadBandHornsGray = ITEMS.register("cyan_headband_horns_gray", () -> new HornHeadband(cyanHeadBand.get()));
    public static final RegistryObject<HornHeadband> cyanHeadBandHornsWhite = ITEMS.register("cyan_headband_horns_white", () -> new HornHeadband(cyanHeadBand.get()));
    public static final RegistryObject<HornHeadband> cyanHeadBandHornsRed = ITEMS.register("cyan_headband_horns_red", () -> new HornHeadband(cyanHeadBand.get()));
    public static final RegistryObject<HornHeadband> cyanHeadBandHornsPurple = ITEMS.register("cyan_headband_horns_purple", () -> new HornHeadband(cyanHeadBand.get()));
    public static final RegistryObject<HornHeadband> cyanHeadBandHornsBlack = ITEMS.register("cyan_headband_horns_black", () -> new HornHeadband(cyanHeadBand.get()));


    public static final RegistryObject<Headband> grayHeadBand = ITEMS.register("gray_headband", () -> new Headband(Items.AIR));
    public static final RegistryObject<CatHeadband> grayHeadBandCatEarsBlack = ITEMS.register("gray_headband_cat_ears_black", () -> new CatHeadband(grayHeadBand.get()));
    public static final RegistryObject<CatHeadband> grayHeadBandCatEarsWhite = ITEMS.register("gray_headband_cat_ears_white", () -> new CatHeadband(grayHeadBand.get()));
    public static final RegistryObject<CatHeadband> grayHeadBandCatEarsCaramel = ITEMS.register("gray_headband_cat_ears_caramel", () -> new CatHeadband(grayHeadBand.get()));
    public static final RegistryObject<Headband> grayHeadBandFoxEarsBlack = ITEMS.register("gray_headband_fox_ears_black", () -> new Headband(grayHeadBand.get()));
    public static final RegistryObject<Headband> grayHeadBandFoxEarsWhite = ITEMS.register("gray_headband_fox_ears_white", () -> new Headband(grayHeadBand.get()));
    public static final RegistryObject<Headband> grayHeadBandFoxEarsRed = ITEMS.register("gray_headband_fox_ears_red", () -> new Headband(grayHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> grayHeadBandBunnyEarsBlack = ITEMS.register("gray_headband_bunny_ears_black", () -> new BunnyHeadband(grayHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> grayHeadBandBunnyEarsWhite = ITEMS.register("gray_headband_bunny_ears_white", () -> new BunnyHeadband(grayHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> grayHeadBandBunnyEarsCaramel = ITEMS.register("gray_headband_bunny_ears_caramel", () -> new BunnyHeadband(grayHeadBand.get()));
    public static final RegistryObject<HornHeadband> grayHeadBandHornsLightGray = ITEMS.register("gray_headband_horns_light_gray", () -> new HornHeadband(grayHeadBand.get()));
    public static final RegistryObject<HornHeadband> grayHeadBandHornsGray = ITEMS.register("gray_headband_horns_gray", () -> new HornHeadband(grayHeadBand.get()));
    public static final RegistryObject<HornHeadband> grayHeadBandHornsWhite = ITEMS.register("gray_headband_horns_white", () -> new HornHeadband(grayHeadBand.get()));
    public static final RegistryObject<HornHeadband> grayHeadBandHornsRed = ITEMS.register("gray_headband_horns_red", () -> new HornHeadband(grayHeadBand.get()));
    public static final RegistryObject<HornHeadband> grayHeadBandHornsPurple = ITEMS.register("gray_headband_horns_purple", () -> new HornHeadband(grayHeadBand.get()));
    public static final RegistryObject<HornHeadband> grayHeadBandHornsBlack = ITEMS.register("gray_headband_horns_black", () -> new HornHeadband(grayHeadBand.get()));


    public static final RegistryObject<Headband> greenHeadBand = ITEMS.register("green_headband", () -> new Headband(Items.AIR));
    public static final RegistryObject<CatHeadband> greenHeadBandCatEarsBlack = ITEMS.register("green_headband_cat_ears_black", () -> new CatHeadband(greenHeadBand.get()));
    public static final RegistryObject<CatHeadband> greenHeadBandCatEarsWhite = ITEMS.register("green_headband_cat_ears_white", () -> new CatHeadband(greenHeadBand.get()));
    public static final RegistryObject<CatHeadband> greenHeadBandCatEarsCaramel = ITEMS.register("green_headband_cat_ears_caramel", () -> new CatHeadband(greenHeadBand.get()));
    public static final RegistryObject<Headband> greenHeadBandFoxEarsBlack = ITEMS.register("green_headband_fox_ears_black", () -> new Headband(greenHeadBand.get()));
    public static final RegistryObject<Headband> greenHeadBandFoxEarsWhite = ITEMS.register("green_headband_fox_ears_white", () -> new Headband(greenHeadBand.get()));
    public static final RegistryObject<Headband> greenHeadBandFoxEarsRed = ITEMS.register("green_headband_fox_ears_red", () -> new Headband(greenHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> greenHeadBandBunnyEarsBlack = ITEMS.register("green_headband_bunny_ears_black", () -> new BunnyHeadband(greenHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> greenHeadBandBunnyEarsWhite = ITEMS.register("green_headband_bunny_ears_white", () -> new BunnyHeadband(greenHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> greenHeadBandBunnyEarsCaramel = ITEMS.register("green_headband_bunny_ears_caramel", () -> new BunnyHeadband(greenHeadBand.get()));
    public static final RegistryObject<HornHeadband> greenHeadBandHornsLightGray = ITEMS.register("green_headband_horns_light_gray", () -> new HornHeadband(greenHeadBand.get()));
    public static final RegistryObject<HornHeadband> greenHeadBandHornsGray = ITEMS.register("green_headband_horns_gray", () -> new HornHeadband(greenHeadBand.get()));
    public static final RegistryObject<HornHeadband> greenHeadBandHornsWhite = ITEMS.register("green_headband_horns_white", () -> new HornHeadband(greenHeadBand.get()));
    public static final RegistryObject<HornHeadband> greenHeadBandHornsRed = ITEMS.register("green_headband_horns_red", () -> new HornHeadband(greenHeadBand.get()));
    public static final RegistryObject<HornHeadband> greenHeadBandHornsPurple = ITEMS.register("green_headband_horns_purple", () -> new HornHeadband(greenHeadBand.get()));
    public static final RegistryObject<HornHeadband> greenHeadBandHornsBlack = ITEMS.register("green_headband_horns_black", () -> new HornHeadband(greenHeadBand.get()));


    public static final RegistryObject<Headband> light_blueHeadBand = ITEMS.register("light_blue_headband", () -> new Headband(Items.AIR));
    public static final RegistryObject<CatHeadband> light_blueHeadBandCatEarsBlack = ITEMS.register("light_blue_headband_cat_ears_black", () -> new CatHeadband(light_blueHeadBand.get()));
    public static final RegistryObject<CatHeadband> light_blueHeadBandCatEarsWhite = ITEMS.register("light_blue_headband_cat_ears_white", () -> new CatHeadband(light_blueHeadBand.get()));
    public static final RegistryObject<CatHeadband> light_blueHeadBandCatEarsCaramel = ITEMS.register("light_blue_headband_cat_ears_caramel", () -> new CatHeadband(light_blueHeadBand.get()));
    public static final RegistryObject<Headband> light_blueHeadBandFoxEarsBlack = ITEMS.register("light_blue_headband_fox_ears_black", () -> new Headband(light_blueHeadBand.get()));
    public static final RegistryObject<Headband> light_blueHeadBandFoxEarsWhite = ITEMS.register("light_blue_headband_fox_ears_white", () -> new Headband(light_blueHeadBand.get()));
    public static final RegistryObject<Headband> light_blueHeadBandFoxEarsRed = ITEMS.register("light_blue_headband_fox_ears_red", () -> new Headband(light_blueHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> light_blueHeadBandBunnyEarsBlack = ITEMS.register("light_blue_headband_bunny_ears_black", () -> new BunnyHeadband(light_blueHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> light_blueHeadBandBunnyEarsWhite = ITEMS.register("light_blue_headband_bunny_ears_white", () -> new BunnyHeadband(light_blueHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> light_blueHeadBandBunnyEarsCaramel = ITEMS.register("light_blue_headband_bunny_ears_caramel", () -> new BunnyHeadband(light_blueHeadBand.get()));
    public static final RegistryObject<HornHeadband> light_blueHeadBandHornsLightGray = ITEMS.register("light_blue_headband_horns_light_gray", () -> new HornHeadband(light_blueHeadBand.get()));
    public static final RegistryObject<HornHeadband> light_blueHeadBandHornsGray = ITEMS.register("light_blue_headband_horns_gray", () -> new HornHeadband(light_blueHeadBand.get()));
    public static final RegistryObject<HornHeadband> light_blueHeadBandHornsWhite = ITEMS.register("light_blue_headband_horns_white", () -> new HornHeadband(light_blueHeadBand.get()));
    public static final RegistryObject<HornHeadband> light_blueHeadBandHornsRed = ITEMS.register("light_blue_headband_horns_red", () -> new HornHeadband(light_blueHeadBand.get()));
    public static final RegistryObject<HornHeadband> light_blueHeadBandHornsPurple = ITEMS.register("light_blue_headband_horns_purple", () -> new HornHeadband(light_blueHeadBand.get()));
    public static final RegistryObject<HornHeadband> light_blueHeadBandHornsBlack = ITEMS.register("light_blue_headband_horns_black", () -> new HornHeadband(light_blueHeadBand.get()));


    public static final RegistryObject<Headband> light_grayHeadBand = ITEMS.register("light_gray_headband", () -> new Headband(Items.AIR));
    public static final RegistryObject<CatHeadband> light_grayHeadBandCatEarsBlack = ITEMS.register("light_gray_headband_cat_ears_black", () -> new CatHeadband(light_grayHeadBand.get()));
    public static final RegistryObject<CatHeadband> light_grayHeadBandCatEarsWhite = ITEMS.register("light_gray_headband_cat_ears_white", () -> new CatHeadband(light_grayHeadBand.get()));
    public static final RegistryObject<CatHeadband> light_grayHeadBandCatEarsCaramel = ITEMS.register("light_gray_headband_cat_ears_caramel", () -> new CatHeadband(light_grayHeadBand.get()));
    public static final RegistryObject<Headband> light_grayHeadBandFoxEarsBlack = ITEMS.register("light_gray_headband_fox_ears_black", () -> new Headband(light_grayHeadBand.get()));
    public static final RegistryObject<Headband> light_grayHeadBandFoxEarsWhite = ITEMS.register("light_gray_headband_fox_ears_white", () -> new Headband(light_grayHeadBand.get()));
    public static final RegistryObject<Headband> light_grayHeadBandFoxEarsRed = ITEMS.register("light_gray_headband_fox_ears_red", () -> new Headband(light_grayHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> light_grayHeadBandBunnyEarsBlack = ITEMS.register("light_gray_headband_bunny_ears_black", () -> new BunnyHeadband(light_grayHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> light_grayHeadBandBunnyEarsWhite = ITEMS.register("light_gray_headband_bunny_ears_white", () -> new BunnyHeadband(light_grayHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> light_grayHeadBandBunnyEarsCaramel = ITEMS.register("light_gray_headband_bunny_ears_caramel", () -> new BunnyHeadband(light_grayHeadBand.get()));
    public static final RegistryObject<HornHeadband> light_grayHeadBandHornsLightGray = ITEMS.register("light_gray_headband_horns_light_gray", () -> new HornHeadband(light_grayHeadBand.get()));
    public static final RegistryObject<HornHeadband> light_grayHeadBandHornsGray = ITEMS.register("light_gray_headband_horns_gray", () -> new HornHeadband(light_grayHeadBand.get()));
    public static final RegistryObject<HornHeadband> light_grayHeadBandHornsWhite = ITEMS.register("light_gray_headband_horns_white", () -> new HornHeadband(light_grayHeadBand.get()));
    public static final RegistryObject<HornHeadband> light_grayHeadBandHornsRed = ITEMS.register("light_gray_headband_horns_red", () -> new HornHeadband(light_grayHeadBand.get()));
    public static final RegistryObject<HornHeadband> light_grayHeadBandHornsPurple = ITEMS.register("light_gray_headband_horns_purple", () -> new HornHeadband(light_grayHeadBand.get()));
    public static final RegistryObject<HornHeadband> light_grayHeadBandHornsBlack = ITEMS.register("light_gray_headband_horns_black", () -> new HornHeadband(light_grayHeadBand.get()));


    public static final RegistryObject<Headband> limeHeadBand = ITEMS.register("lime_headband", () -> new Headband(Items.AIR));
    public static final RegistryObject<CatHeadband> limeHeadBandCatEarsBlack = ITEMS.register("lime_headband_cat_ears_black", () -> new CatHeadband(limeHeadBand.get()));
    public static final RegistryObject<CatHeadband> limeHeadBandCatEarsWhite = ITEMS.register("lime_headband_cat_ears_white", () -> new CatHeadband(limeHeadBand.get()));
    public static final RegistryObject<CatHeadband> limeHeadBandCatEarsCaramel = ITEMS.register("lime_headband_cat_ears_caramel", () -> new CatHeadband(limeHeadBand.get()));
    public static final RegistryObject<Headband> limeHeadBandFoxEarsBlack = ITEMS.register("lime_headband_fox_ears_black", () -> new Headband(limeHeadBand.get()));
    public static final RegistryObject<Headband> limeHeadBandFoxEarsWhite = ITEMS.register("lime_headband_fox_ears_white", () -> new Headband(limeHeadBand.get()));
    public static final RegistryObject<Headband> limeHeadBandFoxEarsRed = ITEMS.register("lime_headband_fox_ears_red", () -> new Headband(limeHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> limeHeadBandBunnyEarsBlack = ITEMS.register("lime_headband_bunny_ears_black", () -> new BunnyHeadband(limeHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> limeHeadBandBunnyEarsWhite = ITEMS.register("lime_headband_bunny_ears_white", () -> new BunnyHeadband(limeHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> limeHeadBandBunnyEarsCaramel = ITEMS.register("lime_headband_bunny_ears_caramel", () -> new BunnyHeadband(limeHeadBand.get()));
    public static final RegistryObject<HornHeadband> limeHeadBandHornsLightGray = ITEMS.register("lime_headband_horns_light_gray", () -> new HornHeadband(limeHeadBand.get()));
    public static final RegistryObject<HornHeadband> limeHeadBandHornsGray = ITEMS.register("lime_headband_horns_gray", () -> new HornHeadband(limeHeadBand.get()));
    public static final RegistryObject<HornHeadband> limeHeadBandHornsWhite = ITEMS.register("lime_headband_horns_white", () -> new HornHeadband(limeHeadBand.get()));
    public static final RegistryObject<HornHeadband> limeHeadBandHornsRed = ITEMS.register("lime_headband_horns_red", () -> new HornHeadband(limeHeadBand.get()));
    public static final RegistryObject<HornHeadband> limeHeadBandHornsPurple = ITEMS.register("lime_headband_horns_purple", () -> new HornHeadband(limeHeadBand.get()));
    public static final RegistryObject<HornHeadband> limeHeadBandHornsBlack = ITEMS.register("lime_headband_horns_black", () -> new HornHeadband(limeHeadBand.get()));


    public static final RegistryObject<Headband> magentaHeadBand = ITEMS.register("magenta_headband", () -> new Headband(Items.AIR));
    public static final RegistryObject<CatHeadband> magentaHeadBandCatEarsBlack = ITEMS.register("magenta_headband_cat_ears_black", () -> new CatHeadband(magentaHeadBand.get()));
    public static final RegistryObject<CatHeadband> magentaHeadBandCatEarsWhite = ITEMS.register("magenta_headband_cat_ears_white", () -> new CatHeadband(magentaHeadBand.get()));
    public static final RegistryObject<CatHeadband> magentaHeadBandCatEarsCaramel = ITEMS.register("magenta_headband_cat_ears_caramel", () -> new CatHeadband(magentaHeadBand.get()));
    public static final RegistryObject<Headband> magentaHeadBandFoxEarsBlack = ITEMS.register("magenta_headband_fox_ears_black", () -> new Headband(magentaHeadBand.get()));
    public static final RegistryObject<Headband> magentaHeadBandFoxEarsWhite = ITEMS.register("magenta_headband_fox_ears_white", () -> new Headband(magentaHeadBand.get()));
    public static final RegistryObject<Headband> magentaHeadBandFoxEarsRed = ITEMS.register("magenta_headband_fox_ears_red", () -> new Headband(magentaHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> magentaHeadBandBunnyEarsBlack = ITEMS.register("magenta_headband_bunny_ears_black", () -> new BunnyHeadband(magentaHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> magentaHeadBandBunnyEarsWhite = ITEMS.register("magenta_headband_bunny_ears_white", () -> new BunnyHeadband(magentaHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> magentaHeadBandBunnyEarsCaramel = ITEMS.register("magenta_headband_bunny_ears_caramel", () -> new BunnyHeadband(magentaHeadBand.get()));
    public static final RegistryObject<HornHeadband> magentaHeadBandHornsLightGray = ITEMS.register("magenta_headband_horns_light_gray", () -> new HornHeadband(magentaHeadBand.get()));
    public static final RegistryObject<HornHeadband> magentaHeadBandHornsGray = ITEMS.register("magenta_headband_horns_gray", () -> new HornHeadband(magentaHeadBand.get()));
    public static final RegistryObject<HornHeadband> magentaHeadBandHornsWhite = ITEMS.register("magenta_headband_horns_white", () -> new HornHeadband(magentaHeadBand.get()));
    public static final RegistryObject<HornHeadband> magentaHeadBandHornsRed = ITEMS.register("magenta_headband_horns_red", () -> new HornHeadband(magentaHeadBand.get()));
    public static final RegistryObject<HornHeadband> magentaHeadBandHornsPurple = ITEMS.register("magenta_headband_horns_purple", () -> new HornHeadband(magentaHeadBand.get()));
    public static final RegistryObject<HornHeadband> magentaHeadBandHornsBlack = ITEMS.register("magenta_headband_horns_black", () -> new HornHeadband(magentaHeadBand.get()));


    public static final RegistryObject<Headband> orangeHeadBand = ITEMS.register("orange_headband", () -> new Headband(Items.AIR));
    public static final RegistryObject<CatHeadband> orangeHeadBandCatEarsBlack = ITEMS.register("orange_headband_cat_ears_black", () -> new CatHeadband(orangeHeadBand.get()));
    public static final RegistryObject<CatHeadband> orangeHeadBandCatEarsWhite = ITEMS.register("orange_headband_cat_ears_white", () -> new CatHeadband(orangeHeadBand.get()));
    public static final RegistryObject<CatHeadband> orangeHeadBandCatEarsCaramel = ITEMS.register("orange_headband_cat_ears_caramel", () -> new CatHeadband(orangeHeadBand.get()));
    public static final RegistryObject<Headband> orangeHeadBandFoxEarsBlack = ITEMS.register("orange_headband_fox_ears_black", () -> new Headband(orangeHeadBand.get()));
    public static final RegistryObject<Headband> orangeHeadBandFoxEarsWhite = ITEMS.register("orange_headband_fox_ears_white", () -> new Headband(orangeHeadBand.get()));
    public static final RegistryObject<Headband> orangeHeadBandFoxEarsRed = ITEMS.register("orange_headband_fox_ears_red", () -> new Headband(orangeHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> orangeHeadBandBunnyEarsBlack = ITEMS.register("orange_headband_bunny_ears_black", () -> new BunnyHeadband(orangeHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> orangeHeadBandBunnyEarsWhite = ITEMS.register("orange_headband_bunny_ears_white", () -> new BunnyHeadband(orangeHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> orangeHeadBandBunnyEarsCaramel = ITEMS.register("orange_headband_bunny_ears_caramel", () -> new BunnyHeadband(orangeHeadBand.get()));
    public static final RegistryObject<HornHeadband> orangeHeadBandHornsLightGray = ITEMS.register("orange_headband_horns_light_gray", () -> new HornHeadband(orangeHeadBand.get()));
    public static final RegistryObject<HornHeadband> orangeHeadBandHornsGray = ITEMS.register("orange_headband_horns_gray", () -> new HornHeadband(orangeHeadBand.get()));
    public static final RegistryObject<HornHeadband> orangeHeadBandHornsWhite = ITEMS.register("orange_headband_horns_white", () -> new HornHeadband(orangeHeadBand.get()));
    public static final RegistryObject<HornHeadband> orangeHeadBandHornsRed = ITEMS.register("orange_headband_horns_red", () -> new HornHeadband(orangeHeadBand.get()));
    public static final RegistryObject<HornHeadband> orangeHeadBandHornsPurple = ITEMS.register("orange_headband_horns_purple", () -> new HornHeadband(orangeHeadBand.get()));
    public static final RegistryObject<HornHeadband> orangeHeadBandHornsBlack = ITEMS.register("orange_headband_horns_black", () -> new HornHeadband(orangeHeadBand.get()));


    public static final RegistryObject<Headband> pinkHeadBand = ITEMS.register("pink_headband", () -> new Headband(Items.AIR));
    public static final RegistryObject<CatHeadband> pinkHeadBandCatEarsBlack = ITEMS.register("pink_headband_cat_ears_black", () -> new CatHeadband(pinkHeadBand.get()));
    public static final RegistryObject<CatHeadband> pinkHeadBandCatEarsWhite = ITEMS.register("pink_headband_cat_ears_white", () -> new CatHeadband(pinkHeadBand.get()));
    public static final RegistryObject<CatHeadband> pinkHeadBandCatEarsCaramel = ITEMS.register("pink_headband_cat_ears_caramel", () -> new CatHeadband(pinkHeadBand.get()));
    public static final RegistryObject<Headband> pinkHeadBandFoxEarsBlack = ITEMS.register("pink_headband_fox_ears_black", () -> new Headband(pinkHeadBand.get()));
    public static final RegistryObject<Headband> pinkHeadBandFoxEarsWhite = ITEMS.register("pink_headband_fox_ears_white", () -> new Headband(pinkHeadBand.get()));
    public static final RegistryObject<Headband> pinkHeadBandFoxEarsRed = ITEMS.register("pink_headband_fox_ears_red", () -> new Headband(pinkHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> pinkHeadBandBunnyEarsBlack = ITEMS.register("pink_headband_bunny_ears_black", () -> new BunnyHeadband(pinkHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> pinkHeadBandBunnyEarsWhite = ITEMS.register("pink_headband_bunny_ears_white", () -> new BunnyHeadband(pinkHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> pinkHeadBandBunnyEarsCaramel = ITEMS.register("pink_headband_bunny_ears_caramel", () -> new BunnyHeadband(pinkHeadBand.get()));
    public static final RegistryObject<HornHeadband> pinkHeadBandHornsLightGray = ITEMS.register("pink_headband_horns_light_gray", () -> new HornHeadband(pinkHeadBand.get()));
    public static final RegistryObject<HornHeadband> pinkHeadBandHornsGray = ITEMS.register("pink_headband_horns_gray", () -> new HornHeadband(pinkHeadBand.get()));
    public static final RegistryObject<HornHeadband> pinkHeadBandHornsWhite = ITEMS.register("pink_headband_horns_white", () -> new HornHeadband(pinkHeadBand.get()));
    public static final RegistryObject<HornHeadband> pinkHeadBandHornsRed = ITEMS.register("pink_headband_horns_red", () -> new HornHeadband(pinkHeadBand.get()));
    public static final RegistryObject<HornHeadband> pinkHeadBandHornsPurple = ITEMS.register("pink_headband_horns_purple", () -> new HornHeadband(pinkHeadBand.get()));
    public static final RegistryObject<HornHeadband> pinkHeadBandHornsBlack = ITEMS.register("pink_headband_horns_black", () -> new HornHeadband(pinkHeadBand.get()));


    public static final RegistryObject<Headband> redHeadBand = ITEMS.register("red_headband", () -> new Headband(Items.AIR));
    public static final RegistryObject<CatHeadband> redHeadBandCatEarsBlack = ITEMS.register("red_headband_cat_ears_black", () -> new CatHeadband(redHeadBand.get()));
    public static final RegistryObject<CatHeadband> redHeadBandCatEarsWhite = ITEMS.register("red_headband_cat_ears_white", () -> new CatHeadband(redHeadBand.get()));
    public static final RegistryObject<CatHeadband> redHeadBandCatEarsCaramel = ITEMS.register("red_headband_cat_ears_caramel", () -> new CatHeadband(redHeadBand.get()));
    public static final RegistryObject<Headband> redHeadBandFoxEarsBlack = ITEMS.register("red_headband_fox_ears_black", () -> new Headband(redHeadBand.get()));
    public static final RegistryObject<Headband> redHeadBandFoxEarsWhite = ITEMS.register("red_headband_fox_ears_white", () -> new Headband(redHeadBand.get()));
    public static final RegistryObject<Headband> redHeadBandFoxEarsRed = ITEMS.register("red_headband_fox_ears_red", () -> new Headband(redHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> redHeadBandBunnyEarsBlack = ITEMS.register("red_headband_bunny_ears_black", () -> new BunnyHeadband(redHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> redHeadBandBunnyEarsWhite = ITEMS.register("red_headband_bunny_ears_white", () -> new BunnyHeadband(redHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> redHeadBandBunnyEarsCaramel = ITEMS.register("red_headband_bunny_ears_caramel", () -> new BunnyHeadband(redHeadBand.get()));
    public static final RegistryObject<HornHeadband> redHeadBandHornsLightGray = ITEMS.register("red_headband_horns_light_gray", () -> new HornHeadband(redHeadBand.get()));
    public static final RegistryObject<HornHeadband> redHeadBandHornsGray = ITEMS.register("red_headband_horns_gray", () -> new HornHeadband(redHeadBand.get()));
    public static final RegistryObject<HornHeadband> redHeadBandHornsWhite = ITEMS.register("red_headband_horns_white", () -> new HornHeadband(redHeadBand.get()));
    public static final RegistryObject<HornHeadband> redHeadBandHornsRed = ITEMS.register("red_headband_horns_red", () -> new HornHeadband(redHeadBand.get()));
    public static final RegistryObject<HornHeadband> redHeadBandHornsPurple = ITEMS.register("red_headband_horns_purple", () -> new HornHeadband(redHeadBand.get()));
    public static final RegistryObject<HornHeadband> redHeadBandHornsBlack = ITEMS.register("red_headband_horns_black", () -> new HornHeadband(redHeadBand.get()));


    public static final RegistryObject<Headband> whiteHeadBand = ITEMS.register("white_headband", () -> new Headband(Items.AIR));
    public static final RegistryObject<CatHeadband> whiteHeadBandCatEarsBlack = ITEMS.register("white_headband_cat_ears_black", () -> new CatHeadband(whiteHeadBand.get()));
    public static final RegistryObject<CatHeadband> whiteHeadBandCatEarsWhite = ITEMS.register("white_headband_cat_ears_white", () -> new CatHeadband(whiteHeadBand.get()));
    public static final RegistryObject<CatHeadband> whiteHeadBandCatEarsCaramel = ITEMS.register("white_headband_cat_ears_caramel", () -> new CatHeadband(whiteHeadBand.get()));
    public static final RegistryObject<Headband> whiteHeadBandFoxEarsBlack = ITEMS.register("white_headband_fox_ears_black", () -> new Headband(whiteHeadBand.get()));
    public static final RegistryObject<Headband> whiteHeadBandFoxEarsWhite = ITEMS.register("white_headband_fox_ears_white", () -> new Headband(whiteHeadBand.get()));
    public static final RegistryObject<Headband> whiteHeadBandFoxEarsRed = ITEMS.register("white_headband_fox_ears_red", () -> new Headband(whiteHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> whiteHeadBandBunnyEarsBlack = ITEMS.register("white_headband_bunny_ears_black", () -> new BunnyHeadband(whiteHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> whiteHeadBandBunnyEarsWhite = ITEMS.register("white_headband_bunny_ears_white", () -> new BunnyHeadband(whiteHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> whiteHeadBandBunnyEarsCaramel = ITEMS.register("white_headband_bunny_ears_caramel", () -> new BunnyHeadband(whiteHeadBand.get()));
    public static final RegistryObject<HornHeadband> whiteHeadBandHornsLightGray = ITEMS.register("white_headband_horns_light_gray", () -> new HornHeadband(whiteHeadBand.get()));
    public static final RegistryObject<HornHeadband> whiteHeadBandHornsGray = ITEMS.register("white_headband_horns_gray", () -> new HornHeadband(whiteHeadBand.get()));
    public static final RegistryObject<HornHeadband> whiteHeadBandHornsWhite = ITEMS.register("white_headband_horns_white", () -> new HornHeadband(whiteHeadBand.get()));
    public static final RegistryObject<HornHeadband> whiteHeadBandHornsRed = ITEMS.register("white_headband_horns_red", () -> new HornHeadband(whiteHeadBand.get()));
    public static final RegistryObject<HornHeadband> whiteHeadBandHornsPurple = ITEMS.register("white_headband_horns_purple", () -> new HornHeadband(whiteHeadBand.get()));
    public static final RegistryObject<HornHeadband> whiteHeadBandHornsBlack = ITEMS.register("white_headband_horns_black", () -> new HornHeadband(whiteHeadBand.get()));


    public static final RegistryObject<Headband> blackHeadBand = ITEMS.register("black_headband", () -> new Headband(Items.AIR));
    public static final RegistryObject<CatHeadband> blackHeadBandCatEarsBlack = ITEMS.register("black_headband_cat_ears_black", () -> new CatHeadband(blackHeadBand.get()));
    public static final RegistryObject<CatHeadband> blackHeadBandCatEarsWhite = ITEMS.register("black_headband_cat_ears_white", () -> new CatHeadband(blackHeadBand.get()));
    public static final RegistryObject<CatHeadband> blackHeadBandCatEarsCaramel = ITEMS.register("black_headband_cat_ears_caramel", () -> new CatHeadband(blackHeadBand.get()));
    public static final RegistryObject<Headband> blackHeadBandFoxEarsBlack = ITEMS.register("black_headband_fox_ears_black", () -> new Headband(blackHeadBand.get()));
    public static final RegistryObject<Headband> blackHeadBandFoxEarsWhite = ITEMS.register("black_headband_fox_ears_white", () -> new Headband(blackHeadBand.get()));
    public static final RegistryObject<Headband> blackHeadBandFoxEarsRed = ITEMS.register("black_headband_fox_ears_red", () -> new Headband(blackHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> blackHeadBandBunnyEarsBlack = ITEMS.register("black_headband_bunny_ears_black", () -> new BunnyHeadband(blackHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> blackHeadBandBunnyEarsWhite = ITEMS.register("black_headband_bunny_ears_white", () -> new BunnyHeadband(blackHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> blackHeadBandBunnyEarsCaramel = ITEMS.register("black_headband_bunny_ears_caramel", () -> new BunnyHeadband(blackHeadBand.get()));
    public static final RegistryObject<HornHeadband> blackHeadBandHornsLightGray = ITEMS.register("black_headband_horns_light_gray", () -> new HornHeadband(blackHeadBand.get()));
    public static final RegistryObject<HornHeadband> blackHeadBandHornsGray = ITEMS.register("black_headband_horns_gray", () -> new HornHeadband(blackHeadBand.get()));
    public static final RegistryObject<HornHeadband> blackHeadBandHornsWhite = ITEMS.register("black_headband_horns_white", () -> new HornHeadband(blackHeadBand.get()));
    public static final RegistryObject<HornHeadband> blackHeadBandHornsRed = ITEMS.register("black_headband_horns_red", () -> new HornHeadband(blackHeadBand.get()));
    public static final RegistryObject<HornHeadband> blackHeadBandHornsPurple = ITEMS.register("black_headband_horns_purple", () -> new HornHeadband(blackHeadBand.get()));
    public static final RegistryObject<HornHeadband> blackHeadBandHornsBlack = ITEMS.register("black_headband_horns_black", () -> new HornHeadband(blackHeadBand.get()));


    public static final RegistryObject<Headband> yellowHeadBand = ITEMS.register("yellow_headband", () -> new Headband(Items.AIR));
    public static final RegistryObject<CatHeadband> yellowHeadBandCatEarsBlack = ITEMS.register("yellow_headband_cat_ears_black", () -> new CatHeadband(yellowHeadBand.get()));
    public static final RegistryObject<CatHeadband> yellowHeadBandCatEarsWhite = ITEMS.register("yellow_headband_cat_ears_white", () -> new CatHeadband(yellowHeadBand.get()));
    public static final RegistryObject<CatHeadband> yellowHeadBandCatEarsCaramel = ITEMS.register("yellow_headband_cat_ears_caramel", () -> new CatHeadband(yellowHeadBand.get()));
    public static final RegistryObject<Headband> yellowHeadBandFoxEarsBlack = ITEMS.register("yellow_headband_fox_ears_black", () -> new Headband(yellowHeadBand.get()));
    public static final RegistryObject<Headband> yellowHeadBandFoxEarsWhite = ITEMS.register("yellow_headband_fox_ears_white", () -> new Headband(yellowHeadBand.get()));
    public static final RegistryObject<Headband> yellowHeadBandFoxEarsRed = ITEMS.register("yellow_headband_fox_ears_red", () -> new Headband(yellowHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> yellowHeadBandBunnyEarsBlack = ITEMS.register("yellow_headband_bunny_ears_black", () -> new BunnyHeadband(yellowHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> yellowHeadBandBunnyEarsWhite = ITEMS.register("yellow_headband_bunny_ears_white", () -> new BunnyHeadband(yellowHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> yellowHeadBandBunnyEarsCaramel = ITEMS.register("yellow_headband_bunny_ears_caramel", () -> new BunnyHeadband(yellowHeadBand.get()));
    public static final RegistryObject<HornHeadband> yellowHeadBandHornsLightGray = ITEMS.register("yellow_headband_horns_light_gray", () -> new HornHeadband(yellowHeadBand.get()));
    public static final RegistryObject<HornHeadband> yellowHeadBandHornsGray = ITEMS.register("yellow_headband_horns_gray", () -> new HornHeadband(yellowHeadBand.get()));
    public static final RegistryObject<HornHeadband> yellowHeadBandHornsWhite = ITEMS.register("yellow_headband_horns_white", () -> new HornHeadband(yellowHeadBand.get()));
    public static final RegistryObject<HornHeadband> yellowHeadBandHornsRed = ITEMS.register("yellow_headband_horns_red", () -> new HornHeadband(yellowHeadBand.get()));
    public static final RegistryObject<HornHeadband> yellowHeadBandHornsPurple = ITEMS.register("yellow_headband_horns_purple", () -> new HornHeadband(yellowHeadBand.get()));
    public static final RegistryObject<HornHeadband> yellowHeadBandHornsBlack = ITEMS.register("yellow_headband_horns_black", () -> new HornHeadband(yellowHeadBand.get()));


    public static final RegistryObject<Headband> purpleHeadBand = ITEMS.register("purple_headband", () -> new Headband(Items.AIR));
    public static final RegistryObject<CatHeadband> purpleHeadBandCatEarsBlack = ITEMS.register("purple_headband_cat_ears_black", () -> new CatHeadband(purpleHeadBand.get()));
    public static final RegistryObject<CatHeadband> purpleHeadBandCatEarsWhite = ITEMS.register("purple_headband_cat_ears_white", () -> new CatHeadband(purpleHeadBand.get()));
    public static final RegistryObject<CatHeadband> purpleHeadBandCatEarsCaramel = ITEMS.register("purple_headband_cat_ears_caramel", () -> new CatHeadband(purpleHeadBand.get()));
    public static final RegistryObject<Headband> purpleHeadBandFoxEarsBlack = ITEMS.register("purple_headband_fox_ears_black", () -> new Headband(purpleHeadBand.get()));
    public static final RegistryObject<Headband> purpleHeadBandFoxEarsWhite = ITEMS.register("purple_headband_fox_ears_white", () -> new Headband(purpleHeadBand.get()));
    public static final RegistryObject<Headband> purpleHeadBandFoxEarsRed = ITEMS.register("purple_headband_fox_ears_red", () -> new Headband(purpleHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> purpleHeadBandBunnyEarsBlack = ITEMS.register("purple_headband_bunny_ears_black", () -> new BunnyHeadband(purpleHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> purpleHeadBandBunnyEarsWhite = ITEMS.register("purple_headband_bunny_ears_white", () -> new BunnyHeadband(purpleHeadBand.get()));
    public static final RegistryObject<BunnyHeadband> purpleHeadBandBunnyEarsCaramel = ITEMS.register("purple_headband_bunny_ears_caramel", () -> new BunnyHeadband(purpleHeadBand.get()));
    public static final RegistryObject<HornHeadband> purpleHeadBandHornsLightGray = ITEMS.register("purple_headband_horns_light_gray", () -> new HornHeadband(purpleHeadBand.get()));
    public static final RegistryObject<HornHeadband> purpleHeadBandHornsGray = ITEMS.register("purple_headband_horns_gray", () -> new HornHeadband(purpleHeadBand.get()));
    public static final RegistryObject<HornHeadband> purpleHeadBandHornsWhite = ITEMS.register("purple_headband_horns_white", () -> new HornHeadband(purpleHeadBand.get()));
    public static final RegistryObject<HornHeadband> purpleHeadBandHornsRed = ITEMS.register("purple_headband_horns_red", () -> new HornHeadband(purpleHeadBand.get()));
    public static final RegistryObject<HornHeadband> purpleHeadBandHornsPurple = ITEMS.register("purple_headband_horns_purple", () -> new HornHeadband(purpleHeadBand.get()));
    public static final RegistryObject<HornHeadband> purpleHeadBandHornsBlack = ITEMS.register("purple_headband_horns_black", () -> new HornHeadband(purpleHeadBand.get()));

    public static final RegistryObject<Item> coffeeFruit = ITEMS.register("coffee_fruit", () -> new BlockItem(
            BlockRegister.coffeePlant.get(), new Item.Properties().tab(foods).food(new FoodProperties.Builder().fast().nutrition(2).saturationMod(1.125f).build()
    )));
    public static final RegistryObject<Item> driedCoffeeBeans = ITEMS.register("dried_coffee_beans", () -> new Item(new Item.Properties().tab(foods)));
    public static final RegistryObject<Item> roastedCoffeeBeans = ITEMS.register("roasted_coffee_beans", () -> new Item(new Item.Properties().tab(foods)));
    public static final RegistryObject<Item> coffeePowder = ITEMS.register("coffee_powder", () -> new Item(new Item.Properties().tab(foods)));

    public static final RegistryObject<Item> driedCocoaBeans = ITEMS.register("dried_cocoa_beans", () -> new Item(new Item.Properties().tab(foods)));
    public static final RegistryObject<Item> roastedCocoaBeans = ITEMS.register("roasted_cocoa_beans", () -> new Item(new Item.Properties().tab(foods)));
    public static final RegistryObject<Item> cocoaPowder = ITEMS.register("cocoa_powder", () -> new Item(new Item.Properties().tab(foods)));

    public static final RegistryObject<Item> whiteChocolateBar = ITEMS.register("white_chocolate_bar", () -> new Item(new Item.Properties().tab(foods).food(new FoodProperties.Builder().nutrition(3).saturationMod(1.5f).build())));
    public static final RegistryObject<Item> darkChocolateBar = ITEMS.register("dark_chocolate_bar", () -> new Item(new Item.Properties().tab(foods).food(new FoodProperties.Builder().nutrition(3).saturationMod(1.5f).build())));
    public static final RegistryObject<Item> milkChocolateBar = ITEMS.register("milk_chocolate_bar", () -> new Item(new Item.Properties().tab(foods).food(new FoodProperties.Builder().nutrition(3).saturationMod(1.5f).build())));


    public static final RegistryObject<PlaceableFoodItem> expressoCoffee = ITEMS.register("expresso_coffee", () -> new PlaceableFoodItem(BlockRegister.expressoMug.get(), 3, 1.2f, ItemRegister.mug.get()));
    public static final RegistryObject<PlaceableFoodItem> americanCoffee = ITEMS.register("american_coffee", () -> new PlaceableFoodItem(BlockRegister.americanMug.get(), 4, 1.2f, ItemRegister.mug.get()));
    public static final RegistryObject<PlaceableFoodItem> latteCoffee = ITEMS.register("latte_coffee", () -> new PlaceableFoodItem(BlockRegister.latteMug.get(), 5, 1.2f, ItemRegister.mug.get()));
    public static final RegistryObject<PlaceableFoodItem> mochaCoffee = ITEMS.register("mocha_coffee", () -> new PlaceableFoodItem(BlockRegister.mochaMug.get(), 6, 1.2f, ItemRegister.mug.get()));
    public static final RegistryObject<PlaceableFoodItem> macchiatoCoffee = ITEMS.register("macchiato_coffee", () -> new PlaceableFoodItem(BlockRegister.macchiatoMug.get(), 3, 1.2f, ItemRegister.mug.get()));
    public static final RegistryObject<PlaceableFoodItem> doppioCoffee = ITEMS.register("doppio_coffee", () -> new PlaceableFoodItem(BlockRegister.doppioMug.get(), 5, 1.2f, ItemRegister.mug.get()));
    public static final RegistryObject<PlaceableFoodItem> cappuccinoCoffee = ITEMS.register("cappuccino_coffee", () -> new PlaceableFoodItem(BlockRegister.cappuccinoMug.get(), 6, 1.2f, ItemRegister.mug.get()));

    public static final RegistryObject<PlaceableFoodItem> sweetBerryIceCream = ITEMS.register("sweet_berry_ice_cream", () -> new PlaceableFoodItem(BlockRegister.sweetBerryIceCream.get(), 6, 1.2f, ItemRegister.glassCup.get()));
    public static final RegistryObject<PlaceableFoodItem> napolitanoIceCream = ITEMS.register("napolitano_ice_cream", () -> new PlaceableFoodItem(BlockRegister.napolitanoIceCream.get(), 6, 1.2f, ItemRegister.glassCup.get()));
    public static final RegistryObject<PlaceableFoodItem> creamIceCream = ITEMS.register("cream_ice_cream", () -> new PlaceableFoodItem(BlockRegister.creamIceCream.get(), 6, 1.2f, ItemRegister.glassCup.get()));
    public static final RegistryObject<PlaceableFoodItem> chocolateIceCream = ITEMS.register("chocolate_ice_cream", () -> new PlaceableFoodItem(BlockRegister.chocolateIceCream.get(), 6, 1.2f, ItemRegister.glassCup.get()));
    public static final RegistryObject<PlaceableFoodItem> coffeeIceCream = ITEMS.register("coffee_ice_cream", () -> new PlaceableFoodItem(BlockRegister.coffeeIceCream.get(), 6, 1.2f, ItemRegister.glassCup.get()));
    public static final RegistryObject<PlaceableFoodItem> mochaIceCream = ITEMS.register("mocha_ice_cream", () -> new PlaceableFoodItem(BlockRegister.mochaIceCream.get(), 6, 1.2f, ItemRegister.glassCup.get()));
    public static final RegistryObject<PlaceableFoodItem> glowBerryIceCream = ITEMS.register("glow_berry_ice_cream", () -> new PlaceableFoodItem(BlockRegister.glowBerryIceCream.get(), 6, 1.2f, ItemRegister.glassCup.get()));

    public static final RegistryObject<PlaceableFoodItem> sweetBerryMilkshake = ITEMS.register("sweet_berry_milkshake",() -> new PlaceableFoodItem(BlockRegister.sweetBerryMilkshake.get(), 6, 1.2f, ItemRegister.milkshakeCup.get()));
    public static final RegistryObject<PlaceableFoodItem> chocolateMilkshake = ITEMS.register("chocolate_milkshake",() -> new PlaceableFoodItem(BlockRegister.chocolateMilkshake.get(), 6, 1.2f, ItemRegister.milkshakeCup.get()));
    public static final RegistryObject<PlaceableFoodItem> creamMilkshake = ITEMS.register("cream_milkshake",() -> new PlaceableFoodItem(BlockRegister.creamMilkshake.get(), 6, 1.2f, ItemRegister.milkshakeCup.get()));
    public static final RegistryObject<PlaceableFoodItem> napolitanoMilkshake = ITEMS.register("napolitano_milkshake",() -> new PlaceableFoodItem(BlockRegister.napolitanoMilkshake.get(), 6, 1.2f, ItemRegister.milkshakeCup.get()));
    public static final RegistryObject<PlaceableFoodItem> coffeeMilkshake = ITEMS.register("coffee_milkshake",() -> new PlaceableFoodItem(BlockRegister.coffeeMilkshake.get(), 6, 1.2f, ItemRegister.milkshakeCup.get()));
    public static final RegistryObject<PlaceableFoodItem> mochaMilkshake = ITEMS.register("mocha_milkshake",() -> new PlaceableFoodItem(BlockRegister.mochaMilkshake.get(), 6, 1.2f, ItemRegister.milkshakeCup.get()));
    public static final RegistryObject<PlaceableFoodItem> glowBerryMilkshake = ITEMS.register("glow_berry_milkshake",() -> new PlaceableFoodItem(BlockRegister.glowBerryMilkshake.get(), 6, 1.2f, ItemRegister.milkshakeCup.get()));

    public static final RegistryObject<Item> condensedMilk = ITEMS.register("condensed_milk", () -> new Item(new Item.Properties().tab(foods)));
    public static final RegistryObject<Item> brigadeiroMix = ITEMS.register("brigadeiro_mix", () -> new Item(new Item.Properties().tab(foods)));

    public static final RegistryObject<Item> creamCheese = ITEMS.register("cream_cheese", () -> new Item(new Item.Properties().tab(foods)));

    public static final RegistryObject<Item> cakePiece = ITEMS.register("piece_of_cake", () -> new Item(new Item.Properties().tab(foods).food(cake)));
    public static final RegistryObject<Item> cheeseCakePiece = ITEMS.register("piece_of_cheesecake", () -> new Item(new Item.Properties().tab(foods).food(cake)));
    public static final RegistryObject<Item> chocolateCheeseCakePiece = ITEMS.register("piece_of_chocolate_cheesecake", () -> new Item(new Item.Properties().tab(foods).food(cake)));

    public static final RegistryObject<Item> honeyCheeseCakePiece = ITEMS.register("piece_of_honey_cheesecake", () -> new Item(new Item.Properties().tab(foods).food(cake)));

    public static final RegistryObject<Item> cheeseCake = ITEMS.register("cheese_cake", ()-> new BlockItem(BlockRegister.cheeseCake.get(), new Item.Properties().tab(foods)));
    public static final RegistryObject<Item> chocolateCheeseCake = ITEMS.register("chocolate_cheese_cake", ()-> new BlockItem(BlockRegister.chocolateCheeseCake.get(), new Item.Properties().tab(foods)));
    public static final RegistryObject<Item> honeyCheeseCake = ITEMS.register("honey_cheese_cake", () -> new BlockItem(BlockRegister.honeyCheeseCake.get(),new Item.Properties().tab(foods)));

    public static final RegistryObject<Candy> beijinho = ITEMS.register("beijinho", () -> new Candy(BlockRegister.beijinho.get(), 3, 1.2f));
    public static final RegistryObject<Candy> brigadeiro = ITEMS.register("brigadeiro", () -> new Candy(BlockRegister.brigadeiro.get(), 3, 1.2f));


    public static final RegistryObject<Item> sweetBerryCookie = ITEMS.register("sweet_berry_cookie", () -> new Item(new Item.Properties().tab(foods).food(new FoodProperties.Builder().saturationMod(1).nutrition(3).build())));
    public static final RegistryObject<Item> honeyCookie = ITEMS.register("honey_cookie", () -> new Item(new Item.Properties().tab(foods).food(new FoodProperties.Builder().saturationMod(1).nutrition(3).build())));
    public static final RegistryObject<Item> chocolateCookie = ITEMS.register("chocolate_cookie", () -> new Item(new Item.Properties().tab(foods).food(new FoodProperties.Builder().saturationMod(1).nutrition(3).build())));
    public static final RegistryObject<Item> goldenCookie = ITEMS.register("golden_cookie", () -> new Item(new Item.Properties().tab(foods).food(new FoodProperties.Builder().saturationMod(1).nutrition(6).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION,20*60,1),1f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION,20*20,1),1f).build())));
    public static final RegistryObject<Item> unbindingCookie = ITEMS.register("cookie_of_unbinding", () -> new UnbindingCookie(new Item.Properties().tab(foods).food(new FoodProperties.Builder().saturationMod(1).nutrition(3).alwaysEat().build())));

    public static final RegistryObject<Item> blackStool = ITEMS.register("black_stool", () -> new BlockItem(BlockRegister.blackStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> blueStool = ITEMS.register("blue_stool", () -> new BlockItem(BlockRegister.blueStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> brownStool = ITEMS.register("brown_stool", () -> new BlockItem(BlockRegister.brownStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> cyanStool = ITEMS.register("cyan_stool", () -> new BlockItem(BlockRegister.cyanStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> grayStool = ITEMS.register("gray_stool", () -> new BlockItem(BlockRegister.grayStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> greenStool = ITEMS.register("green_stool", () -> new BlockItem(BlockRegister.greenStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> lightBlueStool = ITEMS.register("light_blue_stool", () -> new BlockItem(BlockRegister.lightBlueStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> lightGrayStool = ITEMS.register("light_gray_stool", () -> new BlockItem(BlockRegister.lightGrayStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> limeStool = ITEMS.register("lime_stool", () -> new BlockItem(BlockRegister.limeStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> magentaStool = ITEMS.register("magenta_stool", () -> new BlockItem(BlockRegister.magentaStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> orangeStool = ITEMS.register("orange_stool", () -> new BlockItem(BlockRegister.orangeStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> pinkStool = ITEMS.register("pink_stool", () -> new BlockItem(BlockRegister.pinkStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> purpleStool = ITEMS.register("purple_stool", () -> new BlockItem(BlockRegister.purpleStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> redStool = ITEMS.register("red_stool", () -> new BlockItem(BlockRegister.redStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> whiteStool = ITEMS.register("white_stool", () -> new BlockItem(BlockRegister.whiteStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> yellowStool = ITEMS.register("yellow_stool", () -> new BlockItem(BlockRegister.yellowStool.get(), new Item.Properties().tab(decoration)));

    public static void register(IEventBus bus) {

        ITEMS.register(bus);
    }
}
