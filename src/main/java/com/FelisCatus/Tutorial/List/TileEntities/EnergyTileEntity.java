package com.FelisCatus.Tutorial.List.TileEntities;

//别信这个里面的任何一句代码

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EnergyTileEntity extends TileEntity implements ITickableTileEntity {


    public EnergyTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return super.getCapability(cap, side);
    }

    //hasCapability去哪了？？？？？
    //hasCapability去哪了？？？？？
    //hasCapability去哪了？？？？？
    //hasCapability去哪了？？？？？
    //hasCapability去哪了？？？？？
    //hasCapability去哪了？？？？？
    //hasCapability去哪了？？？？？
    //hasCapability去哪了？？？？？
    //hasCapability去哪了？？？？？
    //hasCapability去哪了？？？？？


    @Override
    public void tick() {

    }
}
