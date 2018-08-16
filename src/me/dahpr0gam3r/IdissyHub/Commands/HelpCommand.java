package me.dahpr0gam3r.IdissyHub.Commands;

import me.dahpr0gam3r.IdissyHub.IdissyHub;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;


public class HelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Plugin plugin = IdissyHub.getInstance();
        if (command.getName().equalsIgnoreCase("help") || command.getName().equalsIgnoreCase("?")) {
                String string0 = String.join("\n", plugin.getConfig()
                        .getStringList("HelpCommand.HelpMessage"));
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', string0));
                return true;
        }
        return false;
    }
}
