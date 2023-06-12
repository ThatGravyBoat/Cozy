package tech.thatgravyboat.cozy.common.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamresourceful.resourcefullib.common.codecs.recipes.IngredientCodec;
import com.teamresourceful.resourcefullib.common.codecs.recipes.ItemStackCodec;
import com.teamresourceful.resourcefullib.common.recipe.CodecRecipe;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import org.jetbrains.annotations.NotNull;
import tech.thatgravyboat.cozy.common.registry.ModRecipes;

public class CopyNbtSmeltingRecipe extends SmeltingRecipe implements CodecRecipe<Container> {

    private ItemStack input = null;

    public static Codec<SmeltingRecipe> codec(ResourceLocation id) {
        return RecordCodecBuilder.create(instance -> instance.group(
                RecordCodecBuilder.point(id),
                Codec.STRING.fieldOf("group").orElse("").forGetter(SmeltingRecipe::getGroup),
                IngredientCodec.CODEC.fieldOf("ingredient").forGetter(recipe -> recipe.getIngredients().get(0)),
                ItemStackCodec.CODEC.fieldOf("result").forGetter(CopyNbtSmeltingRecipe::getResult),
                Codec.FLOAT.fieldOf("experience").orElse(0f).forGetter(SmeltingRecipe::getExperience),
                Codec.INT.fieldOf("cookingtime").orElse(200).forGetter(SmeltingRecipe::getCookingTime)
        ).apply(instance, CopyNbtSmeltingRecipe::new));
    }

    public CopyNbtSmeltingRecipe(ResourceLocation resourceLocation, String string, Ingredient ingredient, ItemStack result, float f, int i) {
        super(resourceLocation, string, CookingBookCategory.FOOD, ingredient, result, f, i);
    }

    @Override
    public @NotNull ResourceLocation id() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.COPY_SMELTING_RECIPE_SERIALIZER.get();
    }

    @Override
    public @NotNull ItemStack getResultItem(@NotNull RegistryAccess access) {
        ItemStack stack = super.getResultItem(access);
        if (input != null) {
            var tag = input.getTag();
            if (tag != null) stack.setTag(tag);
            input = null;
        }
        return stack;
    }

    //I do this for forge because forge fixes it.
    @Override
    public @NotNull ItemStack assemble(Container container, @NotNull RegistryAccess access) {
        setLocalContainer(container.getItem(0));
        return getResultItem(access);
    }

    public void setLocalContainer(ItemStack input) {
        this.input = input;
    }

    private static ItemStack getResult(SmeltingRecipe recipe) {
        return recipe instanceof CopyNbtSmeltingRecipe copyRecipe ? copyRecipe.result : ItemStack.EMPTY;
    }
}
