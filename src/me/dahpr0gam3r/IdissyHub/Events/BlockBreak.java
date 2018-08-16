/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.World
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.BlockBreakEvent
 */
package me.dahpr0gam3r.IdissyHub.Events;

import java.util.HashMap;

import me.dahpr0gam3r.IdissyHub.Config.Messages;
import me.dahpr0gam3r.IdissyHub.IdissyHub;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak
implements Listener {
    public static HashMap<String, Long> breakCooldown = new HashMap();

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (IdissyHub.getInstance().getConfig().getString("World_Settings.Block_Break.Disabled").equalsIgnoreCase("true")) {
            if (IdissyHub.getInstance().getConfig().getStringList("disabled-worlds").contains(event.getPlayer().getWorld().getName())) {
                return;
            }
            if (!event.getPlayer().hasPermission("idissyhub.block.break")) {
                long secondsLeft;
                event.setCancelled(true);
                int cooldownTime = 3;
                if (breakCooldown.containsKey(event.getPlayer().getName()) && (secondsLeft = breakCooldown.get(event.getPlayer().getName()) / 1000 + (long)cooldownTime - System.currentTimeMillis() / 1000) > 0) {
                    return;
                }
                breakCooldown.put(event.getPlayer().getName(), System.currentTimeMillis());
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String) IdissyHub.getInstance().getConfig().getString("World_Settings.Block_Break.Deny_Message").replace("%prefix%", Messages.config.getString("Messages.Prefix"))));
                return;
            }
        }
    }
}

