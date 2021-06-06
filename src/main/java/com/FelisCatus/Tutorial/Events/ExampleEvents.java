package com.FelisCatus.Tutorial.Events;

import com.FelisCatus.Tutorial.List.BlockList;
import com.FelisCatus.Tutorial.List.ItemList;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "tutorial", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ExampleEvents {
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getState().getBlock().equals(BlockList.TUTORIAL_BLOCK.get())) {
            event.setExpToDrop(50);
            event.getExpToDrop();
            event.getPlayer().addItemStackToInventory(new ItemStack(ItemList.SOUP.get(),1));
        }
    }

}
