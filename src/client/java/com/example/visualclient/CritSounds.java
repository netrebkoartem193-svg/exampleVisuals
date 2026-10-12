package com.example.visualclient;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;

public class CritSounds {
    public static boolean enabled = true;

    public static void init() {
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (!enabled || !world.isClientSide()) return InteractionResult.PASS;

            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null && mc.player.fallDistance > 0 && !mc.player.onGround() && !mc.player.isInWater()) {
                // Звук крита
                mc.player.playSound(SoundEvents.PLAYER_ATTACK_CRIT, 1.0f, 1.0f);
            }
            return InteractionResult.PASS;
        });
    }
}
