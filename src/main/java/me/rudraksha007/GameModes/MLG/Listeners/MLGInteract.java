package me.rudraksha007.GameModes.MLG.Listeners;

import me.rudraksha007.GameModes.MLG.MLGGameManager;
import me.rudraksha007.Objects.GUIs.MLGOptions;
import me.rudraksha007.Objects.MLGArena;
import org.bukkit.ChatColor;
import org.bukkit.Instrument;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static me.rudraksha007.Objects.HashMaps.igp;
import static me.rudraksha007.Practice.*;

public class MLGInteract {

    MLGGameManager manager = new MLGGameManager();

    public void onInvClick(InventoryClickEvent event, MLGArena arena) {
        Player player = (Player) event.getWhoClicked();
        event.setCancelled(true);
        if (event.getClickedInventory().getHolder() instanceof MLGOptions){
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

    public void onInteract(Player player){
        if(!igp.containsKey(player.getUniqueId()))return;
        player.openInventory(new MLGOptions().getInventory());
        player.playNote(player.getLocation(), Instrument.PIANO, Note.flat(1, Note.Tone.E));
    }
}
