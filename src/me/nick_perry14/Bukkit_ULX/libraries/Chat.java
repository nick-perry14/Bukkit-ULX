package me.nick_perry14.Bukkit_ULX.libraries;

import org.bukkit.ChatColor;

public final class Chat {
	private Chat() {
		
	}
	
	public static String prefix() {
		return ChatColor.DARK_GRAY + "[" + ChatColor.RED + "BULX" + ChatColor.DARK_GRAY + "] " + ChatColor.RESET;
	}
}
