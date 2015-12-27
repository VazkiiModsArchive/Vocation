package vazkii.vocation.client;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import vazkii.vocation.common.CommonProxy;
import vazkii.vocation.common.core.Message;
import vazkii.vocation.common.core.MessageLoader;

public class ClientProxy extends CommonProxy {

	@Override
	public void showMessage(String id) {
		Message message = MessageLoader.allMessages.get(id);
		if(message != null) {
			Minecraft mc = Minecraft.getMinecraft();
			mc.thePlayer.addChatMessage(new ChatComponentText("[DEBUG] " + message.narrator + ": " + message.message));
		}
	}
	
}
