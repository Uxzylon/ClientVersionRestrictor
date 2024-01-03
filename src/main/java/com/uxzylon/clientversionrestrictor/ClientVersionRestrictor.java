package com.uxzylon.clientversionrestrictor;

import com.uxzylon.clientversionrestrictor.Events.ClientConnectionEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class ClientVersionRestrictor extends JavaPlugin {

    public static ClientVersionRestrictor plugin;

    @Override
    public void onEnable() {
        plugin = this;

        createConfig();

        getServer().getPluginManager().registerEvents(new ClientConnectionEvent(), this);

        plugin.getLogger().info("Enabled!");
    }

    private void createConfig() {
        getConfig().options().copyDefaults(true);

        getConfig().addDefault("kickMessage", "§cYou are using an unsupported version of Minecraft!");
        getConfig().addDefault("minVersion", 472);
        getConfig().addDefault("maxVersion", -1);

        saveConfig();
        reloadConfig();
    }
}
