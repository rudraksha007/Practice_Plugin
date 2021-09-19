package me.rudraksha007.GameModes.MLG.Listeners;

import me.rudraksha007.Objects.MLGArena;
import me.rudraksha007.Practice;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static me.rudraksha007.Objects.HashMaps.*;

public class MLGQuit {

    FileConfiguration config = Practice.plugin.getConfig();

    public void onQuit(Player player, MLGArena arena){
        player.teleport(Lobby);
        player.setHealth(player.getMaxHealth());
        player.getInventory().clear();
        player.getInventory().setContents(invs.get(player.getUniqueId()));
        player.updateInventory();
//TODO catch null pointers while getting all stored scores and values!/////////////////////////////////////////////////////////
        try { int score = config.getInt("player-data."+player.getUniqueId()+".total-score")+ arena.getScore();
            config.set("player-data."+player.getUniqueId()+".total-score", score);
        }catch (NullPointerException e){config.set("player-data."+player.getUniqueId()+".total-score", arena.getScore());}


        try {int wins = config.getInt("player-data."+player.getUniqueId()+".wins")+ arena.getWins();
            config.set("player-data."+player.getUniqueId()+".wins", wins);
        }catch (NullPointerException e){config.set("player-data."+player.getUniqueId()+".wins", arena.getWins());}


        try {int fails = config.getInt("player-data."+player.getUniqueId()+".fails")+ arena.getFails();
            config.set("player-data."+player.getUniqueId()+".fails", fails);
        }catch (NullPointerException e){config.set("player-data."+player.getUniqueId()+".fails", arena.getFails());}
//TODO try catch ends here ////////////////////////////////////////////////////////////////////////////////////////////////////

        if (arena.getBlocks()!=null){for (Location loc: arena.getBlocks()){ loc.getWorld().getBlockAt(loc).setType(Material.AIR);}}
        Location loc = arena.getEnd();
        for (int i = -2; i!=3; i++){
            for (int j=-2; j!=3;j++){
                loc.getWorld().getBlockAt(loc.getBlockX()+i, loc.getBlockY()+arena.getHeight(), loc.getBlockZ()).setType(Material.AIR);
            }
        }
        igp.remove(player.getUniqueId());
        MLGArenas.add(arena.getDefault());
    }
}
