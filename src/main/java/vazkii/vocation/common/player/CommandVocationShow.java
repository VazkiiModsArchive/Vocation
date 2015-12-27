package vazkii.vocation.common.player;

import vazkii.vocation.common.core.Message;
import vazkii.vocation.common.core.MessageLoader;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class CommandVocationShow extends CommandBase {

	public String getCommandName() {
		return "vocation-show";
	}

	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "<player> <id> [set-seen]";
	}

	public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
		EntityPlayerMP entityplayermp = getPlayer(p_71515_1_, p_71515_2_[0]);
		if(entityplayermp != null) {
			String id = p_71515_2_[1];
			Message m = MessageLoader.allMessages.get(id);
			System.out.println(m + " " + id);
			if(m != null) {
				m.sendToPlayer(entityplayermp);
				if(p_71515_2_.length > 2) {
					String var2 = p_71515_2_[2];
					if(var2.equalsIgnoreCase("set-seen"))
						PlayerDataStorage.setSeen(entityplayermp, id, true);
				}
				func_152373_a(p_71515_1_, this, "Shown message!");
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
