package tech.thatgravyboat.cozy.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tech.thatgravyboat.cozy.common.blocks.base.Hammerable;
import tech.thatgravyboat.cozy.common.blocks.base.SittingBlock;
import tech.thatgravyboat.cozy.common.items.CozyItemExtension;
import tech.thatgravyboat.cozy.common.registry.ModItems;
import tech.thatgravyboat.cozy.common.utils.WoolHelper;

@SuppressWarnings("deprecation")
public class ChairBlock extends HorizontalDirectionalBlock implements SittingBlock, Hammerable, CozyItemExtension, EntityBlock {

    private static final VoxelShape BASE_LOWER_SHAPE = Shapes.join(box(1d, 7d, 1d, 15d, 10d, 15d), box(2d, 0d, 2d, 14d, 7d, 14d), BooleanOp.OR);
    private static final VoxelShape BASE_UPPER_SHAPE = Shapes.join(box(1d, -9d, 1d, 15d, -6d, 15d), box(2d, -16d, 2d, 14d, -9d, 14d), BooleanOp.OR);

    private static final VoxelShape[] SHAPES = new VoxelShape[8];

    static {
        SHAPES[0] = Shapes.join(BASE_LOWER_SHAPE, box(1d, 10d, 1d, 15d, 27d, 3d), BooleanOp.OR); //South
        SHAPES[1] = Shapes.join(BASE_LOWER_SHAPE, box(13d, 10d, 1d, 15d, 27d, 15d), BooleanOp.OR); //West
        SHAPES[2] = Shapes.join(BASE_LOWER_SHAPE, box(1d, 10d, 13d, 15d, 27d, 15d), BooleanOp.OR); //North
        SHAPES[3] = Shapes.join(BASE_LOWER_SHAPE, box(1d, 10d, 1d, 3d, 27d, 15d), BooleanOp.OR); //East
        SHAPES[4] = Shapes.join(BASE_UPPER_SHAPE, box(1d, -6d, 1d, 15d, 11d, 3d), BooleanOp.OR); //South Lower
        SHAPES[5] = Shapes.join(BASE_UPPER_SHAPE, box(13d, -6d, 1d, 15d, 11d, 15d), BooleanOp.OR); //West Lower
        SHAPES[6] = Shapes.join(BASE_UPPER_SHAPE, box(1d, -6d, 13d, 15d, 11d, 15d), BooleanOp.OR); //North Lower
        SHAPES[7] = Shapes.join(BASE_UPPER_SHAPE, box(1d, -6d, 1d, 3d, 11d, 15d), BooleanOp.OR); //East Lower
    }


    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public ChairBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER).setValue(FACING, Direction.NORTH));
    }

    //region Double Tall Block
    @Override
    public BlockState updateShape(BlockState state, @NotNull Direction direction, @NotNull BlockState neighborState, @NotNull LevelAccessor level, @NotNull BlockPos pos, @NotNull BlockPos neighborPos) {
        DoubleBlockHalf half = state.getValue(HALF);
        if (half == DoubleBlockHalf.UPPER && direction == Direction.DOWN && !neighborState.is(this)) {
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        BlockPos blockPos = blockPlaceContext.getClickedPos();
        Level level = blockPlaceContext.getLevel();
        if (blockPos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(blockPos.above()).canBeReplaced(blockPlaceContext)) {
            return this.defaultBlockState().setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite());
        }
        return null;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, @NotNull BlockState state, LivingEntity entity, @NotNull ItemStack stack) {
        level.setBlock(pos.above(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(FACING, state.getValue(FACING)), 3);
    }

    @Override
    public boolean canSurvive(BlockState blockState, @NotNull LevelReader level, @NotNull BlockPos pos) {
        if (blockState.getValue(HALF) != DoubleBlockHalf.UPPER) return super.canSurvive(blockState, level, pos);
        BlockState stateBelow = level.getBlockState(pos.below());
        return stateBelow.is(this) && stateBelow.getValue(HALF) == DoubleBlockHalf.LOWER;
    }

    @Override
    public void playerWillDestroy(Level level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Player player) {
        if (!level.isClientSide) {
            if (player.isCreative()) {
                if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
                    BlockPos below = pos.below();
                    BlockState belowState = level.getBlockState(below);
                    if (belowState.is(state.getBlock()) && belowState.getValue(HALF) == DoubleBlockHalf.LOWER) {
                        level.setBlock(below, Blocks.AIR.defaultBlockState(), 35);
                        level.levelEvent(player, 2001, below, Block.getId(belowState));
                    }
                }
            } else {
                if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
                    BlockPos below = pos.below();
                    BlockState belowState = level.getBlockState(below);
                    dropResources(belowState, level, below, null, player, player.getMainHandItem());
                    level.removeBlock(below, false);
                }else {
                    dropResources(state, level, pos, null, player, player.getMainHandItem());
                }
            }
        }

        super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public void playerDestroy(@NotNull Level level, @NotNull Player player, @NotNull BlockPos pos, @NotNull BlockState state, @Nullable BlockEntity blockEntity, @NotNull ItemStack stack) {
        super.playerDestroy(level, player, pos, Blocks.AIR.defaultBlockState(), blockEntity, stack);
    }

    @Override
    public PushReaction getPistonPushReaction(@NotNull BlockState state) {
        return PushReaction.BLOCK;
    }

    //endregion

    @Override
    public InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult result) {
        ItemStack stack = player.getItemInHand(hand);
        BlockPos chairBase = state.getValue(HALF) == DoubleBlockHalf.UPPER ? pos.below() : pos;
        if (level.getBlockEntity(chairBase) instanceof ChairBlockEntity entity) {
            DyeColor woolDye = WoolHelper.getDyeColor(stack.getItem());
            if (woolDye != null && !entity.hasColor()) {
                entity.setColor(woolDye);
                entity.setChanged();
                level.sendBlockUpdated(chairBase, state, state, Block.UPDATE_ALL);
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
            DyeColor dye = stack.getItem() instanceof DyeItem dyeItem ? dyeItem.getDyeColor() : null;
            if (dye != null && entity.hasColor()) {
                entity.setColor(dye);
                entity.setChanged();
                level.sendBlockUpdated(chairBase, state, state, Block.UPDATE_ALL);
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
            if (stack.is(Items.SHEARS) && entity.hasColor()) {
                dropCushion(level, chairBase);
                entity.setColor(null);
                entity.setChanged();
                level.sendBlockUpdated(chairBase, state, state, Block.UPDATE_ALL);
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }
        if (!stack.is(ModItems.HAMMER.get()) && canSitOn(level, chairBase, player, state.getValue(FACING))) {
            return InteractionResult.SUCCESS;
        }
        return super.use(state, level, pos, player, hand, result);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF);
    }

    @Override
    public VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext ctx) {
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            if (level.getBlockState(pos.above()).is(this)) {
                return SHAPES[state.getValue(FACING).get2DDataValue()];
            }
            return BASE_LOWER_SHAPE;
        }
        return SHAPES[4 + state.getValue(FACING).get2DDataValue()];
    }

    @Override
    public BlockState hammer(Player player, BlockPos pos, BlockState state) {
        boolean shifting = player.isShiftKeyDown();
        Level level = player.level;
        BlockState aboveState = level.getBlockState(pos.above());
        if (shifting) {
            boolean isUpper = state.getValue(HALF) == DoubleBlockHalf.UPPER;
            boolean canContinue = (aboveState.is(this) && aboveState.getValue(HALF) == DoubleBlockHalf.UPPER) || isUpper;
            if (canContinue) {
                if (isUpper) {
                    BlockState belowState = level.getBlockState(pos.below()).rotate(Rotation.CLOCKWISE_90);
                    level.setBlockAndUpdate(pos.below(), belowState);
                    return state.setValue(FACING, belowState.getValue(FACING));
                }
                aboveState = aboveState.rotate(Rotation.CLOCKWISE_90);
                level.setBlockAndUpdate(pos.above(), aboveState);
                return state.setValue(FACING, aboveState.getValue(FACING));
            }
        } else {
            if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
                return Blocks.AIR.defaultBlockState();
            } else if (aboveState.is(this) && aboveState.getValue(HALF) == DoubleBlockHalf.UPPER) {
                level.setBlockAndUpdate(pos.above(), Blocks.AIR.defaultBlockState());
            } else if (aboveState.isAir()) {
                level.setBlockAndUpdate(pos.above(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(FACING, state.getValue(FACING)));
            }
        }
        return state;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return state.getValue(HALF) == DoubleBlockHalf.LOWER ? new ChairBlockEntity(pos, state) : null;
    }

    @Override
    public void onRemove(BlockState state, @NotNull Level level, @NotNull BlockPos pos, BlockState newState, boolean moving) {
        if (!state.is(newState.getBlock())) {
            dropCushion(level, pos);
        }
        super.onRemove(state, level, pos, newState, moving);
    }

    public void dropCushion(Level level, BlockPos pos) {
        if (level.getBlockEntity(pos) instanceof ChairBlockEntity entity) {
            if (entity.hasColor()) {
                Block block = WoolHelper.getBlock(entity.getColor());
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(block != null ? block : Blocks.WHITE_WOOL));
            }
        }
    }
}
