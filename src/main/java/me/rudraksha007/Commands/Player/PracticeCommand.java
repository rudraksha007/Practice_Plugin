package me.rudraksha007.Commands.Player;

import me.rudraksha007.Java.MLGGameManager;
import me.rudraksha007.Java.ParkourManager;
import me.rudraksha007.Objects.Arena;
import me.rudraksha007.Objects.GUIs.stats;
import me.rudraksha007.Objects.MLGArena;
import me.rudraksha007.Objects.ParkourArena;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.rudraksha007.Objects.HashMaps.igp;

public class PracticeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){sender.sendMessage("You can't do this from here!");return true;}
        Player player = (Player) sender;
        if (!player.hasPermission("practice.go")){
            player.sendMessage(form("&c&lYou don't have permission to do this!"));return true;}
        if (args.length==0){new MLGGameManager().quickJoinMLG(player);return  true;}
        switch (args[0]){
            case "join":new joinCmd().execute(player, args);break;
            case "leave":
                if (!igp.containsKey(player.getUniqueId())){player.sendMessage(form("&c&lYou are not in any game"));return true;}
                Arena a = igp.get(player.getUniqueId());
                if (a instanceof MLGArena){ new MLGGameManager().endMLG((MLGArena) igp.get(player.getUniqueId()));break; }
                else if (a instanceof ParkourArena){ new ParkourManager().Leave(player);break;}

            case "stats": player.openInventory(new stats().create(player));break;
            default: player.sendMessage(form("&c&lInvalid command. Usage: /practice [join|leave|stats]"));break;

        }
        return true;
    }

    public String form(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
