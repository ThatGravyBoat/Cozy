package tech.thatgravyboat.cozy.common.blocks.base;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;

public interface Hammerable {

    BlockState hammer(Player player, BlockPos pos, BlockState state);
}
