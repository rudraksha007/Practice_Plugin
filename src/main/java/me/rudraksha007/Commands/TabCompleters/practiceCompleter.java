package me.rudraksha007.Commands.TabCompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class practiceCompleter implements TabCompleter {

    List<String> all = new ArrayList<>();
    List<String> arg = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length==0)return null;
        if (args[0]==null){
            all.add("join");all.add("leave");all.add("stats");
            return all;
        }else if (args.length==1){
            for (String s: all){
                if (args[0].startsWith(s))arg.add(s);
            }
            return arg;
        }
        return null;
    }
}
