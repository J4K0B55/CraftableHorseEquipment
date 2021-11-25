package me.J4K0B55.CraftableHorseEquipment;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {

		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		console.sendMessage(ChatColor.GREEN + "Plugin [CraftableHorseEquipment] activated");
		
		saveDefaultConfig();
		checkRecipes();
		
	}

	@Override
	public void onDisable() {

		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		console.sendMessage(ChatColor.RED + "Plugin [CraftableHorseEquipment] deactivated");
		
	}
	
	private Recipe recipeSaddle = (new ShapedRecipe(new NamespacedKey(this, "saddle"), new ItemStack(Material.SADDLE))).shape(new String[]{"LLL","LIL","SSS"}).setIngredient('L', Material.LEATHER).setIngredient('I', Material.IRON_INGOT).setIngredient('S', Material.STRING);
	private Recipe recipeDiamondHorseArmor = (new ShapedRecipe(new NamespacedKey(this, "diamond_horse_armor"), new ItemStack(Material.DIAMOND_HORSE_ARMOR))).shape(new String[]{"D D","DDD","D D"}).setIngredient('D', Material.DIAMOND);
	private Recipe recipeGoldenHorseArmor = (new ShapedRecipe(new NamespacedKey(this, "golden_horse_armor"), new ItemStack(Material.GOLDEN_HORSE_ARMOR))).shape(new String[]{"G G","GGG","G G"}).setIngredient('G', Material.GOLD_INGOT);
	private Recipe recipeIronHorseArmor = (new ShapedRecipe(new NamespacedKey(this, "iron_horse_armor"), new ItemStack(Material.IRON_HORSE_ARMOR))).shape(new String[]{"I I","III","I I"}).setIngredient('I', Material.IRON_INGOT);
	
	private void checkRecipes() {
		
		this.getServer().removeRecipe(new NamespacedKey(this, "iron_horse_armor"));
		this.getServer().removeRecipe(new NamespacedKey(this, "golden_horse_armor"));
		this.getServer().removeRecipe(new NamespacedKey(this, "diamond_horse_armor"));
		this.getServer().removeRecipe(new NamespacedKey(this, "saddle"));
		
        if (this.getConfig().getBoolean("IronHorseArmor")) {
            this.getServer().addRecipe(recipeIronHorseArmor);
        }
        if (this.getConfig().getBoolean("GoldenHorseArmor")) {
            this.getServer().addRecipe(recipeGoldenHorseArmor);
        }
        if (this.getConfig().getBoolean("DiamondHorseArmor")) {
            this.getServer().addRecipe(recipeDiamondHorseArmor);
        }
        if (this.getConfig().getBoolean("Saddle")) {
            this.getServer().addRecipe(recipeSaddle);
        }

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("craftablehorseequipment")) {
			if(args.length == 1) {
				switch(args[0]) {
				case "reload":
					if(sender.hasPermission("craftablehorseequipment.reload")) {
						reloadConfig();
						checkRecipes();
					 		if (sender instanceof Player) {
								Player player = (Player) sender;
								player.sendMessage(net.md_5.bungee.api.ChatColor.of("#8596ab") + "[" + net.md_5.bungee.api.ChatColor.of("#486d9c") + net.md_5.bungee.api.ChatColor.BOLD + "CHE" + net.md_5.bungee.api.ChatColor.of("#8596ab") + "]" + net.md_5.bungee.api.ChatColor.of("#51c1e0") + " Plugin configuration file reloaded successfully!");
								ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
								console.sendMessage(ChatColor.GREEN + "[CraftableHorseEquipment] " + player.getName() + " reloaded the plugin configuration file");
							} else {
								sender.sendMessage(ChatColor.GREEN + "[CraftableHorseEquipment] Plugin configuration file reloaded successfully!");
								ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
								console.sendMessage(ChatColor.GREEN + "[CraftableHorseEquipment] Console reloaded the plugin configuration file");
							}
					} else {
						if(sender instanceof Player) {
							Player player = (Player) sender;
							player.sendMessage(net.md_5.bungee.api.ChatColor.of("#8596ab") + "[" + net.md_5.bungee.api.ChatColor.of("#486d9c") + net.md_5.bungee.api.ChatColor.BOLD + "CHE" + net.md_5.bungee.api.ChatColor.of("#8596ab") + "]" + net.md_5.bungee.api.ChatColor.of("#51c1e0") + " You don't have permissions to use this command!");
							ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
							console.sendMessage(ChatColor.GREEN + "[CraftableHorseEquipment] " + player.getName() + " tried to reload the plugin configuration file but had insufficient permissions");
						}
					}
					break;
				case "help":
					if (sender instanceof Player) {
						Player player = (Player) sender;
						player.sendMessage(net.md_5.bungee.api.ChatColor.of("#8596ab") + "[" + net.md_5.bungee.api.ChatColor.of("#486d9c") + net.md_5.bungee.api.ChatColor.BOLD + "CHE" + net.md_5.bungee.api.ChatColor.of("#8596ab") + "]" + net.md_5.bungee.api.ChatColor.of("#51c1e0") + " CraftableHorseEquipment v1.0.0.");
						player.sendMessage(net.md_5.bungee.api.ChatColor.of("#8596ab") + "-" + net.md_5.bungee.api.ChatColor.of("#59c272") + " /craftablehorseequipment reload");
					} else {
						sender.sendMessage(ChatColor.GREEN + "[CraftableHorseEquipment] Console cannot use this command.");
					}
					break;
				default:
					if (sender instanceof Player) {
						Player player = (Player) sender;
						player.sendMessage(net.md_5.bungee.api.ChatColor.of("#8596ab") + "[" + net.md_5.bungee.api.ChatColor.of("#486d9c") + net.md_5.bungee.api.ChatColor.BOLD + "CHE" + net.md_5.bungee.api.ChatColor.of("#8596ab") + "]" + net.md_5.bungee.api.ChatColor.of("#51c1e0") + " Wrong command usage!");
						player.sendMessage(net.md_5.bungee.api.ChatColor.of("#8596ab") + "[" + net.md_5.bungee.api.ChatColor.of("#486d9c") + net.md_5.bungee.api.ChatColor.BOLD + "CHE" + net.md_5.bungee.api.ChatColor.of("#8596ab") + "]" + net.md_5.bungee.api.ChatColor.of("#51c1e0") + " Use [" + net.md_5.bungee.api.ChatColor.of("#59c272") + "/craftablehorseequipment help" + net.md_5.bungee.api.ChatColor.of("#51c1e0") + "] to view all availabe commands.");

					} else {
						ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
						console.sendMessage(ChatColor.GREEN + "[CraftableHorseEquipment] Wrong command usage!");
					}
					break;
				}
			} else {
				sender.sendMessage(net.md_5.bungee.api.ChatColor.of("#8596ab") + "[" + net.md_5.bungee.api.ChatColor.of("#486d9c") + net.md_5.bungee.api.ChatColor.BOLD + "CHE" + net.md_5.bungee.api.ChatColor.of("#8596ab") + "]" + net.md_5.bungee.api.ChatColor.of("#51c1e0") + " CraftableHorseEquipment v1.0.0.");
				sender.sendMessage(net.md_5.bungee.api.ChatColor.of("#8596ab") + "[" + net.md_5.bungee.api.ChatColor.of("#486d9c") + net.md_5.bungee.api.ChatColor.BOLD + "CHE" + net.md_5.bungee.api.ChatColor.of("#8596ab") + "]" + net.md_5.bungee.api.ChatColor.of("#51c1e0") + " Use [" + net.md_5.bungee.api.ChatColor.of("#59c272") + "/craftablehorseequipment help" + net.md_5.bungee.api.ChatColor.of("#51c1e0") + "] to view all availabe commands.");
			}
		}
		return true;
	}
	
}
