package tech.thatgravyboat.cozy.common.registry.forge;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import tech.thatgravyboat.cozy.common.registry.ModBlocks;

public class ModBlocksImpl {

    public static <E extends BlockEntity> BlockEntityType<E> createBlockEntityType(ModBlocks.BlockEntityFactory<E> factory, Block... blocks) {
        return BlockEntityType.Builder.of(factory::create, blocks).build(null);
    }
}
