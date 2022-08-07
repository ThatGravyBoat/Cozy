package tech.thatgravyboat.cozy.common.blocks.pizza;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tech.thatgravyboat.cozy.common.registry.ModBlocks;

import java.util.List;

public class CookingPizzaBlock extends PizzaBlock implements EntityBlock {

    public CookingPizzaBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, LevelReader levelReader, BlockPos blockPos) {
        return levelReader.getBlockState(blockPos.below()).is(Blocks.SMOKER);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
        return type == ModBlocks.COOKING_PIZZA_ENTITY.get() && !level.isClientSide() ? CookingPizzaBlockEntity::tick : null;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new CookingPizzaBlockEntity(pos, state);
    }

    @Override
    public List<ItemStack> getDrops(@NotNull BlockState state, LootContext.@NotNull Builder builder) {
        return PizzaBlock.getReturnItems(state);
    }
}
