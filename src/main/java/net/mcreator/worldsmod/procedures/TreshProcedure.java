package net.mcreator.worldsmod.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.ItemStack;

import net.mcreator.worldsmod.WorldsModModVariables;
import net.mcreator.worldsmod.WorldsModModElements;

import java.util.Map;

@WorldsModModElements.ModElement.Tag
public class TreshProcedure extends WorldsModModElements.ModElement {
	public TreshProcedure(WorldsModModElements instance) {
		super(instance, 79);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure Tresh!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		{
			TileEntity _ent = world.getTileEntity(new BlockPos((int) (WorldsModModVariables.WorldVariables.get(world).chx),
					(int) (WorldsModModVariables.WorldVariables.get(world).chy), (int) (WorldsModModVariables.WorldVariables.get(world).chz)));
			if (_ent != null) {
				final int _sltid = (int) (44);
				final int _amount = (int) 1;
				_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable) {
						ItemStack _stk = capability.getStackInSlot(_sltid).copy();
						_stk.shrink(_amount);
						((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _stk);
					}
				});
			}
		}
	}
}
