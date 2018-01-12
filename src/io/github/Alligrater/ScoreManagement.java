package io.github.Alligrater;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ScoreManagement {
	public static boolean changeScore(String player, int value) {

		boolean allow = false;
		
		if(value == 0)
			return false;
		
		//port to hashmap (uses memory instead)
		
		if(ScoreTrading.playerscores.get(player) != null) {
			if(ScoreTrading.playerscores.get(player) + value > 0) {
				allow = true;
				ScoreTrading.playerscores.put(player, ScoreTrading.playerscores.get(player) + value);
			}
			else {
				allow = false;
			}
		}
		else {
			if(value > 0) {
				allow = true;
				ScoreTrading.playerscores.put(player, value);
			}
			else {
				allow = false;
			}
		}
		
		return allow;
	}
	
	public static boolean setScore(String player, int value) {

		boolean allow = true;
		if(value < 0) {
			allow = false;
		}
		
		if(allow) {
			ScoreTrading.playerscores.put(player, value);
		}
		
		return allow;
	}
	
	public static int getScore(String player) {
		
		return ScoreTrading.playerscores.getOrDefault(player, -1);
	}
}
