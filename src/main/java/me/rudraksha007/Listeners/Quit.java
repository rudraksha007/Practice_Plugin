package me.rudraksha007.Listeners;

import me.rudraksha007.GameModes.MLG.Listeners.MLGQuit;
import me.rudraksha007.GameModes.Parkour.ParkourManager;
import me.rudraksha007.Objects.Arena;
import me.rudraksha007.Objects.MLGArena;
import me.rudraksha007.Objects.ParkourArena;
import me.rudraksha007.Practice;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static me.rudraksha007.Objects.HashMaps.igp;

public class Quit implements Listener {

    FileConfiguration config = Practice.plugin.getConfig();

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        if (igp.isEmpty())return;
        if (!igp.containsKey(event.getPlayer().getUniqueId()))return;
        Player player = event.getPlayer();
        Arena a = igp.get(player.getUniqueId());
        if (a instanceof MLGArena)new MLGQuit().onQuit(player, (MLGArena) a);
        else if (a instanceof ParkourArena)new ParkourManager().Leave(player.getUniqueId());
    }

}
