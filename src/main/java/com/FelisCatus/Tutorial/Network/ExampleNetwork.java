package com.FelisCatus.Tutorial.Network;

import com.FelisCatus.Tutorial.Network.packets.KeyInputPKT;
import com.FelisCatus.Tutorial.Tutorial;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class ExampleNetwork {
    public static final String NETWORK_VER = "0.1";
    //创建Simple Channel
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(Tutorial.MOD_ID, "network"),
            () -> NETWORK_VER, version -> version.equals(NETWORK_VER), version -> version.equals(NETWORK_VER));

    //注册创建的包
    public static void list() {
        CHANNEL.registerMessage(0, KeyInputPKT.class, KeyInputPKT::encode, KeyInputPKT::decode, KeyInputPKT::handlePacket);
    }
}
