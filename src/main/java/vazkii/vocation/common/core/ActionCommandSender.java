package vazkii.vocation.common.core;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class ActionCommandSender implements ICommandSender {

	final EntityPlayer player;
	
	public ActionCommandSender(EntityPlayer player) {
		this.player = player;
	}
	
	@Override
	public String getCommandSenderName() {
		return "Vocation-actions";
	}

	@Override
	public IChatComponent func_145748_c_() {
		return player.func_145748_c_();
	}

	@Override
	public void addChatMessage(IChatComponent p_145747_1_) {
		player.addChatComponentMessage(p_145747_1_);
	}

	@Override
	public boolean canCommandSenderUseCommand(int p_70003_1_, String p_70003_2_) {
		return p_70003_1_ <= 2;
	}

	@Override
	public ChunkCoordinates getPlayerCoordinates() {
		System.out.println("Getting coords!");
		return player.getPlayerCoordinates();
	}

	@Override
	public World getEntityWorld() {
		return player.getEntityWorld();
	}

}
