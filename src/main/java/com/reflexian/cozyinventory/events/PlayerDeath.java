package com.reflexian.cozyinventory.events;

import com.reflexian.cozyinventory.commands.KeepInventory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (KeepInventory.INVENTORY_SAVERS.contains(e.getEntity().getUniqueId())) {
            e.setKeepInventory(true);
        }
    }

}
