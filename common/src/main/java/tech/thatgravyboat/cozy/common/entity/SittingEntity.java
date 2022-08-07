package tech.thatgravyboat.cozy.common.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tech.thatgravyboat.cozy.common.blocks.base.SittingBlock;
import tech.thatgravyboat.cozy.common.registry.ModEntities;

import java.util.HashMap;
import java.util.Map;

public class SittingEntity extends Entity {

    public static final Map<BlockPos, SittingEntity> CURRENTLY_SITTING = new HashMap<>();

    private final BlockPos sittingPosition;

    public SittingEntity(EntityType<?> type, Level level) {
        super(type, level);
        this.sittingPosition = BlockPos.ZERO;
    }

    private SittingEntity(BlockPos pos, Level level) {
        super(ModEntities.SEAT.get(), level);
        this.sittingPosition = pos;
    }

    public static SittingEntity of(Level level, BlockPos pos, Direction direction) {
        SittingEntity entity = new SittingEntity(pos, level);
        entity.setYRot(direction.toYRot());
        entity.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        return entity;
    }

    @Override
    public boolean shouldRender(double d, double e, double f) {
        return false;
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    @Override
    public boolean isVehicle() {
        return true;
    }

    @Override
    public Vec3 getDismountLocationForPassenger(@Nullable LivingEntity livingEntity) {
        return new Vec3(this.getX(), this.getBoundingBox().maxY + 0.5, this.getZ());
    }

    @Override
    public void tick() {
        super.tick();
        if (!level.isClientSide() && !(this.level.getBlockState(this.sittingPosition).getBlock() instanceof SittingBlock)) {
            CURRENTLY_SITTING.remove(this.sittingPosition);
            this.discard();
        }
    }

    @Override
    protected void removePassenger(@NotNull Entity entity) {
        super.removePassenger(entity);
        if (this.getPassengers().isEmpty()) {
            CURRENTLY_SITTING.remove(this.sittingPosition);
            this.discard();
        }
    }

    //region Useless Data
    @Override
    protected void defineSynchedData() {}
    @Override
    protected void readAdditionalSaveData(@NotNull CompoundTag tag) {}
    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag tag) {}
    //endregion

    @Override
    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }
}
