package com.example.nametagtoggle.mixin;

import com.example.nametagtoggle.NametagToggleClient;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin {
    @Inject(method = "shouldShowName", at = @At("HEAD"), cancellable = true, require = 0)
    private void nametagtoggle$hide(Entity entity, double distance, CallbackInfoReturnable<Boolean> cir) {
        if (!NametagToggleClient.nametagsEnabled) {
            cir.setReturnValue(false);
        }
    }
}
