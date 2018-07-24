package vazkii.vocation.common.core;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.minecraftforge.fml.common.FMLLog;

import javax.annotation.Nonnull;

public class MessageLoader {

	public static Map<String, Message> allMessages;
	
	public static File baseDir;
	public static Gson gson = new Gson();
	
	public static void loadAll(File baseDir) {
		MessageLoader.baseDir = baseDir;
		clear();
		
		for(File f : baseDir.listFiles( file -> file.isDirectory() ))
			loadDir(f);
	}
	
	public static void loadDir(@Nonnull File baseDir) {
		for(File f : baseDir.listFiles()) {
			if(f.isDirectory())
				loadDir(f);
			
			String namespace = baseDir.getName();
			String name = f.getName();
			if(name.endsWith(".json"))
				loadJson(namespace, f);
		}
	}
	
	public static void loadJson(String namespace, File f) {
		try {
			List<Message> list = gson.fromJson(new FileReader(f), new TypeToken<List<Message>>(){}.getType());
			for(Message m : list) {
				FMLLog.log.info( "[Vocation] Loaded message " + m + " on file " + f);
				m.namespace = namespace;
				allMessages.put(m.id, m);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void clear() {
		allMessages = new HashMap<>();
	}
}
