package com.example.visualclient;

import net.minecraft.client.Minecraft;

public class Fullbright {
    public static boolean enabled = false;
    private static double originalGamma = 1.0;

    public static void init() {
        // Простой вариант через gamma
    }

    public static void toggle() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.options == null) return;

        if (!enabled) {
            originalGamma = mc.options.gamma().get();
            mc.options.gamma().set(16.0);
            enabled = true;
        } else {
            mc.options.gamma().set(originalGamma);
            enabled = false;
        }
    }
}
