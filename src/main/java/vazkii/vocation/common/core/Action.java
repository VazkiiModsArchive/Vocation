package vazkii.vocation.common.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

public class Action {

	public String action;
	public String param;
	public StackWrapper stack;
	
	public void run(EntityPlayer player) {
		if(action.equals("run_command"))
			runCommand(player);
		else if(action.equals("give_item"))
			giveItem(player);
	}
	
	public void runCommand(EntityPlayer player) {
		ActionCommandSender sender = new ActionCommandSender(player);
		MinecraftServer server = MinecraftServer.getServer();
		server.getCommandManager().executeCommand(server, param);
	}
	
	public void giveItem(EntityPlayer player) {
		if(stack != null) {
			ItemStack istack = stack.asStack();
			if(istack != null) {
				ItemStack giveStack = istack.copy();
				if(!player.inventory.addItemStackToInventory(giveStack))
					player.dropPlayerItemWithRandomChoice(giveStack, false);
			}
		}
	}
	
	@Override
	public String toString() {
		return "Action["
				+ "action=" + action
				+ " param=" + param
				+ "]";
	}
	
}
