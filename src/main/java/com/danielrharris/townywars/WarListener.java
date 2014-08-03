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

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;

public class WarListener implements Listener
{

  private static Town townadd;
  WarListener() {}
  public static HashMap open;
  @EventHandler
  public void onInteractEvent(PlayerInteractEvent event)
  {
	  Player player = event.getPlayer();
	  if(event.getPlayer().getWorld().toString() != "world"){
		  if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
			  if(event.getClickedBlock().getType() == Material.CHEST){
				  if(WarManager.getValidKey(player).getType() != Material.AIR){
					  Chest chest = (Chest) event.getClickedBlock();
					  player.openInventory(chest.getInventory());
					  open.put(chest.getInventory(), event.getPlayer().getName());
					  Bukkit.broadcastMessage("NEW INVENTORY OPENED AND ADDED TO HASHTABLE WITH PLAYER " + open.get(chest.getInventory()));
				  }
			  }
		  }
	  }
  }
  @EventHandler
  public void inventoryDropEvent(PlayerDropItemEvent event){
	  if(event.getItemDrop().getItemStack().hasItemMeta()){
		  if(event.getItemDrop().getItemStack().getItemMeta().getDisplayName().contains("RAID KEY!")){
			  event.setCancelled(true);
		  }
	  }
  }
  
  @EventHandler
  public void inventoryMoveEvent(InventoryClickEvent event){
	  if(event.getInventory().getItem(event.getSlot()).hasItemMeta()){
		  if(event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("RAID KEY!")){
			  event.setCancelled(true);
		  }
	  }
  }
  @EventHandler
  public  void inventoryClose(InventoryCloseEvent event){
	  if(open.containsKey(event.getInventory())){
		  open.remove(event.getInventory());
		  Bukkit.broadcastMessage("INVENTORY REMOVED FROM HASHMAP");
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
}
