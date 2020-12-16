
package net.mcreator.worldsmod.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ArmorItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.worldsmod.procedures.AtalonearmerBodyTickEventProcedure;
import net.mcreator.worldsmod.itemgroup.UnmodtecItemGroup;
import net.mcreator.worldsmod.WorldsModModElements;

import java.util.Map;
import java.util.HashMap;

@WorldsModModElements.ModElement.Tag
public class AtalonearmerItem extends WorldsModModElements.ModElement {
	@ObjectHolder("worlds_mod:atalonearmer_helmet")
	public static final Item helmet = null;
	@ObjectHolder("worlds_mod:atalonearmer_chestplate")
	public static final Item body = null;
	@ObjectHolder("worlds_mod:atalonearmer_leggings")
	public static final Item legs = null;
	@ObjectHolder("worlds_mod:atalonearmer_boots")
	public static final Item boots = null;
	public AtalonearmerItem(WorldsModModElements instance) {
		super(instance, 80);
	}

	@Override
	public void initElements() {
		IArmorMaterial armormaterial = new IArmorMaterial() {
			public int getDurability(EquipmentSlotType slot) {
				return new int[]{13, 15, 16, 11}[slot.getIndex()] * 283;
			}

			public int getDamageReductionAmount(EquipmentSlotType slot) {
				return new int[]{2, 5, 166, 2}[slot.getIndex()];
			}

			public int getEnchantability() {
				return 9;
			}

			public net.minecraft.util.SoundEvent getSoundEvent() {
				return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.anvil.fall"));
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(SinspanelItem.block, (int) (1)));
			}

			@OnlyIn(Dist.CLIENT)
			public String getName() {
				return "atalonearmer";
			}

			public float getToughness() {
				return 5f;
			}
		};
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.CHEST, new Item.Properties().group(UnmodtecItemGroup.tab)) {
			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "worlds_mod:textures/models/armor/stone_1_layer_" + (slot == EquipmentSlotType.LEGS ? "2" : "1") + ".png";
			}

			@Override
			public void onArmorTick(ItemStack itemstack, World world, PlayerEntity entity) {
				double x = entity.posX;
				double y = entity.posY;
				double z = entity.posZ;
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					AtalonearmerBodyTickEventProcedure.executeProcedure($_dependencies);
				}
			}
		}.setRegistryName("atalonearmer_chestplate"));
	}
}
