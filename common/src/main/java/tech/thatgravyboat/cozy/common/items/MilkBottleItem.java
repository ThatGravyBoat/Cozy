package tech.thatgravyboat.cozy.common.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import tech.thatgravyboat.cozy.mixin.MobEffectInstanceAccessor;

public class MilkBottleItem extends ReturningFoodItem {
    public MilkBottleItem(Properties settings) {
        super(UseAnim.DRINK, Items.GLASS_BOTTLE, settings);
    }

    @Override
    public ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level world, @NotNull LivingEntity user) {
        user.getActiveEffects()
                .stream()
                .filter(effect -> !effect.getEffect().isBeneficial())
                .forEachOrdered(effect -> ((MobEffectInstanceAccessor)effect).setDuration((int) (effect.getDuration() * 0.9f)));
        return super.finishUsingItem(stack, world, user);
    }


}
