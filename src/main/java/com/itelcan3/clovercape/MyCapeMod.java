package com.itelcan3.clovercape;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import com.itelcan3.clovercape.client.ClientEventHandler;

@Mod(modid = "clovercape", name = "CloverCape", version = "1.0")
public class MyCapeMod {

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // Registriamo il client event handler sul bus di Forge
        FMLCommonHandler.instance().bus().register(new ClientEventHandler());
    }
}