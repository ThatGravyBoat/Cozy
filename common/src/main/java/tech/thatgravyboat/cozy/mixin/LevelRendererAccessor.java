package tech.thatgravyboat.cozy.mixin;

import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(LevelRenderer.class)
public interface LevelRendererAccessor {

    @Accessor
    Map<BlockPos, SoundInstance> getPlayingRecords();
}
