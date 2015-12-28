package vazkii.vocation.common.core;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import vazkii.vocation.common.network.NetworkHandler;
import vazkii.vocation.common.network.PacketSendMessage;
import vazkii.vocation.common.player.PlayerDataStorage;

public class Message {

	public String id;
	public String message;
	public String narrator;
	public int time;
	public String voiceover;
	public String triggerMode;
	public List<Trigger> triggers;
	public List<Action> actions;
	
	public transient String namespace;
	
	public void sendToPlayer(EntityPlayer player) {
		if(player instanceof EntityPlayerMP) {
			NetworkHandler.INSTANCE.sendTo(new PacketSendMessage(id), (EntityPlayerMP) player);
			PlayerDataStorage.setLastSeen(player, id);
			if(!PlayerDataStorage.hasShownMessage(player)) {
				PlayerDataStorage.setShownMessage(player);
				player.addChatComponentMessage(new ChatComponentText("Use /vocation-review to see the last message you got!").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.AQUA)));
			}
		}
	}
	
	public String getAudio() {
		return namespace + "/audio/" + voiceover;
	}
	
	public void runActions(EntityPlayer player) {
		for(Action action : actions)
			action.run(player);
	}
	
	@Override
	public String toString() {
		return "Message["
				+ "id=" + id
				+ " message=" + message
				+ " narrator=" + narrator
				+ " time=" + time
				+ " voiceover=" + voiceover
				+ " triggerMode=" + triggerMode
				+ " triggers=" + triggers
				+ " actions=" + actions
				+ "]";
	}
	
}
