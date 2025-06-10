package com.tunombre.npcskin;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class NpcSkinPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getScheduler().runTaskLater(this, () -> {
            Player player = Bukkit.getOnlinePlayers().stream().findFirst().orElse(null);
            if (player == null) return;

            NPC npc = CitizensAPI.getNPCRegistry().createNPC(player.getType(), player.getName());
            npc.spawn(player.getLocation());

            SkinTrait skinTrait = npc.getOrAddTrait(SkinTrait.class);
            skinTrait.setSkin(player.getName()); // Usa la skin del jugador conectado

        }, 40L);
    }
}