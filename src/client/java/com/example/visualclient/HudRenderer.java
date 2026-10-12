package com.example.visualclient;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;

public class HudRenderer {
    public static boolean targetHud = true;
    public static boolean keystrokes = true;
    public static boolean armorStatus = true;
    public static boolean potionEffects = true;
    public static boolean showFpsCoords = true;

    public static void render(GuiGraphics graphics, net.minecraft.client.DeltaTracker tickCounter) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.options.hideGui) return;

        int y = 10;

        if (showFpsCoords) {
            graphics.drawString(mc.font, Component.literal("FPS: " + mc.getFps()), 10, y, 0xFFFFFF);
            y += 12;

            LocalPlayer p = mc.player;
            graphics.drawString(mc.font, Component.literal(String.format("XYZ: %.1f %.1f %.1f", p.getX(), p.getY(), p.getZ())), 10, y, 0x55FFFF);
            y += 12;
            double speed = p.getDeltaMovement().horizontalDistance() * 20;
            graphics.drawString(mc.font, Component.literal(String.format("Speed: %.1f", speed)), 10, y, 0x55FF55);
            y += 16;
        }

        if (armorStatus) {
            int armorX = 10;
            for (ItemStack stack : mc.player.getInventory().armor) {
                if (!stack.isEmpty()) {
                    graphics.renderItem(stack, armorX, y);
                    graphics.renderItemDecorations(mc.font, stack, armorX, y);
                    armorX += 18;
                }
            }
            y += 20;
        }

        if (potionEffects) {
            for (MobEffectInstance effect : mc.player.getActiveEffects()) {
                String name = effect.getEffect().value().getDisplayName().getString();
                int duration = effect.getDuration() / 20;
                graphics.drawString(mc.font, Component.literal(name + " " + duration + "s"), 10, y, 0xFFAA00);
                y += 10;
            }
            y += 6;
        }

        if (targetHud && mc.crosshairPickEntity instanceof LivingEntity target) {
            graphics.fill(10, y, 150, y + 42, 0x88000000);
            graphics.drawString(mc.font, Component.literal(target.getName().getString()), 14, y + 4, 0xFFFFFF);
            graphics.drawString(mc.font, Component.literal(String.format("HP: %.1f / %.1f", target.getHealth(), target.getMaxHealth())), 14, y + 16, 0xFF5555);
            graphics.drawString(mc.font, Component.literal(String.format("Dist: %.1f", mc.player.distanceTo(target))), 14, y + 28, 0xAAAAAA);
        }

        if (keystrokes) {
            drawKey(graphics, mc, "W", 200, 30, mc.options.keyUp.isDown());
            drawKey(graphics, mc, "A", 180, 50, mc.options.keyLeft.isDown());
            drawKey(graphics, mc, "S", 200, 50, mc.options.keyDown.isDown());
            drawKey(graphics, mc, "D", 220, 50, mc.options.keyRight.isDown());
            drawKey(graphics, mc, "L", 180, 75, mc.options.keyAttack.isDown());
            drawKey(graphics, mc, "R", 220, 75, mc.options.keyUse.isDown());
        }
    }

    private static void drawKey(GuiGraphics g, Minecraft mc, String text, int x, int y, boolean pressed) {
        int bg = pressed ? 0xAA55FF55 : 0x88000000;
        g.fill(x, y, x + 18, y + 18, bg);
        g.drawCenteredString(mc.font, text, x + 9, y + 5, 0xFFFFFF);
    }
}
