
package net.mcreator.worldsmod.world.biome;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.feature.structure.PillagerOutpostConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.TwoFeatureChoiceConfig;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.block.Blocks;

import net.mcreator.worldsmod.block.MoongruntBlock;
import net.mcreator.worldsmod.WorldsModModElements;

import com.google.common.collect.Lists;

@WorldsModModElements.ModElement.Tag
public class MoonbBiome extends WorldsModModElements.ModElement {
	@ObjectHolder("worlds_mod:moonb")
	public static final CustomBiome biome = null;
	public MoonbBiome(WorldsModModElements instance) {
		super(instance, 7);
	}

	@Override
	public void initElements() {
		elements.biomes.add(() -> new CustomBiome());
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.SNOWY);
	}
	static class CustomBiome extends Biome {
		public CustomBiome() {
			super(new Biome.Builder().downfall(0f).depth(0f).scale(0f).temperature(0f).precipitation(Biome.RainType.NONE).category(Biome.Category.ICY)
					.waterColor(4159204).waterFogColor(329011).parent("desert").surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(
							MoongruntBlock.block.getDefaultState(), Blocks.STONE.getDefaultState(), Blocks.STONE.getDefaultState())));
			setRegistryName("moonb");
			DefaultBiomeFeatures.addCarvers(this);
			DefaultBiomeFeatures.addMonsterRooms(this);
			DefaultBiomeFeatures.addStructures(this);
			DefaultBiomeFeatures.addOres(this);
			DefaultBiomeFeatures.addLakes(this);
			this.addStructure(Feature.STRONGHOLD, IFeatureConfig.NO_FEATURE_CONFIG);
			this.addStructure(Feature.MINESHAFT, new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL));
			this.addStructure(Feature.PILLAGER_OUTPOST, new PillagerOutpostConfig(0.004D));
			addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
					Biome.createDecoratedFeature(
							Feature.RANDOM_BOOLEAN_SELECTOR, new TwoFeatureChoiceConfig(Feature.HUGE_RED_MUSHROOM,
									new BigMushroomFeatureConfig(false), Feature.HUGE_BROWN_MUSHROOM, new BigMushroomFeatureConfig(false)),
							Placement.COUNT_HEIGHTMAP, new FrequencyConfig(1)));
			addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
					Biome.createDecoratedFeature(Feature.DISK,
							new SphereReplaceConfig(Blocks.SAND.getDefaultState(), 7, 2,
									Lists.newArrayList(MoongruntBlock.block.getDefaultState(), Blocks.STONE.getDefaultState())),
							Placement.COUNT_TOP_SOLID, new FrequencyConfig(14)));
			addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
					Biome.createDecoratedFeature(Feature.DISK,
							new SphereReplaceConfig(Blocks.GRAVEL.getDefaultState(), 6, 2,
									Lists.newArrayList(MoongruntBlock.block.getDefaultState(), Blocks.STONE.getDefaultState())),
							Placement.COUNT_TOP_SOLID, new FrequencyConfig(15)));
			this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SHULKER, 20, 4, 4));
			this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 20, 4, 4));
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getSkyColorByTemp(float currentTemperature) {
			return -16777216;
		}
	}
}
