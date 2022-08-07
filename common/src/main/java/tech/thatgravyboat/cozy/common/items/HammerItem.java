package tech.thatgravyboat.cozy.common.items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import tech.thatgravyboat.cozy.common.blocks.base.Hammerable;

public class HammerItem extends AttackDamageItem {

    public HammerItem(Properties properties) {
        super(1.5D, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() instanceof Hammerable hammerable) {
            if (!level.isClientSide && player != null) {
                ItemStack stack = player.getItemInHand(context.getHand());
                level.setBlockAndUpdate(pos, hammerable.hammer(player, pos, state));
                stack.hurtAndBreak(1, player, (entity) -> entity.broadcastBreakEvent(context.getHand()));
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return super.useOn(context);
    }
}
