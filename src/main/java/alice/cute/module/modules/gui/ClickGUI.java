package alice.cute.module.modules.gui;


import alice.cute.module.Module;
import alice.cute.setting.Mode;
import alice.cute.module.Category;
import alice.cute.gui.ClickUI;
import alice.cute.util.Util;

public class ClickGUI extends Module
{
	public ClickUI clickUI;

    public ClickGUI() 
    {
    	super("Click GUI", Category.CLIENT, "Provides a GUI");
    }

    @Override
    public void onEnable() 
    {
        if(this.clickUI == null) 
        {
            this.clickUI = new ClickUI();
            this.clickUI.closeOnKey = this.getKeybind().getKeyCode();
        }
        
        mc.displayGuiScreen(this.clickUI);
        
        toggle();
    }
    
    @Override
    public void onDisable()
    {
//    	this is for debug 
//    	this.clickUI = null;
    }
}
