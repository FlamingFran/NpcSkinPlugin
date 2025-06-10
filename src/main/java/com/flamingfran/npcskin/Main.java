package com.flamingfran.npcskin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("NpcSkinPlugin ha sido activado.");
    }

    @Override
    public void onDisable() {
        getLogger().info("NpcSkinPlugin ha sido desactivado.");
    }
}
