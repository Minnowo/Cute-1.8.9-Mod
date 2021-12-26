package alice.cute.setting.keybind;

import alice.cute.setting.Setting;
import alice.cute.setting.SubSetting;
import alice.cute.setting.checkbox.Checkbox;
import alice.cute.setting.mode.Mode;
import alice.cute.setting.slider.Slider;

public class SubKeybind extends SubSetting
{
	    private int _key;
	    private boolean _binding;

	    public SubKeybind(Setting parent, String name, int key) 
	    {
	        this._parent = parent;
	        this._name = name;
	        this._key = key;
	        this._binding = false;

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

	    public int getKey() 
	    {
	        return this._key;
	    }

	    public void setKey(int key) 
	    {
	        this._key = key;
	    }

	    public boolean isBinding() 
	    {
	        return this._binding;
	    }

	    public void setBinding(boolean in) 
	    {
	        this._binding = in;
	    }
}
