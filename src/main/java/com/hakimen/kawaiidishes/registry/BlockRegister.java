package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.block.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockRegister {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, KawaiiDishes.MODID);
    private static final BlockBehaviour.Properties coffeeProps =  BlockBehaviour.Properties.of()
            .strength(1f)
            .destroyTime(1f)
            .noOcclusion()
            .sound(SoundType.STONE);
    public static final DeferredHolder<Block, CoffeeBushBlock> COFFEE_BUSH = BLOCKS.register("coffee_bush",() -> new CoffeeBushBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)
                    .randomTicks()
                    .sound(SoundType.SWEET_BERRY_BUSH)
                    .noCollission()
                    ));

    // Decorative
    public static final DeferredHolder<Block, SeatBlock> SEAT = BLOCKS.register("seat", () -> new SeatBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .noOcclusion()
                    .isSuffocating((state, level, pos) -> false)
    ));

    public static final DeferredHolder<Block, Block> KITCHEN_TILES = BLOCKS.register("kitchen_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredHolder<Block, DisplayCaseBlock> DISPLAY_CASE = BLOCKS.register("display_case", DisplayCaseBlock::new);


    public static final DeferredHolder<Block, CoffeeMachineBlock> COFFEE_MACHINE = BLOCKS.register("coffee_machine", CoffeeMachineBlock::new);

    public static final DeferredHolder<Block, BlenderBlock> BLENDER = BLOCKS.register("blender", BlenderBlock::new);
    public static final DeferredHolder<Block, IceCreamMakerBlock> ICE_CREAM_MAKER = BLOCKS.register("ice_cream_maker", IceCreamMakerBlock::new);

    public static final DeferredHolder<Block, CoffeeMugBlock> MUG = BLOCKS.register("mug", () -> new CoffeeMugBlock(coffeeProps));

    //Coffees
    public static final DeferredHolder<Block, CoffeeMugBlock> ESPRESSO_COFFEE = BLOCKS.register("espresso_coffee", () -> new CoffeeMugBlock(coffeeProps));

    public static final DeferredHolder<Block, CoffeeMugBlock> DOPPIO_COFFEE = BLOCKS.register("doppio_coffee", () -> new CoffeeMugBlock(coffeeProps));

    public static final DeferredHolder<Block, CoffeeMugBlock> MACCHIATO_COFFEE = BLOCKS.register("macchiato_coffee", () -> new CoffeeMugBlock(coffeeProps));

    public static final DeferredHolder<Block, CoffeeMugBlock> DARK_COFFEE = BLOCKS.register("dark_coffee", () -> new CoffeeMugBlock(coffeeProps));
    public static final DeferredHolder<Block, CoffeeMugBlock> LATTE_COFFEE = BLOCKS.register("latte_coffee", () -> new CoffeeMugBlock(coffeeProps));
    public static final DeferredHolder<Block, CoffeeMugBlock> CAPUCCINO_COFFEE = BLOCKS.register("capuccino_coffee", () -> new CoffeeMugBlock(coffeeProps));
    public static final DeferredHolder<Block, CoffeeMugBlock> MOCHA_COFFEE = BLOCKS.register("mocha_coffee", () -> new CoffeeMugBlock(coffeeProps));
    public static final DeferredHolder<Block, CoffeeMugBlock> HOT_COCOA = BLOCKS.register("hot_cocoa", () -> new CoffeeMugBlock(coffeeProps));

    //Cakes

    public static final DeferredHolder<Block, CakeBlock> CHEESE_CAKE = BLOCKS.register("cheese_cake", ()-> new CakeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
    public static final DeferredHolder<Block, CakeBlock> CHOCOLATE_CHEESE_CAKE = BLOCKS.register("chocolate_cheese_cake", ()-> new CakeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
    public static final DeferredHolder<Block, CakeBlock> HONEY_CHEESE_CAKE = BLOCKS.register("honey_cheese_cake", ()-> new CakeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));

    //Pie
    public static final DeferredHolder<Block, CakeBlock> APPLE_PIE = BLOCKS.register("apple_pie", ()-> new CakeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
    public static final DeferredHolder<Block, CakeBlock> SWEET_BERRY_PIE = BLOCKS.register("sweet_berry_pie", ()-> new CakeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
    public static final DeferredHolder<Block, CakeBlock> GLOW_BERRY_PIE = BLOCKS.register("glow_berry_pie", ()-> new CakeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
    public static final DeferredHolder<Block, CakeBlock> CHERRY_PIE = BLOCKS.register("cherry_pie", ()-> new CakeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));

    public static final DeferredHolder<Block, IncenseBlock> INCENSE_GLASS = BLOCKS.register("incense_glass", IncenseBlock::new);
    public static void register(IEventBus bus){
        BLOCKS.register(bus);
    }
}
