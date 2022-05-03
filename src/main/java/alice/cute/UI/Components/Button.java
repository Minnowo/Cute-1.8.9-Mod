package alice.cute.ui.components;


import java.awt.*;
import java.util.ArrayList;

//import me.peanut.hydrogen.module.Module;
//import me.peanut.hydrogen.ui.clickgui.component.components.sub.*;
//import me.peanut.hydrogen.ui.clickgui.component.components.sub.CheckboxButton;
//import me.peanut.hydrogen.font.FontHelper;
//import me.peanut.hydrogen.font.FontUtil;
//import me.peanut.hydrogen.utils.RenderUtil;
//import me.peanut.hydrogen.utils.Utils;
//import me.peanut.hydrogen.Hydrogen;
//import me.peanut.hydrogen.ui.clickgui.ClickGui;
//import me.peanut.hydrogen.ui.clickgui.component.Component;
//import me.peanut.hydrogen.ui.clickgui.component.Frame;
//import me.peanut.hydrogen.settings.Setting;

import alice.cute.module.Module;

import alice.cute.setting.Setting;
import alice.cute.setting.checkbox.Checkbox;
import alice.cute.setting.slider.Slider;
import alice.cute.ui.ClickUI;
import alice.cute.ui.components.sub.CheckboxButton;
import alice.cute.ui.components.sub.KeybindButton;
import alice.cute.ui.components.sub.ModeButton;
import alice.cute.ui.components.sub.SliderButton;
import alice.cute.setting.color.ColorPicker;
import alice.cute.setting.mode.Mode;
import alice.cute.setting.keybind.Keybind;

import alice.cute.setting.SettingType;

import alice.cute.util.RenderUtil;
import alice.cute.util.FontUtil;
import net.minecraft.client.Minecraft;

public class Button extends Component 
{
	public final Module mod;
	public final Frame parent;
	public final ArrayList<Component> subcomponents;

	public int offset;
	
	private boolean isHovered;
	public boolean open;
	
	
	public Button(Module mod, Frame parent, int offset) 
	{
		this.mod    = mod;
		this.parent = parent;
		this.offset = offset;
		this.subcomponents = new ArrayList<Component>();
		this.open = false;
		
		int opY = offset + this.height;
		
		for(Setting s : mod.getSettings())
		{
			switch(s.getSettingType())
			{
				default:
					break;
				case CHECKBOX:
					this.subcomponents.add(new CheckboxButton((Checkbox)s, this, opY));
					opY += this.height;
					break;
					
				case SLIDER:
					this.subcomponents.add(new SliderButton((Slider)s, this, opY));
					opY += this.height;
					break;
					
				case MODE:
					this.subcomponents.add(new ModeButton((Mode)s, this, opY));
					opY += this.height;
					break;
			}
		}
		
		this.subcomponents.add(new KeybindButton(this, opY));
	}


	@Override
	public void setOff(int newOff) 
	{
		this.offset = newOff;
		
		int opY = newOff + this.height;
		
		for(Component comp : this.subcomponents) 
		{
			comp.setOff(opY);
			opY += this.height;
		}
	}
	
	@Override
	public void renderComponent()
	{
		int x = this.parent.getX();
		int y = this.parent.getY() + this.offset;
		int x2 = x + this.parent.getWidth();
		int y2 = y + this.height;
		
		Color c = new Color(200, 100, 100);
		
		RenderUtil.beginRenderRect();
		RenderUtil.setColor(this.backColor);
		RenderUtil.renderRect(x, y, x2, y2);
		RenderUtil.setColor(this.sliderColor);
		RenderUtil.renderRect(x, y, x2, y2);
		
		if(this.isHovered)
		{
			RenderUtil.renderRect(x, y, x2, y2);
		}
		
		if(this.mod.isEnabled())
		{
			RenderUtil.renderRect(x, y, x2, y2);
		}

		RenderUtil.endRenderRect();
		
		FontUtil.drawTotalCenteredStringWithShadowMC(
				this.mod.getName(), 
				x + this.parent.getWidth() / 2, 
				y + (int)(this.height / 2) + 1, 
				this.textColorInt);

		
		if(!this.open)
			return;
		
		if (this.subcomponents.isEmpty())
			return;
	
		for (Component comp : this.subcomponents) 
		{
			comp.renderComponent();
		}
		
		
		RenderUtil.setColor(ClickUI.color);
		RenderUtil.renderRectSingle(x + 2, y2, x + 3, y + ((this.subcomponents.size() + 1) * this.height));
	}
	
	@Override
	public int getHeight() 
	{
		if(this.open) 
		{
			return (12 * (this.subcomponents.size() + 1));
		}
		
		return 12;
	}
	
	
	@Override
	public void updateComponent(int mouseX, int mouseY) 
	{
		 this.isHovered = isMouseOnButton(mouseX, mouseY);
		
		if(this.subcomponents.isEmpty())
			return;
		
		for(Component comp : this.subcomponents) 
		{
			comp.updateComponent(mouseX, mouseY);
		}
	}
	
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) 
	{
		if(isMouseOnButton(mouseX, mouseY))
		{
			if(button == 0) 
			{
				this.mod.toggle();
			}

			else if(button == 1) 
			{
				this.open = !this.open;
				this.parent.refresh();
			}
		}
		
			
		for(Component comp : this.subcomponents) 
		{
			comp.mouseClicked(mouseX, mouseY, button);
		}
	}
	
	
	@Override
	public void mouseReleased(int mouseX, int mouseY, int mouseButton) 
	{
		for(Component comp : this.subcomponents) 
		{
			comp.mouseReleased(mouseX, mouseY, mouseButton);
		}
	}
	
	
	@Override
	public void keyTyped(char typedChar, int key) 
	{
		for(Component comp : this.subcomponents) 
		{
			comp.keyTyped(typedChar, key);
		}
	}
	
	
	public boolean isMouseOnButton(int x, int y) 
	{
		return x > parent.getX() && 
			   x < parent.getX() + parent.getWidth() && 
			   y > this.parent.getY() + this.offset &&
			   y < this.parent.getY() + this.height + this.offset;
	}
}











