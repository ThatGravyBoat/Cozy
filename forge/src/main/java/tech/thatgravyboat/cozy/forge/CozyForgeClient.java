package tech.thatgravyboat.cozy.forge;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import tech.thatgravyboat.cozy.Cozy;
import tech.thatgravyboat.cozy.client.CozyClient;
import tech.thatgravyboat.cozy.client.CozyClientRegisterers;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Cozy.MOD_ID, value = Dist.CLIENT)
public class CozyForgeClient {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        CozyClient.init();
    }

    @SubscribeEvent
    public static void onModelLoading(ModelEvent.RegisterAdditional event) {
        CozyClient.registerModel(event::register);
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        var registerer = new CozyClientRegisterers() {

            @Override
            public <T extends BlockEntity> void registerBlockEntity(BlockEntityType<? extends T> blockEntityType, BlockEntityRendererProvider<T> blockEntityRendererProvider) {
                event.registerBlockEntityRenderer(blockEntityType, blockEntityRendererProvider);
            }

            @Override
            public <T extends Entity> void registerEntity(EntityType<? extends T> entityType, EntityRendererProvider<T> entityRendererProvider) {
                event.registerEntityRenderer(entityType, entityRendererProvider);
            }
        };
        CozyClient.registerEntityRenderers(registerer);
        CozyClient.registerBlockRenderers(registerer);
    }
}