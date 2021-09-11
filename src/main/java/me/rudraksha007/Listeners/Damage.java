package me.rudraksha007.Listeners;

import org.bukkit.attribute.Attribute;
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
        event.setCancelled(true);
        Player player = (Player) event.getEntity();
        player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()/2);
    }
}
