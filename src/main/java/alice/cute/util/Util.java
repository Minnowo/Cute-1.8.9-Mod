package alice.cute.util;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class Util
{
	private static String playerName = Minecraft.getMinecraft().thePlayer.getName();
	
	public static String playerName() 
	{
		return playerName;
	}
	
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
	
	public static void sendRawClientMessage(String message) 
	{
		Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(new ChatComponentText(message), 69);
	}
	
	public static Color getHealthColor(float health) 
    {
        if (health <= 4)
            return new Color(200, 0, 0);
        
        if (health <= 8)
            return new Color(231, 143, 85);
        
        if (health <= 12)
            return new Color(219, 201, 106);
        
        if (health <= 16)
            return new Color(117, 231, 85);
        
        return new Color(44, 186, 19);
    }
}
