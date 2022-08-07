package tech.thatgravyboat.cozy;

import tech.thatgravyboat.cozy.common.registry.ModBlocks;
import tech.thatgravyboat.cozy.common.registry.ModEntities;
import tech.thatgravyboat.cozy.common.registry.ModItems;
import tech.thatgravyboat.cozy.common.registry.ModRecipes;
import tech.thatgravyboat.cozy.common.utils.PiePlacer;

public class Cozy {
    public static final String MOD_ID = "cozy";

    public static void init() {
        ModBlocks.init();
        ModItems.init();
        ModEntities.init();
        ModRecipes.init();
    }
}