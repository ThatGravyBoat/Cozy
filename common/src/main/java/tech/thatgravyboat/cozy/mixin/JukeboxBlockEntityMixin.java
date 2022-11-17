package tech.thatgravyboat.cozy.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.thatgravyboat.cozy.client.LevelRendererBridge;

@Mixin(JukeboxBlockEntity.class)
public abstract class JukeboxBlockEntityMixin {

    @Inject(method = "playRecordTick", at = @At("HEAD"))
    private static void cozy$onPlayRecordTick(Level level, BlockPos pos, BlockState state, JukeboxBlockEntity entity, CallbackInfo ci) {
        boolean isPlayingARecord = state.getValue(JukeboxBlock.HAS_RECORD);
        if (isPlayingARecord && level.isClientSide && level.random.nextInt(5) == 0 && LevelRendererBridge.isPlayingRecord(pos)) {
            int num = level.random.nextInt(24);
            double randomX = level.random.nextDouble();
            double randomZ = level.random.nextDouble();
            level.addParticle(ParticleTypes.NOTE, pos.getX() + randomX, pos.getY() + 1.2, pos.getZ() + randomZ, num / 24.0, 0.0, 0.0);
        }
    }
}
