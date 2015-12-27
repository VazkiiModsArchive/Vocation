package vazkii.vocation.common.core;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MessageLoader {

	public static Map<String, Message> allMessages;
	
	public static Gson gson = new Gson();
	
	public static void loadAll(File baseDir) {
		clear();
		
		File[] dirsInside = baseDir.listFiles(new FileFilter() {
			public boolean accept(File arg0) {
				return arg0.isDirectory();
			}
		});
		
		for(File f : dirsInside)
			load(f);
	}
	
	public static void load(File baseDir) {
		for(File f : baseDir.listFiles()) {
			if(f.isDirectory())
				load(f);
			
			String name = f.getName();
			if(name.endsWith(".json"))
				loadJson(f);
		}
	}
	
	public static void loadJson(File f) {
		try {
			List<Message> list = gson.<List<Message>>fromJson(new FileReader(f), new TypeToken<List<Message>>(){}.getType());
			for(Message m : list) {
				System.out.println("loaded " + m.id + " to " + m);
				allMessages.put(m.id, m);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void clear() {
		allMessages = new HashMap();
	}
}
