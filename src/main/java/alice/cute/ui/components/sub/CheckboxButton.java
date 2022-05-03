package alice.cute.ui.components.sub;


//import me.peanut.hydrogen.font.FontHelper;
//import me.peanut.hydrogen.Hydrogen;
//import me.peanut.hydrogen.ui.clickgui.component.Component;
//import me.peanut.hydrogen.ui.clickgui.component.components.Button;
//import me.peanut.hydrogen.settings.Setting;

import alice.cute.setting.checkbox.Checkbox;
import alice.cute.ui.components.Button;
import alice.cute.ui.components.Component;
import alice.cute.util.FontUtil;
import alice.cute.util.RenderUtil;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class CheckboxButton extends Component 
{
	private final Checkbox setting;
	private final Button parent;
	
	private boolean hovered;
	
	private int offset;
	private int x;
	private int y;
	
	public CheckboxButton(Checkbox option, Button button, int offset) 
	{
		this.setting = option;
		this.parent = button;
		this.x = button.parent.getX() + button.parent.getWidth();
		this.y = button.parent.getY() + button.offset;
		this.offset = offset;
	}

	@Override
	public void renderComponent() 
	{
		int width = this.parent.parent.getWidth();
		
		RenderUtil.beginRenderRect();
		RenderUtil.setColor(this.backColor);
		RenderUtil.renderRect(x, y, x + width , y + this.height);
		RenderUtil.renderRect(x, y, x + 2     , y + this.height);

		RenderUtil.setColor(this.backColor);
		RenderUtil.renderRect(x + width - 8, y + 3, x + width - 2, y + 9);
		
		if(this.setting.getValue()) 
		{
			RenderUtil.setColor(this.textColor);
			RenderUtil.renderRect(x + width - 7, y + 4, x + width - 3, y + 8);
		}
		
		RenderUtil.endRenderRect();
		
		
//		this makes the text rendered below fit in the box
		GL11.glPushMatrix();
		GL11.glScalef(0.75f,0.75f, 0.75f);

		FontUtil.drawStringWithShadow(
				this.setting.getName(), 
				(x + 3) * 1.3333333333f + 5, 
				(y + 2) * 1.3333333333f + 2,
				this.textColorInt);
		
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
		this.hovered = isMouseOnButton(mouseX, mouseY);
		this.y = parent.parent.getY() + offset;
		this.x = parent.parent.getX();
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) 
	{
		if(isMouseOnButton(mouseX, mouseY) && button == 0 && this.parent.open) 
		{
			this.setting.toggleValue();
		}
	}
	
	public boolean isMouseOnButton(int x, int y) 
	{
		return x > this.x && 
			   x < this.x + this.width && 
			   y > this.y && 
			   y < this.y + this.height;
	}
}














