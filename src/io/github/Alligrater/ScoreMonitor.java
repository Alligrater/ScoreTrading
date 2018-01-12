package io.github.Alligrater;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class ScoreMonitor implements Listener{
	static List<String> nogainworld = new ArrayList<String>();

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		if(ScoreTrading.playerscores.get(event.getPlayer().getName()) != null) {
			return;
		}
		else {
			ScoreTrading.playerscores.put(event.getPlayer().getName(), 0);
		}
		
		ScoreTrading.saveScore();
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void scoreGain(PlayerExpChangeEvent event) {
		if(nogainworld.contains(event.getPlayer().getWorld().getName())) {
			return;
		}
		else {
			Plugin plugin = Bukkit.getPluginManager().getPlugin("ScoreTrading");
			FileConfiguration config = plugin.getConfig();
			ScoreManagement.changeScore(event.getPlayer().getName(), (int) Math.abs(event.getAmount() *config.getDouble("ScoreMultiplier", 1.0)));
		}

	}
	
	@EventHandler
	public void enchantGain(EnchantItemEvent event) {
		if(nogainworld.contains(event.getEnchanter().getWorld().getName())) {
			return;
		}
		else {
			Plugin plugin = Bukkit.getPluginManager().getPlugin("ScoreTrading");
			FileConfiguration config = plugin.getConfig();
			ScoreManagement.changeScore(event.getEnchanter().getName(), (int) Math.abs(event.getExpLevelCost()*config.getDouble("EnchantMultiplier", 8.0)));
		}

	}
}
