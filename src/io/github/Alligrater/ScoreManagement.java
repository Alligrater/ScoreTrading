package io.github.Alligrater;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ScoreManagement {
	public static boolean changeScore(OfflinePlayer player, int value) {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("ScoreTrading");
		FileConfiguration config = plugin.getConfig();
		boolean allow = true;
		
		if(value == 0)
			return false;
		
		if(config.get("scores."+player.getName()) != null) {
			if((int)config.get("scores."+player.getName()) + value > 0) {
				allow = true;
				config.set("scores."+player.getName(), (int)config.get("scores."+player.getName()) + value);
				
			}
			else
				allow = false;
		}
		else {
			if(value > 0) {
				allow = true;
				config.set("scores."+player.getName(), value);
			}
			else {
				allow = false;
			}
		}
		if(allow) {
			plugin.saveConfig();
		}
		return allow;
	}
	
	public static boolean setScore(OfflinePlayer player, int value) {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("ScoreTrading");
		FileConfiguration config = plugin.getConfig();
		boolean allow = true;
		if(value < 0) {
			allow = false;
		}
		
		if(allow) {
			config.set("scores."+player.getName(), value);
		}
		
		return allow;
	}
	
	public static int getScore(OfflinePlayer player) {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("ScoreTrading");
		FileConfiguration config = plugin.getConfig();
		boolean allow = true;
		
		if(config.get("scores."+player.getName()) != null) {
			return (int) config.get("scores."+player.getName());
		}
		else {
			config.set("scores."+player.getName(), 0);
			return 0;
		}
	}
}
