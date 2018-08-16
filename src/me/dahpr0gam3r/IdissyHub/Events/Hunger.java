/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.bukkit.World
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.HumanEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.FoodLevelChangeEvent
 */
package me.dahpr0gam3r.IdissyHub.Events;

import me.dahpr0gam3r.IdissyHub.IdissyHub;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class Hunger
implements Listener {
    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player p = (Player)event.getEntity();
        if (event.getEntityType() == EntityType.PLAYER) {
            if (IdissyHub.getInstance().getConfig().getStringList("disabled-worlds").contains(p.getWorld().getName())) {
                event.setCancelled(false);
                return;
            }
            if (IdissyHub.getInstance().getConfig().getString("World_Settings.Disable_Hunger_Loss").equalsIgnoreCase("true")) {
                event.setCancelled(true);
            }
        }
    }
}

