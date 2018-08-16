/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  me.clip.placeholderapi.PlaceholderAPI
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.World
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerQuitEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.PlayerInventory
 *  org.bukkit.inventory.meta.ItemMeta
 */
package me.dahpr0gam3r.IdissyHub.Events;

import me.clip.placeholderapi.PlaceholderAPI;
import me.dahpr0gam3r.IdissyHub.IdissyHub;
import me.dahpr0gam3r.IdissyHub.PlayerHider;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlayerQuit
implements Listener {
    @EventHandler(priority=EventPriority.HIGHEST, ignoreCancelled=true)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (IdissyHub.getInstance().getConfig().getStringList("disabled-worlds").contains(event.getPlayer().getWorld().getName())) {
            return;
        }
        if (IdissyHub.getInstance().getConfig().getString("Join_Leave_Messages.Enabled").equalsIgnoreCase("true")) {
            ItemMeta meta;
            ArrayList<String> coloredLore;
            List lore;
            ItemStack i;
            event.setQuitMessage(ChatColor.translateAlternateColorCodes((char)'&', (String) IdissyHub.getInstance().getConfig().getString("Join_Leave_Messages.Quit").replace("%player%", event.getPlayer().getName())));
            if (!PlayerHider.hidden.contains((Object)player)) {
                i = new ItemStack(Material.valueOf((String) IdissyHub.getInstance().getConfig().getString("Player_Hider.Not_Hidden.Item")), (int)((short) IdissyHub.getInstance().getConfig().getInt("Player_Hider.Not_Hidden.Amount")), (short) IdissyHub.getInstance().getConfig().getInt("Player_Hider.Not_Hidden.Data"));
                meta = i.getItemMeta();
                lore = IdissyHub.getInstance().getConfig().getStringList("Player_Hider.Not_Hidden.Lore");
                coloredLore = new ArrayList<String>();
                for (Object s : lore) {
                    coloredLore.add(ChatColor.translateAlternateColorCodes((char)'&', (String)s));
                }
                meta.setLore(coloredLore);
                meta.setDisplayName(ChatColor.translateAlternateColorCodes((char)'&', (String) IdissyHub.getInstance().getConfig().getString("Player_Hider.Not_Hidden.Name")));
                i.setItemMeta(meta);
                player.getInventory().removeItem(new ItemStack[]{i});
            } else {
                i = new ItemStack(Material.valueOf((String) IdissyHub.getInstance().getConfig().getString("Player_Hider.Hidden.Item")), (int)((short) IdissyHub.getInstance().getConfig().getInt("Player_Hider.Hidden.Amount")), (short) IdissyHub.getInstance().getConfig().getInt("Player_Hider.Hidden.Data"));
                meta = i.getItemMeta();
                lore = IdissyHub.getInstance().getConfig().getStringList("Player_Hider.Hidden.Lore");
                coloredLore = new ArrayList();
                for (Object s : lore) {
                    coloredLore.add(ChatColor.translateAlternateColorCodes((char)'&', (String)s));
                }
                meta.setLore(coloredLore);
                meta.setDisplayName(ChatColor.translateAlternateColorCodes((char)'&', (String) IdissyHub.getInstance().getConfig().getString("Player_Hider.Hidden.Name")));
                i.setItemMeta(meta);
                player.getInventory().removeItem(new ItemStack[]{i});
            }
            i = new ItemStack(Material.valueOf((String) IdissyHub.getInstance().getConfig().getString("Server_Selector.Item")), (int)((short) IdissyHub.getInstance().getConfig().getInt("Server_Selector.Amount")), (short) IdissyHub.getInstance().getConfig().getInt("Server_Selector.Data"));
            meta = i.getItemMeta();
            lore = IdissyHub.getInstance().getConfig().getStringList("Server_Selector.Lore");
            coloredLore = new ArrayList();
            Iterator iterator = lore.iterator();
            while (iterator.hasNext()) {
                String s;
                s = (String)iterator.next();
                if (IdissyHub.getInstance().usingPlaceholderAPI) {
                    s = PlaceholderAPI.setPlaceholders((Player)player, (String)s);
                }
                coloredLore.add(ChatColor.translateAlternateColorCodes((char)'&', (String)s));
            }
            meta.setLore(coloredLore);
            meta.setDisplayName(ChatColor.translateAlternateColorCodes((char)'&', (String) IdissyHub.getInstance().getConfig().getString("Server_Selector.Name")));
            i.setItemMeta(meta);
            player.getInventory().removeItem(new ItemStack[]{i});
        }
    }
}

