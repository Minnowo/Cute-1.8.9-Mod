package alice.minecraftmod1.xray;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import alice.minecraftmod1.Reference;
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
import util.render.ESPUtil;

public class ESP
{
	public static ESP instance = new ESP();
	
	// is entity esp enabled 
	public static boolean Enabled = false;
	
	
	// search radius 
	public static int Radius = 45;
	public static int DisplayListId = 0; // no idea what this is lmao
	
	// the number of ticks until refreshing the blocks
	public static int CooldownTicks = 80;  
	private static int _cooldownTicks = 0; // the internal counter for the ticks 
	
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
		
		// register with event bus so we can register listeners 
		FMLCommonHandler.instance().bus().register(this);
		MinecraftForge.EVENT_BUS.register(this);
		
//		DisplayListId = GL11.glGenLists(GL11.GL_TRIANGLE_STRIP) + 3;
		
		// i really have no idea what this does but its very important 
		// DisplayListId = GL11.glGenLists(GL11.GL_TRIANGLE_STRIP) + 3;
	}
	
	@SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) 
	{
        if (this.mc.theWorld == null || this.mc.thePlayer == null)
            return;

        if (mc.getRenderManager() == null || mc.getRenderManager().options == null)
            return;

        
        GL11.glPushMatrix();
        /* 110 */       GL11.glDisable(3553);
        /* 111 */       GL11.glEnable(3042);
        /* 112 */       GL11.glBlendFunc(770, 771);
        /* 113 */       GL11.glDisable(2929);
        /* 114 */       GL11.glDepthMask(false);
        /* 115 */       GL11.glEnable(2848);
        /* 116 */       GL11.glLineWidth(2.0F);
        ESPUtil.setColor(new Color(44, 186, 19));
        
        for(Object entity : this.mc.theWorld.loadedEntityList) 
		{
			Entity e = (Entity)entity;
			
			if(e instanceof EntityPlayer)
				continue;
			
			double renderPosX = mc.getRenderManager().viewerPosX;
			double renderPosY = mc.getRenderManager().viewerPosY;
			double renderPosZ = mc.getRenderManager().viewerPosZ;
			
			System.out.println(e.getName());
			double x1 = e.posX + e.width - renderPosX;
/* 152 */         double x2 = e.posX - e.width - renderPosX;
/*     */         
/* 154 */         double y1 = e.posY - renderPosY;
/* 155 */         double y2 = e.posY + e.height - renderPosY;
/*     */         
/* 157 */         double z1 = e.posZ + e.width - renderPosZ;
/* 158 */         double z2 = e.posZ - e.width - renderPosZ;
/*     */         
/* 160 */         GL11.glBegin(2);
/*     */         
/* 162 */         GL11.glVertex3d(x1, y1, z1);
/* 163 */         GL11.glVertex3d(x2, y1, z1);
/* 164 */         GL11.glVertex3d(x2, y1, z2);
/* 165 */         GL11.glVertex3d(x1, y1, z2);
/*     */         
/* 167 */         GL11.glEnd();
/*     */         
/* 169 */         GL11.glBegin(1);
/*     */         
/* 171 */         GL11.glVertex3d(x1, y1, z1);
/* 172 */         GL11.glVertex3d(x1, y2, z1);
/*     */         
/* 174 */         GL11.glVertex3d(x1, y1, z2);
/* 175 */         GL11.glVertex3d(x1, y2, z2);
/*     */         
/* 177 */         GL11.glVertex3d(x2, y1, z1);
/* 178 */         GL11.glVertex3d(x2, y2, z1);
/*     */         
/* 180 */         GL11.glVertex3d(x2, y1, z2);
/* 181 */         GL11.glVertex3d(x2, y2, z2);
/*     */         
/* 183 */         GL11.glEnd();
/*     */         
/* 185 */         GL11.glBegin(2);
/*     */         
/* 187 */         GL11.glVertex3d(x1, y2, z1);
/* 188 */         GL11.glVertex3d(x2, y2, z1);
/* 189 */         GL11.glVertex3d(x2, y2, z2);
/* 190 */         GL11.glVertex3d(x1, y2, z2);
/*     */         
/* 192 */         GL11.glEnd();

//	        GL11.glDisable(GL11.GL_TEXTURE_2D);
//	        GL11.glDisable(GL11.GL_DEPTH_TEST);
//	        GL11.glEnable(GL11.GL_BLEND);
//	        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//	        GL11.glDepthMask(false);
            
//			glBegin(2);
//	        glVertex2d(-e.width, 0);
//	        glVertex2d(-e.width, e.height);
//	        glEnd();
//	        glBegin(2);
//	        glVertex2d(-e.width, 0);
//	        glVertex2d(e.width, 0);
//	        glEnd();
//	        glBegin(2);
//	        glVertex2d(e.width, 0);
//	        glVertex2d(e.width, e.height);
//	        glEnd();
//	        glBegin(2);
//	        glVertex2d(-e.width, e.height);
//	        glVertex2d(e.width, e.height);
//	        glEnd();
////	        
			
			
			
			
			
//	        glBegin(GL_POLYGON);
//            ESPUtil.setColor(new Color(44, 186, 19));
//            glVertex2d(-e.width - 0.05, 0);
//            glVertex2d(-e.width - 0.075, 0);
//            glVertex2d(-e.width - 0.075, (e.height / 20) * 20);
//            glVertex2d(-e.width - 0.05, (e.height / 20) * 20);
//            glEnd();
//	        
//	        if (e instanceof EntityPlayer) 
//	        {
//	            glBegin(GL_POLYGON);
//	            ESPUtil.setColor(new Color(44, 186, 19));
//	            glVertex2d(-e.width - 0.05, 0);
//	            glVertex2d(-e.width - 0.075, 0);
//	            glVertex2d(-e.width - 0.075, (e.height / 20) * 20);
//	            glVertex2d(-e.width - 0.05, (e.height / 20) * 20);
//	            glEnd();
//
//	            glBegin(GL_POLYGON);
//	            ESPUtil.setColor(new Color(232, 188, 65));
//	            glVertex2d(-e.width - 0.1, 0);
//	            glVertex2d(-e.width - 0.125, 0);
//	            glVertex2d(-e.width - 0.125, (e.height / 16) * ((EntityPlayer) e).getAbsorptionAmount());
//	            glVertex2d(-e.width - 0.1, (e.height / 16) * ((EntityPlayer) e).getAbsorptionAmount());
//	            glEnd();
//	        }
//			
//	        GL11.glDepthMask(true);
//	        GL11.glEnable(GL11.GL_TEXTURE_2D);
//	        GL11.glEnable(GL11.GL_DEPTH_TEST);
//	        GL11.glDisable(GL11.GL_BLEND);
		}
        GL11.glDisable(3042);
        /* 196 */       GL11.glEnable(3553);
        /* 197 */       GL11.glEnable(2929);
        /* 198 */       GL11.glDepthMask(true);
        /* 199 */       GL11.glDisable(2848);
        /* 200 */       GL11.glPopMatrix();
        			
    }
}
