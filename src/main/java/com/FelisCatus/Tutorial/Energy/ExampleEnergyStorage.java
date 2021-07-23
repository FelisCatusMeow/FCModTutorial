package com.FelisCatus.Tutorial.Energy;



//别信这里面任何一句代码




import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.energy.EnergyStorage;

public class ExampleEnergyStorage extends EnergyStorage {
    public ExampleEnergyStorage(int capacity) {
        super(capacity, capacity, capacity, 0);
    }

    public ExampleEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer, maxTransfer, 0);
    }

    public ExampleEnergyStorage(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract, 0);
    }

    public ExampleEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) {
        super(capacity, maxReceive, maxExtract, energy);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return super.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return super.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored() {
        return super.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        return super.getMaxEnergyStored();
    }

    @Override
    public boolean canExtract() {
        return super.canExtract();
    }

    @Override
    public boolean canReceive() {
        return super.canReceive();
    }

    //写入NBT
    public void writeNBT(CompoundNBT compound) {
        compound.putInt("Energy", this.energy);
        compound.putInt("Capacity", this.capacity);
        compound.putInt("MaxReceive", this.maxReceive);
        compound.putInt("MaxExtract", this.maxExtract);
    }

    //读取NBT
    public void readNBT(CompoundNBT compound) {
        this.energy = compound.getInt("Energy");
        this.capacity = compound.getInt("Capacity");
        this.maxReceive = compound.getInt("MaxReceive");
        this.maxExtract = compound.getInt("MaxExtract");
    }

}
