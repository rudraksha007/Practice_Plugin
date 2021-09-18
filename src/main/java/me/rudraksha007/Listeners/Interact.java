package me.rudraksha007.Listeners;

import me.rudraksha007.Java.ArenaManager;
import me.rudraksha007.Java.MLGGameManager;
import me.rudraksha007.Java.ParkourManager;
import me.rudraksha007.Objects.Arena;
import me.rudraksha007.Objects.GUIs.GUIs;
import me.rudraksha007.Objects.GUIs.MLGOptions;
import me.rudraksha007.Objects.MLGArena;
import me.rudraksha007.Objects.ParkourArena;
import me.rudraksha007.Practice;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static me.rudraksha007.Objects.HashMaps.*;
import static me.rudraksha007.Practice.*;

public class Interact implements Listener {

    MLGGameManager manager = new MLGGameManager();
    @EventHandler
    public void onInvClick(InventoryClickEvent event){
        if (!igp.containsKey(event.getWhoClicked().getUniqueId()))return;
        if (event.getClickedInventory()==null)return;
        if (event.getCurrentItem()==null)return;
        if (!(event.getClickedInventory().getHolder() instanceof GUIs))return;
        if (!(event.getWhoClicked() instanceof Player))return;
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        if (!(igp.get(player.getUniqueId()) instanceof MLGArena))return;
        if (event.getClickedInventory().getHolder() instanceof MLGOptions){
            MLGArena arena = (MLGArena) igp.get(player.getUniqueId());
            ItemStack stack = event.getCurrentItem();
            if(stack.getType().equals(Material.SUGAR_CANE)) {
                player.sendMessage(ChatColor.GREEN+"Changes were made successfully");
                player.playSound(player.getLocation(), level, 1, 1);
                event.setCancelled(true);
                switch (stack.getAmount()) {
                    case 1: manager.createPlatform(arena,1, 40);break;
                    case 2: manager.createPlatform(arena,1, 20);break;
                    case 3: manager.createPlatform(arena,1, 0);break;
                    case 4: manager.createPlatform(arena,1,-20);break;
                    case 5: manager.createPlatform(arena,1,-40);break;
                }
            }
            else if(stack.getType().equals(Material.GOLD_BLOCK)) {
                switch (stack.getAmount()) {
                    case 1:manager.createPlatform(arena, 2, arena.getHeight());break;
                    case 2:manager.createPlatform(arena, 1, arena.getHeight());break;
                    case 3:manager.createPlatform(arena, 0, arena.getHeight());break;
                }
            }
            if(stack.getType().equals(Material.WATER_BUCKET)) {arena.setMLGType(Material.WATER_BUCKET);}
            else if(stack.getType().equals(Material.LADDER)) {arena.setMLGType(Material.LADDER);}
            else if(stack.getType().equals(boat.getType())) {arena.setMLGType(boat.getType());}
            else if(stack.getType().equals(web.getType())) {arena.setMLGType(web.getType());}
            manager.giveMLGItems(arena);
            igp.put(player.getUniqueId(), arena);

        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction().equals(Action.PHYSICAL)){
            if (igp.isEmpty())return;
            if (!igp.containsKey(player.getUniqueId()))return;
            Arena a = igp.get(player.getUniqueId());
            if (!(a instanceof ParkourArena))return;
            ParkourArena arena = (ParkourArena) a;
            Location loc = event.getClickedBlock().getLocation().add(0,-1,0);
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
        else {
            if(event.getItem()==null)return;
            if(!event.getItem().hasItemMeta())return;
            if(!event.getItem().getItemMeta().hasEnchant(Enchantment.DURABILITY))return;


            if(event.getItem().getType().equals(Material.BLAZE_ROD)) {

                if(event.getClickedBlock()==null)return;
                if(admin.isEmpty())return;
                if(!admin.containsKey(event.getPlayer().getUniqueId()))return;

                if(event.getClickedBlock().getLocation().getBlockY()<50) {
                    player.playSound(player.getLocation(), wither_hurt, 1, 1);
                    player.sendMessage(ChatColor.RED+"End point must be higher than Y=50");return;}
                event.setCancelled(true);
                Arena obj = admin.get(player.getUniqueId());
                if(obj instanceof MLGArena) {
                    MLGArena arena =(MLGArena) obj;
                    if(event.getClickedBlock().getLocation().getBlockY()+50>arena.getSpawn().getBlockY()) {
                        player.playSound(player.getLocation(), wither_hurt, 1, 1);
                        player.sendMessage(ChatColor.RED+"End point must be at least 50 blocks lower than spawn location");
                        return;
                    }
                    arena.setEnd(event.getClickedBlock().getLocation());
                    player.sendMessage(ChatColor.GREEN+"Normal Arena End Location Has Been Set!");
                    player.sendMessage(ChatColor.GREEN+""+ChatColor.BOLD+"Please run /pa reload: to reload the plugin and add the new maps!");
                    new ArenaManager().save(arena);
                    admin.remove(player.getUniqueId());
                    player.getInventory().clear();
                    player.playNote(player.getLocation(), Instrument.PIANO, Note.flat(1, Note.Tone.E));
                }
                else if(obj instanceof ParkourArena){
                    ParkourArena arena = (ParkourArena) obj;
                    arena.setEnd(event.getClickedBlock().getLocation());
                    player.getWorld().getBlockAt(event.getClickedBlock().getLocation().add(0,1,0)).setType(Practice.plate.getType());
                    player.sendMessage(ChatColor.GREEN+"End Location Has Been Set!");
                    admin.put(player.getUniqueId(), arena);
                    ItemStack stack = new ItemStack(Material.STICK);
                    ItemMeta meta = stack.getItemMeta();
                    meta.setDisplayName(form("&a&lCheckpoint adding tool"));
                    meta.addEnchant(Enchantment.DURABILITY, 3, true);
                    stack.setItemMeta(meta);
                    player.getInventory().setItemInHand(stack);
                    player.sendMessage(form("&a&lRight click all the checkpoint blocks"));
                    player.playNote(player.getLocation(), Instrument.PIANO, Note.flat(1, Note.Tone.E));
                }
            }
            else if(event.getItem().getType().equals(Material.COMPASS)) {
                if(!igp.containsKey(player.getUniqueId()))return;
                player.openInventory(new MLGOptions().getInventory());
                player.playNote(player.getLocation(), Instrument.PIANO, Note.flat(1, Note.Tone.E));
            }
            else if (event.getItem().getType().equals(Material.STICK)){
                if (admin.isEmpty())return;
                if (!admin.containsKey(player.getUniqueId()))return;
                ParkourArena arena = (ParkourArena) admin.get(player.getUniqueId());
                if (event.getAction().equals(Action.LEFT_CLICK_AIR)||event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
                    player.sendMessage(ChatColor.GREEN+"Arena is now saved!");
                    player.sendMessage(ChatColor.GREEN+""+ChatColor.BOLD+"Please run /pa reload: to reload the plugin and add the new maps!");
                    new ArenaManager().save(arena);
                    admin.remove(player.getUniqueId());
                    player.teleport(Lobby);
                    player.getInventory().clear();
                    player.playNote(player.getLocation(), Instrument.PIANO, Note.flat(1, Note.Tone.E));
                    return;
                }
                if(event.getClickedBlock()==null)return;
                if (event.getClickedBlock().getLocation().getY()<60){
                    player.sendMessage(form("&c&lYou can't set a checkpoint below Y = 60"));
                    player.playSound(player.getLocation(), wither_hurt, 1, 1);
                    return;
                }
                List<Location> points = new ArrayList<>();
                if (arena.getCheckpoints()!=null)points.addAll(arena.getCheckpoints());
                points.add(event.getClickedBlock().getLocation());
                player.getWorld().getBlockAt(event.getClickedBlock().getLocation().add(0,1,0)).setType(Practice.plate.getType());
                arena.setCheckpoints(points);
                admin.put(player.getUniqueId(), arena);
                player.sendMessage(form("&a&lCheckpoint added!"));
                player.playNote(player.getLocation(), Instrument.PIANO, Note.flat(1, Note.Tone.E));
            }
        }
    }

    public String form(String msg){
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
