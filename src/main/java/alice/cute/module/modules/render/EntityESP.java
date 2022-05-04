package alice.cute.module.modules.render;

import java.awt.Color;

import alice.cute.module.Category;
import alice.cute.module.Module;
import alice.cute.setting.Checkbox;
import alice.cute.setting.ColorPicker;
import alice.cute.setting.Mode;
import alice.cute.setting.Slider;
import alice.cute.util.EntityUtil;
import alice.cute.util.RenderUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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

    public static Checkbox neutral = new Checkbox("Neutral Creatures", true);
    public static ColorPicker neutralPicker = new ColorPicker(neutral, "Neutral Picker", new Color(255, 255, 255));
    
    public static Checkbox vehicles = new Checkbox("Vehicles", true);
    public static ColorPicker vehiclesPicker = new ColorPicker(vehicles, "Vehicle Picker", new Color(0, 255, 255));
    
    public static Checkbox items = new Checkbox("Items", true);
    public static ColorPicker itemsPicker = new ColorPicker(items, "Item Picker", new Color(199, 196, 19));

    public static Slider lineWidth = new Slider("Line Width", 0.1D, 2.5D, 5.0D, 1);
    
    @Override
    public void setup() 
	{
        addSetting(mode);
        addSetting(players);
        addSetting(animals);
        addSetting(mobs);
        addSetting(neutral);
        addSetting(vehicles);
        addSetting(items);
        addSetting(lineWidth);
    }
    
    @Override
    public boolean nullCheck() 
    {
    	return mc.thePlayer == null ||
    		   mc.theWorld == null ||
    		   mc.getRenderManager() == null || 
			   mc.getRenderManager().options == null;
    }
    
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void onRenderWorld(RenderWorldLastEvent event) 
	{
		if(nullCheck())
			return;
		
		RenderUtil.beginRenderHitbox((float)lineWidth.getValue());

        for(Entity entity : this.mc.theWorld.loadedEntityList) 
		{
        	// to add 
        	// support for slimes / ender dragon 
        	// check other mobs which might not get a hitbox shown
 
        	if(entity instanceof EntityPlayer) 
        	{
        		if(players.getValue() && entity.getName() != this.mc.thePlayer.getName()) 
        		{
        			RenderUtil.setColor(playerPicker.getColor());
        			RenderUtil.renderEntityHitbox(entity);
        		}
        		continue;
        	}
        	
        	if(entity instanceof EntityItem) 
        	{
        		if(items.getValue()) 
        		{
        			RenderUtil.setColor(itemsPicker.getColor());
        			RenderUtil.renderEntityHitbox(entity);
        		}
        		continue;
        	}
        	
        	if(EntityUtil.isHostileMob(entity))
        	{
        		if(mobs.getValue()) 
        		{
        			RenderUtil.setColor(mobsPicker.getColor());
        			RenderUtil.renderEntityHitbox(entity);
        		}
        		continue;
        	}
        	
        	if(EntityUtil.isPassive(entity)) 
        	{
        		if(animals.getValue()) 
        		{
        			RenderUtil.setColor(animalPicker.getColor());
        			RenderUtil.renderEntityHitbox(entity);
        		}
        		continue;
        	}
        	
        	if(EntityUtil.isNeutralMob(entity)) 
        	{
        		if(neutral.getValue()) 
        		{
        			RenderUtil.setColor(neutralPicker.getColor());
        			RenderUtil.renderEntityHitbox(entity);
        		}
        		continue;
        	}        	
        	
        	if(EntityUtil.isVehicle(entity)) 
        	{
        		if(vehicles.getValue()) 
        		{
        			RenderUtil.setColor(vehiclesPicker.getColor());
        			RenderUtil.renderEntityHitbox(entity);
        		}
        	}
		}
        
        RenderUtil.endRenderHitbox();
    }
}
