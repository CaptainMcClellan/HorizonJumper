package com.somethinghub.HorizonJumper.DataManagers;

import com.somethinghub.HorizonJumper.HorizonJumperBase;
import com.somethinghub.HorizonJumper.Objects.HorizonJump;
import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

public class HorizonJumperJumpingManager extends HorizonJumperBase {
    
    public static YamlConfiguration yml;
    
    public static boolean LoadConfig() {
        HorizonJump.jumps.clear();
        
        try {
            boolean newConfig = false;
            if(!getPlugin().getDataFolder().exists()) {
                getPlugin().getDataFolder().mkdir();
            }
            
            File configFile = new File(getPlugin().getDataFolder(), "jumps.yml");
            if(!configFile.exists()) {
                newConfig = true;
                configFile.createNewFile();
            }
            
            yml = YamlConfiguration.loadConfiguration(configFile);
            
            if(newConfig) {
                yml.set("TestJump.message", "AHHH!");
                yml.set("TestJump.fromWorld", Bukkit.getWorlds().get(0).getName());
                yml.set("TestJump.toWorld", Bukkit.getWorlds().get(1).getName());
                yml.set("TestJump.fromY", -10);
                yml.set("TestJump.toY", 400);
		yml.set("TestJump.jump",true);
                
                yml.set("JumpTwo.message", "Please stay at spawn");
                yml.set("JumpTwo.fromWorld", Bukkit.getWorlds().get(1).getName());
                yml.set("JumpTwo.toWorld", Bukkit.getWorlds().get(1).getName());
                yml.set("JumpTwo.fromY", -10);
                yml.set("JumpTwo.toY", 70);
                yml.set("JumpTwo.forceX", 0);
                yml.set("JumpTwo.forceZ", 0);
		yml.set("JumpTwo.jump",true);
                
                yml.save(configFile);
            }
            
            for(String Jump : yml.getKeys(false)) {
                String message = yml.getString(Jump + ".message", "");
                String worldName = yml.getString(Jump + ".fromWorld");
                String toWorldName = yml.getString(Jump + ".toWorld");
                double fromY = yml.getDouble(Jump + ".fromY", -10);
                double toY = yml.getDouble(Jump + ".toY", 400);
		if(yml.contains(Jump + ".jump")){
                boolean jump = yml.getBoolean(Jump + ".jump");}
		else { boolean jump = false ; }
                World fWorld = Bukkit.getWorld(worldName);
                if(fWorld == null) {
                    msgConsole(ChatError + "Failed to load world " + worldName);
                    continue;
                }
                
                World tWorld = Bukkit.getWorld(toWorldName);
                if(tWorld == null) {
                    msgConsole(ChatError + "Failed to load world " + toWorldName);
                    continue;
                }
                
                HorizonJump jmp = new HorizonJump(fWorld, tWorld, fromY, toY, Color(message));
                
                if(yml.contains(Jump + ".forceX")) {
                    jmp.shouldForceX = true;
                    jmp.forceX = yml.getDouble(Jump + ".forceX");
                }
                
                if(yml.contains(Jump + ".forceZ")) {
                    jmp.shouldForceZ = true;
                    jmp.forceZ = yml.getDouble(Jump + ".forceZ");
                }
                HorizonJump.jumps.add(jmp);
            }
            
            return true;
        } catch(Exception ex) {
            Error("Failed to load config", ex);
            return false;
        }
    }
}
