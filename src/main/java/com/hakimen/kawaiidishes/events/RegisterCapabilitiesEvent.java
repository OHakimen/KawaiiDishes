package com.hakimen.kawaiidishes.events;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.Capabilities;

import java.util.List;

@Mod.EventBusSubscriber(modid = KawaiiDishes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterCapabilitiesEvent {

    @SubscribeEvent
    public static void linkCapsToResources(net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent event){
         event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, BlockEntityRegister.BLENDER.get(),(object, context) -> object.getInventory());

         event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, BlockEntityRegister.COFFEE_MACHINE.get(),(object, context) -> object.getInventory());
         event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, BlockEntityRegister.COFFEE_MACHINE.get(),(object, context) -> object.getWaterTank());

         event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, BlockEntityRegister.INCENSE.get(),(object, context) -> object.getInventory());
         event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, BlockEntityRegister.ICE_CREAM_MAKER.get(),(object, context) -> object.getInventory());
         event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, BlockEntityRegister.DISPLAY_CASE.get(),(object, context) -> object.getInventory());

    }

}
