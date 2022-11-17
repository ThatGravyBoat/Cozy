package tech.thatgravyboat.cozy.common.registry.forge;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;

public class ModComposablesImpl {
    public static void register(float value, ItemLike... items) {
        for (ItemLike item : items) {
            ComposterBlock.COMPOSTABLES.put(item.asItem(), value);
        }
    }
}
