package com.FelisCatus.Tutorial.List.TileEntities;

import com.FelisCatus.Tutorial.Energy.ExampleEnergyStorage;
import com.FelisCatus.Tutorial.List.TileEntityList;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GeneratorTileEntity extends TileEntity implements ITickable
{


    public ItemStackHandler handler = new ItemStackHandler(1);

    private ExampleEnergyStorage storage = new ExampleEnergyStorage(100000);
    public int time;
    public int energy = storage.getEnergyStored();
    private final LazyOptional<IEnergyStorage> lazyOptional = LazyOptional.of(() -> storage);


    public int maxminum_time = 30;

    public GeneratorTileEntity(TileEntityType<?> tileEntityTypeIn)
    {
        super(tileEntityTypeIn);
    }

    public GeneratorTileEntity()
    {
        this(TileEntityList.GENERATOR_TILE_ENTITY.get());
    }

    @Override
    public void tick()
    {
        if (!handler.getStackInSlot(0).isEmpty() && hasItem(handler.getStackInSlot(0)))
        {
            time++;
            if (time == maxminum_time)
            {
                energy += getCoalValue(handler.getStackInSlot(0));
                handler.getStackInSlot(0).shrink(1);
                time = 0;
            }
        }
    }

    private boolean hasItem(ItemStack stackInSlot)
    {
        return getCoalValue(stackInSlot) > 0;
    }

    private int getCoalValue(ItemStack stack)
    {
        if (stack.getItem() == Items.COAL)
            return 1500;
        else return 0;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {
        if (cap == CapabilityEnergy.ENERGY) return this.lazyOptional.cast();
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return this.lazyOptional.empty();
        return super.getCapability(cap, side);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        super.write(compound);
        compound.putInt("Time", time);
        compound.put("Inventory", this.handler.serializeNBT());
        compound.putInt("EnergyG", this.storage.getEnergyStored());
        this.storage.writeNBT(compound);
        return compound;
    }


    @Override
    public void read(BlockState state, CompoundNBT nbt)
    {
        super.read(state, nbt);
        this.handler.deserializeNBT(nbt.getCompound("Inventory"));
        this.time = nbt.getInt("Time");
        this.energy = nbt.getInt("EnergyG");
        this.storage.readNBT(nbt);
    }

    public int getEnergyStored()
    {
        return this.energy;
    }

    public int getMaxEnergyStored()
    {
        return this.storage.getMaxEnergyStored();
    }

    public int getRun(int id)
    {
        if(id==0)return this.energy;
        else if(id == 1) return this.time;
        else return 0;
    }

    public void setRun(int id, int value)
    {
        if(id == 0) this.energy = value;
        else if(id ==1) this.time =value;
    }

}
