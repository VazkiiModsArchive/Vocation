package vazkii.vocation.common.network;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ChatComponentText;
import vazkii.vocation.client.ClientProxy;
import vazkii.vocation.common.CommonProxy;
import vazkii.vocation.common.Vocation;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketSendMessage implements IMessage, IMessageHandler<PacketSendMessage, IMessage> {

	public String id;

	public PacketSendMessage() { }

	public PacketSendMessage(String id) {
		this.id = id;
	}

	public void toBytes(ByteBuf buffer) {
		PacketBuffer packet = new PacketBuffer(buffer);
		try {
			packet.writeStringToBuffer(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void fromBytes(ByteBuf buffer) {
		PacketBuffer packet = new PacketBuffer(buffer);
		try {
			id = packet.readStringFromBuffer(64);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public IMessage onMessage(PacketSendMessage message, MessageContext context) {
		Vocation.proxy.showMessage(message.id);
		return null;
	}

}
