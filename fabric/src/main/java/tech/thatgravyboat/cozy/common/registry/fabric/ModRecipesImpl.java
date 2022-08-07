package tech.thatgravyboat.cozy.common.registry.fabric;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import tech.thatgravyboat.cozy.Cozy;

import java.util.function.Supplier;

public class ModRecipesImpl {
    public static <R extends Recipe<?>, T extends RecipeSerializer<R>> Supplier<T> registerSerializer(String id, Supplier<T> type) {
        var output = Registry.register(Registry.RECIPE_SERIALIZER, new ResourceLocation(Cozy.MOD_ID, id), type.get());
        return () -> output;
    }

    public static <R extends Recipe<?>, T extends RecipeType<R>> Supplier<T> registerType(String id, Supplier<T> type) {
        var output = Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(Cozy.MOD_ID, id), type.get());
        return () -> output;
    }
}
