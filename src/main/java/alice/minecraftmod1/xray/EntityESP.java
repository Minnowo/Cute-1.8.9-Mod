package alice.minecraftmod1.xray;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import alice.minecraftmod1.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityESP
{
	public static EntityESP instance = new EntityESP();
	
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
		
		DisplayListId = GL11.glGenLists(GL11.GL_TRIANGLE_STRIP) + 3;
		
		// i really have no idea what this does but its very important 
		// DisplayListId = GL11.glGenLists(GL11.GL_TRIANGLE_STRIP) + 3;
	}
	
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void keyboardEvent(InputEvent.KeyInputEvent key) 
	{
		// if there is a gui shown ignore keyinput 
		if(this.mc.currentScreen instanceof GuiScreen)
			return;
		
		// xray bind pressed 
		if(this._toggleEntityESP.isPressed()) 
		{
			this.Enabled = !(this.Enabled);
			this._cooldownTicks = 0;
			
			if(!this.Enabled) 
			{     
				// GL11.glDeleteLists(DisplayListId, 1);
			} 
			System.out.println("ENtity esp has been toggled");
		}
	}
	
	
	public float lastFrame = 0;
	
	@SubscribeEvent
    public void onRender(TickEvent.RenderTickEvent e) 
	{
		if(this.Enabled)
			compileDL();
		
//		double frameTime = Math.min(((float)System.currentTimeMillis() - this.lastFrame / 5), 1.0D);
//		this.lastFrame = System.currentTimeMillis();
//		
//		GL11.glPushMatrix();
//		GL11.glDisable(3553);
//		GL11.glEnable(3042);
//		GL11.glBlendFunc(770,  771);
//		GL11.glDisable(2929);
//		GL11.glDepthMask(false);
//		GL11.glEnable(2848);
//		GL11.glLineWidth(2.0f);
//		GL11.glColor4f(1, 1, 1, 1);
//		
//		double renderPosX = this.mc.getRenderManager().viewerPosX;
//		double renderPosY = this.mc.getRenderManager().viewerPosY;
//		double renderPosZ = this.mc.getRenderManager().viewerPosZ;
//		
		
    }
	
	private void compileDL() 
	{
		// GL11.glNewList(DisplayListId, GL11.GL_COMPILE);
		
		
        
        for(Object entity : this.mc.theWorld.loadedEntityList) 
		{
			Entity e = (Entity)entity;
    			
			
			GL11.glNewList(DisplayListId, GL11.GL_COMPILE);
			GL11.glBlendFunc(770, 771);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glLineWidth(2.0F);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(false);

			GL11.glColor4d(1, 1, 1, 1);
			
			GL11.glVertex3d(e.posX + 1, e.posY + 1, e.posZ);
			GL11.glVertex3d(e.posX + 1, e.posY + 1, e.posZ + 1);
			
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(true);
			GL11.glEndList();
			//	        
//	        GL11.glColor4d(1, 1, 1, 1);
//			
			// Minecraft.getMinecraft().getRenderManager();
			// AxisAlignedBB eb = e.getEntityBoundingBox();
			
			if(e instanceof EntityPlayer)
				continue;
			
			System.out.println(e.getName());
			System.out.println(e.posX);
			System.out.println(e.posY);
			System.out.println(e.posZ);
			return;
			// RenderGlobal.drawSelectionBoundingBox(eb);
			
//			AxisAlignedBB bb = new AxisAlignedBB(
//					eb.minX - 0.05 - e.posX + (e.posX - mc.getRenderManager().viewerPosX), 
//					eb.minY -        e.posY + (e.posY - mc.getRenderManager().viewerPosY), 
//					eb.minZ - 0.05 - e.posZ + (e.posZ - mc.getRenderManager().viewerPosZ), 
//					eb.maxX + 0.05 - e.posX + (e.posX - mc.getRenderManager().viewerPosX), 
//					eb.maxY + 0.1  - e.posY + (e.posY - mc.getRenderManager().viewerPosY), 
//					eb.maxZ + 0.05 - e.posZ + (e.posZ - mc.getRenderManager().viewerPosZ)
//					);
//			
//			RenderGlobal.drawOutlinedBoundingBox(bb, 255, 255, 255, 255);
//			
//			GL11.glDepthMask(true);
//	        GL11.glEnable(GL11.GL_TEXTURE_2D);
//	        GL11.glEnable(GL11.GL_DEPTH_TEST);
//	        GL11.glDisable(GL11.GL_BLEND);
    			// System.out.println(e.getName());
    			
    			 // entityESPBox((Entity)entity, 0);
    			
//    			if(entity instanceof EntityPlayer && !((Entity)entity).getName().equals(Minecraft.getMinecraft().getSession().getUsername()))
//    				RenderUtils.entityESPBox((Entity)entity, Client.wurst.friends
//    					.contains(((EntityPlayer)entity).getName()) ? 1 : 0);
		}
        

        // GL11.glEnd();
        
        // GL11.glEndList();
        
//		for(Object entity : this.mc.theWorld.loadedEntityList) 
//		{
//			Entity e = (Entity)entity;
//			
//			// System.out.println(e.getName());
//			
//			 entityESPBox((Entity)entity, 0);
//			
////			if(entity instanceof EntityPlayer && !((Entity)entity).getName().equals(Minecraft.getMinecraft().getSession().getUsername()))
////				RenderUtils.entityESPBox((Entity)entity, Client.wurst.friends
////					.contains(((EntityPlayer)entity).getName()) ? 1 : 0);
//		}
	}
//	
	public static void entityESPBox(Entity entity, int mode)
	{
		GL11.glBlendFunc(770, 771);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glLineWidth(2.0F);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		
//		GL11.glNewList(DisplayListId, GL11.GL_COMPILE);
//		
//        GL11.glDisable(GL11.GL_TEXTURE_2D);
//        GL11.glDisable(GL11.GL_DEPTH_TEST);
//        GL11.glEnable(GL11.GL_BLEND);
//        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//        GL11.glDepthMask(false);
//        GL11.glBegin(GL11.GL_ONE);
		
        
		if(mode == 0)// Enemy
			GL11.glColor4d(
				1 - Minecraft.getMinecraft().thePlayer
					.getDistanceToEntity(entity) / 40,
				Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity) / 40,
				0, 0.5F);
		
		else if(mode == 1)// Friend
			GL11.glColor4d(0, 0, 1, 0.5F);
		
		else if(mode == 2)// Other
			GL11.glColor4d(1, 1, 0, 0.5F);
		
		else if(mode == 3)// Target
			GL11.glColor4d(1, 0, 0, 0.5F);
		
		else if(mode == 4)// Team
			GL11.glColor4d(0, 1, 0, 0.5F);
		
		Minecraft.getMinecraft().getRenderManager();
		
		AxisAlignedBB eb = entity.getEntityBoundingBox();
		
		RenderGlobal.drawSelectionBoundingBox(eb);
		
		// GL11.glColor4ub((byte) block.r, (byte) block.g, (byte) block.b, (byte) block.a);

        GL11.glVertex3f((float)entity.posX, (float)entity.posY, (float)entity.posZ);
        GL11.glVertex3f((float)(entity.posX + 1), (float)entity.posY, (float)entity.posZ);

//        GL11.glVertex3f(x + 1, y, z);
//        GL11.glVertex3f(x + 1, y, z + 1);
//
//        GL11.glVertex3f(x, y, z);
//        GL11.glVertex3f(x, y, z + 1);
//
//        GL11.glVertex3f(x, y, z + 1);
//        GL11.glVertex3f(x + 1, y, z + 1);
//
//        GL11.glVertex3f(x, y + 1, z);
//        GL11.glVertex3f(x + 1, y + 1, z);
//
//        GL11.glVertex3f(x + 1, y + 1, z);
//        GL11.glVertex3f(x + 1, y + 1, z + 1);
//
//        GL11.glVertex3f(x, y + 1, z);
//        GL11.glVertex3f(x, y + 1, z + 1);
//
//        GL11.glVertex3f(x, y + 1, z + 1);
//        GL11.glVertex3f(x + 1, y + 1, z + 1);
//
//        GL11.glVertex3f(x, y, z);
//        GL11.glVertex3f(x, y + 1, z);
//
//        GL11.glVertex3f(x, y, z + 1);
//        GL11.glVertex3f(x, y + 1, z + 1);
//
//        GL11.glVertex3f(x + 1, y, z);
//        GL11.glVertex3f(x + 1, y + 1, z);
//
//        GL11.glVertex3f(x + 1, y, z + 1);
//        GL11.glVertex3f(x + 1, y + 1, z + 1);
		
		RenderGlobal.drawOutlinedBoundingBox(new AxisAlignedBB
			(
				entity.getEntityBoundingBox().minX
					- 0.05
					- entity.posX
					+ (entity.posX), // - Minecraft.getMinecraft().getRenderManager().renderPosX),
				entity.getEntityBoundingBox().minY
					- entity.posY
					+ (entity.posY), // - Minecraft.getMinecraft().getRenderManager().renderPosY),
				entity.getEntityBoundingBox().minZ
					- 0.05
					- entity.posZ
					+ (entity.posZ), // - Minecraft.getMinecraft().getRenderManager().renderPosZ),
				entity.getEntityBoundingBox().maxX
					+ 0.05
					- entity.posX
					+ (entity.posX), // - Minecraft.getMinecraft().getRenderManager().renderPosX),
				entity.getEntityBoundingBox().maxY
					+ 0.1
					- entity.posY
					+ (entity.posY), // - Minecraft.getMinecraft().getRenderManager().renderPosY),
				entity.getEntityBoundingBox().maxZ
					+ 0.05
					- entity.posZ
					+ (entity.posZ) // - Minecraft.getMinecraft().getRenderManager().renderPosZ)
			), -1, -1,-1,-1);
		
//		GL11.glEnd();
//        GL11.glDepthMask(true);
//        GL11.glEnable(GL11.GL_TEXTURE_2D);
//        GL11.glEnable(GL11.GL_DEPTH_TEST);
//        GL11.glDisable(GL11.GL_BLEND);
//        GL11.glEndList();
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
	}
}
