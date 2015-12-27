package vazkii.vocation.common.core;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class MessageLoader {

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
			System.out.println("loading " + f);
			List<Message> list = gson.fromJson(new FileReader(f), List.class);
			
			for(Message m : list)
				System.out.println(m.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void clear() {
		// TODO
	}
}
