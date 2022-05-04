package alice.cute;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class Cache 
{
	public static HashSet<Block> blockCache = new HashSet<Block>();
	
	public static HashSet<Potion> potionCache = new HashSet<Potion>();

	public static void loadAllCache()
	{
		if(blockCache.size() > 0 && potionCache.size() > 0)
			return;
		
		blockCache.clear();
		potionCache.clear();
		
		Set<ResourceLocation> pots = Potion.getPotionLocations();
		
		for(ResourceLocation rl : pots)
		{
			potionCache.add(Potion.getPotionFromResourceLocation(rl.toString()));
		}
		
		Set<ResourceLocation> bloc = Block.blockRegistry.getKeys();
		
		for(ResourceLocation rl : bloc)
		{
			Block b = Block.blockRegistry.getObject(rl);
			
			if(b == Blocks.air)
				continue;
			
			blockCache.add(b);
		}
	}
}







