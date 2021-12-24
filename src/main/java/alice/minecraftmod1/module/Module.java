package alice.minecraftmod1.module;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import alice.minecraftmod1.Reference;
import alice.minecraftmod1.util.IMixin;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class Module implements IMixin
{
	private String     _name;
	private Category   _category;
	private String     _description;
	private KeyBinding _key;

	private boolean _enabled;
	private boolean _opened;
	private boolean _drawn;
	public boolean  _isKeyDown = false;
	private boolean _isBinding;
	public float    _remainingAnimation = 0.0f;


	public Module(String name, Category category, @Nullable String description) 
	{
		this._name = name;
		this._category = category;
		this._description = description;
		this._enabled = false;
		this._opened = false;
		this._drawn = true;

		this._key = new KeyBinding(name, Keyboard.KEY_NONE, Reference.NAME);
		ClientRegistry.registerKeyBinding(this._key);

		this.setup();
	}

	public void setup() 
	{

	}

	public void addSetting(Setting s) 
	{
		settingsList.add(s);
	}

	public boolean isEnabled() 
	{
		return this._enabled;
	}

	public boolean isKeyDown() 
	{
		return this._isKeyDown;
	}

	public void setKeyDown(boolean b) 
	{
		this._isKeyDown = b;
	}

	public void onEnable() 
	{
		this._remainingAnimation = 0.0f;

//		if (ModuleManager.getModuleByClass(EnableMessage.class).isEnabled() && 
//				!this._name.equalsIgnoreCase("ClickGUI"))
//			MessageUtil.sendClientMessage(this._name + ChatFormatting.GREEN + " ENABLED");

		MinecraftForge.EVENT_BUS.register(this);
	}

	public void onDisable() 
	{
		this._remainingAnimation = 0.0f;
		// mc.timer.tickLength = 50;
		
//		if (ModuleManager.getModuleByClass(EnableMessage.class).isEnabled() && 
//				!this._name.equalsIgnoreCase("ClickGUI"))
//			MessageUtil.sendClientMessage(this._name + ChatFormatting.RED + " DISABLED");

		MinecraftForge.EVENT_BUS.unregister(this);
	}

	public void onToggle() 
	{
		this._remainingAnimation = 0.0f;
	}

	public void onUpdate() 
	{

	}

	public void onFastUpdate() 
	{

	}

	public void onServerUpdate() 
	{

	}

	public void onValueChange() 
	{

	}

	public void toggle() 
	{
		this._enabled = !this._enabled;
		
		try 
		{
			if (this.isEnabled())
				this.onEnable();

			else
				this.onDisable();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void enable() 
	{
		if (this.isEnabled())
			return;
		
		this._enabled = true;
		
		try 
		{
			this.onEnable();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void disable() 
	{
		if (!this.isEnabled()) 
			return;
		
		this._enabled = false;
		
		try 
		{
			this.onDisable();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void setEnabled(boolean enabled) 
	{
		this._enabled = enabled;
	}

	public String getName() 
	{
		return this._name;
	}

	public Category getCategory() 
	{
		return this._category;
	}

	public String getDescription() 
	{
		return this._description;
	}

	public KeyBinding getKeybind() 
	{
		return this._key;
	}

	public String getHUDData() 
	{
		return "";
	}

//	public boolean hasSettings() 
//	{
//		return this.settingsList.size() > 0;
//	}

//	public List<Setting> getSettings(){
//		return this.settingsList;
//	}

	public void toggleState() 
	{
		this._opened = !this._opened;
	}

	public boolean isOpened() 
	{
		return this._opened;
	}

	public boolean isBinding() 
	{
		return this._isBinding;
	}

	public boolean isDrawn() 
	{
		return this._drawn;
	}

	public void setBinding(boolean b) 
	{
		this._isBinding = b;
	}

	public void setDrawn(boolean in) 
	{
		this._drawn = in;
	}

	
	public enum Category 
	{
		CLIENT  ("Client", new Color(234, 71, 71)),
		COMBAT  ("Combat", new Color(56, 103, 224)),
		PLAYER  ("Player", new Color(37, 205, 84)),
		MISC    ("Miscellaneous", new Color(122, 61, 217)),
		MOVEMENT("Movement", new Color(217, 49, 103)),
		RENDER  ("Render", new Color(231, 164, 73)),
		BOT     ("Bot", new Color(208, 68, 195));

		String _name;
		Color _color;

		Category(String name, Color color) 
		{
			this._name = name;
			this._color = color;
		}

		public String getName() 
		{
			return this._name;
		}

		public Color getColor() 
		{
			return this._color;
		}
	}
}
