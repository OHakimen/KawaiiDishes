package com.hakimen.kawaiidishes.integration.jei.categories.utils;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import java.util.ArrayList;

public class EntityUtils {
    static ArrayList<EntityType<?>> humanoids = new ArrayList<>();
    static{
        humanoids.add(EntityType.PILLAGER);
        humanoids.add(EntityType.VILLAGER);
        humanoids.add(EntityType.EVOKER);
        humanoids.add(EntityType.ILLUSIONER);
        humanoids.add(EntityType.WITCH);
        humanoids.add(EntityType.WANDERING_TRADER);
        humanoids.add(EntityType.PLAYER);
    }
    public static boolean isHumanoid(LivingEntity entity){
        for (EntityType<?> type:humanoids) {
            if(entity.getType() == type){
                return true;
            }
        }
        return false;
    }
}
