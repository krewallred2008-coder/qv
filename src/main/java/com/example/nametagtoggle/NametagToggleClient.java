package com.example.nametagtoggle;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class NametagToggleClient implements ClientModInitializer {
    public static boolean nametagsEnabled = true;

    private static KeyMapping toggleKey;

    @Override
    public void onInitializeClient() {
        KeyMapping.Category category = KeyMapping.Category.register(
                Identifier.fromNamespaceAndPath("nametagtoggle", "main"));

        toggleKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.nametagtoggle.toggle",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_N,
                category));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKey.consumeClick()) {
                nametagsEnabled = !nametagsEnabled;
                if (client.player != null) {
                    client.gui.setOverlayMessage(
                            Component.literal("Nametags: " + (nametagsEnabled ? "ON" : "OFF")), false);
                }
            }
        });
    }
}
