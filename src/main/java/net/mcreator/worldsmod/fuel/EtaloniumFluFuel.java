
package net.mcreator.worldsmod.fuel;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.item.ItemStack;

import net.mcreator.worldsmod.item.EtaloniumItem;
import net.mcreator.worldsmod.WorldsModModElements;

@WorldsModModElements.ModElement.Tag
public class EtaloniumFluFuel extends WorldsModModElements.ModElement {
	public EtaloniumFluFuel(WorldsModModElements instance) {
		super(instance, 92);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(EtaloniumItem.block, (int) (1)).getItem())
			event.setBurnTime(1600);
	}
}
