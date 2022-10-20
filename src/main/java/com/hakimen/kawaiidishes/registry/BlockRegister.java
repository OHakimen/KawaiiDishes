package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.blocks.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.hakimen.kawaiidishes.KawaiiDishes.modId;

public class BlockRegister {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,modId);

    public static final RegistryObject<MugBlock> mug = BLOCKS.register("mug",MugBlock::new);

    public static final RegistryObject<MugWithCoffee> expressoMug = BLOCKS.register("expresso_coffee",MugWithCoffee::new);
    public static final RegistryObject<MugWithCoffee> americanMug = BLOCKS.register("american_coffee",MugWithCoffee::new);
    public static final RegistryObject<MugWithCoffee> latteMug = BLOCKS.register("latte_coffee",MugWithCoffee::new);
    public static final RegistryObject<MugWithCoffee> mochaMug = BLOCKS.register("mocha_coffee",MugWithCoffee::new);
    public static final RegistryObject<MugWithCoffee> cafeAuLaitMug = BLOCKS.register("cafe_au_lait_coffee",MugWithCoffee::new);
    public static final RegistryObject<MugWithCoffee> cappuccinoMug = BLOCKS.register("cappuccino_coffee",MugWithCoffee::new);
    public static final RegistryObject<MugWithCoffee> doppioMug = BLOCKS.register("doppio_coffee",MugWithCoffee::new);
    public static final RegistryObject<MugWithCoffee> macchiatoMug = BLOCKS.register("macchiato_coffee",MugWithCoffee::new);

    public static final RegistryObject<StoolBlock> blackStool = BLOCKS.register("black_stool",StoolBlock::new);
    public static final RegistryObject<StoolBlock> blueStool = BLOCKS.register("blue_stool",StoolBlock::new);
    public static final RegistryObject<StoolBlock> brownStool = BLOCKS.register("brown_stool",StoolBlock::new);
    public static final RegistryObject<StoolBlock> cyanStool = BLOCKS.register("cyan_stool",StoolBlock::new);
    public static final RegistryObject<StoolBlock> grayStool = BLOCKS.register("gray_stool",StoolBlock::new);
    public static final RegistryObject<StoolBlock> greenStool = BLOCKS.register("green_stool",StoolBlock::new);
    public static final RegistryObject<StoolBlock> lightBlueStool = BLOCKS.register("light_blue_stool",StoolBlock::new);
    public static final RegistryObject<StoolBlock> lightGrayStool = BLOCKS.register("light_gray_stool",StoolBlock::new);
    public static final RegistryObject<StoolBlock> limeStool = BLOCKS.register("lime_stool",StoolBlock::new);
    public static final RegistryObject<StoolBlock> magentaStool = BLOCKS.register("magenta_stool",StoolBlock::new);
    public static final RegistryObject<StoolBlock> orangeStool = BLOCKS.register("orange_stool",StoolBlock::new);
    public static final RegistryObject<StoolBlock> pinkStool = BLOCKS.register("pink_stool",StoolBlock::new);
    public static final RegistryObject<StoolBlock> purpleStool = BLOCKS.register("purple_stool",StoolBlock::new);
    public static final RegistryObject<StoolBlock> redStool = BLOCKS.register("red_stool",StoolBlock::new);
    public static final RegistryObject<StoolBlock> whiteStool = BLOCKS.register("white_stool",StoolBlock::new);
    public static final RegistryObject<StoolBlock> yellowStool = BLOCKS.register("yellow_stool",StoolBlock::new);

    public static final RegistryObject<CoffeePress> coffeePress = BLOCKS.register("coffee_press",CoffeePress::new);

    public static final RegistryObject<CoffeeMachine> coffeeMachine = BLOCKS.register("coffee_machine",CoffeeMachine::new);


    public static final RegistryObject<CoffeePlant> coffeePlant = BLOCKS.register("coffee_bush",CoffeePlant::new);

    public static void register(IEventBus bus){
        BLOCKS.register(bus);
    }
}
