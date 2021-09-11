package me.rudraksha007.Listeners;

import me.rudraksha007.Objects.MLGArena;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

import java.util.ArrayList;
import java.util.List;

import static me.rudraksha007.Objects.HashMaps.igp;

public class Place implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        if (igp.isEmpty())return;
        if (!igp.containsKey(event.getPlayer().getUniqueId()))return;
        Player player = event.getPlayer();
        MLGArena arena = (MLGArena) igp.get(player.getUniqueId());
        try {
            List<Location> blocks = new ArrayList<>(arena.getBlocks());
            blocks.add(event.getBlock().getLocation());
            arena.setBlocks(blocks);
        }catch (NullPointerException e){
            List<Location> blocks = new ArrayList<>();
            blocks.add(event.getBlock().getLocation());
            arena.setBlocks(blocks);
        }
        igp.put(player.getUniqueId(), arena);
    }

    @EventHandler
    public void onEmpty(PlayerBucketEmptyEvent event){
        if (igp.isEmpty())return;
        if (!igp.containsKey(event.getPlayer().getUniqueId()))return;
        Player player = event.getPlayer();
        MLGArena arena = (MLGArena) igp.get(player.getUniqueId());
        try {
            List<Location> blocks = new ArrayList<>(arena.getBlocks());
            blocks.add(event.getBlockClicked().getLocation().add(0,1,0));
            arena.setBlocks(blocks);
        }catch (NullPointerException e){
            List<Location> blocks = new ArrayList<>();
            blocks.add(event.getBlockClicked().getLocation().add(0,1,0));
            arena.setBlocks(blocks);
        }
        igp.put(player.getUniqueId(), arena);
    }
}
