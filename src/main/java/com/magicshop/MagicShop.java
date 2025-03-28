package com.magicshop;

import com.magicshop.item.wrench;
import com.magicshop.net.ModMessage;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(MagicShop.MODID)
public class MagicShop {

    public static final String MODID = "magicshop";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,MODID);
    public static final RegistryObject<Item> magic_shop = ITEMS.register("magicshop",()->new Item(new Item.Properties()));
//121211212121212155511115
    public MagicShop() {
        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(bus);
        ModMessage.register();
        InitAll(bus);
    }
    public void InitAll(IEventBus iEventBus){//正常注册进世界总线
        wrench.init(iEventBus);
    }
}
