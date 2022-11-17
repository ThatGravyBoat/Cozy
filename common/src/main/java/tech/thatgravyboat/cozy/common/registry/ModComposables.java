package tech.thatgravyboat.cozy.common.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.level.ItemLike;
import org.apache.commons.lang3.NotImplementedException;

public class ModComposables {

    public static void init() {
        register(0.3F, ModFoods.TOMATO_SEEDS.get());
        register(0.3F, ModFoods.TOMATO.get());
        register(0.3F, ModFoods.CHEESE.get());
        register(0.3F, ModFoods.BUTTERSCOTCH.get());
        register(0.4F, ModFoods.BUTTERSCOTCH_CHOCOLATE.get());
        register(0.5F, ModFoods.PIZZA_SLICE.get());
        register(0.5F, ModFoods.GRILLED_CHEESE.get());
        register(0.5F, ModFoods.COOKED_BACON.get());
        register(0.5F, ModFoods.COOKED_EGG.get());
        register(0.75F, ModFoods.COOKED_PIZZA.get());
        register(1F, ModFoods.APPLE_PIE.get());
        register(1F, ModFoods.SWEET_BERRY_PIE.get());
        register(1F, ModFoods.CHOCOLATE_PIE.get());
        register(1F, ModFoods.GLOWBERRY_PIE.get());
    }

    @ExpectPlatform
    public static void register(float value, ItemLike... items) {
        throw new NotImplementedException();
    }
}
