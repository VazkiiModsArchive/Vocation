package vazkii.vocation.common.player;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import vazkii.vocation.common.core.Message;
import vazkii.vocation.common.core.MessageLoader;

import javax.annotation.Nonnull;

public class CommandVocationShow extends CommandBase {

	@Override
	@Nonnull
	public String getName() {
		return "vocation-show";
	}

	@Override
	@Nonnull
	public String getUsage(@Nonnull ICommandSender sender) {
		return "<player> <id> [do-actions]";
	}

	@Override
	public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException {
		EntityPlayerMP entityplayermp = getPlayer(server, sender, args[0]);
		if(entityplayermp != null) {
			String id = args[1];
			Message m = MessageLoader.allMessages.get(id);
			if(m != null) {
				boolean doActions = false;
				if(args.length > 2) {
					String var2 = args[2];
					if(var2.equalsIgnoreCase("do-actions"))
						doActions = true;
				}
				
				m.sendToPlayer(entityplayermp, doActions);
				notifyCommandListener(sender, this, "Shown message!");
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
