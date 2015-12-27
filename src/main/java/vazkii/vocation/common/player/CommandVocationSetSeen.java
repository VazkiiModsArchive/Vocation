package vazkii.vocation.common.player;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import vazkii.vocation.common.core.Message;
import vazkii.vocation.common.core.MessageLoader;

public class CommandVocationSetSeen extends CommandBase {

	public String getCommandName() {
		return "vocation-set-seen";
	}

	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "<player> <id> <true/false>";
	}

	public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
		EntityPlayerMP entityplayermp = p_71515_2_.length == 2 ? getCommandSenderAsPlayer(p_71515_1_) : getPlayer(p_71515_1_, p_71515_2_[0]);
		if(entityplayermp != null) {
			String id = p_71515_2_[1];
			boolean seen = Boolean.parseBoolean(p_71515_2_[2]);
			Message m = MessageLoader.allMessages.get(id);
			if(m != null) {
				boolean wasSeen = PlayerDataStorage.isSeen(entityplayermp, id);
				PlayerDataStorage.setSeen(entityplayermp, id, seen);
				func_152373_a(p_71515_1_, this, "Set seen to " + seen + " from " + wasSeen);
			}
		}
	}
	
	@Override
    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
        return p_82358_2_ == 0;
    }
	
}
