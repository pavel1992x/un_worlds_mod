
package net.mcreator.worldsmod.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.World;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Hand;
import net.minecraft.util.Direction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.worldsmod.procedures.ShipSpawnProcedure;
import net.mcreator.worldsmod.itemgroup.UnmodtecItemGroup;
import net.mcreator.worldsmod.WorldsModModElements;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;

@WorldsModModElements.ModElement.Tag
public class SosmodulBlock extends WorldsModModElements.ModElement {
	@ObjectHolder("worlds_mod:sosmodul")
	public static final Block block = null;
	public SosmodulBlock(WorldsModModElements instance) {
		super(instance, 96);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(UnmodtecItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(1f, 10f).lightValue(0));
			setRegistryName("sosmodul");
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public void addInformation(ItemStack itemstack, IBlockReader world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(new StringTextComponent("\u0441\u043E\u0441 \u043C\u043E\u0434\u0443\u043B\u044C"));
			list.add(new StringTextComponent(
					"\u043F\u0440\u0435\u0434\u043D\u043E\u0437\u043D\u0430\u0447\u0435\u043D \u0434\u043B\u044F \u043F\u0440\u0438\u0437\u044B\u0432\u0430 \u043A\u043E\u0441\u043C\u0438\u0447\u0435\u0441\u043A\u043E\u0433\u043E \u043A\u043E\u0440\u043E\u0431\u043B\u044F"));
			list.add(new StringTextComponent("\u0432\u043D\u0438\u043C\u0430\u043D\u0438\u0435"));
			list.add(new StringTextComponent(
					"\u0434\u0430\u043D\u043D\u044B\u0439 \u0431\u043B\u043E\u043A \u043C\u043E\u0436\u0435\u0442 \u0432\u044B\u0437\u0432\u0430\u0442\u044C \u043A\u043E\u0440\u0430\u0431\u043B\u044C \u043D\u0435 \u0442\u043E\u0447\u043D\u043E."));
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}

		@Override
		public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity entity, Hand hand, BlockRayTraceResult hit) {
			boolean retval = super.onBlockActivated(state, world, pos, entity, hand, hit);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			Direction direction = hit.getFace();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				ShipSpawnProcedure.executeProcedure($_dependencies);
			}
			return true;
		}
	}
}
