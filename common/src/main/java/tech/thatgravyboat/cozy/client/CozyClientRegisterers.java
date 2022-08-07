package tech.thatgravyboat.cozy.client;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public interface CozyClientRegisterers {


    <T extends BlockEntity> void registerBlockEntity(BlockEntityType<? extends T> blockEntityType, BlockEntityRendererProvider<T> blockEntityRendererProvider);

    <T extends Entity> void registerEntity(EntityType<? extends T> entityType, EntityRendererProvider<T> entityRendererProvider);

}
