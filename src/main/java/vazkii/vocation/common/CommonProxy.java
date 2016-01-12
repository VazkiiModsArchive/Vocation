package vazkii.vocation.common;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import vazkii.vocation.common.core.MessageLoader;
import vazkii.vocation.common.core.TriggerHandler;
import vazkii.vocation.common.network.NetworkHandler;
import vazkii.vocation.common.player.CommandVocationCheck;
import vazkii.vocation.common.player.CommandVocationClear;
import vazkii.vocation.common.player.CommandVocationReload;
import vazkii.vocation.common.player.CommandVocationReview;
import vazkii.vocation.common.player.CommandVocationSetSeen;
import vazkii.vocation.common.player.CommandVocationShow;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		File configFile = event.getSuggestedConfigurationFile();
		File configDir = configFile.getParentFile();
		File vocationDir = new File(configDir.getParentFile(), "vocation_data");
		if(!vocationDir.exists())
			vocationDir.mkdir();

		ConfigHandler.init(configFile);
		MessageLoader.loadAll(vocationDir);
	}
	
	public void init(FMLInitializationEvent event) {
		NetworkHandler.init();
		
		MinecraftForge.EVENT_BUS.register(TriggerHandler.INSTANCE);
		FMLCommonHandler.instance().bus().register(TriggerHandler.INSTANCE);
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