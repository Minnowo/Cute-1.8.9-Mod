package alice.minecraftmod1.managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Keyboard;

import alice.minecraftmod1.module.*;
import alice.minecraftmod1.module.Module.Category;
import alice.minecraftmod1.module.modules.*;
import alice.minecraftmod1.module.modules.render.*;
import alice.minecraftmod1.util.IMixin;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModuleManager implements IMixin
{
	public ModuleManager() 
	{
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	static List<Module> modules = Arrays.asList
			(
				new BlockESP(),
				new EntityESP(),
				new Fullbright(),
				new Tracers()
			);
	
	public static List<Module> getModules()
	{
		return new ArrayList(modules);
	}
	
	public static List<Module> getModulesInCategory(Category cat)
	{
		List<Module> module = new ArrayList();
		
		for (Module m : modules) 
		{
			if (m.getCategory().equals(cat))
				module.add(m);
		}
		
		return module;
	}
	
	public static Module getModuleByName(String name) 
	{
		return null;
		// return modules.stream().filter(module -> module.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}
	
	public static Module getModuleByClass(Class<?> clazz) 
	{
		return null;
		// return modules.stream().filter(module -> module.getClass().equals(clazz)).findFirst().orElse(null);
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void onTick(TickEvent.ClientTickEvent event) 
	{
		ModuleManager.onUpdate();
		ModuleManager.keyListen();
		// ThemeColor.updateColors();
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void onFastTick(TickEvent event) 
	{
		ModuleManager.onFastUpdate();
	}

	
	public static void onUpdate() 
	{
		for (Module m : modules) 
		{
			if (m.isEnabled())
				m.onUpdate();
		}
	}
	
	public static void onFastUpdate() 
	{
		for (Module m : modules) 
		{
			if (m.isEnabled())
				m.onFastUpdate();
		}
	}
	
	public static void onServerUpdate() 
	{
		for (Module m : modules) 
		{
			if (m.isEnabled())
				m.onServerUpdate();
		}
	}
	
	public static void keyListen() 
	{
		if (mc.currentScreen != null)
			return;
		
		for (Module m : modules) 
		{
			try 
			{
				if (Keyboard.isKeyDown(Keyboard.KEY_NONE) || Keyboard.isKeyDown(Keyboard.CHAR_NONE))
					return;
	
				if (Keyboard.isKeyDown(m.getKeybind().getKeyCode()) && !m.isKeyDown()) 
				{
					m.setKeyDown(true);
					m.toggle();
				}
	
				else if (!Keyboard.isKeyDown(m.getKeybind().getKeyCode()))
					m.setKeyDown(false);
	
			}
			catch (Exception e) 
			{
				// e.printStackTrace();
			}
		}
	}
}
