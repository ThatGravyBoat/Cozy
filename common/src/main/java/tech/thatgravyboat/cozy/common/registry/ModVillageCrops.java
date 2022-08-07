package tech.thatgravyboat.cozy.common.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import tech.thatgravyboat.cozy.mixin.StructureProcessorListAccessor;

import java.util.ArrayList;
import java.util.List;

/**
 * This is taken from <a href="https://gist.github.com/TelepathicGrunt/c02333993a1c35dea26fdb98fead5074">Telepathic Grunt addModdedCropsToVillageFarms.java</a> and modified for
 * multi loader.
 */
public class ModVillageCrops {

    public static void addNewVillageCrop(MinecraftServer server) {
        Registry<StructureProcessorList> registry = server.registryAccess().registry(Registry.PROCESSOR_LIST_REGISTRY).orElseThrow();

        List<StructureProcessor> replacers = List.of(
            new RuleProcessor(List.of(new ProcessorRule(
                new RandomBlockMatchTest(Blocks.WHEAT, 0.05F),
                AlwaysTrueTest.INSTANCE,
                ModBlocks.TOMATO_CROP.get().defaultBlockState()
            ))),
            new RuleProcessor(List.of(new ProcessorRule(
                    new RandomBlockMatchTest(Blocks.POTATOES, 0.1F),
                    AlwaysTrueTest.INSTANCE,
                    ModBlocks.TOMATO_CROP.get().defaultBlockState()
            ))),
            new RuleProcessor(List.of(new ProcessorRule(
                    new RandomBlockMatchTest(Blocks.CARROTS, 0.1F),
                    AlwaysTrueTest.INSTANCE,
                    ModBlocks.TOMATO_CROP.get().defaultBlockState()
            )))
        );

        addNewProcessorRule(new ResourceLocation("minecraft:farm_plains"), replacers, registry);
        addNewProcessorRule(new ResourceLocation("minecraft:farm_savanna"), replacers, registry);
        addNewProcessorRule(new ResourceLocation("minecraft:farm_desert"), replacers, registry);
        addNewProcessorRule(new ResourceLocation("repurposed_structures:villages/badlands/crop_replacement"), replacers, registry);
    }

    private static void addNewProcessorRule(ResourceLocation target, List<StructureProcessor> processors, Registry<StructureProcessorList> processorListRegistry) {
        processorListRegistry.getOptional(target).ifPresent(struct -> ((StructureProcessorListAccessor)struct).setList(createList(struct.list(), processors)));
    }

    @SafeVarargs
    private static <T> List<T> createList(List<T>... lists) {
        List<T> result = new ArrayList<>();
        for (List<T> list : lists) result.addAll(list);
        return result;
    }
}
