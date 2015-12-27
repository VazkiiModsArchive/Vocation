package vazkii.vocation.common.core;

import com.google.gson.JsonObject;

public class StackWrapper {

	public String id;
	public int meta = -1;
	public JsonObject nbt = null;
	
	@Override
	public String toString() {
		return "StackWrapper["
				+ " id=" + id
				+ " meta=" + meta
				+ " nbt=" + MessageLoader.gson.toJson(nbt)
				+ "]";
	}
	
}
