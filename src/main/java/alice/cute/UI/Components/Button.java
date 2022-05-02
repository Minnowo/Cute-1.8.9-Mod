package alice.cute.UI.Components;


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
import alice.cute.setting.color.ColorPicker;
import alice.cute.setting.mode.Mode;
import alice.cute.setting.keybind.Keybind;

import alice.cute.setting.SettingType;

import alice.cute.util.RenderUtil;
import alice.cute.util.FontUtil;

import alice.cute.UI.ClickGUI;

import net.minecraft.client.Minecraft;

public class Button extends Component 
{
	public final Module mod;
	public final Frame parent;
	public final ArrayList<Component> subcomponents;
	
	public Color color;
	public Color colorHover;
	public Color colorEnabled;
	public Color colorEnabledHovered;
	public final int fontColor = new Color(255, 255, 233).getRGB();
	
	public final int height;
	
	public int offset;
	private	int tooltipX;
	private int tooltipY;
	
	private boolean isHovered;
	public boolean open;
	
	
	public Button(Module mod, Frame parent, int offset) 
	{
		this.mod    = mod;
		this.parent = parent;
		this.offset = offset;
		this.subcomponents = new ArrayList<Component>();
		this.open = false;
		this.height = 12;
		
		this.color               = new Color(51, 0, 0);
		this.colorEnabledHovered = new Color(32, 0, 0);
		this.colorEnabled        = new Color(64, 0, 0);
		this.colorHover          = new Color(48, 0, 0);
		
		
		int opY = offset + 12;
		
		for(Setting s : mod.getSettings())
		{
			switch(s.getSettingType())
			{
				case CHECKBOX:
//					this.subcomponents.add(new CheckboxButton(s, this, mod, opY));
					opY += this.height;
					break;
					
				case SLIDER:
//					this.subcomponents.add(new SliderButton(s, this, mod, opY));
					opY += this.height;
					break;
					
				case MODE:
//					this.subcomponents.add(new ModeButton(s, this, mod, opY));
					opY += this.height;
					break;
					
				case KEYBIND:
//					this.subcomponents.add(new TextButton(s, this, mod, opY));
					opY += this.height;
					break;
			}
		}
		
//		this.subcomponents.add(new VisibleButton(this, mod, opY));
//		this.subcomponents.add(new KeybindButton(this, opY));
	}

	public void updateTooltipPosition(int mouseX, int mouseY) 
	{
		tooltipX = mouseX + 18;
		tooltipY = mouseY - 18;
	}

//	public void renderTooltip(String name) 
//	{
////		boolean ttf = Hydrogen.getClient().settingsManager.getSettingByName("Font Type").getMode().equalsIgnoreCase("TTF");
////		if(ttf) {
////			RenderUtil.drawBorderedCorneredRect(parent.getWidth() / 2 + tooltipX - 54, this.parent.barHeight + tooltipY - 3, parent.getWidth() / 2 + tooltipX + FontHelper.sf_l.getStringWidth(name) - 47, this.parent.barHeight + tooltipY + 12, 2, 0x95000000, 0x80000000);
////			FontHelper.sf_l.drawStringWithShadow(name, parent.getWidth() / 2 + tooltipX - 50, (this.parent.barHeight + tooltipY) - 2, Color.white);
////
////			RenderUtil.startClip(parent.getWidth() / 2 + tooltipX - 54, this.parent.barHeight + tooltipY - 3, parent.getWidth() / 2 + tooltipX + FontHelper.sf_l.getStringWidth(name) - 45, this.parent.barHeight + tooltipY + 12);
////		} else {
//
//		
//		RenderUtil.drawBorderedCorneredRect(parent.getWidth() / 2 + tooltipX - 54, this.parent.barHeight + tooltipY - 3, parent.getWidth() / 2 + tooltipX + Minecraft.getMinecraft().fontRendererObj.getStringWidth(name) - 45, this.parent.barHeight + tooltipY + 12, 2, 0x95000000, 0x80000000);
//		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(name, parent.getWidth() / 2 + tooltipX - 50, (this.parent.barHeight + tooltipY), -1);
//
//		RenderUtil.startClip(parent.getWidth() / 2 + tooltipX - 54, this.parent.barHeight + tooltipY - 3, parent.getWidth() / 2 + tooltipX + Minecraft.getMinecraft().fontRendererObj.getStringWidth(name) - 45, this.parent.barHeight + tooltipY + 12);
//	
//		RenderUtil.endClip();
//	}
	
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
		
		RenderUtil.rect(x, y, x2, y2, this.color);
		RenderUtil.rect(x, y, x2, y2, this.color);

		if(this.mod.isEnabled() && this.isHovered) 
		{
			RenderUtil.rect(x, y, x2, y2, this.colorEnabledHovered);
		}

		if(this.mod.isEnabled()) 
		{
			RenderUtil.rect(x, y, x2, y2, this.colorEnabledHovered);
		}

		if(this.isHovered) 
		{
			RenderUtil.rect(x, y, x2, y2, this.colorHover);
		}

//		if(Hydrogen.getClient().settingsManager.getSettingByName("Font Type").getMode().equalsIgnoreCase("TTF")) {
//			FontUtil.drawTotalCenteredStringWithShadowSFL(this.mod.toggled ? this.mod.getName() : this.isHovered ? "�7" + this.mod.getName() : "�f" + this.mod.getName(), parent.getX() + parent.getWidth() / 2, (parent.getY() + offset + 7) - 2, new Color(255, 233, 181));
//		} else {
		
		
//		if(this.mod.isEnabled())
//		{
			FontUtil.drawTotalCenteredStringWithShadowMC(
					this.mod.getName(), 
					x + this.parent.getWidth() / 2, 
					y + (int)(this.height / 2) + 1, 
					this.fontColor);
//		}
			
//	    this is a 1 line of the above basically, try this if the above breaks 
//		FontUtil.drawTotalCenteredStringWithShadowMC(this.mod.isEnabled() ? this.mod.getName() : this.isHovered ? "�7" + this.mod.getName() : "�f" + this.mod.getName(), parent.getX() + parent.getWidth() / 2, (parent.getY() + offset + 7), 0xffffe9b5);
		

//		if(this.subcomponents.size() > 2)
//			FontHelper.sf_l.drawStringWithShadow(this.open ? "v" : "�f>", (parent.getX() + parent.getWidth() - 10), (parent.getY() + offset), new Color(255, 230, 181));

		
		if(!this.open)
			return;
		
		if (this.subcomponents.isEmpty())
			return;
	
		for (Component comp : this.subcomponents) 
		{
			comp.renderComponent();
		}
		RenderUtil.rect(x + 2, y2, x + 3, y + ((this.subcomponents.size() + 1) * this.height), ClickGUI.color);
		
		

//		if(this.isHovered && Hydrogen.getClient().settingsManager.getSettingByName("Tooltip").isEnabled()) {
//			renderTooltip(mod.getDescription());
//		}
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
		
//		if(Hydrogen.getClient().settingsManager.getSettingByName("Tooltip").isEnabled()) 
//		{
//			updateTooltipPosition(mouseX, mouseY);
//		}
		
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
		if(isMouseOnButton(mouseX, mouseY) && button == 0) 
		{
			this.mod.toggle();
		}
	
		if(isMouseOnButton(mouseX, mouseY) && button == 1) 
		{
			this.open = !this.open;
			this.parent.refresh();
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
			   y < this.parent.getY() + 12 + this.offset;
	}
}











