package com.somethinghub.HorizonJumper;

import com.somethinghub.HorizonJumper.Objects.HorizonJump;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HorizonJumperBase {
    
    public static String ChatDefault = ChatColor.GRAY.toString();
    public static String ChatImportant = ChatColor.BLUE.toString();
    public static String ChatError = ChatColor.RED.toString();
    
    public static boolean BlockVoid = true;
    public static boolean BlockFall = true;
    
    public static final List<Player> jumps = new ArrayList<Player>();
    
    public static HorizonJumperPlugin getPlugin() {
        return HorizonJumperPlugin.getHorizonJumperPlugin();
    }
    
    public static void Error(String reason, Exception cause) {
        Error(reason);
        
        if(cause == null) {
            return;
        }
        
        msgConsole("Show this error to the plugin Admin:");
        cause.printStackTrace();
    }
    
    public static void permBroadcast(String perm, String message) {
        msgConsole(message);
        for(Player p : Bukkit.getServer().getOnlinePlayers()) {
            if(!p.hasPermission(perm)) {
                continue;
            }
            
            msgPlayer(p, message);
        }
    }
    
    public static void broadcast(String message) {
        msgConsole(message);
        for(Player p : Bukkit.getServer().getOnlinePlayers()) {
            msgPlayer(p, message);
        }
    }
    
    public static void msgConsole(String message) {
        msgPlayer(Bukkit.getConsoleSender(), message);
    }
    
    public static void msgPlayer(CommandSender player, String message) {
        if(message.equalsIgnoreCase("")) return;
        player.sendMessage(ChatDefault + message);
    }
    
    public static void Error(String reason) {
        msgConsole(ChatError + reason);
    }
    
    public static String Color(String string) {
        
        String[] normvalues = { "&0", "&1", "&2", "&3", "&4", "&5", "&6", "&7", "&8", "&9", "&a", "&b", "&c", "&d", "&e", "&f", "&l", "&m", "&n", "&k", "&r", "&o" };
        String[] coloredvalues = { "\u00470", "\u00A71", "\u00A72", "\u00A73", "\u00A74", "\u00A75", "\u00A76", "\u00A77", "\u00A78", "\u00A79", "\u00A7a", "\u00A7b", "\u00A7c", "\u00A7d", "\u00A7e", "\u00A7f", "\u00A7l", "\u00A7m", "\u00A7n", "\u00A7k", "\u00A7r", "\u00A7o" };
        
        for(int i = 0; i < normvalues.length; i++) {
            string = string.replaceAll(normvalues[i], coloredvalues[i]);
        }
        
        return string;
    }
    
    public static void debug(String message) {
        broadcast("\u00A7bDEBUG: \u00A7d: " + message);
    }
    
    public static HorizonJump getJump(World world) {
        for(HorizonJump ff : HorizonJump.jumps) {
            if(ff.getFrom().equals(world)) {
                return ff;
            }
        }
        
        return null;
    }
}
