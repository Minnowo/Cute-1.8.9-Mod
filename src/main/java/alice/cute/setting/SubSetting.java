package alice.cute.setting;

public class SubSetting
{
	protected Setting  _parent;
	protected String   _name;

	public String getName() 
	{
		return this._name;
	}
	
	public Setting getParent() 
	{
		return this._parent;
	}
}
