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
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.crafting.SmokingRecipe;
import org.jetbrains.annotations.NotNull;
import tech.thatgravyboat.cozy.common.registry.ModRecipes;

public class CopyNbtSmokingRecipe extends SmokingRecipe implements CodecRecipe<Container> {

    private ItemStack input = null;

    public static Codec<SmokingRecipe> codec(ResourceLocation id) {
        return RecordCodecBuilder.create(instance -> instance.group(
                RecordCodecBuilder.point(id),
                Codec.STRING.fieldOf("group").orElse("").forGetter(SmokingRecipe::getGroup),
                IngredientCodec.CODEC.fieldOf("ingredient").forGetter(recipe -> recipe.getIngredients().get(0)),
                ItemStackCodec.CODEC.fieldOf("result").forGetter(CopyNbtSmokingRecipe::getResult),
                Codec.FLOAT.fieldOf("experience").orElse(0f).forGetter(SmokingRecipe::getExperience),
                Codec.INT.fieldOf("cookingtime").orElse(200).forGetter(SmokingRecipe::getCookingTime)
        ).apply(instance, CopyNbtSmokingRecipe::new));
    }

    public CopyNbtSmokingRecipe(ResourceLocation resourceLocation, String string, Ingredient ingredient, ItemStack result, float f, int i) {
        super(resourceLocation, string, ingredient, result, f, i);
    }

    @Override
    public @NotNull ResourceLocation id() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.COPY_SMOKING_RECIPE_SERIALIZER.get();
    }

    public ItemStack getResultSuper() {
        return super.getResultItem();
    }

    @Override
    public @NotNull ItemStack getResultItem() {
        ItemStack stack = super.getResultItem();
        if (input != null) {
            var tag = input.getTag();
            if (tag != null) stack.setTag(tag);
            input = null;
        }
        return stack;
    }

    //I do this for forge because forge fixes it.
    @Override
    public @NotNull ItemStack assemble(@NotNull Container container) {
        setLocalContainer(container.getItem(0));
        return getResultItem();
    }

    public void setLocalContainer(ItemStack input) {
        this.input = input;
    }

    private static ItemStack getResult(SmokingRecipe recipe) {
        return recipe instanceof CopyNbtSmokingRecipe copyRecipe ? copyRecipe.getResultSuper() : recipe.getResultItem();
    }
}
