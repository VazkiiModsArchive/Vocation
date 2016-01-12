package vazkii.vocation.common.core;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.stats.StatisticsFile;

public class Trigger {

	public String trigger;
	public String key;
	public int value;
	public StackWrapper stack = null;
	
	public void trigger(Message message, EntityPlayer player) {
		if(message.canTriggersWork(player))
			message.sendToPlayer(player, true);
	}
	
	public void onMessageSeen(Message message, EntityPlayer player, String entry) {
		if(trigger.equals("message_seen") && key.equals(entry))
			trigger(message, player);
	}
	
	public void onTick(Message message, EntityPlayer player) {
		if(trigger.equals("stat_above") && player instanceof EntityPlayerMP) {
			StatBase stat = StatList.getOneShotStat(key);
			if(stat != null) {	
				StatisticsFile file = ((EntityPlayerMP) player).getStatFile();
				if(file.readStat(stat) >= value)
					trigger(message, player);
			}
		}
	}
	
	public void onItemPickup(Message message, EntityPlayer player, ItemStack stack) {
		if(trigger.equals("item_pickup") && this.stack != null && this.stack.isEquivalentItem(stack))
			trigger(message, player);
	}
	
	public void onItemCraft(Message message, EntityPlayer player, ItemStack stack) {
		if(trigger.equals("item_craft") && this.stack != null && this.stack.isEquivalentItem(stack))
			trigger(message, player);
	}
	
	public void onEntityKilled(Message message, EntityPlayer player, Entity killed) {
		if(trigger.equals("mob_kill")) {
			String name = EntityList.getEntityString(killed);
			if(key.equals(name))
				trigger(message, player);
		}
	}
	
	@Override
	public String toString() {
		return "Trigger["
				+ "trigger=" + trigger
				+ " key=" + key
				+ " value=" + value
				+ " stack=" + stack
				+ "]";
	}
}
