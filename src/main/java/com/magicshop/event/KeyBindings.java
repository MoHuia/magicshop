package com.magicshop.event;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.controls.KeyBindsScreen;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static KeyMapping DEFUSE_KEY;

    public static void register(RegisterKeyMappingsEvent event) {
        DEFUSE_KEY = new KeyMapping(
                "key.defuse", // 本地化键名
                GLFW.GLFW_KEY_R,        // 默认按键（R键）
                "key.eyiqian" // 本地化分类名
        );
        event.register(DEFUSE_KEY);
    }
}