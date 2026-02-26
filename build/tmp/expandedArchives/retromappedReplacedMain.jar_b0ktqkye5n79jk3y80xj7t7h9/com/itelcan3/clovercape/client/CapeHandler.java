package com.itelcan3.clovercape.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.util.ResourceLocation;
import java.io.File;
import java.lang.reflect.Field;

public class CapeHandler {

    private static final ResourceLocation CAPE_RES = new ResourceLocation("clovercape/custom_cape");
    private static boolean isTextureLoaded = false;
    private static File capeFile;

    public static void initFolder() {
        File mcDir = Minecraft.func_71410_x().field_71412_D;
        File configDir = new File(mcDir, ".clovercape");
        if (!configDir.exists()) configDir.mkdirs();
        capeFile = new File(configDir, "cape.png");
    }

    public static void apply() {
        Minecraft mc = Minecraft.func_71410_x();
        if (mc.field_71439_g == null || mc.func_147114_u() == null) return;

        AbstractClientPlayer player = mc.field_71439_g;
        NetworkPlayerInfo info = mc.func_147114_u().func_175102_a(player.func_110124_au());
        
        if (info != null && capeFile.exists()) {
            if (!isTextureLoaded) {
                loadCapeFromFile();
                isTextureLoaded = true;
            }

            try {
                // Usiamo il campo che abbiamo visto nel tuo log: field_178862_f
                // Questo campo imposta direttamente la ResourceLocation del mantello
                Field fCape = NetworkPlayerInfo.class.getDeclaredField("field_178862_f");
                fCape.setAccessible(true);
                fCape.set(info, CAPE_RES);
                
            } catch (NoSuchFieldException e) {
                // Se per caso cambia nome (es. in ambiente dev), proviamo il nome umano
                try {
                    Field fCapeDev = NetworkPlayerInfo.class.getDeclaredField("locationCape");
                    fCapeDev.setAccessible(true);
                    fCapeDev.set(info, CAPE_RES);
                } catch (Exception ignored) {}
            } catch (Exception e) {
                System.out.println("CloverCape > En error occured during loading Cape: " + e.getMessage());
            }
        }
    }

    private static void loadCapeFromFile() {
        ThreadDownloadImageData textureData = new ThreadDownloadImageData(capeFile, null, null, null);
        Minecraft.func_71410_x().func_110434_K().func_110579_a(CAPE_RES, textureData);
        System.out.println("CloverCape > Cape Loaded successful");
    }
}