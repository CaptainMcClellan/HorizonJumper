package com.somethinghub.HorizonJumper.Commands;

import com.somethinghub.HorizonJumper.DataManagers.HorizonJumperConfigManager;
import com.somethinghub.HorizonJumper.DataManagers.HorizonJumperJumpingManager;
import com.somethinghub.HorizonJumper.HorizonJumperBase;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HorizonJumperHorizonJumperCommands extends HorizonJumperBase implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("HorizonJumper") || cmd.getName().equalsIgnoreCase("hj")) {
            sender.sendMessage(ChatImportant + "Reloading Config..");
            if(!HorizonJumperConfigManager.LoadConfig()) {
                sender.sendMessage(ChatError + "Failed to reload Config!");
                return true;
            }

            if(!HorizonJumperJumpingManager.LoadConfig()) {
                sender.sendMessage(ChatError + "Failed to reload Config!");
                return true;
            }
            
            sender.sendMessage(ChatDefault + "Reloaded Config!");
            return true;
        }
        return false;
    }
}
