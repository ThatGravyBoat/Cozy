package tech.thatgravyboat.cozy.forge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import tech.thatgravyboat.cozy.Cozy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import tech.thatgravyboat.cozy.common.registry.ModComposables;
import tech.thatgravyboat.cozy.common.registry.ModVillageCrops;
import tech.thatgravyboat.cozy.common.registry.forge.ModBlocksImpl;
import tech.thatgravyboat.cozy.common.registry.forge.ModEntitiesImpl;
import tech.thatgravyboat.cozy.common.registry.forge.ModItemsImpl;
import tech.thatgravyboat.cozy.common.registry.forge.ModRecipesImpl;
import tech.thatgravyboat.cozy.common.utils.PiePlacer;

@Mod(Cozy.MOD_ID)
public class CozyForge {
    public CozyForge() {
        Cozy.init();

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItemsImpl.ITEMS.register(bus);
        ModBlocksImpl.BLOCKS.register(bus);
        ModBlocksImpl.BLOCK_ENTITIES.register(bus);
        ModEntitiesImpl.ENTITIES.register(bus);
        ModRecipesImpl.TYPES.register(bus);
        ModRecipesImpl.SERIALIZERS.register(bus);

        MinecraftForge.EVENT_BUS.addListener(CozyForge::onPlace);
        MinecraftForge.EVENT_BUS.addListener(CozyForge::onServerStart);
        bus.addListener(CozyForge::onCommonSetup);
    }

    public static void onCommonSetup(FMLCommonSetupEvent event) {
        PiePlacer.init();
        ModComposables.init();
    }

    public static void onPlace(PlayerInteractEvent.RightClickBlock event) {
        if (PiePlacer.place(event.getHand(), event.getEntity(), event.getHitVec())) {
            event.setCanceled(true);
        }
    }

    public static void onServerStart(ServerAboutToStartEvent event) {
        ModVillageCrops.addNewVillageCrop(event.getServer());
    }
}