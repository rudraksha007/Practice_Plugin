package me.rudraksha007.Commands.TabCompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class pa implements TabCompleter {

    List<String> argue = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length==0)return null;
        else if (args.length==1){
            if (args[0].isEmpty()){
                argue.add("addmap");argue.add("setlobby");
                return argue;
            }else {
                argue.add("addmap");argue.add("setlobby");
                argue.removeIf(a-> !a.contains(args[0]));
                return argue;
            }
        }
        return null;
    }
}
