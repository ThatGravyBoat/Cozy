package tech.thatgravyboat.cozy.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import tech.thatgravyboat.cozy.common.registry.ModFoods;

public class TomatoCropBlock extends CropBlock implements BonemealableBlock {

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(5.0D, 0.0D, 5.0D, 11.0D, 4.0D, 11.0D),
            Block.box(4.0D, 0.0D, 4.0D, 12.0D, 7.0D, 12.0D),
            Block.box(3.0D, 0.0D, 3.0D, 13.0D, 13.0D, 13.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 14.0D, 14.0D),
            Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D)
    };

    public TomatoCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    public int getMaxAge() {
        return 4;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModFoods.TOMATO_SEEDS.get();
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return BlockStateProperties.AGE_4;
    }

    @Override
    public InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult result) {
        if (getAge(state) != 4) return super.use(state, level, pos, player, hand, result);

        popResource(level, pos, new ItemStack(ModFoods.TOMATO.get(), 2 + level.random.nextInt(1)));
        level.playSound(null, pos, SoundEvents.CAVE_VINES_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
        BlockState baseState = state.setValue(getAgeProperty(), 2);
        level.setBlock(pos, baseState, 2);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, baseState));
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(getAgeProperty());
    }

    @Override
    public VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE_BY_AGE[state.getValue(this.getAgeProperty())];
    }
}
