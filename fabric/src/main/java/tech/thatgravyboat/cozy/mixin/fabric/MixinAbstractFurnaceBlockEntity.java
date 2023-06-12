package tech.thatgravyboat.cozy.mixin.fabric;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.thatgravyboat.cozy.common.recipes.CopyNbtSmeltingRecipe;
import tech.thatgravyboat.cozy.common.recipes.CopyNbtSmokingRecipe;

@Mixin(AbstractFurnaceBlockEntity.class)
public class MixinAbstractFurnaceBlockEntity {

    @Inject(
        method = "burn",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/item/crafting/Recipe;getResultItem(Lnet/minecraft/core/RegistryAccess;)Lnet/minecraft/world/item/ItemStack;"
        )
    )
    private static void cozy$onRecipeResult(RegistryAccess registryAccess, Recipe<?> recipe, NonNullList<ItemStack> nonNullList, int i, CallbackInfoReturnable<Boolean> cir) {
        //This is needed because for some reason the smelting recipe doesn't use assemble and just gets the result item without caring.
        //Also, we don't redirect recipe.getResultItem() so that we can keep compat and still be able to do what we need.
        if (recipe instanceof CopyNbtSmeltingRecipe nbtRecipe) {
            nbtRecipe.setLocalContainer(nonNullList.get(0).copy());
        }
        if (recipe instanceof CopyNbtSmokingRecipe nbtRecipe) {
            nbtRecipe.setLocalContainer(nonNullList.get(0).copy());
        }
    }
}
