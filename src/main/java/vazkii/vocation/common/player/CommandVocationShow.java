package vazkii.vocation.common.player;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import vazkii.vocation.common.core.Message;
import vazkii.vocation.common.core.MessageLoader;

public class CommandVocationShow extends CommandBase {

	public String getCommandName() {
		return "vocation-show";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "<player> <id> [do-actions]";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
		EntityPlayerMP entityplayermp = getPlayer(server, p_71515_1_, p_71515_2_[0]);
		if(entityplayermp != null) {
			String id = p_71515_2_[1];
			Message m = MessageLoader.allMessages.get(id);
			if(m != null) {
				boolean doActions = false;
				if(p_71515_2_.length > 2) {
					String var2 = p_71515_2_[2];
					if(var2.equalsIgnoreCase("do-actions"))
						doActions = true;
				}
				
				m.sendToPlayer(entityplayermp, doActions);
				notifyCommandListener(p_71515_1_, this, "Shown message!");
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
