package vazkii.vocation.common.core;

import net.minecraft.command.CommandResultStats.Type;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ActionCommandSender implements ICommandSender {

	final EntityPlayer player;
	
	public ActionCommandSender(EntityPlayer player) {
		this.player = player;
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
	public World getEntityWorld() {
		return player.getEntityWorld();
	}

	@Override
	public String getName() {
		return "Vocation-actions";
	}

	@Override
	public IChatComponent getDisplayName() {
		return null;
	}

	@Override
	public BlockPos getPosition() {
		return player.getPosition();
	}

	@Override
	public Vec3 getPositionVector() {
		return player.getPositionVector();
	}

	@Override
	public Entity getCommandSenderEntity() {
		return null;
	}

	@Override
	public boolean sendCommandFeedback() {
		return false;
	}

	@Override
	public void setCommandStat(Type type, int amount) {
		// NO-OP
	}

}
