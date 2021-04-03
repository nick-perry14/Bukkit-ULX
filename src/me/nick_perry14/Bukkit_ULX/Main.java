package me.nick_perry14.Bukkit_ULX;

import org.bukkit.plugin.java.JavaPlugin;

import me.nick_perry14.Bukkit_ULX.commands.CommandLoader;

public class Main extends JavaPlugin{
	private static Main instance;
	
	public static Main getInstance() {
		return instance;
	}
	
	public void onEnable() {
		instance = this;
		CommandLoader.loadCommands(this);
	}
	
	public void onDisable() {
		
	}
}
