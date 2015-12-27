package vazkii.vocation.common.core;

import java.io.File;
import java.io.FileFilter;

public class MessageLoader {

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
		// TODO
	}
	
	public static void clear() {
		// TODO
	}
}
