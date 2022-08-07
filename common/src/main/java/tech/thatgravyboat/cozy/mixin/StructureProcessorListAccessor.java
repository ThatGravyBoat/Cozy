package tech.thatgravyboat.cozy.mixin;

import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(StructureProcessorList.class)
public interface StructureProcessorListAccessor {

    @Accessor("list")
    void setList(List<StructureProcessor> list);
}
