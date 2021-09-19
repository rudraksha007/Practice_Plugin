package me.rudraksha007.Commands.Admin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Admin implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){sender.sendMessage("You can't do this from here!");return true;}
        Player player = (Player) sender;
        if (!player.hasPermission("practice.admin")){
            player.sendMessage(form("&c&lYou don't have permission to do this!"));return true;}
        if (args.length<2){ player.sendMessage(form("&c&lInvalid command. Usage: /pa [addmap|setlobby]"));return true;}
        switch (args[0]){
            case "addmap": new addmap().execute(player, args); break;
            case "setlobby": new setlobby().execute(player);break;
            case "reload":new reload().execute(player);break;
            default: player.sendMessage(form("&c&lInvalid command. Usage: /pa [ addmap | setlobby ]")); return true;
        }
        return true;
    }

    public String form(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
