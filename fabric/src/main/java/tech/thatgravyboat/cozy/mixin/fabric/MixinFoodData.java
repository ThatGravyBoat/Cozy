package tech.thatgravyboat.cozy.mixin.fabric;

import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.thatgravyboat.cozy.common.items.CozyItemExtension;

@Mixin(FoodData.class)
public abstract class MixinFoodData {

    @Shadow public abstract void eat(int i, float f);

    @Inject(method = "eat(Lnet/minecraft/world/item/Item;Lnet/minecraft/world/item/ItemStack;)V", at = @At("HEAD"), cancellable = true)
    public void onEat(Item item, ItemStack itemStack, CallbackInfo ci) {
        if (item.isEdible() && item instanceof CozyItemExtension extension) {
            FoodProperties properties = extension.getFoodProperties(itemStack);
            this.eat(properties.getNutrition(), properties.getSaturationModifier());
            ci.cancel();
        }
    }
}
