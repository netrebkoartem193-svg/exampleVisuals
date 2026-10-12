package com.example.visualclient;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class VisualClient implements ClientModInitializer {
    public static final String MOD_ID = "visualclient";
    public static boolean showHud = true;

    @Override
    public void onInitializeClient() {
        HudElementRegistry.attachElementBefore(
            VanillaHudElements.CHAT,
            Identifier.fromNamespaceAndPath(MOD_ID, "visual_hud"),
            VisualClient::renderHud
        );
    }

    private static void renderHud(GuiGraphics graphics, net.minecraft.client.DeltaTracker tickCounter) {
        if (!showHud) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.options.hideGui) return;

        graphics.drawString(mc.font, Component.literal("FPS: " + mc.getFps()), 10, 10, 0xFFFFFF);

        double time = System.currentTimeMillis() / 1000.0;
        float pulse = Mth.abs(Mth.sin((float) time * 2.0f));
        int r = (int) (255 * pulse);
        int g = (int) (100 * (1 - pulse));
        int color = 0xFF000000 | (r << 16) | (g << 8) | 255;

        graphics.fill(10, 25, 120, 38, color);
        graphics.drawString(mc.font, Component.literal("Visuals Active"), 15, 28, 0xFFFFFF);

        double speed = mc.player.getDeltaMovement().horizontalDistance() * 20.0;
        graphics.drawString(mc.font, Component.literal(String.format("Speed: %.1f b/s", speed)), 10, 45, 0x55FF55);
    }
}
