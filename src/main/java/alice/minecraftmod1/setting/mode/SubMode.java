package alice.minecraftmod1.setting.mode;

import alice.minecraftmod1.setting.Setting;
import alice.minecraftmod1.setting.SubSetting;
import alice.minecraftmod1.setting.checkbox.Checkbox;
import alice.minecraftmod1.setting.keybind.Keybind;
import alice.minecraftmod1.setting.slider.Slider;

public class SubMode extends SubSetting
{
	private String[] _modes;
	private int      _mode;

	public SubMode(Setting parent, String name, String... modes) 
	{
		this._parent = parent;
		this._name = name;
		this._modes = modes;
		
		if (parent instanceof Checkbox) 
		{
			Checkbox p = (Checkbox) parent;
			p.addSub(this);
		}

		else if (parent instanceof Mode) 
		{
			Mode p = (Mode) parent;
			p.addSub(this);
		}

		else if (parent instanceof Slider) 
		{
			Slider p = (Slider) parent;
			p.addSub(this);
		}

		else if (parent instanceof Keybind) 
		{
			Keybind p = (Keybind) parent;
			p.addSub(this);
		}
	}
	
	
	public String getMode(int modeIndex) 
	{
		return this._modes[modeIndex];
	}
	
	public void setMode(int mode) 
	{
		this._mode = mode;
	}
	
	public int getValue() 
	{
		return this._mode;
	}
	
	public int nextMode() 
	{
		return this._mode + 1 >= this._modes.length ? 0 : this._mode + 1;
	}
}
