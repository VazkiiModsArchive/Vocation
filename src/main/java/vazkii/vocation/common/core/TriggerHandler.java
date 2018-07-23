package vazkii.vocation.common.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

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
	
	@SubscribeEvent
	public void onItemPickup(ItemPickupEvent event) {
		for(Message message : MessageLoader.allMessages.values())
			message.onItemPickup(event.player, event.getOriginalEntity().getItem());
	}
	
	@SubscribeEvent
	public void onItemCraft(ItemCraftedEvent event) {
		for(Message message : MessageLoader.allMessages.values())
			message.onItemCraft(event.player, event.crafting);
	}
	
	@SubscribeEvent
	public void onEntityKilled(LivingDeathEvent event) {
		if(event.getSource() != null && event.getSource().getImmediateSource() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getSource().getImmediateSource();
			for(Message message : MessageLoader.allMessages.values())
				message.onEntityKilled(player, event.getEntityLiving());	
		}
	}
}
