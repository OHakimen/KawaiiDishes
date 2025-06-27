package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.block.*;
import com.hakimen.kawaiidishes.custom.Recorder;
import java.util.Properties;
import java.util.function.Supplier;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.sound.BlockSoundGroup;

public class BlockRegister {

    private static final AbstractBlock.Settings coffeeProps = AbstractBlock.Settings.create()
            .strength(1f)
            .hardness(1f)
            .nonOpaque()
            .sounds(BlockSoundGroup.STONE);
    static Recorder<Block> BLOCKS = new Recorder<>(Registries.BLOCK, KawaiiDishes.MODID);
    public static final Supplier<CoffeeMugBlock> MUG = BLOCKS.register("mug", () -> new CoffeeMugBlock(coffeeProps));

    public static final Supplier<CoffeeBushBlock> COFFEE_BUSH = BLOCKS.register("coffee_bush", () -> new CoffeeBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)
            .ticksRandomly()
            .sounds(BlockSoundGroup.SWEET_BERRY_BUSH)
            .noCollision()
    ));

    //Coffees
    public static final Supplier<CoffeeMugBlock> ESPRESSO_COFFEE = BLOCKS.register("espresso_coffee", () -> new CoffeeMugBlock(coffeeProps));
    public static final Supplier<CoffeeMugBlock> DOPPIO_COFFEE = BLOCKS.register("doppio_coffee", () -> new CoffeeMugBlock(coffeeProps));
    public static final Supplier<CoffeeMugBlock> MACCHIATO_COFFEE = BLOCKS.register("macchiato_coffee", () -> new CoffeeMugBlock(coffeeProps));
    public static final Supplier<CoffeeMugBlock> DARK_COFFEE = BLOCKS.register("dark_coffee", () -> new CoffeeMugBlock(coffeeProps));
    public static final Supplier<CoffeeMugBlock> LATTE_COFFEE = BLOCKS.register("latte_coffee", () -> new CoffeeMugBlock(coffeeProps));
    public static final Supplier<CoffeeMugBlock> CAPUCCINO_COFFEE = BLOCKS.register("capuccino_coffee", () -> new CoffeeMugBlock(coffeeProps));
    public static final Supplier<CoffeeMugBlock> MOCHA_COFFEE = BLOCKS.register("mocha_coffee", () -> new CoffeeMugBlock(coffeeProps));
    public static final Supplier<CoffeeMugBlock> HOT_COCOA = BLOCKS.register("hot_cocoa", () -> new CoffeeMugBlock(coffeeProps));

    //Cakes
    public static final Supplier<CakeBlock> CHEESE_CAKE = BLOCKS.register("cheese_cake", () -> new CakeBlock(AbstractBlock.Settings.copy(Blocks.CAKE)));
    public static final Supplier<CakeBlock> CHOCOLATE_CHEESE_CAKE = BLOCKS.register("chocolate_cheese_cake", () -> new CakeBlock(AbstractBlock.Settings.copy(Blocks.CAKE)));
    public static final Supplier<CakeBlock> HONEY_CHEESE_CAKE = BLOCKS.register("honey_cheese_cake", () -> new CakeBlock(AbstractBlock.Settings.copy(Blocks.CAKE)));

    //Pie
    public static final Supplier<CakeBlock> APPLE_PIE = BLOCKS.register("apple_pie", () -> new CakeBlock(AbstractBlock.Settings.copy(Blocks.CAKE)));
    public static final Supplier<CakeBlock> SWEET_BERRY_PIE = BLOCKS.register("sweet_berry_pie", () -> new CakeBlock(AbstractBlock.Settings.copy(Blocks.CAKE)));
    public static final Supplier<CakeBlock> GLOW_BERRY_PIE = BLOCKS.register("glow_berry_pie", () -> new CakeBlock(AbstractBlock.Settings.copy(Blocks.CAKE)));
    public static final Supplier<CakeBlock> CHERRY_PIE = BLOCKS.register("cherry_pie", () -> new CakeBlock(AbstractBlock.Settings.copy(Blocks.CAKE)));

    //Machinery
    public static final Supplier<CoffeeMachineBlock> COFFEE_MACHINE = BLOCKS.register("coffee_machine", CoffeeMachineBlock::new);
    public static final Supplier<IceCreamMakerBlock> ICE_CREAM_MAKER = BLOCKS.register("ice_cream_maker", IceCreamMakerBlock::new);
    public static final Supplier<BlenderBlock> BLENDER = BLOCKS.register("blender", BlenderBlock::new);


    // Decorative
    public static final Supplier<SeatBlock> SEAT = BLOCKS.register("seat", () -> new SeatBlock(
            AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)
                    .nonOpaque()
                    .suffocates((state, level, pos) -> false)
    ));

    public static final Supplier<Block> KITCHEN_TILES = BLOCKS.register("kitchen_tiles", () -> new Block(AbstractBlock.Settings.copy(Blocks.STONE)));

    public static final Supplier<IncenseBlock> INCENSE_GLASS = BLOCKS.register("incense_glass", IncenseBlock::new);
    public static final Supplier<DisplayCaseBlock> DISPLAY_CASE = BLOCKS.register("display_case", DisplayCaseBlock::new);

    public static void register() {
        //This does nothing but bootstrap the class
    }
}
