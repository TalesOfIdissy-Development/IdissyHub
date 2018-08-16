/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  me.clip.placeholderapi.PlaceholderAPI
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Color
 *  org.bukkit.FireworkEffect
 *  org.bukkit.FireworkEffect$Builder
 *  org.bukkit.FireworkEffect$Type
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Server
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.command.CommandSender
 *  org.bukkit.command.ConsoleCommandSender
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Firework
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerJoinEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.PlayerInventory
 *  org.bukkit.inventory.meta.FireworkMeta
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.plugin.PluginDescriptionFile
 */
package me.dahpr0gam3r.IdissyHub.Events;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.clip.placeholderapi.PlaceholderAPI;
import me.dahpr0gam3r.IdissyHub.IdissyHub;
import me.dahpr0gam3r.IdissyHub.PlayerHider;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginDescriptionFile;

public class PlayerJoin
implements Listener {
    @EventHandler(priority=EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        ItemMeta meta;
        ItemStack i;
        FireworkMeta fm;
        List lore;
        ArrayList<String> coloredLore;
        Player player = event.getPlayer();
        if (IdissyHub.getInstance().getConfig().getStringList("disabled-worlds").contains(event.getPlayer().getWorld().getName())) {
            return;
        }
        if (IdissyHub.getInstance().getConfig().getString("Join_Leave_Messages.Enabled").equalsIgnoreCase("true")) {
            event.setJoinMessage(ChatColor.translateAlternateColorCodes((char)'&', (String) IdissyHub.getInstance().getConfig().getString("Join_Leave_Messages.Join").replace("%player%", event.getPlayer().getName())));
        }
        if (IdissyHub.getInstance().getConfig().getString("Player_Join_Settings.Heal").equalsIgnoreCase("true")) {
            player.setMaxHealth(20.0);
            player.setFoodLevel(20);
            player.setHealth(20.0);
        }
        if (IdissyHub.getInstance().getConfig().getString("Player_Join_Settings.Extinguish").equalsIgnoreCase("true")) {
            player.setFireTicks(0);
        }
        if (IdissyHub.getInstance().getConfig().getString("Player_Join_Settings.Clear_Inventory").equalsIgnoreCase("true")) {
            player.getInventory().clear();
        }
        if (IdissyHub.getInstance().getConfig().getString("Player_Join_Settings.Firework").equalsIgnoreCase("true")) {
            Firework f = (Firework)event.getPlayer().getWorld().spawn(event.getPlayer().getLocation(), Firework.class);
            fm = f.getFireworkMeta();
            fm.addEffect(FireworkEffect.builder().flicker(true).trail(true).with(FireworkEffect.Type.BURST).with(FireworkEffect.Type.BALL).with(FireworkEffect.Type.BALL_LARGE).withColor(Color.AQUA).withColor(Color.YELLOW).withColor(Color.LIME).withColor(Color.TEAL).withColor(Color.RED).withColor(Color.WHITE).build());
            fm.setPower(1);
            f.setFireworkMeta(fm);
        }
        for (String joinEvent : IdissyHub.getInstance().getConfig().getStringList("Join_Events"))
        {
            if (joinEvent.startsWith("[message]"))
            {
                joinEvent = joinEvent.replace("[message] ", "").replace("%player%", player.getName());
                if (IdissyHub.getInstance().usingPlaceholderAPI) {
                    joinEvent = PlaceholderAPI.setPlaceholders(event.getPlayer(), joinEvent);
                }
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', joinEvent).replace("[message] ", "").replace("%player%", player.getName()));
            }
            if (joinEvent.startsWith("[sound]"))
            {
                try
                {
                    player.playSound(player.getLocation(), org.bukkit.Sound.valueOf(joinEvent.replace("[sound] ", "")), 50.0F, 50.0F);
                }
                catch (Exception ex)
                {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThe sound specified in the configuration (" + joinEvent.replace("[sound] ", "") + ") is invalid!"));
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cPlease make sure you are using the correct sound for your server version"));
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThe default sound in the config is for 1.9.x+ servers"));
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou will need to change the sound to a 1.8.x if your server version is 1.8.x"));
                }
            }

            if (joinEvent.startsWith("[command]"))
            {
                player.performCommand(joinEvent.replace("[command] ", ""));
            }
            if (joinEvent.startsWith("[consolecommand]"))
            {
                IdissyHub.getInstance().getServer().dispatchCommand(IdissyHub.getInstance().getServer().getConsoleSender(), joinEvent.replace("[consolecommand] ", "").replace("%player%", player.getName()));
            }
            if (joinEvent.startsWith("[title]"))
            {
                String[] temp = joinEvent.split(";");
                String mainTitle = temp[0];
                String subTitle = temp[1];
                player.sendTitle(ChatColor.translateAlternateColorCodes('&', mainTitle.replace("[title] ", "").replace("%player%", event.getPlayer().getName())), ChatColor.translateAlternateColorCodes('&', subTitle.replace("[title] ", "").replace("%player%", event.getPlayer().getName())));
            }
        }
        PluginDescriptionFile pdfFile = IdissyHub.getInstance().getDescription();
        Player p = event.getPlayer();
        if (IdissyHub.getInstance().getConfig().getString("Server_Selector.Enabled").equalsIgnoreCase("true")) {
            i = new ItemStack(Material.valueOf((String) IdissyHub.getInstance().getConfig().getString("Server_Selector.Item")), (int)((short) IdissyHub.getInstance().getConfig().getInt("Server_Selector.Amount")), (short) IdissyHub.getInstance().getConfig().getInt("Server_Selector.Data"));
            meta = i.getItemMeta();
            lore = IdissyHub.getInstance().getConfig().getStringList("Server_Selector.Lore");
            coloredLore = new ArrayList<String>();
            Iterator iterator = lore.iterator();
            while (iterator.hasNext()) {
                String s = (String)iterator.next();
                if (IdissyHub.getInstance().usingPlaceholderAPI) {
                    s = PlaceholderAPI.setPlaceholders((Player)event.getPlayer(), (String)s);
                }
                coloredLore.add(ChatColor.translateAlternateColorCodes((char)'&', (String)s));
            }
            meta.setLore(coloredLore);
            meta.setDisplayName(ChatColor.translateAlternateColorCodes((char)'&', (String) IdissyHub.getInstance().getConfig().getString("Server_Selector.Name")));
            i.setItemMeta(meta);
            player.getInventory().setItem(IdissyHub.getInstance().getConfig().getInt("Server_Selector.Slot"), i);
        }
        if (PlayerHider.hidden.contains((Object)player)) {
            for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
                player.showPlayer(pl);
            }
            PlayerHider.hidden.remove((Object)player);
        }
        if (IdissyHub.getInstance().getConfig().getString("Player_Hider.Enabled").equalsIgnoreCase("true")) {
            i = new ItemStack(Material.valueOf((String) IdissyHub.getInstance().getConfig().getString("Player_Hider.Not_Hidden.Item")), (int)((short) IdissyHub.getInstance().getConfig().getInt("Player_Hider.Not_Hidden.Amount")), (short) IdissyHub.getInstance().getConfig().getInt("Player_Hider.Not_Hidden.Data"));
            meta = i.getItemMeta();
            lore = IdissyHub.getInstance().getConfig().getStringList("Player_Hider.Not_Hidden.Lore");
            coloredLore = new ArrayList();
            for (Object s : lore) {
                coloredLore.add(ChatColor.translateAlternateColorCodes((char)'&', (String)s));
            }
            meta.setLore(coloredLore);
            meta.setDisplayName(ChatColor.translateAlternateColorCodes((char)'&', (String) IdissyHub.getInstance().getConfig().getString("Player_Hider.Not_Hidden.Name")));
            i.setItemMeta(meta);
            player.getInventory().setItem(IdissyHub.getInstance().getConfig().getInt("Player_Hider.Slot"), i);
        }
    }
}

