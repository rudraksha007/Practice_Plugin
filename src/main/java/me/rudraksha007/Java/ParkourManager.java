package me.rudraksha007.Java;

import me.rudraksha007.Objects.ParkourArena;
import me.rudraksha007.Practice;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

import static me.rudraksha007.Objects.HashMaps.*;

public class ParkourManager {

    FileConfiguration config = Practice.plugin.getConfig();
    public void quickJoin(Player player){
        if (ParkourArenas.isEmpty()){ player.sendMessage(form("&c&lThere are no arenas available for parkour right now"));return; }
        if (igp.containsKey(player.getUniqueId())){ player.sendMessage(form("&c&lYour are already in a game")); return; }
        for (ParkourArena arena: ParkourArenas){ Join(arena, player);break; }
    }

    public void Join(ParkourArena arena, Player player){
        new ArenaManager().saveInventory(player);
        int games;
        try {
            games = config.getInt("player-data."+player.getUniqueId()+".total-games");
            config.set("player-data."+player.getUniqueId()+".total-games", games+ 1);
        }catch (NullPointerException e){ config.set("player-data."+player.getUniqueId()+".total-parkours", 1); }
        arena.setPlayer(player);
        player.teleport(arena.getSpawn());
        igp.put(player.getUniqueId(), arena);
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));
        player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
        player.sendMessage(form("&a&lYou joined a Parkour course! Good Luck"));
        new Timers().startParkour(player);
    }

    public void Leave(Player player){
        if (igp.isEmpty()||!igp.containsKey(player.getUniqueId())){ player.sendMessage("&c&lYou are not in any Parkour course");return; }
        teleport(player, Lobby);
        player.getInventory().clear();
        player.getInventory().setContents(invs.get(player.getUniqueId()));
        player.updateInventory();
        invs.remove(player.getUniqueId());
        player.getActivePotionEffects().remove(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));
        igp.remove(player.getUniqueId());
        player.sendMessage(form("&a&lYou left the course!"));
    }

    public void Leave(UUID id){
        Player player = Bukkit.getPlayer(id);
        if (igp.isEmpty()||!igp.containsKey(player.getUniqueId())){ player.sendMessage("&c&lYou are not in any Parkour course");return; }
        teleport(player, Lobby);
        player.getInventory().clear();
        player.getInventory().setContents(invs.get(player.getUniqueId()));
        player.updateInventory();
        invs.remove(player.getUniqueId());
        for (PotionEffect e: player.getActivePotionEffects()){
            player.removePotionEffect(e.getType());
        }
        igp.remove(player.getUniqueId());
        player.sendMessage(form("&a&lYou left the course!"));
    }

    public String form(String msg){
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public void teleport(Player player, Location loc){
        player.setNoDamageTicks(5);
        player.teleport(loc);
    }
}
