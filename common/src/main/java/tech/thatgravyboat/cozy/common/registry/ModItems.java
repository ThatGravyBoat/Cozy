package tech.thatgravyboat.cozy.common.registry;

import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import tech.thatgravyboat.cozy.Cozy;
import tech.thatgravyboat.cozy.common.items.AttackDamageItem;
import tech.thatgravyboat.cozy.common.items.HammerItem;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ModItems {

    public static final ResourcefulRegistry<Item> ITEMS = ResourcefulRegistries.create(BuiltInRegistries.ITEM, Cozy.MOD_ID);
    public static final ResourcefulRegistry<Item> MISC = ResourcefulRegistries.create(ITEMS);
    public static final ResourcefulRegistry<Item> DECO = ResourcefulRegistries.create(ITEMS);

    //region Tables
    public static final Supplier<Item> ACACIA_TABLE = DECO.register("acacia_table", () -> new BlockItem(ModBlocks.ACACIA_TABLE.get(), new Item.Properties()));
    public static final Supplier<Item> BIRCH_TABLE = DECO.register("birch_table", () -> new BlockItem(ModBlocks.BIRCH_TABLE.get(), new Item.Properties()));
    public static final Supplier<Item> CRIMSON_TABLE = DECO.register("crimson_table", () -> new BlockItem(ModBlocks.CRIMSON_TABLE.get(), new Item.Properties()));
    public static final Supplier<Item> DARK_OAK_TABLE = DECO.register("dark_oak_table", () -> new BlockItem(ModBlocks.DARK_OAK_TABLE.get(), new Item.Properties()));
    public static final Supplier<Item> JUNGLE_TABLE = DECO.register("jungle_table", () -> new BlockItem(ModBlocks.JUNGLE_TABLE.get(), new Item.Properties()));
    public static final Supplier<Item> MANGROVE_TABLE = DECO.register("mangrove_table", () -> new BlockItem(ModBlocks.MANGROVE_TABLE.get(), new Item.Properties()));
    public static final Supplier<Item> OAK_TABLE = DECO.register("oak_table", () -> new BlockItem(ModBlocks.OAK_TABLE.get(), new Item.Properties()));
    public static final Supplier<Item> SPRUCE_TABLE = DECO.register("spruce_table", () -> new BlockItem(ModBlocks.SPRUCE_TABLE.get(), new Item.Properties()));
    public static final Supplier<Item> WARPED_TABLE = DECO.register("warped_table", () -> new BlockItem(ModBlocks.WARPED_TABLE.get(), new Item.Properties()));
    public static final Supplier<Item> CHERRY_TABLE = DECO.register("cherry_table", () -> new BlockItem(ModBlocks.CHERRY_TABLE.get(), new Item.Properties()));
    public static final Supplier<Item> BAMBOO_TABLE = DECO.register("bamboo_table", () -> new BlockItem(ModBlocks.BAMBOO_TABLE.get(), new Item.Properties()));
    //endregion
    //region Chairs
    public static final Supplier<Item> ACACIA_CHAIR = DECO.register("acacia_chair", () -> new BlockItem(ModBlocks.ACACIA_CHAIR.get(), new Item.Properties()));
    public static final Supplier<Item> BIRCH_CHAIR = DECO.register("birch_chair", () -> new BlockItem(ModBlocks.BIRCH_CHAIR.get(), new Item.Properties()));
    public static final Supplier<Item> CRIMSON_CHAIR = DECO.register("crimson_chair", () -> new BlockItem(ModBlocks.CRIMSON_CHAIR.get(), new Item.Properties()));
    public static final Supplier<Item> DARK_OAK_CHAIR = DECO.register("dark_oak_chair", () -> new BlockItem(ModBlocks.DARK_OAK_CHAIR.get(), new Item.Properties()));
    public static final Supplier<Item> JUNGLE_CHAIR = DECO.register("jungle_chair", () -> new BlockItem(ModBlocks.JUNGLE_CHAIR.get(), new Item.Properties()));
    public static final Supplier<Item> MANGROVE_CHAIR = DECO.register("mangrove_chair", () -> new BlockItem(ModBlocks.MANGROVE_CHAIR.get(), new Item.Properties()));
    public static final Supplier<Item> OAK_CHAIR = DECO.register("oak_chair", () -> new BlockItem(ModBlocks.OAK_CHAIR.get(), new Item.Properties()));
    public static final Supplier<Item> SPRUCE_CHAIR = DECO.register("spruce_chair", () -> new BlockItem(ModBlocks.SPRUCE_CHAIR.get(), new Item.Properties()));
    public static final Supplier<Item> WARPED_CHAIR = DECO.register("warped_chair", () -> new BlockItem(ModBlocks.WARPED_CHAIR.get(), new Item.Properties()));
    public static final Supplier<Item> CHERRY_CHAIR = DECO.register("cherry_chair", () -> new BlockItem(ModBlocks.CHERRY_CHAIR.get(), new Item.Properties()));
    public static final Supplier<Item> BAMBOO_CHAIR = DECO.register("bamboo_chair", () -> new BlockItem(ModBlocks.BAMBOO_CHAIR.get(), new Item.Properties()));
    //endregion
    //region Lamps
    public static final Supplier<Item> BLACK_LAMP = DECO.register("black_lamp", () -> new BlockItem(ModBlocks.BLACK_LAMP.get(), new Item.Properties()));
    public static final Supplier<Item> BLUE_LAMP = DECO.register("blue_lamp", () -> new BlockItem(ModBlocks.BLUE_LAMP.get(), new Item.Properties()));
    public static final Supplier<Item> BROWN_LAMP = DECO.register("brown_lamp", () -> new BlockItem(ModBlocks.BROWN_LAMP.get(), new Item.Properties()));
    public static final Supplier<Item> CYAN_LAMP = DECO.register("cyan_lamp", () -> new BlockItem(ModBlocks.CYAN_LAMP.get(), new Item.Properties()));
    public static final Supplier<Item> GRAY_LAMP = DECO.register("gray_lamp", () -> new BlockItem(ModBlocks.GRAY_LAMP.get(), new Item.Properties()));
    public static final Supplier<Item> GREEN_LAMP = DECO.register("green_lamp", () -> new BlockItem(ModBlocks.GREEN_LAMP.get(), new Item.Properties()));
    public static final Supplier<Item> LIGHT_BLUE_LAMP = DECO.register("light_blue_lamp", () -> new BlockItem(ModBlocks.LIGHT_BLUE_LAMP.get(), new Item.Properties()));
    public static final Supplier<Item> LIGHT_GRAY_LAMP = DECO.register("light_gray_lamp", () -> new BlockItem(ModBlocks.LIGHT_GRAY_LAMP.get(), new Item.Properties()));
    public static final Supplier<Item> LIME_LAMP = DECO.register("lime_lamp", () -> new BlockItem(ModBlocks.LIME_LAMP.get(), new Item.Properties()));
    public static final Supplier<Item> MAGENTA_LAMP = DECO.register("magenta_lamp", () -> new BlockItem(ModBlocks.MAGENTA_LAMP.get(), new Item.Properties()));
    public static final Supplier<Item> ORANGE_LAMP = DECO.register("orange_lamp", () -> new BlockItem(ModBlocks.ORANGE_LAMP.get(), new Item.Properties()));
    public static final Supplier<Item> PINK_LAMP = DECO.register("pink_lamp", () -> new BlockItem(ModBlocks.PINK_LAMP.get(), new Item.Properties()));
    public static final Supplier<Item> PURPLE_LAMP = DECO.register("purple_lamp", () -> new BlockItem(ModBlocks.PURPLE_LAMP.get(), new Item.Properties()));
    public static final Supplier<Item> RED_LAMP = DECO.register("red_lamp", () -> new BlockItem(ModBlocks.RED_LAMP.get(), new Item.Properties()));
    public static final Supplier<Item> WHITE_LAMP = DECO.register("white_lamp", () -> new BlockItem(ModBlocks.WHITE_LAMP.get(), new Item.Properties()));
    public static final Supplier<Item> YELLOW_LAMP = DECO.register("yellow_lamp", () -> new BlockItem(ModBlocks.YELLOW_LAMP.get(), new Item.Properties()));
    //endregion

    //region Cutting Board
    public static final Supplier<Item> KNIFE = ITEMS.register("knife", () -> new AttackDamageItem(1.5D, new Item.Properties().durability(250)));
    public static final Supplier<Item> CUTTING_BOARD = DECO.register("cutting_board", () -> new BlockItem(ModBlocks.CUTTING_BOARD.get(), new Item.Properties()));
    //endregion

    public static final Supplier<Item> HAMMER = ITEMS.register("hammer", () -> new HammerItem(new Item.Properties().durability(1000)));

}
