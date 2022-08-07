package tech.thatgravyboat.cozy.common.compat.jei;

import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

/**
 * Taken from Resourceful Bees with permission from myself.
 */
public abstract class BaseCategory<T> implements IRecipeCategory<T> {

    private final RecipeType<T> recipeType;
    private final Component localizedName;
    private final IDrawable background;
    private final IDrawable icon;

    protected BaseCategory(IGuiHelper guiHelper, RecipeType<T> recipeType, Component localizedName, IDrawable background, IDrawable icon) {
        this.recipeType = recipeType;
        this.localizedName = localizedName;
        this.background = background;
        this.icon = icon;
    }

    @Override
    public @NotNull RecipeType<T> getRecipeType() {
        return this.recipeType;
    }

    @Override
    public @NotNull Component getTitle() {
        return localizedName;
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return icon;
    }
}
