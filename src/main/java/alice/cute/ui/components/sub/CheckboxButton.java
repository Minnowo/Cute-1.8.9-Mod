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

		int c1 = new Color(17, 17, 17, 140).getRGB(); // 0x88111111
		int c3 = new Color(34, 34, 34, 140).getRGB(); // 0x88222222

		int x = this.parent.parent.getX();
		int y = this.parent.parent.getY() + this.offset;
		int width = this.parent.parent.getWidth();
		
		int color = hovered ? 0x99000000 : 0x88000000;
		
		Gui.drawRect(x, y, x + width , y + this.height, color);
		Gui.drawRect(x, y, x + 2     , y + 12         , color);

//		this makes the text rendered below fit in the box
		GL11.glPushMatrix();
		GL11.glScalef(0.75f,0.75f, 0.75f);

		FontUtil.drawStringWithShadow(
				this.setting.getName(), 
				(x + 3) * 1.3333333333f + 5, 
				(y + 2) * 1.3333333333f + 2,
				-1);
		
		GL11.glPopMatrix();

		
		Gui.drawRect(x + width - 2, y + 3, x + width - 8, y + 9, 0x88999999);
		
		if(this.setting.getValue()) 
		{
			Gui.drawRect(x + width - 3, y + 4, x + width - 7, y + 8, 0x99000000);
		}
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
			   x < this.x + 88 && 
			   y > this.y && 
			   y < this.y + 12;
	}
}














