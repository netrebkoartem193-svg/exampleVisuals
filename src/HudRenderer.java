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
    public static boolean showInfo = true;

    public static void render(GuiGraphics graphics, net.minecraft.client.DeltaTracker tickCounter) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.options.hideGui) return;

        LocalPlayer player = mc.player;
        int x = 8;
        int y = 8;

        // Основной информационный блок с фоном
        if (showInfo) {
            int panelHeight = 48;
            graphics.fill(x - 4, y - 4, x + 140, y + panelHeight, 0x88000000);
            graphics.fill(x - 4, y - 4, x + 140, y - 2, 0xFF55AAFF); // верхняя линия

            graphics.drawString(mc.font, Component.literal("FPS: " + mc.getFps()), x, y, 0xFFFFFF);
            y += 11;
            graphics.drawString(mc.font, Component.literal(String.format("XYZ: %.0f %.0f %.0f", player.getX(), player.getY(), player.getZ())), x, y, 0x55FFFF);
            y += 11;
            double speed = player.getDeltaMovement().horizontalDistance() * 20;
            graphics.drawString(mc.font, Component.literal(String.format("Speed: %.1f", speed)), x, y, 0x55FF55);
            y += 11;
            graphics.drawString(mc.font, Component.literal("Health: " + (int) player.getHealth()), x, y, 0xFF5555);
            y += 16;
        }

        // Броня
        if (armorStatus) {
            int armorX = x;
            for (ItemStack stack : player.getInventory().armor) {
                if (!stack.isEmpty()) {
                    graphics.renderItem(stack, armorX, y);
                    graphics.renderItemDecorations(mc.font, stack, armorX, y);
                    armorX += 18;
                }
            }
            y += 22;
        }

        // Зелья
        if (potionEffects && !player.getActiveEffects().isEmpty()) {
            graphics.fill(x - 4, y - 2, x + 120, y + player.getActiveEffects().size() * 10 + 2, 0x66000000);
            for (MobEffectInstance effect : player.getActiveEffects()) {
                String name = effect.getEffect().value().getDisplayName().getString();
                int sec = effect.getDuration() / 20;
                graphics.drawString(mc.font, Component.literal(name + " §7" + sec + "s"), x, y, 0xFFAA00);
                y += 10;
            }
            y += 6;
        }

        // Target HUD
        if (targetHud && mc.crosshairPickEntity instanceof LivingEntity target && target != player) {
            int th = 46;
            graphics.fill(x - 4, y, x + 160, y + th, 0xAA000000);
            graphics.fill(x - 4, y, x + 160, y + 2, 0xFFFF5555);

            graphics.drawString(mc.font, Component.literal(target.getName().getString()), x, y + 6, 0xFFFFFF);
            float hp = target.getHealth();
            float max = target.getMaxHealth();
            graphics.drawString(mc.font, Component.literal(String.format("§c%.1f §7/ %.1f", hp, max)), x, y + 18, 0xFFFFFF);

            // Полоска здоровья
            int barWidth = 140;
            int filled = (int) (barWidth * (hp / max));
            graphics.fill(x, y + 30, x + barWidth, y + 36, 0xFF333333);
            graphics.fill(x, y + 30, x + filled, y + 36, 0xFFFF5555);

            graphics.drawString(mc.font, Component.literal(String.format("Dist: %.1f", player.distanceTo(target))), x, y + 38, 0xAAAAAA);
            y += th + 6;
        }

        // Keystrokes
        if (keystrokes) {
            int kx = 220;
            int ky = 20;
            drawKey(graphics, mc, "W", kx + 20, ky, mc.options.keyUp.isDown());
            drawKey(graphics, mc, "A", kx, ky + 20, mc.options.keyLeft.isDown());
            drawKey(graphics, mc, "S", kx + 20, ky + 20, mc.options.keyDown.isDown());
            drawKey(graphics, mc, "D", kx + 40, ky + 20, mc.options.keyRight.isDown());
            drawKey(graphics, mc, "L", kx, ky + 45, mc.options.keyAttack.isDown());
            drawKey(graphics, mc, "R", kx + 40, ky + 45, mc.options.keyUse.isDown());
        }
    }

    private static void drawKey(GuiGraphics g, Minecraft mc, String text, int x, int y, boolean pressed) {
        int bg = pressed ? 0xCC55FF55 : 0xAA111111;
        int border = pressed ? 0xFFFFFFFF : 0xFF555555;
        g.fill(x, y, x + 18, y + 18, bg);
        g.fill(x, y, x + 18, y + 1, border);
        g.fill(x, y + 17, x + 18, y + 18, border);
        g.drawCenteredString(mc.font, text, x + 9, y + 5, 0xFFFFFF);
    }
}
