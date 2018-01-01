package io.github.Alligrater;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ScoreModify implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		if(sender.hasPermission("ScoreTrading.manage")) {
			if(arg3.length == 1) {
				if(arg3[0].equalsIgnoreCase("reload")) {
					Bukkit.getPluginManager().getPlugin("ScoreTrading").reloadConfig();
					sender.sendMessage("¡ì7Plugin Configuration Reloaded.");
				}
			}
			else if(arg3.length == 2) {
				if(arg3[0].equalsIgnoreCase("query")) {
					OfflinePlayer player = Bukkit.getPlayer(arg3[1]);
					
					if(player != null) {
						sender.sendMessage(String.format("¡ì7Score of Player %s is %s!", player.getName(), ScoreManagement.getScore(player)));
					}
				}
			}
			else if(arg3.length == 3) {
				if(arg3[0].equalsIgnoreCase("add")) {
					OfflinePlayer player = Bukkit.getPlayer(arg3[2]);
						
					
					if(player != null) {
						int value = 0;
						
						try {
							value = Integer.parseInt(arg3[1]);
						} catch (Exception e) {
							value = 0;
						}
						if (ScoreManagement.changeScore(player, value)) {
							sender.sendMessage(String.format("¡ì7Score of Player %s Changed to %s!", player.getName(), ScoreManagement.getScore(player)));
						}
						else {
							sender.sendMessage("¡ì7Failed to Changed Score!");
						}
					}
				}
				else if(arg3[0].equalsIgnoreCase("set")) {
					OfflinePlayer player = Bukkit.getPlayer(arg3[2]);

					
					if(player != null) {
						int value = 0;
						
						try {
							value = Integer.parseInt(arg3[1]);
						} catch (Exception e) {
							value = 0;
						}
						if (ScoreManagement.setScore(player, value)) {
							sender.sendMessage(String.format("¡ì7Score of Player %s Changed to %s!", player.getName(), ScoreManagement.getScore(player)));
						}
						else {
							sender.sendMessage("¡ì7Failed to Changed Score!");
						}

					}
				}
			}
			else {
				sender.sendMessage("¡ìcPlease specify an action.");
			}
		}
		else {
			sender.sendMessage("¡ìcYou can't do this!");
		}
		return true;
	}
	
}
