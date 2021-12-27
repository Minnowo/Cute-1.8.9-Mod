package alice.cute.util.physics;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;

public class ArrowPhysics extends StandardPhysics
{
	public Item item;

    public ArrowPhysics()
    {
        super(0.05, 0.99, 1, 0, Items.arrow);
    }

    public double launchSpeed(Entity shooter) 
    {
        if (!(shooter instanceof EntityPlayer)) 
        	return 3;
//    
        EntityPlayer player = (EntityPlayer) shooter;
        
        ItemStack stack = player.getHeldItem();
        
        if(stack == null)
        	return 3;
        
        if(!(stack.getItem() instanceof ItemBow))
        	return 3;

//        // counts down from maxItemUseDuration when pulling back bow, 0 otherwise
        int pull = player.getItemInUseDuration();
//        int max = Items.bow.getMaxItemUseDuration(player.getHeldItem());

        
        // s = 3 * min(1, c^2 / 1200 + c / 30) for c 0-72000
        return 3 * getArrowVelocity((pull != 0) ? (pull) : 0);
    }
    
    public static float getArrowVelocity(int charge) 
    {
         float f = (float)charge / 20.0F;
         
         f = (f * f + f * 2.0F) / 3.0F;

         if ((double)f < 0.1D)
             return 0;

         if (f > 1.0F)
             return 1.0F;
         
         return f;
//         
//		float strength = (float) charge / 20.0F;
//		strength = (strength * strength + strength * 2.0F) / 3.0F * 1.15F;
//		if (strength > 1.0F) {
//			strength = 1.0F;
//		}
//		return strength;
	}
}
