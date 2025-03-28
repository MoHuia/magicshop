package com.magicshop.net;

import com.magicshop.item.wrench;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class KeyPack {
    private final boolean Code; // 需要传输的整数数据

    // 构造函数
    public KeyPack(boolean defuseCode) {
        this.Code = defuseCode;
    }

    // 编码方法（发送时调用）
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBoolean(Code); // 必须与decode顺序完全一致
    }

    // 解码方法（接收时调用）
    public static KeyPack decode(FriendlyByteBuf buffer) {
        return new KeyPack(
                buffer.readBoolean() // 与encode写入顺序一致
        );
    }

    // 数据包处理器
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // 在主线程执行（重要！）
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;
            if(player.getInventory().selected == 36 || player.getInventory().getSelected().getItem()== wrench.WRENCH.get()){
                
            }
            // 这里添加业务逻辑...
        });


    }
}