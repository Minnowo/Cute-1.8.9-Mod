package alice.minecraftmod1;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.input.Keyboard;

import alice.minecraftmod1.managers.ModuleManager;
import alice.minecraftmod1.module.Module;
import alice.minecraftmod1.module.modules.render.VirtualBlock;
import alice.minecraftmod1.proxy.*;
import alice.minecraftmod1.xray.BlockESP;

@Mod
(
		modid = Reference.MODID, 
		name = Reference.NAME, 
		version = Reference.VERSION
)

public class uwu
{
	@SidedProxy
	(
		serverSide = Reference.SERVER_PROXY_CLASS,
		clientSide = Reference.CLIENT_PROXY_CLASS
	)
	public static CommonProxy proxy;
	
	public static ModuleManager moduleManager;
	
	@Mod.Instance("um1")
	public static uwu instance;
	
	@SideOnly(Side.CLIENT)
	@EventHandler()
	public static void preInit(FMLPreInitializationEvent event) 
	{
		proxy.preInit(event);
	}
	
	@SideOnly(Side.CLIENT)
	@EventHandler()
	public static void init(FMLInitializationEvent event) 
	{
		proxy.init(event);
		proxy.registerRenders();	
		moduleManager = new ModuleManager();
		
		VirtualBlock.setStandardList();
		
		moduleManager.getModules().get(0).getKeybind().setKeyCode(Keyboard.KEY_1);
		moduleManager.getModules().get(1).getKeybind().setKeyCode(Keyboard.KEY_2);
		moduleManager.getModules().get(2).getKeybind().setKeyCode(Keyboard.KEY_3);
		
		System.out.println(Reference.NAME + " init");
	}
	
	@SideOnly(Side.CLIENT)
	@EventHandler()
	public static void postInit(FMLPostInitializationEvent event) 
	{
		proxy.postInit(event);
		System.out.println("post init done");
	}
}
