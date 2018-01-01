package io.github.Alligrater;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WhatismyScore implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			sender.sendMessage(String.format("§7你的当前得分为§e%s§7!", ScoreManagement.getScore(player)));
		}
		return true;
	}
	
}
