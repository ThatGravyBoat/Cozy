package tech.thatgravyboat.cozy.common.items;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tech.thatgravyboat.cozy.common.blocks.pizza.PizzaBlock;

import java.util.List;
import java.util.Set;

public class PizzaItem extends BlockItem implements CozyItemExtension {

    private static final Set<String> ALLOWED_TOPPINGS = Set.of("sauce", "cheese", "chicken", "mushroom", "pepperoni", "sausage");

    public PizzaItem(PizzaBlock block, Properties properties) {
        super(block, properties);
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
}
