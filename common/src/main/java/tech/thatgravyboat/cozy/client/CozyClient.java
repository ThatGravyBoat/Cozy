package tech.thatgravyboat.cozy.client;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import org.apache.commons.lang3.NotImplementedException;
import tech.thatgravyboat.cozy.Cozy;
import tech.thatgravyboat.cozy.client.renderers.CushionRenderer;
import tech.thatgravyboat.cozy.client.renderers.CuttingBoardRenderer;
import tech.thatgravyboat.cozy.common.registry.ModBlocks;
import tech.thatgravyboat.cozy.common.registry.ModEntities;

import java.util.function.Consumer;

public class CozyClient {

    public static void init() {
        initRenderTypes();
    }

    public static void registerBlockRenderers(CozyClientRegisterers registerer) {
        registerer.registerBlockEntity(ModBlocks.CUTTING_BOARD_ENTITY.get(), CuttingBoardRenderer::new);
        registerer.registerBlockEntity(ModBlocks.CHAIR_ENTITY.get(), CushionRenderer::new);
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

    public static void registerModel(Consumer<ResourceLocation> modelLoader) {
        for (DyeColor value : DyeColor.values()) {
            modelLoader.accept(new ResourceLocation(Cozy.MOD_ID, "block/cushion/" + value.getName()));
        }
    }

    @ExpectPlatform
    public static void setRenderType(Block block, RenderType type) {
        throw new NotImplementedException();
    }

    @ExpectPlatform
    public static BakedModel getModel(BlockRenderDispatcher dispatcher, ResourceLocation model) {
        throw new NotImplementedException();
    }
}
