package tech.thatgravyboat.cozy.mixin.fabric;

import net.minecraft.gametest.framework.GameTestServer;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.thatgravyboat.cozy.fabric.CozyFabric;

@Mixin(GameTestServer.class)
public class MixinGameTestServer {

    @Inject(method = "initServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/gametest/framework/GameTestServer;loadLevel()V"))
    public void beforeLoadLevel(CallbackInfoReturnable<Boolean> cir) {
        CozyFabric.serverAboutToStart((MinecraftServer) ((Object) this));
    }
}
