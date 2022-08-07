package tech.thatgravyboat.cozy.mixin.fabric;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.thatgravyboat.cozy.fabric.CozyFabric;

@Mixin(DedicatedServer.class)
public class MixinDedicatedServer {

    @Inject(method = "initServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/dedicated/DedicatedServer;loadLevel()V"))
    public void beforeLoadLevel(CallbackInfoReturnable<Boolean> cir) {
        CozyFabric.serverAboutToStart((MinecraftServer) ((Object) this));
    }
}
