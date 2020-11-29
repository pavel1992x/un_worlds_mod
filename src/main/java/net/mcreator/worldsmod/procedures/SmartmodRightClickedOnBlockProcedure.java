package net.mcreator.worldsmod.procedures;

import net.minecraft.world.IWorld;

import net.mcreator.worldsmod.WorldsModModVariables;
import net.mcreator.worldsmod.WorldsModModElements;

import java.util.Map;

@WorldsModModElements.ModElement.Tag
public class SmartmodRightClickedOnBlockProcedure extends WorldsModModElements.ModElement {
	public SmartmodRightClickedOnBlockProcedure(WorldsModModElements instance) {
		super(instance, 65);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure SmartmodRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure SmartmodRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure SmartmodRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure SmartmodRightClickedOnBlock!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		WorldsModModVariables.WorldVariables.get(world).x = (double) x;
		WorldsModModVariables.WorldVariables.get(world).syncData(world);
		WorldsModModVariables.WorldVariables.get(world).y = (double) y;
		WorldsModModVariables.WorldVariables.get(world).syncData(world);
		WorldsModModVariables.WorldVariables.get(world).z = (double) z;
		WorldsModModVariables.WorldVariables.get(world).syncData(world);
	}
}
