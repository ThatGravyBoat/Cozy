package tech.thatgravyboat.cozy.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.CreativeModeTab;
import tech.thatgravyboat.cozy.Cozy;
import tech.thatgravyboat.cozy.common.registry.ModComposables;
import tech.thatgravyboat.cozy.common.registry.ModVillageCrops;
import tech.thatgravyboat.cozy.common.utils.PiePlacer;

public class CozyFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Cozy.init();

        PiePlacer.init();

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) ->
                PiePlacer.place(hand, player, hitResult) ? InteractionResult.SUCCESS : InteractionResult.PASS);
        ModComposables.init();

        ItemGroupEvents.MODIFY_ENTRIES_ALL.register((tab, stacks) -> {
            ResourceLocation loc = BuiltInRegistries.CREATIVE_MODE_TAB.getKey(tab);
            if (loc == null) return;
            ResourceKey<CreativeModeTab> key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, loc);
            Cozy.initCreativeTabContents(key, stacks::accept);
        });
    }

    public static void serverAboutToStart(MinecraftServer server) {
        ModVillageCrops.addNewVillageCrop(server);
    }
}