package me.nick_perry14.Bukkit_ULX.libraries;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.nick_perry14.Bukkit_ULX.Main;

public final class Chat {
	private static byte notifyLevel = 0;

	private Chat() {

	}

	public static String prefix() {
		return ChatColor.DARK_GRAY + "[" + ChatColor.RED + "BULX" + ChatColor.DARK_GRAY + "] " + ChatColor.RESET;
	}

	public static String playerOnly() {
		return prefix() + ChatColor.RED + "This command can only be executed by players!";
	}

	public static void actionMessage(CommandSender executor, Player target, String action, String permission) {
		boolean playerexec = executor instanceof Player;
		String executorname = playerexec ? ((Player) executor).getDisplayName() : ChatColor.DARK_GRAY + "(Console)";
		String targetDisplayname = (playerexec && (Player) executor == target)
				? ChatColor.DARK_AQUA + "Themself" + ChatColor.RESET
				: ((Player) target).getDisplayName();
		if (playerexec)
			Main.getInstance().getServer().getConsoleSender()
					.sendMessage(executorname + " " + action + " " + targetDisplayname + "!");
		else
			executor.sendMessage(
					ChatColor.DARK_AQUA + "You " + ChatColor.RESET + action + " " + targetDisplayname + "!");
		switch (notifyLevel) {
		case 0:
			// No message, since level = 0
			break;
		case 1:
			for (Player online : Main.getInstance().getServer().getOnlinePlayers()) {
				if (online == target) {
					if (playerexec && online == executor)
						online.sendMessage(ChatColor.DARK_AQUA + "You " + ChatColor.RESET + action + ChatColor.DARK_AQUA
								+ " Yourself!");
					else if (online.hasPermission(permission))
						online.sendMessage(executorname + " " + action + ChatColor.DARK_AQUA + " You!");
					else
						online.sendMessage(ChatColor.DARK_AQUA + "(Someone) " + ChatColor.RESET + action
								+ ChatColor.DARK_AQUA + " You!");
				} else if (playerexec && online == ((Player) executor)) {
					online.sendMessage(
							ChatColor.DARK_AQUA + "You " + ChatColor.RESET + action + " " + targetDisplayname + "!");
				} else if (online.hasPermission(permission)) {
					online.sendMessage(executorname + " " + action + " " + target.getDisplayName() + "!");
				} else {
					online.sendMessage(
							ChatColor.DARK_AQUA + "(Someone) " + ChatColor.RESET + action + " " + targetDisplayname);
				}
			}
			break;
		case 2:
			for (Player online : Main.getInstance().getServer().getOnlinePlayers()) {
				if (online == target) {
					if (playerexec && online == executor)
						online.sendMessage(ChatColor.DARK_AQUA + "You " + ChatColor.RESET + action + ChatColor.DARK_AQUA
								+ " Yourself!");
					else
						online.sendMessage(executorname + " " + action + ChatColor.DARK_AQUA + " You!");
				} else if (playerexec && online == (Player) executor) {
					online.sendMessage(ChatColor.DARK_AQUA + "You " + action + " " + targetDisplayname + "!");
				} else
					online.sendMessage(executorname + " " + action + " " + targetDisplayname + "!");
			}
			break;
		default:
			Bukkit.getConsoleSender().sendMessage(Chat.prefix() + ChatColor.RED
					+ " Notification level not set up correctly!  Please check config.yml!");
		}
	}
}
