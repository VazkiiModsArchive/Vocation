package vazkii.vocation.common.player;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class CommandVocationClear extends CommandBase {

	public String getCommandName() {
		return "vocation-clear";
	}

	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "<player>";
	}

	public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
		EntityPlayerMP entityplayermp = p_71515_2_.length == 0 ? getCommandSenderAsPlayer(p_71515_1_) : getPlayer(p_71515_1_, p_71515_2_[0]);
		if(entityplayermp != null) {
			PlayerDataStorage.clearAllSeen(entityplayermp);
			func_152373_a(p_71515_1_, this, "Cleared!");
		}
	}
	
	@Override
    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
        return p_82358_2_ == 0;
    }

}
