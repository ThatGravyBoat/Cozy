package tech.thatgravyboat.cozy.common.registry.fabric;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import tech.thatgravyboat.cozy.Cozy;
import tech.thatgravyboat.cozy.common.registry.ModBlocks;

import java.util.function.Supplier;

public class ModBlocksImpl {
    public static <T extends Block> Supplier<T> registerBlock(String id, Supplier<T> block) {
        var output = Registry.register(Registry.BLOCK, new ResourceLocation(Cozy.MOD_ID, id), block.get());
        return () -> output;
    }

    public static <E extends BlockEntity, T extends BlockEntityType<E>> Supplier<T> registerBlockEntity(String id, Supplier<T> item) {
        var register = Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Cozy.MOD_ID, id), item.get());
        return () -> register;
    }

    public static <E extends BlockEntity> BlockEntityType<E> createBlockEntityType(ModBlocks.BlockEntityFactory<E> factory, Block... blocks) {
        return FabricBlockEntityTypeBuilder.create(factory::create, blocks).build();
    }
}
