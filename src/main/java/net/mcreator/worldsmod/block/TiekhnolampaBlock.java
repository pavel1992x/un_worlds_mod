
package net.mcreator.worldsmod.block;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.worldsmod.itemgroup.UnmodtecItemGroup;
import net.mcreator.worldsmod.WorldsModModElements;

import java.util.List;
import java.util.Collections;

@WorldsModModElements.ModElement.Tag
public class TiekhnolampaBlock extends WorldsModModElements.ModElement {
	@ObjectHolder("worlds_mod:tiekhnolampa")
	public static final Block block = null;
	public TiekhnolampaBlock(WorldsModModElements instance) {
		super(instance, 94);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(UnmodtecItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.GLASS).sound(SoundType.GLASS).hardnessAndResistance(10f, 10000f).lightValue(15));
			setRegistryName("tiekhnolampa");
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}
