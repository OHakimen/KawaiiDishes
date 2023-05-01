package com.hakimen.kawaiidishes.events;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.config.KawaiiDishesCommonConfig;
import com.hakimen.kawaiidishes.registry.EffectRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import com.hakimen.kawaiidishes.registry.VillagerWorkRegister;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
        if (!KawaiiDishesCommonConfig.villagerTrades.get()) return;
        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
        VillagerProfession profession = event.getType();
        if (profession.toString().equals(null)) return;
        if (profession.equals(VillagerWorkRegister.baristaVillagerProfession.get())) {
            // First Tier Trades
            trades.get(1).add(buyTrade(ItemRegister.roastedCoffeeBeans.get(), 16, 16, 5));
            trades.get(1).add(buyTrade(ItemRegister.roastedCocoaBeans.get(), 16, 16, 5));
            trades.get(1).add(conversionTrade(4, ItemRegister.coffeeFruit.get(), ItemRegister.roastedCoffeeBeans.get(), 16, 16, 5));
            trades.get(1).add(conversionTrade(4, Items.COCOA_BEANS, ItemRegister.roastedCocoaBeans.get(), 16, 16, 5));
            trades.get(1).add(sellTrade(4, ItemRegister.mug.get(), 4, 16, 5));
            // Second Tier Trades
            trades.get(2).add(buyTrade(ItemRegister.cocoaPowder.get(), 4, 16, 5));
            trades.get(2).add(buyTrade(ItemRegister.coffeePowder.get(), 4, 16, 5));
            trades.get(2).add(buyTrade(Items.HONEY_BOTTLE, 4, 16, 5));
            trades.get(2).add(buyTrade(Items.SWEET_BERRIES, 16, 16, 5));


            //Third Tier Trades
            trades.get(3).add(sellTrade(1, ItemRegister.chocolateCookie.get(), 8, 16, 5));
            trades.get(3).add(sellTrade(1, ItemRegister.sweetBerryCookie.get(), 8, 16, 5));
            trades.get(3).add(sellTrade(1, ItemRegister.honeyCookie.get(), 8, 16, 5));
            trades.get(3).add(sellTrade(1, ItemRegister.beijinho.get(), 8, 16, 5));
            trades.get(3).add(sellTrade(1, ItemRegister.brigadeiro.get(), 8, 16, 5));

            //Fourth Tier Trades
            trades.get(4).add(coffeeTrade(1, ItemRegister.mug.get(), ItemRegister.americanCoffee.get()
                            .getStackWithEffects(
                                    new MobEffectInstance(EffectRegister.kawaiiEffect.get(), 15 * 20),
                                    new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 30 * 20))
                    , 1, 16, 5));
            trades.get(4).add(coffeeTrade(2, ItemRegister.mug.get(), ItemRegister.doppioCoffee.get()
                            .getStackWithEffects(
                                    new MobEffectInstance(EffectRegister.kawaiiEffect.get(), 15 * 20),
                                    new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 30 * 20, 1))
                    , 1, 16, 5));
            trades.get(4).add(coffeeTrade(2, ItemRegister.mug.get(), ItemRegister.expressoCoffee.get()
                            .getStackWithEffects(
                                    new MobEffectInstance(EffectRegister.kawaiiEffect.get(), 15 * 20),
                                    new MobEffectInstance(MobEffects.NIGHT_VISION, 15 * 20))
                    , 1, 16, 5));
            trades.get(4).add(coffeeTrade(4, ItemRegister.mug.get(), ItemRegister.latteCoffee.get()
                            .getStackWithEffects(
                                    new MobEffectInstance(EffectRegister.kawaiiEffect.get(), 15 * 20),
                                    new MobEffectInstance(MobEffects.DIG_SPEED, 30 * 20))
                    , 1, 16, 5));
            trades.get(4).add(coffeeTrade(4, ItemRegister.mug.get(), ItemRegister.mochaCoffee.get()
                            .getStackWithEffects(
                                    new MobEffectInstance(EffectRegister.kawaiiEffect.get(), 15 * 20),
                                    new MobEffectInstance(MobEffects.REGENERATION, 30 * 20, 1))
                    , 1, 16, 5));


            //Fifth Tier Trades
            trades.get(5).add(sellTrade(8, ItemRegister.goldenCookie.get(), 1, 16, 5));
            trades.get(5).add(coffeeTrade(4, ItemRegister.mug.get(), ItemRegister.cappuccinoCoffee.get().getStackWithEffects(
                            new MobEffectInstance(EffectRegister.kawaiiEffect.get(), 15 * 20),
                            new MobEffectInstance(MobEffects.DIG_SPEED, 60 * 20, 1))
                    , 1, 16, 5));
            trades.get(5).add(coffeeTrade(4, ItemRegister.mug.get(), ItemRegister.macchiatoCoffee.get()
                            .getStackWithEffects(
                                    new MobEffectInstance(EffectRegister.kawaiiEffect.get(), 15 * 20),
                                    new MobEffectInstance(MobEffects.ABSORPTION, 30 * 20, 1))
                    , 1, 16, 5));
        }
        if (KawaiiDishesCommonConfig.villagerTrades.get()) {
            if (profession.toString().equals("farmer")) {
                trades.get(2).add(buyTrade(ItemRegister.coffeeFruit.get(), 22 + KawaiiDishes.RANDOM.nextInt(-12, 8), 16, 5));
                trades.get(3).add(sellTrade(ItemRegister.honeyCheeseCakePiece.get(), 4, 16, 5));
                trades.get(3).add(sellTrade(ItemRegister.chocolateCheeseCakePiece.get(), 4, 16, 5));
                trades.get(3).add(sellTrade(ItemRegister.cheeseCakePiece.get(), 4, 16, 5));
                trades.get(4).add(sellTrade(ItemRegister.honeyCheeseCake.get(), 1, 16, 5));
                trades.get(4).add(sellTrade(ItemRegister.chocolateCheeseCake.get(), 1, 16, 5));
                trades.get(4).add(sellTrade(ItemRegister.cheeseCake.get(), 1, 16, 5));
            }
        }
    }

    @SubscribeEvent
    public static void onWandererTrades(WandererTradesEvent event) {
        if (KawaiiDishesCommonConfig.wanderingTraderTrades.get()) {
            List<VillagerTrades.ItemListing> trades = event.getGenericTrades();
            trades.add(sellTrade(ItemRegister.coffeeFruit.get(), 4, 1, 12));
        }
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
