package alice.cute.gui.components.sub;

//import me.peanut.hydrogen.module.Module;
//import me.peanut.hydrogen.font.FontHelper;
//import me.peanut.hydrogen.Hydrogen;
//import me.peanut.hydrogen.ui.clickgui.component.Component;
//import me.peanut.hydrogen.ui.clickgui.component.components.Button;
//import me.peanut.hydrogen.settings.Setting;

import alice.cute.gui.components.Component;
import alice.cute.gui.components.Button;
import alice.cute.util.FontUtil;
import alice.cute.util.RenderUtil;
import alice.cute.setting.Mode;
import alice.cute.setting.Setting;
import alice.cute.module.Module;


import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class ModeButton extends Component {

	private boolean hovered;
	private final Button parent;
	private final Mode setting;
	private int offset;
	private int x;
	private int y;

	private int modeIndex;
	
	public ModeButton(Mode set, Button button, int offset) 
	{
		this.setting = set;
		this.parent = button;
		this.x = button.parent.getX() + button.parent.getWidth();
		this.y = button.parent.getY() + button.offset;
		this.offset = offset;
		this.modeIndex = 0;
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
		RenderUtil.beginRenderRect();
		RenderUtil.setColor(this.backColor);
		RenderUtil.renderRect(x, y, x + width, y + this.height);
		RenderUtil.renderRect(x, y, x + 2    , y + 12         );
		RenderUtil.endRenderRect();
		
//		this makes the text rendered below fit in the box
		GL11.glPushMatrix();
		GL11.glScalef(0.75f,0.75f, 0.75f);

		FontUtil.drawStringWithShadow(
				this.setting.getName(), 
				(x + 3) * 1.3333333333f + 5, 
				(y + 2) * 1.3333333333f + 2,
				-1);
		
		FontUtil.drawStringWithShadow(
				this.setting.getMode(), 
				(x + this.parent.parent.getWidth()) * 1.3333333333f - FontUtil.getStringWidth(this.setting.getMode()), //- FontHelper.verdana.getStringWidth(set.getMode()), 
				(y + 2) * 1.3333333333f + 2,
				-1);
		
		
		GL11.glPopMatrix();
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
		if(button != 0 || !this.parent.isOpen() || !isMouseOnButton(mouseX, mouseY))
			return;
		
		this.setting.nextMode();
	}
	
	public boolean isMouseOnButton(int x, int y) 
	{
		return x > this.x && 
			   x < this.x + 88 && 
			   y > this.y && 
			   y < this.y + this.height;
	}
}










