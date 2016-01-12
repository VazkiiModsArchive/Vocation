package vazkii.vocation.common.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import vazkii.vocation.common.Vocation;

public class NetworkHandler {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Vocation.MOD_ID);

	public static void init() {
		INSTANCE.registerMessage(PacketSendMessage.class, PacketSendMessage.class, 0, Side.CLIENT);
	}
	
}
