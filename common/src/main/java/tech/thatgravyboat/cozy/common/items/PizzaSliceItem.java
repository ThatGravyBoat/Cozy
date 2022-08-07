package tech.thatgravyboat.cozy.common.items;

import dev.architectury.injectables.annotations.PlatformOnly;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tech.thatgravyboat.cozy.common.blocks.pizza.PizzaBlock;

import java.util.List;
import java.util.Set;

public class PizzaSliceItem extends Item implements CozyItemExtension {

    private static final Set<String> ALLOWED_TOPPINGS = Set.of("sauce", "cheese", "chicken", "mushroom", "pepperoni", "sausage");

    public PizzaSliceItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        if (stack.hasTag() && stack.getTag().contains("BlockStateTag", Tag.TAG_COMPOUND)) {
            list.add(Component.translatable("block.cozy.pizza.tooltip.toppings").withStyle(ChatFormatting.GRAY));
            CompoundTag tag = stack.getTag().getCompound("BlockStateTag");
            tag.getAllKeys()
                    .stream()
                    .filter(ALLOWED_TOPPINGS::contains)
                    .filter(key -> tag.getString(key).equals("true"))
                    .map(key -> "block.cozy.pizza.tooltip.topping." + key)
                    .map(Component::translatable)
                    .map(component -> component.withStyle(ChatFormatting.GRAY))
                    .forEachOrdered(list::add);
        }
    }

    @Override
    public boolean isEdible() {
        return true;
    }

    @Nullable
    @Override
    public FoodProperties getFoodProperties() {
        return new FoodProperties.Builder().nutrition(2).build();
    }

    @SuppressWarnings("unused")
    @PlatformOnly("forge")
    public FoodProperties getFoodProperties(ItemStack stack, @Nullable LivingEntity entity) {
        return getFoodProperties(stack);
    }

    @Override
    public FoodProperties getFoodProperties(ItemStack stack) {
        if (!stack.hasTag() || !stack.getTag().contains("BlockStateTag")) {
            return getFoodProperties();
        }
        CompoundTag tag = stack.getTag().getCompound("BlockStateTag");
        var props = PizzaBlock.determineFood(
                hasValue(tag, "sauce"),
                hasValue(tag, "cheese"),
                hasValue(tag, "chicken"),
                hasValue(tag, "mushroom"),
                hasValue(tag, "pepperoni"),
                hasValue(tag, "sausage")
        );
        return new FoodProperties.Builder().nutrition(props.getFirst()).saturationMod(props.getSecond()).build();
    }

    private static boolean hasValue(CompoundTag tag, String key) {
        return tag.contains(key) && "true".equals(tag.getString(key));
    }
}
