package alice.cute.ui.components;

import java.awt.Color;

public class Component 
{
	protected final int height = 12;
	protected final int width  = 88;
	
	protected Color colorEnabled      = new Color(136, 50, 0);
	protected Color colorDisabled     = new Color(100, 0, 0);
	protected Color colorEnabledHover = new Color(156, 0, 100);
	protected Color colorHover        = new Color(153, 0, 0);
	protected Color textColor         = new Color(255, 255, 255);
	protected int   textColorInt      = textColor.getRGB();
	
	protected Color checkboxBackground = new Color(136, 153, 153);
	protected Color checkboxChecked    = new Color(51, 33, 33);
	
	protected Color backColor   = new Color(17, 17, 17, 140);
	protected Color sliderColor = new Color(128, 00, 0, 128);
	
	
	public void renderComponent() 
	{
	}
	
	public void updateComponent(int mouseX, int mouseY) 
	{
		
	}
	
	public void mouseClicked(int mouseX, int mouseY, int button) 
	{
		
	}
	
	public void mouseReleased(int mouseX, int mouseY, int mouseButton) 
	{
	}
	
	public int getParentHeight() 
	{
		return 0;
	}
	
	public void keyTyped(char typedChar, int key) 
	{
		
	}
	
	public void setOff(int newOff) 
	{
		
	}
	
	public int getHeight() 
	{
		return 0;
	}
}