/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerJoinEvent
 *  org.bukkit.event.player.PlayerQuitEvent
 */
package me.dahpr0gam3r.IdissyHub.Commands;

import me.dahpr0gam3r.IdissyHub.Config.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class FlyCommand
implements Listener,
CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("fly") && !(sender instanceof Player)) {
            System.out.println("This command is for players only!");
            return true;
        }
        Player p = (Player)sender;
        if (!p.hasPermission("idissyhub.fly")) {
            p.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.No_Permission").replace("%prefix%", Messages.config.getString("Messages.Prefix"))));
            return true;
        }
        if (p.getAllowFlight()) {
            p.setAllowFlight(false);
            p.setFlying(false);
            p.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.Flight_Disable").replace("%prefix%", Messages.config.getString("Messages.Prefix"))));
        } else {
            p.setAllowFlight(true);
            p.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.Flight_Enable").replace("%prefix%", Messages.config.getString("Messages.Prefix"))));
        }
        return true;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (p.getAllowFlight()) {
            p.setFlying(false);
            p.setAllowFlight(false);
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        p.setAllowFlight(false);
        p.setFlying(false);
    }
}

