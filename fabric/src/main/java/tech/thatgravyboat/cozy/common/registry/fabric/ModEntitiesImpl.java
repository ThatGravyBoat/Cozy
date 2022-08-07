package tech.thatgravyboat.cozy.common.registry.fabric;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import tech.thatgravyboat.cozy.Cozy;

import java.util.function.Supplier;

public class ModEntitiesImpl {

    public static <T extends Entity> Supplier<EntityType<T>> registerEntity(String id, EntityType.EntityFactory<T> factory, MobCategory category, float width, float height, boolean noSave) {
        var build = FabricEntityTypeBuilder.create(category, factory).dimensions(EntityDimensions.scalable(width, height));
        if (noSave) build = build.disableSaving();
        var object = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(Cozy.MOD_ID, id), build.build());
        return () -> object;
    }
}
