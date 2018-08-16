/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.AsyncPlayerChatEvent
 */
package me.dahpr0gam3r.IdissyHub.Events;

import me.dahpr0gam3r.IdissyHub.Config.Messages;
import me.dahpr0gam3r.IdissyHub.IdissyHub;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat
implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String message = e.getMessage().toLowerCase();
        if (IdissyHub.getInstance().getConfig().getString("Anti-Swear.Enabled").equalsIgnoreCase("true")) {
            for (String blockedWords : IdissyHub.getInstance().getConfig().getStringList("Anti-Swear.Blocked_Words")) {
                if (player.hasPermission("idissyhub.chat.bypass") || !message.contains(blockedWords.toLowerCase())) continue;
                e.setCancelled(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String) IdissyHub.getInstance().getConfig().getString("Anti-Swear.Swear_Block").replace("%prefix%", Messages.config.getString("Messages.Prefix"))));
                for (Player admins : Bukkit.getOnlinePlayers()) {
                    if (!admins.hasPermission("idissyhub.chat.alert")) continue;
                    admins.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String) IdissyHub.getInstance().getConfig().getString("Anti-Swear.Swear_Notify").replace("%prefix%", Messages.config.getString("Messages.Prefix")).replace("%player%", player.getName()).replace("%word%", message)));
                    return;
                }
            }
        }
    }
}

