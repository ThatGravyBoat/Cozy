package tech.thatgravyboat.cozy.common.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {

    public static final FoodProperties APPLE_PIE = new FoodProperties.Builder().nutrition(8).saturationMod(0.3F).build();
    public static final FoodProperties CHOCOLATE_PIE = new FoodProperties.Builder().nutrition(8).saturationMod(0.3F).build();
    public static final FoodProperties GLOWBERRY_PIE = new FoodProperties.Builder().nutrition(8).saturationMod(0.3F).build();
    public static final FoodProperties SWEET_BERRY_PIE = new FoodProperties.Builder().nutrition(8).saturationMod(0.3F).build();

    public static final FoodProperties BACON_AND_EGGS = new FoodProperties.Builder().nutrition(6).saturationMod(0.5F).build();
    public static final FoodProperties COOKED_BACON = new FoodProperties.Builder().nutrition(4).saturationMod(0.4F).build();
    public static final FoodProperties RAW_BACON = new FoodProperties.Builder().nutrition(2).saturationMod(0.15F).build();
    public static final FoodProperties BEEF_STEW = new FoodProperties.Builder().nutrition(12).saturationMod(0.6F).build();
    public static final FoodProperties CHOCOLATE = new FoodProperties.Builder().nutrition(4).saturationMod(0.2F).build();
    public static final FoodProperties BUTTERSCOTCH = new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).build();
    public static final FoodProperties BUTTERSCOTCH_CHOCOLATE = new FoodProperties.Builder().nutrition(7).saturationMod(0.3F).build();
    public static final FoodProperties CANDY_APPLE = new FoodProperties.Builder().nutrition(6).saturationMod(0.4F).build();
    public static final FoodProperties GOLDEN_APPLE_CANDY = new FoodProperties.Builder().nutrition(7).saturationMod(0.4F).effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1), 1.0F).effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 0), 1.0F).alwaysEat().build();
    public static final FoodProperties COOKED_EGG = new FoodProperties.Builder().nutrition(3).saturationMod(0.2F).build();
    public static final FoodProperties EGG_IN_A_CUP = new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build();
    public static final FoodProperties SWEET_BERRY_JAM = new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build();
    public static final FoodProperties GLOWBERRY_JAM = new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build();
    public static final FoodProperties CHEESE = new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build();
    public static final FoodProperties CHEESESTEAK = new FoodProperties.Builder().nutrition(13).saturationMod(0.6F).build();
    public static final FoodProperties GRILLED_CHEESE = new FoodProperties.Builder().nutrition(6).saturationMod(0.4F).build();
    public static final FoodProperties CHICKEN_SANDWICH = new FoodProperties.Builder().nutrition(8).saturationMod(0.4F).build();

    public static final FoodProperties BERRIES_AND_CREAMS = new FoodProperties.Builder().nutrition(6).saturationMod(0.3F).build();
    public static final FoodProperties CHOCOLATE_MILK_BOTTLE = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).build();
    public static final FoodProperties MILK_BOTTLE = new FoodProperties.Builder().nutrition(1).build();
    public static final FoodProperties TOMATO = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).build();
    public static final FoodProperties TOMATO_SWEET_BERRY_SALAD = new FoodProperties.Builder().nutrition(6).saturationMod(0.3F).build();

    public static final FoodProperties PEANUT_BUTTER_COOKIE = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).build();
    public static final FoodProperties PEANUT_BUTTER = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).build();
    public static final FoodProperties PBJ = new FoodProperties.Builder().nutrition(6).saturationMod(0.2F).build();

}
