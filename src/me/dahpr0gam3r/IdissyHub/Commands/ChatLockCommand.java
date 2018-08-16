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
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.AsyncPlayerChatEvent
 */
package me.dahpr0gam3r.IdissyHub.Commands;

import me.dahpr0gam3r.IdissyHub.Config.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatLockCommand
implements CommandExecutor,
Listener {
    public static boolean locked = false;

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("lockchat")) {
            if (!sender.hasPermission("idissyhub.lockchat")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.No_Permission").replace("%prefix%", Messages.config.getString("Messages.Prefix"))));
                return true;
            }
            if (locked) {
                Bukkit.broadcastMessage((String)ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.Chat_Unlock_Broadcast").replace("%prefix%", Messages.config.getString("Messages.Prefix")).replace("%player%", sender.getName())));
                locked = false;
                return true;
            }
            Bukkit.broadcastMessage((String)ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.Chat_Lock_Broadcast").replace("%prefix%", Messages.config.getString("Messages.Prefix")).replace("%player%", sender.getName())));
            locked = true;
            return true;
        }
        return true;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("idissyhub.lockchat.bypass")) {
            return;
        }
        if (locked) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.Chat_Locked").replace("%prefix%", Messages.config.getString("Messages.Prefix"))));
            return;
        }
    }
}

