package me.rudraksha007.Objects.GUIs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MLGOptions extends GUIs implements InventoryHolder {
    @Override
    public Inventory getInventory() {

        Inventory inv = Bukkit.createInventory(new MLGOptions(), 54);
        ItemStack h1 = new ItemStack(Material.SUGAR_CANE);
        ItemMeta h1m = h1.getItemMeta();
        h1m.setDisplayName(ChatColor.GREEN+"Easiest drop (less points)");
        h1.setItemMeta(h1m);
        inv.setItem(9, h1);

        ItemStack h2 = new ItemStack(Material.SUGAR_CANE);
        ItemMeta h2m = h2.getItemMeta();
        h2m.setDisplayName(ChatColor.YELLOW+"Easy drop");
        h2.setItemMeta(h2m);
        h2.setAmount(2);
        inv.setItem(18, h2);

        ItemStack h3 = new ItemStack(Material.SUGAR_CANE);
        ItemMeta h3m = h3.getItemMeta();
        h3m.setDisplayName(ChatColor.RED+"Medium drop");
        h3.setItemMeta(h3m);
        h3.setAmount(3);
        inv.setItem(27, h3);

        ItemStack h4 = new ItemStack(Material.SUGAR_CANE);
        ItemMeta h4m = h4.getItemMeta();
        h4m.setDisplayName(ChatColor.RED+"High drop");
        h4.setItemMeta(h4m);
        h4.setAmount(4);
        inv.setItem(36, h4);

        ItemStack h5 = new ItemStack(Material.SUGAR_CANE);
        ItemMeta h5m = h5.getItemMeta();
        h5m.setDisplayName(ChatColor.RED+"Highest drop");
        h5.setItemMeta(h5m);
        h5.setAmount(5);
        inv.setItem(45, h5);

////////////////////////////////////////////////////////////////////////

        ItemStack t1 = new ItemStack(Material.WATER_BUCKET);
        ItemMeta t1m = t1.getItemMeta();
        t1m.setDisplayName(ChatColor.GOLD+"Water Bucket MLG");
        t1.setItemMeta(t1m);
        inv.setItem(4, t1);

        ItemStack t2 = new ItemStack(Material.LADDER);
        ItemMeta t2m = t2.getItemMeta();
        t2m.setDisplayName(ChatColor.GOLD+"Ladder MLG");
        t2.setItemMeta(t2m);
        inv.setItem(13, t2);

        ItemStack t3 = new ItemStack(Material.COBWEB);
        ItemMeta t3m = t3.getItemMeta();
        t3m.setDisplayName(ChatColor.GOLD+"Cobweb MLG");
        t3.setItemMeta(t3m);
        inv.setItem(22, t3);

        ItemStack t5 = new ItemStack(Material.OAK_BOAT);
        ItemMeta t5m = t5.getItemMeta();
        t5m.setDisplayName(ChatColor.GOLD+"Boat MLG");
        t5.setItemMeta(t5m);
        inv.setItem(40, t5);

////////////////////////////////////////////////////////////////////////

        ItemStack s1 = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta s1m = s1.getItemMeta();
        s1m.setDisplayName(ChatColor.GREEN+"5x5 Platform");
        s1.setItemMeta(s1m);
        inv.setItem(8, s1);

        ItemStack s2 = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta s2m = s2.getItemMeta();
        s2m.setDisplayName(ChatColor.YELLOW+"3x3 Platform");
        s2.setItemMeta(s2m);
        s2.setAmount(2);
        inv.setItem(17, s2);

        ItemStack s3 = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta s3m = s3.getItemMeta();
        s3m.setDisplayName(ChatColor.RED+"1x1 Platform");
        s3.setItemMeta(s3m);
        s3.setAmount(3);
        inv.setItem(26, s3);

////////////////////////////////////////////////////////////////////////


        return inv;
    }
}
