package com.hakimen.kawaiidishes.events;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.config.ServerConfig;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@Mod.EventBusSubscriber(modid = KawaiiDishes.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
@ParametersAreNonnullByDefault
public class AddTradesEvent {

    @SubscribeEvent
    public static void villagerTrades(VillagerTradesEvent event){
        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
        VillagerProfession profession = event.getType();
        if(ServerConfig.shouldAddVillagerTrades.get()) {
            if (profession.toString().equals("farmer")) {
                trades.get(1).add(buyTrade(ItemRegister.COFFEE_BERRIES.get(), 22, 16, 5));
                trades.get(3).add(sellTrade(2, ItemRegister.HONEY_CHEESE_CAKE_SLICE.get(), 4, 16, 5));
                trades.get(3).add(sellTrade(2, ItemRegister.CHOCOLATE_CHEESE_CAKE_SLICE.get(), 4, 16, 5));
                trades.get(3).add(sellTrade(2, ItemRegister.CHEESE_CAKE_SLICE.get(), 4, 16, 5));
                trades.get(4).add(sellTrade(4, ItemRegister.WAFFLE.get(), 1, 16, 5));
                trades.get(4).add(sellTrade(4, ItemRegister.CHOCOLATE_WAFFLE.get(), 1, 16, 5));
                trades.get(4).add(sellTrade(4, ItemRegister.CHEESE_CAKE.get(), 1, 16, 5));
            }
        }
    }
    @SubscribeEvent
    public static void wandererTrades(WandererTradesEvent event) {
        if(!ServerConfig.shouldAddVillagerTrades.get()) return;
        List<VillagerTrades.ItemListing> trades = event.getGenericTrades();
        trades.add(sellTrade(ItemRegister.COFFEE_BERRIES.get(), 4,1, 12));
    }

    public static BasicItemListing buyTrade(ItemLike item, int count, int maxTrades, int xp) {
        return new BasicItemListing(new ItemStack(item, count), new ItemStack(Items.EMERALD), maxTrades, xp, 0.05F);
    }

    public static BasicItemListing conversionTrade(int cost,ItemLike item, ItemLike result, int count, int maxTrades, int xp){
        return new BasicItemListing(new ItemStack(Items.EMERALD,cost),new ItemStack(item,count), new ItemStack(result,count), maxTrades, xp, 0.05f);
    }

    public static BasicItemListing coffeeTrade(int cost,ItemLike item, ItemStack stack, int count, int maxTrades, int xp){
        var temp = stack.copy();
        temp.setCount(count);
        return new BasicItemListing(new ItemStack(Items.EMERALD,cost),new ItemStack(item,count), temp, maxTrades, xp, 0.05f);
    }

    public static BasicItemListing twoPartTrade(ItemLike item1, int item1Count, ItemLike item2, int item2Count, ItemLike result, int count, int maxTrades, int xp){
        return new BasicItemListing(new ItemStack(item1,item1Count),new ItemStack(item2,item2Count), new ItemStack(result,count), maxTrades, xp, 0.05f);
    }

    public static BasicItemListing sellTrade(ItemLike item, int count, int maxTrades, int xp) {
        return new BasicItemListing(1, new ItemStack(item,count), maxTrades, xp, 0.05F);
    }
    public static BasicItemListing sellTrade(int value,ItemLike item, int count, int maxTrades, int xp) {
        return new BasicItemListing(value, new ItemStack(item,count), maxTrades, xp, 0.05F);
    }

}
