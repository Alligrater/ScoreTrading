package io.github.Alligrater;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;



public class ScoreTrading extends JavaPlugin {
	
	public static HashMap<Integer, ItemStack> iteminshop = new HashMap<Integer, ItemStack>();
	
	
	@Override
	public void onEnable() {
		

		
		FileConfiguration config = this.getConfig();
		this.reloadConfig();
		
		AddtoShop.updateshop();
		
		this.getCommand("scoreshop").setExecutor(new PaymentGUIOpen());
		this.getCommand("scoretrading").setExecutor(new ScoreModify());
		this.getCommand("myscore").setExecutor(new WhatismyScore());
		this.getCommand("shoptool").setExecutor(new AddtoShop());
		getServer().getPluginManager().registerEvents(new PaymentGUI(), this);
		getServer().getPluginManager().registerEvents(new ScoreMonitor(), this);
	}
	
	@Override
	public void onDisable() {
		this.saveConfig();
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.closeInventory();
		}
	}
	
	

	
}
