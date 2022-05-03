package alice.cute.ui.components.sub;


import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import alice.cute.setting.Setting;
import alice.cute.setting.slider.Slider;
import alice.cute.ui.components.Button;
import alice.cute.ui.components.Component;
import alice.cute.util.FontUtil;
import alice.cute.util.RenderUtil;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;

public class SliderButton extends Component 
{

	private boolean hovered;

	private final Slider setting;
	private final Button parent;
	private int offset;
	private int x;
	private int y;
	private boolean dragging = false;

	private double renderWidth;
	
	public SliderButton(Slider value, Button button, int offset) 
	{
		this.setting = value;
		this.parent = button;
		this.x = button.parent.getX() + button.parent.getWidth();
		this.y = button.parent.getY() + button.offset;
		this.offset = offset;
	}
	
	@Override
	public void renderComponent() 
	{

		Color c1 = new Color(17, 17, 17, 140); // 0x88111111
		Color c2 = new Color(0, 0, 0, 115); // 0x77000000
		Color c3 = new Color(34, 34, 34, 140); // 0x88222222

		Color notHover = new Color(136, 0, 0);
		
		int x = this.parent.parent.getX();
		int y = this.parent.parent.getY() + this.offset;
		int width = this.parent.parent.getWidth();
		String displaValue = String.valueOf(roundToPlace(this.setting.getValue(), 2));
		
		RenderUtil.beginRenderRect();
		RenderUtil.renderRect(x + 2, y, x + width                , y + this.height, notHover);
		RenderUtil.renderRect(x + 2, y, x + (int)this.renderWidth, y + this.height, notHover);
		
		
		if(this.hovered) 
		{
			RenderUtil.renderRect(x + 2, y, x + (int)this.renderWidth, y + this.height, c2);
		}
		RenderUtil.renderRect(x, y, x + 2, y + this.height, c1);
		RenderUtil.endRenderRect();
		
		GL11.glPushMatrix();
		GL11.glScalef(0.75f,0.75f, 0.75f);
		
		FontUtil.drawStringWithShadow(
				this.setting.getName() + " ", 
				(parent.parent.getX() * 1.333333333333f + 9), 
				(parent.parent.getY() + offset + 2) * 1.33333333333333f + 2, 
				-1);
		
		FontUtil.drawStringWithShadow(
				displaValue, 
				(x + width) * 1.3333333333f - FontUtil.getStringWidth(displaValue), 
				(y + 2)     * 1.3333333333f + 2,
				-1);
		

		GL11.glPopMatrix();
	}

	@Override
	public void setOff(int newOff) 
	{
		offset = newOff;
	}
	
	@Override
	public void updateComponent(int mouseX, int mouseY) 
	{
		this.hovered = isMouseOnButtonD(mouseX, mouseY) || isMouseOnButtonI(mouseX, mouseY);
		this.y = parent.parent.getY() + offset;
		this.x = parent.parent.getX();
		
		double diff = Math.min(88, Math.max(0, mouseX - this.x));

		double min = this.setting.getMinValue();
		double max = this.setting.getMaxValue();
		
		renderWidth = (this.parent.parent.width) * (this.setting.getValue() - min) / (max - min);
		
		if (!dragging)
			return;
		
		if (diff == 0) 
		{
			this.setting.setValue(this.setting.getMinValue());
		}
		else 
		{
			double newValue = roundToPlace(((diff / 88) * (max - min) + min), 2);
			this.setting.setValue(newValue);
		}
	}
	
	private static double roundToPlace(double value, int places) 
	{
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) 
	{
		if(!this.parent.open)
			return;
		
		if(isMouseOnButtonD(mouseX, mouseY) && button == 0) 
		{
			dragging = true;
		}
		
		if(isMouseOnButtonI(mouseX, mouseY) && button == 0) 
		{
			dragging = true;
		}
	}
	
	@Override
	public void mouseReleased(int mouseX, int mouseY, int mouseButton) 
	{
		dragging = false;
	}
	
	public boolean isMouseOnButtonD(int x, int y) 
	{
		return x > this.x && 
			   x < this.x + (parent.parent.getWidth() / 2 + 1) &&
			   y > this.y && 
			   y < this.y + this.height;
	}
	
	public boolean isMouseOnButtonI(int x, int y) 
	{
		return x > this.x + parent.parent.getWidth() / 2 && 
			   x < this.x + parent.parent.getWidth() &&
			   y > this.y && 
			   y < this.y + this.height;
	}
}



















