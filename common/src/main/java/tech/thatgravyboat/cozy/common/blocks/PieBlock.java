package tech.thatgravyboat.cozy.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import tech.thatgravyboat.cozy.common.utils.PiePlacer;

public class PieBlock extends Block {

    public static final VoxelShape TWO_SHAPE = Block.box(2.0D, 0.0D, 8.0D, 14.0D, 3.0D, 14.0D);
    public static final VoxelShape ONE_SHAPE = Block.box(8.0D, 0.0D, 8.0D, 14.0D, 3.0D, 14.0D);
    public static final VoxelShape THREE_SHAPE = Shapes.join(TWO_SHAPE, Block.box(8.0D, 0.0D, 2.0D, 14.0D, 3.0D, 8.0D), BooleanOp.OR);
    public static final VoxelShape FULL_SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D);

    public static final IntegerProperty SLICES = IntegerProperty.create("slices", 1, 4);

    public PieBlock(Properties properties) {
        super(properties);

        this.registerDefaultState(this.stateDefinition.any().setValue(SLICES, 4));
    }

    @Override
    public ItemStack getCloneItemStack(@NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull BlockState state) {
        return new ItemStack(PiePlacer.PIES.inverse().get(this));
    }

    @Override
    public InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult result) {
        if (!level.isClientSide() && player.canEat(false)) {
            Item item = PiePlacer.PIES.inverse().get(this);
            if (item != null) {
                FoodProperties food = item.getFoodProperties();
                if (food != null)
                    player.getFoodData().eat((int) (food.getNutrition() / 4f), food.getSaturationModifier() / 4f);
            }
            if (state.getValue(SLICES) == 1) {
                level.removeBlock(pos, false);
            } else {
                level.setBlockAndUpdate(pos, state.setValue(SLICES, state.getValue(SLICES) - 1));
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SLICES);
    }

    @Override
    public VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return switch (state.getValue(SLICES)) {
            case 1 -> ONE_SHAPE;
            case 2 -> TWO_SHAPE;
            case 3 -> THREE_SHAPE;
            default -> FULL_SHAPE;
        };
    }
}
