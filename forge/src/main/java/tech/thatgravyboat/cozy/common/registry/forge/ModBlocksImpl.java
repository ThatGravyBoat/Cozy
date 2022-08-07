package tech.thatgravyboat.cozy.common.registry.forge;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import tech.thatgravyboat.cozy.Cozy;
import tech.thatgravyboat.cozy.common.registry.ModBlocks;

import java.util.function.Supplier;

public class ModBlocksImpl {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Cozy.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Cozy.MOD_ID);

    public static <T extends Block> Supplier<T> registerBlock(String id, Supplier<T> block) {
        return BLOCKS.register(id, block);
    }

    public static <E extends BlockEntity, T extends BlockEntityType<E>> Supplier<T> registerBlockEntity(String id, Supplier<T> item) {
        return BLOCK_ENTITIES.register(id, item);
    }

    public static <E extends BlockEntity> BlockEntityType<E> createBlockEntityType(ModBlocks.BlockEntityFactory<E> factory, Block... blocks) {
        return BlockEntityType.Builder.of(factory::create, blocks).build(null);
    }
}
