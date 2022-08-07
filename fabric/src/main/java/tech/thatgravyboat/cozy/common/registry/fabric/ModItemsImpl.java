package tech.thatgravyboat.cozy.common.registry.fabric;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import tech.thatgravyboat.cozy.Cozy;

import java.util.function.Supplier;

public class ModItemsImpl {
    public static <T extends Item> Supplier<T> registerItem(String id, Supplier<T> item) {
        var output = Registry.register(Registry.ITEM, new ResourceLocation(Cozy.MOD_ID, id), item.get());
        return () -> output;
    }
}
