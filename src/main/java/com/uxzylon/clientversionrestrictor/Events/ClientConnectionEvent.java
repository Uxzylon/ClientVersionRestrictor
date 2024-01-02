package com.uxzylon.clientversionrestrictor.Events;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.ViaAPI;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ClientConnectionEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent pje) {
        ViaAPI api = Via.getAPI();
        int version = api.getPlayerVersion(pje.getPlayer().getUniqueId());
        if (version < ProtocolVersion.v1_14.getVersion()) {
            pje.getPlayer().kickPlayer("You are using an outdated client version. Please update to 1.14 or higher.");
        }
    }
}
