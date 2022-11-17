package tech.thatgravyboat.cozy.common.registry;

import com.teamresourceful.resourcefullib.common.recipe.CodecRecipeSerializer;
import com.teamresourceful.resourcefullib.common.recipe.CodecRecipeType;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.item.crafting.*;
import tech.thatgravyboat.cozy.common.recipes.CopyNbtSmeltingRecipe;
import tech.thatgravyboat.cozy.common.recipes.CopyNbtSmokingRecipe;
import tech.thatgravyboat.cozy.common.recipes.CuttingBoardRecipe;

import java.util.function.Supplier;

public class ModRecipes {

    public static final Supplier<CodecRecipeType<CuttingBoardRecipe>> CUTTING_BOARD_RECIPE = registerType("cutting_board", () -> CodecRecipeType.of("cutting_board"));
    public static final Supplier<CodecRecipeSerializer<CuttingBoardRecipe>> CUTTING_BOARD_SERIALIZER = registerSerializer("cutting_board", () -> new CodecRecipeSerializer<>(CUTTING_BOARD_RECIPE.get(), CuttingBoardRecipe::codec));
    public static final Supplier<CodecRecipeSerializer<SmeltingRecipe>> COPY_SMELTING_RECIPE_SERIALIZER = registerSerializer("smelting_copy", () -> new CodecRecipeSerializer<>(RecipeType.SMELTING, CopyNbtSmeltingRecipe::codec));
    public static final Supplier<CodecRecipeSerializer<SmokingRecipe>> COPY_SMOKING_RECIPE_SERIALIZER = registerSerializer("smoking_copy", () -> new CodecRecipeSerializer<>(RecipeType.SMOKING, CopyNbtSmokingRecipe::codec));

    public static void init() {
        //Init class loader
    }

    @ExpectPlatform
    public static <R extends Recipe<?>, T extends RecipeSerializer<R>> Supplier<T> registerSerializer(String id, Supplier<T> type) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <R extends Recipe<?>, T extends RecipeType<R>> Supplier<T> registerType(String id, Supplier<T> type) {
        throw new AssertionError();
    }
}
