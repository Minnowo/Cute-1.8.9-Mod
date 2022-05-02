package alice.cute;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.input.Keyboard;

import alice.cute.managers.ConfigManagerJSON;
import alice.cute.managers.ModuleManager;
import alice.cute.module.Module;
import alice.cute.module.modules.render.VirtualBlock;
import alice.cute.proxy.*;
import alice.cute.util.Util;

@Mod
(
		clientSideOnly = true,
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
	
	@Mod.Instance(Reference.MODID)
	public static uwu instance;
	
	public uwu() 
	{
		instance = this;
	}
	
	@SideOnly(Side.CLIENT)
	@EventHandler()
	public void preInit(FMLPreInitializationEvent event) 
	{
		proxy.preInit(event);
	}
	
	@SideOnly(Side.CLIENT)
	@EventHandler()
	public void init(FMLInitializationEvent event) 
	{
		proxy.init(event);
//		VirtualBlock.setStandardList();
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
		{
			@Override
			public void run()
			{
			    ConfigManagerJSON.saveConfig();
			}
		}));
		
		moduleManager = new ModuleManager();
		ConfigManagerJSON.loadConfig();
		
//		entity esp 
//		moduleManager.modules.get(1).setKeyCode(Keyboard.KEY_1);
	}
	
	@SideOnly(Side.CLIENT)
	@EventHandler()
	public void postInit(FMLPostInitializationEvent event) 
	{
		proxy.postInit(event);		
	}
}
