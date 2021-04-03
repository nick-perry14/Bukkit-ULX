package me.nick_perry14.Bukkit_ULX.commands.mod;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.nick_perry14.Bukkit_ULX.events.connection.PlayerLeave;
import me.nick_perry14.Bukkit_ULX.libraries.Chat;

public class RecentDC implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			String[] dcs = PlayerLeave.getRecentDC();
			sender.sendMessage(Chat.prefix() + "Recent Disconnects:");
			for (int i = 0; i < dcs.length; i++) {
				sender.sendMessage((dcs.length - (i + 1)) + ": " + dcs[i]);
			}
			return true;
		}
		return false;
	}
}
