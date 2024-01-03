package com.uxzylon.clientversionrestrictor;

import com.uxzylon.clientversionrestrictor.Events.ClientConnectionEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class ClientVersionRestrictor extends JavaPlugin {

    public static ClientVersionRestrictor plugin;
    public static List<Player> playersToKick = new ArrayList<>();

    @Override
    public void onEnable() {
        plugin = this;

        createConfig();

        getServer().getPluginManager().registerEvents(new ClientConnectionEvent(), this);

        plugin.getLogger().info("Enabled!");

        // Kick outdated players every 5 seconds (needed because kicking on join creates an error)
        // Also, listening to PlayerLoginEvent doesn't work because ViaVersion returns -1 as the player version
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player player : playersToKick) {
                player.kickPlayer(getConfig().getString("kickMessage"));
            }
            playersToKick.clear();
        }, 0L, 100L);
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
