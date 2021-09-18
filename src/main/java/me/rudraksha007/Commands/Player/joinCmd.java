package me.rudraksha007.Commands.Player;

import me.rudraksha007.Java.MLGGameManager;
import me.rudraksha007.Java.ParkourManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class joinCmd {
    public void execute(Player player, String[] args){
        if (args.length>2){ player.sendMessage(form("&c&lInvalid command. Usage: /practice [join|leave]"));return;}
        switch (args[1].toLowerCase()){
            case "mlg": new MLGGameManager().quickJoinMLG(player);break;
            case "parkour": new ParkourManager().quickJoin(player);break;
            default: player.sendMessage(form("&c&lInvalid argument. Available game are MLG or Parkour"));
        }
    }

    public String form(String msg){
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
