package com.FelisCatus.Tutorial.Util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class CustomBlock extends HorizontalBlockBase {

    private static VoxelShape shape;
    static {
        VoxelShape shape1 = Block.makeCuboidShape(3, 0, 5, 14, 1, 11);
        VoxelShape shape2 = Block.makeCuboidShape(4, 1, 5, 12, 2, 6);

        shape = VoxelShapes.or(shape1, shape2);
    }

    public CustomBlock(Properties properties) {
        super(properties);
        runCalculation(shape);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPES.get(this).get(state.get(HORIZONTAL_FACING));
    }
}
