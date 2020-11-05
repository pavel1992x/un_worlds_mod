package net.mcreator.worldsmod.procedures;

import net.minecraft.world.IWorld;

import net.mcreator.worldsmod.WorldsModModElements;

import java.util.Map;

@WorldsModModElements.ModElement.Tag
public class WoffProcedure extends WorldsModModElements.ModElement {
	public WoffProcedure(WorldsModModElements instance) {
		super(instance, 20);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Woff!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		world.getWorldInfo().setRaining((true));
	}
}
