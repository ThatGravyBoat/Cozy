package tech.thatgravyboat.cozy.common.blocks.globe;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tech.thatgravyboat.cozy.common.registry.ModBlocks;

public class GlobeBlockEntity extends BlockEntity {

    private GlobeData data = GlobeData.EMPTY;

    public GlobeBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlocks.GLOBE_ENTITY.get(), blockPos, blockState);
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        this.data = GlobeData.fromNbt(tag);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        this.data.toNbt(tag);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }

    public GlobeData getData() {
        return this.data;
    }

    public InteractionResult tryTeleport(Player player) {
        if (this.data == GlobeData.EMPTY) {
            return InteractionResult.CONSUME_PARTIAL;
        }
        if (player.level().isClientSide) return InteractionResult.SUCCESS;
        BlockPos pos = this.data.pos();
        ResourceKey<Level> dimension = this.data.dim();
        if (player.level().dimension().equals(dimension)) {
            boolean canTp = player.level().getBlockState(pos).getCollisionShape(player.level(), pos).isEmpty() && player.level().getBlockState(pos.above()).getCollisionShape(player.level(), pos.above()).isEmpty();
            canTp = canTp && player.level().getBlockState(pos.below()).isFaceSturdy(player.level(), pos.below(), net.minecraft.core.Direction.UP);
            if (canTp) {
                player.setTicksFrozen(130);
                player.teleportToWithTicket(pos.getX() + 0.5, pos.getY() + 0.1, pos.getZ() + 0.5);
                return InteractionResult.SUCCESS;
            }
            player.displayClientMessage(Component.translatable("gui.cozy.item.globe.not_safe"), true);
            return InteractionResult.CONSUME_PARTIAL;
        }
        player.displayClientMessage(Component.translatable("gui.cozy.item.globe.same_dim"), true);
        return InteractionResult.CONSUME_PARTIAL;
    }
}
