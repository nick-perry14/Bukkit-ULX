package me.nick_perry14.Bukkit_ULX.commands.teleportation;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.nick_perry14.Bukkit_ULX.libraries.Chat;

public class GoTo implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Chat.playerOnly());
			return true;
		}
		
		return true;
	}
}
