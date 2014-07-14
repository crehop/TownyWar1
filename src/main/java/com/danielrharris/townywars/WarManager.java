package com.danielrharris.townywars;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyUniverse;


public class WarManager{
	public static List<RaidKey> keys;
	public static RaidKey getKey(ItemStack stack){
		RaidKey hold = null;
		int validate = Integer.parseInt(stack.getItemMeta().getLore().get(4));
		for(RaidKey check:keys){
			if(check.validate(validate) == true){
				hold = check;
			}
		}
		return hold;
	}
	public static boolean confirmInNation(Player player){
		try {
			if(TownyUniverse.getPlayer((Resident) player) != null){
				Resident resident = (Resident)player;
				Town town = resident.getTown();
				Nation nation = town.getNation();
				if(nation != null && town != null && resident != null){
					return true;
				}
				
			}
		} catch (NotRegisteredException e) {
			e.printStackTrace();
		} catch (TownyException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static ItemStack getValidKey(Player player){
		if(confirmInNation(player)){
			if(player.getInventory().contains(Material.GOLD_NUGGET)){
				ItemStack item = new ItemStack(Material.AIR);
				for(ItemStack check:player.getInventory()){
					if(item.getType() == Material.GOLD_NUGGET){
						item = check;
					}
				}
				List<String> lore = ItemUtils.getLore(item);
				if(Integer.parseInt(lore.get(1)) > 0 && Integer.parseInt(lore.get(3)) > 0){
					return item;
				}
				else{
					player.sendMessage(ChatColor.RED + "KEY FOR " + lore.get(0) + " DESTROYED, NO LONGER VALID");
					item.setType(Material.AIR);
					return item;
				}
			}
		}
		return new ItemStack(Material.AIR);
	}
	public static void giveKey(Player player){
		
	}
}