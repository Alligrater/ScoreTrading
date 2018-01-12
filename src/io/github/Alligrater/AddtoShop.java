package io.github.Alligrater;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class AddtoShop implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		if(sender instanceof Player && sender.hasPermission("ScoreTrading.addItems")) {
			
			Plugin plugin = Bukkit.getPluginManager().getPlugin("ScoreTrading");
			FileConfiguration config = plugin.getConfig();
			
			Player player = (Player) sender;
			
			//./shoptool set 3:9 1000
			if(arg3.length > 2) {
				
				//row 1 column 1
				String slotx = arg3[1].substring(0, arg3[1].indexOf(":"));
				String sloty = arg3[1].substring(arg3[1].indexOf(":") + 1);
				
				int slot = -1;
				
				try {
					slot = (Integer.parseInt(slotx) - 1) * 9 + Integer.parseInt(sloty) - 1;
				} catch (Exception e) {
					slot = -1;
				}
				
				int price = 0;
				
				try {
					price = Integer.parseInt(arg3[2]);
				} catch (Exception e) {
					price = 0;
				}
				

				
				if(player.getInventory().getItemInMainHand().getType() != Material.AIR) {
					if(slot != -1 && slot < 54) {
						if(arg3[0].equalsIgnoreCase("set")) {
							config.set("ScoreShop.Items."+ slot + ".item", player.getInventory().getItemInMainHand().serialize());
							config.set("ScoreShop.Items."+ slot + ".price", price);
							plugin.saveConfig();
							plugin.reloadConfig();
							updateshop();

							player.sendMessage("¡ì7Successfully set item in slot¡ìe " + slot + "(" + arg3[1] + ")");
						}
					}
					else {
						player.sendMessage("¡ì7Incorrect format. \nParams should be: ¡ìe/shoptool set <row>:<col> <price>");
					}
				}
				else {
					player.sendMessage("¡ì7You don't have any ¡ìeitem¡ì7 in your hand");
				}
				
			}
			
			//./shoptool remove 2
			else if(arg3.length == 2) {
				
				String slotx = arg3[1].substring(0, arg3[1].indexOf(":"));
				String sloty = arg3[1].substring(arg3[1].indexOf(":") + 1);
				
				int slot = -1;
				
				try {
					slot = (Integer.parseInt(slotx) - 1) * 9 + Integer.parseInt(sloty) - 1;
				} catch (Exception e) {
					slot = -1;
				}
				
				if(slot != -1) {
					config.set("ScoreShop.Items."+slot, null);
					plugin.saveConfig();
					plugin.reloadConfig();
					updateshop();
					player.sendMessage("¡ì7Successfully removed item in slot¡ìe " + slot + "(" + arg3[1] + ")");
				}

			}
			
			else if(arg3.length == 1) {
				if(arg3[0].equalsIgnoreCase("reload")) {
					plugin.reloadConfig();
					updateshop();
					ScoreMonitor.nogainworld =(List<String>) config.getList("Nogainworld", new ArrayList<String>());
					player.sendMessage("¡ì7Successfully updated config files!");
				}
			}
			

		}

		return true;
	}
	
	public static void updateshop() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("ScoreTrading");
		FileConfiguration config = plugin.getConfig();
		
		
		ScoreTrading.iteminshop.clear();
		
		for(int i = 0; i < 54; i++) {
				
				if(config.isConfigurationSection("ScoreShop.Items." + i + ".item")) {
					ItemStack iteminshop = ItemStack.deserialize(config.getConfigurationSection("ScoreShop.Items." + i + ".item").getValues(false));
					
					ItemMeta imeta = iteminshop.getItemMeta();
					
					if((int)config.get("ScoreShop.Items." + i + ".price") != 0) {
						List<String> lores = new ArrayList<String>();
						
						if(imeta.hasLore()) {
							lores = imeta.getLore();
						}
						
						lores.add("¡ìe" + config.get("ScoreShop.Items." + i + ".price"));
						imeta.setLore(lores);
					}
					

					

					
					iteminshop.setItemMeta(imeta);
					
					ScoreTrading.iteminshop.put(i, iteminshop);
				}

		}
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.getOpenInventory().getTitle().contains("¡ìrScoreShop - Balance: ¡ìe"))
				PaymentGUIOpen.newopenGUI(p);
		}
	}

}
