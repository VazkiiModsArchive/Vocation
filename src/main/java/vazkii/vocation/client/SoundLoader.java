package vazkii.vocation.client;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound.AttenuationType;
import net.minecraft.client.audio.PositionedSound;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundList;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.FallbackResourceManager;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.util.ResourceLocation;
import vazkii.vocation.common.Vocation;
import vazkii.vocation.common.core.Message;
import vazkii.vocation.common.core.MessageLoader;

import com.google.common.collect.Sets;

import cpw.mods.fml.relauncher.ReflectionHelper;

// Sound loading code stolen from HQM because I'm too dumb
// to write my own, yay :D
public class SoundLoader {

	private static final String DOMAIN = Vocation.MOD_ID.toLowerCase();

	public static Map<String, ResourceLocation> soundResourcesPerMessage = new HashMap();
	public static Map<String, ResourceLocation> soundResourcesPerPath = new HashMap();

	public static void loadAllSounds(File dir) {
		Map resourceManagers = ReflectionHelper.getPrivateValue(SimpleReloadableResourceManager.class, (SimpleReloadableResourceManager) Minecraft.getMinecraft().getResourceManager(), 2);
		FallbackResourceManager resourceManager = (FallbackResourceManager) resourceManagers.get(DOMAIN);
		resourceManager.addResourcePack(new MessageResourcePack(dir));

		SoundHandler handler = Minecraft.getMinecraft().getSoundHandler();
		SoundList list = new SoundList();
		list.setSoundCategory(SoundCategory.MASTER);

		Method method = ReflectionHelper.findMethod(net.minecraft.client.audio.SoundHandler.class, handler, new String[]{"loadSoundResource", "func_147693_a", "a"}, ResourceLocation.class, SoundList.class);
		if(method == null || handler == null)
			return;

		for(Message message : MessageLoader.allMessages.values()) {
			String sound = message.namespace + "/audio/" + message.voiceover;
			SoundList.SoundEntry entry = new SoundList.SoundEntry();
			entry.setSoundEntryName(sound);
			list.getSoundList().add(entry);

			try {
				ResourceLocation loc = new ResourceLocation(DOMAIN, sound);
				method.invoke(handler, loc, list);
				soundResourcesPerMessage.put(message.id, loc);
				soundResourcesPerPath.put(sound, loc);
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static class MessageResourcePack extends AbstractResourcePack {
		
		private static final Set domains = Sets.newHashSet(DOMAIN);

		public MessageResourcePack(File folder) {
			super(folder);
		}

		@Override
		protected InputStream getInputStreamByName(String name) throws IOException {
			return new BufferedInputStream(new FileInputStream(new File(resourcePackFile, name)));
		}

		@Override
		protected boolean hasResourceName(String name) {
			return soundResourcesPerPath.containsKey(name);
		}

		public InputStream getInputStream(ResourceLocation resource) throws IOException {
			return getInputStreamByName(resource.getResourcePath());
		}

		public boolean resourceExists(ResourceLocation resource) {
			return soundResourcesPerPath.containsValue(resource);
		}

		public Set getResourceDomains() {
			return domains;
		}
	}

	public static class ClientSound extends PositionedSound {
		public ClientSound(ResourceLocation resource, float volume, float pitch) {
			super(resource);
			this.volume = volume;
			this.field_147663_c = pitch;
			this.xPosF = 0.0F;
			this.yPosF = 0.0F;
			this.zPosF = 0.0F;
			this.repeat = false;
			this.field_147665_h = 0;
			this.field_147666_i = AttenuationType.NONE;
		}
	}
}
