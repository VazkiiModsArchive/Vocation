package vazkii.vocation.common.core;

import vazkii.vocation.common.player.PlayerDataStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
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
			StatBase stat = StatList.func_151177_a(key);
			if(stat != null) {
				StatisticsFile file = ((EntityPlayerMP) player).func_147099_x();
				if(file.writeStat(stat) >= value)
					trigger(message, player);
			}
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
