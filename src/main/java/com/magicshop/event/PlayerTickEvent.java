package com.magicshop.event;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;

public class PlayerTickEvent{
    PlayerTickEvent(){
      //  MinecraftForge.EVENT_BUS.register(this);
    }
    IEventBus bus = MinecraftForge.EVENT_BUS;
    public static void onPlayerTick(PlayerTickEvent event) {
        // 事件处理逻辑
        //444454
    }
}

