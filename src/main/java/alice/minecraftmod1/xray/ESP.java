package alice.minecraftmod1.xray;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import alice.minecraftmod1.Reference;
import alice.minecraftmod1.util.Util;
import alice.minecraftmod1.util.render.ESPUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ESP
{
	public static ESP instance = new ESP();
	
	// is entity esp enabled 
	public static boolean Enabled = false;
	
	// setup keybindings
	private KeyBinding _toggleEntityESP= new KeyBinding("Toggle Fullbright", Keyboard.KEY_7, Reference.NAME);
	
	private static Minecraft mc;
	
	
	public void preInit(FMLPreInitializationEvent event) 
	{
		
	}
	
	
	public void postIniit(FMLPostInitializationEvent event) 
	{
		
	}
	
	
	public void init(FMLInitializationEvent event) 
	{
		// set our game instance
		this.mc = Minecraft.getMinecraft();
//		
//		// register with event bus so we can register listeners 
//		FMLCommonHandler.instance().bus().register(this);
//		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) 
	{
        if (Util.nullCheck() || Util.checkRender())
            return;
		
        ESPUtil.beginRenderHitbox(2f);
        
        for(Object entity : this.mc.theWorld.loadedEntityList) 
		{
			Entity e = (Entity)entity;
			
			if(e instanceof EntityPlayer)
				continue;
			
			// ESPUtil.renderEntityHitbox(e);
		}
        
        ESPUtil.endRenderHitbox();
        		
    }
}
