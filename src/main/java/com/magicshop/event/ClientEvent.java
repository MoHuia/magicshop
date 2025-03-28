package com.magicshop.event;


import com.magicshop.MagicShop;
import com.magicshop.item.wrench;
import com.magicshop.net.DefusePack;
import com.magicshop.net.ModMessage;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MagicShop.MODID,bus = Mod.EventBusSubscriber.Bus.FORGE,value = Dist.CLIENT)//注解，告诉编译器底下是事件注册
public class ClientEvent {//以上是监听注解，，这是静态注册事件的办法
    static int tick;
    static int enable;
//右键检测的代码
//    @SubscribeEvent//表示,以下是执行代码
//    public static void PlayerHandWrench(PlayerInteractEvent.EntityInteract event) {
//        //获取生物
//        // 由于长按期间经常出现误判，因此应该更换方法，将参数写入玩家内，
//        Entity entity = event.getTarget();
//        Level level = event.getLevel();
//        if (level.isClientSide()) return;
//        if (event.getItemStack().getItem() == wrench.WRENCH.get() && entity instanceof Pig && entity.distanceTo(event.getEntity()) < 2.0) {//当且仅当扳手
//            tick++;
//            enable = 1;
//            System.out.println("level:" + level + tick);
//            //获取世界
//            level.addFreshEntity(new PrimedTnt(level, entity.getX() + 0.5, entity.getY() + 10, entity.getZ() + 0.5, (LivingEntity) entity));
//            //event.setCanceled(true);
//        } else {
//            System.out.println("level:" + level);
//            //tick=0;
//        }
//    }

    private static boolean wasKeyPressed = false;
    @SubscribeEvent
    public static void onKeyInput(TickEvent.ClientTickEvent event) {
        if (KeyBindings.DEFUSE_KEY.isDown()) {
            // 当按键被按下时执行
//            Minecraft.getInstance().player.sendSystemMessage(
//                  //  Component.literal("R键被按下!")
//                    //处理逻辑
//            );
            wasKeyPressed = true;
            DefusePack pack = new DefusePack(1);
            ModMessage.sendToServer(pack);
        } else if (wasKeyPressed != KeyBindings.DEFUSE_KEY.isDown()) {
            wasKeyPressed = false;
            Minecraft.getInstance().player.sendSystemMessage(
                    Component.literal("玩家不按了")
            );
        }

    }


}