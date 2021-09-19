package me.rudraksha007.GameModes.MLG;

import me.rudraksha007.ArenaManager;
import me.rudraksha007.Java.Timers;
import me.rudraksha007.Objects.Arena;
import me.rudraksha007.Objects.HashMaps;
import me.rudraksha007.Objects.MLGArena;
import me.rudraksha007.Practice;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class MLGGameManager {

    public Map<UUID, Arena> igp = HashMaps.igp;
    public Map<UUID, ItemStack[]> invs = HashMaps.invs;
    public Set<MLGArena> MLGArenas = HashMaps.MLGArenas;
    FileConfiguration config = Practice.plugin.getConfig();

    public void quickJoinMLG(Player player){
        if (MLGArenas.isEmpty()){player.sendMessage(form("&c&lNo games are available right now!"));return;}
        if (igp.containsKey(player.getUniqueId())){player.sendMessage(form("&c&lYou are already in a game!"));return;}
        for (MLGArena a: MLGArenas){
            startMLG(a, player);
            break;
        }
        int games;
        try {
            games = config.getInt("player-data."+player.getUniqueId()+".total-games");
            config.set("player-data."+player.getUniqueId()+".total-games", games+ 1);
        }catch (NullPointerException e){
            config.set("player-data."+player.getUniqueId()+".total-games", 1);
        }
    }

    public void startMLG(MLGArena arena1, Player player){
        MLGArena arena = arena1.getDefault();
        arena.setPlayer(player);
        player.teleport(arena.getSpawn());
        new ArenaManager().saveInventory(player);
        giveMLGItems(arena);
        igp.put(player.getUniqueId(), arena);
        createPlatform(arena, 2, 0);
        player.sendMessage(form("&a&lYou entered a game. Good Luck!"));
        new Timers().startMLG(player);
    }

    public void endMLG(MLGArena arena){
        Player player = arena.getPlayer();
        player.setNoDamageTicks(5);
        player.teleport(HashMaps.Lobby);
        player.sendMessage(form("&f&l<<<---------------------------------------->>>"));
        player.sendMessage(form(   "&a&lThe game you were in, just ended"));
        player.sendMessage(form("&a&lA total of "+"&a&lA total of "+arena.getScore()+" was added to your account"));
        player.sendMessage(form("&f&l<<<---------------------------------------->>>"));
        player.setHealth(player.getMaxHealth());
        player.getInventory().clear();
        player.getInventory().setContents(invs.get(player.getUniqueId()));
        player.updateInventory();
        invs.remove(player.getUniqueId());
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
                loc.getWorld().getBlockAt(loc.getBlockX()+i, loc.getBlockY()+arena.getHeight(), loc.getBlockZ()+j).setType(Material.AIR);
            }
        }
        igp.remove(player.getUniqueId());
        MLGArenas.add(arena.getDefault());
        Practice.plugin.saveConfig();
    }

    public void resetMLG(Player player){
        player.setVelocity(new Vector(0,0,0).multiply(0));
        MLGArena arena = (MLGArena) igp.get(player.getUniqueId());
        player.teleport(arena.getSpawn());
        if (arena.getBlocks()!=null){
            for (Location loc : arena.getBlocks()){player.getWorld().getBlockAt(loc).setType(Material.AIR);}}
        arena.setBlocks(null);
        giveMLGItems(arena);
        Bukkit.getScheduler().runTaskLater(Practice.plugin, () -> player.setHealth(player.getMaxHealth()), 5L);
        igp.put(player.getUniqueId(), arena);
    }

    public void giveMLGItems(MLGArena arena) {
        Player player = arena.getPlayer();
        player.getInventory().clear();
        ItemStack options = new ItemStack(Material.COMPASS);
        ItemMeta meta = options.getItemMeta();
        meta.setDisplayName(form("&aGame Options"));
        meta.addEnchant(Enchantment.DURABILITY, 3, true);
        options.setItemMeta(meta);
        player.getInventory().setItem(0, options);

        ItemStack mlg = new ItemStack(arena.getMLGType());
        player.getInventory().setItem(1, mlg);
    }

    public void createPlatform(MLGArena arena, int size, int height){
        Location loc = arena.getEnd();
        if (size==2){
            for (int i=-2; i!=3; i++){
                for (int j=-2; j!=3;j++){
                    loc.getWorld().getBlockAt(loc.getBlockX()+i, loc.getBlockY()+arena.getHeight(), loc.getBlockZ()+j).setType(Material.AIR);
                    loc.getWorld().getBlockAt(loc.getBlockX()+i, loc.getBlockY()+height,loc.getBlockZ()+j).setType(Material.GOLD_BLOCK);
                }
            }
        }
        else if (size==1){
            for (int i =-2; i!=3; i++){
                for (int j = -2; j!=3; j++){
                    if (i==-2||i==2||j==-2||j==2){
                        loc.getWorld().getBlockAt(loc.getBlockX()+i, loc.getBlockY()+arena.getHeight(), loc.getBlockZ()+j).setType(Material.AIR);
                    }else {
                        loc.getWorld().getBlockAt(loc.getBlockX() + i, loc.getBlockY() + arena.getHeight(), loc.getBlockZ() + j).setType(Material.AIR);
                        loc.getWorld().getBlockAt(loc.getBlockX() + i, loc.getBlockY() + height, loc.getBlockZ() + j).setType(Material.GOLD_BLOCK);
                    }
                }
            }
        }else {
            for (int i =-2; i!=3; i++){
                for (int j = -2; j!=3; j++){
                    if (i==0&&j==0){
                        loc.getWorld().getBlockAt(loc.getBlockX()+i, loc.getBlockY()+arena.getHeight(), loc.getBlockZ()+j).setType(Material.AIR);
                        loc.getWorld().getBlockAt(loc.getBlockX() + i, loc.getBlockY() + height, loc.getBlockZ() + j).setType(Material.GOLD_BLOCK);
                    }else {
                        loc.getWorld().getBlockAt(loc.getBlockX() + i, loc.getBlockY() + arena.getHeight(), loc.getBlockZ() + j).setType(Material.AIR);
                    }
                }
            }
        }
        arena.setHeight(height);
        igp.put(arena.getPlayer().getUniqueId(),arena);
    }

    public String form(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
