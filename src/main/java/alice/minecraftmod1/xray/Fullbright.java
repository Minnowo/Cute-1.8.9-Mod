package alice.minecraftmod1.xray;

import net.minecraft.client.Minecraft;

public class Fullbright
{
	// can be high or low, i think 20 is the max in game brightness you can get 
	public static int MaxFullbright = 20;
		
	private static boolean FullbrightEnabled = Minecraft.getMinecraft().gameSettings.gammaSetting > 1.0; // 1 is the max you can set in game	
	
	public static boolean IsEnabled() 
	{
		return FullbrightEnabled;
	}
	
	public static void Enable(boolean fbs) 
	{
		FullbrightEnabled = fbs;
		
		if(FullbrightEnabled) 
		{
			Minecraft.getMinecraft().gameSettings.gammaSetting = MaxFullbright;
		}
		else 
		{
			Minecraft.getMinecraft().gameSettings.gammaSetting = 1;
		}
	}
	
	public static void ToggleFullbright() 
	{
		Enable(!FullbrightEnabled);
	}
}
