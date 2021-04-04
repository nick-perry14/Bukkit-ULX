package me.nick_perry14.Bukkit_ULX.commands;

import me.nick_perry14.Bukkit_ULX.Main;
import me.nick_perry14.Bukkit_ULX.commands.mod.RecentDC;
import me.nick_perry14.Bukkit_ULX.commands.mod.Spectate;
import me.nick_perry14.Bukkit_ULX.commands.teleportation.Bring;
import me.nick_perry14.Bukkit_ULX.commands.teleportation.GoTo;
import me.nick_perry14.Bukkit_ULX.commands.teleportation.Return;
import me.nick_perry14.Bukkit_ULX.commands.teleportation.TP;

/**
 * A Public Class only to hold the method that loads the command
 * 
 * @author Nick Perry
 *
 */
public final class CommandLoader {
	private CommandLoader() {
	}

	public static void loadCommands(Main instance) {
		instance.getCommand("teleport").setExecutor(new TP());
		instance.getCommand("bring").setExecutor(new Bring());
		instance.getCommand("goto").setExecutor(new GoTo());
		instance.getCommand("return").setExecutor(new Return());
		instance.getCommand("recentdc").setExecutor(new RecentDC());
		instance.getCommand("spectate").setExecutor(new Spectate());
	}
}
