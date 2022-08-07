package tech.thatgravyboat.cozy.common.blocks.cuttingboard;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tech.thatgravyboat.cozy.common.registry.ModItems;

public class CuttingBoardBlock extends Block implements EntityBlock {

    public static final VoxelShape SHAPE = Block.box(1, 0, 1, 15, 1, 15);

    public CuttingBoardBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult result) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof CuttingBoardBlockEntity cuttingBoard) {
            ItemStack stack = player.getItemInHand(hand);
            if (stack.is(ModItems.KNIFE.get())) {
                if (!cuttingBoard.getItem().isEmpty() && cuttingBoard.use()) {
                    stack.hurtAndBreak(1, player, (entity) -> entity.broadcastBreakEvent(hand));
                    return InteractionResult.sidedSuccess(level.isClientSide());
                }
            } else if (player.isShiftKeyDown() && stack.isEmpty()) {
                if (!cuttingBoard.getItem().isEmpty()) {
                    player.setItemInHand(hand, cuttingBoard.getAndRemoveItem());
                    return InteractionResult.sidedSuccess(level.isClientSide());
                }
            } else if (cuttingBoard.getItem().isEmpty()) {
                player.setItemInHand(hand, cuttingBoard.setItem(stack));
                if (level.isClientSide()) cuttingBoard.randomDegree = level.random.nextInt(360);
                return InteractionResult.sidedSuccess(level.isClientSide());
            }
        }
        return InteractionResult.FAIL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new CuttingBoardBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE;
    }
}
