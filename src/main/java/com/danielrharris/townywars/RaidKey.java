package com.danielrharris.townywars;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Chicken;
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
	private int flash = 0;
	private int teleportCooldown;
	private ItemStack stack;
	private Player keyHolder;
	
	private void setValidation(int validation2) {
		this.validation = validation2;
	}
	private void setTeleportCoolDown(int teleportCooldown2) {
		this.teleportCooldown = teleportCooldown2;
	}
	private void setItemsLeft(int itemsLeft2) {
		this.itemsLeft = itemsLeft2;
	}
	public RaidKey(ItemStack stack, Nation nation,Player player){
		Random rand = new Random();
		this.setKeyItemStack(stack);
		this.nation = nation.toString();
		this.timeLeft = 1800;
		this.blocksLeft = 100;
		this.itemsLeft = 15;
		this.teleportCooldown = 150;
		this.setKeyHolder(player);
		List<String> lore = new ArrayList<String>();
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(ChatColor.RED + "RAID KEY!");
		lore.add(ChatColor.YELLOW + "Nation Name: " + ChatColor.GREEN + "" + this.nation);
		lore.add(ChatColor.YELLOW + "Time Left (Seconds): " + ChatColor.GREEN + "" + this.timeLeft);
		lore.add(ChatColor.YELLOW + "Blocks Destroyable: " + ChatColor.GREEN + "" + this.blocksLeft);
		lore.add(ChatColor.YELLOW + "Items Lootable: " + ChatColor.GREEN + "" + this.itemsLeft + "");
		this.validation = rand.nextInt(100000000);
		lore.add(ChatColor.BLACK + "" + validation + "");
		meta.setLore(lore);
		stack.setItemMeta(meta);
		WarManager.keys.add(this);
	}
	private void setKeyItemStack(ItemStack stack) {
		this.stack = stack;
	}

	public void tick(){
		String color;
		if(this.flash == 0){
			this.flash = 1;
			color = ChatColor.WHITE + "";
		}
		else{
			this.flash = 0;
			color = ChatColor.RED + "";
		}		
		this.timeLeft--;
		ItemMeta meta = stack.getItemMeta();
		List<String> lore = new ArrayList<String>();
		meta.setDisplayName(ChatColor.RED + "RAID KEY!");
		lore.add(ChatColor.YELLOW + "Nation Name: " + ChatColor.GREEN + "" + this.nation);
		lore.add(ChatColor.YELLOW + "Time Left (Seconds): " + color + "" + this.timeLeft);
		lore.add(ChatColor.YELLOW + "Blocks Destroyable: " + ChatColor.GREEN + "" + this.blocksLeft);
		lore.add(ChatColor.YELLOW + "Items Lootable: " + ChatColor.GREEN + "" + this.itemsLeft + "");
		lore.add(ChatColor.BLACK + "" + validation + "");
		meta.setLore(lore);
		int found = 0;
		if(this.getKeyHolder().getItemOnCursor() != null){
			if(this.getKeyHolder().getItemOnCursor().hasItemMeta()){
				if(this.getKeyHolder().getItemOnCursor().getItemMeta().hasLore()){
					if(this.getKeyHolder().getItemOnCursor().getItemMeta().getLore().get(4).contains(this.validation + "")){
						Bukkit.broadcastMessage("VALIDATED");
						found = 1;
					}
				}
			}
		}
		if(found != 1){
			for(ItemStack stack:this.getKeyHolder().getInventory()){
				if(stack != null){
					if(stack.hasItemMeta()){
						if(stack.getItemMeta().hasLore()){
							if(stack.getItemMeta().getLore().get(4).contains(this.validation + "")){
								Bukkit.broadcastMessage("VALIDATED");
								stack.setItemMeta(meta);
								found = 1;
							}
						}
					}
				}
			}
			if(found != 1){
				this.voidKey();
			}
		}
		
		if(timeLeft <= 0){
			WarManager.keys.remove(this);
			stack.setType(Material.AIR);
		}
	}
	private void voidKey() {
		this.setTimeLeft(0);
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
	public void setBlocksLeft(int i) {
		this.blocksLeft = i;
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
	public Player getKeyHolder() {
		return keyHolder;
	}
	public void setKeyHolder(Player keyHolder) {
		this.keyHolder = keyHolder;
	}
	public RaidKey clone(RaidKey another) {
		another = this;
		return another;
	}
}
