package me.rudraksha007.Commands.Admin;

import me.rudraksha007.ArenaManager;
import me.rudraksha007.GameModes.MLG.MLGGameManager;
import me.rudraksha007.GameModes.Parkour.ParkourManager;
import me.rudraksha007.Objects.Arena;
import me.rudraksha007.Objects.MLGArena;
import me.rudraksha007.Objects.ParkourArena;
import me.rudraksha007.Practice;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

import static me.rudraksha007.Objects.HashMaps.*;

public class reload {

    public void execute(Player player){
        if (!igp.isEmpty()){
            for (UUID id: igp.keySet()){
                Arena arena = igp.get(id);
                if (arena instanceof MLGArena)new MLGGameManager().endMLG((MLGArena) arena);
                else if (arena instanceof ParkourArena)new ParkourManager().Leave(arena.getPlayer());
            }
        }
        Practice.plugin.saveConfig();
        Practice.plugin.reloadConfig();
        MLGArenas.clear();
        ParkourArenas.clear();
        new ArenaManager().setupArenas();
        player.sendMessage(ChatColor.GREEN+""+ChatColor.BOLD+"Plugin reload successful!");
    }
}
