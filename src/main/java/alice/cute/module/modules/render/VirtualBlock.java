package alice.cute.module.modules.render;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public class VirtualBlock
{
	public static ArrayList<VirtualBlock> Blocks = new ArrayList<VirtualBlock>();
	
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
	
	public VirtualBlock() 
	{
		
	}
	
	public VirtualBlock(int r, int g, int b, int a, int meta, String id, boolean enabled) 
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
	
	private static void insertBlock(VirtualBlock vb) 
	{
		for(int i = 0; i < VirtualBlock.Blocks.size(); i++) 
		{
			if(VirtualBlock.Blocks.get(i).id == vb.id) 
			{
				VirtualBlock.Blocks.set(i, vb);
				return;
			}
		}
		
		VirtualBlock.Blocks.add(vb);
	}
	
	public static boolean registerBlock(VirtualBlock vb) 
	{
		vb.location = new ResourceLocation(vb.id);

		if (Block.blockRegistry.containsKey(vb.location)) 
		{
			vb.block = (Block)Block.blockRegistry.getObject(vb.location);
			insertBlock(vb);
			return true;
		}
		
		return false;
	}
	
	
	public static void removeInvalidBlocks() 
	{
		for (int i = 0; i < Blocks.size(); ++i) 
		{
			VirtualBlock block = Blocks.get(i);

			if (Block.blockRegistry.containsKey(block.location)) 
			{
				block.block = (Block)Block.blockRegistry.getObject(block.location);
				continue;
			}
			
			Blocks.remove(block);
		}
	}
	
	public static VirtualBlock FromString(String str) 
	{
		VirtualBlock result = new VirtualBlock();
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
