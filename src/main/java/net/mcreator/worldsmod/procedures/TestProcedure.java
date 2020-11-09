package net.mcreator.worldsmod.procedures;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.gui.widget.TextFieldWidget;

import net.mcreator.worldsmod.WorldsModModElements;

import java.util.Map;
import java.util.HashMap;

@WorldsModModElements.ModElement.Tag
public class TestProcedure extends WorldsModModElements.ModElement {
	public TestProcedure(WorldsModModElements instance) {
		super(instance, 22);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Test!");
			return;
		}
		if (dependencies.get("guistate") == null) {
			System.err.println("Failed to load dependency guistate for procedure Test!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		HashMap guistate = (HashMap) dependencies.get("guistate");
		if ((((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:term");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())).equals("\u0432\u044B\u0445\u043E\u0434"))) {
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).closeScreen();
		}
	}
}
