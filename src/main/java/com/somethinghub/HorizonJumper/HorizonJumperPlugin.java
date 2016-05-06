package com.somethinghub.HorizonJumper;

import com.somethinghub.HorizonJumper.Commands.HorizonJumperHorizonJumperCommands;
import com.somethinghub.HorizonJumper.DataManagers.HorizonJumperConfigManager;
import com.somethinghub.HorizonJumper.DataManagers.HorizonJumperJumpingManager;
import com.somethinghub.HorizonJumper.DataManagers.HorizonJumperPluginManager;
import com.somethinghub.HorizonJumper.Listeners.HorizonJumperJumpingListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class HorizonJumperPlugin extends JavaPlugin {
    
    public static boolean isEnabled = false;
    public static PluginManager pluginManager;
    
    //Define Commands
    public static HorizonJumperHorizonJumperCommands HorizonJumperCommands;
    
    //Define Listeners
    public static HorizonJumperJumpingListener JumpingListener;
    
    @Override
    public void onEnable() {
        pluginManager = Bukkit.getPluginManager();
        
        if(!HorizonJumperPluginManager.LoadPlugin()) {
            Disable();
            return;
        }
        
        if(!HorizonJumperConfigManager.LoadConfig()) {
            Disable();
            return;
        }
        
        if(!HorizonJumperJumpingManager.LoadConfig()) {
            Disable();
            return;
        }
        
        //Load Commands
        HorizonJumperCommands = new HorizonJumperHorizonJumperCommands();
        
        //Load Listeners
        JumpingListener = new HorizonJumperJumpingListener();
        
        //Register Commands
        getCommand("HorizonJumper").setExecutor(HorizonJumperCommands);
        
        for(String c : HorizonJumperPluginManager.getCommands()) {
            getCommand(c).setPermission(HorizonJumperPluginManager.PluginYML.getString(HorizonJumperBase.ChatError + "permission"));
        }
        
        //Register Listeners
        pluginManager.registerEvents(JumpingListener, this);
        
        
        HorizonJumperBase.permBroadcast(
            "HorizonJumper.*",
            "\u00A7dLoaded " + HorizonJumperPluginManager.getName() + 
            " version " + HorizonJumperPluginManager.getVersion() + 
            "!"
        );
        isEnabled = true;
    }
    
    @Override
    public void onDisable() {
        if(!isEnabled) {
            getLogger().info("Failed to load plugin! Check Console for errors.");
            return;
        }
        
        /*** Stop Threads ***/
    }
    
    public void Disable() {
        Bukkit.getPluginManager().disablePlugin(this);
    }

    public static HorizonJumperPlugin getHorizonJumperPlugin() {
        try {
            Plugin plugin = Bukkit.getPluginManager().getPlugin("HorizonJumper");
            if(plugin == null || !(plugin instanceof HorizonJumperPlugin)) {
                return null;
            }
            
            return (HorizonJumperPlugin) plugin;
        } catch(NoClassDefFoundError e) {
            return null;
        }
    }
    
}
