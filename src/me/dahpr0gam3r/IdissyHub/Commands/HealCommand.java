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
 *  org.bukkit.entity.Player
 */
package me.dahpr0gam3r.IdissyHub.Commands;

import me.dahpr0gam3r.IdissyHub.Config.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand
implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("heal")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("You cannot heal yourself!");
                return true;
            }
            if (!sender.hasPermission("idissyhub.heal")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.No_Permission").replace("%prefix%", Messages.config.getString("Messages.Prefix"))));
                return true;
            }
            Player player = (Player)sender;
            if (args.length == 0) {
                player.setFoodLevel(20);
                player.setHealth(20.0);
                player.setFireTicks(0);
                player.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.Healed").replace("%prefix%", Messages.config.getString("Messages.Prefix"))));
            } else if (args.length == 1 && sender.hasPermission("idissyhub.heal.others")) {
                Player playerother = Bukkit.getPlayer((String)args[0]);
                if (playerother == null) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.Player_Not_Valid").replace("%prefix%", Messages.config.getString("Messages.Prefix")).replace("%player%", args[0])));
                } else {
                    playerother.setFoodLevel(20);
                    playerother.setHealth(20.0);
                    playerother.setFireTicks(0);
                    playerother.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.Healed").replace("%prefix%", Messages.config.getString("Messages.Prefix"))));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.Healed_Other").replace("%prefix%", Messages.config.getString("Messages.Prefix")).replace("%player%", args[0])));
                }
            }
        }
        return true;
    }
}

