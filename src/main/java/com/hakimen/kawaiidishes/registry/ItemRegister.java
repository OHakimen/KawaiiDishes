package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.blocks.MugBlock;
import com.hakimen.kawaiidishes.items.CatEars;
import com.hakimen.kawaiidishes.items.Drink;
import com.hakimen.kawaiidishes.items.Hat;
import com.hakimen.kawaiidishes.items.armor.CatMaidArmorItem;
import com.hakimen.kawaiidishes.items.armor.CatTailArmorItem;
import com.hakimen.kawaiidishes.items.armor.MaidDressArmorItem;
import com.hakimen.kawaiidishes.items.armor.ThighHighsArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
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

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,modId);

    public static final RegistryObject<Item> mug = ITEMS.register("mug", () -> new BlockItem(BlockRegister.mug.get(), new Item.Properties().tab(blocks)));

    public static final RegistryObject<CatEars> blackCatEars = ITEMS.register("black_cat_ears", CatEars::new);
    public static final RegistryObject<CatEars> caramelCatEars = ITEMS.register("caramel_cat_ears", CatEars::new);
    public static final RegistryObject<CatEars> whiteCatEars = ITEMS.register("white_cat_ears", CatEars::new);

    public static final RegistryObject<Item> blackCatTail = ITEMS.register("black_cat_tail",
            () -> new CatTailArmorItem("black_cat_tail.png"));
    public static final RegistryObject<Item> caramelCatTail = ITEMS.register("caramel_cat_tail",
            () -> new CatTailArmorItem("caramel_cat_tail.png"));
    public static final RegistryObject<Item> whiteCatTail = ITEMS.register("white_cat_tail",
            () -> new CatTailArmorItem("white_cat_tail.png"));

    public static final RegistryObject<Item> blackMaidDress = ITEMS.register("black_maid_dress",
            () -> new MaidDressArmorItem("black_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> blackMaidDressCatTailBlack = ITEMS.register("black_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("black_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> blackMaidDressCatTailCaramel = ITEMS.register("black_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("black_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> blackMaidDressCatTailWhite = ITEMS.register("black_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("black_maid_dress_cat_tail_white.png"));

    public static final RegistryObject<Item> blueMaidDress = ITEMS.register("blue_maid_dress",
            () -> new MaidDressArmorItem("blue_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> blueMaidDressCatTailBlack = ITEMS.register("blue_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("blue_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> blueMaidDressCatTailCaramel = ITEMS.register("blue_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("blue_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> blueMaidDressCatTailWhite = ITEMS.register("blue_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("blue_maid_dress_cat_tail_white.png"));

    public static final RegistryObject<Item> brownMaidDress = ITEMS.register("brown_maid_dress",
            () -> new MaidDressArmorItem("brown_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> brownMaidDressCatTailBlack = ITEMS.register("brown_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("brown_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> brownMaidDressCatTailCaramel = ITEMS.register("brown_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("brown_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> brownMaidDressCatTailWhite = ITEMS.register("brown_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("brown_maid_dress_cat_tail_white.png"));

    public static final RegistryObject<Item> cyanMaidDress = ITEMS.register("cyan_maid_dress",
            () -> new MaidDressArmorItem("cyan_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> cyanMaidDressCatTailBlack = ITEMS.register("cyan_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("cyan_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> cyanMaidDressCatTailCaramel = ITEMS.register("cyan_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("cyan_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> cyanMaidDressCatTailWhite = ITEMS.register("cyan_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("cyan_maid_dress_cat_tail_white.png"));

    public static final RegistryObject<Item> grayMaidDress = ITEMS.register("gray_maid_dress",
            () -> new MaidDressArmorItem("gray_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> grayMaidDressCatTailBlack = ITEMS.register("gray_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("gray_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> grayMaidDressCatTailCaramel = ITEMS.register("gray_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("gray_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> grayMaidDressCatTailWhite = ITEMS.register("gray_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("gray_maid_dress_cat_tail_white.png"));

    public static final RegistryObject<Item> greenMaidDress = ITEMS.register("green_maid_dress",
            () -> new MaidDressArmorItem("green_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> greenMaidDressCatTailBlack = ITEMS.register("green_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("green_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> greenMaidDressCatTailCaramel = ITEMS.register("green_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("green_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> greenMaidDressCatTailWhite = ITEMS.register("green_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("green_maid_dress_cat_tail_white.png"));

    public static final RegistryObject<Item> light_blueMaidDress = ITEMS.register("light_blue_maid_dress",
            () -> new MaidDressArmorItem("light_blue_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> light_blueMaidDressCatTailBlack = ITEMS.register("light_blue_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("light_blue_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> light_blueMaidDressCatTailCaramel = ITEMS.register("light_blue_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("light_blue_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> light_blueMaidDressCatTailWhite = ITEMS.register("light_blue_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("light_blue_maid_dress_cat_tail_white.png"));

    public static final RegistryObject<Item> light_grayMaidDress = ITEMS.register("light_gray_maid_dress",
            () -> new MaidDressArmorItem("light_gray_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> light_grayMaidDressCatTailBlack = ITEMS.register("light_gray_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("light_gray_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> light_grayMaidDressCatTailCaramel = ITEMS.register("light_gray_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("light_gray_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> light_grayMaidDressCatTailWhite = ITEMS.register("light_gray_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("light_gray_maid_dress_cat_tail_white.png"));

    public static final RegistryObject<Item> limeMaidDress = ITEMS.register("lime_maid_dress",
            () -> new MaidDressArmorItem("lime_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> limeMaidDressCatTailBlack = ITEMS.register("lime_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("lime_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> limeMaidDressCatTailCaramel = ITEMS.register("lime_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("lime_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> limeMaidDressCatTailWhite = ITEMS.register("lime_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("lime_maid_dress_cat_tail_white.png"));

    public static final RegistryObject<Item> magentaMaidDress = ITEMS.register("magenta_maid_dress",
            () -> new MaidDressArmorItem("magenta_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> magentaMaidDressCatTailBlack = ITEMS.register("magenta_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("magenta_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> magentaMaidDressCatTailCaramel = ITEMS.register("magenta_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("magenta_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> magentaMaidDressCatTailWhite = ITEMS.register("magenta_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("magenta_maid_dress_cat_tail_white.png"));

    public static final RegistryObject<Item> orangeMaidDress = ITEMS.register("orange_maid_dress",
            () -> new MaidDressArmorItem("orange_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> orangeMaidDressCatTailBlack = ITEMS.register("orange_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("orange_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> orangeMaidDressCatTailCaramel = ITEMS.register("orange_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("orange_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> orangeMaidDressCatTailWhite = ITEMS.register("orange_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("orange_maid_dress_cat_tail_white.png"));

    public static final RegistryObject<Item> pinkMaidDress = ITEMS.register("pink_maid_dress",
            () -> new MaidDressArmorItem("pink_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> pinkMaidDressCatTailBlack = ITEMS.register("pink_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("pink_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> pinkMaidDressCatTailCaramel = ITEMS.register("pink_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("pink_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> pinkMaidDressCatTailWhite = ITEMS.register("pink_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("pink_maid_dress_cat_tail_white.png"));

    public static final RegistryObject<Item> redMaidDress = ITEMS.register("red_maid_dress",
            () -> new MaidDressArmorItem("red_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> redMaidDressCatTailBlack = ITEMS.register("red_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("red_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> redMaidDressCatTailCaramel = ITEMS.register("red_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("red_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> redMaidDressCatTailWhite = ITEMS.register("red_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("red_maid_dress_cat_tail_white.png"));

    public static final RegistryObject<Item> whiteMaidDress = ITEMS.register("white_maid_dress",
            () -> new MaidDressArmorItem("white_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> whiteMaidDressCatTailBlack = ITEMS.register("white_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("white_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> whiteMaidDressCatTailCaramel = ITEMS.register("white_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("white_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> whiteMaidDressCatTailWhite = ITEMS.register("white_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("white_maid_dress_cat_tail_white.png"));

    public static final RegistryObject<Item> yellowMaidDress = ITEMS.register("yellow_maid_dress",
            () -> new MaidDressArmorItem("yellow_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> yellowMaidDressCatTailBlack = ITEMS.register("yellow_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("yellow_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> yellowMaidDressCatTailCaramel = ITEMS.register("yellow_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("yellow_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> yellowMaidDressCatTailWhite = ITEMS.register("yellow_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("yellow_maid_dress_cat_tail_white.png"));

    public static final RegistryObject<Item> purpleMaidDress = ITEMS.register("purple_maid_dress",
            () -> new MaidDressArmorItem("purple_maid_dress_cat_tail_black.png"));

    public static final RegistryObject<Item> purpleMaidDressCatTailBlack = ITEMS.register("purple_maid_dress_cat_tail_black",
            () -> new CatMaidArmorItem("purple_maid_dress_cat_tail_black.png"));
    public static final RegistryObject<Item> purpleMaidDressCatTailCaramel = ITEMS.register("purple_maid_dress_cat_tail_caramel",
            () -> new CatMaidArmorItem("purple_maid_dress_cat_tail_caramel.png"));
    public static final RegistryObject<Item> purpleMaidDressCatTailWhite = ITEMS.register("purple_maid_dress_cat_tail_white",
            () -> new CatMaidArmorItem("purple_maid_dress_cat_tail_white.png"));
    public static final RegistryObject<Item> whiteThighHighs = ITEMS.register("white_thigh_highs",
            () -> new ThighHighsArmorItem("white_thigh_highs.png", EquipmentSlot.LEGS));
    public static final RegistryObject<Item> blackThighHighs = ITEMS.register("black_thigh_highs",
            () -> new ThighHighsArmorItem("black_thigh_highs.png",EquipmentSlot.LEGS));

    public static final RegistryObject<Item> whiteThighHighsShoes = ITEMS.register("brown_shoes",
            () -> new ThighHighsArmorItem("white_thigh_highs.png", EquipmentSlot.FEET));
    public static final RegistryObject<Item> blackThighHighsShoes = ITEMS.register("dark_brown_shoes",
            () -> new ThighHighsArmorItem("black_thigh_highs.png",EquipmentSlot.FEET));

    public static final RegistryObject<Item> coffeeFruit = ITEMS.register("coffee_fruit", () -> new BlockItem(
            BlockRegister.coffeePlant.get(),new Item.Properties().tab(foods).food(new FoodProperties.Builder().fast().nutrition(2).saturationMod(2).build()
    )));
    public static final RegistryObject<Item> driedCoffeeBeans = ITEMS.register("dried_coffee_beans", () ->  new Item(new Item.Properties().tab(foods)));
    public static final RegistryObject<Item> roastedCoffeeBeans = ITEMS.register("roasted_coffee_beans",() ->  new Item(new Item.Properties().tab(foods)));

    public static final RegistryObject<Drink> expressoCoffee = ITEMS.register("expresso_coffee", () -> new Drink(BlockRegister.expressoMug.get(),3,1));
    public static final RegistryObject<Drink> americanCoffee = ITEMS.register("american_coffee", () -> new Drink(BlockRegister.americanMug.get(),4,2));
    public static final RegistryObject<Drink> latteCoffee = ITEMS.register("latte_coffee",() -> new Drink(BlockRegister.latteMug.get(),5,3));
    public static final RegistryObject<Drink> mochaCoffee = ITEMS.register("mocha_coffee", () -> new Drink(BlockRegister.mochaMug.get(),6,4));

    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }
}
