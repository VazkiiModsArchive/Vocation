package vazkii.vocation.common.player;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandVocationClear extends CommandBase {

	public String getCommandName() {
		return "vocation-clear";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "<player>";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException{
		EntityPlayerMP entityplayermp = p_71515_2_.length == 0 ? getCommandSenderAsPlayer(p_71515_1_) : getPlayer(server, p_71515_1_, p_71515_2_[0]);
		if(entityplayermp != null) {
			PlayerDataStorage.clearAllSeen(entityplayermp);
			notifyCommandListener(p_71515_1_, this, "Cleared!");
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
