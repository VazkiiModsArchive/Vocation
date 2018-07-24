package vazkii.vocation.common.player;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import vazkii.vocation.common.core.MessageLoader;

import javax.annotation.Nonnull;

public class CommandVocationReload extends CommandBase {

	@Override
	@Nonnull
	public String getName() {
		return "vocation-reload";
	}

	@Override
	@Nonnull
	public String getUsage(ICommandSender iCommandSender) {
		return "";
	}

	@Override
	public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException {
		EntityPlayerMP entityplayermp = args.length == 0 ? getCommandSenderAsPlayer(sender) : getPlayer(server, sender, args[0]);
		if(entityplayermp != null) {
			MessageLoader.loadAll(MessageLoader.baseDir);
			notifyCommandListener(sender, this, "Reloaded!");
		}
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 4;
	}
	
}
