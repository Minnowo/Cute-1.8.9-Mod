package alice.minecraftmod1.setting.checkbox;

import alice.minecraftmod1.setting.Setting;

public class Checkbox extends Setting
{
	private boolean _checked;

	public Checkbox(String name, boolean checked) 
	{
		this._name = name;
		this._checked = checked;
		this._opened = false;
	}
	
	public void toggleValue() 
	{
		this._checked = !this._checked;
	}
	
	public String getName() 
	{
		return this._name;
	}

	public boolean getValue() 
	{
		return this._checked;
	}

	public void setChecked(boolean newValue) 
	{
		this._checked = newValue;
	}
}
