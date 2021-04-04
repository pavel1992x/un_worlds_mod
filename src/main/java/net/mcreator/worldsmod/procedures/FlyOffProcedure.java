package net.mcreator.worldsmod.procedures;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.worldsmod.WorldsModModElements;

import java.util.Map;

@WorldsModModElements.ModElement.Tag
public class FlyOffProcedure extends WorldsModModElements.ModElement {
	public FlyOffProcedure(WorldsModModElements instance) {
		super(instance, 167);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure FlyOff!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity) {
			((PlayerEntity) entity).abilities.allowFlying = (false);
			((PlayerEntity) entity).sendPlayerAbilities();
		}
	}
}
