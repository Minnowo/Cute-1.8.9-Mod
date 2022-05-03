package alice.cute.ui.components.sub;



import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import alice.cute.ui.components.Button;
import alice.cute.ui.components.Component;
import alice.cute.util.FontUtil;
import alice.cute.util.RenderUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class KeybindButton extends Component 
{
	private final Button parent;
	
	private boolean hovered;
	private boolean binding;
	
	private int offset;
	private int x;
	private int y;
	
	public KeybindButton(Button button, int offset) 
	{
		this.parent = button;
		this.x = button.parent.getX() + button.parent.getWidth();
		this.y = button.parent.getY() + button.offset;
		this.offset = offset;
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
		String bindingText = this.binding ? "Binding. Unbind: RMB" : "Keybind";
		
		RenderUtil.beginRenderRect();
		RenderUtil.setColor(this.backColor);
		RenderUtil.renderRect(this.x + 2, this.y, this.x + this.width, this.y + this.height);
		RenderUtil.renderRect(this.x    , this.y, this.x + 2         , this.y + this.height);
		RenderUtil.endRenderRect();
		
		GL11.glPushMatrix();
		GL11.glScalef(0.75f,0.75f, 0.75f);

		FontUtil.drawStringWithShadow(
				bindingText, 
				(parent.parent.getX() * 1.333333333333f + 9), 
				(parent.parent.getY() + offset + 2) * 1.33333333333333f + 2, 
				this.textColorInt);
		
		bindingText = binding ? "" : Keyboard.getKeyName(this.parent.mod.getKeybind().getKeyCode());
		
		FontUtil.drawStringWithShadow(
				bindingText, 
				(x + width) * 1.3333333333f - FontUtil.getStringWidth(bindingText), 
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
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) 
	{
		if(this.parent.isOpen() && button == 0 && isMouseOnButton(mouseX, mouseY)) 
		{
			this.binding = !this.binding;
		} 
		else if(button == 1 && this.binding) 
		{
			this.parent.mod.unbindKey();
			this.binding = false;
		}
	}
	
	@Override
	public void keyTyped(char typedChar, int key) 
	{
		if(this.binding) 
		{
			this.parent.mod.setKeyCode(key);
			this.binding = false;
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







