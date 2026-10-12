package com.example.visualclient;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;

public class FloatingDamage {
    public static boolean enabled = true;
    private static final Map<Integer, DamagePopup> popups = new HashMap<>();

    public static void init() {
        // Простая версия через отслеживание здоровья (не идеальная, но визуальная)
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!enabled || client.level == null || client.player == null) return;

            // Можно расширить через mixin на LivingEntity
        });
    }

    public static class DamagePopup {
        public double x, y, z;
        public float damage;
        public int age;
        public boolean isCrit;
    }
}
