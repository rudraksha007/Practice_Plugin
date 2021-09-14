package me.rudraksha007.Objects.GUIs;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class stats extends GUIs implements InventoryHolder {

    public Player player;
    @Override
    public Inventory getInventory() {
        Inventory inv = Bukkit.createInventory(new stats(), 9, form("&a&lYour Stats"));

        ItemStack bucket = new ItemStack(Material.WATER_BUCKET);
        ItemMeta bm = bucket.getItemMeta();
        bm.setDisplayName(PlaceholderAPI.setPlaceholders(player, form("&a&lTotal score: &c&l%practice_score%")));
        bucket.setItemMeta(bm);
        inv.setItem(0, bucket);

        ItemStack win = new ItemStack(Material.BEACON);
        ItemMeta wm = win.getItemMeta();
        float wins = Integer.parseInt(PlaceholderAPI.setPlaceholders(player, "%practice_wins%"));
        wm.setDisplayName(PlaceholderAPI.setPlaceholders(player, form("&a&lTotal MLGs Landed: &c&l"+wins)));
        win.setItemMeta(wm);
        inv.setItem(1, win);

        ItemStack lose = new ItemStack(Material.REDSTONE);
        ItemMeta lm = lose.getItemMeta();
        float loses = Integer.parseInt(PlaceholderAPI.setPlaceholders(player, "%practice_fails%"));
        lm.setDisplayName(form("&a&lTotal MLGs Failed: &c&l"+loses));
        lose.setItemMeta(lm);
        inv.setItem(2, lose);

        ItemStack rate = new ItemStack(Material.BOWL);
        ItemMeta rm = rate.getItemMeta();

        if(loses==0) {
            rm.setDisplayName(ChatColor.BLUE+"MLG Land Rate: "+ChatColor.RED+"100%");
        }else {
            BigDecimal decimal = new BigDecimal(wins/loses);
            BigDecimal rat = decimal.round(new MathContext(2, RoundingMode.HALF_UP));
            rm.setDisplayName(ChatColor.BLUE+"MLG Land Rate:"+ChatColor.RED+ rat);
        }
        rate.setItemMeta(rm);
        inv.setItem(3, rate);

        return inv;
    }

    public Inventory create(Player player){
        this.player = player;
        return getInventory();
    }

    public String form(String msg){
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
