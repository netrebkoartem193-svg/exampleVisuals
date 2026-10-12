package com.example.visualclient;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionResult;

public class HitParticles {
    public static boolean enabled = true;

    public static void init() {
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (!enabled || !world.isClientSide() || entity == null) return InteractionResult.PASS;

            Minecraft mc = Minecraft.getInstance();
            if (mc.level != null) {
                double x = entity.getX();
                double y = entity.getY() + entity.getBbHeight() * 0.5;
                double z = entity.getZ();

                for (int i = 0; i < 10; i++) {
                    mc.level.addParticle(ParticleTypes.CRIT,
                        x, y, z,
                        (Math.random() - 0.5) * 0.6,
                        Math.random() * 0.4,
                        (Math.random() - 0.5) * 0.6);
                }
            }
            return InteractionResult.PASS;
        });
    }
}
