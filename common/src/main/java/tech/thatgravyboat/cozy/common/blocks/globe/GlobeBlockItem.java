package tech.thatgravyboat.cozy.common.blocks.globe;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GlobeBlockItem extends BlockItem {

    public GlobeBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getPlayer() != null && !context.getPlayer().isShiftKeyDown()) {
            return InteractionResult.FAIL;
        }
        return super.useOn(context);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.hasTag() && stack.getOrCreateTag().contains("BlockEntityTag")) {
            return super.use(level, player, hand);
        }
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return super.isFoil(stack) || (stack.hasTag() && stack.getOrCreateTag().contains("BlockEntityTag"));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, level, list, flag);
        if (stack.hasTag() && stack.getOrCreateTag().contains("BlockEntityTag")) {
            BlockPos pos = NbtUtils.readBlockPos(stack.getOrCreateTag().getCompound("BlockEntityTag").getCompound("GlobePos"));
            list.add(Component.translatable("gui.cozy.item.globe.location", pos.toShortString()));
        }
    }

    @Override
    public UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return 16;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, @NotNull Level level, @NotNull LivingEntity entity) {
        if (!stack.hasTag() || !stack.getOrCreateTag().contains("BlockEntityTag")) {
            if (!level.isClientSide) {
                GlobeData data = GlobeData.fromLevel(level, entity.blockPosition());
                stack.getOrCreateTag().put("BlockEntityTag", data.toNbt(new CompoundTag()));
                return stack;
            }
        }
        return super.finishUsingItem(stack, level, entity);
    }
}
