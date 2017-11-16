package selim.consolespawnhelper;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class CommandCSummon implements CommandExecutor {

	private static final Random rand = new Random();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player && (!((Player) sender).isOp()
				&& !((Player) sender).hasPermission("consolespawnhelper.csummon"))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command.");
			return true;
		}
		if (args.length < 2) {
			sender.sendMessage(ChatColor.RED + "Usage: " + cmd.getUsage());
			return true;
		}
		@SuppressWarnings("deprecation")
		Player target = Bukkit.getPlayer(args[0]);
		if (target == null) {
			sender.sendMessage(ChatColor.RED + "Player " + args[0] + " not found.");
			return true;
		}
		EntityType entity = EntityType.valueOf(args[1].toUpperCase());
		if (entity == null) {
			sender.sendMessage(ChatColor.RED + "Entity " + args[1] + " not found.");
			return true;
		}
		if (args.length == 2) {
			target.getWorld().spawnEntity(target.getLocation(), entity);
			return true;
		}
		if (args.length != 5) {
			sender.sendMessage(ChatColor.RED + "Usage: " + cmd.getUsage());
			return true;
		}
		try {
			int x;
			char xChar = args[2].charAt(0);
			switch (xChar) {
			case 'r':
				int randX = Integer.valueOf(args[2].substring(1));
				x = target.getLocation().getBlockX()
						+ (rand.nextBoolean() ? -1 : 1) * rand.nextInt(randX);
				break;
			default:
				x = target.getLocation().getBlockX() + Integer.valueOf(args[2]);
				break;
			}
			int y;
			char yChar = args[3].charAt(0);
			switch (yChar) {
			case 'r':
				int randY = Integer.valueOf(args[3].substring(1));
				y = target.getLocation().getBlockY()
						+ (rand.nextBoolean() ? -1 : 1) * rand.nextInt(randY);
				break;
			default:
				y = target.getLocation().getBlockY() + Integer.valueOf(args[3]);
				break;
			}
			int z;
			char zChar = args[4].charAt(0);
			switch (zChar) {
			case 'r':
				int randZ = Integer.valueOf(args[4].substring(1));
				z = target.getLocation().getBlockZ()
						+ (rand.nextBoolean() ? -1 : 1) * rand.nextInt(randZ);
				break;
			default:
				z = target.getLocation().getBlockZ() + Integer.valueOf(args[4]);
				break;
			}
			Location spawnLoc = new Location(target.getWorld(), x, y, z);
			target.getWorld().spawnEntity(spawnLoc, entity);
		} catch (NumberFormatException e) {
			sender.sendMessage(ChatColor.RED + "Usage: " + cmd.getUsage());
			return true;
		}
		return true;
	}

}
