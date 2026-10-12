package com.example.visualclient;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.resources.Identifier;

public class VisualClient implements ClientModInitializer {
    public static final String MOD_ID = "visualclient";

    @Override
    public void onInitializeClient() {
        HudElementRegistry.attachElementBefore(
            VanillaHudElements.CHAT,
            Identifier.fromNamespaceAndPath(MOD_ID, "main_hud"),
            HudRenderer::render
        );

        HitParticles.init();
        CustomCrosshair.init();
        Fullbright.init();
    }
}
