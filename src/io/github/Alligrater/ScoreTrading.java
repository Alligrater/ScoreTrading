package io.github.Alligrater;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;



public class ScoreTrading extends JavaPlugin {
	
	public static HashMap<Integer, ItemStack> iteminshop = new HashMap<Integer, ItemStack>();
	public static HashMap<String, Integer> playerscores = new HashMap<String, Integer>();
	
	@Override
	public void onEnable() {
		
		FileConfiguration config = this.getConfig();
		
		List<String> nogain = new ArrayList<String>();
		nogain.add("DefaultNoGainWorld");
		
		List<String> nospend = new ArrayList<String>();
		nospend.add("DefaultNoSpendWorld");
		
		//if you don't want someone to gain score on certain worlds or spend on certain worlds.
		config.addDefault("NoGainworld", nogain);
		config.addDefault("NoSpendworld", nospend);
		config.addDefault("ScoreMultiplier", 1.0);
		config.addDefault("EnchantMultiplier", 8.0);
		config.addDefault("SaveInterval", 300);
		
		config.options().copyDefaults(true);
		
		loadScore();

		this.saveConfig();
		
		ScoreMonitor.nogainworld = readfromfile();
		
		
		AddtoShop.updateshop();
		
		this.getCommand("scoreshop").setExecutor(new PaymentGUIOpen());
		this.getCommand("sshop").setExecutor(new PaymentGUIOpen());
		this.getCommand("scoretrading").setExecutor(new ScoreModify());
		this.getCommand("strade").setExecutor(new ScoreModify());
		this.getCommand("myscore").setExecutor(new WhatismyScore());
		this.getCommand("score").setExecutor(new WhatismyScore());
		this.getCommand("shoptool").setExecutor(new AddtoShop());
		getServer().getPluginManager().registerEvents(new PaymentGUI(), this);
		getServer().getPluginManager().registerEvents(new ScoreMonitor(), this);
		
		//autosave
		BukkitRunnable save = new BukkitRunnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				saveScore();
			}
			
		};
		
		save.runTaskTimer(this, 10 , config.getInt("SaveInterval", 200) * 20);
	}
	
	@Override
	public void onDisable() {
		this.saveConfig();
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.getOpenInventory().getTitle().contains("¡ìrScoreShop - Balance: ¡ì2"))
				p.closeInventory();
		}
		saveScore();
	}
	
	public List<String> readfromfile() {
		FileConfiguration config = this.getConfig();
		List<String> ng = config.getStringList("NoGainworld");
		Bukkit.getLogger().info("No gain world is: " + ng);
		return ng;
	}
	
	public static void loadScore() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("ScoreTrading");
		FileConfiguration config = plugin.getConfig();
		
		Set<String> playerkeys = config.getConfigurationSection("scores").getKeys(false);
		for(String s : playerkeys) {
			playerscores.put(s, (Integer) config.get("scores." + s));
		}
	}
	
	public static void saveScore() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("ScoreTrading");
		FileConfiguration config = plugin.getConfig();
		for(String s : playerscores.keySet()) {
			config.set("scores." + s, playerscores.get(s));
		}
		Bukkit.getLogger().info("[ScoreTrading]Saving data to config...");
	}
	

	
}
