package net.mcreator.worldsmod.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.worldsmod.WorldsModModVariables;
import net.mcreator.worldsmod.WorldsModModElements;

import java.util.Map;

@WorldsModModElements.ModElement.Tag
public class ShipSpawnProcedure extends WorldsModModElements.ModElement {
	public ShipSpawnProcedure(WorldsModModElements instance) {
		super(instance, 96);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure ShipSpawn!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure ShipSpawn!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure ShipSpawn!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure ShipSpawn!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure ShipSpawn!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		{
			double _setval = (double) x;
			entity.getCapability(WorldsModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.bcx = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = (double) y;
			entity.getCapability(WorldsModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.bcy = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = (double) z;
			entity.getCapability(WorldsModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.bcz = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		world.setBlockState(new BlockPos(
				(int) ((entity.getCapability(WorldsModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new WorldsModModVariables.PlayerVariables())).bcx),
				(int) ((entity.getCapability(WorldsModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new WorldsModModVariables.PlayerVariables())).bcy),
				(int) ((entity.getCapability(WorldsModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new WorldsModModVariables.PlayerVariables())).bcz)),
				Blocks.AIR.getDefaultState(), 3);
		if (!world.getWorld().isRemote) {
			Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
					.getTemplateDefaulted(new ResourceLocation("worlds_mod", "sp"));
			if (template != null) {
				template.addBlocksToWorld(world,
						new BlockPos(
								(int) ((entity.getCapability(WorldsModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new WorldsModModVariables.PlayerVariables())).bcx),
								(int) ((entity.getCapability(WorldsModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new WorldsModModVariables.PlayerVariables())).bcy),
								(int) ((entity.getCapability(WorldsModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new WorldsModModVariables.PlayerVariables())).bcz)),
						new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
			}
		}
	}
}
