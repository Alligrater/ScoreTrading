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
			if(arg3.length == 2) {
				if(arg3[0].equalsIgnoreCase("query")) {
					String player = arg3[1];
					
					sender.sendMessage(String.format("��7Score of Player ��e%s��7 is ��e%s��7!", player, ScoreManagement.getScore(player)));
				}
			}
			else if(arg3.length == 3) {
				if(arg3[0].equalsIgnoreCase("add")) {
					String player = arg3[2];
						
						int value = 0;
						
						try {
							value = Integer.parseInt(arg3[1]);
						} catch (Exception e) {
							value = 0;
						}
						if (ScoreManagement.changeScore(player, value)) {
							sender.sendMessage(String.format("��7Score of Player ��e%s��7 Changed to ��e%s��7!", player, ScoreManagement.getScore(player)));
						}
						else {
							sender.sendMessage("��7Failed to Changed Score!");
						}
				}
				else if(arg3[0].equalsIgnoreCase("set")) {
					String player = arg3[2];

					
					if(player != null) {
						int value = 0;
						
						try {
							value = Integer.parseInt(arg3[1]);
						} catch (Exception e) {
							value = 0;
						}
						if (ScoreManagement.setScore(player, value)) {
							sender.sendMessage(String.format("��7Score of Player ��e%s��7 Changed to ��e%s��7!", player, ScoreManagement.getScore(player)));
						}
						else {
							sender.sendMessage("��7Failed to Changed Score!");
						}

					}
				}
			}
			else {
				sender.sendMessage("��7Please specify an action.\nAvailable options: \n��e - query <playername> \n��e - add <playername> <score>\n��e - set <playername> <score>");
			}
		}
		else {
			sender.sendMessage("��cYou can't do this!");
		}
		return true;
	}
	
}
