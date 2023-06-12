package tech.thatgravyboat.cozy.common.blocks.pizza;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import tech.thatgravyboat.cozy.common.registry.ModFoods;

import java.util.List;
import java.util.function.Predicate;

public class BuilderPizzaBlock extends PizzaBlock {

    public BuilderPizzaBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult result) {
        ItemStack stack = player.getItemInHand(hand);
        if (applyState(stack, SAUCE, ModFoods.TOMATO_SAUCE.get()::equals, state, player, pos)) return InteractionResult.sidedSuccess(level.isClientSide());
        if (applyState(stack, CHEESE, ModFoods.CHEESE.get()::equals, state, player, pos)) return InteractionResult.sidedSuccess(level.isClientSide());
        if (applyState(stack, CHICKEN, Items.COOKED_CHICKEN::equals, state, player, pos)) return InteractionResult.sidedSuccess(level.isClientSide());
        if (applyState(stack, MUSHROOM, Items.BROWN_MUSHROOM::equals, state, player, pos)) return InteractionResult.sidedSuccess(level.isClientSide());
        if (applyState(stack, PEPPERONI, Items.COOKED_PORKCHOP::equals, state, player, pos)) return InteractionResult.sidedSuccess(level.isClientSide());
        if (applyState(stack, SAUSAGE, Items.COOKED_BEEF::equals, state, player, pos)) return InteractionResult.sidedSuccess(level.isClientSide());

        var hasAnyState = state.getProperties().stream()
                .filter(prop -> prop instanceof BooleanProperty)
                .map(prop -> (BooleanProperty) prop)
                .anyMatch(state::getValue);

        if (player.isShiftKeyDown() && stack.isEmpty() && hasAnyState) {
            player.setItemInHand(hand, PizzaBlock.createPizzaFromState(new ItemStack(ModFoods.RAW_PIZZA.get()), state));
            level.removeBlock(pos, false);
            return InteractionResult.sidedSuccess(level.isClientSide());
        }
        return super.use(state, level, pos, player, hand, result);
    }

    @Override
    public List<ItemStack> getDrops(@NotNull BlockState state, LootParams.@NotNull Builder builder) {
        return PizzaBlock.getReturnItems(state);
    }

    private static boolean applyState(ItemStack stack, BooleanProperty property, Predicate<Item> checker, BlockState state, Player player, BlockPos pos) {
        if (checker.test(stack.getItem()) && !state.getValue(property)) {
            if (stack.getItem().hasCraftingRemainingItem()) {
                Item returnAble = stack.getItem().getCraftingRemainingItem();
                if (returnAble != null) {
                    player.addItem(returnAble.getDefaultInstance());
                }
            }
            if (!player.getAbilities().instabuild) stack.shrink(1);
            player.level().setBlockAndUpdate(pos, state.setValue(property, true));
            return true;
        }
        return false;
    }
}
