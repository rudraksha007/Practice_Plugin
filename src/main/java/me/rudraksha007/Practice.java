package me.rudraksha007;

import me.rudraksha007.Commands.Admin.Admin;
import me.rudraksha007.Commands.Player.PracticeCommand;
import me.rudraksha007.Commands.Player.leave;
import me.rudraksha007.Commands.TabCompleters.pa;
import me.rudraksha007.Commands.TabCompleters.practiceCompleter;
import me.rudraksha007.Java.ArenaManager;
import me.rudraksha007.Java.MLGGameManager;
import me.rudraksha007.Java.ParkourManager;
import me.rudraksha007.Listeners.*;
import me.rudraksha007.Objects.Arena;
import me.rudraksha007.Objects.MLGArena;
import me.rudraksha007.Objects.ParkourArena;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;
import java.util.logging.Level;

import static me.rudraksha007.Objects.HashMaps.igp;

public final class Practice extends JavaPlugin {

    public static Practice plugin;
    public static ItemStack boat;
    public static ItemStack web;
    public static ItemStack plate;
    public static Sound wither_hurt;
    public static Sound level;

    @Override
    public void onEnable() {
        plugin = this;
        this.saveConfig();
        if (this.getServer().getPluginManager().getPlugin("PlaceholderAPI")==null)
            Bukkit.getLogger().log(Level.SEVERE, "PlaceholderAPI is not not found, please install it ASAP");
        else new PlaceHolders().register();
        setupCommands();
        setupListeners();
        setupCrossVersion();
        new ArenaManager().setupArenas();
    }

    @Override
    public void onDisable() {
        if (!igp.isEmpty()){ for (UUID id: igp.keySet()){
            Arena a = igp.get(id);
            if (a instanceof MLGArena) new MLGGameManager().endMLG((MLGArena) igp.get(id));
            else if (a instanceof ParkourArena) new ParkourManager().Leave(id);
            }
        }
        igp.clear();
        this.saveConfig();
    }

    public void setupCommands(){
        this.getCommand("practice").setExecutor(new PracticeCommand());
        this.getCommand("practice").setTabCompleter(new practiceCompleter());
        this.getCommand("practice").setAliases(Collections.singletonList("pr"));
        this.getCommand("pa").setExecutor(new Admin());
        this.getCommand("pa").setTabCompleter(new pa());
        this.getCommand("pa").setAliases(Arrays.asList("practice", "pra", "admin", "practicing"));
        this.getCommand("prleave").setExecutor(new leave());
    }

    public void setupListeners(){
        this.getServer().getPluginManager().registerEvents(new Damage(), this);
        this.getServer().getPluginManager().registerEvents(new Interact(), this);
        this.getServer().getPluginManager().registerEvents(new Move(), this);
        this.getServer().getPluginManager().registerEvents(new Quit(), this);
        this.getServer().getPluginManager().registerEvents(new Place(), this);
    }

    public void setupCrossVersion(){
        try {boat = new ItemStack(Material.matchMaterial("OAK_BOAT")); }catch (Exception e){ boat = new ItemStack(Material.matchMaterial("BOAT")); }
        try { web =  new ItemStack(Material.matchMaterial("COBWEB")); }catch (Exception e){ web = new ItemStack(Material.matchMaterial("WEB")); }
        try { plate = new ItemStack(Material.matchMaterial("HEAVY_WEIGHTED_PRESSURE_PLATE")); }catch (Exception e){ plate = new ItemStack(Material.matchMaterial("GOLD_PLATE")); }
        try { wither_hurt = Sound.valueOf("ENTITY_WITHER_HURT"); }catch (Exception e){ wither_hurt = Sound.valueOf("WITHER_HURT"); }
        try { level = Sound.valueOf("ENTITY_PLAYER_LEVELUP"); }catch (Exception e){ wither_hurt = Sound.valueOf("LEVEL_UP"); }
    }
}
