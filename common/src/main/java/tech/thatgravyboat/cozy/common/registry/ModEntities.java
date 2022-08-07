package tech.thatgravyboat.cozy.common.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import tech.thatgravyboat.cozy.common.entity.SittingEntity;

import java.util.function.Supplier;

public class ModEntities {

    public static final Supplier<EntityType<SittingEntity>> SEAT = registerEntity("seat", SittingEntity::new, MobCategory.MISC, 0.875F, 0.625F, true);

    public static void init() {
        //Init class into class loader.
    }

    @ExpectPlatform
    public static <T extends Entity> Supplier<EntityType<T>> registerEntity(String id, EntityType.EntityFactory<T> factory, MobCategory category, float width, float height, boolean noSave) {
        throw new AssertionError();
    }
}
