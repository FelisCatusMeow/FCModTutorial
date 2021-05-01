package com.FelisCatus.Tutorial.Util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class CustomBlock extends Block {

    private static VoxelShape shape;
    static
    {
        VoxelShape column0 = Block.makeCuboidShape(0, 0, 0, 14, 2, 16);
        //"from": [0, 0, 0],
		//"to": [14, 2, 16],

        VoxelShape column1 = Block.makeCuboidShape(0, 2, 0, 2, 14, 16);
        //"from": [0, 2, 0],
		//"to": [2, 14, 16],

        shape = VoxelShapes.or(column0, column1);
    }


    public CustomBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return shape;
    }
    
}
