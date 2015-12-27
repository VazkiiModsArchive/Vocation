package vazkii.vocation.common.network;

import vazkii.vocation.common.Vocation;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class NetworkHandler {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Vocation.MOD_ID);

	public static void init() {
		INSTANCE.registerMessage(PacketSendMessage.class, PacketSendMessage.class, 0, Side.CLIENT);
	}
	
}
