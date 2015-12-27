package vazkii.vocation.common.core;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import vazkii.vocation.common.network.NetworkHandler;
import vazkii.vocation.common.network.PacketSendMessage;

public class Message {

	public String id;
	public String message;
	public String narrator;
	public int time;
	public String triggerMode;
	public List<Trigger> triggers;
	public List<Action> actions;
	
	@Override
	public String toString() {
		return "Message["
				+ "id=" + id
				+ " message=" + message
				+ " narrator=" + narrator
				+ " time=" + time
				+ " triggerMode=" + triggerMode
				+ " triggers=" + triggers
				+ " actions=" + actions
				+ "]";
	}
	
	public void sendToPlayer(EntityPlayer player) {
		if(player instanceof EntityPlayerMP)
			NetworkHandler.INSTANCE.sendTo(new PacketSendMessage(id), (EntityPlayerMP) player);
	}
	
}
