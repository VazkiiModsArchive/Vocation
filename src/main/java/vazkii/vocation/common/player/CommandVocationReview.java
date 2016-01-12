package vazkii.vocation.common.player;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import vazkii.vocation.common.core.Message;
import vazkii.vocation.common.core.MessageLoader;

public class CommandVocationReview extends CommandBase {

	public String getCommandName() {
		return "vocation-review";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "";
	}

	@Override
	public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
		EntityPlayerMP entityplayermp = p_71515_2_.length == 0 ? getCommandSenderAsPlayer(p_71515_1_) : getPlayer(p_71515_1_, p_71515_2_[0]);
		if(entityplayermp != null) {
			String last = PlayerDataStorage.getLastSeen(entityplayermp);
			Message m = MessageLoader.allMessages.get(last);
			if(m != null)
				m.sendToPlayer(entityplayermp, false);
		}
	}
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
		return true;
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}
	
}
