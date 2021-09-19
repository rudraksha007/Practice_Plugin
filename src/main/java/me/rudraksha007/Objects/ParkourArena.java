package me.rudraksha007.Objects;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public class ParkourArena extends Arena{

    Player player;
    Location spawn;
    Location end;
    Location checkedpoint;
    List<Location> checkpoints;
    float time;
    String name;

    public ParkourArena(Player player, Location spawn, Location end, List<Location> blocks, Location checkedpoint, float time, String name) {
        super(player, spawn, end, blocks);
        this.player = player;
        this.spawn = spawn;
        this.end = end;
        this.checkpoints = blocks;
        this.checkedpoint = checkedpoint;
        this.time = time;
        this.name = name;
    }

    public void setCheckpoints(List<Location> points){
        this.checkpoints = points;
    }

    public List<Location> getCheckpoints (){
        return checkpoints;
    }

    public boolean isCheckpoint(Location point){
        return checkpoints.contains(point);
    }

    public void setCheckedpoint(int hash){
        this.checkedpoint = checkpoints.get(hash);
    }

    public void setCheckedpoint(Location checkedpoint){
        if (!checkpoints.contains(checkedpoint))return;
        this.checkedpoint = checkedpoint;
    }

    public Location getLastCheckpoint(){
        return checkedpoint;
    }

    public ParkourArena getDefault(){
      this.player = null;
      this.checkedpoint = spawn;
      return this;
    }

   public void updateTime(){
        this.time = time+0.05F;
   }

   public float getTime(){
        return time;
   }

   public void setTime(float time){
        this.time = time;
   }

   public void setName(String name){
        this.name = name;
   }

   public String getName(){
        return this.name;
   }
}
