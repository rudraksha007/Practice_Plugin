package me.rudraksha007.Java;

import me.rudraksha007.Objects.Arena;
import me.rudraksha007.Objects.MLGArena;
import me.rudraksha007.Objects.ParkourArena;
import me.rudraksha007.Practice;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static me.rudraksha007.Objects.HashMaps.*;

public class ArenaManager {

    FileConfiguration config = Practice.plugin.getConfig();

    public void save(Location loc, String path){
        double x = loc.getBlockX();
        double y = loc.getBlockY();
        double z = loc.getBlockZ();
        World world = loc.getWorld();
        String val = x+":"+y+":"+z+":"+world.getName();
        config.set(path, val);
        Practice.plugin.saveConfig();
    }

    public void save(Arena arena){
        String path = null;
        if (arena instanceof MLGArena){
            path = "arenas.mlg";
            if(config.getConfigurationSection(path)==null||
                    config.getConfigurationSection(path).getKeys(false).size()==0) {
                path = path+"."+0;
            }else { path = path+"."+config.getConfigurationSection(path).getKeys(false).size(); }

        }
        else if (arena instanceof ParkourArena){
            path = "arenas.parkour";
            if(config.getConfigurationSection(path)==null||
                    config.getConfigurationSection(path).getKeys(false).size()==0) {
                path = path+"."+0;
            }
            List<String>locs = new ArrayList<>();
            try {
                for (Location loc: ((ParkourArena) arena).getCheckpoints()){
                    int x = loc.getBlockX();
                    int y = loc.getBlockY();
                    int z = loc.getBlockZ();
                    World world = loc.getWorld();
                    String val = x+":"+y+":"+z+":"+world.getName();
                    locs.add(val);
                }
            }catch (NullPointerException e){
                arena.getPlayer().sendMessage(ChatColor.RED+""+ChatColor.BOLD+"Arena was not saved! please add checkpoints and then save them");
            }

            config.set(path+".checkpoints", locs);
        }
        save(arena.getSpawn(), path+".spawn");
        save(arena.getEnd(), path+".end");
        Practice.plugin.saveConfig();
    }

    public Location loadLocation(String path){
        if (config.getString(path)==null)return null;
        String[] loc = config.getString(path).split(":");
        double x = Double.parseDouble(loc[0]);
        double y = Double.parseDouble(loc[1]);
        double z = Double.parseDouble(loc[2]);
        World world = Bukkit.getWorld(loc[3]);
        return new Location(world, x, y, z);
    }

    public Location loadLocation(String[] loc){
        double x = Double.parseDouble(loc[0]);
        double y = Double.parseDouble(loc[1]);
        double z = Double.parseDouble(loc[2]);
        World world = Bukkit.getWorld(loc[3]);
        return new Location(world, x, y, z);
    }

    public Arena loadArena(ArenaType type, String path){
        if (config.getString(path+".spawn")==null||
                config.getString(path+".end")==null){return null;}
        Location location = loadLocation(path+".end");
        Location location1 = loadLocation(path+".spawn");
        if (type.equals(ArenaType.MLG))return new MLGArena(null, location1, location, null, Material.WATER_BUCKET, 0, 0, 0, 0);
        else if (type.equals(ArenaType.PARKOUR)){
            List<String> checks = config.getStringList(path+".checkpoints");
            List<Location> checkpoints = new ArrayList<>();
            for (String s: checks){
                checkpoints.add(loadLocation(s.split(":")));
            }
            return new ParkourArena(null, location1, location, checkpoints, location1, 0);
        }
        return null;
    }

    public void setupArenas() {
        try {Lobby = loadLocation("lobby");
            }catch (NullPointerException e){
            Bukkit.getLogger().log(Level.SEVERE, "No registered Lobby found. Please register ASAP as no games will run further"); return;}
        if (Lobby==null){Bukkit.getLogger().log(Level.SEVERE, "No registered Lobby found. Please register ASAP as no games will run further");return; }
        try {for (String s: config.getConfigurationSection("arenas.mlg").getKeys(false)){
            MLGArena arena= (MLGArena) loadArena(ArenaType.MLG, "arenas.mlg."+s);
                if (arena==null)continue;
                Bukkit.getLogger().log(Level.INFO, "Registered one of the MLG arenas!");
                MLGArenas.add(arena);}
        }catch (NullPointerException e){Bukkit.getLogger().log(Level.SEVERE, "No registered arenas for MLG found, please register ASAP");}
        try {
            for (String s: config.getConfigurationSection("arenas.parkour").getKeys(false)){
                ParkourArena arena = (ParkourArena) loadArena(ArenaType.PARKOUR, "arenas.parkour."+s);
                Bukkit.getLogger().log(Level.INFO, "Registered one of the Parkour arenas!");
                ParkourArenas.add(arena);
            }
        }catch (NullPointerException e){
            Bukkit.getLogger().log(Level.SEVERE, "No Parkour courses found! Please register using right command");
        }
    }

    public void saveInventory(Player player){
        invs.put(player.getUniqueId(), player.getInventory().getContents());
        player.getInventory().clear();

    }

    public enum ArenaType{
        MLG, PARKOUR
    }
}
