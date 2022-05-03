package alice.cute.module.modules.render;

import alice.cute.module.Module;
import alice.cute.setting.Checkbox;
import alice.cute.module.Category;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NoRender extends Module
{
	public NoRender() 
	{
		super("No Render", Category.RENDER, "Prevent certain things from being rendered");
	}
	
	public static Checkbox fire = new Checkbox("Fire", true);
    public static Checkbox water = new Checkbox("Water", true);
	public static Checkbox blockOverlay = new Checkbox("Block Overlay", true);
	public static Checkbox fog = new Checkbox("Fog", true);
	
	@Override
    public void setup() 
	{
        addSetting(fire);
        addSetting(water);
        addSetting(blockOverlay);
        addSetting(fog);
    }
	
//	public static Checkbox hurtCamera = new Checkbox("Hurt Camera", true);
//    
//    public static Checkbox armor = new Checkbox("Armor", false);
//    public static Checkbox bossBar = new Checkbox("Boss Bars", true);
//    public static Checkbox witherSkulls = new Checkbox("Wither Skulls", true);
//    
//    public static Checkbox noCluster = new Checkbox("Cluster", true);
//    public static Checkbox particles = new Checkbox("Particles", true);
//    public static Checkbox fireworks = new Checkbox("Fireworks", true);
//    public static Checkbox offhand = new Checkbox("Offhand", true);
//    public static Checkbox enchantmentTables = new Checkbox("Enchantment Tables", false);
//    public static Checkbox beacons = new Checkbox("Beacons", false);
//	
    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void onRenderBlockOverlay(RenderBlockOverlayEvent event) 
	{
        if (fire.getValue() && event.overlayType.equals(RenderBlockOverlayEvent.OverlayType.FIRE))
            event.setCanceled(true);

        if (water.getValue() && event.overlayType.equals(RenderBlockOverlayEvent.OverlayType.WATER))
            event.setCanceled(true);

        if (blockOverlay.getValue() && event.overlayType.equals(RenderBlockOverlayEvent.OverlayType.BLOCK))
            event.setCanceled(true);
    }
    
    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void onFogRender(EntityViewRenderEvent.FogDensity event) 
    {
    	if(fog.getValue()) 
    	{	
    		event.density = 0;
    		event.setCanceled(true);
    	}
    }
}
