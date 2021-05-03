package com.FelisCatus.Tutorial.EnergySystem;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import javax.annotation.Nonnull;

import com.FelisCatus.Tutorial.Tutorial;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class ChargedItem extends Item {

    public ChargedItem() {
        super(new Item.Properties().group(Tutorial.TUTORIAL_GROUP));
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT nbt) {
        
        return new ICapabilityProvider() {
            private LazyOptional<IEnergyStorage> lazyOptional = LazyOptional.of(() -> new IEnergyStorage() {

                @Override
                public int receiveEnergy(int maxReceive, boolean simulate) {
                    int energy = this.getEnergyStored();
                    int diff = Math.min(this.getMaxEnergyStored() - energy, maxReceive);
                    if (!simulate) {
                        stack.getOrCreateTag().putInt("BatteryEnergy", energy + diff);
                    }
                    return diff;
                }

                @Override
                public int extractEnergy(int maxExtract, boolean simulate) {
                    int energy = this.getEnergyStored();
                    int diff = Math.min(energy, maxExtract);
                    if (!simulate) {
                        stack.getOrCreateTag().putInt("BatteryEnergy", energy - diff);
                    }
                    return diff;
                }

                @Override
                public int getEnergyStored() {
                    if (stack.hasTag()) {
                        int energy = Objects.requireNonNull(stack.getTag()).getInt("BatteryEnergy");
                        return Math.max(0, Math.min(this.getMaxEnergyStored(), energy));
                    }
                    return 0;
                }

                @Override
                public int getMaxEnergyStored() {
                    return 48_000;
                }

                @Override
                public boolean canExtract() {
                    return true;
                }

                @Override
                public boolean canReceive() {
                    return true;
                }
            });

            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
                boolean isEnergy = Objects.equals(cap, CapabilityEnergy.ENERGY);
                return isEnergy ? this.lazyOptional.cast() : LazyOptional.empty();
            }
        };
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        stack.getCapability(CapabilityEnergy.ENERGY).ifPresent(e -> {
            String msg = e.getEnergyStored() + " FE / " + e.getMaxEnergyStored() + " FE";
            tooltip.add(new StringTextComponent(msg).mergeStyle(TextFormatting.GRAY));
        });
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.isInGroup(group)) {
            IntStream.rangeClosed(0, 4).forEach(i -> {
                ItemStack stack = new ItemStack(this);
                stack.getCapability(CapabilityEnergy.ENERGY).ifPresent(e -> {
                    int energy = e.getMaxEnergyStored() / 4 * i;
                    e.receiveEnergy(energy, false);
                    items.add(stack);
                });
            });
        }
    }

}
