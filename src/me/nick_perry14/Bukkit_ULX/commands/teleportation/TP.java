package me.nick_perry14.Bukkit_ULX.commands.teleportation;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.nick_perry14.Bukkit_ULX.libraries.Chat;
import me.nick_perry14.Bukkit_ULX.libraries.ULXPlayer;
import net.md_5.bungee.api.ChatColor;

public class TP implements CommandExecutor {
	static Map<UUID, Location> lastLoc = new HashMap<>();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		boolean playerExec = sender instanceof Player;
		if (args.length == 1 && playerExec) {
			Player player = (Player) sender;
			Player target = ULXPlayer.getPlayer(player, args[0]);
			if (target != null) {
				if (ULXPlayer.canTarget(sender, target)) {
					TP.lastLoc.put(player.getUniqueId(), player.getLocation());
					player.teleport(target);
					Chat.actionMessage(player, target, "Teleported To", "ulx.tp");
				} else
					sender.sendMessage(Chat.immune(target));
			} else
				player.sendMessage(Chat.prefix() + ChatColor.RED + args[0] + " is not online!");
			return true;
		} else if (args.length == 2) {
			if (playerExec) {
				Player player = (Player) sender;
				Player from = ULXPlayer.getPlayer(player, args[0]);
				Player to = ULXPlayer.getPlayer(player, args[1]);
				if (from == null) {
					player.sendMessage(Chat.prefix() + ChatColor.RED + args[0] + " is not online!");
					return true;
				}
				if (to == null) {
					player.sendMessage(Chat.prefix() + ChatColor.RED + args[1] + " is not online!");
					return true;
				}
				if (player == from) {
					if (player == to) {
						player.sendMessage(Chat.prefix() + ChatColor.RED + "You cannot teleport to yourself!");
						return true;
					}
					if (ULXPlayer.canTarget(player, to)) {
						lastLoc.put(player.getUniqueId(), player.getLocation());
						player.teleport(to);
						Chat.actionMessage(player, to, "Teleported To", "ulx.tp");
					} else
						player.sendMessage(Chat.immune(to));
				} else if (player == to) {
					if (ULXPlayer.canTarget(player, from)) {
						lastLoc.put(to.getUniqueId(), to.getLocation());
						to.teleport(player);
						Chat.actionMessage(player, to, "Brought", "ulx.tp");
					} else
						player.sendMessage(Chat.immune(from));
				} else {
					if (!ULXPlayer.canTarget(player, from)) {
						player.sendMessage(Chat.immune(from));
						return true;
					}
					if (!ULXPlayer.canTarget(player, to)) {
						player.sendMessage(Chat.immune(to));
						return true;
					}
					lastLoc.put(from.getUniqueId(), from.getLocation());
					from.teleport(to);
					Chat.actionMessage(player, to, "Teleported " + from.getDisplayName() + " To", "ulx.tp");
				}
			}
			// Console Teleport
			else {
				Player from = ULXPlayer.getPlayer(null, args[0]);
				Player to = ULXPlayer.getPlayer(null, args[1]);
				if (from == null) {
					sender.sendMessage(Chat.prefix() + ChatColor.RED + args[0] + " is not online!");
					return true;
				}
				if (to == null) {
					sender.sendMessage(Chat.prefix() + ChatColor.RED + args[1] + " is not online!");
					return true;
				}
				lastLoc.put(from.getUniqueId(), from.getLocation());
				from.teleport(to);
				Chat.actionMessage(sender, to, "Teleported " + from.getDisplayName() + " To", "ulx.tp");
			}
		}
		return false;
	}

}
