/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.bukkit.configuration.Configuration
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.configuration.file.YamlConfiguration
 *  org.bukkit.plugin.Plugin
 */
package me.dahpr0gam3r.IdissyHub.Config;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.logging.Level;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Messages {
    private static Plugin plugin;
    private static File folder;
    private static String fileName;
    private static boolean lookForDefault;
    public static FileConfiguration config;
    private static File configFile;

    public Messages(Plugin plugin, File directory, String name, boolean isDefault) {
        if (!name.endsWith(".yml")) {
            name = String.valueOf(name) + ".yml";
        }
        Messages.plugin = plugin;
        folder = directory;
        fileName = name;
        lookForDefault = isDefault;
        Messages.reload();
    }

    public Messages(Plugin plugin, File directory, String name) {
        this(plugin, directory, name, false);
    }

    public Messages(Plugin plugin, String name) {
        this(plugin, plugin.getDataFolder(), name);
    }

    public Messages(Plugin plugin, String name, boolean isDefault) {
        this(plugin, plugin.getDataFolder(), name, isDefault);
    }

    public static FileConfiguration getConfig() {
        if (config == null) {
            Messages.reload();
        }
        return config;
    }

    public static boolean reload() {
        if (!folder.exists()) {
            try {
                if (folder.mkdir()) {
                    plugin.getLogger().log(Level.INFO, "Folder " + folder.getName() + " created.");
                } else {
                    plugin.getLogger().log(Level.SEVERE, "Unable to create folder " + folder.getName() + ".");
                }
            }
            catch (Exception exception) {
                plugin.getLogger().log(Level.WARNING, "Failed to reload " + fileName + " config.");
                return false;
            }
        }
        configFile = new File(folder, fileName);
        config = YamlConfiguration.loadConfiguration((File)configFile);
        if (lookForDefault) {
            try {
                InputStreamReader defaultConfigStream = new InputStreamReader(plugin.getResource(fileName), "UTF8");
                if (defaultConfigStream != null) {
                    YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration((Reader)defaultConfigStream);
                    config.setDefaults((Configuration)defaultConfig);
                    Messages.saveDefaultConfig();
                }
            }
            catch (Exception e) {
                plugin.getLogger().log(Level.WARNING, "Failed to load default " + fileName + " config.");
            }
        } else if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            }
            catch (Exception exception) {
                plugin.getLogger().log(Level.WARNING, "Failed to reload " + fileName + " config.");
                return false;
            }
        }
        return true;
    }

    public static boolean save() {
        if (config == null || configFile == null) {
            return false;
        }
        try {
            Messages.getConfig().save(configFile);
            return true;
        }
        catch (IOException ex) {
            plugin.getLogger().log(Level.WARNING, "Failed to save config to " + configFile.getName(), ex);
            return false;
        }
    }

    public static void saveDefaultConfig() {
        if (configFile == null) {
            configFile = new File(plugin.getDataFolder(), fileName);
        }
        if (!configFile.exists()) {
            plugin.saveResource(fileName, false);
        }
    }

    public Object get(String path) {
        return Messages.getConfig().get(path);
    }

    public void set(String path, Object object) {
        Messages.getConfig().set(path, object);
    }
}

