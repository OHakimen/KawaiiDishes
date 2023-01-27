package com.hakimen.kawaiidishes;

import com.hakimen.kawaiidishes.client.screens.BlenderScreen;
import com.hakimen.kawaiidishes.client.screens.CoffeeMachineScreen;
import com.hakimen.kawaiidishes.client.screens.IceCreamScreen;
import com.hakimen.kawaiidishes.config.KawaiiDishesClientConfig;
import com.hakimen.kawaiidishes.config.KawaiiDishesCommonConfig;
import com.hakimen.kawaiidishes.registry.*;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.SquidRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.Random;

import static com.hakimen.kawaiidishes.utils.MaidMobEventHandler.armorBuild;


@Mod("kawaiidishes")
public class KawaiiDishes {

    // Directly reference a slf4j logger
    public static final Random RANDOM = new Random();
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String modId = "kawaiidishes";

    public KawaiiDishes() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, KawaiiDishesClientConfig.clientSpec, "kawaii-dishes-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, KawaiiDishesCommonConfig.commonSpec, "kawaii-dishes-common.toml");

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        Registration.init();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        forgeBus.addListener(this::onLivingSpecialSpawn);
        bus.addListener(this::enqueueIMC);
        bus.addListener(this::clientStartup);
        bus.addListener(this::setup);
    }

    public void onLivingSpecialSpawn(LivingSpawnEvent.SpecialSpawn event) {
        Entity entity = event.getEntity();
        if (entity instanceof Monster monster && !entity.serializeNBT().getBoolean("isBaby") && RANDOM.nextFloat(0,1) < KawaiiDishesCommonConfig.chanceToSpawnWithDress.get()) {
            if((monster instanceof Skeleton
                    || monster instanceof WitherSkeleton
                    || monster instanceof Stray
                    || monster instanceof Zombie
                    || monster instanceof Piglin
                    || monster instanceof PiglinBrute) && KawaiiDishesCommonConfig.shouldMobSpawnWithDress.get()){
                ItemStack[] stacks = armorBuild(RANDOM);

                monster.setItemSlot(EquipmentSlot.HEAD, stacks[0]);
                monster.setItemSlot(EquipmentSlot.CHEST, stacks[1]);
                monster.setItemSlot(EquipmentSlot.LEGS, stacks[2]);
                monster.setItemSlot(EquipmentSlot.FEET, stacks[3]);

                monster.setDropChance(EquipmentSlot.HEAD,KawaiiDishesCommonConfig.changeToDropArmorSet.get().floatValue());
                monster.setDropChance(EquipmentSlot.CHEST,KawaiiDishesCommonConfig.changeToDropArmorSet.get().floatValue());
                monster.setDropChance(EquipmentSlot.LEGS,KawaiiDishesCommonConfig.changeToDropArmorSet.get().floatValue());
                monster.setDropChance(EquipmentSlot.FEET,KawaiiDishesCommonConfig.changeToDropArmorSet.get().floatValue());

            }
        }
    }

    private void setup(final FMLCommonSetupEvent event) {
        ComposterBlock.COMPOSTABLES.put(ItemRegister.coffeeFruit.get(),0.25f);
        ComposterBlock.COMPOSTABLES.put(ItemRegister.driedCoffeeBeans.get(),0.50f);
        ComposterBlock.COMPOSTABLES.put(ItemRegister.roastedCoffeeBeans.get(),0.75f);

        ComposterBlock.COMPOSTABLES.put(ItemRegister.driedCocoaBeans.get(),0.50f);
        ComposterBlock.COMPOSTABLES.put(ItemRegister.roastedCocoaBeans.get(),0.75f);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
    }

    private void processIMC(final InterModProcessEvent event) {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }
    private void clientStartup(final FMLClientSetupEvent event){

        event.enqueueWork(() -> {
            MenuScreens.register(ContainerRegister.coffeeMachine.get(), CoffeeMachineScreen::new);
            MenuScreens.register(ContainerRegister.iceCreamMachine.get(), IceCreamScreen::new);
            MenuScreens.register(ContainerRegister.blenderContainer.get(), BlenderScreen::new);
        });


        for(var block: BlockRegister.BLOCKS.getEntries().stream().toList()){
            if(block.get().getDescriptionId().contains("_stool")){
                ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout());
            }else if(block.get().getDescriptionId().contains("_ice_cream")){
                ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout());
            }else if(block.get().getDescriptionId().contains("_milkshake")){
                ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.translucent());
            }
        }

        ItemBlockRenderTypes.setRenderLayer(BlockRegister.iceCreamMachine.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(BlockRegister.cheeseCake.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegister.chocolateCheeseCake.get(), RenderType.cutout());



        ItemBlockRenderTypes.setRenderLayer(BlockRegister.glassCup.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegister.coffeePlant.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegister.coffeePress.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegister.blender.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BlockRegister.milkshakeCup.get(), RenderType.translucent());

    }
}
