package alice.cute.ui.components.sub;


import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import alice.cute.setting.Setting;
import alice.cute.setting.Slider;
import alice.cute.ui.components.Button;
import alice.cute.ui.components.Component;
import alice.cute.util.FontUtil;
import alice.cute.util.RenderUtil;
import alice.cute.util.Util;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;

public class SliderButton extends Component 
{
	private final Button parent;
	private final Slider setting;

	private boolean hovered;
	private boolean dragging = false;
	
	private int offset;
	private int x;
	private int y;

	private double renderWidth;
	
	public SliderButton(Slider value, Button button, int offset) 
	{
		this.subcomponents = new ArrayList<Component>();
		this.setting = value;
		this.parent = button;
		this.x = button.parent.getX() + button.parent.getWidth();
		this.y = button.parent.getY() + button.offset;
		this.offset = offset;
		
		double min = this.setting.getMinValue();
		double max = this.setting.getMaxValue();
		this.renderWidth = (this.parent.parent.width) * (this.setting.getValue() - min) / (max - min);
	}
	
	@Override
	public int getHeight() 
	{
		if(this.open) 
		{
			return (this.height * (this.subcomponents.size() + 1));
		}
		
		return this.height;
	}
	
	
	@Override
	public void setOff(int newOff) 
	{
		this.offset = newOff;
	}
	
	@Override
	public void renderComponent() 
	{
		String displaValue = String.valueOf(Util.roundToPlace(this.setting.getValue(), 2));
		
		RenderUtil.beginRenderRect();
		RenderUtil.setColor(this.backColor);
		RenderUtil.renderRect(x + 2, y, x + width, y + this.height);
		RenderUtil.renderRect(x, y, x + 2, y + this.height);
		RenderUtil.setColor(this.sliderColor);
		RenderUtil.renderRect(x + 2, y, x + (int)this.renderWidth, y + this.height);
		
		if(this.hovered) 
		{
			RenderUtil.setColor(this.sliderColor);
			RenderUtil.renderRect(x + 2, y, x + (int)this.renderWidth, y + this.height);
		}
		
		RenderUtil.endRenderRect();
		
		GL11.glPushMatrix();
		GL11.glScalef(0.75f,0.75f, 0.75f);
		
		FontUtil.drawStringWithShadow(
				this.setting.getName() + " ", 
				(x * 1.333333333333f + 9), 
				(y + 2) * 1.33333333333333f + 2, 
				this.textColorInt);
		
		FontUtil.drawStringWithShadow(
				displaValue, 
				(x + width) * 1.3333333333f - FontUtil.getStringWidth(displaValue), 
				(y + 2)     * 1.3333333333f + 2,
				this.textColorInt);
		

		GL11.glPopMatrix();
	}

	
	@Override
	public void updateComponent(int mouseX, int mouseY) 
	{
		this.hovered = isMouseOnButton(mouseX, mouseY);
		this.y = parent.parent.getY() + offset;
		this.x = parent.parent.getX();
		
		if (!dragging)
			return;
		
		double diff = Math.min(width, Math.max(0, mouseX - this.x));

		double min = this.setting.getMinValue();
		double max = this.setting.getMaxValue();
		
		this.renderWidth = (this.parent.parent.width) * (this.setting.getValue() - min) / (max - min);
		
		
		if (diff == 0) 
		{
			this.setting.setValue(this.setting.getMinValue());
		}
		else 
		{
			double newValue = (diff / width) * (max - min) + min;
			this.setting.setValue(newValue);
		}
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) 
	{
		if(!this.parent.isOpen() || button != 0 || !isMouseOnButton(mouseX, mouseY))
			return;
		
		dragging = true;	
	}
	
	@Override
	public void mouseReleased(int mouseX, int mouseY, int mouseButton) 
	{
		dragging = false;
	}
	
	public boolean isMouseOnButton(int x, int y) 
	{
		return x > this.x && 
			   x < this.x + this.width  &&
			   y > this.y && 
			   y < this.y + this.height;
	}
	
//	public boolean isMouseOnButtonI(int x, int y) 
//	{
//		return x > this.x + this.width / 2 && 
//			   x < this.x + this.width &&
//			   y > this.y && 
//			   y < this.y + this.height;
//	}
}



















