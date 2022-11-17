package tech.thatgravyboat.cozy.common.utils;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.Util;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class WoolHelper {

    private static final BiMap<Block, DyeColor> BLOCK_DYE_COLOR = Util.make(HashBiMap.create(), map -> {
        map.put(Blocks.WHITE_WOOL, DyeColor.WHITE);
        map.put(Blocks.ORANGE_WOOL, DyeColor.ORANGE);
        map.put(Blocks.MAGENTA_WOOL, DyeColor.MAGENTA);
        map.put(Blocks.LIGHT_BLUE_WOOL, DyeColor.LIGHT_BLUE);
        map.put(Blocks.YELLOW_WOOL, DyeColor.YELLOW);
        map.put(Blocks.LIME_WOOL, DyeColor.LIME);
        map.put(Blocks.PINK_WOOL, DyeColor.PINK);
        map.put(Blocks.GRAY_WOOL, DyeColor.GRAY);
        map.put(Blocks.LIGHT_GRAY_WOOL, DyeColor.LIGHT_GRAY);
        map.put(Blocks.CYAN_WOOL, DyeColor.CYAN);
        map.put(Blocks.PURPLE_WOOL, DyeColor.PURPLE);
        map.put(Blocks.BLUE_WOOL, DyeColor.BLUE);
        map.put(Blocks.BROWN_WOOL, DyeColor.BROWN);
        map.put(Blocks.GREEN_WOOL, DyeColor.GREEN);
        map.put(Blocks.RED_WOOL, DyeColor.RED);
        map.put(Blocks.BLACK_WOOL, DyeColor.BLACK);
    });

    public static DyeColor getDyeColor(ItemLike itemLike) {
       if (itemLike instanceof Block block) {
           return getDyeColor(block);
       }
       if (itemLike instanceof BlockItem blockItem) {
           return getDyeColor(blockItem.getBlock());
       }
       return null;
    }

    public static DyeColor getDyeColor(Block block) {
        return BLOCK_DYE_COLOR.get(block);
    }

    public static Block getBlock(DyeColor dyeColor) {
        return BLOCK_DYE_COLOR.inverse().get(dyeColor);
    }

}
