package vazkii.vocation.common;

import java.io.File;

import sun.nio.ch.Net;
import vazkii.vocation.common.core.MessageLoader;
import vazkii.vocation.common.network.NetworkHandler;
import vazkii.vocation.common.player.CommandVocationClear;
import vazkii.vocation.common.player.CommandVocationSetSeen;
import vazkii.vocation.common.player.CommandVocationShow;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		File configDir = event.getSuggestedConfigurationFile().getParentFile();
		File vocationDir = new File(configDir.getParentFile(), "vocation_data");
		if(!vocationDir.exists())
			vocationDir.mkdir();

		MessageLoader.loadAll(vocationDir);
	}
	
	public void init(FMLInitializationEvent event) {
		NetworkHandler.init();
	}
	
	public void serverStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandVocationClear());
		event.registerServerCommand(new CommandVocationShow());
		event.registerServerCommand(new CommandVocationSetSeen());
		// TODO Check and Review
	}
	
	public void showMessage(String id) {
		// NO-OP
	}
	
	public void initSounds(File dir) {
		// NO-OP
	}
}
