package tech.thatgravyboat.cozy.common.blocks.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import tech.thatgravyboat.cozy.common.entity.SittingEntity;

public interface SittingBlock {

    default boolean canSitOn(Level level, BlockPos pos, Player player, Direction direction) {
        if (!SittingEntity.CURRENTLY_SITTING.containsKey(pos) && !level.isClientSide()) {
            SittingEntity seat = SittingEntity.of(level, pos, direction);
            level.addFreshEntity(seat);
            player.startRiding(seat);
            return true;
        }
        return false;
    }
}
