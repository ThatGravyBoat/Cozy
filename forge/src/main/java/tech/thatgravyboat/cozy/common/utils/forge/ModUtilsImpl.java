package tech.thatgravyboat.cozy.common.utils.forge;

import net.minecraftforge.fml.ModList;

public class ModUtilsImpl {
    public static boolean isModLoaded(String id) {
        return ModList.get().isLoaded(id);
    }
}
