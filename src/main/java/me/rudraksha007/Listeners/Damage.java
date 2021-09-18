package me.rudraksha007.Listeners;

import me.rudraksha007.Java.MLGGameManager;
import me.rudraksha007.Objects.Arena;
import me.rudraksha007.Objects.MLGArena;
import me.rudraksha007.Objects.ParkourArena;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import static me.rudraksha007.Objects.HashMaps.igp;
import static me.rudraksha007.Practice.wither_hurt;

public class Damage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if (!(event.getEntity() instanceof Player))return;
        if (!igp.containsKey(event.getEntity().getUniqueId()))return;
        if (!event.getCause().equals(EntityDamageEvent.DamageCause.FALL))return;
        Arena a = igp.get(event.getEntity().getUniqueId());
        if (a instanceof MLGArena){
            event.setCancelled(true);
            Player player = (Player) event.getEntity();
            MLGArena arena = (MLGArena) igp.get(player.getUniqueId());
            player.sendMessage(form("&c&lYou failed in this try, resetting for you!"));
            arena.setFails(arena.getFails()+1);
            new MLGGameManager().resetMLG(player);
            player.playSound(player.getLocation(), wither_hurt, 1, 1);
        }
        else if (a instanceof ParkourArena)event.setCancelled(true);
    }

    public String form(String msg){
        return ChatColor.translateAlternateColorCodes('&', msg );
    }
}
