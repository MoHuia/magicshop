package com.magicshop.net;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DefusePack {
    private final int defuseCode; // 需要传输的整数数据

    // 构造函数
    public DefusePack(int defuseCode) {
        this.defuseCode = defuseCode;
    }

    // 编码方法（发送时调用）
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(defuseCode); // 必须与decode顺序完全一致
    }

    // 解码方法（接收时调用）
    public static DefusePack decode(FriendlyByteBuf buffer) {
        return new DefusePack(
                buffer.readInt() // 与encode写入顺序一致
        );
    }

    // 数据包处理器
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // 在主线程执行（重要！）
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;
            System.out.println("Received defuse code: " +defuseCode);
            System.out.println("Instance: " + this.hashCode() + ", Code: " + defuseCode);
            // 这里添加业务逻辑...
        });


    }
}