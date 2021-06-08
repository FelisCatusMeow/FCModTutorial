package com.FelisCatus.Tutorial.Util.SpecialBlocks;

//<---这是一个测试用文档，代码可行度未知--->//
/*
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.state.IntegerProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class ExampleFurnace extends ContainerBlock {
    public ExampleFurnace() {
        super(AbstractBlock.Properties.create(Material.ROCK).setLightLevel(ExampleFurnace::getLightValue));//TODO 设置亮度
        BlockState defaultState = this.getStateContainer().getBaseState().with(BURNING_OR_NOT,0);//TODO
        this.setDefaultState(defaultState);
    }

    public static final IntegerProperty BURNING_OR_NOT =
            IntegerProperty.create("burning_or_not", 0, 1);

    public static int getLightValue(BlockState state) {
        int lightValue = 0;
        Integer isBurning = state.get(BURNING_OR_NOT);
        if (isBurning == 0) {
            lightValue = 0;
        } else {
            lightValue = 15;
        }
        lightValue = (int) MathHelper.clampedLerp(lightValue,0,15);
        return lightValue;
    }

    //创建TileEntity

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return createNewTileEntity(world);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        reutrn;//TODO return一个TileEntity
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    //右击时call，打开gui

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) return ActionResultType.SUCCESS;//CLIENT留空
        INamedContainerProvider namedContainerProvider = this.getContainer(state, worldIn, pos);
        if (namedContainerProvider != null) {
            if (!(player instanceof ServerPlayerEntity)) return ActionResultType.FAIL;
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) player;
            NetworkHooks.openGui(serverPlayerEntity, namedContainerProvider, (packetBuffer) -> {
            });
        }
        return ActionResultType.SUCCESS;
    }

}


 */