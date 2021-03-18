package net.mcreator.worldsmod.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.worldsmod.WorldsModModVariables;
import net.mcreator.worldsmod.WorldsModModElements;

import java.util.Map;
import java.util.Collections;

@WorldsModModElements.ModElement.Tag
public class LtpProcedure extends WorldsModModElements.ModElement {
	public LtpProcedure(WorldsModModElements instance) {
		super(instance, 70);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure Ltp!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure Ltp!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		{
			Entity _ent = entity;
			_ent.setPositionAndUpdate((WorldsModModVariables.WorldVariables.get(world).x), (WorldsModModVariables.WorldVariables.get(world).y),
					(WorldsModModVariables.WorldVariables.get(world).z));
			if (_ent instanceof ServerPlayerEntity) {
				((ServerPlayerEntity) _ent).connection.setPlayerLocation((WorldsModModVariables.WorldVariables.get(world).x),
						(WorldsModModVariables.WorldVariables.get(world).y), (WorldsModModVariables.WorldVariables.get(world).z), _ent.rotationYaw,
						_ent.rotationPitch, Collections.emptySet());
			}
		}
	}
}
