package me.rudraksha007.GameModes.Parkour.Listeners;

import me.rudraksha007.Objects.ParkourArena;
import org.bukkit.event.entity.EntityDamageEvent;

public class ParkourDamage {
    public void onDamage(EntityDamageEvent event, ParkourArena a) {
        event.setCancelled(true);
    }
}
