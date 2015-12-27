package vazkii.vocation.common;

import java.io.File;
import java.io.FileFilter;

import vazkii.vocation.common.core.MessageLoader;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		File configDir = event.getSuggestedConfigurationFile().getParentFile();
		File vocationDir = new File(configDir.getParentFile(), "vocation_data");
		if(!vocationDir.exists())
			vocationDir.mkdir();

		MessageLoader.loadAll(vocationDir);
	}
	
}
