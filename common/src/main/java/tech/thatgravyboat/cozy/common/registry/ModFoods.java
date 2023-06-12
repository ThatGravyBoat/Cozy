package tech.thatgravyboat.cozy.common.registry;

import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import com.teamresourceful.resourcefullib.common.utils.modinfo.ModInfoUtils;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import tech.thatgravyboat.cozy.common.items.MilkBottleItem;
import tech.thatgravyboat.cozy.common.items.PizzaItem;
import tech.thatgravyboat.cozy.common.items.PizzaSliceItem;
import tech.thatgravyboat.cozy.common.items.ReturningFoodItem;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ModFoods {

    public static final ResourcefulRegistry<Item> FOODS = ResourcefulRegistries.create(ModItems.ITEMS);

    //region Ingredients
    public static final Supplier<Item> DOUGH = ModItems.MISC.register("dough", () -> new BlockItem(ModBlocks.DOUGH.get(), new Item.Properties()));
    public static final Supplier<Item> CREAM = ModItems.MISC.register("cream", () -> new Item(new Item.Properties().craftRemainder(Items.BOWL)));
    //endregion

    public static final Supplier<Item> RAW_PIZZA = FOODS.register("raw_pizza", () -> new PizzaItem(ModBlocks.RAW_PIZZA.get(), new Item.Properties()));
    public static final Supplier<Item> COOKED_PIZZA = FOODS.register("cooked_pizza", () -> new PizzaItem(ModBlocks.COOKED_PIZZA.get(), new Item.Properties()));
    public static final Supplier<Item> PIZZA_SLICE = FOODS.register("pizza_slice", () -> new PizzaSliceItem(new Item.Properties()));

    public static final Supplier<Item> RAW_BACON = FOODS.register("raw_bacon", () -> new Item(normal(ModFoodProperties.RAW_BACON)));
    public static final Supplier<Item> COOKED_BACON = FOODS.register("cooked_bacon", () -> new Item(normal(ModFoodProperties.COOKED_BACON)));

    public static final Supplier<Item> TOMATO_SEEDS = ModItems.MISC.register("tomato_seeds", () -> new BlockItem(ModBlocks.TOMATO_CROP.get(), new Item.Properties()));
    public static final Supplier<Item> TOMATO = FOODS.register("tomato", () -> new Item(normal(ModFoodProperties.TOMATO)));
    public static final Supplier<Item> TOMATO_SAUCE = FOODS.register("tomato_sauce", () -> new Item(new Item.Properties().craftRemainder(Items.BOWL)));
    public static final Supplier<Item> TOMATO_SWEET_BERRY_SALAD = FOODS.register("tomato_sweet_berry_salad", () -> new BowlFoodItem(bowl(ModFoodProperties.TOMATO_SWEET_BERRY_SALAD)));

    public static final Supplier<Item> APPLE_PIE = FOODS.register("apple_pie", () -> new Item(normal(ModFoodProperties.APPLE_PIE)));
    public static final Supplier<Item> CHOCOLATE_PIE = FOODS.register("chocolate_pie", () -> new Item(normal(ModFoodProperties.CHOCOLATE_PIE)));
    public static final Supplier<Item> GLOWBERRY_PIE = FOODS.register("glow_berry_pie", () -> new Item(normal(ModFoodProperties.GLOWBERRY_PIE)));
    public static final Supplier<Item> SWEET_BERRY_PIE = FOODS.register("sweet_berry_pie", () -> new Item(normal(ModFoodProperties.SWEET_BERRY_PIE)));

    public static final Supplier<Item> BACON_AND_EGGS = FOODS.register("bacon_and_eggs", () -> new ReturningFoodItem(UseAnim.EAT, Items.BOWL, normal(ModFoodProperties.BACON_AND_EGGS)));
    public static final Supplier<Item> COOKED_EGG = FOODS.register("cooked_egg", () -> new Item(normal(ModFoodProperties.COOKED_EGG)));
    public static final Supplier<Item> EGG_IN_A_CUP = FOODS.register("egg_in_a_cup", () -> new BowlFoodItem(bowl(ModFoodProperties.EGG_IN_A_CUP)));


    public static final Supplier<Item> BEEF_STEW = FOODS.register("beef_stew", () -> new BowlFoodItem(bowl(ModFoodProperties.BEEF_STEW)));
    public static final Supplier<Item> CHEESE = FOODS.register("cheese", () -> new Item(normal(ModFoodProperties.CHEESE)));
    public static final Supplier<Item> CHEESESTEAK = FOODS.register("cheesesteak", () -> new Item(normal(ModFoodProperties.CHEESESTEAK)));
    public static final Supplier<Item> GRILLED_CHEESE = FOODS.register("grilled_cheese", () -> new Item(normal(ModFoodProperties.GRILLED_CHEESE)));

    public static final Supplier<Item> CHICKEN_SANDWICH = FOODS.register("chicken_sandwich", () -> new Item(normal(ModFoodProperties.CHICKEN_SANDWICH)));

    public static final Supplier<Item> CHOCOLATE = FOODS.register("chocolate", () -> new Item(normal(ModFoodProperties.CHOCOLATE)));
    public static final Supplier<Item> CHOCOLATE_MILK_BOTTLE = FOODS.register("chocolate_milk_bottle", () -> new MilkBottleItem(normal(ModFoodProperties.CHOCOLATE_MILK_BOTTLE)));
    public static final Supplier<Item> MILK_BOTTLE = FOODS.register("milk_bottle", () -> new MilkBottleItem(normal(ModFoodProperties.MILK_BOTTLE)));

    public static final Supplier<Item> CANDY_APPLE = FOODS.register("candy_apple", () -> new ReturningFoodItem(UseAnim.EAT, Items.STICK, normal(ModFoodProperties.CANDY_APPLE)));
    public static final Supplier<Item> GOLDEN_CANDY_APPLE = FOODS.register("golden_candy_apple", () -> new ReturningFoodItem(UseAnim.EAT, Items.STICK, normal(ModFoodProperties.GOLDEN_APPLE_CANDY)));

    public static final Supplier<Item> BERRIES_AND_CREAM = FOODS.register("berries_and_cream", () -> new BowlFoodItem(bowl(ModFoodProperties.BERRIES_AND_CREAMS)));
    public static final Supplier<Item> BUTTERSCOTCH = FOODS.register("butterscotch", () -> new Item(normal(ModFoodProperties.BUTTERSCOTCH)));
    public static final Supplier<Item> BUTTERSCOTCH_CHOCOLATE = FOODS.register("butterscotch_chocolate", () -> new Item(normal(ModFoodProperties.BUTTERSCOTCH_CHOCOLATE)));
    public static final Supplier<Item> GLOWBERRY_JAM = FOODS.register("glow_berry_jam", () -> new ReturningFoodItem(UseAnim.DRINK, Items.GLASS_BOTTLE, normal(ModFoodProperties.GLOWBERRY_JAM)));
    public static final Supplier<Item> SWEET_BERRY_JAM = FOODS.register("sweet_berry_jam", () -> new ReturningFoodItem(UseAnim.DRINK, Items.GLASS_BOTTLE, normal(ModFoodProperties.SWEET_BERRY_JAM)));

    static {
        if (ModInfoUtils.isModLoaded("sprout")) {
            FOODS.register("peanut_butter", () -> new ReturningFoodItem(UseAnim.DRINK, Items.GLASS_BOTTLE, normal(ModFoodProperties.PEANUT_BUTTER)));
            FOODS.register("peanut_butter_cookie", () -> new Item(normal(ModFoodProperties.PEANUT_BUTTER_COOKIE)));
            FOODS.register("pbj", () -> new Item(normal(ModFoodProperties.PBJ)));
        }
    }

    private static Item.Properties bowl(FoodProperties properties) {
        return new Item.Properties().food(properties).stacksTo(1);
    }

    private static Item.Properties normal(FoodProperties properties) {
        return new Item.Properties().food(properties);
    }
}
