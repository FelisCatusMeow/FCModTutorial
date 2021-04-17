package com.FelisCatus.Tutorial.Energy.Test;



/**************************************
            =====注意！=====
本文件为测试用文件，代码可能不会正常运行!
**************************************/




import java.util.List;
import java.util.Objects;

import javax.annotation.Nonnull;

import com.FelisCatus.Tutorial.Tutorial;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;



public class ChargedItem extends Item {

    public ChargedItem() {
        super(new Item.Properties().maxStackSize(1).group(Tutorial.TUTORIAL_GROUP));

    }

    /*
     * 
     * @Override public void fillItemGroup(@Nonnull ItemGroup group, @Nonnull
     * NonNullList<ItemStack> items) { if (this.isInGroup(group)) {
     * IntStream.rangeClosed(0, 4).forEach(i -> { ItemStack stack = new
     * ItemStack(this); stack.getCapability(CapabilityEnergy.ENERGY).ifPresent(e ->
     * { int energy = e.getMaxEnergyStored() / 4 * i; e.receiveEnergy(energy,
     * false); items.add(stack); }); }); } }
     */

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(@Nonnull ItemStack stack, World worldIn, @Nonnull List<ITextComponent> tooltip,
            @Nonnull ITooltipFlag flagIn) {
        stack.getCapability(CapabilityEnergy.ENERGY).ifPresent(e -> {
            String msg = e.getEnergyStored() + "FE/" + e.getMaxEnergyStored() + "FE";
            tooltip.add(new StringTextComponent(msg).mergeStyle(TextFormatting.GRAY));
        });
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT nbt) {
        return new ICapabilityProvider() {
            private final LazyOptional<IEnergyStorage> lazyOptional = LazyOptional.of(() -> new IEnergyStorage() {

                // 接收能量
                @Override
                public int receiveEnergy(int maxReceive, boolean simulate) {
                    int energy = this.getEnergyStored();
                    int _min = Math.min(this.getEnergyStored() - energy, maxReceive);
                    if (!simulate) {
                        stack.getOrCreateTag().putInt("EnergyLeft", energy + _min);
                    }
                    return _min;
                }

                // 输出能量
                @Override
                public int extractEnergy(int maxExtract, boolean simulate) {
                    int energy = this.getEnergyStored();
                    int _min = Math.min(energy, maxExtract);
                    if (!simulate) {
                        stack.getOrCreateTag().putInt("EnergyLeft", energy - _min);
                    }
                    return _min;
                }

                // 储存能量
                @Override
                public int getEnergyStored() {

                    if (stack.hasTag()) {
                        int energy = Objects.requireNonNull(stack.getTag()).getInt("EnergyLeft");
                        return Math.max(0, Math.min(this.getEnergyStored(), energy));
                    }
                    return 0;
                }

                // 最大储存能量
                @Override
                public int getMaxEnergyStored() {

                    return 48000;
                }

                // 能否输出能量
                @Override
                public boolean canExtract() {

                    return true;
                }

                // 能否接收能量
                @Override
                public boolean canReceive() {

                    return true;
                }

            });

            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
                boolean withEnergy = Objects.equals(cap, CapabilityEnergy.ENERGY);
                if (withEnergy = true) {
                    return this.lazyOptional.cast();
                } else {
                    return LazyOptional.empty();
                }
            }

        };
    }

}
