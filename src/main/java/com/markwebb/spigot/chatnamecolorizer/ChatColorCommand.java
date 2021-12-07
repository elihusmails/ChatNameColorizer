package com.markwebb.spigot.chatnamecolorizer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class ChatColorCommand implements CommandExecutor{

    private static final Logger logger = Logger.getLogger(ChatColorCommand.class.getSimpleName());
    private final ChatNameColorizer colorizer;

    public ChatColorCommand(ChatNameColorizer colorizer){
        this.colorizer = colorizer;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 1){
            logger.warning("Incorrect arguments");
            return false;
        }

        if(sender instanceof Player){
            Player player = (Player) sender;
            String colorArg = args[0];
            logger.info("Setting chat color " + colorArg + " for user " + player.getDisplayName());
            this.colorizer.addColor(player, colorArg);
        }

        return true;
    }

}