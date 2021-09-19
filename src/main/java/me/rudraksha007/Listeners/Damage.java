package me.rudraksha007.Listeners;

import me.rudraksha007.GameModes.MLG.Listeners.MLGDamage;
import me.rudraksha007.GameModes.Parkour.Listeners.ParkourDamage;
import me.rudraksha007.Objects.Arena;
import me.rudraksha007.Objects.MLGArena;
import me.rudraksha007.Objects.ParkourArena;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import static me.rudraksha007.Objects.HashMaps.igp;

public class Damage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if (!(event.getEntity() instanceof Player))return;
        if (!igp.containsKey(event.getEntity().getUniqueId()))return;
        if (!event.getCause().equals(EntityDamageEvent.DamageCause.FALL))return;
        Arena a = igp.get(event.getEntity().getUniqueId());

        if (a instanceof MLGArena) new MLGDamage().onDamage(event, (MLGArena) a);
        else if (a instanceof ParkourArena)new ParkourDamage().onDamage(event, (ParkourArena)a);
    }
}
