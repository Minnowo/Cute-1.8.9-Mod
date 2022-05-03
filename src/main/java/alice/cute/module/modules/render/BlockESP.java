package alice.cute.module.modules.render;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import alice.cute.Reference;
import alice.cute.module.Module;
import alice.cute.module.Category;
import alice.cute.module.modules.render.VirtualBlock;
import alice.cute.setting.Checkbox;
import alice.cute.setting.Slider;
import alice.cute.util.Util;
import alice.cute.util.render.ESPUtil;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
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

public class BlockESP extends Module
{
	public BlockESP() 
	{
		super("Block ESP", Category.RENDER, "Highlights blocks");
	}
	
	public static Checkbox IntervalRefresh = new Checkbox("Auto Refresh", false);
	public static Slider RefreshInterval   = new Slider("Refresh", 1.0D, 30D, 500D, 1);
	public static Slider SearchRadius      = new Slider("Radius", 0.0D, 45D, 200D, 1);

	public static int DisplayListId = 0; // no idea what this is lmao
	  
	private static int _cooldownTicks = 0; // the internal counter for the ticks 

	@Override
    public void setup() 
	{
        addSetting(IntervalRefresh);
        addSetting(RefreshInterval);
        addSetting(SearchRadius);
    }

	@Override
	public boolean nullCheck() 
	{
		return mc.thePlayer == null ||
	    	   mc.theWorld == null ||
	    	   mc.getRenderManager() == null || 
			   mc.getRenderManager().options == null;
	}
	
	@Override
	public void onEnable() 
	{
		this.DisplayListId = GL11.glGenLists(GL11.GL_TRIANGLE_STRIP) + 3;
		
		MinecraftForge.EVENT_BUS.register(this);
		
		this._cooldownTicks = 0;
		
		this.compileDL();
	}

	
	@Override
	public void onDisable() 
	{
		GL11.glDeleteLists(DisplayListId, 1);
		MinecraftForge.EVENT_BUS.unregister(this);
	}

	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public boolean onTickInGame(TickEvent.ClientTickEvent e) 
	{
//		if(!super._enabled)
//		{
//			return false;
//		}
//		
		if(this._cooldownTicks < 1) 
		{
			this.compileDL();
			this._cooldownTicks = (int)this.RefreshInterval.getValue();
		}
		
		if(this.IntervalRefresh.getValue()) 
		{
			return true;
		}
		
		this._cooldownTicks--;
		return true;
	}
	
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void renderWorldLastEvent(RenderWorldLastEvent evt) 
    {
        if (nullCheck())
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
	
	private void compileDL() 
	{
		if(nullCheck())
			return;
		
		// i have no idea what this is but i changed all the numbers to make more sense at least
		// https://javadoc.lwjgl.org/constant-values.html#org.lwjgl.opengl.GL11.GL_2_BYTES
		
		GL11.glNewList(DisplayListId, GL11.GL_COMPILE);
		
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDepthMask(false);
        GL11.glBegin(GL11.GL_ONE);
        
        
        WorldClient world = this.mc.theWorld;

        EntityPlayerSP player = this.mc.thePlayer;
        
        if ((world == null) || (player == null))
            return;
        
        int x = (int)player.posX;
        int z = (int)player.posZ;
        
        int radius = (int)this.SearchRadius.getValue();
        
        // x 
        for (int i = x - radius; i <= x + radius; ++i) 
        {
        	// z
            for (int j = z - radius; j <= z + radius; ++j) 
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
                    
                    for (VirtualBlock block : VirtualBlock.Blocks) 
                    {
                        if (!block.enabled)
                        	continue;
                        
                        // blocks are different
                        if(block.block != bId)
                        	continue;
                        
                        if((block.meta == -1) || (block.meta == bId.getMetaFromState(blockState))) 
                        {
                            ESPUtil.renderBlock(i, k, j, block);
                            break;
                        }
                    }
                }
            }
        }

        GL11.glEnd();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEndList();
	}
}
