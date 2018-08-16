/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  me.clip.placeholderapi.PlaceholderAPI
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.Server
 *  org.bukkit.command.CommandSender
 *  org.bukkit.command.ConsoleCommandSender
 *  org.bukkit.configuration.ConfigurationSection
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.HumanEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.Action
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryHolder
 *  org.bukkit.inventory.InventoryView
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package me.dahpr0gam3r.IdissyHub;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ServerSelector
implements Listener {
    static int guiSlots = IdissyHub.getInstance().getConfig().getInt("Server_Selector.GUI.Slots");
    static String guiName = ChatColor.translateAlternateColorCodes((char)'&', (String) IdissyHub.getInstance().getConfig().getString("Server_Selector.GUI.Name"));

    public static void newInventory(Player player) {
        Inventory selector = Bukkit.createInventory((InventoryHolder)null, (int)guiSlots, (String)guiName);
        for (String key : IdissyHub.getInstance().getConfig().getConfigurationSection("Server_Selector.GUI.Items").getKeys(false)) {
            ItemStack item = new ItemStack(Material.valueOf((String) IdissyHub.getInstance().getConfig().getString("Server_Selector.GUI.Items." + key + ".Item")), IdissyHub.getInstance().getConfig().getInt("Server_Selector.GUI.Items." + key + ".Amount"), (short) IdissyHub.getInstance().getConfig().getInt("Server_Selector.GUI.Items." + key + ".Data"));
            ItemMeta meta = item.getItemMeta();
            List lore = IdissyHub.getInstance().getConfig().getStringList("Server_Selector.GUI.Items." + key + ".Lore");
            ArrayList<String> coloredLore = new ArrayList<String>();
            Iterator iterator = lore.iterator();
            while (iterator.hasNext()) {
                String s = (String)iterator.next();
                if (IdissyHub.getInstance().usingPlaceholderAPI) {
                    s = PlaceholderAPI.setPlaceholders((Player)player, (String)s);
                }
                coloredLore.add(ChatColor.translateAlternateColorCodes((char)'&', (String)s));
            }
            meta.setDisplayName(ChatColor.translateAlternateColorCodes((char)'&', (String) IdissyHub.getInstance().getConfig().getString("Server_Selector.GUI.Items." + key + ".Name")));
            meta.setLore(coloredLore);
            item.setItemMeta(meta);
            selector.setItem(IdissyHub.getInstance().getConfig().getInt("Server_Selector.GUI.Items." + key + ".Slot"), item);
        }
        player.openInventory(selector);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (IdissyHub.getInstance().getConfig().getString("Server_Selector.Enabled").equalsIgnoreCase("true")) {
            Player player = (Player)e.getWhoClicked();
            ItemStack clicked = e.getCurrentItem();
            Inventory inventory = e.getInventory();
            if (e.getClick() == null) {
                return;
            }
            if (inventory.getName().equals(ChatColor.translateAlternateColorCodes((char)'&', (String)guiName))) {
                if (clicked == null || !clicked.hasItemMeta()) {
                    return;
                }
                for (String key : IdissyHub.getInstance().getConfig().getConfigurationSection("Server_Selector.GUI.Items").getKeys(false)) {
                    if (clicked.getType() != Material.valueOf((String) IdissyHub.getInstance().getConfig().getString("Server_Selector.GUI.Items." + key + ".Item"))) continue;
                    if (player.hasPermission((String) IdissyHub.getInstance().getConfig().getString("Server_Selector.GUI.Items." + key + ".Permission"))) {
                        Iterator iterator = IdissyHub.getInstance().getConfig().getStringList("Server_Selector.GUI.Items." + key + ".Commands").iterator();
                        while (iterator.hasNext()) {
                            String command = (String) iterator.next();
                            if (command.startsWith("[message]")) {
                                player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&', (String) command).replace("[message] ", "").replace("%player%", player.getName()));
                            }
                            if (command.startsWith("[command]")) {
                                player.performCommand(command.replace("[command] ", ""));
                            }
                            if (command.startsWith("[consolecommand]")) {
                                IdissyHub.getInstance().getServer().dispatchCommand((CommandSender) IdissyHub.getInstance().getServer().getConsoleSender(), command.replace("[consolecommand] ", "").replace("%player%", player.getName()));
                            }
                            if (!command.startsWith("[bungee]")) continue;
                            command = command.replace("[bungee] ", "").replace("%player%", player.getName());
                            IdissyHub.getInstance().changeServer(player, command);
                        }
                    }
                    else {player.sendMessage(ChatColor.translateAlternateColorCodes((char) '&',(String)"&7You do not have the correct permissions to join this server!"));}
                    player.closeInventory();
                }
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onUse(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (IdissyHub.getInstance().getConfig().getString("Server_Selector.Enabled").equalsIgnoreCase("true")) {
            if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) {
                return;
            }
            if (player.getItemInHand().getType() != Material.valueOf((String) IdissyHub.getInstance().getConfig().getString("Server_Selector.Item"))) {
                return;
            }
            if (player.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes((char)'&', (String) IdissyHub.getInstance().getConfig().getString("Server_Selector.Name")))) {
                ServerSelector.newInventory(player);
                return;
            }
        }
    }
}

