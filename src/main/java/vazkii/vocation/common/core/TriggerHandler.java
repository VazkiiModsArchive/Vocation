package vazkii.vocation.common.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
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
	
	@SubscribeEvent
	public void onItemPickup(ItemPickupEvent event) {
		for(Message message : MessageLoader.allMessages.values())
			message.onItemPickup(event.player, event.pickedUp.getEntityItem());
	}
	
	@SubscribeEvent
	public void onItemCraft(ItemCraftedEvent event) {
		for(Message message : MessageLoader.allMessages.values())
			message.onItemCraft(event.player, event.crafting);
	}
	
	@SubscribeEvent
	public void onEntityKilled(LivingDeathEvent event) {
		if(event.source != null && event.source.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.source.getEntity();
			for(Message message : MessageLoader.allMessages.values())
				message.onEntityKilled(player, event.entityLiving);	
		}
	}
}
