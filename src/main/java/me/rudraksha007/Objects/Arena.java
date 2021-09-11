package me.rudraksha007.Objects;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public class Arena {
    Player player;
    Location spawn;
    Location end;
    List<Location> blocks;
    public Arena(Player player, Location spawn, Location end, List<Location> blocks){
        this.player = player;
        this.spawn = spawn;
        this.end = end;
        this.blocks = blocks;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Location getSpawn() {
        return spawn;
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    public Location getEnd() {
        return end;
    }

    public void setEnd(Location end) {
        this.end = end;
    }

    public List<Location> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Location> blocks) {
        this.blocks = blocks;
    }
}
