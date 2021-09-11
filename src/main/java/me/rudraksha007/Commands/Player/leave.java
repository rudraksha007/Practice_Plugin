package me.rudraksha007.Commands.Player;

import me.rudraksha007.Java.MLGGameManager;
import me.rudraksha007.Objects.MLGArena;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.rudraksha007.Objects.HashMaps.igp;

public class leave implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)){sender.sendMessage("You can't do this from here!");return true;}
        Player player = (Player) sender;
        if (!igp.containsKey(player.getUniqueId())){player.sendMessage(form("&c&lYou are not in any game"));return true;}
        new MLGGameManager().endMLG((MLGArena) igp.get(player.getUniqueId()));
        return true;
    }

    public String form(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
