package tech.thatgravyboat.cozy.client;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.world.level.block.Block;
import tech.thatgravyboat.cozy.client.renderers.CuttingBoardRenderer;
import tech.thatgravyboat.cozy.common.registry.ModBlocks;
import tech.thatgravyboat.cozy.common.registry.ModEntities;

public class CozyClient {

    public static void init() {
        initRenderTypes();
    }

    public static void registerBlockRenderers(CozyClientRegisterers registerer) {
        registerer.registerBlockEntity(ModBlocks.CUTTING_BOARD_ENTITY.get(), CuttingBoardRenderer::new);
    }

    public static void registerEntityRenderers(CozyClientRegisterers registerer) {
        registerer.registerEntity(ModEntities.SEAT.get(), NoopRenderer::new);
    }

    public static void initRenderTypes() {
        setRenderType(ModBlocks.ACACIA_CHAIR.get(), RenderType.cutout());
        setRenderType(ModBlocks.BIRCH_CHAIR.get(), RenderType.cutout());
        setRenderType(ModBlocks.CRIMSON_CHAIR.get(), RenderType.cutout());
        setRenderType(ModBlocks.DARK_OAK_CHAIR.get(), RenderType.cutout());
        setRenderType(ModBlocks.JUNGLE_CHAIR.get(), RenderType.cutout());
        setRenderType(ModBlocks.MANGROVE_CHAIR.get(), RenderType.cutout());
        setRenderType(ModBlocks.OAK_CHAIR.get(), RenderType.cutout());
        setRenderType(ModBlocks.SPRUCE_CHAIR.get(), RenderType.cutout());
        setRenderType(ModBlocks.WARPED_CHAIR.get(), RenderType.cutout());

        setRenderType(ModBlocks.BLACK_LAMP.get(), RenderType.translucent());
        setRenderType(ModBlocks.BLUE_LAMP.get(), RenderType.translucent());
        setRenderType(ModBlocks.BROWN_LAMP.get(), RenderType.translucent());
        setRenderType(ModBlocks.CYAN_LAMP.get(), RenderType.translucent());
        setRenderType(ModBlocks.GRAY_LAMP.get(), RenderType.translucent());
        setRenderType(ModBlocks.GREEN_LAMP.get(), RenderType.translucent());
        setRenderType(ModBlocks.LIGHT_BLUE_LAMP.get(), RenderType.translucent());
        setRenderType(ModBlocks.LIGHT_GRAY_LAMP.get(), RenderType.translucent());
        setRenderType(ModBlocks.LIME_LAMP.get(), RenderType.translucent());
        setRenderType(ModBlocks.MAGENTA_LAMP.get(), RenderType.translucent());
        setRenderType(ModBlocks.ORANGE_LAMP.get(), RenderType.translucent());
        setRenderType(ModBlocks.PINK_LAMP.get(), RenderType.translucent());
        setRenderType(ModBlocks.PURPLE_LAMP.get(), RenderType.translucent());
        setRenderType(ModBlocks.RED_LAMP.get(), RenderType.translucent());
        setRenderType(ModBlocks.WHITE_LAMP.get(), RenderType.translucent());
        setRenderType(ModBlocks.YELLOW_LAMP.get(), RenderType.translucent());

        setRenderType(ModBlocks.PIZZA.get(), RenderType.cutout());
        setRenderType(ModBlocks.RAW_PIZZA.get(), RenderType.cutout());
        setRenderType(ModBlocks.COOKED_PIZZA.get(), RenderType.cutout());

        setRenderType(ModBlocks.TOMATO_CROP.get(), RenderType.cutout());
    }

    @ExpectPlatform
    public static void setRenderType(Block block, RenderType type) {
        throw new AssertionError();
    }
}
