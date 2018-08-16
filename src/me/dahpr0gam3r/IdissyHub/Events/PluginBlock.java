/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerCommandPreprocessEvent
 */
package me.dahpr0gam3r.IdissyHub.Events;

import me.dahpr0gam3r.IdissyHub.Config.Messages;
import me.dahpr0gam3r.IdissyHub.IdissyHub;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PluginBlock
implements Listener {
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (IdissyHub.getInstance().getConfig().getString("Plugin_Blocker.Enabled").equalsIgnoreCase("true") && IdissyHub.getInstance().getConfig().getStringList("Plugin_Blocker.Blocked_Commands").contains(event.getMessage().toLowerCase()) && !event.getPlayer().hasPermission("idissyhub.blockedcommands.bypass")) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String) IdissyHub.getInstance().getConfig().getString("Plugin_Blocker.Deny_Message").replace("%prefix%", Messages.config.getString("Messages.Prefix"))));
        }
    }
}

