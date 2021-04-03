package me.nick_perry14.Bukkit_ULX.commands.teleportation;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.nick_perry14.Bukkit_ULX.libraries.Chat;
import me.nick_perry14.Bukkit_ULX.libraries.ULXPlayer;
import net.md_5.bungee.api.ChatColor;

public class Bring implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Chat.playerOnly());
			return true;
		}
		Player player = (Player) sender;
		if (args.length == 1) {
			Player target = ULXPlayer.getPlayer(player, args[0]);
			if (target != null) {
				if (target != player) {
					if (ULXPlayer.canTarget(sender, target)) {
						TP.lastLoc.put(target.getUniqueId(), target.getLocation());
						target.teleport(player);
						Chat.actionMessage(player, target, "Brought", "ulx.tp");
					} else {
						sender.sendMessage(Chat.immune(target));
					}
				} else {
					player.sendMessage(Chat.noMirror());
				}
			} else
				player.sendMessage(Chat.prefix() + ChatColor.RED + args[0] + " is not online!");
			return true;
		}
		return false;
	}
}
