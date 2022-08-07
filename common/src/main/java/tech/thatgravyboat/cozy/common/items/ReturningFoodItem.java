package tech.thatgravyboat.cozy.common.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ReturningFoodItem extends Item {

    private final UseAnim animation;

    public ReturningFoodItem(UseAnim animation, Item returnItem, Properties settings) {
        super(settings.craftRemainder(returnItem));
        this.animation = animation;
    }

    @Override
    public ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level world, @NotNull LivingEntity user) {
        super.finishUsingItem(stack, world, user);
        if (user instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
        }

        ItemStack itemStack = new ItemStack(this.getCraftingRemainingItem());

        if (stack.isEmpty()) return itemStack;

        if (user instanceof Player playerEntity && !playerEntity.getAbilities().instabuild) {
            if (!playerEntity.getInventory().add(itemStack)) {
                playerEntity.drop(itemStack, false);
            }
        }

        return stack;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand interactionHand) {
        return ItemUtils.startUsingInstantly(level, player, interactionHand);
    }

    @Override
    public UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return this.animation;
    }
}
