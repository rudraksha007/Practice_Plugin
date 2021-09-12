package me.rudraksha007;

import me.rudraksha007.Commands.Admin.Admin;
import me.rudraksha007.Commands.Player.PracticeCommand;
import me.rudraksha007.Commands.Player.leave;
import me.rudraksha007.Java.ArenaManager;
import me.rudraksha007.Java.MLGGameManager;
import me.rudraksha007.Listeners.*;
import me.rudraksha007.Objects.MLGArena;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;
import java.util.logging.Level;

import static me.rudraksha007.Objects.HashMaps.igp;

public final class Practice extends JavaPlugin {

    public static Practice plugin;
    @Override
    public void onEnable() {
        plugin = this;
        this.saveConfig();
        if (this.getServer().getPluginManager().getPlugin("PlaceholderAPI")==null)
            Bukkit.getLogger().log(Level.SEVERE, "PlaceholderAPI is not not found, please install it ASAP");
        else new PlaceHolders().register();
        setupCommands();
        setupListeners();
        new ArenaManager().setupArenas();
    }

    @Override
    public void onDisable() {
        for (UUID id: igp.keySet()){
            new MLGGameManager().endMLG((MLGArena) igp.get(id));
        }
        this.saveConfig();
    }

    public void setupCommands(){
        this.getCommand("practice").setExecutor(new PracticeCommand());
        this.getCommand("pa").setExecutor(new Admin());
        this.getCommand("prleave").setExecutor(new leave());
    }

    public void setupListeners(){
        this.getServer().getPluginManager().registerEvents(new Damage(), this);
        this.getServer().getPluginManager().registerEvents(new Interact(), this);
        this.getServer().getPluginManager().registerEvents(new Move(), this);
        this.getServer().getPluginManager().registerEvents(new Quit(), this);
        this.getServer().getPluginManager().registerEvents(new Place(), this);
    }
}
