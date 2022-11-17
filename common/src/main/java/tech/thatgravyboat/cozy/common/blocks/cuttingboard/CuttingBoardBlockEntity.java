package tech.thatgravyboat.cozy.common.blocks.cuttingboard;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tech.thatgravyboat.cozy.common.registry.ModBlocks;
import tech.thatgravyboat.cozy.common.registry.ModRecipes;

public class CuttingBoardBlockEntity extends BlockEntity {

    private ItemStack item = ItemStack.EMPTY;

    public int randomDegree = 0;

    private int uses = 0;
    private int maxUses = 0;

    public CuttingBoardBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlocks.CUTTING_BOARD_ENTITY.get(), blockPos, blockState);
    }

    public boolean use() {
        if (maxUses == 0) return false;
        uses++;
        if (uses >= maxUses && level != null) {
            var recipe = level.getRecipeManager().getRecipeFor(ModRecipes.CUTTING_BOARD_RECIPE.get(), new SimpleContainer(item), level);
            if (recipe.isEmpty()) {
                this.updateMaxUses();
                return false;
            }
            ItemStack output = recipe.get().output();
            if (recipe.get().copyNbt() && item.hasTag()) {
                output.setTag(item.getTag());
            }
            this.item = ItemStack.EMPTY;
            if (level != null) {
                level.addFreshEntity(new ItemEntity(level, getBlockPos().getX() + 0.5, getBlockPos().getY(), getBlockPos().getZ() + 0.5, output));
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
            this.updateMaxUses();
        }
        return true;
    }

    public ItemStack setItem(ItemStack item) {
        this.item = item.copy();
        this.item.setCount(1);
        item.shrink(1);
        if (level != null)
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        this.updateMaxUses();
        return item;
    }

    public ItemStack getAndRemoveItem() {
        var item = this.item;
        this.item = ItemStack.EMPTY;
        if (level != null)
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        return item;
    }

    public void updateMaxUses() {
        if (level == null || this.item.isEmpty()) {
            this.uses = 0;
            this.maxUses = 0;
            return;
        }
        var recipe = level.getRecipeManager().getRecipeFor(ModRecipes.CUTTING_BOARD_RECIPE.get(), new SimpleContainer(item), level);
        if (recipe.isEmpty()) {
            this.uses = 0;
            this.maxUses = 0;
            return;
        }
        this.uses = 0;
        this.maxUses = recipe.get().uses();
    }

    public ItemStack getItem() {
        return item;
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        if (!item.isEmpty()) {
            tag.put("Item", item.save(new CompoundTag()));
        }
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        item = tag.contains("Item") ? ItemStack.of(tag.getCompound("Item")) : ItemStack.EMPTY;
        updateMaxUses();
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        if (!item.isEmpty()) {
            tag.put("Item", item.save(new CompoundTag()));
        }
        return tag;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
