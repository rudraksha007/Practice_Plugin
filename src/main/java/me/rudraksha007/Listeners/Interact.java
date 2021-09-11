package me.rudraksha007.Listeners;

import me.rudraksha007.Java.ArenaManager;
import me.rudraksha007.Java.MLGGameManager;
import me.rudraksha007.Objects.Arena;
import me.rudraksha007.Objects.GUIs.GUIs;
import me.rudraksha007.Objects.GUIs.MLGOptions;
import me.rudraksha007.Objects.MLGArena;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Level;

import static me.rudraksha007.Objects.HashMaps.admin;
import static me.rudraksha007.Objects.HashMaps.igp;

public class Interact implements Listener {

    MLGGameManager manager = new MLGGameManager();

    @EventHandler
    public void onInvClick(InventoryClickEvent event){
        if (event.getClickedInventory()==null)return;
        if (event.getCurrentItem()==null)return;
        if (!(event.getClickedInventory().getHolder() instanceof GUIs))return;
        if (!(event.getWhoClicked() instanceof Player))return;
        Player player = (Player) event.getWhoClicked();
        if (!igp.containsKey(player.getUniqueId()))return;
        event.setCancelled(true);
        if (event.getClickedInventory().getHolder() instanceof MLGOptions){
            MLGArena arena = (MLGArena) igp.get(player.getUniqueId());
            ItemStack stack = event.getCurrentItem();
            if(stack.getType().equals(Material.SUGAR_CANE)) {
                player.sendMessage(ChatColor.GREEN+"Changes were made successfully");
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
            else if(stack.getType().equals(Material.WATER_BUCKET)) {arena.setMLGType(Material.WATER_BUCKET);}
            else if(stack.getType().equals(Material.LADDER)) {arena.setMLGType(Material.LADDER);}
            else if(stack.getType().equals(Material.OAK_BOAT)) {arena.setMLGType(Material.OAK_BOAT);}
            else if(stack.getType().equals(Material.COBWEB)) {arena.setMLGType(Material.COBWEB);}
            manager.giveMLGItems(arena);
            igp.put(player.getUniqueId(), arena);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if(event.getItem()==null)return;
        if(!event.getItem().hasItemMeta())return;
        if(!event.getItem().getItemMeta().hasEnchant(Enchantment.DURABILITY))return;


        if(event.getItem().getType().equals(Material.BLAZE_ROD)) {

            if(event.getClickedBlock()==null)return;
            if(admin.isEmpty())return;
            if(!admin.containsKey(event.getPlayer().getUniqueId()))return;

            Player player = event.getPlayer();
            if(event.getClickedBlock().getLocation().getBlockY()<50) {player.sendMessage(ChatColor.RED+"End point must be higher than Y=50");return;}
            event.setCancelled(true);
            Arena obj = admin.get(player.getUniqueId());
            if(obj instanceof MLGArena) {
                MLGArena arena =(MLGArena) obj;
                if(event.getClickedBlock().getLocation().getBlockY()+50>arena.getSpawn().getBlockY()) {
                    player.sendMessage(ChatColor.RED+"End point must be at least 50 blocks lower than spawn location");
                    return;
                }
                arena.setEnd(event.getClickedBlock().getLocation());
                player.sendMessage(ChatColor.GREEN+"Normal Arena End Location Has Been Set!");
                new ArenaManager().save(arena);
                admin.remove(player.getUniqueId());
            }
            player.getInventory().clear();
        }


        else if(event.getItem().getType().equals(Material.COMPASS)) {
            Player player = event.getPlayer();
            if(!igp.containsKey(player.getUniqueId()))return;
            player.openInventory(new MLGOptions().getInventory());
        }
    }
}
