package tech.thatgravyboat.cozy.common.registry.fabric;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.world.level.ItemLike;

public class ModComposablesImpl {
    public static void register(float value, ItemLike... items) {
        for (ItemLike item : items) {
            CompostingChanceRegistry.INSTANCE.add(item, value);
        }
    }
}
