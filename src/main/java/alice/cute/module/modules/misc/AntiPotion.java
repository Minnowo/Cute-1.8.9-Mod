package alice.cute.module.modules.misc;

import java.util.Arrays;
import java.util.List;

import alice.cute.module.Category;
import alice.cute.module.Module;
import net.minecraft.potion.Potion;

public class AntiPotion extends Module 
{
	public static List<Integer> PotionEffectIDs =  Arrays.asList
												(
													Potion.blindness.id,
													Potion.confusion.id
												);

	public AntiPotion() 
	{
		super("Anti Potion", Category.MISC, "Removes potion effects");
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
		
		for(int id : AntiPotion.PotionEffectIDs) 
		{
			if(mc.thePlayer.isPotionActive(id)) 
			{
				mc.thePlayer.removePotionEffectClient(id);
			}
		}
	}
}
