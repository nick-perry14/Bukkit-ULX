package me.nick_perry14.Bukkit_ULX;

import org.bukkit.plugin.java.JavaPlugin;

import me.nick_perry14.Bukkit_ULX.commands.CommandLoader;
import me.nick_perry14.Bukkit_ULX.database.ULXSQL;
import me.nick_perry14.Bukkit_ULX.events.EventLoader;

public class Main extends JavaPlugin {
	private static Main instance;

	public static Main getInstance() {
		return instance;
	}

	public void onEnable() {
		instance = this;
		CommandLoader.loadCommands(this);
		EventLoader.loadEvents(this);
		getServer().getLogger().info("ULX Bukkit Enabled: Created by Nick Perry");
	}

	public void loadConfig() {
		ULXSQL.setUpSQL(getConfig().getString("host"), getConfig().getString("username"),
				getConfig().getString("password"));
	}

	public void onDisable() {

	}
}
