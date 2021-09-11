package me.rudraksha007.Objects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class MLGArena extends Arena{

    Player player;
    Location spawn;
    Location end;
    List<Location> blocks;
    Material MLGType;
    int height;
    int wins;
    int fails;
    int scores;

    public MLGArena(Player player, Location spawn, Location end, List<Location> blocks, Material MLGType, int height, int wins, int scores, int fails) {
        super(player, spawn, end, blocks);
        this.player = player;
        this.spawn = spawn;
        this.end = end;
        this.blocks = blocks;
        this.MLGType = MLGType;
        this.height = height;
        this.wins = wins;
        this.fails = fails;
        this.scores = scores;
    }

    public int getFails() {
        return fails;
    }

    public void setFails(int fails) {
        this.fails = fails;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getScore() {
        return scores;
    }

    public void setScore(int scores) {
        this.scores = scores;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public Material getMLGType() {
        return MLGType;
    }

    public void setMLGType(Material MLGType) {
        this.MLGType = MLGType;
    }

    public MLGArena getDefault(){
        this.setPlayer(null);
        this.setFails(0);
        this.setMLGType(Material.WATER_BUCKET);
        this.setHeight(0);
        this.setScore(0);
        this.setWins(0);
        this.setBlocks(null);
        return this;
    }
}
