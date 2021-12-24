package alice.minecraftmod1.module.modules.render;

import alice.minecraftmod1.module.Module;
import alice.minecraftmod1.setting.mode.Mode;
import alice.minecraftmod1.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class Fullbright extends Module
{
	private float _oldBrightness = -1;
	
	// 0 for gamma, 1 for potion
	public static Mode Mode = new Mode("Mode", "Gamma", "Potion");
	
	public Fullbright()
	{
		super("Fullbright", Category.RENDER, "Provides night vision");
	}
	
	@Override
	public void setup() 
	{
		this.addSetting(this.Mode);
	}
	
	@Override
	public void onEnable() 
	{
		if(Util.nullCheck())
			return;
		
		// 0 for gamma
		if(this.Mode.getValue() == 0) 
		{
			// save the old brightness setting 
			if(this._oldBrightness == -1) 
			{
				this._oldBrightness = this.mc.gameSettings.gammaSetting;
			}
			
			this.mc.gameSettings.gammaSetting = 100;
		}
	}

	@Override
	public void onDisable() 
	{
		// restore setting / potion status 
		this.mc.thePlayer.removePotionEffect(Potion.nightVision.id);
		this.mc.gameSettings.gammaSetting = this._oldBrightness;	
		this._oldBrightness = -1;
	}
	
	@Override
	public void onUpdate() 
	{
		if(Util.nullCheck())
			return;

        if (this.Mode.getValue() == 1)
            mc.thePlayer.addPotionEffect(new PotionEffect(new PotionEffect(Potion.nightVision.id, 80950, 1, false, false)));
	}
}
