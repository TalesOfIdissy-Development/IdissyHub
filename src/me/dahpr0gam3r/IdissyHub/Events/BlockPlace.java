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
 *  org.bukkit.event.block.BlockPlaceEvent
 */
package me.dahpr0gam3r.IdissyHub.Events;

import java.util.HashMap;

import me.dahpr0gam3r.IdissyHub.Config.Messages;
import me.dahpr0gam3r.IdissyHub.IdissyHub;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace
implements Listener {
    public static HashMap<String, Long> placeCooldown = new HashMap();

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (IdissyHub.getInstance().getConfig().getString("World_Settings.Block_Place.Disabled").equalsIgnoreCase("true")) {
            if (IdissyHub.getInstance().getConfig().getStringList("disabled-worlds").contains(event.getPlayer().getWorld().getName())) {
                return;
            }
            if (!event.getPlayer().hasPermission("idissyhub.block.place")) {
                long secondsLeft;
                event.setCancelled(true);
                int cooldownTime = 3;
                if (placeCooldown.containsKey(event.getPlayer().getName()) && (secondsLeft = placeCooldown.get(event.getPlayer().getName()) / 1000 + (long)cooldownTime - System.currentTimeMillis() / 1000) > 0) {
                    return;
                }
                placeCooldown.put(event.getPlayer().getName(), System.currentTimeMillis());
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String) IdissyHub.getInstance().getConfig().getString("World_Settings.Block_Place.Deny_Message").replace("%prefix%", Messages.config.getString("Messages.Prefix"))));
                return;
            }
        }
    }
}

