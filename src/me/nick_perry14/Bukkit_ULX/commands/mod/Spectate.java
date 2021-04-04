package me.nick_perry14.Bukkit_ULX.commands.mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.nick_perry14.Bukkit_ULX.libraries.Chat;
import me.nick_perry14.Bukkit_ULX.libraries.ULXPlayer;

public class Spectate implements CommandExecutor {
	private static Map<UUID, GameMode> lastMode = new HashMap<>();
	private static Map<UUID, Location> lastLoc = new HashMap<>();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Chat.playerOnly());
			return true;
		}
		if (args.length == 1) {
			Player player = (Player) sender;
			Player target = ULXPlayer.getPlayer(player, args[0]);
			if (target != null) {
				lastMode.put(player.getUniqueId(), player.getGameMode());
				if (player.getGameMode().equals(GameMode.SPECTATOR)) {
					GameMode lastGM = lastMode.remove(player.getUniqueId());
					Location lastLc = lastLoc.remove(player.getUniqueId());
					if (lastGM != null) {
						player.setGameMode(lastGM);
					} else {
						player.setGameMode(GameMode.SURVIVAL);
					}
					if (lastLc != null) {
						player.teleport(lastLc);
					}
				} else {
					lastMode.put(player.getUniqueId(), player.getGameMode());
					lastLoc.put(player.getUniqueId(), player.getLocation());
					player.setGameMode(GameMode.SPECTATOR);
					player.setSpectatorTarget(target);
				}
			} else {
				player.sendMessage(Chat.prefix() + ChatColor.RED + args[0] + " is not online!");
			}
			return true;
		}
		return false;
	}
}
