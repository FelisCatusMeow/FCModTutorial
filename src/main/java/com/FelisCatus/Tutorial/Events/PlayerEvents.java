package com.FelisCatus.Tutorial.Events;

import com.FelisCatus.Tutorial.Tutorial;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Tutorial.MOD_ID)
public class PlayerEvents
{
    @SubscribeEvent
    public static void onPlayerCloneData(PlayerEvent.Clone event)
    {
        if (!event.getOriginal().getEntityWorld().isRemote())
        {
            event.getPlayer().getPersistentData().putIntArray(Tutorial.MOD_ID + ".saved_position",
                    event.getOriginal().getPersistentData().getIntArray(Tutorial.MOD_ID + ".saved_position"));
        }
    }
}
