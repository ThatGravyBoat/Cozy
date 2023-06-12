package tech.thatgravyboat.cozy.common.blocks.pizza;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import tech.thatgravyboat.cozy.common.registry.ModFoods;

import java.util.List;

public class CookedPizzaBlock extends PizzaBlock {

    public static final IntegerProperty SLICES = IntegerProperty.create("slices", 1, 4);

    public CookedPizzaBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(defaultBlockState().setValue(SLICES, 4));
    }

    @Override
    public InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult result) {
        if (!level.isClientSide() && player.canEat(false)) {
            var food = determineFood(state.getValue(SAUCE), state.getValue(CHEESE), state.getValue(CHICKEN), state.getValue(MUSHROOM), state.getValue(PEPPERONI), state.getValue(SAUSAGE));
            player.getFoodData().eat(food.getFirst(), food.getSecond());
            if (state.getValue(SLICES) == 1) {
                level.removeBlock(pos, false);
            } else {
                level.setBlockAndUpdate(pos, state.setValue(SLICES, state.getValue(SLICES) - 1));
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    public List<ItemStack> getDrops(@NotNull BlockState state, LootParams.@NotNull Builder builder) {
        int value = state.getValue(SLICES);
        if (value == 4) {
            return List.of(PizzaBlock.createPizzaFromState(ModFoods.COOKED_PIZZA.get().getDefaultInstance(), state));
        }
        return List.of(PizzaBlock.createPizzaFromState(new ItemStack(ModFoods.PIZZA_SLICE.get(), value), state));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
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
