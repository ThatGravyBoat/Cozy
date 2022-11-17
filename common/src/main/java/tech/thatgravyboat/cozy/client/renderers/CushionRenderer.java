package tech.thatgravyboat.cozy.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import org.jetbrains.annotations.NotNull;
import tech.thatgravyboat.cozy.client.CozyClient;
import tech.thatgravyboat.cozy.common.blocks.ChairBlockEntity;

public class CushionRenderer implements BlockEntityRenderer<ChairBlockEntity> {

    private final BlockRenderDispatcher blockRenderer;

    public CushionRenderer(BlockEntityRendererProvider.Context context) {
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(@NotNull ChairBlockEntity chair, float partialTicks, @NotNull PoseStack stack, @NotNull MultiBufferSource source, int light, int overlay) {
        if (!chair.hasColor()) return;
        BakedModel model = CozyClient.getModel(blockRenderer, chair.getModel());
        stack.pushPose();
        stack.translate(0, 10d/16d, 0);
        blockRenderer.getModelRenderer().renderModel(stack.last(), source.getBuffer(Sheets.cutoutBlockSheet()), null, model, 1f, 1f, 1f, light, OverlayTexture.NO_OVERLAY);
        stack.popPose();
    }
}
