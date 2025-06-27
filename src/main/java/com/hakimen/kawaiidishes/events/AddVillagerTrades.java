package com.hakimen.kawaiidishes.events;

import com.hakimen.kawaiidishes.configs.ServerConfig;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import org.jetbrains.annotations.Nullable;

public class AddVillagerTrades {
    public static void handle() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1,
                factories -> factories.add((entity, random) -> buyTrade(
                        ItemRegister.COFFEE_BERRIES.get(),
                        22,
                        16,
                        5)
                )
        );

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 3,
                factories -> {
                    factories.add((entity, randomSource) ->
                            sellTrade(
                                    ItemRegister.HONEY_CHEESE_CAKE_SLICE.get(),
                                    4,
                                    16,
                                    5)
                    );

                    factories.add((entity, randomSource) ->
                            sellTrade(ItemRegister.CHOCOLATE_CHEESE_CAKE_SLICE.get(),
                                    4,
                                    16,
                                    5)
                    );

                    factories.add((entity, randomSource) ->
                            sellTrade(
                                    ItemRegister.CHEESE_CAKE_SLICE.get(),
                                    4,
                                    16,
                                    5)
                    );
                });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 4,
                factories -> {
                    factories.add((entity, randomSource) ->
                            sellTrade(4,
                                    ItemRegister.WAFFLE.get(),
                                    1,
                                    16,
                                    5)
                    );

                    factories.add((entity, randomSource) ->
                            sellTrade(4,
                                    ItemRegister.CHOCOLATE_WAFFLE.get(),
                                    1,
                                    16,
                                    5)
                    );

                    factories.add((entity, randomSource) ->
                            sellTrade(4,
                                    ItemRegister.CHEESE_CAKE.get(),
                                    1,
                                    16,
                                    5)
                    );
                }
        );

        TradeOfferHelper.registerWanderingTraderOffers(1, itemListings -> {
            itemListings.add(((entity, randomSource) -> sellTrade(ItemRegister.COFFEE_BERRIES.get(), 4,1, 12)));
        });
    }

    public static TradeOffer buyTrade(ItemConvertible item, int count, int maxTrades, int xp) {
        return new TradeOffer(new ItemStack(item, count), new ItemStack(Items.EMERALD), maxTrades, xp, 0.05F);
    }

    public static TradeOffer sellTrade(ItemConvertible item, int count, int maxTrades, int xp) {
        return new TradeOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(item, count), maxTrades, xp, 0.05F);
    }

    public static TradeOffer sellTrade(int value, ItemConvertible item, int count, int maxTrades, int xp) {
        return new TradeOffer(new ItemStack(Items.EMERALD, value), new ItemStack(item, count), maxTrades, xp, 0.05F);
    }
}
