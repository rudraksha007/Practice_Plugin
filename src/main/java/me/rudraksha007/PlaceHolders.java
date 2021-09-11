package me.rudraksha007;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class PlaceHolders extends PlaceholderExpansion {

    FileConfiguration config = Practice.plugin.getConfig();

    @Override
    public @NotNull String getIdentifier() {
        return "practice";
    }

    @Override
    public @NotNull String getAuthor() {
        return "rudraksha007";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.5.5";
    }
    @Override
    public String onRequest(OfflinePlayer player, String string){
        switch (string){
            case "practice_wins":
                return config.getString("player-data."+player.getUniqueId()+".wins");
            case "practice_fails":
                return config.getString("player-data."+player.getUniqueId()+".fails");
            case "practice_score":
                return config.getString("player-data."+player.getUniqueId()+".total-score");
            case "practice_games":
                return config.getString("player-data."+player.getUniqueId()+".total-games");
        }
        return null;
    }
}
