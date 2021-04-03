package me.nick_perry14.Bukkit_ULX.events.connection;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.nick_perry14.Bukkit_ULX.libraries.ULXPlayer;

public class PlayerJoin implements Listener {
	public void addImmunities(PlayerJoinEvent e) {
		ULXPlayer.addPlayerImmunity(e.getPlayer());
	}
}
