
package net.mcreator.worldsmod.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

import net.mcreator.worldsmod.WorldsModModElements;

@WorldsModModElements.ModElement.Tag
public class StillSwordItem extends WorldsModModElements.ModElement {
	@ObjectHolder("worlds_mod:still_sword")
	public static final Item block = null;
	public StillSwordItem(WorldsModModElements instance) {
		super(instance, 112);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 5000;
			}

			public float getEfficiency() {
				return 9f;
			}

			public float getAttackDamage() {
				return 48f;
			}

			public int getHarvestLevel() {
				return 4;
			}

			public int getEnchantability() {
				return 40;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(StillIngItem.block, (int) (1)));
			}
		}, 3, 0f, new Item.Properties().group(ItemGroup.COMBAT)) {
		}.setRegistryName("still_sword"));
	}
}
