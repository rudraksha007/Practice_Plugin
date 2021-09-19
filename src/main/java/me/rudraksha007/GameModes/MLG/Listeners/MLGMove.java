package me.rudraksha007.GameModes.MLG.Listeners;

import me.rudraksha007.GameModes.MLG.MLGGameManager;
import me.rudraksha007.Objects.MLGArena;
import me.rudraksha007.Practice;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import static me.rudraksha007.Objects.HashMaps.igp;
import static me.rudraksha007.Practice.form;
import static me.rudraksha007.Practice.level;

public class MLGMove {

    public void onMove(Player player, MLGArena arena){
        if (player.getLocation().getY()<10){ player.setHealth(10); return;}
        if (!player.getWorld().getBlockAt(player.getLocation().add(0,-1,0)).getType().equals(Material.GOLD_BLOCK))return;
        Bukkit.getScheduler().runTaskLater(Practice.plugin, () -> {
            if (!player.getWorld().getBlockAt(player.getLocation().add(0,-1,0)).getType().equals(Material.GOLD_BLOCK))return;
            player.sendMessage(form("&a&lYou succeeded in this try!! resetting for you!"));
            player.playSound(arena.getSpawn(), level, 1, 1);
            int val = (int) ((-0.5* arena.getHeight())+30);
            arena.setScore(arena.getScore()+val);
            arena.setWins(arena.getWins()+1);
            igp.put(player.getUniqueId(), arena);
            new MLGGameManager().resetMLG(player);
        },1L);
    }
}
