package me.rudraksha007.Listeners;

import me.rudraksha007.Java.MLGGameManager;
import me.rudraksha007.Objects.MLGArena;
import me.rudraksha007.Practice;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import static me.rudraksha007.Objects.HashMaps.igp;

public class Move implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        if (!igp.containsKey(event.getPlayer().getUniqueId()))return;
        Player player = event.getPlayer();
        MLGArena arena = (MLGArena) igp.get(player.getUniqueId());
        if (player.getLocation().getY()<10){
            player.damage(10);
        }
        if (!player.getWorld().getBlockAt(player.getLocation().add(0,-1,0)).getType().equals(Material.GOLD_BLOCK))return;
        Bukkit.getScheduler().runTaskLater(Practice.plugin, new Runnable() {
            @Override
            public void run() {
                if (!player.getWorld().getBlockAt(player.getLocation().add(0,-1,0)).getType().equals(Material.GOLD_BLOCK))return;
                    player.sendMessage(form("&a&lYou succeeded in this try!! resetting for you!"));
                    int val = (int) ((-0.5* arena.getHeight())+30);
                    arena.setScore(arena.getScore()+val);
                    arena.setWins(arena.getWins()+1);
                igp.put(player.getUniqueId(), arena);
                new MLGGameManager().resetMLG(player);
            }
        },1L);
    }

    public String form(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
