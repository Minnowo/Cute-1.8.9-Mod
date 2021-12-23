package alice.minecraftmod1.proxy;

import alice.minecraftmod1.xray.XRay;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenders()
	{
		
	}
	
	@Override
    public void preInit(FMLPreInitializationEvent e) 
	{
		XRay.instance.preInit(e);
        super.preInit(e);
    }

    @Override
    public void init(FMLInitializationEvent e) 
    {
    	XRay.instance.init(e);
        super.init(e);
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) 
    {
    	XRay.instance.postIniit(e);
        super.postInit(e);
    }
}
