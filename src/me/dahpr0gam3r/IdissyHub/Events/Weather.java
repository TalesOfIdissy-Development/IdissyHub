/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.weather.WeatherChangeEvent
 */
package me.dahpr0gam3r.IdissyHub.Events;

import me.dahpr0gam3r.IdissyHub.IdissyHub;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class Weather
implements Listener {
    @EventHandler
    public void onWeatherChangeEvent(WeatherChangeEvent event) {
        if (event.toWeatherState() && IdissyHub.getInstance().getConfig().getString("World_Settings.Disable_Weather_Change").equalsIgnoreCase("true")) {
            event.setCancelled(true);
        }
    }
}

