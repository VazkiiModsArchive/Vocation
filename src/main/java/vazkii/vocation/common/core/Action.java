package vazkii.vocation.common.core;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

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
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		server.getCommandManager().executeCommand(sender, param);
	}
	
	public void giveItem(EntityPlayer player) {
		if(stack != null) {
			ItemStack istack = stack.asStack();
			if(istack != null) {
				ItemStack giveStack = istack.copy();
		        player.captureDrops = true;
		        EntityItem item = player.dropItem(giveStack, false);
		        player.capturedDrops.clear();
		        player.captureDrops = false;
		        item.setPickupDelay(0);
		        player.worldObj.spawnEntityInWorld(item);
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
