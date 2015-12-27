package vazkii.vocation.client;

import java.util.ArrayDeque;
import java.util.Queue;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import vazkii.vocation.common.core.Message;

public class HUDHandler {

	private static final int FADE_TICKS = 10;
	
	// TODO Clear on world leave
	private Queue<Message> messageQueue = new ArrayDeque();
	private Message currentMessage;
	private Message lastMessage;
	int ticksOnCurrentMessage = 0;
	int ticksForFadeout = 0;
	
	public void addMessageToQueue(Message m) {
		messageQueue.add(m);
		if(currentMessage == null)
			swapMessage();
	}
	
	@SubscribeEvent
	public void onTick(ClientTickEvent event) {
		if(Minecraft.getMinecraft().theWorld == null) {
			currentMessage = null;
			lastMessage = null;
			ticksOnCurrentMessage = 0;
			ticksForFadeout = 0;
			messageQueue.clear();
			return;
		}
		
		if(event.phase == Phase.START) {
			if(currentMessage != null) {
				if(ticksOnCurrentMessage >= (currentMessage.time + FADE_TICKS))
					swapMessage();
				else ticksOnCurrentMessage++;
			}
			
			if(lastMessage != null) {
				if(ticksForFadeout >= FADE_TICKS) {
					lastMessage = null;
					ticksForFadeout = 0;
				} else ticksForFadeout++;
			}
		}
	}
	
	private void swapMessage() {
		if(currentMessage != null)
			lastMessage = currentMessage;
		
		if(!messageQueue.isEmpty())
			currentMessage = messageQueue.poll();
		else currentMessage = null;
		
		ticksOnCurrentMessage = 0;
		ticksForFadeout = 0;
	}
	
	@SubscribeEvent
	public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
		if(currentMessage != null) {
			float a = 1F;
			float time = ticksOnCurrentMessage + event.partialTicks;
			if(time < FADE_TICKS)
				a = time / FADE_TICKS;

			renderMessage(event, currentMessage, a);
		}
	
		if(lastMessage != null) {
			float a = 1F;
			if(ticksForFadeout > 0)
				a = (FADE_TICKS - Math.min(FADE_TICKS, (ticksForFadeout + event.partialTicks))) / FADE_TICKS;
			renderMessage(event, lastMessage, a);
		}
	}
	
	private void renderMessage(RenderGameOverlayEvent.Post event, Message m, float a) {
		Minecraft mc = Minecraft.getMinecraft();
		FontRenderer font = mc.fontRenderer;
		
		float width = font.getStringWidth(m.message);
		float dist = 30F;
		
		GL11.glPushMatrix();
		GL11.glTranslatef((width + dist) * a - width, 0F, 0F);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//		int alphaVal = (int) (a * 0xFF);
		int color = 0xFFFFFF;// + (alphaVal << 24);
		font.drawString(m.message, 0, 20, color);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glPopMatrix();
	}
	
}
