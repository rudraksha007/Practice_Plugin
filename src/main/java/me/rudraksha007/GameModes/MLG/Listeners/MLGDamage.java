package me.rudraksha007.GameModes.MLG.Listeners;

import me.rudraksha007.GameModes.MLG.MLGGameManager;
import me.rudraksha007.Objects.MLGArena;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

import static me.rudraksha007.Practice.form;
import static me.rudraksha007.Practice.wither_hurt;

public class MLGDamage {

    public void onDamage(EntityDamageEvent event, MLGArena arena){
            event.setCancelled(true);
            Player player = (Player) event.getEntity();
            player.sendMessage(form("&c&lYou failed in this try, resetting for you!"));
            arena.setFails(arena.getFails()+1);
            new MLGGameManager().resetMLG(player);
            player.playSound(player.getLocation(), wither_hurt, 1, 1);
    }
}
