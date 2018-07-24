package vazkii.vocation.common.core;

import net.minecraft.command.CommandResultStats.Type;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ActionCommandSender implements ICommandSender {

	final EntityPlayer player;
	
	public ActionCommandSender(EntityPlayer player) {
		this.player = player;
	}

	@Override
	@Nonnull
	public World getEntityWorld() {
		return player.getEntityWorld();
	}

	@Override
	@Nonnull
	public String getName() {
		return "Vocation-actions";
	}

	@Override
	public boolean canUseCommand(int i, String s) {
		return i <= 2;
	}

	@Override
	@Nonnull
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
	@Nonnull
	public Vec3d getPositionVector() {
		return player.getPositionVector();
	}

	@Override
	public MinecraftServer getServer() {
		return player.getServer();
	}

}
