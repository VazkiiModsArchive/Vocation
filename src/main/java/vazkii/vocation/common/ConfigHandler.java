package vazkii.vocation.common;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigHandler {

	private static Configuration config;
	
	public static int boxWidth = 300;
	public static int paddingX = 20;
	public static int paddingY = 20;
	
	public static void init(File f) {
		config = new Configuration(f);
		
		config.load();
		
		boxWidth = loadPropInt("boxWidth", "The width of the message box.", boxWidth);
		paddingX = loadPropInt("paddingX", "The amount of padding on the left of the message.", paddingX);
		paddingY = loadPropInt("paddingY", "The amount of padding on the top of the message.", paddingY);

		if(config.hasChanged())
			config.save();
	}
	
	public static int loadPropInt(String propName, String desc, int default_) {
		Property prop = config.get(Configuration.CATEGORY_GENERAL, propName, default_);
		prop.comment = desc;

		return prop.getInt(default_);
	}
	
}

