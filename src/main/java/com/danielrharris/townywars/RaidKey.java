package com.danielrharris.townywars;

import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.palmergames.bukkit.towny.object.Nation;

public class RaidKey {
	private int timeLeft;
	private int blocksLeft;
	private int itemsLeft;
	private String nation;
	private int validation;
	Location enemyTown;
	private int teleportCooldown;
	private ItemStack stack;
	public RaidKey(ItemStack stack,Nation nation){
		Random rand = new Random();
		this.nation = nation.toString();
		this.timeLeft = 1800;
		this.blocksLeft = 100;
		this.itemsLeft = 15;
		this.teleportCooldown = 150;
		List<String> lore = stack.getItemMeta().getLore();
		ItemMeta meta = stack.getItemMeta();
		lore.set(0, nation.toString());
		lore.set(1, this.timeLeft + "");
		lore.set(2, this.blocksLeft + "");
		lore.set(3, this.itemsLeft + "");
		this.validation = rand.nextInt(100000000);
		lore.set(4, validation + "");
		WarManager.keys.add(this);
	}
	public void tick(){
		this.timeLeft--;
		if(timeLeft <= 0){
			WarManager.keys.remove(this);
		}
	}
	public boolean validate(int check){
		if(check == validation){
			return true;
		}
		return false;
	}
	public int getTimeLeft() {
		return timeLeft;
	}
	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}
	public int getBlocksLeft() {
		return blocksLeft;
	}
	public void minusBlock() {
		List<String> lore = stack.getItemMeta().getLore();
		ItemMeta meta = stack.getItemMeta();
		if(Integer.parseInt(lore.get(2)) <= 0){
			lore.set(2, "0");
		}
		else{
			this.blocksLeft--;
			lore.set(2, this.blocksLeft + "");
		}
		meta.setLore(lore);
		stack.setItemMeta(meta);
	}
	public int getItemsLeft() {
		return itemsLeft;
	}
	public void minusItemsLeft() {
		List<String> lore = stack.getItemMeta().getLore();
		ItemMeta meta = stack.getItemMeta();
		if(Integer.parseInt(lore.get(3)) <= 0){
			lore.set(3, "0");
		}
		else{
			this.blocksLeft--;
			lore.set(3, this.blocksLeft + "");
		}
		meta.setLore(lore);
		stack.setItemMeta(meta);
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
}
