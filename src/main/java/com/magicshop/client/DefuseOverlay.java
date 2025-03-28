package com.magicshop.client;


import com.magicshop.MagicShop;
import com.magicshop.event.KeyBindings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

// 施法进度条覆盖层类，继承自Forge的GUI覆盖层接口
public class DefuseOverlay implements IGuiOverlay {
    private static int waterLevel = 0;

    //教程
    public static final IGuiOverlay WATER_LEVEL_HUD = (((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        var font = gui.getMinecraft().font;
        var text = "Water Level: " + waterLevel;
        guiGraphics.drawString(font, text, 100, 100, 0xFFFFFF);
    }));



    // 单例实例，用于全局访问
    public static  DefuseOverlay instance = new DefuseOverlay();
    // 纹理资源路径（GUI图标图集）
    public final static ResourceLocation TEXTURE = new ResourceLocation(MagicShop.MODID,"textures/gui/icons.png");
    // 进度条总宽度（包含边框）
    static final int IMAGE_WIDTH = 54;
    // 实际进度显示部分的宽度（不含边框）
    static final int COMPLETION_BAR_WIDTH = 44;
    // 进度条高度
    static final int IMAGE_HEIGHT = 21;

    //主方法，分别是gui，gui处理器，tick，屏幕位
    public void render(ForgeGui gui, GuiGraphics guiHelper, float partialTick, int screenWidth, int screenHeight){
        // 计算进度条绘制位置
        if (!KeyBindings.DEFUSE_KEY.isDown()) return;
        long clientGameTime=0;
        if (Minecraft.getInstance().level != null) {
             clientGameTime = Minecraft.getInstance().level.getGameTime(); // 客户端游戏刻
        }

        int barX, barY;
        barX = screenWidth / 2 - IMAGE_WIDTH / 2;  // 水平居中
        barY = screenHeight / 2 + screenHeight / 8;  // 垂直位置：屏幕中心下方1/8处

        // 绘制进度条背景（固定部分）
        guiHelper.blit(TEXTURE,
                barX, barY,                            // 屏幕坐标
                0, IMAGE_HEIGHT * 2,           // 纹理坐标（从图集选择背景区域）
                IMAGE_WIDTH, IMAGE_HEIGHT,              // 绘制尺寸
                256, 256                                // 纹理图集尺寸
        );
        // 绘制进度条背景（固定部分）
        guiHelper.blit(TEXTURE,
                barX, barY,                            // 屏幕坐标
                0, IMAGE_HEIGHT * 3,           // 纹理坐标（从图集选择背景区域）
                (int)(clientGameTime%IMAGE_WIDTH), IMAGE_HEIGHT,              // 绘制尺寸
                256, 256                                // 纹理图集尺寸
        );
        // 计时文本位置
        int textX, textY;
        var font = gui.getFont();  // 获取Minecraft字体渲染器
        // 水平居中计算：进度条中心 - 文本宽度/2
        textX = barX + (IMAGE_WIDTH ) / 2;
        // 垂直居中计算：进度条垂直中心 - 字体高度/2 + 1像素微调
        textY = barY + IMAGE_HEIGHT / 2 - font.lineHeight / 2 + 1;

        String castTimeString = new String().formatted("hello，world！");
        guiHelper.drawString(font, castTimeString, textX, textY, 0xFFFFFF);

    }
}
