package net.mcreator.worldsmod.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;

import net.mcreator.worldsmod.block.ChestBlock;
import net.mcreator.worldsmod.WorldsModModVariables;
import net.mcreator.worldsmod.WorldsModModElements;

import java.util.Map;

@WorldsModModElements.ModElement.Tag
public class Chest_linkProcedure extends WorldsModModElements.ModElement {
	public Chest_linkProcedure(WorldsModModElements instance) {
		super(instance, 77);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure Chest_link!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure Chest_link!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure Chest_link!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure Chest_link!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == ChestBlock.block.getDefaultState().getBlock())) {
			WorldsModModVariables.WorldVariables.get(world).chx = (double) x;
			WorldsModModVariables.WorldVariables.get(world).syncData(world);
			WorldsModModVariables.WorldVariables.get(world).chy = (double) y;
			WorldsModModVariables.WorldVariables.get(world).syncData(world);
			WorldsModModVariables.WorldVariables.get(world).chz = (double) z;
			WorldsModModVariables.WorldVariables.get(world).syncData(world);
		}
	}
}
