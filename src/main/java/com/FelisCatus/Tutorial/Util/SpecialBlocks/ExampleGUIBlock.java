package com.FelisCatus.Tutorial.Util.SpecialBlocks;

import com.FelisCatus.Tutorial.List.ItemList;
import com.FelisCatus.Tutorial.List.TileEntities.GUITileEntity;
import com.FelisCatus.Tutorial.List.TileEntityList;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ExampleGUIBlock extends Block {

    public ExampleGUIBlock() {
        super(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(5.0F).sound(SoundType.METAL));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityList.GUI_TILE_ENTITY_TYPE.get().create();
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
                                             Hand handIn, BlockRayTraceResult hit) {

        if (player.getHeldItemMainhand().getItem()==ItemList.SOUP.get())//检测手持物品是否满足条件
        {
            // 如果worldIn 没有 remote
            if (!worldIn.isRemote()) {
                TileEntity tileentity = worldIn.getTileEntity(pos);

                // 检查TileEntity是否为关于显示的TileEntity
                if (tileentity instanceof GUITileEntity) {
                    NetworkHooks.openGui((ServerPlayerEntity) player, (GUITileEntity) tileentity, pos);
                }
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

}
