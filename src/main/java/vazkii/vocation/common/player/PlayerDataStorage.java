package vazkii.vocation.common.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import vazkii.vocation.common.core.MessageLoader;

public class PlayerDataStorage {

	private static final String DATA_TAG = "VocationData";
	private static final String LAST_SEEN = "_lastSeen";
	private static final String SHOWN_MESSAGE = "_shownMessage";
	
	private static final List<String> exempt = new ArrayList() {{
		add(LAST_SEEN);
		add(SHOWN_MESSAGE);
	}};
	
	public static NBTTagCompound getDataForPlayer(EntityPlayer player) {
		NBTTagCompound forgeData = player.getEntityData();
		if(!forgeData.hasKey(EntityPlayer.PERSISTED_NBT_TAG))
			forgeData.setTag(EntityPlayer.PERSISTED_NBT_TAG, new NBTTagCompound());
		
		NBTTagCompound persistentData = forgeData.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
		if(!persistentData.hasKey(DATA_TAG))
			persistentData.setTag(DATA_TAG, new NBTTagCompound());
		
		return persistentData.getCompoundTag(DATA_TAG);
	}
	
	public static List<String> getSeenMessage(EntityPlayer player) {
		NBTTagCompound cmp = getDataForPlayer(player);
		Set<String> keys = cmp.getKeySet();
		
		List<String> completed = new ArrayList();
		for(String s : keys) {
			if(exempt.contains(s))
				continue;
			
			if(MessageLoader.allMessages.containsKey(s)) {
				NBTBase nbt = cmp.getTag(s);
				if(nbt instanceof NBTTagByte && ((NBTTagByte) nbt).getByte() == 1)
					completed.add(s);
			}
		}
		
		return completed;
	}
	
	public static boolean isSeen(EntityPlayer player, String message) {
		NBTTagCompound cmp = getDataForPlayer(player);
		return cmp.getBoolean(message);
	}
	
	public static void setSeen(EntityPlayer player, String message, boolean seen) {
		NBTTagCompound cmp = getDataForPlayer(player);
		cmp.setBoolean(message, seen);
	}
	
	public static void clearAllSeen(EntityPlayer player) {
		NBTTagCompound cmp = getDataForPlayer(player);
		
		List<String> keys = new ArrayList(cmp.getKeySet());
		for(String s : keys)
			if(!exempt.contains(s))
				cmp.removeTag(s);
	}
	
	public static void setLastSeen(EntityPlayer player, String lastSeen) {
		NBTTagCompound cmp = getDataForPlayer(player);
		cmp.setString(LAST_SEEN, lastSeen);
	}
	
	public static String getLastSeen(EntityPlayer player) {
		NBTTagCompound cmp = getDataForPlayer(player);
		return cmp.getString(LAST_SEEN);
	}
	
	public static void setShownMessage(EntityPlayer player) {
		NBTTagCompound cmp = getDataForPlayer(player);
		cmp.setBoolean(SHOWN_MESSAGE, true);
	}
	
	public static boolean hasShownMessage(EntityPlayer player) {
		NBTTagCompound cmp = getDataForPlayer(player);
		return cmp.getBoolean(SHOWN_MESSAGE);
	}
	
}
