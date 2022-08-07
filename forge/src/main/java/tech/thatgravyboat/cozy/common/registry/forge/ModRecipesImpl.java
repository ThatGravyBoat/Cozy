package tech.thatgravyboat.cozy.common.registry.forge;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import tech.thatgravyboat.cozy.Cozy;

import java.util.function.Supplier;

public class ModRecipesImpl {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Cozy.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, Cozy.MOD_ID);

    public static <R extends Recipe<?>, T extends RecipeSerializer<R>> Supplier<T> registerSerializer(String id, Supplier<T> type) {
        return SERIALIZERS.register(id, type);
    }

    public static <R extends Recipe<?>, T extends RecipeType<R>> Supplier<T> registerType(String id, Supplier<T> type) {
        return TYPES.register(id, type);
    }
}
