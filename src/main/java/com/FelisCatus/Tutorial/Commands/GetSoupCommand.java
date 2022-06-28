package com.FelisCatus.Tutorial.Commands;

import com.FelisCatus.Tutorial.List.ItemList;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TranslationTextComponent;

public class GetSoupCommand
{
    //   /get soup
    public GetSoupCommand(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("get").then(Commands.literal("soup").executes((command) ->
        {
            return getSoup(command.getSource());
        })));
    }

    private int getSoup(CommandSource source) throws CommandSyntaxException
    {
        ServerPlayerEntity player = source.asPlayer();
        player.addItemStackToInventory(new ItemStack(ItemList.SOUP.get(),1));
        source.sendFeedback(new TranslationTextComponent("tutorial.get.soup"), true);
        return 1;
    }
}
