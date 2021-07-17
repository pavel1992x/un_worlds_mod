package net.mcreator.worldsmod.procedures;

import net.minecraftforge.fml.server.ServerLifecycleHooks;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.Entity;

import net.mcreator.worldsmod.WorldsModModVariables;
import net.mcreator.worldsmod.WorldsModModElements;

import java.util.Map;

@WorldsModModElements.ModElement.Tag
public class TpcardRightClickedInAirProcedure extends WorldsModModElements.ModElement {
	public TpcardRightClickedInAirProcedure(WorldsModModElements instance) {
		super(instance, 69);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure TpcardRightClickedInAir!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure TpcardRightClickedInAir!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure TpcardRightClickedInAir!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure TpcardRightClickedInAir!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		{
			double _setval = (double) x;
			entity.getCapability(WorldsModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.x = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = (double) y;
			entity.getCapability(WorldsModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.y = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = (double) z;
			entity.getCapability(WorldsModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.z = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
			if (mcserv != null)
				mcserv.getPlayerList().sendMessage(new StringTextComponent(
						(("\u043B\u043E\u043A\u0430\u043B\u044C\u043D\u044B\u0439 \u0442\u0435\u043B\u0435\u043F\u043E\u0440\u0442 \u0441\u043E\u0437\u0434\u0430\u043D ")
								+ "" + ("x ") + ""
								+ (((entity.getCapability(WorldsModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new WorldsModModVariables.PlayerVariables())).x))
								+ "" + (" y ") + ""
								+ (((entity.getCapability(WorldsModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new WorldsModModVariables.PlayerVariables())).y))
								+ "" + (" z ") + ""
								+ (((entity.getCapability(WorldsModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new WorldsModModVariables.PlayerVariables())).z))
								+ ""
								+ (". \u0412\u043D\u0438\u043C\u0430\u043D\u0438\u0435 \u0442\u043E\u0447\u043A\u0443 \u043B\u043E\u043A\u0430\u043B\u044C\u043D\u043E\u0433\u043E \u0442\u0435\u043B\u0435\u043F\u043E\u0440\u0442\u0430 \u043C\u043E\u0436\u043D\u043E \u0438\u0437\u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u044C \u0442\u043E\u043B\u044C\u043A\u043E \u0432 \u043F\u0440\u0438\u0434\u0435\u043B\u0430\u0445 \u043E\u0434\u043D\u043E\u0433\u043E \u043C\u0438\u0440\u0430."))));
		}
	}
}
