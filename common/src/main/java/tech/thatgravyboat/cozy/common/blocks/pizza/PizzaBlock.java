package tech.thatgravyboat.cozy.common.blocks.pizza;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import tech.thatgravyboat.cozy.common.registry.ModFoods;
import tech.thatgravyboat.cozy.common.registry.ModItems;

import java.util.ArrayList;
import java.util.List;

public class PizzaBlock extends Block {

    public static final VoxelShape TWO_SHAPE = Block.box(1.0D, 0.0D, 8.0D, 15.0D, 1.0D, 15.0D);
    public static final VoxelShape ONE_SHAPE = Block.box(8.0D, 0.0D, 8.0D, 15.0D, 1.0D, 15.0D);
    public static final VoxelShape THREE_SHAPE = Shapes.join(TWO_SHAPE, Block.box(8.0D, 0.0D, 1.0D, 15.0D, 1.0D, 8.0D), BooleanOp.OR);
    public static final VoxelShape FULL_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 1.0D, 15.0D);

    public static final BooleanProperty SAUCE = BooleanProperty.create("sauce");
    public static final BooleanProperty CHEESE = BooleanProperty.create("cheese");
    public static final BooleanProperty CHICKEN = BooleanProperty.create("chicken");
    public static final BooleanProperty MUSHROOM = BooleanProperty.create("mushroom");
    public static final BooleanProperty PEPPERONI = BooleanProperty.create("pepperoni");
    public static final BooleanProperty SAUSAGE = BooleanProperty.create("sausage");

    public PizzaBlock(Properties properties) {
        super(properties);
        var blockState = this.stateDefinition.any()
                .setValue(SAUCE, false)
                .setValue(CHEESE, false)
                .setValue(CHICKEN, false)
                .setValue(MUSHROOM, false)
                .setValue(PEPPERONI, false)
                .setValue(SAUSAGE, false);
        this.registerDefaultState(blockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SAUCE, CHEESE, CHICKEN, MUSHROOM, PEPPERONI, SAUSAGE);
    }

    @Override
    public VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return FULL_SHAPE;
    }

    public static List<ItemStack> getReturnItems(BlockState state) {
        List<ItemStack> items = new ArrayList<>();
        if (state.getValue(CHEESE)) items.add(ModFoods.CHEESE.get().getDefaultInstance());
        if (state.getValue(CHICKEN)) items.add(Items.COOKED_CHICKEN.getDefaultInstance());
        if (state.getValue(MUSHROOM)) items.add(Items.BROWN_MUSHROOM.getDefaultInstance());
        if (state.getValue(PEPPERONI)) items.add(Items.PORKCHOP.getDefaultInstance());
        if (state.getValue(SAUSAGE)) items.add(Items.COOKED_BEEF.getDefaultInstance());
        items.add(ModFoods.DOUGH.get().getDefaultInstance());
        return items;
    }

    public static Pair<Integer, Float> determineFood(boolean sauce, boolean cheese, boolean chicken, boolean mushroom, boolean pepperoni, boolean sausage) {
        double hunger = 2D;
        float saturation = 0.05f;
        if (sauce) hunger += 0.5D;
        if (cheese) { hunger += 1D; saturation += 0.05f; }
        if (chicken) { hunger += 2D; saturation += 0.1f; }
        if (mushroom) hunger += 1D;
        if (pepperoni) { hunger += 1.5D; saturation += 0.1f; }
        if (sausage) { hunger += 2D; saturation += 0.1f; }
        return Pair.of((int)Math.floor(hunger), saturation);
    }

    public static ItemStack createPizzaFromState(ItemStack stack, BlockState state) {
        CompoundTag tag = new CompoundTag();
        CompoundTag blockStateTag = new CompoundTag();
        blockStateTag.putString("sauce", state.getValue(SAUCE) ? "true" : "false");
        blockStateTag.putString("cheese", state.getValue(CHEESE) ? "true" : "false");
        blockStateTag.putString("chicken", state.getValue(CHICKEN) ? "true" : "false");
        blockStateTag.putString("mushroom", state.getValue(MUSHROOM) ? "true" : "false");
        blockStateTag.putString("pepperoni", state.getValue(PEPPERONI) ? "true" : "false");
        blockStateTag.putString("sausage", state.getValue(SAUSAGE) ? "true" : "false");
        tag.put("BlockStateTag", blockStateTag);
        stack.setTag(tag);
        return stack;
    }
}
