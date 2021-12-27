package alice.cute.util.physics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vector3d;

public class StandardPhysics implements IProjectilePhysics
{

    public static final double d2r = Math.PI/180;
    public static final double r2d = 180/Math.PI;

    public double gravity;
    public double dragResistance;
    public double yOffset;
    public double pitchOffset;
    public double launchSpeed = 1.5;
    public Collection<Item> items = new ArrayList<Item>();

    public StandardPhysics(double grav, double drag, double y){
        gravity = grav;
        dragResistance = drag;
        yOffset = y;
        pitchOffset = 0;
    }
    public StandardPhysics(double grav, double drag, double y, double pitch){
        gravity = grav;
        dragResistance = drag;
        yOffset = y;
        pitchOffset = pitch;
    }
    public StandardPhysics(double grav, double drag, double y, double pitch, Item... items){
        gravity = grav;
        dragResistance = drag;
        yOffset = y;
        pitchOffset = pitch;
        
        for(Item i : items )
        {
            this.items.add(i);
        }
    }

    @Override
    public boolean matchesItem(ItemStack stack) 
    {
        return items.contains(stack.getItem());
    }

    @Override
    public ArrayList<Vec3> trajectory(Entity shooter) 
    {
        ArrayList<Vec3> trajectory = new ArrayList<Vec3>(101);

        double speedInitialInPlayerFrame = launchSpeed(shooter);

        double sinMinusPitch = Math.sin(d2r * (-shooter.rotationPitch - pitchOffset));
        double cosMinusPitch = Math.cos(d2r * -shooter.rotationPitch);
        
      //since offset works weirdly
        sinMinusPitch /= Math.sqrt(Math.pow(sinMinusPitch,2) + Math.pow(cosMinusPitch,2));
        cosMinusPitch /= Math.sqrt(Math.pow(sinMinusPitch,2) + Math.pow(cosMinusPitch,2));
        
        double sinYaw = Math.sin(d2r * shooter.rotationYaw);
        double cosYaw = Math.cos(d2r * shooter.rotationYaw);
        double factorX = -speedInitialInPlayerFrame*cosMinusPitch * sinYaw;
        double factorY =  speedInitialInPlayerFrame*sinMinusPitch + gravity / (1-dragResistance);
        double factorZ =  speedInitialInPlayerFrame*cosMinusPitch * cosYaw;

      //initial position (also a slight side offset so it doesn't block itself visually)
        trajectory.add(new Vec3(-cosYaw * 0.05, shooter.getEyeHeight() , -sinYaw * 0.05F));
        
        for(int n = 0; n < 100; ++n)
        {
            double dragFactor = (1 - Math.pow(dragResistance, n)) / (1-dragResistance);
            trajectory.add( 
            		new Vec3(factorX * dragFactor, 
            				 factorY * dragFactor - gravity / (1 - dragResistance) * n,
            				 factorZ * dragFactor).add(trajectory.get(0)));
        }

        return trajectory;
    }

    public double launchSpeed(Entity shooter) 
    {
        return launchSpeed;
    }

    public void setLaunchSpeed(double speed)
    {
        launchSpeed = speed;
    }
}
