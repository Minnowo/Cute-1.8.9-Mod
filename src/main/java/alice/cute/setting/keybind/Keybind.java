package alice.cute.setting.keybind;

import alice.cute.setting.Setting;

public class Keybind extends Setting
{
	    private int _key;
	    private boolean _binding;

	    public Keybind(String name, int key) 
	    {
	        this._name = name;
	        this._key = key;
	        this._opened = false;
	        this._binding = false;
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
