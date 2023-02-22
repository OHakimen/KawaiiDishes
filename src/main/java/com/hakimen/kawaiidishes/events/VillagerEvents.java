package com.hakimen.kawaiidishes.events;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.config.KawaiiDishesCommonConfig;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;


@Mod.EventBusSubscriber(modid = KawaiiDishes.modId)
@ParametersAreNonnullByDefault
public class VillagerEvents {
    @SubscribeEvent
    public static void onVillagerTrades(VillagerTradesEvent event) {
        if(!KawaiiDishesCommonConfig.villagerTrades.get()) return;
        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
        VillagerProfession profession = event.getType();
        if (profession.toString().equals(null)) return;
        if (profession.toString().equals("farmer")) {
            trades.get(2).add(buyTrade(ItemRegister.coffeeFruit.get(), 22 + KawaiiDishes.RANDOM.nextInt(-12,8), 16, 5));
            trades.get(3).add(sellTrade(ItemRegister.honeyCheeseCakePiece.get(), 4, 16, 5));
            trades.get(3).add(sellTrade(ItemRegister.chocolateCheeseCakePiece.get(), 4, 16, 5));
            trades.get(3).add(sellTrade(ItemRegister.cheeseCakePiece.get(), 4, 16, 5));
            trades.get(4).add(sellTrade(ItemRegister.honeyCheeseCake.get(), 1, 16, 5));
            trades.get(4).add(sellTrade(ItemRegister.chocolateCheeseCake.get(), 1, 16, 5));
            trades.get(4).add(sellTrade(ItemRegister.cheeseCake.get(), 1, 16, 5));
        }
    }

    @SubscribeEvent
    public static void onWandererTrades(WandererTradesEvent event) {
        if(!KawaiiDishesCommonConfig.wanderingTraderTrades.get()) return;
        List<VillagerTrades.ItemListing> trades = event.getGenericTrades();
        trades.add(sellTrade(ItemRegister.coffeeFruit.get(), 4,1, 12));
    }

    public static BasicItemListing buyTrade(ItemLike item, int count, int maxTrades, int xp) {
        return new BasicItemListing(new ItemStack(item, count), new ItemStack(Items.EMERALD), maxTrades, xp, 0.05F);
    }

    public static BasicItemListing sellTrade(ItemLike item, int count, int maxTrades, int xp) {
        return new BasicItemListing(1, new ItemStack(item,count), maxTrades, xp, 0.05F);
    }
}
