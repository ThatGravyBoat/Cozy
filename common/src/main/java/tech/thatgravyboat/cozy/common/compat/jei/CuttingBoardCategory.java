package tech.thatgravyboat.cozy.common.compat.jei;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.teamresourceful.resourcefullib.client.CloseablePoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import tech.thatgravyboat.cozy.Cozy;
import tech.thatgravyboat.cozy.common.recipes.CuttingBoardRecipe;
import tech.thatgravyboat.cozy.common.registry.ModBlocks;
import tech.thatgravyboat.cozy.common.registry.ModItems;

public class CuttingBoardCategory extends BaseCategory<CuttingBoardRecipe> {

    private static final ItemStack CUTTING_BOARD = new ItemStack(ModBlocks.CUTTING_BOARD.get());
    public static final ResourceLocation ID = new ResourceLocation(Cozy.MOD_ID, "cutting_board");
    public static final RecipeType<CuttingBoardRecipe> RECIPE_TYPE = new RecipeType<>(ID, CuttingBoardRecipe.class);
    public static final ResourceLocation GUI_BACK = new ResourceLocation(Cozy.MOD_ID, "textures/gui/cutting_board.png");


    protected CuttingBoardCategory(IGuiHelper guiHelper) {
        super(guiHelper, RECIPE_TYPE,
                Component.translatable("gui.cozy.jei.cutting_board"),
                guiHelper.drawableBuilder(GUI_BACK, 0, 0, 74, 59).build(),
                guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.CUTTING_BOARD.get())));
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull CuttingBoardRecipe recipe, @NotNull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 29, 6)
                .addIngredients(recipe.input())
                .setSlotName("input");
        builder.addSlot(RecipeIngredientRole.OUTPUT, 29, 38)
                .addIngredient(VanillaTypes.ITEM_STACK, recipe.output())
                .setSlotName("output");

        builder.addSlot(RecipeIngredientRole.CATALYST, 56, 38)
                .addIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModItems.KNIFE.get(), recipe.uses()))
                .addTooltipCallback((view, tooltip) -> tooltip.add(Component.translatable("gui.cozy.jei.cutting_board.chops", recipe.uses())))
                .setSlotName("knife");
    }

    @Override
    public void draw(@NotNull CuttingBoardRecipe recipe, @NotNull IRecipeSlotsView view, @NotNull GuiGraphics graphics, double mouseX, double mouseY) {
        PoseStack modelViewStack = RenderSystem.getModelViewStack();
        try (var stack = new CloseablePoseStack(modelViewStack)) {
            modelViewStack.mulPoseMatrix(graphics.pose().last().pose());
            RenderSystem.enableDepthTest();

            stack.translate(22, 26, -50);
            stack.scale(2, 2, 1);
            graphics.renderItem(CUTTING_BOARD, 0, 0);
            RenderSystem.disableBlend();
        }
        RenderSystem.applyModelViewMatrix();
    }
}
