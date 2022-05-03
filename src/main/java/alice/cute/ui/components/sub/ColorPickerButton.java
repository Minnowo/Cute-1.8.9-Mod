package alice.cute.ui.components.sub;


//https://github.com/GandyT/GandyClient-2.0/

import java.awt.Color;

import org.lwjgl.opengl.GL11;

//import GandyClient.core.gui.GuiElement;
//import GandyClient.core.gui.hud.ScreenPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import alice.cute.setting.Checkbox;
import alice.cute.setting.ColorPicker;
import alice.cute.setting.Mode;
import alice.cute.ui.components.Button;
import alice.cute.ui.components.Component;
import alice.cute.util.FontUtil;
import alice.cute.util.RenderUtil;
import alice.cute.util.Util;

public class ColorPickerButton extends Component 
{
	private final ColorPicker setting;
	private final Button parent;
	
	private final int min = 0;
	private final int max = 255;
	
	private boolean hovered;
	
	private int redWidth;
	private int greenWidth;
	private int blueWidth;
	
	private int previewColorHeight = (int)(this.height / 2);
//	distnace between colors 
	private int dist = 2;
	
//	0 for none
//	1 for red
//	2 for green
//	3 for blue 
	private int dragging = 0;
	
	private int offset;
	private int x;
	private int y;
	
	public ColorPickerButton(ColorPicker set, Button button, int offset) 
	{
		this.setting = set;
		this.parent = button;
		this.x = button.parent.getX() + button.parent.getWidth();
		this.y = button.parent.getY() + button.offset;
		this.offset = offset;
		
		this.redWidth   = this.width * this.setting.getRed()   / this.max;
		this.greenWidth = this.width * this.setting.getGreen() / this.max;
		this.blueWidth  = this.width * this.setting.getBlue()  / this.max;
	}

	@Override
	public int getHeight() 
	{
		return this.height * 3 + this.previewColorHeight;
	}

	
	@Override
	public void setOff(int newOff) 
	{
		this.offset = newOff;
	}

	

	@Override
	public void renderComponent() 
	{
		String displaValue = String.valueOf(this.setting.getRed());
		
		int previewOffset = this.previewColorHeight + dist;
		
		RenderUtil.beginRenderRect();
		
//		background 
		RenderUtil.setColor(this.backColor);
		RenderUtil.renderRect(x + 2, y, x + width, y + this.getHeight());
		
//		preview color 
		RenderUtil.setColor(this.setting.getColor());
		RenderUtil.renderRect(x + 2, y, x + this.width, y + this.previewColorHeight);
		
//		red slider 
		RenderUtil.setColor(this.red);
		RenderUtil.renderRect(x + 2, y + previewOffset, x + (int)this.redWidth, y + this.height + this.previewColorHeight );
		
//		green slider 
		RenderUtil.setColor(this.green);
		RenderUtil.renderRect(x + 2, y + previewOffset + this.height, x + (int)this.greenWidth, y + this.height * 2 + this.previewColorHeight);
		
//		blue slider 
		RenderUtil.setColor(this.blue);
		RenderUtil.renderRect(x + 2, y + previewOffset + this.height * 2, x + (int)this.blueWidth, y + this.height * 3 + this.previewColorHeight);
	
		RenderUtil.endRenderRect();
		
		GL11.glPushMatrix();
		GL11.glScalef(0.75f,0.75f, 0.75f);
		
		FontUtil.drawStringWithShadow(
				displaValue + " ", 
				(this.x * 1.333333333333f + 4), 
				(this.y + previewOffset + 1) * 1.33333333333333f + 2, 
				this.textColorInt);
		
		displaValue =  String.valueOf(this.setting.getGreen());
		
		FontUtil.drawStringWithShadow(
				displaValue + " ", 
				(this.x * 1.333333333333f + 4), 
				(this.y + previewOffset + this.height + 1) * 1.33333333333333f + 2, 
				this.textColorInt);
		
		displaValue =  String.valueOf(this.setting.getBlue());
		
		FontUtil.drawStringWithShadow(
				displaValue + " ", 
				(this.x * 1.333333333333f + 4), 
				(this.y + previewOffset + this.height * 2 + 1) * 1.33333333333333f + 2, 
				this.textColorInt);
		
		GL11.glPopMatrix();
		
	}
	

	@Override
	public void updateComponent(int mouseX, int mouseY) 
	{
		this.y = parent.parent.getY() + offset - this.height;
		this.x = parent.parent.getX() + this.width;
		
		if(this.dragging == 0)
			return;
		
		double diff = Math.min(this.width, Math.max(0, mouseX - this.x));
		
		int newVal;
		
		if(diff == 0)
		{
			newVal = 0;
		}
		else 
		{
			newVal = (int)((diff / this.width) * max);
		}
		
		switch(this.dragging)
		{
			case 1:
				
				this.redWidth = this.width * this.setting.getRed() / this.max;
				this.setting.setRed(newVal);
				return;
				
			case 2:
				
				this.greenWidth = this.width * this.setting.getGreen() / this.max;
				this.setting.setGreen(newVal);
				return;
				
			case 3:
				
				this.blueWidth = this.width * this.setting.getBlue() / this.max;
				this.setting.setBlue(newVal);
				return;
		}
	}
	
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) 
	{
		if(!this.parent.isOpen() || button != 0)
			return;
		
		if(this.isMouseOnButtonRed(mouseX, mouseY))
		{
			this.dragging = 1;
			return;
		}
		
		if(this.isMouseOnButtonGreen(mouseX, mouseY))
		{
			this.dragging = 2;
			return;
		}
		
		if(this.isMouseOnButtonBlue(mouseX, mouseY))
		{
			this.dragging = 3;
		}
	}	
	
	@Override
	public void mouseReleased(int mouseX, int mouseY, int mouseButton) 
	{
		this.dragging = 0;
	}
	
	
	
	public boolean isMouseOnButtonRed(int x, int y)
	{
		return x > this.x  && 
			   x < this.x + this.width && 
			   y > this.y + this.previewColorHeight&& 
			   y < this.y + this.height + this.previewColorHeight;
	}
	
	public boolean isMouseOnButtonGreen(int x, int y)
	{
		return x > this.x && 
			   x < this.x + this.width  && 
			   y > this.y + this.height + this.previewColorHeight&& 
			   y < this.y + this.height * 2 + this.previewColorHeight;
	}
	
	public boolean isMouseOnButtonBlue(int x, int y)
	{
		return x > this.x && 
				   x < this.x + this.width  && 
				   y > this.y + this.height + this.previewColorHeight&& 
				   y < this.y + this.height * 3 + this.previewColorHeight;
	}
}



















