package me.rudraksha007.Commands.Player;

import me.rudraksha007.Java.MLGGameManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PracticeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){sender.sendMessage("You can't do this from here!");return true;}
        Player player = (Player) sender;
        if (!player.hasPermission("practice.player")){
            player.sendMessage(form("&c&lYou don't have permission to do this!"));return true;}
        if (args.length>0){ player.sendMessage(form("&c&lInvalid command. Usage: /practice: joins a MLG game"));return true;}
        new MLGGameManager().quickJoinMLG(player);
        return true;
    }

    public String form(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
