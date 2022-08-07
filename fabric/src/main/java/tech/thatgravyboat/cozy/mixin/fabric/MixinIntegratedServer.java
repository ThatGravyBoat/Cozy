package tech.thatgravyboat.cozy.mixin.fabric;

import net.minecraft.client.server.IntegratedServer;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.thatgravyboat.cozy.fabric.CozyFabric;

@Mixin(IntegratedServer.class)
public class MixinIntegratedServer {

    @Inject(method = "initServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/server/IntegratedServer;loadLevel()V"))
    public void beforeLoadLevel(CallbackInfoReturnable<Boolean> cir) {
        CozyFabric.serverAboutToStart((MinecraftServer) ((Object) this));
    }
}
