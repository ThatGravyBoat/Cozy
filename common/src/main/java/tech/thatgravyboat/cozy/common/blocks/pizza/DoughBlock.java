package tech.thatgravyboat.cozy.common.blocks.pizza;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import tech.thatgravyboat.cozy.common.registry.ModBlocks;

public class DoughBlock extends Block {

    public static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);

    public DoughBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult result) {
        level.setBlockAndUpdate(pos, ModBlocks.PIZZA.get().defaultBlockState());
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    public void fallOn(Level level, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull Entity entity, float f) {
        if (!level.isClientSide && level.random.nextFloat() < f - 0.5F && entity instanceof LivingEntity && (entity instanceof Player || level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) && entity.getBbWidth() * entity.getBbWidth() * entity.getBbHeight() > 0.512F) {
            level.setBlockAndUpdate(pos, ModBlocks.PIZZA.get().defaultBlockState());
        }

        super.fallOn(level, state, pos, entity, f);
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, LevelReader levelReader, BlockPos blockPos) {
        return !levelReader.getBlockState(blockPos.below()).is(Blocks.SMOKER);
    }

    @Override
    public VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE;
    }
}
