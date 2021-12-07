package com.markwebb.spigot.chatnamecolorizer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.logging.Logger;

public class ChatColorsCommand implements CommandExecutor {

    private static final Logger logger = Logger.getLogger(ChatColorsCommand.class.getSimpleName());
    private final ChatNameColorizer colorizer;

    public ChatColorsCommand(ChatNameColorizer colorizer){
        this.colorizer = colorizer;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        logger.info("Sending supported colors to the user");
        sender.sendMessage("Supported colors are:\n" + String.join(", ", colorizer.getSupportedColorCodes()));
        return true;
    }
}
