
package net.mcreator.worldsmod.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.worldsmod.item.BazaaktItem;
import net.mcreator.worldsmod.WorldsModModElements;

@WorldsModModElements.ModElement.Tag
public class UnmodtecItemGroup extends WorldsModModElements.ModElement {
	public UnmodtecItemGroup(WorldsModModElements instance) {
		super(instance, 47);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabunmodtec") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(BazaaktItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundImageName("item_search.png");
	}
	public static ItemGroup tab;
}
