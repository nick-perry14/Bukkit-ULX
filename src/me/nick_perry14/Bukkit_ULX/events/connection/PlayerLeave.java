package me.nick_perry14.Bukkit_ULX.events.connection;

import java.util.Queue;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {

	private static Queue<String> recentDC;

	/**
	 * This method logs recent disconnects
	 * 
	 * @param e Quit Event
	 */
	@EventHandler
	public void logDC(PlayerQuitEvent e) {
		String logString = e.getPlayer().getName() + " - " + e.getPlayer().getUniqueId().toString();
		if (!recentDC.contains(logString)) {
			recentDC.add(logString);
			if (recentDC.size() > 5)
				recentDC.remove();
		}
	}

	/**
	 * Returns the list containing information about recently disconnected players.
	 * @return
	 */
	public static String[] getRecentDC() {
		return (String[]) recentDC.toArray();
	}
}
