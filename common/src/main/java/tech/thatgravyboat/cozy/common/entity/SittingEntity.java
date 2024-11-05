package tech.thatgravyboat.cozy.common.entity;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RespawnAnchorBlock;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import tech.thatgravyboat.cozy.common.blocks.base.SittingBlock;
import tech.thatgravyboat.cozy.common.registry.ModEntities;

import java.util.Collection;

public class SittingEntity extends Entity {

    public static final Multimap<ResourceKey<Level>, BlockPos> CURRENTLY_SITTING = ArrayListMultimap.create();

    private final BlockPos sittingPosition;
    private boolean markedForRemoval = false;
    private boolean canRotate = false;

    public SittingEntity(EntityType<?> type, Level level) {
        super(type, level);
        this.sittingPosition = BlockPos.ZERO;
    }

    private SittingEntity(BlockPos pos, Level level) {
        super(ModEntities.SEAT.get(), level);
        this.sittingPosition = pos;
    }

    public static SittingEntity of(Level level, BlockPos pos, Direction direction, boolean canRotate) {
        SittingEntity entity = new SittingEntity(pos, level);
        entity.canRotate = canRotate;
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
    public @NotNull Vec3 getDismountLocationForPassenger(@NotNull LivingEntity entity) {
        return RespawnAnchorBlock.findStandUpPosition(entity.getType(), this.level(), this.sittingPosition)
                .orElseGet(() -> super.getDismountLocationForPassenger(entity));
    }

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide()) {
            boolean isSitting = level().getBlockState(sittingPosition).getBlock() instanceof SittingBlock;
            if (!isSitting || markedForRemoval) {
                getMap(level()).remove(sittingPosition);
                this.discard();
            }
        }
    }

    @Override
    protected void removePassenger(@NotNull Entity entity) {
        super.removePassenger(entity);
        if (this.getPassengers().isEmpty()) {
            markedForRemoval = true;
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
    public void onPassengerTurned(@NotNull Entity entity) {
        if (!canRotate) {
            entity.setYBodyRot(this.getYRot());
            float f = Mth.wrapDegrees(entity.getYRot() - this.getYRot());
            float g = Mth.clamp(f, -105.0F, 105.0F);
            entity.yRotO += g - f;
            entity.setYRot(entity.getYRot() + g - f);
            entity.setYHeadRot(entity.getYRot());
        }
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this, canRotate ? 1 : 0);
    }

    @Override
    public void recreateFromPacket(@NotNull ClientboundAddEntityPacket packet) {
        super.recreateFromPacket(packet);
        this.canRotate = packet.getData() == 1;
    }

    public static Collection<BlockPos> getMap(Level level) {
        return CURRENTLY_SITTING.get(level.dimension());
    }
}
