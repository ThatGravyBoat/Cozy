package tech.thatgravyboat.cozy.common.blocks.globe;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.material.MapColor;

public record GlobeData(ResourceKey<Level> dim, BlockPos pos, MapColor[][][] colors) {

    public static final GlobeData EMPTY = new GlobeData(Level.OVERWORLD, BlockPos.ZERO, new MapColor[16][16][16]);

    public static GlobeData fromLevel(Level level, BlockPos pos) {
        int x = SectionPos.blockToSectionCoord(pos.getX());
        int y = SectionPos.blockToSectionCoord(pos.getY());
        int z = SectionPos.blockToSectionCoord(pos.getZ());
        LevelChunkSection section = level.getChunk(x, z).getSection(level.getSectionIndexFromSectionY(y));
        MapColor[][][] colors = new MapColor[16][16][16];
        if (section != null) {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    for (int k = 0; k < 16; k++) {
                        BlockPos blockPos = new BlockPos(SectionPos.sectionToBlockCoord(x) + i, SectionPos.sectionToBlockCoord(y) + j, SectionPos.sectionToBlockCoord(z) + k);
                        colors[i][j][k] = section.getBlockState(i, j, k).getMapColor(level, blockPos);
                    }
                }
            }
        }
        return new GlobeData(level.dimension(), pos, colors);
    }

    public static GlobeData fromNbt(CompoundTag tag) {
        ListTag colorsTag = tag.getList("Colors", Tag.TAG_BYTE);
        MapColor[][][] colors = new MapColor[16][16][16];
        if (!colorsTag.isEmpty()) {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    for (int k = 0; k < 16; k++) {
                        byte color = ((ByteTag) colorsTag.get(i * 256 + j * 16 + k)).getAsByte();
                        if (color >= 0 && color < 64) {
                            try {
                                colors[i][j][k] = MapColor.byId(color);
                            } catch (Exception ignored) {
                            }
                        }
                    }
                }
            }
        }
        ResourceLocation dim = ResourceLocation.tryParse(tag.getString("Dimension"));
        if (dim == null) return EMPTY;
        return new GlobeData(ResourceKey.create(Registries.DIMENSION, dim), NbtUtils.readBlockPos(tag.getCompound("GlobePos")), colors);
    }

    public CompoundTag toNbt(CompoundTag compoundTag) {
        if (!isEmpty()) {
            ListTag tag = new ListTag();
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    for (int k = 0; k < 16; k++) {
                        MapColor color = colors[i][j][k];
                        tag.add(ByteTag.valueOf((byte) (color != null ? color.id : -1)));
                    }
                }
            }
            compoundTag.put("Colors", tag);
        }
        compoundTag.put("GlobePos", NbtUtils.writeBlockPos(pos));
        compoundTag.putString("Dimension", dim.location().toString());
        return compoundTag;
    }

    public boolean isEmpty() {
        if (this == EMPTY) {
            return true;
        }
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                for (int k = 0; k < 16; k++) {
                    if (colors[i][j][k] != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
