package com.FelisCatus.Tutorial.Miscellaneous;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class RotateBlock extends Block{

    public RotateBlock(Properties properties) {
        super(properties);
    }
    
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state,@Nullable LivingEntity placer, ItemStack stack) {
        if(placer != null)
        {
            worldIn.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromEntity(pos,placer)), 2);
        }
        
        }
        public static Direction getFacingFromEntity(BlockPos clickedBlock, LivingEntity entity) {
            Vector3d vec = entity.getPositionVec();
            return Direction.getFacingFromVector((float)(vec.x - clickedBlock.getX()), (float)(vec.y - clickedBlock.getY()), (float)(vec.z - clickedBlock.getZ()));
    }
    @Override
    protected void fillStateContainer(Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING);
    }
}
