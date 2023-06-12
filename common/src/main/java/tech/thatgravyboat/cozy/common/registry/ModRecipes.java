package tech.thatgravyboat.cozy.common.registry;

import com.teamresourceful.resourcefullib.common.recipe.CodecRecipeSerializer;
import com.teamresourceful.resourcefullib.common.recipe.CodecRecipeType;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.crafting.SmokingRecipe;
import tech.thatgravyboat.cozy.Cozy;
import tech.thatgravyboat.cozy.common.recipes.CopyNbtSmeltingRecipe;
import tech.thatgravyboat.cozy.common.recipes.CopyNbtSmokingRecipe;
import tech.thatgravyboat.cozy.common.recipes.CuttingBoardRecipe;

import java.util.function.Supplier;

public class ModRecipes {

    public static final ResourcefulRegistry<RecipeType<?>> RECIPES = ResourcefulRegistries.create(BuiltInRegistries.RECIPE_TYPE, Cozy.MOD_ID);
    public static final ResourcefulRegistry<RecipeSerializer<?>> SERIALIZERS = ResourcefulRegistries.create(BuiltInRegistries.RECIPE_SERIALIZER, Cozy.MOD_ID);

    public static final Supplier<CodecRecipeType<CuttingBoardRecipe>> CUTTING_BOARD_RECIPE = RECIPES.register("cutting_board", () -> CodecRecipeType.of("cutting_board"));
    public static final Supplier<CodecRecipeSerializer<CuttingBoardRecipe>> CUTTING_BOARD_SERIALIZER = SERIALIZERS.register("cutting_board", () -> new CodecRecipeSerializer<>(CUTTING_BOARD_RECIPE.get(), CuttingBoardRecipe::codec));
    public static final Supplier<CodecRecipeSerializer<SmeltingRecipe>> COPY_SMELTING_RECIPE_SERIALIZER = SERIALIZERS.register("smelting_copy", () -> new CodecRecipeSerializer<>(RecipeType.SMELTING, CopyNbtSmeltingRecipe::codec));
    public static final Supplier<CodecRecipeSerializer<SmokingRecipe>> COPY_SMOKING_RECIPE_SERIALIZER = SERIALIZERS.register("smoking_copy", () -> new CodecRecipeSerializer<>(RecipeType.SMOKING, CopyNbtSmokingRecipe::codec));
}
