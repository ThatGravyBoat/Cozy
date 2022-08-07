package tech.thatgravyboat.cozy.common.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.NotNull;
import tech.thatgravyboat.cozy.Cozy;
import tech.thatgravyboat.cozy.common.registry.ModItems;
import tech.thatgravyboat.cozy.common.registry.ModRecipes;

@JeiPlugin
public class ModJeiPlugin implements IModPlugin {

    @Override
    public void registerCategories(@NotNull IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CuttingBoardCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModItems.CUTTING_BOARD.get()), CuttingBoardCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModItems.KNIFE.get()), CuttingBoardCategory.RECIPE_TYPE);
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        ClientLevel clientWorld = Minecraft.getInstance().level;
        if (clientWorld != null) {
            RecipeManager recipeManager = clientWorld.getRecipeManager();
            registration.addRecipes(CuttingBoardCategory.RECIPE_TYPE, recipeManager.getAllRecipesFor(ModRecipes.CUTTING_BOARD_RECIPE.get()));
        }
    }

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Cozy.MOD_ID, "jei");
    }
}
