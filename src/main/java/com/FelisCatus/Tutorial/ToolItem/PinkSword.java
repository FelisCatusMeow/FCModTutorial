package com.FelisCatus.Tutorial.ToolItem;

import com.FelisCatus.Tutorial.Util.CustomItemTier;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class PinkSword extends SwordItem {

    public PinkSword() 
    {
        super(CustomItemTier.ExampleTier, 8, -2.4F,new Item.Properties().group(ItemGroup.COMBAT));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        if(Screen.hasShiftDown())
        {
            tooltip.add(new TranslationTextComponent("tooltip.tutorial.pink_sword_shift"));

        }else
        {
            tooltip.add(new TranslationTextComponent("tooltip.tutorial.pink_sword"));
        }


        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}