/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.GameMode
 *  org.bukkit.Location
 *  org.bukkit.World
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.configuration.ConfigurationSection
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerJoinEvent
 */
package me.dahpr0gam3r.IdissyHub.Commands;

import me.dahpr0gam3r.IdissyHub.Config.Data;
import me.dahpr0gam3r.IdissyHub.Config.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SpawnCommand
implements CommandExecutor,
Listener {
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        String Prefix = ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.Prefix"));
        if (!(sender instanceof Player)) {
            sender.sendMessage("You cannot teleport to spawn!");
            return true;
        }
        Player p = (Player)sender;
        if (cmd.getName().equalsIgnoreCase("spawn")) {
            if (Data.config.getConfigurationSection("Spawn") == null) {
                p.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)(String.valueOf(Prefix) + " &cThe spawn has not been set yet. Please contact a server administrator to set it. &7(/setspawn)")));
                return true;
            }
            World w = Bukkit.getServer().getWorld(Data.config.getString("Spawn.world"));
            double x = Data.config.getDouble("Spawn.x");
            double y = Data.config.getDouble("Spawn.y");
            double z = Data.config.getDouble("Spawn.z");
            float pitch = Data.config.getInt("Spawn.pitch");
            float yaw = Data.config.getInt("Spawn.yaw");
            p.teleport(new Location(w, x, y, z, yaw, pitch));
            return true;
        }
        return false;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (p.getGameMode().equals((Object)"CREATIVE")) {
            // empty if block
        }
        p.setGameMode(GameMode.SURVIVAL);
        if (Data.config.getConfigurationSection("Spawn") == null) {
            return;
        }
        World w = Bukkit.getServer().getWorld(Data.config.getString("Spawn.world"));
        double x = Data.config.getDouble("Spawn.x");
        double y = Data.config.getDouble("Spawn.y");
        double z = Data.config.getDouble("Spawn.z");
        float pitch = Data.config.getInt("Spawn.pitch");
        float yaw = Data.config.getInt("Spawn.yaw");
        p.teleport(new Location(w, x, y, z, yaw, pitch));
    }
}

