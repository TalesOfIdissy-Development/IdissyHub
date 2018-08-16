package me.dahpr0gam3r.IdissyHub.Commands;

import me.dahpr0gam3r.IdissyHub.IdissyHub;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class HubCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You cannot go to the hub server!");
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("hub")) {
            Player player = (Player)sender;
            IdissyHub.getInstance().changeServer(player, IdissyHub.getInstance().getConfig().getString("hub-command.hub-server"));
            return true;
        }
        return false;
    }
}
