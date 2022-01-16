/*
*
*   Copyright belongs to InboundBark
*
*   https://github.com/InboundBark
*
*/

package io.github.inboundbark.viewbobbingmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    protected GameRendererMixin(MinecraftClient client) {
        this.client = client;
    }

    @Shadow private final MinecraftClient client;
    @Redirect(method = "renderWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;bobView(Lnet/minecraft/client/util/math/MatrixStack;F)V"))
    private void noBobView(GameRenderer gameRenderer, MatrixStack matrixStack, float tickDelta) {
        if (!this.client.options.getPerspective().isFirstPerson()) {
            gameRenderer.bobView(matrixStack, tickDelta);
        }
    }
}
