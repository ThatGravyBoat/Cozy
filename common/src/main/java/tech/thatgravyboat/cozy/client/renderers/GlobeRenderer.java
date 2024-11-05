package tech.thatgravyboat.cozy.client.renderers;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.util.FastColor;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import tech.thatgravyboat.cozy.common.blocks.globe.GlobeBlockEntity;

public class GlobeRenderer implements BlockEntityRenderer<GlobeBlockEntity> {

    public GlobeRenderer(BlockEntityRendererProvider.Context context) {

    }

    public void render(@NotNull GlobeBlockEntity globe, float f, @NotNull PoseStack poseStack, @NotNull MultiBufferSource source, int i, int j) {
        if (globe.getData().isEmpty()) return;
        Tesselator tesselator = Tesselator.getInstance();
        var builder = tesselator.getBuilder();
        poseStack.pushPose();
        poseStack.translate(0.3125f, 0.140625f, 0.3125f);
        builder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR_NORMAL);
        renderWorld(globe.getData().colors(), poseStack, builder);
        if (builder.building()) {
            BufferBuilder.RenderedBuffer renderedBuffer = builder.end();
            RenderSystem.setShader(GameRenderer::getPositionColorShader);
            RenderSystem.enableDepthTest();
            RenderSystem.disableCull();
            RenderSystem.disableBlend();
            BufferUploader.drawWithShader(renderedBuffer);
            RenderSystem.enableCull();
            RenderSystem.enableBlend();
            RenderSystem.disableDepthTest();
        }
        poseStack.popPose();
    }

    public void renderWorld(MapColor[][][] colors, PoseStack poseStack, BufferBuilder bufferBuilder) {
        double factor = (6d / 16d) / 16d;
        double x;
        double y;
        double z;

        Matrix4f matrix4f = poseStack.last().pose();

        x = 0;
        for (MapColor[][] values : colors) {
            y = 0;
            for (MapColor[] valuesvalues : values) {
                z = 0;
                for (MapColor color : valuesvalues) {
                    if (color != null && color != MapColor.NONE) {
                        renderBox(bufferBuilder, matrix4f, new AABB(x, y, z, x + factor, y + factor, z + factor), color);
                    }
                    z += factor;
                }
                y += factor;
            }
            x += factor;
        }

    }

    public void renderBox(VertexConsumer builder, Matrix4f pos, AABB aabb, MapColor color) {
        for (Direction value : Direction.values()) {
            renderFace(builder, pos, value, aabb, color);
        }
    }

    public void renderFace(VertexConsumer builder, Matrix4f pos, Direction side, AABB box, MapColor color) {
        float normalX = side.getStepX();
        float normalY = side.getStepY();
        float normalZ = side.getStepZ();

        Vector3f one = new Vector3f((float) box.minX, (float) box.minY, (float) box.minZ);
        Vector3f two = new Vector3f((float) box.maxX, (float) box.maxY, (float) box.maxZ);

        int r = (int) (FastColor.ARGB32.red(color.col) * 0.75);
        int g = (int) (FastColor.ARGB32.green(color.col) * 0.75);
        int b = (int) (FastColor.ARGB32.blue(color.col) * 0.75);

        switch (side) {
            case DOWN -> {
                builder.vertex(pos, one.x(), one.y(), two.z()).color(r, g, b, 255).normal(normalX, normalY, normalZ).endVertex();
                builder.vertex(pos, one.x(), one.y(), one.z()).color(r, g, b, 255).normal(normalX, normalY, normalZ).endVertex();
                builder.vertex(pos, two.x(), one.y(), one.z()).color(r, g, b, 255).normal(normalX, normalY, normalZ).endVertex();
                builder.vertex(pos, two.x(), one.y(), two.z()).color(r, g, b, 255).normal(normalX, normalY, normalZ).endVertex();
            }
            case UP -> {
                builder.vertex(pos, one.x(), two.y(), two.z()).color(r, g, b, 255).normal(normalX, normalY, normalZ).endVertex();
                builder.vertex(pos, two.x(), two.y(), two.z()).color(r, g, b, 255).normal(normalX, normalY, normalZ).endVertex();
                builder.vertex(pos, two.x(), two.y(), one.z()).color(r, g, b, 255).normal(normalX, normalY, normalZ).endVertex();
                builder.vertex(pos, one.x(), two.y(), one.z()).color(r, g, b, 255).normal(normalX, normalY, normalZ).endVertex();
            }
            case NORTH -> {
                builder.vertex(pos, one.x(), one.y(), one.z()).color(r, g, b, 255).normal(normalX, normalY, normalZ).endVertex();
                builder.vertex(pos, one.x(), two.y(), one.z()).color(r, g, b, 255).normal(normalX, normalY, normalZ).endVertex();
                builder.vertex(pos, two.x(), two.y(), one.z()).color(r, g, b, 255).normal(normalX, normalY, normalZ).endVertex();
                builder.vertex(pos, two.x(), one.y(), one.z()).color(r, g, b, 255).normal(normalX, normalY, normalZ).endVertex();
            }
            case SOUTH -> {
                builder.vertex(pos, two.x(), one.y(), two.z()).color(r, g, b, 255).normal(normalX, normalY, normalZ).endVertex();
                builder.vertex(pos, two.x(), two.y(), two.z()).color(r, g, b, 255).normal(normalX, normalY, normalZ).endVertex();
                builder.vertex(pos, one.x(), two.y(), two.z()).color(r, g, b, 255).normal(normalX, normalY, normalZ).endVertex();
                builder.vertex(pos, one.x(), one.y(), two.z()).color(r, g, b, 255).normal(normalX, normalY, normalZ).endVertex();
            }
            case WEST -> {
                builder.vertex(pos, one.x(), one.y(), two.z()).color(r, g, b, 255).normal(normalX, normalY, normalZ).endVertex();
                builder.vertex(pos, one.x(), two.y(), two.z()).color(r, g, b, 255).normal(normalX, normalY, normalZ).endVertex();
                builder.vertex(pos, one.x(), two.y(), one.z()).color(r, g, b, 255).normal(normalX, normalY, normalZ).endVertex();
                builder.vertex(pos, one.x(), one.y(), one.z()).color(r, g, b, 255).normal(normalX, normalY, normalZ).endVertex();
            }
            case EAST -> {
                builder.vertex(pos, two.x(), one.y(), one.z()).color(r, g, b, 255).normal(normalX, normalY, normalZ).endVertex();
                builder.vertex(pos, two.x(), two.y(), one.z()).color(r, g, b, 255).normal(normalX, normalY, normalZ).endVertex();
                builder.vertex(pos, two.x(), two.y(), two.z()).color(r, g, b, 255).normal(normalX, normalY, normalZ).endVertex();
                builder.vertex(pos, two.x(), one.y(), two.z()).color(r, g, b, 255).normal(normalX, normalY, normalZ).endVertex();
            }
        }
    }
}
