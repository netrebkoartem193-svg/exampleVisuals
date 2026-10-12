package com.example.visualclient;

import net.minecraft.client.Minecraft;

public class ModuleManager {
    public static void toggleFullbright() {
        Fullbright.toggle();
    }

    public static void toggleTrails() {
        Trails.enabled = !Trails.enabled;
    }

    public static void toggleSpeedLines() {
        SpeedLines.enabled = !SpeedLines.enabled;
    }

    public static void toggleHitParticles() {
        HitParticles.enabled = !HitParticles.enabled;
    }

    public static void toggleCrosshair() {
        CustomCrosshair.enabled = !CustomCrosshair.enabled;
    }
}
