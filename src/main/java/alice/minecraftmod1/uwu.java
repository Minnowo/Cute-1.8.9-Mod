package alice.minecraftmod1;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;
import alice.minecraftmod1.init.ModItems;
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
	
	
	@EventHandler()
	public static void preInit(FMLPreInitializationEvent event) 
	{
		if(event.getSide() == Side.SERVER)
			return;
		
		ModItems.init();
		ModItems.register();
		
		XRay.instance.preInit(event);
	}
	
	@EventHandler()
	public static void init(FMLInitializationEvent event) 
	{
		if(event.getSide() == Side.SERVER)
			return;
		
		proxy.registerRenders();
		XRay.instance.init(event);
		
		System.out.println(Reference.NAME + " init");
	}
	
	@EventHandler()
	public static void postInit(FMLPostInitializationEvent event) 
	{
		if(event.getSide() == Side.SERVER)
			return;
		
		XRay.instance.postIniit(event);
		System.out.println("post init done");
	}
}
