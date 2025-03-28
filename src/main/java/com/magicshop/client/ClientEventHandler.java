
package com.magicshop.client;

import com.magicshop.MagicShop;
import com.magicshop.event.MagicShopScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * 注册事件处理器以处理客户端特定事件
 */
@Mod.EventBusSubscriber(modid = MagicShop.MODID, value = Dist.CLIENT)
public class ClientEventHandler {

    /**
     * 监听玩家右键点击物品事件
     * 该事件仅在客户端触发，用于打开魔法商店界面
     *
     * @param event 玩家右键点击物品事件
     */
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT) // 双重保险确保客户端执行
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();

        // 1. 检查是否主手持有目标物品
        ItemStack mainHandStack = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (mainHandStack.getItem() != MagicShop.magic_shop.get()) {
            return; // 非目标物品直接返回
        }

        // 2. 客户端处理逻辑
        if (player.level().isClientSide()) {
            try {
                Minecraft.getInstance().setScreen(new MagicShopScreen());
                event.setCanceled(true); // 阻止后续交互
            } catch (Exception e) {
                // 错误处理
                System.err.println("Failed to open Magic Shop GUI: " + e.getMessage());
                e.printStackTrace();
            }
        }

        // 3. 服务端同步逻辑（可选）
        // 如果需要向服务端发送数据包，可以在此处添加
    }
}