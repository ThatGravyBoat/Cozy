package tech.thatgravyboat.cozy.common.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import tech.thatgravyboat.cozy.common.items.AttackDamageItem;
import tech.thatgravyboat.cozy.common.items.HammerItem;
import tech.thatgravyboat.cozy.common.items.PizzaItem;

import java.util.function.Supplier;

public class ModItems {

    //region Tables
    public static final Supplier<Item> ACACIA_TABLE = registerItem("acacia_table", () -> new BlockItem(ModBlocks.ACACIA_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> BIRCH_TABLE = registerItem("birch_table", () -> new BlockItem(ModBlocks.BIRCH_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> CRIMSON_TABLE = registerItem("crimson_table", () -> new BlockItem(ModBlocks.CRIMSON_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> DARK_OAK_TABLE = registerItem("dark_oak_table", () -> new BlockItem(ModBlocks.DARK_OAK_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> JUNGLE_TABLE = registerItem("jungle_table", () -> new BlockItem(ModBlocks.JUNGLE_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> MANGROVE_TABLE = registerItem("mangrove_table", () -> new BlockItem(ModBlocks.MANGROVE_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> OAK_TABLE = registerItem("oak_table", () -> new BlockItem(ModBlocks.OAK_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> SPRUCE_TABLE = registerItem("spruce_table", () -> new BlockItem(ModBlocks.SPRUCE_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> WARPED_TABLE = registerItem("warped_table", () -> new BlockItem(ModBlocks.WARPED_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    //endregion
    //region Chairs
    public static final Supplier<Item> ACACIA_CHAIR = registerItem("acacia_chair", () -> new BlockItem(ModBlocks.ACACIA_CHAIR.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> BIRCH_CHAIR = registerItem("birch_chair", () -> new BlockItem(ModBlocks.BIRCH_CHAIR.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> CRIMSON_CHAIR = registerItem("crimson_chair", () -> new BlockItem(ModBlocks.CRIMSON_CHAIR.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> DARK_OAK_CHAIR = registerItem("dark_oak_chair", () -> new BlockItem(ModBlocks.DARK_OAK_CHAIR.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> JUNGLE_CHAIR = registerItem("jungle_chair", () -> new BlockItem(ModBlocks.JUNGLE_CHAIR.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> MANGROVE_CHAIR = registerItem("mangrove_chair", () -> new BlockItem(ModBlocks.MANGROVE_CHAIR.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> OAK_CHAIR = registerItem("oak_chair", () -> new BlockItem(ModBlocks.OAK_CHAIR.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> SPRUCE_CHAIR = registerItem("spruce_chair", () -> new BlockItem(ModBlocks.SPRUCE_CHAIR.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> WARPED_CHAIR = registerItem("warped_chair", () -> new BlockItem(ModBlocks.WARPED_CHAIR.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    //endregion
    //region Lamps
    public static final Supplier<Item> BLACK_LAMP = registerItem("black_lamp", () -> new BlockItem(ModBlocks.BLACK_LAMP.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> BLUE_LAMP = registerItem("blue_lamp", () -> new BlockItem(ModBlocks.BLUE_LAMP.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> BROWN_LAMP = registerItem("brown_lamp", () -> new BlockItem(ModBlocks.BROWN_LAMP.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> CYAN_LAMP = registerItem("cyan_lamp", () -> new BlockItem(ModBlocks.CYAN_LAMP.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> GRAY_LAMP = registerItem("gray_lamp", () -> new BlockItem(ModBlocks.GRAY_LAMP.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> GREEN_LAMP = registerItem("green_lamp", () -> new BlockItem(ModBlocks.GREEN_LAMP.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> LIGHT_BLUE_LAMP = registerItem("light_blue_lamp", () -> new BlockItem(ModBlocks.LIGHT_BLUE_LAMP.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> LIGHT_GRAY_LAMP = registerItem("light_gray_lamp", () -> new BlockItem(ModBlocks.LIGHT_GRAY_LAMP.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> LIME_LAMP = registerItem("lime_lamp", () -> new BlockItem(ModBlocks.LIME_LAMP.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> MAGENTA_LAMP = registerItem("magenta_lamp", () -> new BlockItem(ModBlocks.MAGENTA_LAMP.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> ORANGE_LAMP = registerItem("orange_lamp", () -> new BlockItem(ModBlocks.ORANGE_LAMP.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> PINK_LAMP = registerItem("pink_lamp", () -> new BlockItem(ModBlocks.PINK_LAMP.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> PURPLE_LAMP = registerItem("purple_lamp", () -> new BlockItem(ModBlocks.PURPLE_LAMP.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> RED_LAMP = registerItem("red_lamp", () -> new BlockItem(ModBlocks.RED_LAMP.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> WHITE_LAMP = registerItem("white_lamp", () -> new BlockItem(ModBlocks.WHITE_LAMP.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Item> YELLOW_LAMP = registerItem("yellow_lamp", () -> new BlockItem(ModBlocks.YELLOW_LAMP.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    //endregion

    //region Cutting Board
    public static final Supplier<Item> KNIFE = registerItem("knife", () -> new AttackDamageItem(1.5D, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).durability(250)));
    public static final Supplier<Item> CUTTING_BOARD = registerItem("cutting_board", () -> new BlockItem(ModBlocks.CUTTING_BOARD.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    //endregion

    public static final Supplier<Item> HAMMER = registerItem("hammer", () -> new HammerItem(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).durability(1000)));


    public static void init() {
        //Init class into class loader
        ModFoods.init();
    }

    @ExpectPlatform
    public static <T extends Item> Supplier<T> registerItem(String id, Supplier<T> item) {
        throw new AssertionError();
    }
}
