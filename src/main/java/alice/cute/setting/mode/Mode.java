package alice.cute.setting.mode;

import java.util.ArrayList;
import java.util.List;

import alice.cute.setting.*;

public class Mode extends Setting
{
	private String[] _modes;
	private int _mode;
		
	public Mode(String name, String... modes) 
	{
		this._name = name;
		this._modes = modes;
		this._opened = false;
		this._settingType = SettingType.MODE;
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
