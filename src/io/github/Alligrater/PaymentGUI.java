package io.github.Alligrater;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class PaymentGUI implements Listener{
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if(event.getInventory().getName().contains("§rScoreShop - Balance: §a")) {
			event.setCancelled(true);
			if(player.getInventory().firstEmpty() == -1 || event.getCurrentItem().getType() == Material.AIR)
				return;
			else {
				ItemStack item = event.getCurrentItem();
				ItemMeta im = item.getItemMeta();

				int price = 0;
				if(im.hasLore()) {
					String lore = ChatColor.stripColor(im.getLore().get(im.getLore().size()-1));
					try {
						price = Integer.parseInt(lore);
					} catch (Exception e) {
						price = 0;
					}
				}
				else {
					return;
				}
				
				if(ScoreManagement.changeScore(player, -price)) {
					ItemStack outitem = item.clone();
					ItemMeta ometa = outitem.getItemMeta();
					List<String> lores = ometa.getLore();
					lores.remove(lores.size()-1);
					if(lores.isEmpty()) {
						ometa.setLore(null);
					}
					else {
						ometa.setLore(lores);
					}
					
					outitem.setItemMeta(ometa);
					
					player.sendMessage(String.format("§7以§e%s§7的价格成功购买一件物品。当前剩余得分§e%s§7。", price, ScoreManagement.getScore(player)));
					player.getInventory().addItem(outitem);
					PaymentGUIOpen.newopenGUI(player);
				}
				else {
					player.sendMessage(String.format("§7金钱不足！当前剩余得分§e%s§7。",  ScoreManagement.getScore(player)));
				}
				
				
			}
		}
		

				
	}
	

	
	
}
