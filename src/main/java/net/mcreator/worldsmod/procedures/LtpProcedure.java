package net.mcreator.worldsmod.procedures;

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
		Entity entity = (Entity) dependencies.get("entity");
		{
			Entity _ent = entity;
			_ent.setPositionAndUpdate(
					((entity.getCapability(WorldsModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new WorldsModModVariables.PlayerVariables())).x),
					((entity.getCapability(WorldsModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new WorldsModModVariables.PlayerVariables())).y),
					((entity.getCapability(WorldsModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new WorldsModModVariables.PlayerVariables())).z));
			if (_ent instanceof ServerPlayerEntity) {
				((ServerPlayerEntity) _ent).connection.setPlayerLocation(
						((entity.getCapability(WorldsModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new WorldsModModVariables.PlayerVariables())).x),
						((entity.getCapability(WorldsModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new WorldsModModVariables.PlayerVariables())).y),
						((entity.getCapability(WorldsModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new WorldsModModVariables.PlayerVariables())).z),
						_ent.rotationYaw, _ent.rotationPitch, Collections.emptySet());
			}
		}
	}
}
