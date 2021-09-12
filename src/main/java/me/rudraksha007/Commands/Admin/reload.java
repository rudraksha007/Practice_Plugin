package me.rudraksha007.Commands.Admin;

import me.rudraksha007.Java.ArenaManager;
import me.rudraksha007.Objects.MLGArena;
import me.rudraksha007.Practice;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.logging.Level;

import static me.rudraksha007.Objects.HashMaps.Lobby;
import static me.rudraksha007.Objects.HashMaps.MLGArenas;

public class reload {

    FileConfiguration config = Practice.plugin.getConfig();
    public void execute(Player player){
        Practice.plugin.saveConfig();
        Practice.plugin.reloadConfig();
        try {Lobby = new ArenaManager().loadLocation("lobby");
        }catch (NullPointerException e){
            Bukkit.getLogger().log(Level.SEVERE, "No registered Lobby found. Please register ASAP as no games will run further"); return;}
        if (Lobby==null){Bukkit.getLogger().log(Level.SEVERE, "No registered Lobby found. Please register ASAP as no games will run further");return; }
        try {for (String s: config.getConfigurationSection("arenas.mlg").getKeys(false)){
            MLGArena arena= new ArenaManager().loadMLGArena("arenas.mlg."+s);
            if (arena==null)continue;
            if (MLGArenas.contains(arena.getDefault()))continue;
            Bukkit.getLogger().log(Level.INFO, "Registered a new of the arenas!");
            player.sendMessage(ChatColor.GREEN+""+ChatColor.BOLD+"Registered a new of the arenas!");
            MLGArenas.add(arena);}
        }catch (NullPointerException e){Bukkit.getLogger().log(Level.SEVERE, "No registered arenas for MLG found, please register ASAP");}

    }
}
