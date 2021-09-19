package me.rudraksha007.GameModes.Parkour.Listeners;

import me.rudraksha007.GameModes.Parkour.ParkourManager;
import me.rudraksha007.Objects.ParkourArena;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Note;
import org.bukkit.entity.Player;

import static me.rudraksha007.Objects.HashMaps.igp;
import static me.rudraksha007.Practice.form;
import static me.rudraksha007.Practice.level;

public class ParkourInteract {

    public void onInteract(ParkourArena arena, Location loc){
        Player player = arena.getPlayer();
        if (arena.getEnd().equals(loc)){ new ParkourManager().Leave(player);
            player.playSound(player.getLocation(), level, 1, 1);
            player.sendMessage(form("&a&lYou completed the parkour course!"));
            player.sendMessage(form("&a&lYou completed the course in &c&l"+ arena.getTime()+"&a&l seconds"));
        }
        else if (arena.getLastCheckpoint()!=null&&!arena.getLastCheckpoint().equals(loc)){
            player.sendMessage(form("&a&lCheckpoint Set!"));
            player.playNote(player.getLocation(), Instrument.PIANO, Note.flat(1, Note.Tone.E));
            arena.setCheckedpoint(loc);
            igp.put(player.getUniqueId(), arena);
        }
    }
}
