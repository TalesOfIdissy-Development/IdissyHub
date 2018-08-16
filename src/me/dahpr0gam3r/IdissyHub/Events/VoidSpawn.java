/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.World
 *  org.bukkit.configuration.ConfigurationSection
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityDamageEvent
 *  org.bukkit.event.entity.EntityDamageEvent$DamageCause
 */
package me.dahpr0gam3r.IdissyHub.Events;

import me.dahpr0gam3r.IdissyHub.Config.Data;
import me.dahpr0gam3r.IdissyHub.IdissyHub;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class VoidSpawn
implements Listener {
    @EventHandler
    public void onVoidDamage(EntityDamageEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.VOID) {
            Entity ent = event.getEntity();
            if (ent == null) {
                return;
            }
            if (ent instanceof Player) {
                if (IdissyHub.getInstance().getConfig().getString("World_Settings.Disable_Void_Death").equalsIgnoreCase("true")) {
                    if (IdissyHub.getInstance().getConfig().getStringList("disabled-worlds").contains(ent.getWorld().getName())) {
                        return;
                    }
                    ((Player)ent).setFallDistance(0.0f);
                    if (Data.config.getConfigurationSection("Spawn") == null) {
                        return;
                    }
                    World w = Bukkit.getServer().getWorld(Data.config.getString("Spawn.world"));
                    double x = Data.config.getDouble("Spawn.x");
                    double y = Data.config.getDouble("Spawn.y");
                    double z = Data.config.getDouble("Spawn.z");
                    float pitch = Data.config.getInt("Spawn.pitch");
                    float yaw = Data.config.getInt("Spawn.yaw");
                    ent.teleport(new Location(w, x, y, z, yaw, pitch));
                }
                event.setCancelled(true);
            }
        }
    }
}

