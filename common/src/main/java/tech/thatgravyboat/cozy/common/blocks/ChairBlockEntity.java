package tech.thatgravyboat.cozy.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tech.thatgravyboat.cozy.Cozy;
import tech.thatgravyboat.cozy.common.registry.ModBlocks;

import java.util.Objects;

public class ChairBlockEntity extends BlockEntity {

    @Nullable
    private DyeColor color = null;

    public ChairBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlocks.CHAIR_ENTITY.get(), blockPos, blockState);
    }

    public boolean hasColor() {
        return color != null;
    }

    public @Nullable DyeColor getColor() {
        return color;
    }

    public void setColor(@Nullable DyeColor color) {
        this.color = color;
    }

    /**
     * @throws NullPointerException if the color is null
     */
    @NotNull
    public ResourceLocation getModel() {
        Objects.requireNonNull(color);
        return new ResourceLocation(Cozy.MOD_ID, "block/cushion/" + color.getName());
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        if (color != null) {
            tag.putInt("color", color.getId());
        }
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        color = tag.contains("color") ? DyeColor.byId(tag.getInt("color")) : null;
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        saveAdditional(tag);
        return tag;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
