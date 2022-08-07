package tech.thatgravyboat.cozy.common.registry.forge;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import tech.thatgravyboat.cozy.Cozy;

import java.util.function.Supplier;

public class ModEntitiesImpl {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Cozy.MOD_ID);

    public static <T extends Entity> Supplier<EntityType<T>> registerEntity(String id, EntityType.EntityFactory<T> factory, MobCategory category, float width, float height, boolean noSave) {
        return ENTITIES.register(id, () -> {
            var build = EntityType.Builder.of(factory, category).sized(width, height);
            if (noSave) build = build.noSave();
            return build.build(id);
        });
    }
}
