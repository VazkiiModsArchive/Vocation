package vazkii.vocation.common.player;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

import javax.annotation.Nonnull;

public class CommandVocationClear extends CommandBase {

	@Override
	@Nonnull
	public String getName() {
		return "vocation-clear";
	}

	@Override
	@Nonnull
	public String getUsage(@Nonnull ICommandSender sender) {
		return "<player>";
	}

	@Override
	public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException{
		EntityPlayerMP entityplayermp = args.length == 0 ? getCommandSenderAsPlayer(sender) : getPlayer(server, sender, args[0]);
		if(entityplayermp != null) {
			PlayerDataStorage.clearAllSeen(entityplayermp);
			notifyCommandListener(sender, this, "Cleared!");
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
