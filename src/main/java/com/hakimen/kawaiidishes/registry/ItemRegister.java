package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.blocks.MugBlock;
import com.hakimen.kawaiidishes.items.CatEars;
import com.hakimen.kawaiidishes.items.Hat;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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

    public static final RegistryObject<Item> coffeeFruit = ITEMS.register("coffee_fruit", () -> new Item(new Item.Properties().tab(foods).food(new FoodProperties.Builder().fast().nutrition(4).saturationMod(2).build())));
    public static final RegistryObject<Item> driedCoffeeBeans = ITEMS.register("dried_coffee_beans", () ->  new Item(new Item.Properties().tab(foods)));
    public static final RegistryObject<Item> roastedCoffeeBeans = ITEMS.register("roasted_coffee_beans",() ->  new Item(new Item.Properties().tab(foods)));


    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }
}
