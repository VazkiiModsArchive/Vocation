package vazkii.vocation.client;

import java.io.File;
import java.net.URL;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundManager;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import paulscode.sound.SoundSystem;
import vazkii.vocation.common.core.MessageLoader;

// Sound loading code stolen from Lore Expansion because I'm too dumb
// to write my own, yay :D
//
// Source: https://github.com/dmillerw/LoreExpansion/blob/master/src/main/java/dmillerw/lore/client/sound/SoundHandler.java
public class SoundHandler {

	public static final SoundHandler INSTANCE = new SoundHandler();

	private static final String[] SOUND_MANAGER_MAPPING = new String[]{"sndManager", "field_147694_f"};
	private static final String[] SOUND_SYSTEM_MAPPING = new String[]{"sndSystem", "field_148620_e"};

	private static boolean gamePause = false;

	private Set<String> isLoaded = Sets.newHashSet();

	private SoundManager soundManager;

	private SoundSystem soundSystem;

	private String nowPlaying = "";

	private boolean paused = false;
	private boolean loaded = false;

	private void initialize() {
		try {
			soundManager = (SoundManager) ReflectionHelper.findField(net.minecraft.client.audio.SoundHandler.class, SOUND_MANAGER_MAPPING).get(Minecraft.getMinecraft().getSoundHandler());
			soundSystem = (SoundSystem) ReflectionHelper.findField(SoundManager.class, SOUND_SYSTEM_MAPPING).get(soundManager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private SoundSystem getSoundSystem() {
		if (!loaded) {
			initialize();
			loaded = true;
		}
		return soundSystem;
	}

	private File getFile(String name) {
		return new File(MessageLoader.baseDir, name);
	}

	public void play(String name) {
		if (!nowPlaying.isEmpty()) {
			stop();
		}

		try {
			File file = getFile(name);
			URL url = file.toURI().toURL();

			if (!isLoaded.contains(name)) {
				SoundSystem sys = getSoundSystem();
				sys.newStreamingSource(true, name, url, file.getName(), false, 0F, 0F, 0F, 1, 0F);
				sys.activate(name);
			}

			soundSystem.play(name);
			nowPlaying = name;
		} catch (Exception ex) {
			nowPlaying = "";
			ex.printStackTrace();
		}
	}

	public void stop() {
		if (nowPlaying.isEmpty()) {
			return;
		}

		getSoundSystem().stop(nowPlaying);
		//			getSoundSystem().removeSource(nowPlaying);
		//			isLoaded.remove(nowPlaying);
		nowPlaying = "";
		gamePause = false;
		paused = false;
	}

	public void pause() {
		if (nowPlaying.isEmpty()) {
			return;
		}

		getSoundSystem().pause(nowPlaying);
		paused = true;
	}

	public void resume() {
		if (nowPlaying.isEmpty()) {
			return;
		}

		if (paused) {
			getSoundSystem().play(nowPlaying);
			paused = false;
		}
	}

	public boolean isPaused() {
		return paused;
	}

	public boolean isPlaying(String name) {
		return nowPlaying.equals(name);
	}

}
