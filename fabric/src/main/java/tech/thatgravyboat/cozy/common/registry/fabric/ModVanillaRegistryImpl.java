package tech.thatgravyboat.cozy.common.registry.fabric;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.world.level.ItemLike;

public class ModVanillaRegistryImpl {
    public static void register(float value, ItemLike... items) {
        for (ItemLike item : items) {
            CompostingChanceRegistry.INSTANCE.add(item, value);
        }
    }

    public static void registerFuel(int value, ItemLike... items) {
        for (ItemLike item : items) {
            FuelRegistry.INSTANCE.add(item, value);
        }
    }
}
