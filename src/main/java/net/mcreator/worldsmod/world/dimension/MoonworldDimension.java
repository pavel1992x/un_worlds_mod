
package net.mcreator.worldsmod.world.dimension;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.layer.traits.IC0Transformer;
import net.minecraft.world.gen.layer.ZoomLayer;
import net.minecraft.world.gen.layer.VoroniZoomLayer;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.gen.layer.IslandLayer;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.worldsmod.WorldsModModElements;

import javax.annotation.Nullable;

import java.util.function.LongFunction;
import java.util.function.BiFunction;
import java.util.Set;
import java.util.Random;
import java.util.List;
import java.util.Collections;

import com.google.common.collect.Sets;
import com.google.common.collect.ImmutableSet;

@WorldsModModElements.ModElement.Tag
public class MoonworldDimension extends WorldsModModElements.ModElement {
	@ObjectHolder("worlds_mod:moon")
	public static final ModDimension dimension = null;
	public static DimensionType type = null;
	private static Biome[] dimensionBiomes;
	public MoonworldDimension(WorldsModModElements instance) {
		super(instance, 8);
		MinecraftForge.EVENT_BUS.register(this);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void registerDimension(RegistryEvent.Register<ModDimension> event) {
		event.getRegistry().register(new CustomModDimension().setRegistryName("moon"));
	}

	@SubscribeEvent
	public void onRegisterDimensionsEvent(RegisterDimensionsEvent event) {
		if (DimensionType.byName(new ResourceLocation("worlds_mod:moon")) == null) {
			DimensionManager.registerDimension(new ResourceLocation("worlds_mod:moon"), dimension, null, true);
		}
		type = DimensionType.byName(new ResourceLocation("worlds_mod:moon"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		dimensionBiomes = new Biome[]{ForgeRegistries.BIOMES.getValue(new ResourceLocation("worlds_mod:moonb")),};
	}
	public static class CustomModDimension extends ModDimension {
		@Override
		public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
			return CustomDimension::new;
		}
	}

	public static class CustomDimension extends Dimension {
		private BiomeProviderCustom biomeProviderCustom = null;
		public CustomDimension(World world, DimensionType type) {
			super(world, type);
			this.nether = false;
		}

		@Override
		public void calculateInitialWeather() {
		}

		@Override
		public void updateWeather(Runnable defaultWeather) {
		}

		@Override
		public boolean canDoLightning(Chunk chunk) {
			return false;
		}

		@Override
		public boolean canDoRainSnowIce(Chunk chunk) {
			return false;
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public Vec3d getFogColor(float cangle, float ticks) {
			return new Vec3d(0, 0, 0);
		}

		@Override
		public ChunkGenerator<?> createChunkGenerator() {
			if (this.biomeProviderCustom == null) {
				this.biomeProviderCustom = new BiomeProviderCustom(this.world);
			}
			return new ChunkProviderModded(this.world, this.biomeProviderCustom);
		}

		@Override
		public boolean isSurfaceWorld() {
			return false;
		}

		@Override
		public boolean canRespawnHere() {
			return true;
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public boolean doesXZShowFog(int x, int z) {
			return false;
		}

		@Override
		public SleepResult canSleepAt(PlayerEntity player, BlockPos pos) {
			return SleepResult.ALLOW;
		}

		@Nullable
		public BlockPos findSpawn(ChunkPos chunkPos, boolean checkValid) {
			return null;
		}

		@Nullable
		public BlockPos findSpawn(int x, int z, boolean checkValid) {
			return null;
		}

		@Override
		public boolean doesWaterVaporize() {
			return true;
		}

		@Override /**
					 * Calculates the angle of sun and moon in the sky relative to a specified time
					 * (usually worldTime)
					 */
		public float calculateCelestialAngle(long worldTime, float partialTicks) {
			double d0 = MathHelper.frac((double) worldTime / 24000.0D - 0.25D);
			double d1 = 0.5D - Math.cos(d0 * Math.PI) / 2.0D;
			return (float) (d0 * 2.0D + d1) / 3.0F;
		}
	}

	public static class ChunkProviderModded extends OverworldChunkGenerator {
		public ChunkProviderModded(IWorld world, BiomeProvider provider) {
			super(world, provider, new OverworldGenSettings() {
				public BlockState getDefaultBlock() {
					return Blocks.STONE.getDefaultState();
				}

				public BlockState getDefaultFluid() {
					return Blocks.FROSTED_ICE.getDefaultState();
				}
			});
			this.randomSeed.skip(5349);
		}

		@Override
		public void spawnMobs(ServerWorld worldIn, boolean spawnHostileMobs, boolean spawnPeacefulMobs) {
		}
	}

	public static class BiomeLayerCustom implements IC0Transformer {
		@Override
		public int apply(INoiseRandom context, int value) {
			return Registry.BIOME.getId(dimensionBiomes[context.random(dimensionBiomes.length)]);
		}
	}

	public static class BiomeProviderCustom extends BiomeProvider {
		private final Layer genBiomes;
		private final Layer biomeFactoryLayer;
		private final Biome[] biomes;
		private static boolean biomesPatched = false;
		public BiomeProviderCustom(World world) {
			Layer[] aLayer = makeTheWorld(world.getSeed());
			this.genBiomes = aLayer[0];
			this.biomeFactoryLayer = aLayer[1];
			this.biomes = dimensionBiomes;
			if (!biomesPatched) {
				for (Biome biome : this.biomes) {
					biome.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(new CaveWorldCarver(ProbabilityConfig::deserialize, 256) {
						{
							carvableBlocks = ImmutableSet.of(Blocks.STONE.getDefaultState().getBlock(),
									biome.getSurfaceBuilder().getConfig().getTop().getBlock(),
									biome.getSurfaceBuilder().getConfig().getUnder().getBlock());
						}
					}, new ProbabilityConfig(0.14285715f)));
				}
				biomesPatched = true;
			}
		}

		private Layer[] makeTheWorld(long seed) {
			LongFunction<IExtendedNoiseRandom<LazyArea>> contextFactory = l -> new LazyAreaLayerContext(25, seed, l);
			IAreaFactory<LazyArea> parentLayer = IslandLayer.INSTANCE.apply(contextFactory.apply(1));
			IAreaFactory<LazyArea> biomeLayer = (new BiomeLayerCustom()).apply(contextFactory.apply(200), parentLayer);
			biomeLayer = ZoomLayer.NORMAL.apply(contextFactory.apply(1000), biomeLayer);
			biomeLayer = ZoomLayer.NORMAL.apply(contextFactory.apply(1001), biomeLayer);
			biomeLayer = ZoomLayer.NORMAL.apply(contextFactory.apply(1002), biomeLayer);
			biomeLayer = ZoomLayer.NORMAL.apply(contextFactory.apply(1003), biomeLayer);
			biomeLayer = ZoomLayer.NORMAL.apply(contextFactory.apply(1004), biomeLayer);
			biomeLayer = ZoomLayer.NORMAL.apply(contextFactory.apply(1005), biomeLayer);
			IAreaFactory<LazyArea> voronoizoom = VoroniZoomLayer.INSTANCE.apply(contextFactory.apply(10), biomeLayer);
			return new Layer[]{new Layer(biomeLayer), new Layer(voronoizoom)};
		}

		@Override /**
					 * Gets the biome from the provided coordinates
					 */
		public Biome getBiome(int x, int y) {
			return this.biomeFactoryLayer.func_215738_a(x, y);
		}

		@Override
		public Biome func_222366_b(int p_222366_1_, int p_222366_2_) {
			return this.genBiomes.func_215738_a(p_222366_1_, p_222366_2_);
		}

		@Override
		public Biome[] getBiomes(int x, int z, int width, int length, boolean cacheFlag) {
			return this.biomeFactoryLayer.generateBiomes(x, z, width, length);
		}

		@Override
		public Set<Biome> getBiomesInSquare(int centerX, int centerZ, int sideLength) {
			int i = centerX - sideLength >> 2;
			int j = centerZ - sideLength >> 2;
			int k = centerX + sideLength >> 2;
			int l = centerZ + sideLength >> 2;
			int i1 = k - i + 1;
			int j1 = l - j + 1;
			Set<Biome> set = Sets.newHashSet();
			Collections.addAll(set, this.genBiomes.generateBiomes(i, j, i1, j1));
			return set;
		}

		@Override
		@Nullable
		public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random) {
			int i = x - range >> 2;
			int j = z - range >> 2;
			int k = x + range >> 2;
			int l = z + range >> 2;
			int i1 = k - i + 1;
			int j1 = l - j + 1;
			Biome[] abiome = this.genBiomes.generateBiomes(i, j, i1, j1);
			BlockPos blockpos = null;
			int k1 = 0;
			for (int l1 = 0; l1 < i1 * j1; ++l1) {
				int i2 = i + l1 % i1 << 2;
				int j2 = j + l1 / i1 << 2;
				if (biomes.contains(abiome[l1])) {
					if (blockpos == null || random.nextInt(k1 + 1) == 0) {
						blockpos = new BlockPos(i2, 0, j2);
					}
					++k1;
				}
			}
			return blockpos;
		}

		@Override
		public boolean hasStructure(Structure<?> structureIn) {
			return this.hasStructureCache.computeIfAbsent(structureIn, (p_205006_1_) -> {
				for (Biome biome : this.biomes) {
					if (biome.hasStructure(p_205006_1_)) {
						return true;
					}
				}
				return false;
			});
		}

		@Override
		public Set<BlockState> getSurfaceBlocks() {
			if (this.topBlocksCache.isEmpty()) {
				for (Biome biome : this.biomes) {
					this.topBlocksCache.add(biome.getSurfaceBuilderConfig().getTop());
				}
			}
			return this.topBlocksCache;
		}
	}
}
