package com.FelisCatus.Tutorial.ToolItem;

import com.FelisCatus.Tutorial.Util.CustomItemTier;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;

public class PinkSword extends SwordItem {

    public PinkSword() 
    {
        super(CustomItemTier.ExampleTier, 8, -2.4F,new Item.Properties().group(ItemGroup.COMBAT));
    }
    
}
