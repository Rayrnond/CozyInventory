package com.reflexian.cozyinventory.commands;

import com.reflexian.cozyinventory.CozyInventory;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class KeepInventory implements CommandExecutor {

    public static final List<UUID> INVENTORY_SAVERS = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(CozyInventory.getInstance().getConfig().getString("permission"))) {

                if (INVENTORY_SAVERS.contains(player.getUniqueId())) {
                    INVENTORY_SAVERS.remove(player.getUniqueId());
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CozyInventory.getInstance().getConfig().getString("disabled")));
                    return true;
                }
                INVENTORY_SAVERS.add(player.getUniqueId());
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CozyInventory.getInstance().getConfig().getString("enabled")));

            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CozyInventory.getInstance().getConfig().getString("no-permission")));
                return true;
            }


        }else {
            sender.sendMessage("Console cannot execute this command!");
        }

        return true;
    }
}
