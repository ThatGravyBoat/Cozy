package tech.thatgravyboat.cozy.common.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.NotNull;
import tech.thatgravyboat.cozy.common.blocks.*;
import tech.thatgravyboat.cozy.common.blocks.cuttingboard.CuttingBoardBlock;
import tech.thatgravyboat.cozy.common.blocks.cuttingboard.CuttingBoardBlockEntity;
import tech.thatgravyboat.cozy.common.blocks.pizza.*;

import java.util.function.Supplier;

public class ModBlocks {

    //region Tables
    public static final Supplier<ConnectingBlock> ACACIA_TABLE = registerBlock("acacia_table", () -> new ConnectingBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<ConnectingBlock> BIRCH_TABLE = registerBlock("birch_table", () -> new ConnectingBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<ConnectingBlock> CRIMSON_TABLE = registerBlock("crimson_table", () -> new ConnectingBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<ConnectingBlock> DARK_OAK_TABLE = registerBlock("dark_oak_table", () -> new ConnectingBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<ConnectingBlock> JUNGLE_TABLE = registerBlock("jungle_table", () -> new ConnectingBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<ConnectingBlock> MANGROVE_TABLE = registerBlock("mangrove_table", () -> new ConnectingBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<ConnectingBlock> OAK_TABLE = registerBlock("oak_table", () -> new ConnectingBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<ConnectingBlock> SPRUCE_TABLE = registerBlock("spruce_table", () -> new ConnectingBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<ConnectingBlock> WARPED_TABLE = registerBlock("warped_table", () -> new ConnectingBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    //endregion

    //region Chairs
    public static final Supplier<ChairBlock> ACACIA_CHAIR = registerBlock("acacia_chair", () -> new ChairBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<ChairBlock> BIRCH_CHAIR = registerBlock("birch_chair", () -> new ChairBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<ChairBlock> CRIMSON_CHAIR = registerBlock("crimson_chair", () -> new ChairBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<ChairBlock> DARK_OAK_CHAIR = registerBlock("dark_oak_chair", () -> new ChairBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<ChairBlock> JUNGLE_CHAIR = registerBlock("jungle_chair", () -> new ChairBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<ChairBlock> MANGROVE_CHAIR = registerBlock("mangrove_chair", () -> new ChairBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<ChairBlock> OAK_CHAIR = registerBlock("oak_chair", () -> new ChairBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<ChairBlock> SPRUCE_CHAIR = registerBlock("spruce_chair", () -> new ChairBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<ChairBlock> WARPED_CHAIR = registerBlock("warped_chair", () -> new ChairBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    //endregion

    //region Lamps
    public static final Supplier<LampBlock> BLACK_LAMP = registerBlock("black_lamp", () -> new LampBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<LampBlock> BLUE_LAMP = registerBlock("blue_lamp", () -> new LampBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<LampBlock> BROWN_LAMP = registerBlock("brown_lamp", () -> new LampBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<LampBlock> CYAN_LAMP = registerBlock("cyan_lamp", () -> new LampBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<LampBlock> GRAY_LAMP = registerBlock("gray_lamp", () -> new LampBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<LampBlock> GREEN_LAMP = registerBlock("green_lamp", () -> new LampBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<LampBlock> LIGHT_BLUE_LAMP = registerBlock("light_blue_lamp", () -> new LampBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<LampBlock> LIGHT_GRAY_LAMP = registerBlock("light_gray_lamp", () -> new LampBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<LampBlock> LIME_LAMP = registerBlock("lime_lamp", () -> new LampBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<LampBlock> MAGENTA_LAMP = registerBlock("magenta_lamp", () -> new LampBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<LampBlock> ORANGE_LAMP = registerBlock("orange_lamp", () -> new LampBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<LampBlock> PINK_LAMP = registerBlock("pink_lamp", () -> new LampBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<LampBlock> PURPLE_LAMP = registerBlock("purple_lamp", () -> new LampBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<LampBlock> RED_LAMP = registerBlock("red_lamp", () -> new LampBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<LampBlock> WHITE_LAMP = registerBlock("white_lamp", () -> new LampBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final Supplier<LampBlock> YELLOW_LAMP = registerBlock("yellow_lamp", () -> new LampBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    //endregion

    //region Pizza

    public static final Supplier<DoughBlock> DOUGH = registerBlock("dough", () -> new DoughBlock(BlockBehaviour.Properties.of(Material.CAKE).strength(0.5F).sound(SoundType.WOOL)));
    public static final Supplier<PizzaBlock> PIZZA = registerBlock("pizza", () -> new BuilderPizzaBlock(BlockBehaviour.Properties.of(Material.CAKE).strength(0.5F).sound(SoundType.WOOL)));
    public static final Supplier<PizzaBlock> COOKED_PIZZA = registerBlock("cooked_pizza", () -> new CookedPizzaBlock(BlockBehaviour.Properties.of(Material.CAKE).strength(0.5F).sound(SoundType.WOOL)));
    public static final Supplier<PizzaBlock> RAW_PIZZA = registerBlock("raw_pizza", () -> new CookingPizzaBlock(BlockBehaviour.Properties.of(Material.CAKE).strength(0.5F).sound(SoundType.WOOL)));
    public static final Supplier<BlockEntityType<CookingPizzaBlockEntity>> COOKING_PIZZA_ENTITY = registerBlockEntity("cooking_pizza", () -> createBlockEntityType(CookingPizzaBlockEntity::new, ModBlocks.RAW_PIZZA.get()));

    //endregion

    //region Cutting Board
    public static final Supplier<CuttingBoardBlock> CUTTING_BOARD = registerBlock("cutting_board", () -> new CuttingBoardBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2F).sound(SoundType.WOOD)));
    public static final Supplier<BlockEntityType<CuttingBoardBlockEntity>> CUTTING_BOARD_ENTITY = registerBlockEntity("cutting_board", () -> createBlockEntityType(CuttingBoardBlockEntity::new, ModBlocks.CUTTING_BOARD.get()));
    public static final Supplier<BlockEntityType<ChairBlockEntity>> CHAIR_ENTITY = registerBlockEntity("chair", () -> createBlockEntityType(ChairBlockEntity::new, ModBlocks.ACACIA_CHAIR.get(), ModBlocks.MANGROVE_CHAIR.get(), ModBlocks.BIRCH_CHAIR.get(), ModBlocks.DARK_OAK_CHAIR.get(), ModBlocks.JUNGLE_CHAIR.get(), ModBlocks.OAK_CHAIR.get(), ModBlocks.SPRUCE_CHAIR.get(), ModBlocks.CRIMSON_CHAIR.get(), ModBlocks.WARPED_CHAIR.get()));
    //endregion

    //region Pies
    public static final Supplier<PieBlock> PUMPKIN_PIE = registerBlock("pumpkin_pie", () -> new PieBlock(BlockBehaviour.Properties.of(Material.CAKE).strength(0.5F).sound(SoundType.WOOL)));
    public static final Supplier<PieBlock> APPLE_PIE = registerBlock("apple_pie", () -> new PieBlock(BlockBehaviour.Properties.of(Material.CAKE).strength(0.5F).sound(SoundType.WOOL)));
    public static final Supplier<PieBlock> CHOCOLATE_PIE = registerBlock("chocolate_pie", () -> new PieBlock(BlockBehaviour.Properties.of(Material.CAKE).strength(0.5F).sound(SoundType.WOOL)));
    public static final Supplier<PieBlock> GLOWBERRY_PIE = registerBlock("glow_berry_pie", () -> new PieBlock(BlockBehaviour.Properties.of(Material.CAKE).strength(0.5F).sound(SoundType.WOOL)));
    public static final Supplier<PieBlock> SWEET_BERRY_PIE = registerBlock("sweet_berry_pie", () -> new PieBlock(BlockBehaviour.Properties.of(Material.CAKE).strength(0.5F).sound(SoundType.WOOL)));
    //endregion

    //region Tomato
    public static final Supplier<TomatoCropBlock> TOMATO_CROP = registerBlock("tomato", () -> new TomatoCropBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
    //endregion


    public static void init() {
        //Load class into class loader!
    }

    @ExpectPlatform
    public static <T extends Block> Supplier<T> registerBlock(String id, Supplier<T> block) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <E extends BlockEntity, T extends BlockEntityType<E>> Supplier<T> registerBlockEntity(String id, Supplier<T> item) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends BlockEntity> BlockEntityType<T> createBlockEntityType(BlockEntityFactory<T> factory, Block ... block) {
        throw new AssertionError();
    }

    @FunctionalInterface
    public interface BlockEntityFactory<T extends BlockEntity> {
        @NotNull T create(BlockPos pos, BlockState state);
    }
}
