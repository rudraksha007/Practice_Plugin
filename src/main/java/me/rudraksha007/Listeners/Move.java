package me.rudraksha007.Listeners;

import me.rudraksha007.GameModes.MLG.Listeners.MLGMove;
import me.rudraksha007.GameModes.Parkour.Listeners.ParkourMove;
import me.rudraksha007.Objects.Arena;
import me.rudraksha007.Objects.MLGArena;
import me.rudraksha007.Objects.ParkourArena;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import static me.rudraksha007.Objects.HashMaps.igp;

public class Move implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        if (igp.isEmpty())return;
        if (!igp.containsKey(event.getPlayer().getUniqueId()))return;
        Player player = event.getPlayer();
        Arena a = igp.get(player.getUniqueId());
        if (a instanceof MLGArena)new MLGMove().onMove(player, (MLGArena) a);
        else {
            new ParkourMove().onMove((ParkourArena) a, player);
        }
    }

    public String form(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
