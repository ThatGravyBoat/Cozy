package tech.thatgravyboat.cozy.common.registry;

import com.google.common.collect.ImmutableSet;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.flag.FeatureFlags;
import tech.thatgravyboat.cozy.Cozy;
import tech.thatgravyboat.cozy.common.entity.SittingEntity;

import java.util.function.Supplier;

public class ModEntities {

    public static final ResourcefulRegistry<EntityType<?>> ENTITIES = ResourcefulRegistries.create(BuiltInRegistries.ENTITY_TYPE, Cozy.MOD_ID);

    public static final Supplier<EntityType<SittingEntity>> SEAT = ENTITIES.register("seat", () -> new EntityType<>(
            SittingEntity::new,
            MobCategory.MISC,
            false,
            false,
            true,
            true,
            ImmutableSet.of(),
            EntityDimensions.scalable(0.875F, 0.625F),
            5, 3,
            FeatureFlags.DEFAULT_FLAGS
    ));
}
