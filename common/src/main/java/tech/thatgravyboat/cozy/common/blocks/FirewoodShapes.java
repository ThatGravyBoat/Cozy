package tech.thatgravyboat.cozy.common.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class FirewoodShapes {

    public static final VoxelShape EAST_WEST_ONE = Shapes.or(
        Block.box(3.0F, 0.0F, 0.0F, 7.0F, 4.0F, 16.0F),
        Block.box(9.0F, 0.0F, 0.0F, 13.0F, 4.0F, 16.0F),
        Block.box(6.0F, 4.0F, 0.0F, 10.0F, 8.0F, 16.0F)
    );
    public static final VoxelShape NORTH_SOUTH_ONE = rotate90(EAST_WEST_ONE);

    public static final VoxelShape EAST_WEST_TWO = Shapes.or(
            Block.box(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 16.0F),
            Block.box(6.0F, 0.0F, 0.0F, 10.0F, 4.0F, 16.0F),
            Block.box(12.0F, 4.0F, 0.0F, 16.0F, 4.0F, 16.0F),
            Block.box(3.0F, 4.0F, 0.0F, 7.0F, 8.0F, 16.0F),
            Block.box(9.0F, 4.0F, 0.0F, 13.0F, 8.0F, 16.0F),
            Block.box(6.0F, 8.0F, 0.0F, 10.0F, 12.0F, 16.0F)
    );

    public static final VoxelShape NORTH_SOUTH_TWO = rotate90(EAST_WEST_TWO);

    public static final VoxelShape THREE = Block.box(0.0F, 0.0F, 0.0F, 16.0F, 12.0F, 16.0F);


    private static VoxelShape rotate90(VoxelShape shape) {
        final VoxelShape[] voxelShape = {Shapes.empty()};
        shape.forAllBoxes((x1, y1, z1, x2, y2, z2) -> {
            voxelShape[0] = Shapes.or(voxelShape[0], Shapes.box(1.0D - z2, y1, x1, 1.0D - z1, y2, x2));
        });
        return voxelShape[0];
    }

}
