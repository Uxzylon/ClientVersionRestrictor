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

        getConfig().addDefault("kickMessage", "§cYou are using an outdated client version. Please update to 1.14 or higher.");
        getConfig().addDefault("minVersion", "v1.14");
        getConfig().addDefault("maxVersion", "unknown");

        saveConfig();
        reloadConfig();
    }
}
