package com.hakimen.kawaiidishes.client.data;

import java.util.HashMap;
import java.util.UUID;

public class ClientTailWagData {

    private static HashMap<UUID, Boolean> tailStates = new HashMap<>();

    public static void setState(UUID uuid, boolean state){
        tailStates.put(uuid,state);
    }

    public static boolean getState(UUID uuid){
        return tailStates.getOrDefault(uuid, false);
    }
}
