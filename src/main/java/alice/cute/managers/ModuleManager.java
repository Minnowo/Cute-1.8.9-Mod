package alice.cute.managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Keyboard;

import alice.cute.module.Category;
import alice.cute.module.Module;
import alice.cute.module.modules.gui.ClickGUI;
import alice.cute.module.modules.misc.AntiPotion;
import alice.cute.module.modules.render.BlockESP;
import alice.cute.module.modules.render.EntityESP;
import alice.cute.module.modules.render.Fullbright;
import alice.cute.module.modules.render.NoRender;
import alice.cute.module.modules.render.ProjectileTracer;
import alice.cute.module.modules.render.Tracers;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class ModuleManager extends ManagerBase
{	
	public ModuleManager() 
	{
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	public static List<Module> modules = Arrays.asList
				(
					new ClickGUI(),
					
					new BlockESP(),
					new EntityESP(),
					new Fullbright(),
					new Tracers(),
					new ProjectileTracer(),
					new NoRender(),
					
					new AntiPotion()
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
	@SubscribeEvent(priority=EventPriority.HIGHEST, receiveCanceled=true)
	public void onTick(TickEvent.ClientTickEvent event) 
	{
		ModuleManager.onUpdate();
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
	
	
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void keyboardEvent(InputEvent.KeyInputEvent key) 
	{
		if (mc.currentScreen instanceof GuiScreen)
			return;
		
		for (Module m : modules) 
		{
			try 
			{
				if (Keyboard.isKeyDown(m.getKeybind().getKeyCode()) && !m.isKeyDown()) 
				{
					m.setKeyDown(true);
					m.toggle();
				}
				else 
				{
					m.setKeyDown(false);
				}
			}
			catch (Exception e) 
			{
				 e.printStackTrace();
			}
		}
	}
//	
//	public static void keyListen() 
//	{
//		if (mc.currentScreen instanceof GuiScreen)
//			return;
//		
//		for (Module m : modules) 
//		{
//			try 
//			{
//				if (Keyboard.isKeyDown(Keyboard.KEY_NONE) || Keyboard.isKeyDown(Keyboard.CHAR_NONE))
//					return;
//				
//				if (Keyboard.isKeyDown(m.getKeybind().getKeyCode()) && !m.isKeyDown()) 
//				{
//					m.setKeyDown(true);
//					m.toggle();
//				}
//	
//				else if (!Keyboard.isKeyDown(m.getKeybind().getKeyCode()))
//					m.setKeyDown(false);
//	
//			}
//			catch (Exception e) 
//			{
//				 e.printStackTrace();
//			}
//		}
//	}
}
