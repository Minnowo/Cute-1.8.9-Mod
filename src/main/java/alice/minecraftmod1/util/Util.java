package alice.minecraftmod1.util;

import net.minecraft.client.Minecraft;

public class Util
{
	public static boolean nullCheck() 
	{
		return Minecraft.getMinecraft().theWorld == null || 
			   Minecraft.getMinecraft().thePlayer == null;
	}
	
	public static boolean checkRender() 
	{
		return Minecraft.getMinecraft().getRenderManager() == null || 
			   Minecraft.getMinecraft().getRenderManager().options == null;
	}
}
