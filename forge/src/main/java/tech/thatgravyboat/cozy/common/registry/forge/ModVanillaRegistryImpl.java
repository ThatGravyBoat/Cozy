package tech.thatgravyboat.cozy.common.registry.forge;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;

import java.util.IdentityHashMap;
import java.util.Map;

public class ModVanillaRegistryImpl {

    private static final Map<ItemLike, Integer> FUEL_MAP = new IdentityHashMap<>();

    public static void register(float value, ItemLike... items) {
        for (ItemLike item : items) {
            ComposterBlock.COMPOSTABLES.put(item.asItem(), value);
        }
    }

    public static void registerFuel(int value, ItemLike... items) {
        for (ItemLike item : items) {
            FUEL_MAP.put(item, value);
        }
    }

    public static void fuelBurnEvent(FurnaceFuelBurnTimeEvent event) {
        Integer value = FUEL_MAP.get(event.getItemStack().getItem());
        if (value != null) {
            event.setBurnTime(value);
        }
    }
}
