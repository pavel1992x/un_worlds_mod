package net.mcreator.worldsmod.procedures;

import net.minecraftforge.fml.network.NetworkHooks;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.network.PacketBuffer;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.gui.widget.TextFieldWidget;

import net.mcreator.worldsmod.item.TpcardItem;
import net.mcreator.worldsmod.item.ScrudriverItem;
import net.mcreator.worldsmod.item.LtpaktItem;
import net.mcreator.worldsmod.item.Etalone_toolsSwordItem;
import net.mcreator.worldsmod.item.Etalone_toolsShovelItem;
import net.mcreator.worldsmod.item.Etalone_toolsPickaxeItem;
import net.mcreator.worldsmod.item.Etalone_toolsHoeItem;
import net.mcreator.worldsmod.item.Etalone_toolsAxeItem;
import net.mcreator.worldsmod.item.ChestlinkItem;
import net.mcreator.worldsmod.item.BazaaktItem;
import net.mcreator.worldsmod.item.AtalonearmerItem;
import net.mcreator.worldsmod.gui.TeleportintGui;
import net.mcreator.worldsmod.block.LblockBlock;
import net.mcreator.worldsmod.block.ChestBlock;
import net.mcreator.worldsmod.WorldsModModElements;

import java.util.Map;
import java.util.HashMap;

import io.netty.buffer.Unpooled;

@WorldsModModElements.ModElement.Tag
public class TestProcedure extends WorldsModModElements.ModElement {
	public TestProcedure(WorldsModModElements instance) {
		super(instance, 22);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure Test!");
			return;
		}
		if (dependencies.get("guistate") == null) {
			if (!dependencies.containsKey("guistate"))
				System.err.println("Failed to load dependency guistate for procedure Test!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure Test!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure Test!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure Test!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure Test!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		HashMap guistate = (HashMap) dependencies.get("guistate");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:term");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())).equals("\u0430\u043B\u043C\u0430\u0437"))) {
			if (world instanceof World && !world.getWorld().isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(Items.DIAMOND, (int) (1)));
				entityToSpawn.setPickupDelay((int) 9);
				world.addEntity(entityToSpawn);
			}
		} else if ((((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:term");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())).equals("fly on"))) {
			if (entity instanceof PlayerEntity) {
				((PlayerEntity) entity).abilities.allowFlying = (true);
				((PlayerEntity) entity).sendPlayerAbilities();
			}
		} else if ((((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:term");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())).equals("fly off"))) {
			if (entity instanceof PlayerEntity) {
				((PlayerEntity) entity).abilities.allowFlying = (false);
				((PlayerEntity) entity).sendPlayerAbilities();
			}
		} else if ((((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:term");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())).equals("\u0441\u0432\u0435\u0442"))) {
			if (world instanceof World && !world.getWorld().isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(LblockBlock.block, (int) (1)));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		} else if ((((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:term");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())).equals("tools"))) {
			if (world instanceof World && !world.getWorld().isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(Etalone_toolsPickaxeItem.block, (int) (1)));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
			if (world instanceof World && !world.getWorld().isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(Etalone_toolsShovelItem.block, (int) (1)));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
			if (world instanceof World && !world.getWorld().isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(Etalone_toolsAxeItem.block, (int) (1)));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
			if (world instanceof World && !world.getWorld().isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(Etalone_toolsSwordItem.block, (int) (1)));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
			if (world instanceof World && !world.getWorld().isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(Etalone_toolsHoeItem.block, (int) (1)));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
			if (world instanceof World && !world.getWorld().isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(AtalonearmerItem.body, (int) (1)));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		} else if ((((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:term");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())).equals("\u044F\u0434\u0440\u043E"))) {
			if (world instanceof World && !world.getWorld().isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(BazaaktItem.block, (int) (1)));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		} else if ((((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:term");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())).equals("\u043E\u0442\u0432\u0451\u0440\u0442\u043A\u0430"))) {
			if (world instanceof World && !world.getWorld().isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(ScrudriverItem.block, (int) (1)));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		} else if ((((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:term");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())).equals("tp"))) {
			{
				Entity _ent = entity;
				if (_ent instanceof ServerPlayerEntity) {
					BlockPos _bpos = new BlockPos((int) x, (int) y, (int) z);
					NetworkHooks.openGui((ServerPlayerEntity) _ent, new INamedContainerProvider() {
						@Override
						public ITextComponent getDisplayName() {
							return new StringTextComponent("Teleportint");
						}

						@Override
						public Container createMenu(int id, PlayerInventory inventory, PlayerEntity player) {
							return new TeleportintGui.GuiContainerMod(id, inventory, new PacketBuffer(Unpooled.buffer()).writeBlockPos(_bpos));
						}
					}, _bpos);
				}
			}
		} else if ((((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:term");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())).equals("\u0442\u043F \u043A\u0430\u0440\u0442\u0430"))) {
			if (world instanceof World && !world.getWorld().isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(TpcardItem.block, (int) (1)));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		} else if ((((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:term");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())).equals("\u0441\u0443\u043D\u0434\u0443\u043A"))) {
			if (world instanceof World && !world.getWorld().isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(ChestBlock.block, (int) (1)));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
			if (world instanceof World && !world.getWorld().isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(ChestlinkItem.block, (int) (1)));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		} else if ((((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:term");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())).equals("\u0431\u043B\u0430\u0441\u0442\u0435\u0440"))) {
			if (world instanceof World && !world.getWorld().isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(LtpaktItem.block, (int) (1)));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		} else if ((((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:term");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())).equals("\u043D\u043E\u0447\u044C"))) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity)
						.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, (int) Double.POSITIVE_INFINITY, (int) 1, (false), (false)));
		} else if ((((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:term");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())).equals("\u0434\u0435\u043D\u044C"))) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).clearActivePotions();
		}
	}
}
