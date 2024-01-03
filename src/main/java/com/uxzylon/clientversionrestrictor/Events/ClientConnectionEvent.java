package com.uxzylon.clientversionrestrictor.Events;

import com.viaversion.viaversion.api.Via;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static com.uxzylon.clientversionrestrictor.ClientVersionRestrictor.plugin;

public class ClientConnectionEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        int version = Via.getAPI().getPlayerVersion(player.getUniqueId());

        plugin.getLogger().info("Player " + player.getName() + " joined with version " + version);

        if (version < ProtocolVersion.v1_14.getVersion() && !player.hasPermission("clientversionrestrictor.bypass")) {
            plugin.getServer().getScheduler().runTaskLater(plugin, () ->
                    player.kickPlayer(plugin.getConfig().getString("kickMessage")), 100L);
        }
    }


}
