package io.github.Alligrater;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class PaymentGUIOpen implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			newopenGUI(player);
		}
		else {
			
		}
		return true;
	}
	
	
	public static void newopenGUI(Player player) {
		Inventory shop = Bukkit.createInventory(null, 54, "¡ìrScoreShop - Balance: ¡ìa" + ScoreManagement.getScore(player));
		for(int i : ScoreTrading.iteminshop.keySet()) {
			shop.setItem(i, ScoreTrading.iteminshop.get(i));
		}
		
		player.openInventory(shop);
		
	}
	
	
}
