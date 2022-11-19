package com.hakimen.kawaiidishes;

import com.hakimen.kawaiidishes.client.screens.CoffeeMachineScreen;
import com.hakimen.kawaiidishes.client.screens.IceCreamScreen;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import com.hakimen.kawaiidishes.registry.ContainerRegister;
import com.hakimen.kawaiidishes.registry.Registration;
import com.hakimen.kawaiidishes.utils.MaidMobEventHandler;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import static com.hakimen.kawaiidishes.utils.MaidMobEventHandler.armorBuild;


@Mod("kawaiidishes")
public class KawaiiDishes {

    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String modId = "kawaiidishes";

    public KawaiiDishes() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        Registration.init();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        forgeBus.addListener(this::onLivingSpecialSpawn);
        bus.addListener(this::clientStartup);
    }

    public void onLivingSpecialSpawn(LivingSpawnEvent.SpecialSpawn event) {
        Entity entity = event.getEntity();
        if (entity instanceof Monster monster && !monster.serializeNBT().getBoolean("isBaby") && event.getWorld().getRandom().nextFloat(0,1) < 0.075f) {
            ItemStack[] stacks = armorBuild(event.getWorld().getRandom());

            monster.setItemSlot(EquipmentSlot.HEAD, stacks[0]);
            monster.setItemSlot(EquipmentSlot.CHEST, stacks[1]);
            monster.setItemSlot(EquipmentSlot.LEGS, stacks[2]);
            monster.setItemSlot(EquipmentSlot.FEET, stacks[3]);

            monster.setDropChance(EquipmentSlot.HEAD,0.25f);
            monster.setDropChance(EquipmentSlot.CHEST,0.25f);
            monster.setDropChance(EquipmentSlot.LEGS,0.25f);
            monster.setDropChance(EquipmentSlot.FEET,0.25f);

        }
    }

    private void setup(final FMLCommonSetupEvent event) {
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
        });

        for(var block: BlockRegister.BLOCKS.getEntries().stream().toList()){
            if(block.get().getRegistryName().getPath().contains("_stool")){
                ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout());
            }else if(block.get().getRegistryName().getPath().contains("_ice_cream")){
                ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout());
            }
        }

        ItemBlockRenderTypes.setRenderLayer(BlockRegister.iceCreamMachine.get(), RenderType.cutout());


        ItemBlockRenderTypes.setRenderLayer(BlockRegister.glassCup.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegister.coffeePlant.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegister.coffeePress.get(), RenderType.cutout());

    }
}
