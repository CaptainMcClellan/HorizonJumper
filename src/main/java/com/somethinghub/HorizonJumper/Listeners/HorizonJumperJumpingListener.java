package com.somethinghub.HorizonJumper.Listeners;

import com.somethinghub.HorizonJumper.Objects.HorizonJump;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;

public class HorizonJumperJumpingListener extends HorizonJumperListenerBase {
    @EventHandler(ignoreCancelled=true)
    public void onDamage(EntityDamageEvent e) {
        if(!BlockVoid && !BlockFall) {
            return;
        }
        
        if(e.getEntity() == null || !(e.getEntity() instanceof Player)) {
            return;
        }
        
        Player p = (Player) e.getEntity();
        
        if(BlockVoid && e.getCause().equals(DamageCause.VOID)) {
            e.setDamage(0d);
            e.setCancelled(true);
            return;
        }
        
        if(BlockFall && e.getCause().equals(DamageCause.FALL)) {
            /* TODO: Finish */
            
            if(!jumps.contains(p)) {
                return;
            }
            
            jumps.remove(p);
            e.setDamage(0d);
            e.setCancelled(true);
            return;
        }
    }
    
    @EventHandler(ignoreCancelled=true)
    public void onjump(PlayerMoveEvent e) {
        HorizonJump jump = getJump(e.getPlayer().getWorld());
        /* If no jumps found */
        if(jump == null) {
            return;
        }
        
        double playerY = e.getTo().getY();
	if(jump.isJump() == false){
        	if(playerY < jump.getFromY()) {
            		return;
        	}
	}
	else {
		if(playerY > jump.getFromY()) {
			return;
		}
	}
        
        msgPlayer(e.getPlayer(), jump.getMessage());
        
        e.setTo(jump.getToAsLocation(e.getTo()));
        jumps.add(e.getPlayer());
    }
}
