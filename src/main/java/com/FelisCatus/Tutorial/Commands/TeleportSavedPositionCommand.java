package com.FelisCatus.Tutorial.Commands;

import com.FelisCatus.Tutorial.Tutorial;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class TeleportSavedPositionCommand
{
    public TeleportSavedPositionCommand(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("back").then(Commands.literal("position").executes((command ->
        {
            return teleportSavedPosition(command.getSource());
        }))));
    }

    private int teleportSavedPosition(CommandSource source) throws CommandSyntaxException
    {
        ServerPlayerEntity player = source.asPlayer();
        if (player.getPersistentData().getIntArray(Tutorial.MOD_ID + ".saved_position").length != 0)
        {
            int[] playerPos = player.getPersistentData().getIntArray(Tutorial.MOD_ID + ".saved_position");
            player.setPositionAndUpdate(playerPos[0], playerPos[1], playerPos[2]);
            source.sendFeedback(new TranslationTextComponent("tutorial.command.teleported"), true);
            return 1;
        } else
        {
            source.sendFeedback(new TranslationTextComponent("tutorial.command.teleported.missing"), true);
            return -1;
        }
    }
}
