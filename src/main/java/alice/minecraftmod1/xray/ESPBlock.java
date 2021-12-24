package alice.minecraftmod1.xray;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

public class ESPBlock
{
	public static ArrayList<ESPBlock> Blocks = new ArrayList<ESPBlock>();
	
	public int r;
	public int g;
	public int b;
	public int a;
	
	public int meta;
	
	public String id;
	
	public boolean enabled;
	
	// cache resource location of the block 
	public ResourceLocation location;
	
	// cache block to avoid wasteful lookup in the block registry
	public Block block;
	
	public ESPBlock() 
	{
		
	}
	
	public ESPBlock(int r, int g, int b, int a, int meta, String id, boolean enabled) 
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
		this.meta = meta;
		this.id = id;
		this.enabled = enabled;
		this.location = new ResourceLocation(id);
	}
	
	public static ESPBlock FromString(String str) 
	{
		ESPBlock result = new ESPBlock();
		String[] info = str.split(" ");
		
		result.r = Integer.parseInt(info[0]);
		result.g = Integer.parseInt(info[1]);
		result.b = Integer.parseInt(info[2]);
		result.a = Integer.parseInt(info[3]);
		
		result.meta = Integer.parseInt(info[4]);
		result.id = info[5];
		
		result.enabled = Boolean.parseBoolean(info[6]);
		
		return result;
	}
	
	public static void setStandardList() 
	{
		ArrayList block = new ArrayList();
		block.add(new ESPBlock(0, 0, 128, 200, -1, "minecraft:lapis_ore", true));
		block.add(new ESPBlock(255, 0, 0, 200, -1, "minecraft:redstone_ore", true));
		block.add(new ESPBlock(255, 255, 0, 200, -1, "minecraft:gold_ore", true));
		block.add(new ESPBlock(0, 255, 0, 200, -1, "minecraft:emerald_ore", true));
		block.add(new ESPBlock(0, 191, 255, 200, -1, "minecraft:diamond_ore", true));
		block.add(new ESPBlock(0, 191, 128, 0, -1, "minecraft:coal_ore", true));
		block.add(new ESPBlock(0, 200, 128, 0, -1, "minecraft:iron_ore", true));
		
		Blocks = block;
	}
	
	public static void removeInvalidBlocks() 
	{
		for (int i = 0; i < Blocks.size(); ++i) 
		{
			ESPBlock block = Blocks.get(i);

			if (Block.blockRegistry.containsKey(block.location)) 
			{
				block.block = (Block)Block.blockRegistry.getObject(block.location);
				continue;
			}
			
			Blocks.remove(block);
		}
	}
	
	public String toString() 
	{
		return this.r + " " + 
			   this.g + " " + 
			   this.b + " " + 
			   this.a + " " + 
			   this.meta + " " + 
			   this.id + " " + 
			   this.enabled;
	}
}
