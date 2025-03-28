package com.magicshop.net;

import com.magicshop.MagicShop;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessage {
    private static SimpleChannel NETWORK;

    private  static  int packetId = 0;

    private static int id(){
        return packetId++;
    }

    public static void register(){//相当于这个网络的构造
        //(ResourceLocation name, Supplier<String> networkProtocolVersion, Predicate<String>
        // clientAcceptedVersions, Predicate<String> serverAcceptedVersions)
        NETWORK = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(MagicShop.MODID, "main"),
                ()->"1.0",
                s->true,
                s->true);
        //注册各种包
        NETWORK.messageBuilder(DefusePack.class,id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(DefusePack::encode) // 绑定编码方法
                .decoder(DefusePack::decode)                   // 绑定解码方法
                .consumerMainThread(DefusePack::handle) // 绑定处理器
                .add();
        NETWORK.messageBuilder(KeyPack.class,id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(KeyPack::encode) // 绑定编码方法
                .decoder(KeyPack::decode)                   // 绑定解码方法
                .consumerMainThread(KeyPack::handle) // 绑定处理器
                .add();
    }



    //万能发包
    public static <MSG> void sendToServer(MSG Message){
        NETWORK.sendToServer(Message);//发包
    }
    public static <MSG> void sendToPlayer(MSG Message, ServerPlayer player){
        NETWORK.send(PacketDistributor.PLAYER.with(()->player),Message);
    }

}
