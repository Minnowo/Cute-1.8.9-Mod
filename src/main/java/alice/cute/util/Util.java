package alice.cute.util;

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
}
