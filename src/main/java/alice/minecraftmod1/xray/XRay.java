package alice.minecraftmod1.xray;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import alice.minecraftmod1.Keybinds;
import alice.minecraftmod1.Reference;
import alice.minecraftmod1.uwu;
import alice.minecraftmod1.proxy.CommonProxy;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;


public class XRay
{
	public static XRay instance = new XRay();
	
	// is xray enabled
	public static boolean Enabled = false;
	
	// regresh the block outlines every x ticks 
	public static boolean IntervalRefresh = true;
	
	// xray radius 
	public static int Radius = 45;
	public static int DisplayListId = 0; // no idea what this is lmao
	
	// the number of ticks until refreshing the blocks
	public static int CooldownTicks = 80;  
	private static int _cooldownTicks = 0; // the internal counter for the ticks 
	
	// setup keybindings 
	private KeyBinding _toggleXRayBind = new KeyBinding("Toggle Xray", Keyboard.KEY_0, Reference.NAME); 
	private KeyBinding _toggleXRayGUIBind = new KeyBinding("Toggle Xray GUI", Keyboard.KEY_9, Reference.NAME);
	
	// minecraft instance singleton 
	private Minecraft mc;
	
	@EventHandler()
	public void preInit(FMLPreInitializationEvent event) 
	{
		Keybinds.RegisterKeybind(_toggleXRayBind);
		Keybinds.RegisterKeybind(_toggleXRayGUIBind);
	}
	
	@EventHandler()
	public void postIniit(FMLPostInitializationEvent event) 
	{
	}
	
	@EventHandler()
	public void init(FMLInitializationEvent event) 
	{
		// set our game instance
		this.mc = Minecraft.getMinecraft();
		
		// register with event bus so we can register listeners 
		FMLCommonHandler.instance().bus().register(this);
		MinecraftForge.EVENT_BUS.register(this);
		
		// i really have no idea what this does but its very important 
		DisplayListId = GL11.glGenLists(GL11.GL_TRIANGLE_STRIP) + 3;
		
		// init the standard xray blocks 
		XRayBlock.setStandardList();
		XRayBlock.removeInvalidBlocks();
	}
	
	@SubscribeEvent
	public boolean onTickInGame(TickEvent.ClientTickEvent e) 
	{
		if(!this.Enabled)
		{
			return false;
		}
		
		if(this._cooldownTicks < 1) 
		{
			this.compileDL();
			this._cooldownTicks = this.CooldownTicks;
		}
		
		if(!this.IntervalRefresh) 
		{
			return true;
		}
		
		this._cooldownTicks--;
		return true;
	}
	
	@SubscribeEvent
	public void keyboardEvent(InputEvent.KeyInputEvent key) 
	{
		// if there is a gui shown ignore keyinput 
		if(this.mc.currentScreen instanceof GuiScreen)
			return;
		
		// xray bind pressed 
		if(this._toggleXRayBind.isPressed()) 
		{
			this.Enabled = !(this.Enabled);
			this._cooldownTicks = 0;
			
			if(!this.Enabled) 
			{     
				GL11.glDeleteLists(DisplayListId, 1);
			} 
			System.out.println("XRay has been toggled");
		}
		
		if(this._toggleXRayGUIBind.isPressed()) 
		{
			System.out.println("XRay GUI has been toggled");
		}
	}
	
	private void compileDL() 
	{
		// i have no idea what this is but i changed all the numbers to make more sense at least
		// https://javadoc.lwjgl.org/constant-values.html#org.lwjgl.opengl.GL11.GL_2_BYTES
		
		GL11.glNewList(DisplayListId, GL11.GL_COMPILE);
		
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
        GL11.glBegin(GL11.GL_ONE);
        
        WorldClient world = this.mc.theWorld;

        EntityPlayerSP player = this.mc.thePlayer;
        
        if ((world == null) || (player == null))
            return;
        
        int x = (int)player.posX;
        int z = (int)player.posZ;
        
        // x 
        for (int i = x - this.Radius; i <= x + this.Radius; ++i) 
        {
        	// z
            for (int j = z - this.Radius; j <= z + this.Radius; ++j) 
            {
            	int height = world.getHeight(new BlockPos(i, 0, j)).getY();
                
                Block bId;
                
                // y
                for (int k = 0; k <= height; ++k)
                //for (int k = (int) player.posY - this.Radius; k <= (int) player.posY + this.Radius; ++k) 
                {
                    BlockPos blockPos = new BlockPos(i, k, j);
					IBlockState blockState = world.getBlockState(blockPos);
					bId = blockState.getBlock();
					
                    if (bId == Blocks.air)
                        continue;
                    
                    for (XRayBlock block : XRayBlock.Blocks) 
                    {
                        if (!block.enabled)
                        	continue;
                        
                        // blocks are different
                        if(block.block != bId)
                        	continue;
                        
                        if((block.meta == -1) || (block.meta == bId.getMetaFromState(blockState))) 
                        {
                            renderBlock(i, k, j, block);
                            break;
                        }
                    }
                }
            }
        }

        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEndList();
	}
	
	private void renderBlock(int x, int y, int z, XRayBlock block) {
        GL11.glColor4ub((byte) block.r, (byte) block.g, (byte) block.b, (byte) block.a);

        GL11.glVertex3f(x, y, z);
        GL11.glVertex3f(x + 1, y, z);

        GL11.glVertex3f(x + 1, y, z);
        GL11.glVertex3f(x + 1, y, z + 1);

        GL11.glVertex3f(x, y, z);
        GL11.glVertex3f(x, y, z + 1);

        GL11.glVertex3f(x, y, z + 1);
        GL11.glVertex3f(x + 1, y, z + 1);

        GL11.glVertex3f(x, y + 1, z);
        GL11.glVertex3f(x + 1, y + 1, z);

        GL11.glVertex3f(x + 1, y + 1, z);
        GL11.glVertex3f(x + 1, y + 1, z + 1);

        GL11.glVertex3f(x, y + 1, z);
        GL11.glVertex3f(x, y + 1, z + 1);

        GL11.glVertex3f(x, y + 1, z + 1);
        GL11.glVertex3f(x + 1, y + 1, z + 1);

        GL11.glVertex3f(x, y, z);
        GL11.glVertex3f(x, y + 1, z);

        GL11.glVertex3f(x, y, z + 1);
        GL11.glVertex3f(x, y + 1, z + 1);

        GL11.glVertex3f(x + 1, y, z);
        GL11.glVertex3f(x + 1, y + 1, z);

        GL11.glVertex3f(x + 1, y, z + 1);
        GL11.glVertex3f(x + 1, y + 1, z + 1);
    }
	
	
    @SubscribeEvent
    public void renderWorldLastEvent(RenderWorldLastEvent evt) 
    {
        if ((!(this.Enabled)) || (this.mc.theWorld == null))
        {
            return;
        }
        
        double doubleX = this.mc.thePlayer.lastTickPosX
                + (this.mc.thePlayer.posX - this.mc.thePlayer.lastTickPosX)
                * evt.partialTicks;

        double doubleY = this.mc.thePlayer.lastTickPosY
                + (this.mc.thePlayer.posY - this.mc.thePlayer.lastTickPosY)
                * evt.partialTicks;

        double doubleZ = this.mc.thePlayer.lastTickPosZ
                + (this.mc.thePlayer.posZ - this.mc.thePlayer.lastTickPosZ)
                * evt.partialTicks;

        GL11.glPushMatrix();
        GL11.glTranslated(-doubleX, -doubleY, -doubleZ);
        GL11.glCallList(DisplayListId);
        GL11.glPopMatrix();
    }
}

















