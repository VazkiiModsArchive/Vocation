package vazkii.vocation.common.core;

import net.minecraft.command.CommandResultStats.Type;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ActionCommandSender implements ICommandSender {

	final EntityPlayer player;
	
	public ActionCommandSender(EntityPlayer player) {
		this.player = player;
	}

	@Override
	public void addChatMessage(ITextComponent p_145747_1_) {
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
	public ITextComponent getDisplayName() {
		return null;
	}

	@Override
	public BlockPos getPosition() {
		return player.getPosition();
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

	@Override
	public Vec3d getPositionVector() {
		return player.getPositionVector();
	}

	@Override
	public MinecraftServer getServer() {
		return player.getServer();
	}

}
