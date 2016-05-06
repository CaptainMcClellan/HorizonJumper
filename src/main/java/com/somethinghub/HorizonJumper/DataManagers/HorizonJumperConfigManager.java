package com.somethinghub.HorizonJumper.DataManagers;

import com.somethinghub.HorizonJumper.HorizonJumperBase;
import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;

public class HorizonJumperConfigManager extends HorizonJumperBase {
    
    public static YamlConfiguration config;
    
    public static boolean LoadConfig() {
        try {
            if(!getPlugin().getDataFolder().exists()) {
                getPlugin().getDataFolder().mkdir();
            }
            
            File configFile = new File(getPlugin().getDataFolder(), "config.yml");
            if(!configFile.exists()) {
                configFile.createNewFile();
            }
            
            config = YamlConfiguration.loadConfiguration(configFile);
            
            if(!config.contains("StopDeathVoid")) {
                config.set("StopDeathVoid", true);
            }
            if(!config.contains("StopDeathFall")) {
                config.set("StopDeathFall", true);
            }
            
            config.save(configFile);
            return true;
        } catch(Exception ex) {
            Error("Failed to load config", ex);
            return false;
        }
    }
}
