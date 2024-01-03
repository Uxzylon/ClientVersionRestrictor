package com.uxzylon.clientversionrestrictor.Events;

import com.viaversion.viaversion.api.Via;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class ClientConnectionEvent implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        int version = Via.getAPI().getPlayerVersion(player.getUniqueId());
        if (version < ProtocolVersion.v1_14.getVersion() && !player.hasPermission("clientversionrestrictor.bypass")) {
            event.setKickMessage("You are using an outdated client version. Please update to 1.14 or higher.");
            event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
        }
    }
}
