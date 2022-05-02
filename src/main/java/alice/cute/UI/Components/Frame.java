package alice.cute.UI.Components;


import java.awt.*;
import java.util.ArrayList;

//import me.peanut.hydrogen.module.Category;
//import me.peanut.hydrogen.module.Module;
//import me.peanut.hydrogen.ui.clickgui.component.components.Button;
//import me.peanut.hydrogen.font.FontUtil;
//import me.peanut.hydrogen.utils.RenderUtil;
//import me.peanut.hydrogen.Hydrogen;
//import me.peanut.hydrogen.module.modules.gui.ClickGUI;

import alice.cute.module.*;
import alice.cute.UI.ClickGUI;
import alice.cute.managers.ModuleManager;
import alice.cute.util.FontUtil;
import alice.cute.util.RenderUtil;
import net.minecraft.client.gui.FontRenderer;

public class Frame 
{

	public final ArrayList<Component> components;
	public final Category category;
	
	public boolean open;
	
	public int x;
	public int y;
	public final int width;
	public final int barHeight;
	
	private boolean isDragging;
	
	public int dragX;
	public int dragY;

	public Frame(Category cat) 
	{
		this.components = new ArrayList<Component>();
		this.category   = cat;
		this.width      = 88;
		this.x          = 5;
		this.y          = 5;
		this.barHeight  = 13;
		this.dragX      = 0;
		this.open       = false;
		this.isDragging = false;
		
		
		int tY = this.barHeight;

		for (Module mod : ModuleManager.getModulesInCategory(category)) 
		{
			Button modButton = new Button(mod, this, tY);
			this.components.add(modButton);
			tY += 12;
		}
	}

	public ArrayList<Component> getComponents() 
	{
		return components;
	}

	public void setX(int newX) 
	{
		this.x = newX;
	}

	public void setY(int newY) 
	{
		this.y = newY;
	}

	public void setDrag(boolean drag) 
	{
		this.isDragging = drag;
	}

	public boolean isOpen() 
	{
		return open;
	}

	public void setOpen(boolean open) 
	{
		this.open = open;
	}

	public void renderFrame(FontRenderer fontRenderer) 
	{
		Module cgui = ModuleManager.getModuleByClass(ClickGUI.class);
		
		Color color = new Color(128, 128, 128);
		RenderUtil.rect(this.x - 2, this.y - 2, this.x + this.width + 2, this.y + this.barHeight, color);

//		if(Hydrogen.getClient().settingsManager.getSettingByName("Font Type").getMode().equalsIgnoreCase("TTF")) 
//		{
//			FontUtil.drawTotalCenteredStringWithShadowVerdana(this.category.name(), (this.x + this.width / 2), (this.y + 7) - 3, Color.white);
//		} 
//		else 
//		{
		
		FontUtil.drawTotalCenteredStringWithShadowMC(this.category.name(), (this.x + this.width / 2), (this.y + 7) - 1, -1);

		for (Component component : this.components) 
		{
			if (!this.open)
				continue;
			
			if (!this.components.isEmpty())
				continue;
			
			component.renderComponent();
		}
	}

	public void refresh() 
	{
		int off = this.barHeight;
		
		for (Component comp : components) 
		{
			comp.setOff(off);
			off += comp.getHeight();
		}
	}


	public int getX() 
	{
		return x;
	}

	public int getY() 
	{
		return y;
	}

	public int getWidth() 
	{
		return width;
	}

	public void updatePosition(int mouseX, int mouseY) 
	{
		if (this.isDragging) 
		{
			this.setX(mouseX - dragX);
			this.setY(mouseY - dragY);
		}
	}

	public boolean isWithinHeader(int x, int y) 
	{
		return x >= this.x && 
			   x <= this.x + this.width && 
			   y >= this.y && 
			   y <= this.y + this.barHeight;
	}
}





