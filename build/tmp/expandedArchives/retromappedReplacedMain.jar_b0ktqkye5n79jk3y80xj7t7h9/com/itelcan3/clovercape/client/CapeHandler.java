package com.itelcan3.clovercape.client;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ResourceLocation;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class CapeHandler {

    // Il cape della tua mod
    private static final ResourceLocation MY_CAPE = new ResourceLocation("clovercape", "textures/cape.png");

    // Tiene traccia dei player a cui abbiamo già applicato il cape
    private static final Set<String> appliedPlayers = new HashSet<String>();

    public static void addCapeToPlayer(AbstractClientPlayer player) {
        if (player == null || player.func_70005_c_() == null) return;

        // Applica il cape solo se non è già stato applicato
        if (!appliedPlayers.contains(player.func_70005_c_())) {
            try {
                // Reflection per accedere al campo privato locationCape
                Field capeField = AbstractClientPlayer.class.getDeclaredField("locationCape");
                capeField.setAccessible(true);
                capeField.set(player, MY_CAPE);
            } catch (Exception e) {
                e.printStackTrace();
            }
            appliedPlayers.add(player.func_70005_c_());
        }
    }
}