package com.FelisCatus.Tutorial.Commands;

import com.FelisCatus.Tutorial.Tutorial;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;

public class SavePositionCommand
{
    public SavePositionCommand(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("position").then(Commands.literal("save").executes((command ->
        {
            return savePosition(command.getSource());
        }))));
    }

    private int savePosition(CommandSource source) throws CommandSyntaxException
    {
        ServerPlayerEntity player = source.asPlayer();
        BlockPos playerPos = player.getPosition();
        String pos = playerPos.getX() + "," + playerPos.getY() + "," + playerPos.getZ();
        player.getPersistentData().putIntArray(Tutorial.MOD_ID + ".saved_position", new int[]{playerPos.getX(), playerPos.getY(),
                playerPos.getZ()});
        source.sendFeedback(new TranslationTextComponent(Tutorial.MOD_ID + ".position.save",pos),
                true);
        return 1;
    }
}
