package me.nick_perry14.Bukkit_ULX.libraries;

import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import me.nick_perry14.Bukkit_ULX.Main;

public final class ULXPlayer {
	private ULXPlayer() {

	}

	/**
	 * Gets the specified online server player. Also respects shortcuts such as @,
	 * ^, and $
	 * 
	 * @param player
	 */
	public static Player getPlayer(Player requestor, String player) {
		Main instance = Main.getInstance();
		if (player == null)
			return null;
		// Format: $00000000-0000-0000-0000-000000000000 (Minecraft UUID)
		if (player.matches("^\\$[a-fA-F0-9]{8}-([a-fA-F0-9]{4}-){3}[a-fA-F0-9]{12}$")) {
			// Sets the string to lowercase, and removes all - and the leading $
			player = player.toLowerCase().substring(1);
			return instance.getServer().getPlayer(UUID.fromString(player));
		}
		if (player.matches("^\\$[a-fA-F0-9]{32}$")) {
			player = player.toLowerCase().substring(1);
			String uuid = player.substring(0, 8) + "-" + player.substring(8, 12) + "-" + player.substring(12, 16) + "-"
					+ player.substring(16, 20) + "-" + player.substring(20);
			return instance.getServer().getPlayer(UUID.fromString(uuid));
		}
		// Self shortcut
		if (player.equals("^") && requestor != null) {
			return requestor;
		}
		// Looking at shortcut
		if (player.equals("@")) {
			return getLookingAt(requestor);
		}
		return instance.getServer().getPlayer(player);
	}

	/**
	 * Returns basic information about a player
	 * 
	 * @param player Player to get info about
	 * @return String containing formatted information.
	 */
	public static String getPlayerInfo(Player player) {
		StringBuilder info = new StringBuilder("Player info for ");
		info.append(player.getDisplayName());
		info.append("\n");
		info.append("UUID: " + player.getUniqueId().toString());
		info.append("\n");
		info.append("IP: " + player.getAddress().getAddress().getHostAddress());
		info.append("\n");
		info.append("Raw Name: " + player.getName());
		return info.toString();
	}

	/**
	 * Internal method to determine player that the given player is looking at
	 * Adapted from https://github.com/YourAverageCamper/CrosshairCommand
	 * 
	 * @param observer Person in which the line of sight should be referenced from
	 * @return Player (or null) that the observer is looking at
	 * 
	 */
	private static Player getLookingAt(Player observer) {
		Block targetBlock = observer.getTargetBlock(null, 50);
		Location blockLoc = targetBlock.getLocation();
		double bx = blockLoc.getX();
		double by = blockLoc.getY();
		double bz = blockLoc.getZ();
		List<Entity> nearby = observer.getNearbyEntities(100, 100, 100);

		for (Entity entity : nearby) {
			if (entity instanceof Player) {
				Location loc = entity.getLocation();
				double ex = loc.getX();
				double ey = loc.getY();
				double ez = loc.getZ();
				if ((bx - 1.5 <= ex && ex <= bx + 2) && (bz - 1.5 <= ez && ez <= bz + 2)
						&& (by - 1 <= ey && ey <= by + 2.5)) {
					return (Player) entity;
				}
			}
		}
		return null;
	}
}
