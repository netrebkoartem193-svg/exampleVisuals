package com.example.visualclient.mixin;

import com.example.visualclient.CustomCrosshair;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class CrosshairMixin {
    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    private void onRenderCrosshair(GuiGraphics graphics, CallbackInfo ci) {
        if (!CustomCrosshair.enabled) return;

        int cx = graphics.guiWidth() / 2;
        int cy = graphics.guiHeight() / 2;

        // Простой белый крест
        graphics.fill(cx - 6, cy - 1, cx + 6, cy + 1, 0xFFFFFFFF);
        graphics.fill(cx - 1, cy - 6, cx + 1, cy + 6, 0xFFFFFFFF);

        ci.cancel();
    }
}
