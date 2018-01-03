package io.github.Alligrater;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class ScoreMonitor implements Listener{
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("ScoreTrading");
		FileConfiguration config = plugin.getConfig();
		if(config.get("scores."+event.getPlayer().getName()) != null) {
			return;
		}
		else {
			config.set("scores."+event.getPlayer().getName(), 0);
			plugin.saveConfig();

		}
	}
	
	@EventHandler
	public void scoreGain(PlayerExpChangeEvent event) {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("ScoreTrading");
		FileConfiguration config = plugin.getConfig();
		ScoreManagement.changeScore(event.getPlayer(), Math.abs(event.getAmount()));
	}
	
	@EventHandler
	public void enchantGain(EnchantItemEvent event) {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("ScoreTrading");
		FileConfiguration config = plugin.getConfig();
		ScoreManagement.changeScore(event.getEnchanter(), Math.abs(event.getExpLevelCost()*8));
	}
}
