package com.example.visualclient;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class Trails {
    public static boolean enabled = true;
    private static final List<Vec3> trailPoints = new ArrayList<>();
    private static int tickCounter = 0;

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!enabled || client.player == null || client.level == null) return;

            tickCounter++;
            if (tickCounter % 2 != 0) return;

            Vec3 pos = client.player.position().add(0, 0.1, 0);
            trailPoints.add(pos);

            if (trailPoints.size() > 30) {
                trailPoints.remove(0);
            }

            // Рисуем частицы вдоль трейла
            for (int i = 0; i < trailPoints.size(); i++) {
                Vec3 p = trailPoints.get(i);
                client.level.addParticle(ParticleTypes.END_ROD,
                    p.x, p.y, p.z,
                    0, 0.01, 0);
            }
        });
    }
}
