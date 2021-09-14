package me.rudraksha007.Commands.Admin;

import me.rudraksha007.Java.ArenaManager;
import me.rudraksha007.Java.MLGGameManager;
import me.rudraksha007.Objects.MLGArena;
import me.rudraksha007.Practice;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

import static me.rudraksha007.Objects.HashMaps.igp;

public class reload {

    public void execute(Player player){
        if (!igp.isEmpty()){
            for (UUID id: igp.keySet()){
                new MLGGameManager().endMLG((MLGArena) igp.get(id));
            }
        }
        Practice.plugin.saveConfig();
        Practice.plugin.reloadConfig();
        new ArenaManager().setupArenas();
        player.sendMessage(ChatColor.GREEN+""+ChatColor.BOLD+"Plugin reload successful!");
    }
}
