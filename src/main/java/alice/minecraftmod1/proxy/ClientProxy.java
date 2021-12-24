package alice.minecraftmod1.proxy;

import alice.minecraftmod1.xray.BlockESP;
import alice.minecraftmod1.xray.ESP;
import alice.minecraftmod1.xray.EntityESP;
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
        super.preInit(e);
    }

    @Override
    public void init(FMLInitializationEvent e) 
    {
        super.init(e);
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) 
    {
        super.postInit(e);
    }
}
