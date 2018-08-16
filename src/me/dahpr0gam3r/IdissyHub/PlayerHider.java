/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.Action
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.inventory.EquipmentSlot
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.PlayerInventory
 *  org.bukkit.inventory.meta.ItemMeta
 */
package me.dahpr0gam3r.IdissyHub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.dahpr0gam3r.IdissyHub.Config.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerHider
implements Listener {
    public static ArrayList<Player> hidden = new ArrayList();
    public static HashMap<String, Long> hiderCooldowns = new HashMap();

    @EventHandler
    public void onUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (IdissyHub.getInstance().getConfig().getString("Player_Hider.Enabled").equalsIgnoreCase("true") && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            if (!IdissyHub.getInstance().version_8 && event.getHand() != EquipmentSlot.HAND) {
                return;
            }
            if (player.getItemInHand().getType() != Material.valueOf((String) IdissyHub.getInstance().getConfig().getString("Player_Hider.Not_Hidden.Item")) || player.getItemInHand().getType() != Material.valueOf((String) IdissyHub.getInstance().getConfig().getString("Player_Hider.Hidden.Item"))) {
                return;
            }
            if (player.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes((char)'&', (String) IdissyHub.getInstance().getConfig().getString("Player_Hider.Not_Hidden.Name"))) || player.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes((char)'&', (String) IdissyHub.getInstance().getConfig().getString("Player_Hider.Hidden.Name")))) {
                long secondsLeft;
                int cooldownTime = IdissyHub.getInstance().getConfig().getInt("Player_Hider.Cooldown");
                if (hiderCooldowns.containsKey(event.getPlayer().getName()) && (secondsLeft = hiderCooldowns.get(event.getPlayer().getName()) / 1000 + (long)cooldownTime - System.currentTimeMillis() / 1000) > 0) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String) Messages.config.getString("Messages.Cooldown_Active").replace("%prefix%", Messages.config.getString("Messages.Prefix")).replace("%time%", String.valueOf(secondsLeft))));
                    return;
                }
                hiderCooldowns.put(event.getPlayer().getName(), System.currentTimeMillis());
                if (!hidden.contains((Object)player)) {
                    for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
                        player.hidePlayer(pl);
                    }
                    hidden.add(player);
                    player.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.Players_Hidden").replace("%prefix%", Messages.config.getString("Messages.Prefix"))));
                    ItemStack i = new ItemStack(Material.valueOf((String) IdissyHub.getInstance().getConfig().getString("Player_Hider.Hidden.Item")), (int)((short) IdissyHub.getInstance().getConfig().getInt("Player_Hider.Hidden.Amount")), (short) IdissyHub.getInstance().getConfig().getInt("Player_Hider.Hidden.Data"));
                    ItemMeta meta = i.getItemMeta();
                    List lore = IdissyHub.getInstance().getConfig().getStringList("Player_Hider.Hidden.Lore");
                    ArrayList<String> coloredLore = new ArrayList<String>();
                    for (Object s : lore) {
                        coloredLore.add(ChatColor.translateAlternateColorCodes((char)'&', (String)s));
                    }
                    meta.setLore(coloredLore);
                    meta.setDisplayName(ChatColor.translateAlternateColorCodes((char)'&', (String) IdissyHub.getInstance().getConfig().getString("Player_Hider.Hidden.Name")));
                    i.setItemMeta(meta);
                    player.getInventory().setItem(IdissyHub.getInstance().getConfig().getInt("Player_Hider.Slot"), new ItemStack(Material.AIR));
                    player.getInventory().setItem(IdissyHub.getInstance().getConfig().getInt("Player_Hider.Slot"), i);
                    return;
                }
                for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
                    player.showPlayer(pl);
                }
                hidden.remove((Object)player);
                player.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.Players_Shown").replace("%prefix%", Messages.config.getString("Messages.Prefix"))));
                ItemStack i = new ItemStack(Material.valueOf((String) IdissyHub.getInstance().getConfig().getString("Player_Hider.Not_Hidden.Item")), (int)((short) IdissyHub.getInstance().getConfig().getInt("Player_Hider.Not_Hidden.Amount")), (short) IdissyHub.getInstance().getConfig().getInt("Player_Hider.Not_Hidden.Data"));
                ItemMeta meta = i.getItemMeta();
                List lore = IdissyHub.getInstance().getConfig().getStringList("Player_Hider.Not_Hidden.Lore");
                ArrayList<String> coloredLore = new ArrayList<String>();
                for (Object s : lore) {
                    coloredLore.add(ChatColor.translateAlternateColorCodes((char)'&', (String)s));
                }
                meta.setLore(coloredLore);
                meta.setDisplayName(ChatColor.translateAlternateColorCodes((char)'&', (String) IdissyHub.getInstance().getConfig().getString("Player_Hider.Not_Hidden.Name")));
                i.setItemMeta(meta);
                player.getInventory().setItem(IdissyHub.getInstance().getConfig().getInt("Player_Hider.Slot"), new ItemStack(Material.AIR));
                player.getInventory().setItem(IdissyHub.getInstance().getConfig().getInt("Player_Hider.Slot"), i);
                return;
            }
        }
    }
}

