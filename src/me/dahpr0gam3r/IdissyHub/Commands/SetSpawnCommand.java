/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Location
 *  org.bukkit.World
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.Player
 */
package me.dahpr0gam3r.IdissyHub.Commands;

import me.dahpr0gam3r.IdissyHub.Config.Data;
import me.dahpr0gam3r.IdissyHub.Config.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand
implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Console cannot set spawn");
            return true;
        }
        Player p = (Player)sender;
        if (cmd.getName().equalsIgnoreCase("setspawn")) {
            if (!p.hasPermission("idissyhub.setspawn")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.No_Permission").replace("%prefix%", Messages.config.getString("Messages.Prefix"))));
                return true;
            }
            Data.config.set("Spawn.world", (Object)p.getLocation().getWorld().getName());
            Data.config.set("Spawn.x", (Object)p.getLocation().getX());
            Data.config.set("Spawn.y", (Object)p.getLocation().getY());
            Data.config.set("Spawn.z", (Object)p.getLocation().getZ());
            Data.config.set("Spawn.pitch", (Object)Float.valueOf(p.getLocation().getPitch()));
            Data.config.set("Spawn.yaw", (Object)Float.valueOf(p.getLocation().getYaw()));
            Data.save();
            p.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.Set_Spawn").replace("%prefix%", Messages.config.getString("Messages.Prefix"))));
            return true;
        }
        return false;
    }
}

