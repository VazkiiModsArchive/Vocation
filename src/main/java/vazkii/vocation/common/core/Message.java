package vazkii.vocation.common.core;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import vazkii.vocation.common.network.NetworkHandler;
import vazkii.vocation.common.network.PacketSendMessage;
import vazkii.vocation.common.player.PlayerDataStorage;

public class Message {

	public String id;
	public String message;
	public String narrator;
	public int time;
	public String voiceover;
	public List<String> requirements;
	public List<Trigger> triggers;
	public List<Action> actions;
	
	public transient String namespace;
	
	public void sendToPlayer(EntityPlayer player, boolean doActions) {
		if(player instanceof EntityPlayerMP) {
			NetworkHandler.INSTANCE.sendTo(new PacketSendMessage(id), (EntityPlayerMP) player);
			PlayerDataStorage.setLastSeen(player, id);
			
			if(!PlayerDataStorage.hasShownMessage(player)) {
				PlayerDataStorage.setShownMessage(player);
				player.addChatComponentMessage(new TextComponentString("Use /vocation-review to see the last message you got!").setStyle(new Style().setColor(TextFormatting.AQUA)));
			}
			
			if(doActions && !PlayerDataStorage.isSeen(player, id)) {
				PlayerDataStorage.setSeen(player, id, true);
				runActions(player);
				TriggerHandler.INSTANCE.onMessageSeen(player, id);
			}
		}
	}
	
	public String getAudio() {
		if(voiceover.isEmpty())
			return null;
		
		return namespace + "/audio/" + voiceover;
	}
	
	public boolean canTriggersWork(EntityPlayer player) {
		if(PlayerDataStorage.isSeen(player, id))
			return false;
		
		if(requirements != null)
			for(String s : requirements)
				if(!PlayerDataStorage.isSeen(player, s))
					return false;
		
		return true;
	}
	
	public void runActions(EntityPlayer player) {
		if(actions != null)
			for(Action action : actions)
				action.run(player);
	}
	
	public void onMessageSeen(EntityPlayer player, String entry) {
		if(triggers != null)
			for(Trigger trigger : triggers)
				trigger.onMessageSeen(this, player, entry);
	}
	
	public void onTick(EntityPlayer player) {
		if(triggers != null)
			for(Trigger trigger : triggers)
				trigger.onTick(this, player);
	}
	
	public void onItemPickup(EntityPlayer player, ItemStack stack) {
		if(triggers != null)
			for(Trigger trigger : triggers)
				trigger.onItemPickup(this, player, stack);
	}
	
	public void onItemCraft(EntityPlayer player, ItemStack stack) {
		if(triggers != null)
			for(Trigger trigger : triggers)
				trigger.onItemCraft(this, player, stack);
	}
	
	public void onEntityKilled(EntityPlayer player, Entity killed) {
		if(triggers != null)
			for(Trigger trigger : triggers)
				trigger.onEntityKilled(this, player, killed);
	}

	@Override
	public String toString() {
		return "Message["
				+ "id=" + id
				+ " message=" + message
				+ " narrator=" + narrator
				+ " time=" + time
				+ " voiceover=" + voiceover
				+ " requirements=" + requirements
				+ " triggers=" + triggers
				+ " actions=" + actions
				+ "]";
	}
	
}
