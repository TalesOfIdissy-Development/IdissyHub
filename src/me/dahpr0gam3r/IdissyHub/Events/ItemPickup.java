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
 *  org.bukkit.event.player.PlayerPickupItemEvent
 */
package me.dahpr0gam3r.IdissyHub.Events;

import java.util.HashMap;

import me.dahpr0gam3r.IdissyHub.Config.Messages;
import me.dahpr0gam3r.IdissyHub.IdissyHub;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class ItemPickup
implements Listener {
    public static HashMap<String, Long> pickupCooldown = new HashMap();

    @EventHandler
    public void onPlayerDropEvent(PlayerPickupItemEvent event) {
        if (IdissyHub.getInstance().getConfig().getString("World_Settings.Item_Pickup.Disabled").equalsIgnoreCase("true")) {
            if (IdissyHub.getInstance().getConfig().getStringList("disabled-worlds").contains(event.getPlayer().getWorld().getName())) {
                return;
            }
            if (!event.getPlayer().hasPermission("idissyhub.items.pickup")) {
                long secondsLeft;
                event.setCancelled(true);
                int cooldownTime = 3;
                if (pickupCooldown.containsKey(event.getPlayer().getName()) && (secondsLeft = pickupCooldown.get(event.getPlayer().getName()) / 1000 + (long)cooldownTime - System.currentTimeMillis() / 1000) > 0) {
                    return;
                }
                pickupCooldown.put(event.getPlayer().getName(), System.currentTimeMillis());
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String) IdissyHub.getInstance().getConfig().getString("World_Settings.Item_Pickup.Deny_Message").replace("%prefix%", Messages.config.getString("Messages.Prefix"))));
                return;
            }
        }
    }
}

