package me.rudraksha007.GameModes.Parkour.Listeners;

import me.rudraksha007.Objects.ParkourArena;
import org.bukkit.entity.Player;

import static me.rudraksha007.Practice.wither_hurt;

public class ParkourMove {

    public void onMove(ParkourArena arena, Player player){
        if (player.getLocation().getY()<60){
            player.playSound(arena.getLastCheckpoint(), wither_hurt, 1, 1);
            player.setNoDamageTicks(5);player.teleport(arena.getLastCheckpoint().add(0,1,0));
        }
    }
}
