package tech.thatgravyboat.cozy.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import tech.thatgravyboat.cozy.Cozy;

public class ConnectingBlock extends Block {

    private static final VoxelShape TOP_SHAPE = box(0d, 12d, 0d, 16d, 16d, 16d);
    private static final VoxelShape NORTHWEST_SHAPE = box(1d, 0d, 1d, 3d, 12d, 3d);
    private static final VoxelShape NORTHEAST_SHAPE = box(13d, 0d, 1d, 15d, 12d, 3d);

    private static final VoxelShape SOUTHWEST_SHAPE = box(1d, 0d, 13d, 3d, 12d, 15d);
    private static final VoxelShape SOUTHEAST_SHAPE = box(13d, 0d, 13d, 15d, 12d, 15d);

    public static final TagKey<Block> CONNECTING_BLOCK_TAG = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(Cozy.MOD_ID, "can_connect_to_table"));

    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;

    public ConnectingBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false));
    }

    @Override
    public void neighborChanged(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Block block, @NotNull BlockPos pos2, boolean bl) {
        super.neighborChanged(state, level, pos, block, pos2, bl);
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            if (!pos2.equals(pos.relative(direction))) continue;
            BlockState dirBlock = level.getBlockState(pos2);
            boolean connected = dirBlock.getBlock() instanceof ConnectingBlock || dirBlock.is(CONNECTING_BLOCK_TAG);
            switch (direction) {
                case NORTH -> level.setBlockAndUpdate(pos, state.setValue(NORTH, connected));
                case SOUTH -> level.setBlockAndUpdate(pos, state.setValue(SOUTH, connected));
                case EAST -> level.setBlockAndUpdate(pos, state.setValue(EAST, connected));
                case WEST -> level.setBlockAndUpdate(pos, state.setValue(WEST, connected));
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, SOUTH, EAST, WEST);
    }

    @Override
    public VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext ctx) {
        VoxelShape shape = TOP_SHAPE;
        if (!state.getValue(NORTH)) {
            if (!state.getValue(EAST)) {
                shape = Shapes.join(shape, NORTHEAST_SHAPE, BooleanOp.OR);
            }
            if (!state.getValue(WEST)) {
                shape = Shapes.join(shape, NORTHWEST_SHAPE, BooleanOp.OR);
            }
        }
        if (!state.getValue(SOUTH)) {
            if (!state.getValue(EAST)) {
                shape = Shapes.join(shape, SOUTHEAST_SHAPE, BooleanOp.OR);
            }
            if (!state.getValue(WEST)) {
                shape = Shapes.join(shape, SOUTHWEST_SHAPE, BooleanOp.OR);
            }
        }
        return shape;
    }
}
