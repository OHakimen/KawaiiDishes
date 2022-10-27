package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.blocks.StoolBlock;
import com.hakimen.kawaiidishes.items.CatEars;
import com.hakimen.kawaiidishes.items.Drink;
import com.hakimen.kawaiidishes.items.armor.*;
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
            return mug.get().getDefaultInstance();
        }
    };
    public static CreativeModeTab foods = new CreativeModeTab("kawaiidishes.foods") {
        @Override
        public ItemStack makeIcon() {
            return roastedCoffeeBeans.get().getDefaultInstance();
        }
    };
    public static CreativeModeTab cosmetics = new CreativeModeTab("kawaiidishes.cosmetics") {
        @Override
        public ItemStack makeIcon() {
            return blackCatEars.get().getDefaultInstance();
        }
    };
    public static CreativeModeTab decoration = new CreativeModeTab("kawaiidishes.decoration") {
        @Override
        public ItemStack makeIcon() {
            return whiteStool.get().getDefaultInstance();
        }
    };

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,modId);

    public static final RegistryObject<Item> mug = ITEMS.register("mug", () -> new BlockItem(BlockRegister.mug.get(), new Item.Properties().tab(blocks)));

    public static final RegistryObject<Item> coffeePress = ITEMS.register("coffee_press", () -> new BlockItem(BlockRegister.coffeePress.get(), new Item.Properties().tab(blocks)));
    public static final RegistryObject<Item> coffeeMachine = ITEMS.register("coffee_machine", () -> new BlockItem(BlockRegister.coffeeMachine.get(), new Item.Properties().tab(blocks)));
    public static final RegistryObject<Item> mortarAndPestle = ITEMS.register("mortar_and_pestle", () -> new BlockItem(BlockRegister.mortarAndPestle.get(), new Item.Properties().tab(blocks)));


    public static final RegistryObject<CatEars> blackCatEars = ITEMS.register("black_cat_ears", CatEars::new);
    public static final RegistryObject<CatEars> caramelCatEars = ITEMS.register("caramel_cat_ears", CatEars::new);
    public static final RegistryObject<CatEars> whiteCatEars = ITEMS.register("white_cat_ears", CatEars::new);


    public static final RegistryObject<Item> blackCatTail = ITEMS.register("black_cat_tail",
            () -> new CatTailArmorItem("black_cat_tail.png"));
    public static final RegistryObject<Item> caramelCatTail = ITEMS.register("caramel_cat_tail",
            () -> new CatTailArmorItem("caramel_cat_tail.png"));
    public static final RegistryObject<Item> whiteCatTail = ITEMS.register("white_cat_tail",
            () -> new CatTailArmorItem("white_cat_tail.png"));

    public static final RegistryObject<Item> blackFoxTail = ITEMS.register("black_fox_tail",
            () -> new FoxTailArmorItem("black_fox_tail.png"));
    public static final RegistryObject<Item> redFoxTail = ITEMS.register("red_fox_tail",
            () -> new FoxTailArmorItem("red_fox_tail.png"));
    public static final RegistryObject<Item> whiteFoxTail = ITEMS.register("white_fox_tail",
            () -> new FoxTailArmorItem("white_fox_tail.png"));

    public static final RegistryObject<Item> blackMaidDress = ITEMS.register("black_maid_dress",
            () -> new MaidDressArmorItem("black_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> blackMaidDressCatTailBlack = ITEMS.register("black_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("black_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> blackMaidDressCatTailCaramel = ITEMS.register("black_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("black_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> blackMaidDressCatTailWhite = ITEMS.register("black_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("black_maid_dress_cat_tail_white.png"));
    public static final RegistryObject<Item> blackMaidDressFoxTailBlack = ITEMS.register("black_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("black_maid_dress_fox_tail_black.png"));
    public static final RegistryObject<Item> blackMaidDressFoxTailRed = ITEMS.register("black_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("black_maid_dress_fox_tail_red.png"));
    public static final RegistryObject<Item> blackMaidDressFoxTailWhite = ITEMS.register("black_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("black_maid_dress_fox_tail_white.png"));


    public static final RegistryObject<Item> blueMaidDress = ITEMS.register("blue_maid_dress",
            () -> new MaidDressArmorItem("blue_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> blueMaidDressCatTailBlack = ITEMS.register("blue_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("blue_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> blueMaidDressCatTailCaramel = ITEMS.register("blue_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("blue_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> blueMaidDressCatTailWhite = ITEMS.register("blue_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("blue_maid_dress_cat_tail_white.png"));
    public static final RegistryObject<Item> blueMaidDressFoxTailBlack = ITEMS.register("blue_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("blue_maid_dress_fox_tail_black.png"));
    public static final RegistryObject<Item> blueMaidDressFoxTailRed = ITEMS.register("blue_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("blue_maid_dress_fox_tail_red.png"));
    public static final RegistryObject<Item> blueMaidDressFoxTailWhite = ITEMS.register("blue_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("blue_maid_dress_fox_tail_white.png"));


    public static final RegistryObject<Item> brownMaidDress = ITEMS.register("brown_maid_dress",
            () -> new MaidDressArmorItem("brown_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> brownMaidDressCatTailBlack = ITEMS.register("brown_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("brown_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> brownMaidDressCatTailCaramel = ITEMS.register("brown_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("brown_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> brownMaidDressCatTailWhite = ITEMS.register("brown_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("brown_maid_dress_cat_tail_white.png"));
    public static final RegistryObject<Item> brownMaidDressFoxTailBlack = ITEMS.register("brown_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("brown_maid_dress_fox_tail_black.png"));
    public static final RegistryObject<Item> brownMaidDressFoxTailRed = ITEMS.register("brown_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("brown_maid_dress_fox_tail_red.png"));
    public static final RegistryObject<Item> brownMaidDressFoxTailWhite = ITEMS.register("brown_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("brown_maid_dress_fox_tail_white.png"));



    public static final RegistryObject<Item> cyanMaidDress = ITEMS.register("cyan_maid_dress",
            () -> new MaidDressArmorItem("cyan_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> cyanMaidDressCatTailBlack = ITEMS.register("cyan_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("cyan_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> cyanMaidDressCatTailCaramel = ITEMS.register("cyan_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("cyan_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> cyanMaidDressCatTailWhite = ITEMS.register("cyan_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("cyan_maid_dress_cat_tail_white.png"));
    public static final RegistryObject<Item> cyanMaidDressFoxTailBlack = ITEMS.register("cyan_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("cyan_maid_dress_fox_tail_black.png"));
    public static final RegistryObject<Item> cyanMaidDressFoxTailRed = ITEMS.register("cyan_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("cyan_maid_dress_fox_tail_red.png"));
    public static final RegistryObject<Item> cyanMaidDressFoxTailWhite = ITEMS.register("cyan_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("cyan_maid_dress_fox_tail_white.png"));


    public static final RegistryObject<Item> grayMaidDress = ITEMS.register("gray_maid_dress",
            () -> new MaidDressArmorItem("gray_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> grayMaidDressCatTailBlack = ITEMS.register("gray_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("gray_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> grayMaidDressCatTailCaramel = ITEMS.register("gray_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("gray_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> grayMaidDressCatTailWhite = ITEMS.register("gray_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("gray_maid_dress_cat_tail_white.png"));
    public static final RegistryObject<Item> grayMaidDressFoxTailBlack = ITEMS.register("gray_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("gray_maid_dress_fox_tail_black.png"));
    public static final RegistryObject<Item> grayMaidDressFoxTailRed = ITEMS.register("gray_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("gray_maid_dress_fox_tail_red.png"));
    public static final RegistryObject<Item> grayMaidDressFoxTailWhite = ITEMS.register("gray_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("gray_maid_dress_fox_tail_white.png"));

    public static final RegistryObject<Item> greenMaidDress = ITEMS.register("green_maid_dress",
            () -> new MaidDressArmorItem("green_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> greenMaidDressCatTailBlack = ITEMS.register("green_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("green_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> greenMaidDressCatTailCaramel = ITEMS.register("green_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("green_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> greenMaidDressCatTailWhite = ITEMS.register("green_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("green_maid_dress_cat_tail_white.png"));
    public static final RegistryObject<Item> greenMaidDressFoxTailBlack = ITEMS.register("green_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("green_maid_dress_fox_tail_black.png"));
    public static final RegistryObject<Item> greenMaidDressFoxTailRed = ITEMS.register("green_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("green_maid_dress_fox_tail_red.png"));
    public static final RegistryObject<Item> greenMaidDressFoxTailWhite = ITEMS.register("green_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("green_maid_dress_fox_tail_white.png"));

    public static final RegistryObject<Item> light_blueMaidDress = ITEMS.register("light_blue_maid_dress",
            () -> new MaidDressArmorItem("light_blue_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> light_blueMaidDressCatTailBlack = ITEMS.register("light_blue_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("light_blue_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> light_blueMaidDressCatTailCaramel = ITEMS.register("light_blue_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("light_blue_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> light_blueMaidDressCatTailWhite = ITEMS.register("light_blue_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("light_blue_maid_dress_cat_tail_white.png"));
    public static final RegistryObject<Item> light_blueMaidDressFoxTailBlack = ITEMS.register("light_blue_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("light_blue_maid_dress_fox_tail_black.png"));
    public static final RegistryObject<Item> light_blueMaidDressFoxTailRed = ITEMS.register("light_blue_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("light_blue_maid_dress_fox_tail_red.png"));
    public static final RegistryObject<Item> light_blueMaidDressFoxTailWhite = ITEMS.register("light_blue_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("light_blue_maid_dress_fox_tail_white.png"));


    public static final RegistryObject<Item> light_grayMaidDress = ITEMS.register("light_gray_maid_dress",
            () -> new MaidDressArmorItem("light_gray_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> light_grayMaidDressCatTailBlack = ITEMS.register("light_gray_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("light_gray_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> light_grayMaidDressCatTailCaramel = ITEMS.register("light_gray_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("light_gray_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> light_grayMaidDressCatTailWhite = ITEMS.register("light_gray_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("light_gray_maid_dress_cat_tail_white.png"));
    public static final RegistryObject<Item> light_grayMaidDressFoxTailBlack = ITEMS.register("light_gray_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("light_gray_maid_dress_fox_tail_black.png"));
    public static final RegistryObject<Item> light_grayMaidDressFoxTailRed = ITEMS.register("light_gray_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("light_gray_maid_dress_fox_tail_red.png"));
    public static final RegistryObject<Item> light_grayMaidDressFoxTailWhite = ITEMS.register("light_gray_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("light_gray_maid_dress_fox_tail_white.png"));


    public static final RegistryObject<Item> limeMaidDress = ITEMS.register("lime_maid_dress",
            () -> new MaidDressArmorItem("lime_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> limeMaidDressCatTailBlack = ITEMS.register("lime_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("lime_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> limeMaidDressCatTailCaramel = ITEMS.register("lime_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("lime_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> limeMaidDressCatTailWhite = ITEMS.register("lime_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("lime_maid_dress_cat_tail_white.png"));
    public static final RegistryObject<Item> limeMaidDressFoxTailBlack = ITEMS.register("lime_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("lime_maid_dress_fox_tail_black.png"));
    public static final RegistryObject<Item> limeMaidDressFoxTailRed = ITEMS.register("lime_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("lime_maid_dress_fox_tail_red.png"));
    public static final RegistryObject<Item> limeMaidDressFoxTailWhite = ITEMS.register("lime_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("lime_maid_dress_fox_tail_white.png"));

    public static final RegistryObject<Item> magentaMaidDress = ITEMS.register("magenta_maid_dress",
            () -> new MaidDressArmorItem("magenta_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> magentaMaidDressCatTailBlack = ITEMS.register("magenta_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("magenta_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> magentaMaidDressCatTailCaramel = ITEMS.register("magenta_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("magenta_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> magentaMaidDressCatTailWhite = ITEMS.register("magenta_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("magenta_maid_dress_cat_tail_white.png"));
    public static final RegistryObject<Item> magentaMaidDressFoxTailBlack = ITEMS.register("magenta_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("magenta_maid_dress_fox_tail_black.png"));
    public static final RegistryObject<Item> magentaMaidDressFoxTailRed = ITEMS.register("magenta_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("magenta_maid_dress_fox_tail_red.png"));
    public static final RegistryObject<Item> magentaMaidDressFoxTailWhite = ITEMS.register("magenta_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("magenta_maid_dress_fox_tail_white.png"));


    public static final RegistryObject<Item> orangeMaidDress = ITEMS.register("orange_maid_dress",
            () -> new MaidDressArmorItem("orange_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> orangeMaidDressCatTailBlack = ITEMS.register("orange_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("orange_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> orangeMaidDressCatTailCaramel = ITEMS.register("orange_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("orange_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> orangeMaidDressCatTailWhite = ITEMS.register("orange_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("orange_maid_dress_cat_tail_white.png"));
    public static final RegistryObject<Item> orangeMaidDressFoxTailBlack = ITEMS.register("orange_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("orange_maid_dress_fox_tail_black.png"));
    public static final RegistryObject<Item> orangeMaidDressFoxTailRed = ITEMS.register("orange_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("orange_maid_dress_fox_tail_red.png"));
    public static final RegistryObject<Item> orangeMaidDressFoxTailWhite = ITEMS.register("orange_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("orange_maid_dress_fox_tail_white.png"));



    public static final RegistryObject<Item> pinkMaidDress = ITEMS.register("pink_maid_dress",
            () -> new MaidDressArmorItem("pink_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> pinkMaidDressCatTailBlack = ITEMS.register("pink_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("pink_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> pinkMaidDressCatTailCaramel = ITEMS.register("pink_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("pink_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> pinkMaidDressCatTailWhite = ITEMS.register("pink_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("pink_maid_dress_cat_tail_white.png"));
    public static final RegistryObject<Item> pinkMaidDressFoxTailBlack = ITEMS.register("pink_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("pink_maid_dress_fox_tail_black.png"));
    public static final RegistryObject<Item> pinkMaidDressFoxTailRed = ITEMS.register("pink_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("pink_maid_dress_fox_tail_red.png"));
    public static final RegistryObject<Item> pinkMaidDressFoxTailWhite = ITEMS.register("pink_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("pink_maid_dress_fox_tail_white.png"));

    public static final RegistryObject<Item> redMaidDress = ITEMS.register("red_maid_dress",
            () -> new MaidDressArmorItem("red_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> redMaidDressCatTailBlack = ITEMS.register("red_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("red_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> redMaidDressCatTailCaramel = ITEMS.register("red_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("red_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> redMaidDressCatTailWhite = ITEMS.register("red_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("red_maid_dress_cat_tail_white.png"));
    public static final RegistryObject<Item> redMaidDressFoxTailBlack = ITEMS.register("red_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("red_maid_dress_fox_tail_black.png"));
    public static final RegistryObject<Item> redMaidDressFoxTailRed = ITEMS.register("red_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("red_maid_dress_fox_tail_red.png"));
    public static final RegistryObject<Item> redMaidDressFoxTailWhite = ITEMS.register("red_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("red_maid_dress_fox_tail_white.png"));

    public static final RegistryObject<Item> whiteMaidDress = ITEMS.register("white_maid_dress",
            () -> new MaidDressArmorItem("white_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> whiteMaidDressCatTailBlack = ITEMS.register("white_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("white_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> whiteMaidDressCatTailCaramel = ITEMS.register("white_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("white_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> whiteMaidDressCatTailWhite = ITEMS.register("white_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("white_maid_dress_cat_tail_white.png"));
    public static final RegistryObject<Item> whiteMaidDressFoxTailBlack = ITEMS.register("white_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("white_maid_dress_fox_tail_black.png"));
    public static final RegistryObject<Item> whiteMaidDressFoxTailRed = ITEMS.register("white_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("white_maid_dress_fox_tail_red.png"));
    public static final RegistryObject<Item> whiteMaidDressFoxTailWhite = ITEMS.register("white_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("white_maid_dress_fox_tail_white.png"));

    public static final RegistryObject<Item> yellowMaidDress = ITEMS.register("yellow_maid_dress",
            () -> new MaidDressArmorItem("yellow_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> yellowMaidDressCatTailBlack = ITEMS.register("yellow_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("yellow_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> yellowMaidDressCatTailCaramel = ITEMS.register("yellow_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("yellow_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> yellowMaidDressCatTailWhite = ITEMS.register("yellow_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("yellow_maid_dress_cat_tail_white.png"));
    public static final RegistryObject<Item> yellowMaidDressFoxTailBlack = ITEMS.register("yellow_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("yellow_maid_dress_fox_tail_black.png"));
    public static final RegistryObject<Item> yellowMaidDressFoxTailRed = ITEMS.register("yellow_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("yellow_maid_dress_fox_tail_red.png"));
    public static final RegistryObject<Item> yellowMaidDressFoxTailWhite = ITEMS.register("yellow_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("yellow_maid_dress_fox_tail_white.png"));

    public static final RegistryObject<Item> purpleMaidDress = ITEMS.register("purple_maid_dress",
            () -> new MaidDressArmorItem("purple_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> purpleMaidDressCatTailBlack = ITEMS.register("purple_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("purple_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> purpleMaidDressCatTailCaramel = ITEMS.register("purple_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("purple_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> purpleMaidDressCatTailWhite = ITEMS.register("purple_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("purple_maid_dress_cat_tail_white.png"));
    public static final RegistryObject<Item> purpleMaidDressFoxTailBlack = ITEMS.register("purple_maid_dress_fox_tail_black",
            () -> new FoxMaidArmorItem("purple_maid_dress_fox_tail_black.png"));
    public static final RegistryObject<Item> purpleMaidDressFoxTailRed = ITEMS.register("purple_maid_dress_fox_tail_red",
            () -> new FoxMaidArmorItem("purple_maid_dress_fox_tail_red.png"));
    public static final RegistryObject<Item> purpleMaidDressFoxTailWhite = ITEMS.register("purple_maid_dress_fox_tail_white",
            () -> new FoxMaidArmorItem("purple_maid_dress_fox_tail_white.png"));

    public static final RegistryObject<Item> whiteThighHighs = ITEMS.register("white_thigh_highs",
            () -> new ThighHighsArmorItem("white_thigh_highs.png", EquipmentSlot.LEGS));
    public static final RegistryObject<Item> blackThighHighs = ITEMS.register("black_thigh_highs",
            () -> new ThighHighsArmorItem("black_thigh_highs.png",EquipmentSlot.LEGS));

    public static final RegistryObject<Item> whiteThighHighsShoes = ITEMS.register("brown_shoes",
            () -> new ThighHighsArmorItem("white_thigh_highs.png", EquipmentSlot.FEET));
    public static final RegistryObject<Item> blackThighHighsShoes = ITEMS.register("dark_brown_shoes",
            () -> new ThighHighsArmorItem("black_thigh_highs.png",EquipmentSlot.FEET));

    //Fox tails for now































    public static final RegistryObject<Item> coffeeFruit = ITEMS.register("coffee_fruit", () -> new BlockItem(
            BlockRegister.coffeePlant.get(),new Item.Properties().tab(foods).food(new FoodProperties.Builder().fast().nutrition(2).saturationMod(2).build()
    )));
    public static final RegistryObject<Item> driedCoffeeBeans = ITEMS.register("dried_coffee_beans", () ->  new Item(new Item.Properties().tab(foods)));
    public static final RegistryObject<Item> roastedCoffeeBeans = ITEMS.register("roasted_coffee_beans",() ->  new Item(new Item.Properties().tab(foods)));
    public static final RegistryObject<Item> coffeePowder = ITEMS.register("coffee_powder",() ->  new Item(new Item.Properties().tab(foods)));

    public static final RegistryObject<Item> driedCocoaBeans = ITEMS.register("dried_cocoa_beans", () ->  new Item(new Item.Properties().tab(foods)));
    public static final RegistryObject<Item> roastedCocoaBeans = ITEMS.register("roasted_cocoa_beans",() ->  new Item(new Item.Properties().tab(foods)));
    public static final RegistryObject<Item> cocoaPowder = ITEMS.register("cocoa_powder",() ->  new Item(new Item.Properties().tab(foods)));

    public static final RegistryObject<Item> whiteChocolateBar = ITEMS.register("white_chocolate_bar", () ->  new Item(new Item.Properties().tab(foods).food(new FoodProperties.Builder().nutrition(3).saturationMod(8f).build())));
    public static final RegistryObject<Item> darkChocolateBar = ITEMS.register("dark_chocolate_bar",() ->  new Item(new Item.Properties().tab(foods).food(new FoodProperties.Builder().nutrition(3).saturationMod(8f).build())));
    public static final RegistryObject<Item> milkChocolateBar = ITEMS.register("milk_chocolate_bar",() ->  new Item(new Item.Properties().tab(foods).food(new FoodProperties.Builder().nutrition(3).saturationMod(8f).build())));



    public static final RegistryObject<Drink> expressoCoffee = ITEMS.register("expresso_coffee", () -> new Drink(BlockRegister.expressoMug.get(),3,1));
    public static final RegistryObject<Drink> americanCoffee = ITEMS.register("american_coffee", () -> new Drink(BlockRegister.americanMug.get(),4,2));
    public static final RegistryObject<Drink> latteCoffee = ITEMS.register("latte_coffee",() -> new Drink(BlockRegister.latteMug.get(),5,3));
    public static final RegistryObject<Drink> mochaCoffee = ITEMS.register("mocha_coffee", () -> new Drink(BlockRegister.mochaMug.get(),6,4));

    public static final RegistryObject<Drink> macchiatoCoffee = ITEMS.register("macchiato_coffee", () -> new Drink(BlockRegister.macchiatoMug.get(),3,1));
    public static final RegistryObject<Drink> doppioCoffee = ITEMS.register("doppio_coffee",() -> new Drink(BlockRegister.doppioMug.get(),5,3));
    public static final RegistryObject<Drink> cappuccinoCoffee = ITEMS.register("cappuccino_coffee", () -> new Drink(BlockRegister.cappuccinoMug.get(),6,4));


    public static final RegistryObject<Item> blackStool = ITEMS.register("black_stool",() -> new BlockItem(BlockRegister.blackStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> blueStool = ITEMS.register("blue_stool",() -> new BlockItem(BlockRegister.blueStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> brownStool = ITEMS.register("brown_stool",() -> new BlockItem(BlockRegister.brownStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> cyanStool = ITEMS.register("cyan_stool",() -> new BlockItem(BlockRegister.cyanStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> grayStool = ITEMS.register("gray_stool",() -> new BlockItem(BlockRegister.grayStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> greenStool = ITEMS.register("green_stool",() -> new BlockItem(BlockRegister.greenStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> lightBlueStool = ITEMS.register("light_blue_stool",() -> new BlockItem(BlockRegister.lightBlueStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> lightGrayStool = ITEMS.register("light_gray_stool",() -> new BlockItem(BlockRegister.lightGrayStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> limeStool = ITEMS.register("lime_stool",() -> new BlockItem(BlockRegister.limeStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> magentaStool = ITEMS.register("magenta_stool",() -> new BlockItem(BlockRegister.magentaStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> orangeStool = ITEMS.register("orange_stool",() -> new BlockItem(BlockRegister.orangeStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> pinkStool = ITEMS.register("pink_stool",() -> new BlockItem(BlockRegister.pinkStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> purpleStool = ITEMS.register("purple_stool",() -> new BlockItem(BlockRegister.purpleStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> redStool = ITEMS.register("red_stool",() -> new BlockItem(BlockRegister.redStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> whiteStool = ITEMS.register("white_stool",() -> new BlockItem(BlockRegister.whiteStool.get(), new Item.Properties().tab(decoration)));
    public static final RegistryObject<Item> yellowStool = ITEMS.register("yellow_stool",() -> new BlockItem(BlockRegister.yellowStool.get(), new Item.Properties().tab(decoration)));


    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }
}
