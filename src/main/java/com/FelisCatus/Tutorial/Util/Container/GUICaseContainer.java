package com.FelisCatus.Tutorial.Util.Container;

import com.FelisCatus.Tutorial.List.BlockList;
import com.FelisCatus.Tutorial.List.ContainerList;
import com.FelisCatus.Tutorial.List.TileEntities.GUITileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

import java.util.Objects;

public class GUICaseContainer extends Container {

    public final GUITileEntity tileentity;
    private final IWorldPosCallable ableToInteract;

    public GUICaseContainer(final int winId, final PlayerInventory playerIvty, final GUITileEntity tileentity) {
        super(ContainerList.GUI_CASE_CONTAINER_TYPE.get(), winId);
        this.tileentity = tileentity;
        this.ableToInteract = IWorldPosCallable.of(tileentity.getWorld(), tileentity.getPos());

        // 创建slots

        // 1、Tile Entity

        this.addSlot(new Slot((IInventory) tileentity, 0, 80, 35)); //0,80,35
        this.addSlot(new Slot((IInventory) tileentity, 1, 98,35));
        this.addSlot(new Slot((IInventory) tileentity, 2, 116,35));

        // 2、创建玩家背包物品栏

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++)// ⑨是不是很熟悉？因为物品栏一横行有九个
            {
                this.addSlot(new Slot(playerIvty, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
            }
        }

        // 3、工具栏
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerIvty, col, 8 + col * 18, 142));
        }

    }

    public GUICaseContainer(final int winId, final PlayerInventory playerIvty, final PacketBuffer data) {
        this(winId, playerIvty, getTileEntity(playerIvty, data));
    }

    // 创建从data里获取Tile Entity的方法
    private static GUITileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {

        // 检查物品栏和data是否为null
        // requireNonNull用法: requireNonNull(检查对象, Exception);
        Objects.requireNonNull(playerInventory, "玩家物品栏不能为null");
        Objects.requireNonNull(data, "Packet Buffer不能为null");

        // 获取Tile Entity

        final TileEntity tileentity = playerInventory.player.world.getTileEntity(data.readBlockPos());

        // 如果tileentity是GUITileEntity的实例
        if (tileentity instanceof GUITileEntity) {
            return ((GUITileEntity) tileentity);
        }
        // 否则报错
        throw new IllegalStateException("Invalid Tile Entity");
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(ableToInteract, playerIn, BlockList.EXAMPLE_GUI_BLOCK.get());
    }

    // 把ItemStack转移到我们看到的另一个容器里的slot
    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        // 检查slot是否不为null且有getHasStack
        if (slot != null && slot.getHasStack()) {
            // 如果满足条件。则创建另一个ItemStack
            ItemStack stack_ = slot.getStack();
            stack = stack_.copy();

            // 考虑各种情况
            if (index < GUITileEntity.slots &&
            /* 给玩家和container中第一个可用的提供ItemStack */
                    !this.mergeItemStack(stack_, GUITileEntity.slots, this.inventorySlots.size(), true)) {
                
                        return ItemStack.EMPTY;
            }

            if (!this.mergeItemStack(stack_, 0, GUITileEntity.slots, false)) {
                return ItemStack.EMPTY;
            }

            if (stack_.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);// 把stack放进slot里
            } else {
                slot.onSlotChanged();// slot里的stack发生改变时call
            }

        }
        return stack;
    }

}
