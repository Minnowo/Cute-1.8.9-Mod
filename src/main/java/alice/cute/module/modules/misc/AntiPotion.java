package alice.cute.module.modules.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.lwjgl.opengl.GL11;

import alice.cute.module.Category;
import alice.cute.module.Module;
import alice.cute.setting.ListSelection;
import alice.cute.setting.enums.ListType;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;


public class AntiPotion extends Module 
{

	public AntiPotion() 
	{
		super("Anti Potion", Category.MISC, "Removes potion effects");
	}
	
	public static ListSelection potions = new ListSelection<Potion>("Potions", new ArrayList<Potion>(), ListType.POTION);
	

	@Override
    public void setup() 
	{
        addSetting(potions);
    }

	@Override
	public void onEnable() 
	{

	}

	@Override
	public boolean nullCheck() 
	{
		return mc.thePlayer == null;
	}
	
	@Override
	public void onUpdate() 
	{
		if(nullCheck())
			return;
		
		ArrayList<Potion> a = this.potions.getEnabledItems();
		
		for(Potion id : a) 
		{
			if(mc.thePlayer.isPotionActive(id.id)) 
			{
				mc.thePlayer.removePotionEffectClient(id.id);
			}
		}
	}
}
