
package net.mcreator.worldsmod.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.BlockState;

import net.mcreator.worldsmod.WorldsModModElements;

import java.util.List;

@WorldsModModElements.ModElement.Tag
public class PlataItem extends WorldsModModElements.ModElement {
	@ObjectHolder("worlds_mod:plata")
	public static final Item block = null;
	public PlataItem(WorldsModModElements instance) {
		super(instance, 45);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("plata");
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
			list.add(new StringTextComponent(
					"\u043C\u0430\u0442\u0435\u0440\u0438\u043D\u0441\u043A\u0430\u044F \u043F\u043B\u0430\u0442\u0430 \u043F\u043B\u0430\u043D\u0448\u0435\u0442\u0430"));
			list.add(new StringTextComponent(
					"\u043A\u043E\u0442\u043E\u0440\u0430\u044F \u0441\u043E\u0434\u0435\u0440\u0436\u0438\u0442 \u0442\u0430\u043A \u0436\u0435 \u0438 \u0431\u0430\u0442\u0430\u0440\u0435\u044E."));
		}
	}
}
