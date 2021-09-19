package me.rudraksha007.Commands.Admin;

import me.rudraksha007.Objects.MLGArena;
import me.rudraksha007.Objects.ParkourArena;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static me.rudraksha007.Objects.HashMaps.admin;
import static me.rudraksha007.Practice.form;

public class addmap {
    public void execute(Player player, String[] args){

        switch (args[1].toLowerCase()){
            case "mlg":admin.put(player.getUniqueId(), new MLGArena(player, player.getLocation(), null, null,Material.WATER_BUCKET,0,0, 0, 0));
                break;
            case "parkour":
                if (args.length!=3){ player.sendMessage(form("&c&lInvalid command, Usage: /pa addmap PARKOUR <Map's Name>"));return; }
                admin.put(player.getUniqueId(), new ParkourArena(player, player.getLocation(), null, null, null, 0, args[2]));
                break;
            default:
                player.sendMessage(form("&c&lInvalid command: Available arguments are MLG and PARKOUR"));
        }
        ItemStack stack = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(form("&a&lEnd Point Selector (Practice Plugin)"));
        meta.addEnchant(Enchantment.DURABILITY, 3, true);
        stack.setItemMeta(meta);
        player.setItemInHand(stack);
        player.sendMessage(form("&a&lSelect normal end point of this map!"));

    }
}
