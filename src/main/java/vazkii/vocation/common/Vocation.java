package vazkii.vocation.common;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Vocation.MOD_ID, name = Vocation.MOD_NAME, version = Vocation.VERSION)
public class Vocation {

	public static final String MOD_ID = "vocation";
	public static final String MOD_NAME = "Vocation";
	public static final String BUILD = "GRADLE:BUILD";
	public static final String VERSION = "GRADLE:VERSION-" + BUILD;	
	
	public static final String PROXY_COMMON = "vazkii.vocation.common.CommonProxy";
	public static final String PROXY_CLIENT = "vazkii.vocation.client.ClientProxy";
	
	@Instance(MOD_ID)
	public static Vocation instance;

	@SidedProxy(serverSide = PROXY_COMMON, clientSide = PROXY_CLIENT)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		proxy.serverStarting(event);
	}
	
}

