package alice.cute.ui;



import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import alice.cute.module.*;
import alice.cute.ui.components.Button;
import alice.cute.ui.components.Component;
import alice.cute.ui.components.Frame;
import net.minecraft.client.Minecraft;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Mouse;


public class ClickUI extends GuiScreen 
{
	public static final Color color = new Color(153, 207, 220);
	
	public static ArrayList<Frame> frames;


	public ClickUI() 
	{
		this.frames = new ArrayList<Frame>();
		
		int frameX = 5;
		
		for(Category category : Category.values()) 
		{
			Frame frame = new Frame(category);
			
			frame.setX(frameX);
			
			frames.add(frame);
			
			frameX += frame.getWidth() + 1;
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) 
	{
//		boolean blur = Hydrogen.getClient().settingsManager.getSettingByName("Blur").isEnabled();
		
		drawRect(0, 0, this.width, this.height, 0x66101010);
		
//		if (blur) 
//		{
//			BlurUtil.blurAll(0.1f);
//		}
		
		for (Frame frame : frames) 
		{
			frame.renderFrame(this.fontRendererObj);
			frame.updatePosition(mouseX, mouseY);
			
			for (Component comp : frame.getComponents()) 
			{
				comp.updateComponent(mouseX, mouseY);
			}
		}
	}

	@Override
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException 
	{
		for(Frame frame : frames) 
		{
			if (frame.isWithinHeader(mouseX, mouseY)) 
			{
				if(mouseButton == 0) 
				{
					frame.setDrag(true);
					frame.dragX = mouseX - frame.getX();
					frame.dragY = mouseY - frame.getY();
				}
				
				if(mouseButton == 1) 
				{
					frame.toggleOpen();
				}
			}
						
			if(!frame.isOpen())
				continue;
			
			if(frame.getComponents().isEmpty())
				continue;
			
			for(Component component : frame.getComponents()) 
			{
				component.mouseClicked(mouseX, mouseY, mouseButton);
			}
		}
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) 
	{
		for(Frame frame : frames) 
		{
			if(!frame.isOpen() || keyCode == 1)
				continue;
			
			if(frame.getComponents().isEmpty())
				continue;
			
			for(Component component : frame.getComponents()) 
			{
				component.keyTyped(typedChar, keyCode);
			}
		}
		
		if (keyCode == 1) 
		{
            this.mc.displayGuiScreen(null);
        }
	}

//	@Override
//	public void onGuiClosed() 
//	{
////		if(!Hydrogen.getClient().panic) 
////		{
////			ClickGuiConfig clickGuiConfig = new ClickGuiConfig();
////			clickGuiConfig.saveConfig();
////		}
//
//		super.onGuiClosed();
//	}

	
	@Override
    protected void mouseReleased(int mouseX, int mouseY, int state) 
	{
		for(Frame frame : frames) 
		{
			frame.setDrag(false);
		}
		
		for(Frame frame : frames) 
		{
			if(!frame.isOpen()) 
				continue;
			
			if(frame.getComponents().isEmpty()) 
				continue;
			
			for(Component component : frame.getComponents()) 
			{
				component.mouseReleased(mouseX, mouseY, state);
			}
		}
	}

	
	@Override
	public boolean doesGuiPauseGame() 
	{
		return false;
	}
}