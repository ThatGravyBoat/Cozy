package tech.thatgravyboat.cozy.common.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamresourceful.resourcefullib.common.codecs.recipes.IngredientCodec;
import com.teamresourceful.resourcefullib.common.codecs.recipes.ItemStackCodec;
import com.teamresourceful.resourcefullib.common.recipe.CodecRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import tech.thatgravyboat.cozy.common.registry.ModRecipes;

public record CuttingBoardRecipe(ResourceLocation id, int uses, boolean copyNbt, Ingredient input, ItemStack output) implements CodecRecipe<Container> {

    public static Codec<CuttingBoardRecipe> codec(ResourceLocation id) {
        return RecordCodecBuilder.create(instance -> instance.group(
            RecordCodecBuilder.point(id),
            Codec.INT.fieldOf("uses").orElse(5).forGetter(CuttingBoardRecipe::uses),
            Codec.BOOL.fieldOf("copyNbt").orElse(false).forGetter(CuttingBoardRecipe::copyNbt),
            IngredientCodec.CODEC.fieldOf("input").forGetter(CuttingBoardRecipe::input),
            ItemStackCodec.CODEC.fieldOf("output").forGetter(CuttingBoardRecipe::output)
        ).apply(instance, CuttingBoardRecipe::new));
    }

    @Override
    public boolean matches(@NotNull Container container, @NotNull Level level) {
        return input.test(container.getItem(0));
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.CUTTING_BOARD_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.CUTTING_BOARD_RECIPE.get();
    }
}