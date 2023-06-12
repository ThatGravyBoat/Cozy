package tech.thatgravyboat.cozy.common.registry;

import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;
import tech.thatgravyboat.cozy.Cozy;
import tech.thatgravyboat.cozy.common.blocks.*;
import tech.thatgravyboat.cozy.common.blocks.cuttingboard.CuttingBoardBlock;
import tech.thatgravyboat.cozy.common.blocks.cuttingboard.CuttingBoardBlockEntity;
import tech.thatgravyboat.cozy.common.blocks.pizza.*;

import java.util.function.Supplier;

public class ModBlocks {

    public static final ResourcefulRegistry<Block> BLOCKS = ResourcefulRegistries.create(BuiltInRegistries.BLOCK, Cozy.MOD_ID);
    public static final ResourcefulRegistry<Block> CHAIRS = ResourcefulRegistries.create(BLOCKS);
    public static final ResourcefulRegistry<BlockEntityType<?>> BLOCK_ENTITIES = ResourcefulRegistries.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Cozy.MOD_ID);

    //region Tables
    public static final Supplier<ConnectingBlock> ACACIA_TABLE = BLOCKS.register("acacia_table", () -> new ConnectingBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_PLANKS)));
    public static final Supplier<ConnectingBlock> BIRCH_TABLE = BLOCKS.register("birch_table", () -> new ConnectingBlock(BlockBehaviour.Properties.copy(Blocks.BIRCH_PLANKS)));
    public static final Supplier<ConnectingBlock> CRIMSON_TABLE = BLOCKS.register("crimson_table", () -> new ConnectingBlock(BlockBehaviour.Properties.copy(Blocks.CRIMSON_PLANKS)));
    public static final Supplier<ConnectingBlock> DARK_OAK_TABLE = BLOCKS.register("dark_oak_table", () -> new ConnectingBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_PLANKS)));
    public static final Supplier<ConnectingBlock> JUNGLE_TABLE = BLOCKS.register("jungle_table", () -> new ConnectingBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_PLANKS)));
    public static final Supplier<ConnectingBlock> MANGROVE_TABLE = BLOCKS.register("mangrove_table", () -> new ConnectingBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<ConnectingBlock> OAK_TABLE = BLOCKS.register("oak_table", () -> new ConnectingBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<ConnectingBlock> SPRUCE_TABLE = BLOCKS.register("spruce_table", () -> new ConnectingBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_PLANKS)));
    public static final Supplier<ConnectingBlock> WARPED_TABLE = BLOCKS.register("warped_table", () -> new ConnectingBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_PLANKS)));
    public static final Supplier<ConnectingBlock> CHERRY_TABLE = BLOCKS.register("cherry_table", () -> new ConnectingBlock(BlockBehaviour.Properties.copy(Blocks.CHERRY_PLANKS)));
    public static final Supplier<ConnectingBlock> BAMBOO_TABLE = BLOCKS.register("bamboo_table", () -> new ConnectingBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_PLANKS)));
    //endregion

    //region Chairs
    public static final Supplier<ChairBlock> ACACIA_CHAIR = CHAIRS.register("acacia_chair", () -> new ChairBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_PLANKS).pushReaction(PushReaction.BLOCK)));
    public static final Supplier<ChairBlock> BIRCH_CHAIR = CHAIRS.register("birch_chair", () -> new ChairBlock(BlockBehaviour.Properties.copy(Blocks.BIRCH_PLANKS).pushReaction(PushReaction.BLOCK)));
    public static final Supplier<ChairBlock> CRIMSON_CHAIR = CHAIRS.register("crimson_chair", () -> new ChairBlock(BlockBehaviour.Properties.copy(Blocks.CRIMSON_PLANKS).pushReaction(PushReaction.BLOCK)));
    public static final Supplier<ChairBlock> DARK_OAK_CHAIR = CHAIRS.register("dark_oak_chair", () -> new ChairBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_PLANKS).pushReaction(PushReaction.BLOCK)));
    public static final Supplier<ChairBlock> JUNGLE_CHAIR = CHAIRS.register("jungle_chair", () -> new ChairBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_PLANKS).pushReaction(PushReaction.BLOCK)));
    public static final Supplier<ChairBlock> MANGROVE_CHAIR = CHAIRS.register("mangrove_chair", () -> new ChairBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).pushReaction(PushReaction.BLOCK)));
    public static final Supplier<ChairBlock> OAK_CHAIR = CHAIRS.register("oak_chair", () -> new ChairBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).pushReaction(PushReaction.BLOCK)));
    public static final Supplier<ChairBlock> SPRUCE_CHAIR = CHAIRS.register("spruce_chair", () -> new ChairBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_PLANKS).pushReaction(PushReaction.BLOCK)));
    public static final Supplier<ChairBlock> WARPED_CHAIR = CHAIRS.register("warped_chair", () -> new ChairBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_PLANKS).pushReaction(PushReaction.BLOCK)));
    public static final Supplier<ChairBlock> CHERRY_CHAIR = CHAIRS.register("cherry_chair", () -> new ChairBlock(BlockBehaviour.Properties.copy(Blocks.CHERRY_PLANKS).pushReaction(PushReaction.BLOCK)));
    public static final Supplier<ChairBlock> BAMBOO_CHAIR = CHAIRS.register("bamboo_chair", () -> new ChairBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_PLANKS).pushReaction(PushReaction.BLOCK)));
    //endregion

    //region Lamps
    public static final Supplier<LampBlock> BLACK_LAMP = BLOCKS.register("black_lamp", () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<LampBlock> BLUE_LAMP = BLOCKS.register("blue_lamp", () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<LampBlock> BROWN_LAMP = BLOCKS.register("brown_lamp", () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<LampBlock> CYAN_LAMP = BLOCKS.register("cyan_lamp", () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<LampBlock> GRAY_LAMP = BLOCKS.register("gray_lamp", () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<LampBlock> GREEN_LAMP = BLOCKS.register("green_lamp", () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<LampBlock> LIGHT_BLUE_LAMP = BLOCKS.register("light_blue_lamp", () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<LampBlock> LIGHT_GRAY_LAMP = BLOCKS.register("light_gray_lamp", () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<LampBlock> LIME_LAMP = BLOCKS.register("lime_lamp", () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<LampBlock> MAGENTA_LAMP = BLOCKS.register("magenta_lamp", () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<LampBlock> ORANGE_LAMP = BLOCKS.register("orange_lamp", () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<LampBlock> PINK_LAMP = BLOCKS.register("pink_lamp", () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<LampBlock> PURPLE_LAMP = BLOCKS.register("purple_lamp", () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<LampBlock> RED_LAMP = BLOCKS.register("red_lamp", () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<LampBlock> WHITE_LAMP = BLOCKS.register("white_lamp", () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<LampBlock> YELLOW_LAMP = BLOCKS.register("yellow_lamp", () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    //endregion

    //region Pizza

    public static final Supplier<DoughBlock> DOUGH = BLOCKS.register("dough", () -> new DoughBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(0.5F).sound(SoundType.WOOL)));
    public static final Supplier<PizzaBlock> PIZZA = BLOCKS.register("pizza", () -> new BuilderPizzaBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(0.5F).sound(SoundType.WOOL)));
    public static final Supplier<PizzaBlock> COOKED_PIZZA = BLOCKS.register("cooked_pizza", () -> new CookedPizzaBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).strength(0.5F).sound(SoundType.WOOL)));
    public static final Supplier<PizzaBlock> RAW_PIZZA = BLOCKS.register("raw_pizza", () -> new CookingPizzaBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(0.5F).sound(SoundType.WOOL)));
    public static final Supplier<BlockEntityType<CookingPizzaBlockEntity>> COOKING_PIZZA_ENTITY = BLOCK_ENTITIES.register("cooking_pizza", () -> createBlockEntityType(CookingPizzaBlockEntity::new, ModBlocks.RAW_PIZZA.get()));

    //endregion

    //region Cutting Board
    public static final Supplier<CuttingBoardBlock> CUTTING_BOARD = BLOCKS.register("cutting_board", () -> new CuttingBoardBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<BlockEntityType<CuttingBoardBlockEntity>> CUTTING_BOARD_ENTITY = BLOCK_ENTITIES.register("cutting_board", () -> createBlockEntityType(CuttingBoardBlockEntity::new, ModBlocks.CUTTING_BOARD.get()));
    public static final Supplier<BlockEntityType<ChairBlockEntity>> CHAIR_ENTITY = BLOCK_ENTITIES.register("chair", () -> createBlockEntityType(ChairBlockEntity::new, ModBlocks.CHAIRS.boundStream().toArray(Block[]::new)));
    //endregion

    //region Pies
    public static final Supplier<PieBlock> PUMPKIN_PIE = BLOCKS.register("pumpkin_pie", () -> new PieBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(0.5F).sound(SoundType.WOOL)));
    public static final Supplier<PieBlock> APPLE_PIE = BLOCKS.register("apple_pie", () -> new PieBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(0.5F).sound(SoundType.WOOL)));
    public static final Supplier<PieBlock> CHOCOLATE_PIE = BLOCKS.register("chocolate_pie", () -> new PieBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(0.5F).sound(SoundType.WOOL)));
    public static final Supplier<PieBlock> GLOWBERRY_PIE = BLOCKS.register("glow_berry_pie", () -> new PieBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(0.5F).sound(SoundType.WOOL)));
    public static final Supplier<PieBlock> SWEET_BERRY_PIE = BLOCKS.register("sweet_berry_pie", () -> new PieBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(0.5F).sound(SoundType.WOOL)));
    //endregion

    //region Tomato
    public static final Supplier<TomatoCropBlock> TOMATO_CROP = BLOCKS.register("tomato", () -> new TomatoCropBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)));
    //endregion

    @ExpectPlatform
    public static <T extends BlockEntity> BlockEntityType<T> createBlockEntityType(BlockEntityFactory<T> factory, Block ... block) {
        throw new AssertionError();
    }

    @FunctionalInterface
    public interface BlockEntityFactory<T extends BlockEntity> {
        @NotNull T create(BlockPos pos, BlockState state);
    }
}
