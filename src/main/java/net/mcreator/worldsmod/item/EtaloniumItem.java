
package net.mcreator.worldsmod.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.mcreator.worldsmod.itemgroup.UnmodtecItemGroup;
import net.mcreator.worldsmod.WorldsModModElements;

@WorldsModModElements.ModElement.Tag
public class EtaloniumItem extends WorldsModModElements.ModElement {
	@ObjectHolder("worlds_mod:etalonium")
	public static final Item block = null;
	public EtaloniumItem(WorldsModModElements instance) {
		super(instance, 91);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(UnmodtecItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("etalonium");
		}

		@Override
		public boolean hasContainerItem() {
			return true;
		}

		@Override
		public ItemStack getContainerItem(ItemStack itemstack) {
			return new ItemStack(this);
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
