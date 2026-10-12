package com.example.visualclient;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;

public class JumpCircle {
    public static boolean enabled = true;

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!enabled || client.player == null || client.level == null) return;

            if (client.player.onGround() && client.player.getDeltaMovement().y > 0.1) {
                // Круг частиц при прыжке
                double x = client.player.getX();
                double y = client.player.getY();
                double z = client.player.getZ();

                for (int i = 0; i < 16; i++) {
                    double angle = (Math.PI * 2 * i) / 16;
                    double px = x + Math.cos(angle) * 0.6;
                    double pz = z + Math.sin(angle) * 0.6;
                    client.level.addParticle(ParticleTypes.CLOUD, px, y + 0.1, pz, 0, 0.05, 0);
                }
            }
        });
    }
}
