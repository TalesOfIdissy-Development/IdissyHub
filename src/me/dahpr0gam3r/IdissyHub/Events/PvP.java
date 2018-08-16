/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.World
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 */
package me.dahpr0gam3r.IdissyHub.Events;

import java.util.HashMap;

import me.dahpr0gam3r.IdissyHub.Config.Messages;
import me.dahpr0gam3r.IdissyHub.IdissyHub;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PvP
implements Listener {
    public static HashMap<String, Long> pvpCooldown = new HashMap();

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player p = (Player)event.getEntity();
            if (IdissyHub.getInstance().getConfig().getString("World_Settings.PvP.Disabled").equalsIgnoreCase("true")) {
                if (IdissyHub.getInstance().getConfig().getStringList("disabled-worlds").contains(p.getWorld().getName())) {
                    return;
                }
                if (!event.getDamager().hasPermission("idissyhub.player.pvp")) {
                    long secondsLeft;
                    event.setCancelled(true);
                    int cooldownTime = 3;
                    if (pvpCooldown.containsKey(event.getDamager().getName()) && (secondsLeft = pvpCooldown.get(event.getDamager().getName()) / 1000 + (long)cooldownTime - System.currentTimeMillis() / 1000) > 0) {
                        return;
                    }
                    pvpCooldown.put(event.getDamager().getName(), System.currentTimeMillis());
                    event.getDamager().sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String) IdissyHub.getInstance().getConfig().getString("World_Settings.PvP.Deny_Message").replace("%prefix%", Messages.config.getString("Messages.Prefix"))));
                    return;
                }
            }
        }
    }
}

