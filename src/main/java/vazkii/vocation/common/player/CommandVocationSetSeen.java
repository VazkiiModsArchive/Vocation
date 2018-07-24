package vazkii.vocation.common.player;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import vazkii.vocation.common.core.Message;
import vazkii.vocation.common.core.MessageLoader;

import javax.annotation.Nonnull;

public class CommandVocationSetSeen extends CommandBase {

	@Override
	@Nonnull
	public String getName() {
		return "vocation-set-seen";
	}

	@Override
	@Nonnull
	public String getUsage(@Nonnull ICommandSender sender) {
		return "<player> <id> <true/false>";
	}

	@Override
	public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException {
		EntityPlayerMP entityplayermp = args.length == 2 ? getCommandSenderAsPlayer(sender) : getPlayer(server, sender, args[0]);
		if(entityplayermp != null) {
			String id = args[1];
			boolean seen = Boolean.parseBoolean(args[2]);
			Message m = MessageLoader.allMessages.get(id);
			if(m != null) {
				boolean wasSeen = PlayerDataStorage.isSeen(entityplayermp, id);
				PlayerDataStorage.setSeen(entityplayermp, id, seen);
				notifyCommandListener(sender, this, "Set seen to " + seen + " from " + wasSeen);
			}
		}
	}
	
	@Override
    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
        return p_82358_2_ == 0;
    }
	
	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}
	
}
