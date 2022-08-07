package tech.thatgravyboat.cozy.common.blocks.pizza;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SmokerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import tech.thatgravyboat.cozy.common.registry.ModBlocks;

public class CookingPizzaBlockEntity extends BlockEntity {

    private int ticks;

    public CookingPizzaBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlocks.COOKING_PIZZA_ENTITY.get(), blockPos, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, BlockEntity entity) {
        if (entity instanceof CookingPizzaBlockEntity cookingPizzaBlockEntity) {
            BlockState below = level.getBlockState(pos.below());
            if (below.hasProperty(SmokerBlock.LIT) && below.getValue(SmokerBlock.LIT) && cookingPizzaBlockEntity.ticks++ >= 300) {
                level.setBlockAndUpdate(pos, ModBlocks.COOKED_PIZZA.get().withPropertiesOf(state));
            }
        }
    }
}
