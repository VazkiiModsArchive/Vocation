package vazkii.vocation.common.player;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import vazkii.vocation.common.core.Message;
import vazkii.vocation.common.core.MessageLoader;

import javax.annotation.Nonnull;

public class CommandVocationReview extends CommandBase {


	@Override
	@Nonnull
	public String getName() {
		return "vocation-review";
	}

	@Override
	@Nonnull
	public String getUsage(ICommandSender sender) {
		return "";
	}

	@Override
	public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException {
		EntityPlayerMP entityplayermp = args.length == 0 ? getCommandSenderAsPlayer(sender) : getPlayer(server, sender, args[0]);
		if(entityplayermp != null) {
			String last = PlayerDataStorage.getLastSeen(entityplayermp);
			Message m = MessageLoader.allMessages.get(last);
			if(m != null)
				m.sendToPlayer(entityplayermp, false);
		}
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}
	
}
