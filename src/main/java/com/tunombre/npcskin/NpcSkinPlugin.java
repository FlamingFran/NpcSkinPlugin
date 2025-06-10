package com.tunombre.npcskin;

import com.comphenix.protocol.*;
import com.comphenix.protocol.events.*;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Method;

public class NpcSkinPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(this,
                ListenerPriority.NORMAL, PacketType.Play.Server.NAMED_ENTITY_SPAWN) {
            @Override
            public void onPacketSending(PacketEvent event) {
                Player viewer = event.getPlayer();
                PacketContainer packet = event.getPacket();

                int entityId = packet.getIntegers().read(0);
                Entity entity = viewer.getWorld().getEntity(entityId);
                if (entity == null) return;

                NPC npc = CitizensAPI.getNPCRegistry().getNPC(entity);
                if (npc == null) return;

                try {
                    Object handle = viewer.getClass().getMethod("getHandle").invoke(viewer);
                    Method getProfile = handle.getClass().getMethod("getProfile");
                    GameProfile playerProfile = (GameProfile) getProfile.invoke(handle);

                    packet.getGameProfiles().write(0, playerProfile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
