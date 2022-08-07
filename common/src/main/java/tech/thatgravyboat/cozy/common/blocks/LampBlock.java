package tech.thatgravyboat.cozy.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class LampBlock extends Block {

    private static final VoxelShape SHAPE = Shapes.join(
            box(6d, 0d, 6d, 10d, 5d, 10d),
            Shapes.join(
                box(2d, 5d, 2d, 14d, 11d, 14d),
                box(4d, 11d, 4d, 12d, 15d, 12d),
                BooleanOp.OR
            ),
            BooleanOp.OR
    );

    public static final BooleanProperty ON = BooleanProperty.create("on");

    public LampBlock(Properties properties) {
        super(properties.lightLevel(state -> state.getValue(ON) ? 15 : 0));
        this.registerDefaultState(this.stateDefinition.any().setValue(ON, false));
    }

    @Override
    public InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult result) {
        BlockState newState = state.cycle(ON);
        level.setBlockAndUpdate(pos, newState);
        level.playSound(null, pos, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 0.3F, newState.getValue(ON) ? 0.6F : 0.5F);
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ON);
    }

    @Override
    public VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext ctx) {
        return SHAPE;
    }
}
