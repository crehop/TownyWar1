package com.danielrharris.townywars;
import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyUniverse;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TownyWars
  extends JavaPlugin
{
  public static TownyUniverse tUniverse;
  public static double pPlayer;
  public static double pPlot;
  public static double pKill;
  public static double declareCost;
  public static double endCost;
  
  public void onDisable()
  {
    try
    {
    }
    catch (Exception ex)
    {
      Logger.getLogger(TownyWars.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public void onEnable()
  {
    try
    {
    }
    catch (Exception ex)
    {
      Logger.getLogger(TownyWars.class.getName()).log(Level.SEVERE, null, ex);
    }
    PluginManager pm = getServer().getPluginManager();
    pm.registerEvents(new WarListener(), this);
    tUniverse = ((Towny)Bukkit.getPluginManager().getPlugin("Towny")).getTownyUniverse();
    
    getConfig().addDefault("pper-player", Double.valueOf(2.0D));
    getConfig().addDefault("pper-plot", Double.valueOf(0.5D));
    getConfig().addDefault("declare-cost", Double.valueOf(10.0D));
    getConfig().addDefault("end-cost", Double.valueOf(0.0D));
    getConfig().addDefault("death-cost", Double.valueOf(0.0D));
    getConfig().options().copyDefaults(true);
    saveConfig();
    
    pPlayer = getConfig().getDouble("pper-player");
    pPlot = getConfig().getDouble("pper-plot");
    declareCost = getConfig().getDouble("declare-cost");
    endCost = getConfig().getDouble("end-cost");
    pKill = getConfig().getDouble("death-cost");
	this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
	{
	    @Override  
	    public void run()
	    {
	    	Iterator<RaidKey> keyChain = WarManager.keys.iterator();
		    while(keyChain.hasNext())
	    	{
		    	RaidKey key = keyChain.next();
		    	key.tick();
			}
		}
	}, 0L, 10L);
  }

  public boolean onCommand(CommandSender sender,Command cmd,String commandLabel, String[] args){
	  Player player = (Player) sender;
	  if(commandLabel.equals("twar")){
		  Bukkit.broadcastMessage("CONFIRM COMMAND");
		  try {
			WarManager.makeKey(player);
		} catch (TownyException e) {
			Bukkit.broadcastMessage("FAILED");
		}
	  }
	  return false;
  }
}
