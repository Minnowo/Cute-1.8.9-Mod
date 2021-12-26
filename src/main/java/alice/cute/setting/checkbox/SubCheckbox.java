package alice.cute.setting.checkbox;

import alice.cute.setting.Setting;
import alice.cute.setting.SubSetting;
import alice.cute.setting.keybind.Keybind;
import alice.cute.setting.mode.Mode;
import alice.cute.setting.slider.Slider;

public class SubCheckbox extends SubSetting
{
	private boolean _checked;

	public SubCheckbox(Setting parent, String name, boolean checked) 
	{
		this._parent = parent;
		this._name = name;
		this._checked = checked;
		
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
	
	public boolean getValue() 
	{
		return this._checked;
	}

	public void setChecked(boolean newValue) 
	{
		this._checked = newValue;
	}
	
	public void toggleValue() 
	{
		this._checked = !this._checked;
	}
}
