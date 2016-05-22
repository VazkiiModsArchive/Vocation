package vazkii.vocation.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import vazkii.vocation.common.Vocation;

public class PacketSendMessage implements IMessage, IMessageHandler<PacketSendMessage, IMessage> {

	public String id;

	public PacketSendMessage() { }

	public PacketSendMessage(String id) {
		this.id = id;
	}

	public void toBytes(ByteBuf buffer) {
		PacketBuffer packet = new PacketBuffer(buffer);
		packet.writeString(id);
	}

	public void fromBytes(ByteBuf buffer) {
		PacketBuffer packet = new PacketBuffer(buffer);
		id = packet.readStringFromBuffer(64);
	}

	public IMessage onMessage(PacketSendMessage message, MessageContext context) {
		Vocation.proxy.showMessage(message.id);
		return null;
	}

}
