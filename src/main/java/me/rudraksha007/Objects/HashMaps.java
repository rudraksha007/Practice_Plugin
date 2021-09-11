package me.rudraksha007.Objects;

import me.rudraksha007.Java.ArenaManager;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class HashMaps {

    public static Map<UUID, Arena>igp = new HashMap<>();
    public static Map<UUID, Arena>admin = new HashMap<>();
    public static Set<MLGArena> MLGArenas = new HashSet<>();
    public static Map<UUID, ItemStack[]> invs = new HashMap<UUID, org.bukkit.inventory.ItemStack[]>();
    public static Location Lobby = new ArenaManager().loadLocation("lobby");
}
