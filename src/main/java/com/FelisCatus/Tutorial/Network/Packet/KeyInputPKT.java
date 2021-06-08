package com.FelisCatus.Tutorial.Network.Packet;

import com.FelisCatus.Tutorial.List.ItemList;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class KeyInputPKT {
    public int key;

    public KeyInputPKT() {

    }

    public KeyInputPKT(int key) {
        this.key = key;
    }

    public static void encode(KeyInputPKT pkt, PacketBuffer buffer) {
        buffer.writeInt(pkt.key);
    }

    public static KeyInputPKT decode(PacketBuffer buffer) {
        return new KeyInputPKT(buffer.readInt());
    }

    public static void handlePacket(KeyInputPKT pkt, Supplier<NetworkEvent.Context> context$) {
        NetworkEvent.Context context = context$.get();
        context.enqueueWork(() -> {
            ServerPlayerEntity player = context.getSender();
            if (player != null) {
                player.addItemStackToInventory(new ItemStack(ItemList.SOUP.get()));
            }
        });
        context.setPacketHandled(true);
    }
}
