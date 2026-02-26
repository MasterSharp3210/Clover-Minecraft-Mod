package com.itelcan3.clovercape;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import com.itelcan3.clovercape.client.ClientEventHandler;
import com.itelcan3.clovercape.client.CapeHandler;

@Mod(modid = "clovercape", name = "CloverCape", version = "1.0")
public class MyCapeMod {

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // Crea la cartella
        CapeHandler.initFolder();
        
        // Registra l'handler sul bus FML (necessario per ClientTickEvent)
        FMLCommonHandler.instance().bus().register(new ClientEventHandler());
    }
}