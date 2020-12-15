
package net.mcreator.worldsmod.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.BlockState;

import net.mcreator.worldsmod.procedures.BazacorecreatProcedure;
import net.mcreator.worldsmod.WorldsModModElements;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

@WorldsModModElements.ModElement.Tag
public class StarterItem extends WorldsModModElements.ModElement {
	@ObjectHolder("worlds_mod:starter")
	public static final Item block = null;
	public StarterItem(WorldsModModElements instance) {
		super(instance, 17);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("starter");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public boolean hasEffect(ItemStack itemstack) {
			return true;
		}

		@Override
		public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(new StringTextComponent("\u0421\u0442\u0430\u0440\u0442\u043E\u0432\u044B\u0439 \u0436\u0435\u0437\u043B"));
			list.add(new StringTextComponent(
					"\u044F\u0432\u043B\u044F\u0435\u0442\u0441\u044F \u043F\u0435\u0440\u0432\u0438\u0447\u043D\u044B\u043C \u0430\u0440\u0442\u0435\u0444\u0430\u043A\u0442\u043E\u043C"));
			list.add(new StringTextComponent(
					"/ \u043A\u043E\u0442\u043E\u0440\u044B\u0439 \u0434\u043E\u043B\u0436\u0435\u043D \u0441\u043E\u0437\u0434\u0430\u0442\u044C \u043D\u0430\u0447\u0435\u043D\u0430\u044E\u0449\u0438\u0439 \u043F\u0443\u0442\u043D\u0438\u043A \u043F\u043E \u043C\u0438\u0440\u0430\u043C."));
			list.add(new StringTextComponent(
					"\u0431\u0435\u0437 \u0434\u0430\u043D\u043D\u043D\u043E\u0433\u043E \u0436\u0435\u0437\u043B\u0430 \u0442\u044B \u043D\u0435 \u0441\u043C\u043E\u0436\u0435\u0448\u044C \u043F\u043E\u043F\u0430\u0434\u0430\u0442\u044C \u0432 \u0434\u0440\u0443\u0433\u0438\u0435 \u043C\u0438\u0440\u044B"));
			list.add(new StringTextComponent(
					"\u0438 \u0441\u043E\u0437\u0434\u0430\u0442\u044C \u043F\u0435\u0440\u0432\u043E\u0431\u0430\u0437\u0443."));
		}

		@Override
		public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
			ActionResult<ItemStack> ar = super.onItemRightClick(world, entity, hand);
			ItemStack itemstack = ar.getResult();
			double x = entity.posX;
			double y = entity.posY;
			double z = entity.posZ;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("world", world);
				BazacorecreatProcedure.executeProcedure($_dependencies);
			}
			return ar;
		}
	}
}
