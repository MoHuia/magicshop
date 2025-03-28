package com.magicshop.item;

import com.magicshop.MagicShop;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class wrench {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MagicShop.MODID);
    public static void init(IEventBus iEventBus){
        ITEMS.register(iEventBus);//注册到事件总线
    }
    public static final RegistryObject<Item> WRENCH = ITEMS.register("wrench",
            () -> new Item(new Item.Properties()));
}
