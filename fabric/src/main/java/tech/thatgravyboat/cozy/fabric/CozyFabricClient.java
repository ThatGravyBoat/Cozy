package tech.thatgravyboat.cozy.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import tech.thatgravyboat.cozy.client.CozyClient;
import tech.thatgravyboat.cozy.client.CozyClientRegisterers;

public class CozyFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CozyClient.init();
        var register = new FabricClientRegister();
        CozyClient.registerBlockRenderers(register);
        CozyClient.registerEntityRenderers(register);
        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> CozyClient.registerModel(out));
    }

    private static class FabricClientRegister implements CozyClientRegisterers {

        @Override
        public <T extends BlockEntity> void registerBlockEntity(BlockEntityType<? extends T> blockEntityType, BlockEntityRendererProvider<T> blockEntityRendererProvider) {
            BlockEntityRenderers.register(blockEntityType, blockEntityRendererProvider);
        }

        @Override
        public <T extends Entity> void registerEntity(EntityType<? extends T> entityType, EntityRendererProvider<T> entityRendererProvider) {
            EntityRendererRegistry.register(entityType, entityRendererProvider);
        }
    }
}
