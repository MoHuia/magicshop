
// MagicShopScreen.java
package com.magicshop.event;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

/**
 * 魔法商店界面类，继承自Screen类
 * 用于渲染魔法商店的图形界面
 */
public class MagicShopScreen extends Screen {
    // 商店背景图的资源位置
    private static final ResourceLocation BACKGROUND = new ResourceLocation("magicshop", "textures/gui/shop_bg.png");
    // 图片宽度
    private final int imageWidth = 256;
    // 图片高度
    private final int imageHeight = 256;

    /**
     * 构造函数
     * 初始化屏幕标题为"Magic Shop"
     */
    public MagicShopScreen() {
        super(Component.literal("Magic Shop"));
    }

    /**
     * 渲染方法
     * 负责绘制界面的背景和商店背景图
     *
     * @param gui          渲染界面的工具
     * @param mouseX       鼠标X坐标，用于界面交互
     * @param mouseY       鼠标Y坐标，用于界面交互
     * @param partialTicks 渲染的部分刻数，用于平滑动画
     */
    @Override
    public void render(GuiGraphics gui, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(gui); // 渲染背景
        super.render(gui, mouseX, mouseY, partialTicks); // 调用父类渲染方法

        // 绘制背景
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        gui.blit(BACKGROUND,
                (this.width - imageWidth) / 2,
                (this.height - imageHeight) / 2,
                0, 0,
                imageWidth, imageHeight);
    }

    /**
     * 判断是否为暂停屏幕
     * 返回false表示游戏在打开GUI时不会暂停
     *
     * @return false 游戏不会在打开GUI时暂停
     */
    @Override
    public boolean isPauseScreen() {
        return false; // 游戏不会在打开GUI时暂停
    }
}