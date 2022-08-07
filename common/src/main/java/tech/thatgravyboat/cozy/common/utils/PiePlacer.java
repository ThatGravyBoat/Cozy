package tech.thatgravyboat.cozy.common.utils;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import tech.thatgravyboat.cozy.common.registry.ModBlocks;
import tech.thatgravyboat.cozy.common.registry.ModFoods;

public class PiePlacer {

    public static final BiMap<Item, Block> PIES = HashBiMap.create();

    public static void init() {
        PIES.put(Items.PUMPKIN_PIE, ModBlocks.PUMPKIN_PIE.get());
        PIES.put(ModFoods.APPLE_PIE.get(), ModBlocks.APPLE_PIE.get());
        PIES.put(ModFoods.CHOCOLATE_PIE.get(), ModBlocks.CHOCOLATE_PIE.get());
        PIES.put(ModFoods.GLOWBERRY_PIE.get(), ModBlocks.GLOWBERRY_PIE.get());
        PIES.put(ModFoods.SWEET_BERRY_PIE.get(), ModBlocks.SWEET_BERRY_PIE.get());
    }

    public static boolean place(InteractionHand hand, Player player, BlockHitResult result) {
        if (!player.isShiftKeyDown()) return false;
        ItemStack stack = player.getItemInHand(hand);
        Block block = PiePlacer.PIES.get(stack.getItem());
        if (block == null) return false;
        BlockPlaceContext context = new BlockPlaceContext(player, hand, stack, result);
        BlockState state = block.defaultBlockState();
        if (context.canPlace() && context.getLevel().setBlock(context.getClickedPos(), state, 11)) {
            BlockPos pos = context.getClickedPos();
            Level level = context.getLevel();
            SoundType soundType = state.getSoundType();

            if (player instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.PLACED_BLOCK.trigger(serverPlayer, pos, stack);
            }

            level.playSound(player, pos, state.getSoundType().getPlaceSound(), SoundSource.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F);
            level.gameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Context.of(player, state));

            if (!player.getAbilities().instabuild) stack.shrink(1);
            return true;
        }
        return false;
    }
}
