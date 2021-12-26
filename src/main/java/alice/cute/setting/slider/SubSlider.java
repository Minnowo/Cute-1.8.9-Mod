package alice.cute.setting.slider;

import alice.cute.setting.Setting;
import alice.cute.setting.SubSetting;
import alice.cute.setting.checkbox.Checkbox;
import alice.cute.setting.keybind.Keybind;
import alice.cute.setting.mode.Mode;

public class SubSlider extends SubSetting
{
	private double _min;
	private double _value;
	private double _max;
	private int    _scale;

	public SubSlider(Setting parent, String name, double min, double value, double max, int scale) 
	{
		this._parent = parent;
		this._name = name;
		this._min = min;
		this._value = scale == 0 ? (int) value : value;
		this._max = max;
		this._scale = scale;
		
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
	
	public int getRoundingScale() 
	{
		return this._scale;
	}
	
	public double getValue() 
	{
		return this._value;
	}
	
	public double getMaxValue() 
	{
		return this._max;
	}
	
	public double getMinValue() 
	{
		return this._min;
	}
	
	public void setValue(double value) 
	{
		this._value = value;
	}
}
