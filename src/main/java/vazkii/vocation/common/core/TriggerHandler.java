package vazkii.vocation.common.core;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public final class TriggerHandler {

	public static final TriggerHandler INSTANCE = new TriggerHandler();
	
	public void onMessageSeen(EntityPlayer player, String entry) {
		for(Message message : MessageLoader.allMessages.values())
			message.onMessageSeen(player, entry);
	}
	
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event) {
		if(event.phase == Phase.END) {
			for(Message message : MessageLoader.allMessages.values())
				message.onTick(event.player);
		}
	}
	
}
