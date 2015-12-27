package vazkii.vocation.common;

import java.io.File;

import vazkii.vocation.common.core.MessageLoader;
import vazkii.vocation.common.network.NetworkHandler;
import vazkii.vocation.common.player.CommandVocationCheck;
import vazkii.vocation.common.player.CommandVocationClear;
import vazkii.vocation.common.player.CommandVocationReload;
import vazkii.vocation.common.player.CommandVocationReview;
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
		event.registerServerCommand(new CommandVocationReload());
		event.registerServerCommand(new CommandVocationCheck());
		event.registerServerCommand(new CommandVocationClear());
		event.registerServerCommand(new CommandVocationShow());
		event.registerServerCommand(new CommandVocationSetSeen());
		event.registerServerCommand(new CommandVocationReview());
	}
	
	public void showMessage(String id) {
		// NO-OP
	}
}