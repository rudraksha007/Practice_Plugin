package me.rudraksha007.Commands.Admin;

import me.rudraksha007.Java.ArenaManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class setlobby {
    public void execute(Player player){
        new ArenaManager().save(player.getLocation(), "lobby");
    }

    public String form(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
