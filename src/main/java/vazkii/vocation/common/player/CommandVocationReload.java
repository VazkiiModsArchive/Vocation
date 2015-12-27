package vazkii.vocation.common.player;

import vazkii.vocation.common.core.MessageLoader;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class CommandVocationReload extends CommandBase {
	
	public String getCommandName() {
		return "vocation-reload";
	}

	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "";
	}

	public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
		EntityPlayerMP entityplayermp = p_71515_2_.length == 0 ? getCommandSenderAsPlayer(p_71515_1_) : getPlayer(p_71515_1_, p_71515_2_[0]);
		if(entityplayermp != null) {
			MessageLoader.loadAll(MessageLoader.baseDir);
			func_152373_a(p_71515_1_, this, "Reloaded!");
		}
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 4;
	}
	
}
