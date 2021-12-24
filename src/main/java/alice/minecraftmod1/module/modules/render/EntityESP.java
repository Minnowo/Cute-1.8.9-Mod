package alice.minecraftmod1.module.modules.render;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import alice.minecraftmod1.module.Module;
import alice.minecraftmod1.setting.checkbox.Checkbox;
import alice.minecraftmod1.setting.color.ColorPicker;
import alice.minecraftmod1.setting.mode.Mode;
import alice.minecraftmod1.setting.slider.Slider;
import alice.minecraftmod1.util.Util;
import alice.minecraftmod1.util.render.ESPUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityESP extends Module
{
	public EntityESP() 
	{
		super("Entity ESP", Category.RENDER, "Highlights entities");
	}
	
	public static Mode mode = new Mode("Mode", "Outline", "Glow", "2D", "CS:GO", "Wireframe");

    public static Checkbox players = new Checkbox("Players", true);
    public static ColorPicker playerPicker = new ColorPicker(players, "Player Picker", new Color(215, 46, 46));

    public static Checkbox animals = new Checkbox("Animals", true);
    public static ColorPicker animalPicker = new ColorPicker(animals, "Animal Picker", new Color(0, 200, 0));

    public static Checkbox mobs = new Checkbox("Mobs", true);
    public static ColorPicker mobsPicker = new ColorPicker(mobs, "Mob Picker", new Color(131, 19, 199));

    public static Checkbox items = new Checkbox("Items", true);
    public static ColorPicker itemsPicker = new ColorPicker(items, "Item Picker", new Color(199, 196, 19));

    public static Slider lineWidth = new Slider("Line Width", 0.0D, 2.5D, 5.0D, 1);
    
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void onRenderWorld(RenderWorldLastEvent event) 
	{
		if(Util.nullCheck() || Util.checkRender())
			return;
		
		ESPUtil.beginRenderHitbox(2f);
		
        for(Entity entity : this.mc.theWorld.loadedEntityList) 
		{
        	if(entity instanceof EntityPlayer) 
        	{
        		if(players.getValue() && entity.getName() != this.mc.thePlayer.getName()) 
        		{
        			ESPUtil.setColor(playerPicker.getColor());
        			ESPUtil.renderEntityHitbox(entity);
        		}
        		continue;
        	}
        	
        	if(entity instanceof EntityMob) 
        	{
        		if(mobs.getValue()) 
        		{
        			ESPUtil.setColor(mobsPicker.getColor());
        			ESPUtil.renderEntityHitbox(entity);
        		}
        		continue;
        	}
        	
        	if(entity instanceof EntityAnimal) 
        	{
        		if(animals.getValue()) 
        		{
        			ESPUtil.setColor(animalPicker.getColor());
        			ESPUtil.renderEntityHitbox(entity);
        		}
        		continue;
        	}
        	
        	if(entity instanceof EntityItem) 
        	{
        		if(items.getValue()) 
        		{
        			ESPUtil.setColor(itemsPicker.getColor());
        			ESPUtil.renderEntityHitbox(entity);
        		}
        		continue;
        	}        	
		}
        
        ESPUtil.endRenderHitbox();
    }
}
