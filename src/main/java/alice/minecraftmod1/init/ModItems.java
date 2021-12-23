package alice.minecraftmod1.init;

import alice.minecraftmod1.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems
{
	public static Item femboy_panties;
	
	public static void init() {
		femboy_panties = new Item().setUnlocalizedName("femboy_panties");
	}
	
	public static void register() {
		registerItem(femboy_panties);
	}
	
	public static void registerRenders() {
		registerRender(femboy_panties);
	}
	
	public static void registerItem(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
		System.out.println("Registered Item: " + item.getUnlocalizedName());
	}
	
	public static void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register
		(
				item,
				0, 
				new ModelResourceLocation(
						Reference.MODID + ":" + item.getUnlocalizedName().substring(5), 
						"inventory"
						)
		);
	}
	
}
