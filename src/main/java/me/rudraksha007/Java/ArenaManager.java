package me.rudraksha007.Java;

import me.rudraksha007.Objects.Arena;
import me.rudraksha007.Objects.MLGArena;
import me.rudraksha007.Practice;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.logging.Level;

import static me.rudraksha007.Objects.HashMaps.Lobby;
import static me.rudraksha007.Objects.HashMaps.MLGArenas;

public class ArenaManager {

    FileConfiguration config = Practice.plugin.getConfig();

    public void save(Location loc, String path){
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        World world = loc.getWorld();
        String val = x+":"+y+":"+z+":"+world;
        config.set(path, val);
        Practice.plugin.saveConfig();
    }

    public void save(Arena arena){
        int x1 = arena.getSpawn().getBlockX();
        int x2 = arena.getEnd().getBlockX();
        int y1 = arena.getSpawn().getBlockY();
        int y2 = arena.getEnd().getBlockY();
        int z1 = arena.getSpawn().getBlockZ();
        int z2 = arena.getEnd().getBlockZ();
        World w1 = arena.getSpawn().getWorld();
        World w2 = arena.getEnd().getWorld();
        String spawn = x1+":"+y1+":"+z1+":"+w1.getName();
        String end = x2+":"+y2+":"+z2+":"+w2.getName();
        String path = "arenas.mlg";
        if(config.getConfigurationSection(path)==null||
                config.getConfigurationSection(path).getKeys(false).size()==0) {
            path = path+"."+0;
        }else {
            path = path+"."+config.getConfigurationSection(path).getKeys(false).size();
        }
        config.set(path+".spawn", spawn);
        config.set(path+".end", end);
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

    public MLGArena loadMLGArena(String path){
        if (config.getString(path+".spawn")==null||
                config.getString(path+".end")==null)return null;
        Location location = loadLocation(path+".end");
        Location location1 = loadLocation(path+".spawn");
        return new MLGArena(null, location1, location, null, Material.WATER_BUCKET, 0, 0, 0, 0);
    }

    public void setupArenas() {
        try {Lobby = loadLocation("lobby");}catch (NullPointerException e){
            Bukkit.getLogger().log(Level.SEVERE, "No registered Lobby found. Please register ASAP as no games will run further"); return;}

        try {for (String s: config.getConfigurationSection("maps.mlg").getKeys(false)){
                MLGArena arena = loadMLGArena(s);
                if (arena==null)continue;
                MLGArenas.add(arena);}
        }catch (NullPointerException e){Bukkit.getLogger().log(Level.SEVERE, "No registered arenas for MLG found, please register ASAP");}

    }
}
