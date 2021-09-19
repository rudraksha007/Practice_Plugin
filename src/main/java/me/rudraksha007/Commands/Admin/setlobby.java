package me.rudraksha007.Commands.Admin;

import me.rudraksha007.ArenaManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class setlobby {
    public void execute(Player player){
        new ArenaManager().save(player.getLocation(), "lobby");
        player.sendMessage(form("&a&lLobby point has been set!"));
    }

    public String form(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
