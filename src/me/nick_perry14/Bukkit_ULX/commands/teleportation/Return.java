package me.nick_perry14.Bukkit_ULX.commands.teleportation;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.nick_perry14.Bukkit_ULX.libraries.Chat;
import me.nick_perry14.Bukkit_ULX.libraries.ULXPlayer;

public class Return implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		boolean playerExec = sender instanceof Player;
		if (args.length == 0 && playerExec) {
			Player player = (Player) sender;
			Location last = TP.lastLoc.remove(player.getUniqueId());
			if (last != null) {
				player.teleport(TP.lastLoc.get(player.getUniqueId()));
				Chat.actionMessage(player, player, "Returned", "ulx.tp");
			} else {
				sender.sendMessage(Chat.prefix() + ChatColor.DARK_AQUA + "You" + ChatColor.RED
						+ " do not have any previous locations!");
			}
			return true;
		}
		if (args.length == 1) {
			Player target = ULXPlayer.getPlayer((playerExec) ? (Player) sender : null, args[0]);
			if (target != null) {
				Location last = TP.lastLoc.remove(target.getUniqueId());
				if (last != null) {
					target.teleport(TP.lastLoc.get(target.getUniqueId()));
					Chat.actionMessage(sender, target, "Returned", "ulx.tp");
				} else {
					sender.sendMessage(Chat.prefix() + target.getDisplayName() + ChatColor.RED
							+ " does not have any previous locations!");
				}
			} else
				sender.sendMessage(Chat.prefix() + ChatColor.RED + args[0] + " is not online!");
			return true;
		}
		return false;
	}
}
