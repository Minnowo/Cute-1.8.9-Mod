package alice.cute.util.physics;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vector3d;

public interface IProjectilePhysics
{
	public boolean matchesItem(ItemStack stack);
    public List<Vec3> trajectory(Entity shooter);
}
