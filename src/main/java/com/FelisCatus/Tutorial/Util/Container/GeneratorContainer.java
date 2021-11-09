package com.FelisCatus.Tutorial.Util.Container;

import com.FelisCatus.Tutorial.List.BlockList;
import com.FelisCatus.Tutorial.List.ContainerList;
import com.FelisCatus.Tutorial.List.TileEntities.GeneratorTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.IContainerListener;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.List;

public class GeneratorContainer extends Container
{
    private final GeneratorTileEntity tileentity;
    private int energy, cookTime;
    private final IWorldPosCallable ableToInteract;
    private List<IContainerListener> listeners;

    public GeneratorContainer(PlayerInventory player, GeneratorTileEntity tileentity, final int winId)
    {
        super(ContainerList.GUI_CASE_CONTAINER_TYPE.get(), winId);
        this.tileentity = tileentity;
        this.ableToInteract = IWorldPosCallable.of(tileentity.getWorld(), tileentity.getPos());
        IItemHandler handler = (IItemHandler) tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        this.addSlot(new SlotItemHandler(handler, 0, 44, 33));

        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 9; col++)// ⑨是不是很熟悉？因为物品栏一横行有九个
            {
                this.addSlot(new Slot(player, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
            }
        }

        // 3、工具栏
        for (int col = 0; col < 9; col++)
        {
            this.addSlot(new Slot(player, col, 8 + col * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn)
    {
        return isWithinUsableDistance(ableToInteract, playerIn, BlockList.GENERATOR_BLOCK.get());
    }

    @Override
    public void updateProgressBar(int id, int data)
    {
        this.tileentity.setRun(id, data);
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); ++i)
        {
            IContainerListener listener = (IContainerListener) this.listeners.get(i);
            if (this.energy != this.tileentity.getRun(0)) listener.sendWindowProperty(this, 0, this.tileentity.getRun(0));
            if (this.cookTime != this.tileentity.getRun(1)) listener.sendWindowProperty(this, 1, this.tileentity.getRun(1));
        }

        this.energy = this.tileentity.getRun(0);
        this.cookTime = this.tileentity.getRun(1);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index)
    {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = (Slot) this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();

            if (index >= 0 && index < 27)
            {
                if (!this.mergeItemStack(stack1, 27, 36, false)) return ItemStack.EMPTY;
            } else if (index >= 27 && index < 36)
            {
                if (!this.mergeItemStack(stack1, 0, 27, false)) return ItemStack.EMPTY;
            } else if (!this.mergeItemStack(stack1, 0, 36, false))
            {
                return ItemStack.EMPTY;
            }

            if (stack1.isEmpty()) slot.putStack(ItemStack.EMPTY);
            else slot.onSlotChanged();

            if (stack1.getCount() == stack.getCount()) return ItemStack.EMPTY;
            slot.onTake(playerIn, stack1);
        }

        return stack;
    }
}
