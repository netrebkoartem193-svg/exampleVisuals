package com.example.visualclient;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

public class SpeedLines {
    public static boolean enabled = true;
    public static float threshold = 0.5f;

    public static void init() {
        HudRenderCallback.EVENT.register((graphics, tickCounter) -> {
            if (!enabled) return;

            Minecraft mc = Minecraft.getInstance();
            if (mc.player == null) return;

            double speed = mc.player.getDeltaMovement().horizontalDistance();
            if (speed < threshold) return;

            int alpha = (int) Math.min(180, speed * 200);
            int color = (alpha << 24) | 0xFFFFFF;

            int w = graphics.guiWidth();
            int h = graphics.guiHeight();

            // Простые линии по краям экрана
            graphics.fill(0, 0, 8, h, color);
            graphics.fill(w - 8, 0, w, h, color);
            graphics.fill(0, 0, w, 6, color);
            graphics.fill(0, h - 6, w, h, color);
        });
    }
}
