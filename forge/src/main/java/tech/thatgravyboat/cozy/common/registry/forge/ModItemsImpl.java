package tech.thatgravyboat.cozy.common.registry.forge;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import tech.thatgravyboat.cozy.Cozy;

import java.util.function.Supplier;

public class ModItemsImpl {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Cozy.MOD_ID);

    public static <T extends Item> Supplier<T> registerItem(String id, Supplier<T> item) {
        return ITEMS.register(id, item);
    }
}
