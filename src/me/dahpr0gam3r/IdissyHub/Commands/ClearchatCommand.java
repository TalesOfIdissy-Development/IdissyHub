/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.configuration.file.FileConfiguration
 */
package me.dahpr0gam3r.IdissyHub.Commands;

import me.dahpr0gam3r.IdissyHub.Config.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ClearchatCommand
implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("clearchat")) {
            if (sender.hasPermission("idissyhub.clearchat")) {
                int i = 0;
                while (i < 100) {
                    Bukkit.broadcastMessage((String)"");
                    ++i;
                }
                Bukkit.broadcastMessage((String)ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.Clearchat").replace("%player%", sender.getName())));
            }
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("No_Permission").replace("%prefix%", Messages.config.getString("Messages.Prefix"))));
        }
        return true;
    }
}

