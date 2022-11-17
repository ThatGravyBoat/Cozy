package tech.thatgravyboat.cozy.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import tech.thatgravyboat.cozy.mixin.LevelRendererAccessor;

public class LevelRendererBridge {

    public static boolean isPlayingRecord(BlockPos pos) {
        if (Minecraft.getInstance().levelRenderer instanceof LevelRendererAccessor accessor) {
            return accessor.getPlayingRecords().containsKey(pos);
        }
        return false;
    }
}
