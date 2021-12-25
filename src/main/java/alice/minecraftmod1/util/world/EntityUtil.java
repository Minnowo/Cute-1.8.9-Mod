package alice.minecraftmod1.util.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import alice.minecraftmod1.util.IMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class EntityUtil implements IMixin
{
	public static List<Class<?>> getPassives() 
	{
        return new ArrayList(Arrays.asList(EntityPigZombie.class, EntitySquid.class, EntityIronGolem.class, EntityWolf.class, EntityEnderman.class, EntityChicken.class, EntityCow.class, EntitySheep.class, EntityRabbit.class, EntityPig.class, EntityBat.class, EntityHorse.class, EntitySnowman.class));
    }

    public static List<Class<?>> getHostiles() 
    {
        return new ArrayList(Arrays.asList(EntitySpider.class, EntitySkeleton.class, EntityZombie.class, EntityBlaze.class, EntityCreeper.class, EntityCaveSpider.class, EntityBlaze.class, EntityGhast.class, EntityWitch.class, EntitySlime.class, EntityWither.class));
    }

    public static List<Class<?>> getVehicles() 
    {

        return new ArrayList(Arrays.asList(EntityBoat.class, EntityMinecart.class));
    }

    public static boolean isPassive(Entity entity) 
    {
        if (entity instanceof EntityWolf && ((EntityWolf) entity).isAngry())
            return false;

        if (entity instanceof EntityAgeable || 
        	entity instanceof EntityAmbientCreature || 
        	entity instanceof EntitySquid)
            return true;

        return entity instanceof EntityIronGolem && ((EntityIronGolem) entity).isPlayerCreated();
    }

    public static boolean isVehicle(Entity entity) 
    {
        return entity instanceof EntityBoat || entity instanceof EntityMinecart;
    }

    public static boolean isHostileMob(Entity entity) 
    {
        return (entity.isCreatureType(EnumCreatureType.MONSTER, false) && !EntityUtil.isNeutralMob(entity)) || 
        		entity instanceof EntitySpider;
    }

    public static boolean isNeutralMob(Entity entity) 
    {
    	return  entity instanceof EntityPigZombie || 
    			entity instanceof EntityEnderman ||
    			entity instanceof EntityWolf || 
    			entity instanceof EntityIronGolem;
    }

    public static boolean isCurrentPlayer(Entity entity) 
    {
    	if(entity == null)
    		return false;
    	return entity.getName() == mc.thePlayer.getName();
    }
    
    public static boolean isLiving(Entity entity) 
    {
        return entity instanceof EntityLivingBase;
    }
}
