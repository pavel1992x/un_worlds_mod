package net.mcreator.worldsmod.procedures;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.worldsmod.WorldsModModElements;

import java.util.Map;

@WorldsModModElements.ModElement.Tag
public class FlyOnProcedure extends WorldsModModElements.ModElement {
	public FlyOnProcedure(WorldsModModElements instance) {
		super(instance, 166);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure FlyOn!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity) {
			((PlayerEntity) entity).abilities.allowFlying = (true);
			((PlayerEntity) entity).sendPlayerAbilities();
		}
	}
}
