package tech.thatgravyboat.cozy.common.registry;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import tech.thatgravyboat.cozy.common.items.MilkBottleItem;
import tech.thatgravyboat.cozy.common.items.PizzaItem;
import tech.thatgravyboat.cozy.common.items.PizzaSliceItem;
import tech.thatgravyboat.cozy.common.items.ReturningFoodItem;
import tech.thatgravyboat.cozy.common.utils.ModUtils;

import java.util.function.Supplier;

import static tech.thatgravyboat.cozy.common.registry.ModItems.registerItem;

public class ModFoods {

    //region Ingredients
    public static final Supplier<Item> DOUGH = registerItem("dough", () -> new BlockItem(ModBlocks.DOUGH.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final Supplier<Item> CREAM = registerItem("cream", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).craftRemainder(Items.BOWL)));
    //endregion

    public static final Supplier<Item> RAW_PIZZA = registerItem("raw_pizza", () -> new PizzaItem(ModBlocks.RAW_PIZZA.get(), new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));
    public static final Supplier<Item> COOKED_PIZZA = registerItem("cooked_pizza", () -> new PizzaItem(ModBlocks.COOKED_PIZZA.get(), new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));
    public static final Supplier<Item> PIZZA_SLICE = registerItem("pizza_slice", () -> new PizzaSliceItem(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));

    public static final Supplier<Item> RAW_BACON = registerItem("raw_bacon", () -> new Item(normal(ModFoodProperties.RAW_BACON)));
    public static final Supplier<Item> COOKED_BACON = registerItem("cooked_bacon", () -> new Item(normal(ModFoodProperties.COOKED_BACON)));

    public static final Supplier<Item> TOMATO_SEEDS = registerItem("tomato_seeds", () -> new BlockItem(ModBlocks.TOMATO_CROP.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final Supplier<Item> TOMATO = registerItem("tomato", () -> new Item(normal(ModFoodProperties.TOMATO)));
    public static final Supplier<Item> TOMATO_SAUCE = registerItem("tomato_sauce", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).craftRemainder(Items.BOWL)));
    public static final Supplier<Item> TOMATO_SWEET_BERRY_SALAD = registerItem("tomato_sweet_berry_salad", () -> new BowlFoodItem(bowl(ModFoodProperties.TOMATO_SWEET_BERRY_SALAD)));

    public static final Supplier<Item> APPLE_PIE = registerItem("apple_pie", () -> new Item(normal(ModFoodProperties.APPLE_PIE)));
    public static final Supplier<Item> CHOCOLATE_PIE = registerItem("chocolate_pie", () -> new Item(normal(ModFoodProperties.CHOCOLATE_PIE)));
    public static final Supplier<Item> GLOWBERRY_PIE = registerItem("glow_berry_pie", () -> new Item(normal(ModFoodProperties.GLOWBERRY_PIE)));
    public static final Supplier<Item> SWEET_BERRY_PIE = registerItem("sweet_berry_pie", () -> new Item(normal(ModFoodProperties.SWEET_BERRY_PIE)));

    public static final Supplier<Item> BACON_AND_EGGS = registerItem("bacon_and_eggs", () -> new ReturningFoodItem(UseAnim.EAT, Items.BOWL, normal(ModFoodProperties.BACON_AND_EGGS)));
    public static final Supplier<Item> COOKED_EGG = registerItem("cooked_egg", () -> new Item(normal(ModFoodProperties.COOKED_EGG)));
    public static final Supplier<Item> EGG_IN_A_CUP = registerItem("egg_in_a_cup", () -> new BowlFoodItem(bowl(ModFoodProperties.EGG_IN_A_CUP)));


    public static final Supplier<Item> BEEF_STEW = registerItem("beef_stew", () -> new BowlFoodItem(bowl(ModFoodProperties.BEEF_STEW)));
    public static final Supplier<Item> CHEESE = registerItem("cheese", () -> new Item(normal(ModFoodProperties.CHEESE)));
    public static final Supplier<Item> CHEESESTEAK = registerItem("cheesesteak", () -> new Item(normal(ModFoodProperties.CHEESESTEAK)));
    public static final Supplier<Item> GRILLED_CHEESE = registerItem("grilled_cheese", () -> new Item(normal(ModFoodProperties.GRILLED_CHEESE)));

    public static final Supplier<Item> CHICKEN_SANDWICH = registerItem("chicken_sandwich", () -> new Item(normal(ModFoodProperties.CHICKEN_SANDWICH)));

    public static final Supplier<Item> CHOCOLATE = registerItem("chocolate", () -> new Item(normal(ModFoodProperties.CHOCOLATE)));
    public static final Supplier<Item> CHOCOLATE_MILK_BOTTLE = registerItem("chocolate_milk_bottle", () -> new MilkBottleItem(normal(ModFoodProperties.CHOCOLATE_MILK_BOTTLE)));
    public static final Supplier<Item> MILK_BOTTLE = registerItem("milk_bottle", () -> new MilkBottleItem(normal(ModFoodProperties.MILK_BOTTLE)));

    public static final Supplier<Item> CANDY_APPLE = registerItem("candy_apple", () -> new ReturningFoodItem(UseAnim.EAT, Items.STICK, normal(ModFoodProperties.CANDY_APPLE)));
    public static final Supplier<Item> GOLDEN_CANDY_APPLE = registerItem("golden_candy_apple", () -> new ReturningFoodItem(UseAnim.EAT, Items.STICK, normal(ModFoodProperties.GOLDEN_APPLE_CANDY)));

    public static final Supplier<Item> BERRIES_AND_CREAM = registerItem("berries_and_cream", () -> new BowlFoodItem(bowl(ModFoodProperties.BERRIES_AND_CREAMS)));
    public static final Supplier<Item> BUTTERSCOTCH = registerItem("butterscotch", () -> new Item(normal(ModFoodProperties.BUTTERSCOTCH)));
    public static final Supplier<Item> BUTTERSCOTCH_CHOCOLATE = registerItem("butterscotch_chocolate", () -> new Item(normal(ModFoodProperties.BUTTERSCOTCH_CHOCOLATE)));
    public static final Supplier<Item> GLOWBERRY_JAM = registerItem("glow_berry_jam", () -> new ReturningFoodItem(UseAnim.DRINK, Items.GLASS_BOTTLE, normal(ModFoodProperties.GLOWBERRY_JAM)));
    public static final Supplier<Item> SWEET_BERRY_JAM = registerItem("sweet_berry_jam", () -> new ReturningFoodItem(UseAnim.DRINK, Items.GLASS_BOTTLE, normal(ModFoodProperties.SWEET_BERRY_JAM)));

    static {
        if (ModUtils.isModLoaded("sprout")) {
            registerItem("peanut_butter", () -> new ReturningFoodItem(UseAnim.DRINK, Items.GLASS_BOTTLE, normal(ModFoodProperties.PEANUT_BUTTER)));
            registerItem("peanut_butter_cookie", () -> new Item(normal(ModFoodProperties.PEANUT_BUTTER_COOKIE)));
            registerItem("pbj", () -> new Item(normal(ModFoodProperties.PBJ)));
        }
    }

    public static void init() {
        //Load class!
    }

    private static Item.Properties bowl(FoodProperties properties) {
        return new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(properties).stacksTo(1);
    }

    private static Item.Properties normal(FoodProperties properties) {
        return new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(properties);
    }
}
