package tech.thatgravyboat.cozy;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;
import tech.thatgravyboat.cozy.common.registry.*;

import java.util.function.Consumer;

public class Cozy {
    public static final String MOD_ID = "cozy";

    public static void init() {
        ModBlocks.BLOCKS.init();
        ModBlocks.BLOCK_ENTITIES.init();
        ModItems.ITEMS.init();
        ModFoods.FOODS.init();
        ModEntities.ENTITIES.init();
        ModRecipes.RECIPES.init();
        ModRecipes.SERIALIZERS.init();
    }

    public static void initCreativeTabContents(ResourceKey<CreativeModeTab> tab, Consumer<ItemLike> adder) {
        if (tab.equals(ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation("food_and_drinks")))) {
            ModFoods.FOODS.boundStream().forEach(adder);
        } else if (tab.equals(ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation("functional_blocks")))) {
            ModItems.DECO.boundStream().forEach(adder);
        } else if (tab.equals(ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation("ingredients")))) {
            ModItems.MISC.boundStream().forEach(adder);
        } else if (tab.equals(ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation("tools_and_utilities")))) {
            adder.accept(ModItems.KNIFE.get());
            adder.accept(ModItems.HAMMER.get());
        }
    }
}