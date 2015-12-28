package vazkii.vocation.common.core;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;

import com.google.gson.JsonObject;

public class StackWrapper {

	public String id;
	public int metadata = -1;
	public int stackSize = 1;
	public JsonObject nbt = null;
	
	private transient ItemStack stack = null;
	private transient boolean validateMetadata = false;
	private transient boolean validateNBT = false;
	
	public ItemStack asStack() {
		if(stack == null)
			initStack();
		
		return stack;
	}
	
	private void initStack() {
		int propMeta = metadata == -1 ? 0 : metadata;
		validateMetadata = metadata != -1;
		
		stack = new ItemStack((Item) Item.itemRegistry.getObject(id), stackSize, propMeta);
		
		if(nbt != null) {
			String json = fixJson(MessageLoader.gson.toJson(nbt));
			
			try {
				NBTBase cmp = JsonToNBT.func_150315_a(json);
				if(cmp instanceof NBTTagCompound) {
					stack.setTagCompound((NBTTagCompound) cmp); 
					validateNBT = true;
				}
			} catch (NBTException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isEquivalentItem(ItemStack stack) {
		ItemStack ourStack = asStack();
		if(ourStack == null)
			return false;
		
		if(stack.getItem() != ourStack.getItem())
			return false;
		
		if(validateMetadata && stack.getItemDamage() != ourStack.getItemDamage())
			return false;
		
		if(validateNBT && !ItemStack.areItemStackTagsEqual(stack, ourStack))
			return false;
		
		return true;
	}
	
	private String fixJson(String s) {
		return s.replaceAll("\"([^,]*?)\":", "$1:");
	}
	
	@Override
	public String toString() {
		return "StackWrapper["
				+ " id=" + id
				+ " metadata=" + metadata
				+ " nbt=" + MessageLoader.gson.toJson(nbt)
				+ "]";
	}
	
}
