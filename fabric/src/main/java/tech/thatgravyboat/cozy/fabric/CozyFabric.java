package tech.thatgravyboat.cozy.fabric;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import tech.thatgravyboat.cozy.Cozy;
import net.fabricmc.api.ModInitializer;
import tech.thatgravyboat.cozy.common.registry.ModVillageCrops;
import tech.thatgravyboat.cozy.common.utils.PiePlacer;

public class CozyFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Cozy.init();

        PiePlacer.init();

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) ->
                PiePlacer.place(hand, player, hitResult) ? InteractionResult.SUCCESS : InteractionResult.PASS);
    }

    public static void serverAboutToStart(MinecraftServer server) {
        ModVillageCrops.addNewVillageCrop(server);
    }
}