package com.markwebb.spigot.chatnamecolorizer;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class ChatNameColorizer extends JavaPlugin implements Listener {

    FileConfiguration config;
    static final Map<String,String> COLOR_CODES;
    static {
        Map<String,String> map = new HashMap<>();
        map.put("magenta", "#FF00FF");
        map.put("pink", "#FFAFAF");
        map.put("green", "#00FF00");
        map.put("black", "#000000");
        map.put("yellow", "#FFFF00");
        map.put("cyan", "#00FFFF");
        map.put("red", "#FF0000");
        map.put("orange", "#FFC800");
        map.put("gray", "#808080");
        map.put("white", "#FFFFFF");
        map.put("blue", "#0000FF");
        map.put("darkgray", "#404040");
        map.put("lightgray", "#C0C0C0");
        map.put("copper", "#B87333");
        map.put("brass", "#B5A642");
        map.put("bronze", "#8C7853");
        map.put("gold", "#CD7F32");
        map.put("silver", "#CD7F32");
        map.put("steelblue", "#236B8E");

        COLOR_CODES = Collections.unmodifiableMap(map);
    }

    public ChatNameColorizer(){

    }

    @Override
    public void onEnable() {
        getLogger().info("Loading user colors");
        this.config = getConfig();
        getLogger().info("Setting commands...");
        this.getCommand("chatcolor").setExecutor(new ChatColorCommand(this));
        this.getCommand("chatcolors").setExecutor(new ChatColorsCommand(this));
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Saving user chat color preferences");
        saveConfig();
        getLogger().info("See you again, SpigotMC!");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

    }

    public String getHexaColor(String colorName) {
        return COLOR_CODES.getOrDefault(colorName.toLowerCase(), "#FFFFFF");
    }

    public void addColor(Player player, String color){
        getLogger().info("Setting color " + color + " for user " + player.getDisplayName());
        String hexColor = COLOR_CODES.get(color.toLowerCase());
        if(hexColor == null ){
            getLogger().warning("Could not find the appropriate color");
            return;
        }

        // set the color for the user
        config.set(player.getDisplayName(), color);
        saveConfig();
    }

    @EventHandler
    public void chatFormat(AsyncPlayerChatEvent event){
        Player p = event.getPlayer();
        String color = config.getString(p.getDisplayName());
        getLogger().info("Color for user: " + p.getDisplayName() + " is " + color);
        if(color != null ) {
            event.setFormat(net.md_5.bungee.api.ChatColor.of(COLOR_CODES.get(color)) + "<" + p.getDisplayName() + ">" + ChatColor.WHITE + event.getMessage());
        } else {
            event.setFormat("<" + p.getDisplayName() + ">" + ChatColor.WHITE + event.getMessage());
        }
    }

    public Set<String> getSupportedColorCodes() {
        return COLOR_CODES.keySet();
    }
}
