/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.GameMode
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.Player
 */
package me.dahpr0gam3r.IdissyHub.Commands.Gamemode;

import me.dahpr0gam3r.IdissyHub.Config.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand
implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("gamemode")) {
                if (!p.hasPermission("idissyhub.gamemode")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.No_Permission").replace("%prefix%", Messages.config.getString("Messages.Prefix"))));
                    return true;
                }
                if (args.length == 0) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.Gamemode_Usage").replace("%prefix%", Messages.config.getString("Messages.Prefix"))));
                    return true;
                }
                if (args[0].equals("0") || args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.Gamemode").replace("%prefix%", Messages.config.getString("Messages.Prefix")).replace("%gamemode%", "SURVIVAL")));
                    p.setGameMode(GameMode.SURVIVAL);
                    return true;
                }
                if (args[0].equals("1") || args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.Gamemode").replace("%prefix%", Messages.config.getString("Messages.Prefix")).replace("%gamemode%", "CREATIVE")));
                    p.setGameMode(GameMode.CREATIVE);
                    return true;
                }
                if (args[0].equals("2") || args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("a")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.Gamemode").replace("%prefix%", Messages.config.getString("Messages.Prefix")).replace("%gamemode%", "ADVENTURE")));
                    p.setGameMode(GameMode.ADVENTURE);
                    return true;
                }
                if (args[0].equals("3") || args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("sp")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.Gamemode").replace("%prefix%", Messages.config.getString("Messages.Prefix")).replace("%gamemode%", "SPECTATOR")));
                    p.setGameMode(GameMode.SPECTATOR);
                    return true;
                }
                return true;
            }
        } else {
            Bukkit.getLogger().severe("You are the console and cannot change gamemode. :(");
            return true;
        }
        return false;
    }
}

