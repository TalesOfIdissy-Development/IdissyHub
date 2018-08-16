/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  me.clip.placeholderapi.PlaceholderAPI
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.World
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerChangedWorldEvent
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
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class WorldChange
implements Listener {
    @EventHandler
    public void onFirstChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();

        if (IdissyHub.getInstance().getConfig().getStringList("disabled-worlds").contains(event.getPlayer().getWorld().getName()))
        {
            if (!PlayerHider.hidden.contains(player))
            {
                ItemStack i = new ItemStack(Material.valueOf(IdissyHub.getInstance().getConfig().getString("Player_Hider.Not_Hidden.Item")), (short) IdissyHub.getInstance().getConfig().getInt("Player_Hider.Not_Hidden.Amount"), (short) IdissyHub.getInstance().getConfig().getInt("Player_Hider.Not_Hidden.Data"));
                ItemMeta meta = i.getItemMeta();
                List<String> lore = IdissyHub.getInstance().getConfig().getStringList("Player_Hider.Not_Hidden.Lore");
                List<String> coloredLore = new ArrayList();
                for (String s : lore) {
                    coloredLore.add(ChatColor.translateAlternateColorCodes('&', s));
                }
                meta.setLore(coloredLore);
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', IdissyHub.getInstance().getConfig().getString("Player_Hider.Not_Hidden.Name")));
                i.setItemMeta(meta);
                player.getInventory().removeItem(new ItemStack[] { i });
            } else {
                ItemStack i = new ItemStack(Material.valueOf(IdissyHub.getInstance().getConfig().getString("Player_Hider.Hidden.Item")), (short) IdissyHub.getInstance().getConfig().getInt("Player_Hider.Hidden.Amount"), (short) IdissyHub.getInstance().getConfig().getInt("Player_Hider.Hidden.Data"));
                ItemMeta meta = i.getItemMeta();
                List<String> lore = IdissyHub.getInstance().getConfig().getStringList("Player_Hider.Hidden.Lore");
                List<String> coloredLore = new ArrayList();
                for (String s : lore) {
                    coloredLore.add(ChatColor.translateAlternateColorCodes('&', s));
                }
                meta.setLore(coloredLore);
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', IdissyHub.getInstance().getConfig().getString("Player_Hider.Hidden.Name")));
                i.setItemMeta(meta);
                player.getInventory().removeItem(new ItemStack[] { i });
            }


            ItemStack i = new ItemStack(Material.valueOf(IdissyHub.getInstance().getConfig().getString("Server_Selector.Item")), (short) IdissyHub.getInstance().getConfig().getInt("Server_Selector.Amount"), (short) IdissyHub.getInstance().getConfig().getInt("Server_Selector.Data"));
            ItemMeta meta = i.getItemMeta();
            List<String> lore = IdissyHub.getInstance().getConfig().getStringList("Server_Selector.Lore");
            List<String> coloredLore = new ArrayList();
            for (String s : lore) {
                s = PlaceholderAPI.setPlaceholders(player, s);
                coloredLore.add(ChatColor.translateAlternateColorCodes('&', s));
            }
            meta.setLore(coloredLore);
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', IdissyHub.getInstance().getConfig().getString("Server_Selector.Name")));
            i.setItemMeta(meta);
            player.getInventory().removeItem(new ItemStack[] { i });
        }

        if (!IdissyHub.getInstance().getConfig().getStringList("disabled-worlds").contains(event.getPlayer().getWorld().getName())) {
            ItemMeta meta;
            if (IdissyHub.getInstance().getConfig().getString("Server_Selector.Enabled").equalsIgnoreCase("true")) {
                ItemStack i = new ItemStack(Material.valueOf(IdissyHub.getInstance().getConfig().getString("Server_Selector.Item")), (short) IdissyHub.getInstance().getConfig().getInt("Server_Selector.Amount"), (short) IdissyHub.getInstance().getConfig().getInt("Server_Selector.Data"));
                meta = i.getItemMeta();
                List<String> lore = IdissyHub.getInstance().getConfig().getStringList("Server_Selector.Lore");
                List<String> coloredLore = new ArrayList();
                for (String s : lore) {
                    s = PlaceholderAPI.setPlaceholders(event.getPlayer(), s);
                    coloredLore.add(ChatColor.translateAlternateColorCodes('&', s));
                }
                meta.setLore(coloredLore);
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', IdissyHub.getInstance().getConfig().getString("Server_Selector.Name")));
                i.setItemMeta(meta);
                player.getInventory().setItem(IdissyHub.getInstance().getConfig().getInt("Server_Selector.Slot"), i);
            }


            if (PlayerHider.hidden.contains(player))
            {
                for (Player pl : org.bukkit.Bukkit.getServer().getOnlinePlayers()) {
                    player.showPlayer(pl);
                }
                PlayerHider.hidden.remove(player);
            }

            if (IdissyHub.getInstance().getConfig().getString("Player_Hider.Enabled").equalsIgnoreCase("true"))
            {
                ItemStack i = new ItemStack(Material.valueOf(IdissyHub.getInstance().getConfig().getString("Player_Hider.Not_Hidden.Item")), (short) IdissyHub.getInstance().getConfig().getInt("Player_Hider.Not_Hidden.Amount"), (short) IdissyHub.getInstance().getConfig().getInt("Player_Hider.Not_Hidden.Data"));
                meta = i.getItemMeta();
                List<String> lore = IdissyHub.getInstance().getConfig().getStringList("Player_Hider.Not_Hidden.Lore");
                List<String> coloredLore = new ArrayList();
                for (String s : lore) {
                    coloredLore.add(ChatColor.translateAlternateColorCodes('&', s));
                }
                meta.setLore(coloredLore);
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', IdissyHub.getInstance().getConfig().getString("Player_Hider.Not_Hidden.Name")));
                i.setItemMeta(meta);
                player.getInventory().setItem(IdissyHub.getInstance().getConfig().getInt("Player_Hider.Slot"), i);
            }
        }
    }
}

