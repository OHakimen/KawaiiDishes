package com.hakimen.kawaiidishes;

import com.hakimen.kawaiidishes.client.screens.BlenderScreen;
import com.hakimen.kawaiidishes.client.screens.CoffeeMachineScreen;
import com.hakimen.kawaiidishes.client.screens.IceCreamScreen;
import com.hakimen.kawaiidishes.config.KawaiiDishesClientConfig;
import com.hakimen.kawaiidishes.config.KawaiiDishesCommonConfig;
import com.hakimen.kawaiidishes.registry.ContainerRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import com.hakimen.kawaiidishes.registry.Registration;
import com.hakimen.kawaiidishes.utils.MaidMobEventHandler;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.Random;



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

        bus.addListener(this::enqueueIMC);
        bus.addListener(this::clientStartup);
        bus.addListener(this::setup);

        MinecraftForge.EVENT_BUS.addListener(this::onLivingSpecialSpawn);
        bus.addListener(this::registerModTab);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void registerModTab(CreativeModeTabEvent.Register event){
        event.registerCreativeModeTab(new ResourceLocation(KawaiiDishes.modId,"cosmetics"),builder -> {
            builder.icon(()->new ItemStack(ItemRegister.dresses.get("black").get()))
                    .title(Component.translatable("itemGroup.kawaiidishes.cosmetics"))
                    .displayItems((flags,out,isOp)->{
                        ItemRegister.ITEMS.getEntries().forEach(x -> {
                            var key = x.get().toString();
                            if(key.contains("maid")){
                                out.accept(x.get());
                            }else if (key.contains("ears") || key.contains("horns")) {
                                out.accept(x.get());
                            }
                            else if (key.contains("tail")) {
                                out.accept(x.get());
                            } else if (key.contains("headband")) {
                                out.accept(x.get());
                            }else if (key.contains("thigh")) {
                                out.accept(x.get());
                            }else if (key.contains("shoes")) {
                                out.accept(x.get());
                            }
                        });
                    }).build();
        });

        event.registerCreativeModeTab(new ResourceLocation(KawaiiDishes.modId,"blocks"),builder -> {
            builder.icon(()->new ItemStack(ItemRegister.coffeeMachine.get()))
                    .title(Component.translatable("itemGroup.kawaiidishes.blocks"))
                    .displayItems((flags,out,isOp)->{
                        out.accept(ItemRegister.mug.get());
                        out.accept(ItemRegister.glassCup.get());
                        out.accept(ItemRegister.milkshakeCup.get());
                        out.accept(ItemRegister.coffeeMachine.get());
                        out.accept(ItemRegister.coffeePress.get());
                        out.accept(ItemRegister.blender.get());
                        out.accept(ItemRegister.iceCreamMachine.get());
                    }).build();
        });

        event.registerCreativeModeTab(new ResourceLocation(KawaiiDishes.modId,"decoration"),builder -> {
            builder.icon(()->new ItemStack(ItemRegister.whiteStool.get()))
                    .title(Component.translatable("itemGroup.kawaiidishes.decoration"))
                    .displayItems((flags,out,isOp)->{
                        ItemRegister.ITEMS.getEntries().forEach(x -> {
                            String item = x.get().toString();
                            if(item.contains("stool")){
                                out.accept(x.get());
                            }

                        });
                    }).build();
        });

        event.registerCreativeModeTab(new ResourceLocation(KawaiiDishes.modId,"food"),builder -> {
            builder.icon(()->new ItemStack(ItemRegister.coffeeFruit.get()))
                    .title(Component.translatable("itemGroup.kawaiidishes.foods"))
                    .displayItems((flags,out,isOp)->{
                        out.accept(ItemRegister.brigadeiroMix.get());
                        out.accept(ItemRegister.condensedMilk.get());
                        out.accept(ItemRegister.creamCheese.get());

                        out.accept(ItemRegister.coffeePowder.get());
                        out.accept(ItemRegister.driedCoffeeBeans.get());
                        out.accept(ItemRegister.roastedCoffeeBeans.get());

                        out.accept(ItemRegister.driedCocoaBeans.get());
                        out.accept(ItemRegister.roastedCocoaBeans.get());
                        ItemRegister.ITEMS.getEntries().forEach(x -> {
                            String item = x.get().toString();
                            if(x.get().isEdible()){
                                out.accept(x.get());
                            }else if(item.contains("cake")){
                                out.accept(x.get());
                            }
                        });
                    }).build();
        });
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
                ItemStack[] stacks = MaidMobEventHandler.armorBuild(RANDOM);

                monster.setItemSlot(EquipmentSlot.HEAD, stacks[0]);
                monster.setItemSlot(EquipmentSlot.CHEST, stacks[1]);
                monster.setItemSlot(EquipmentSlot.LEGS, stacks[2]);
                monster.setItemSlot(EquipmentSlot.FEET, stacks[3]);

                monster.setDropChance(EquipmentSlot.HEAD,KawaiiDishesCommonConfig.chanceToDropArmorSet.get().floatValue());
                monster.setDropChance(EquipmentSlot.CHEST,KawaiiDishesCommonConfig.chanceToDropArmorSet.get().floatValue());
                monster.setDropChance(EquipmentSlot.LEGS,KawaiiDishesCommonConfig.chanceToDropArmorSet.get().floatValue());
                monster.setDropChance(EquipmentSlot.FEET,KawaiiDishesCommonConfig.chanceToDropArmorSet.get().floatValue());

            }
        }
    }

    private void setup(final FMLCommonSetupEvent event) {
        ComposterBlock.COMPOSTABLES.put(ItemRegister.coffeeFruit.get(),0.25f);
        ComposterBlock.COMPOSTABLES.put(ItemRegister.driedCoffeeBeans.get(),0.50f);
        ComposterBlock.COMPOSTABLES.put(ItemRegister.roastedCoffeeBeans.get(),0.75f);

        ComposterBlock.COMPOSTABLES.put(ItemRegister.driedCocoaBeans.get(),0.50f);
        ComposterBlock.COMPOSTABLES.put(ItemRegister.roastedCocoaBeans.get(),0.75f);

        ComposterBlock.COMPOSTABLES.put(ItemRegister.cakePiece.get(),0.65f);
        ComposterBlock.COMPOSTABLES.put(ItemRegister.honeyCheeseCakePiece.get(),0.65f);
        ComposterBlock.COMPOSTABLES.put(ItemRegister.chocolateCheeseCakePiece.get(),0.65f);
        ComposterBlock.COMPOSTABLES.put(ItemRegister.cheeseCakePiece.get(),0.65f);

        ComposterBlock.COMPOSTABLES.put(ItemRegister.honeyCheeseCake.get(),1f);
        ComposterBlock.COMPOSTABLES.put(ItemRegister.chocolateCheeseCake.get(),1f);
        ComposterBlock.COMPOSTABLES.put(ItemRegister.cheeseCake.get(),1f);
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
    }
}
