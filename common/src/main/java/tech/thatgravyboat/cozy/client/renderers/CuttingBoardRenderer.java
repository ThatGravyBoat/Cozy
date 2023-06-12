package tech.thatgravyboat.cozy.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.teamresourceful.resourcefullib.client.CloseablePoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import tech.thatgravyboat.cozy.common.blocks.cuttingboard.CuttingBoardBlockEntity;

public class CuttingBoardRenderer implements BlockEntityRenderer<CuttingBoardBlockEntity> {
    private final ItemRenderer itemRenderer;

    public CuttingBoardRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    public void render(CuttingBoardBlockEntity cuttingBoard, float f, @NotNull PoseStack poseStack, @NotNull MultiBufferSource source, int i, int j) {
        int k = (int) cuttingBoard.getBlockPos().asLong();

        ItemStack itemStack = cuttingBoard.getItem();
        if (!itemStack.isEmpty()) {
            try (var stack = new CloseablePoseStack(poseStack)) {
                stack.translate(0.5D, 0.09375D, 0.5D);
                stack.mulPose(Axis.XP.rotationDegrees(90.0F));
                stack.mulPose(Axis.ZP.rotationDegrees(cuttingBoard.randomDegree));
                stack.scale(0.7F, 0.7F, 0.7F);
                this.itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, i, j, poseStack, source, cuttingBoard.getLevel(), k);
            }
        }

    }
}
