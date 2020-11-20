
package net.mcreator.worldsmod.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.IWorld;
import net.minecraft.world.IEnviromentBlockReader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.worldsmod.world.dimension.MoonworldDimension;
import net.mcreator.worldsmod.item.SinsDustItem;
import net.mcreator.worldsmod.WorldsModModElements;

import java.util.Random;
import java.util.List;
import java.util.Collections;

@WorldsModModElements.ModElement.Tag
public class SinsOreBlock extends WorldsModModElements.ModElement {
	@ObjectHolder("worlds_mod:sins_ore")
	public static final Block block = null;
	public SinsOreBlock(WorldsModModElements instance) {
		super(instance, 50);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(3f, 5f).lightValue(15).harvestLevel(2)
					.harvestTool(ToolType.PICKAXE));
			setRegistryName("sins_ore");
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getPackedLightmapCoords(BlockState state, IEnviromentBlockReader worldIn, BlockPos pos) {
			return 15728880;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(SinsDustItem.block, (int) (3)));
		}
	}
	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("worlds_mod:moonb")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(new OreFeature(OreFeatureConfig::deserialize) {
				@Override
				public boolean place(IWorld world, ChunkGenerator generator, Random rand, BlockPos pos, OreFeatureConfig config) {
					DimensionType dimensionType = world.getDimension().getType();
					boolean dimensionCriteria = false;
					if (dimensionType == MoonworldDimension.type)
						dimensionCriteria = true;
					if (!dimensionCriteria)
						return false;
					return super.place(world, generator, rand, pos, config);
				}
			}, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.create("sins_ore", "sins_ore", blockAt -> {
				boolean blockCriteria = false;
				if (blockAt.getBlock() == Blocks.STONE.getDefaultState().getBlock())
					blockCriteria = true;
				if (blockAt.getBlock() == MoongruntBlock.block.getDefaultState().getBlock())
					blockCriteria = true;
				return blockCriteria;
			}), block.getDefaultState(), 12), Placement.COUNT_RANGE, new CountRangeConfig(20, 1, 1, 90)));
		}
	}
}
