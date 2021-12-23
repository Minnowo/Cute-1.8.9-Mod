package alice.minecraftmod1.proxy;

import alice.minecraftmod1.init.ModItems;

public class ClientProxy extends CommonProxy
{
		
	@Override
	public void registerRenders()
	{
		ModItems.registerRenders();
	}
	
}
