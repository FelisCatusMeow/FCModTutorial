package com.FelisCatus.Tutorial.List;

import com.FelisCatus.Tutorial.Tutorial;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.awt.event.KeyEvent;

@OnlyIn(Dist.CLIENT)
public class KeyBindingList {
    public static KeyBinding exampleKeyBinding;

    private static KeyBinding _add(String name, int key) {
        return new KeyBinding("key." + Tutorial.MOD_ID + "." + name, key, "key." + Tutorial.MOD_ID + "." + "category");
    }

    public static void register(final FMLClientSetupEvent event) {
        exampleKeyBinding = _add("example_key", KeyEvent.VK_Z);
        ClientRegistry.registerKeyBinding(exampleKeyBinding);
    }
}
