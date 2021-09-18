package me.rudraksha007.Commands.TabCompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class practiceCompleter implements TabCompleter {

    List<String> all = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length==0)return null;
        if (args.length==1){
            if (args[0].isEmpty()){
                all.add("join");all.add("leave");return all;
            }
            else {
                all.add("join");all.add("leave");
                all.removeIf(a -> !a.contains(args[0]));
                return all;
            }
        }
        if (args.length==2){
            if (args[1].isEmpty()){all.add("MLG");all.add("PARKOUR");return all;}
            else {
                all.add("MLG");all.add("PARKOUR");
                all.removeIf(a ->!a.contains(args[1]));
                return all;
            }
        }
        return null;
    }
}
