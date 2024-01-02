package com.uxzylon.clientversionrestrictor;

import com.uxzylon.clientversionrestrictor.Events.ClientConnectionEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class ClientVersionRestrictor extends JavaPlugin {

    public static ClientVersionRestrictor plugin;

    @Override
    public void onEnable() {
        plugin = this;

        getServer().getPluginManager().registerEvents(new ClientConnectionEvent(), this);
    }
}
