package com.itelcan3.clovercape.client;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraft.client.entity.AbstractClientPlayer;

public class ClientEventHandler {

    // Questo evento viene chiamato prima di renderizzare ogni player
    @SubscribeEvent
    public void onRenderPlayer(RenderPlayerEvent.Pre event) {
        if (event.entityPlayer instanceof AbstractClientPlayer) {
            CapeHandler.addCapeToPlayer((AbstractClientPlayer) event.entityPlayer);
        }
    }
}