package com.FelisCatus.Tutorial.List.TileEntities;

import com.FelisCatus.Tutorial.Tutorial;
import com.FelisCatus.Tutorial.List.TileEntityList;
import com.FelisCatus.Tutorial.Util.Container.GUICaseContainer;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

//物品格（slot）

public class GUITileEntity extends LockableLootTileEntity {

    public static int slots = 3;// 一个物品格

    // 用空填充ItemStack
    protected NonNullList<ItemStack> stacks = NonNullList.withSize(slots, ItemStack.EMPTY);

    protected GUITileEntity(TileEntityType<?> typeIn) {
        super(typeIn);
    }

    public GUITileEntity() {
        this(TileEntityList.GUI_TILE_ENTITY_TYPE.get());
    }

    @Override
    public int getSizeInventory() {
        // 与withSize一样
        return slots;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {

        return this.stacks;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.stacks = itemsIn;
    }

    @Override
    protected ITextComponent getDefaultName() {

        return new TranslationTextComponent("container." + Tutorial.MOD_ID + ".example_gui_block");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {

        return new GUICaseContainer(id, player, this);
    }


    //||||||||||//
    //用于储存物品//
    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        //检查有没有写入compound
        if(!this.checkLootAndWrite(compound))
        {
            //如果没有,保存所有物品
            ItemStackHelper.saveAllItems(compound,stacks);
        }
        return compound;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        //想要读取的时候，先读取所有需要的data
        this.stacks = NonNullList.withSize(getSizeInventory(),ItemStack.EMPTY);

        //加载所有物品

        //检查
        if(!this.checkLootAndRead(nbt))
        {
            //与上面大同小异
            ItemStackHelper.loadAllItems(nbt, this.stacks);
        }
    }
    //用于读取物品//
    //||||||||||//

    

}
