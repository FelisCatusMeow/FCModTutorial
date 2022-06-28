package com.FelisCatus.Tutorial.Events;

import com.FelisCatus.Tutorial.Commands.GetSoupCommand;
import com.FelisCatus.Tutorial.Commands.SavePositionCommand;
import com.FelisCatus.Tutorial.Commands.TeleportSavedPositionCommand;
import com.FelisCatus.Tutorial.Tutorial;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = Tutorial.MOD_ID)
public class CommandEvents
{
    @SubscribeEvent
    public static void commandRegister(RegisterCommandsEvent event)
    {
        new GetSoupCommand(event.getDispatcher());
        new SavePositionCommand(event.getDispatcher());
        new TeleportSavedPositionCommand(event.getDispatcher());
        ConfigCommand.register(event.getDispatcher());
    }

}
