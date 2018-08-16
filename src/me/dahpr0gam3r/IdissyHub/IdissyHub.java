/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.google.common.io.ByteArrayDataOutput
 *  com.google.common.io.ByteStreams
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Server
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.command.ConsoleCommandSender
 *  org.bukkit.command.PluginCommand
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Listener
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.PluginDescriptionFile
 *  org.bukkit.plugin.PluginManager
 *  org.bukkit.plugin.java.JavaPlugin
 *  org.bukkit.plugin.messaging.Messenger
 */
package me.dahpr0gam3r.IdissyHub;


import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.dahpr0gam3r.IdissyHub.Commands.*;
import me.dahpr0gam3r.IdissyHub.Commands.Gamemode.*;
import me.dahpr0gam3r.IdissyHub.Config.Data;
import me.dahpr0gam3r.IdissyHub.Config.Messages;
import me.dahpr0gam3r.IdissyHub.Events.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class IdissyHub
extends JavaPlugin
implements Listener {
    public boolean version_8;
    public boolean usingPlaceholderAPI;
    public static IdissyHub pl;
    private static IdissyHub instance;
    private static Messages pluginMessages;
    private static Data pluginData;
    public static IdissyHub Main;

    public static IdissyHub getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&e* Sucessfully hooked into PlaceholderAPI *"));
            this.usingPlaceholderAPI = true;
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&cCould not hook into PlaceholderAPI"));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&cThe placeholders will not work!"));
            this.usingPlaceholderAPI = false;
        }
        this.getCommand("vanish").setExecutor((CommandExecutor)new VanishCommand());
        this.getCommand("fly").setExecutor((CommandExecutor)new FlyCommand());
        this.getCommand("spawn").setExecutor((CommandExecutor)new SpawnCommand());
        this.getCommand("setspawn").setExecutor((CommandExecutor)new SetSpawnCommand());
        this.getCommand("gamemode").setExecutor((CommandExecutor)new GamemodeCommand());
        this.getCommand("help").setExecutor(new HelpCommand());
        this.getCommand("?").setExecutor(new HelpCommand());
        this.getCommand("gms").setExecutor((CommandExecutor)new GamemodeSurvivalCommand());
        this.getCommand("gmc").setExecutor((CommandExecutor)new GamemodeCreativeCommand());
        this.getCommand("gma").setExecutor((CommandExecutor)new GamemodeAdventureCommand());
        this.getCommand("gmsp").setExecutor((CommandExecutor)new GamemodeSpectatorCommand());
        this.getCommand("clearinventory").setExecutor((CommandExecutor)new ClearInventoryCommand());
        this.getCommand("heal").setExecutor((CommandExecutor)new HealCommand());
        this.getCommand("clearchat").setExecutor((CommandExecutor)new ClearchatCommand());
        this.getCommand("lockchat").setExecutor((CommandExecutor)new ChatLockCommand());
        this.getCommand("hub").setExecutor((CommandExecutor) new HubCommand());
        this.getServer().getPluginManager().registerEvents((Listener)new FallDamage(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new VanishCommand(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new FlyCommand(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new Weather(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new VoidSpawn(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new SpawnCommand(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new ChatLockCommand(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new PlayerJoin(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new PlayerQuit(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new BlockBreak(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new BlockPlace(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new Chat(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new ItemDrop(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new ItemPickup(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new PvP(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new Hunger(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new WorldChange(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new PluginBlock(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new ServerSelector(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new PlayerHider(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)this);
        this.getServer().getMessenger().registerOutgoingPluginChannel((Plugin)this, "BungeeCord");
        this.loadConfig();
        this.reload_Config();
        this.sendStart();
        if (Bukkit.getVersion().contains("1.8")) {
            this.version_8 = true;
        }
    }

    public void loadConfig() {
        this.saveDefaultConfig();
        pluginMessages = new Messages((Plugin)this, "lang", true);
        pluginData = new Data((Plugin)this, "data", true);
    }

    public void reload_Config() {
        this.reloadConfig();
        Messages.reload();
        Data.reload();
    }


    private void sendStart() {
        PluginDescriptionFile pdfFile = this.getDescription();
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage((Object)ChatColor.GRAY + "=========> " + (Object)ChatColor.LIGHT_PURPLE + "IdissyHub" + (Object)ChatColor.GRAY + " <=========");
        Bukkit.getConsoleSender().sendMessage((Object)ChatColor.LIGHT_PURPLE + "IdissyHub");
        Bukkit.getConsoleSender().sendMessage((Object)ChatColor.YELLOW + "Made By " + (Object)ChatColor.RED + "dahpr0gam3r");
        Bukkit.getConsoleSender().sendMessage((Object)ChatColor.YELLOW + "Version " + (Object)ChatColor.RED + pdfFile.getVersion());
        Bukkit.getConsoleSender().sendMessage("");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        PluginDescriptionFile pdfFile = this.getDescription();
        if (cmd.getName().equalsIgnoreCase("idissyhub") && (args.length == 0 || args[0].equalsIgnoreCase("help"))) {
            if (!sender.hasPermission("idissyhub.help")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)("&8&l> &7Server is running &dIdissyHub &ev" + pdfFile.getVersion() + " &7By &6dahpr0gam3r")));
                return true;
            }
            sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&8&m+----------***----------+"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)("&dIdissy &fv" + pdfFile.getVersion() + " &cBy dahpr0gam3r")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&8&m+----------***----------+"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&8\u00bb &a/idissyhub reload &7- &fReload the plugin"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&8\u00bb &a/hub&7- &fGo to the hub server"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&8\u00bb &a/vanish &7- &fToggle vanish mode"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&8\u00bb &a/fly &7- &fToggle flight mode"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&8\u00bb &a/setspawn &7- &fSet the spawn location"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&8\u00bb &a/spawn &7- &fTeleport to the spawn location"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&8\u00bb &a/gamemode <gamemode> &7- &fSet your gamemode"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&8\u00bb &a/gmc &7- &fGo into creative mode"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&8\u00bb &a/gms &7- &fGo into survival mode"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&8\u00bb &a/gma &7- &fGo into adventure mode"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&8\u00bb &a/gmsp &7- &fGo into spectator mode"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&8\u00bb &a/heal &7- &fHeal yourself"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&8\u00bb &a/clearinventory &7- &fClear your inventory contents"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&8\u00bb &a/clearchat &7- &fClear global chat"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&8\u00bb &a/lockchat &7- &fLock global chat"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&8\u00bb &a/help &7- &fShows custom help"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&8&m+----------***----------+"));
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("idissyhub.reload")) {
                this.reload_Config();
                sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.Config_Reloaded").replace("%prefix%", Messages.config.getString("Messages.Prefix"))));
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Messages.config.getString("Messages.No_Permission").replace("%prefix%", Messages.config.getString("Messages.Prefix"))));
            }
        }
        return false;
    }

    public void changeServer(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ConnectOther");
        out.writeUTF(player.getName());
        out.writeUTF(server);
        player.sendPluginMessage((Plugin) IdissyHub.getInstance(), "BungeeCord", out.toByteArray());
    }
}

