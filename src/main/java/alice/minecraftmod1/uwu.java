package alice.minecraftmod1;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import alice.minecraftmod1.proxy.*;
import alice.minecraftmod1.xray.XRay;

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
