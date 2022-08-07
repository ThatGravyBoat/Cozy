package tech.thatgravyboat.cozy.common.utils.fabric;

import net.fabricmc.loader.api.FabricLoader;

public class ModUtilsImpl {
    public static boolean isModLoaded(String id) {
        return FabricLoader.getInstance().isModLoaded(id);
    }
}
