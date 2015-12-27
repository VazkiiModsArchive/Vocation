package vazkii.vocation.client;

import java.util.ArrayDeque;
import java.util.Queue;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import vazkii.vocation.common.core.Message;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;

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
		if(event.type != ElementType.ALL)
			return;
		
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
		
		int maxWidth = 300;
		float width = Math.min(maxWidth, font.getStringWidth(m.message));
		float dist = 20F;
		float y = 20F;
		int height = 55;
		
		int resolution = event.resolution.getScaleFactor();
		float scale = 1F;
		if(resolution > 2)
			scale = 2F / resolution;
		
		
		GL11.glPushMatrix();
		GL11.glScalef(scale, scale, scale);
		GL11.glTranslatef(((width + dist) * a - width) / scale, y / scale, 0F);
		TextRenderer.renderText(0, 0, maxWidth, 1, m.message);
		
		GL11.glPopAttrib();
		GL11.glPopMatrix();
	}
	
}