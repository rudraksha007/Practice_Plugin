package me.rudraksha007.Java;

import dev.jcsoftware.jscoreboards.JPerPlayerScoreboard;
import me.rudraksha007.Objects.Arena;
import me.rudraksha007.Objects.HashMaps;
import me.rudraksha007.Objects.MLGArena;
import me.rudraksha007.Practice;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

public class Timers {

    public Map<UUID, Arena> igp = HashMaps.igp;
    int time = 180;
    int id = 0;

    public void startMLG(Player player1){
        MLGArena arena = (MLGArena) igp.get(player1.getUniqueId());
        JPerPlayerScoreboard scoreBoard = new JPerPlayerScoreboard(
                (player) ->{
                    return "&a&l<<&c&l Practice MLG &a&l>>";
                },
                (player) ->{
                    return Arrays.asList("&c&lTime Left: &a&l"+time, "&c&lSuccessful MLGs: &a&l"+arena.getWins(),
                            "&c&lFailed MLGs: &a&l"+arena.getFails(), "&c&lTotal Score: &a&l"+arena.getScore());
                });
        scoreBoard.addPlayer(player1);
        id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Practice.plugin, new Runnable() {
            @Override
            public void run() {
                scoreBoard.updateScoreboard();
                if(!igp.containsKey(player1.getUniqueId())||!player1.isOnline()){
                    scoreBoard.destroy();
                    Bukkit.getScheduler().cancelTask(id);
                }
                if (time==60)player1.sendMessage(form("&a&l60 seconds are remaining"));
                else if (time==30) player1.sendMessage(form("&e&l30 seconds are left for this round to end!"));
                else if (time==10) player1.sendMessage(form("&c&lOnly 10 seconds are left"));
                else if (time==0){
                    new MLGGameManager().endMLG(arena);
                    Bukkit.getScheduler().cancelTask(id);
                    scoreBoard.removePlayer(player1);
                    scoreBoard.destroy();
                }
                time--;
            }
        }, 20L, 20L);
    }

    public String form(String msg){
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
