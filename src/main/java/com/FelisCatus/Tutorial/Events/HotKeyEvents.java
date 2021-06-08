package com.FelisCatus.Tutorial.Events;

import com.FelisCatus.Tutorial.List.KeyBindingList;
import com.FelisCatus.Tutorial.Network.ExampleNetwork;
import com.FelisCatus.Tutorial.Network.Packet.KeyInputPKT;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "tutorial", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class HotKeyEvents {

    @SubscribeEvent
    public static void onKeyPressed(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.world == null) {
            return;
        }
        int key = event.getKey();
        if (mc.currentScreen == null && KeyBindingList.exampleKeyBinding.isPressed()) {
            System.out.println("Z is pressed.");
            ExampleNetwork.CHANNEL.sendToServer(new KeyInputPKT(key));
        }
    }
}
