package tech.thatgravyboat.cozy.common.items;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;

public interface CozyItemExtension {

    default FoodProperties getFoodProperties(ItemStack stack) {
        return null;
    }
}
