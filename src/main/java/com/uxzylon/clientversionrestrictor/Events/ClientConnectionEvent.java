package com.uxzylon.clientversionrestrictor.Events;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.ViaAPI;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class ClientConnectionEvent implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerLoginEvent event) {
        ViaAPI api = Via.getAPI();
        int version = api.getPlayerVersion(event.getPlayer().getUniqueId());
        if (version < ProtocolVersion.v1_14.getVersion()) {
            event.setKickMessage("You are using an outdated client version. Please update to 1.14 or higher.");
            event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
        }
    }
}
