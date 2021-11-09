package com.FelisCatus.Tutorial.Util.SpecialBlocks;

import com.FelisCatus.Tutorial.List.TileEntities.GeneratorTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class GeneratorBlock extends Block
{

    public GeneratorBlock()
    {
        super(Properties.create(Material.ROCK).hardnessAndResistance(5f));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (!worldIn.isRemote())
        {

            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof GeneratorTileEntity)
            {
                NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileentity, pos);
                //括号内填Tile Entity
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return new GeneratorTileEntity();
    }

    @Override//破坏掉落物
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state)
    {
        GeneratorTileEntity tileentity = (GeneratorTileEntity) worldIn.getTileEntity(pos);
        worldIn.addEntity(new ItemEntity((World) worldIn, pos.getX(), pos.getY(), pos.getZ(), tileentity.handler.getStackInSlot(0)));
        super.onPlayerDestroy(worldIn, pos, state);
    }
}
