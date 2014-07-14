package com.danielrharris.townywars;

import com.palmergames.bukkit.towny.event.NationAddTownEvent;
import com.palmergames.bukkit.towny.event.NationRemoveTownEvent;
import com.palmergames.bukkit.towny.event.TownAddResidentEvent;
import com.palmergames.bukkit.towny.event.TownRemoveResidentEvent;
import com.palmergames.bukkit.towny.exceptions.AlreadyRegisteredException;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class WarListener implements Listener
{
  private static Town townadd;
  WarListener(TownyWars aThis) {}
  
  @EventHandler
  public void onInteractEvent(PlayerInteractEvent event)
  {
	  Player player = event.getPlayer();
	  if(event.getPlayer().getWorld().toString() != "world"){
		  if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
			  if(event.getClickedBlock().getType() == Material.CHEST){
				  if(WarManager.getValidKey(player).getType() != Material.AIR){
				  }
			  }
		  }
	  }
  }
  
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event)
  {
  }
  
  @EventHandler
  public void onResidentLeave(TownRemoveResidentEvent event)
  {
  }
  
  @EventHandler
  public void onResidentAdd(TownAddResidentEvent event)
  {
  }
  
  @EventHandler
  public void onNationAdd(NationAddTownEvent event)
  {
  }
  
  @EventHandler
  public void onNationRemove(NationRemoveTownEvent event)
  {
  }
  
  @EventHandler
  public void onPlayerDeath(PlayerDeathEvent event)
  {
  }
}
