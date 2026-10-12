package com.example.visualclient;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;

public class ViewModel {
    public static boolean enabled = true;
    public static float scale = 1.0f;
    public static float posX = 0f;
    public static float posY = 0f;
    public static float posZ = 0f;

    public static void init() {
        // Основная логика через mixin на ItemInHandRenderer
    }

    public static void reset() {
        scale = 1.0f;
        posX = posY = posZ = 0f;
    }
}
