package me.nick_perry14.Bukkit_ULX.events;

import me.nick_perry14.Bukkit_ULX.Main;
import me.nick_perry14.Bukkit_ULX.events.connection.PlayerJoin;
import me.nick_perry14.Bukkit_ULX.events.connection.PlayerLeave;

public final class EventLoader {
	private EventLoader() {
		
	}
	/**
	 * Method to load all event listeners
	 * @param instance JavaPlugin Instance
	 */
	public static void loadEvents(Main instance) {
		instance.getPluginLoader().createRegisteredListeners(new PlayerLeave(), instance);
		instance.getPluginLoader().createRegisteredListeners(new PlayerJoin(), instance);
	}
}
