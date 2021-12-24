package alice.minecraftmod1.setting;

import java.util.ArrayList;
import java.util.List;

public class Setting
{
	protected String _name;
	protected boolean _opened;
	
	protected List<SubSetting> _subs = new ArrayList();
	

	public List<SubSetting> getSubSettings()
	{
		return this._subs;
	}
	
	public boolean hasSubSettings() 
	{
		return this._subs.size() > 0;
	}
	
	public void addSub(SubSetting s) 
	{
		this._subs.add(s);
	}

	public void toggleState() 
	{
		this._opened = !this._opened;
	}
	
	public boolean isOpened() 
	{
		return this._opened;
	}
	
	public String getName() 
	{
		return this._name;
	}
	
}
