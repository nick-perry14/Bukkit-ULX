package me.nick_perry14.Bukkit_ULX.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.UUID;

public class ULXSQL {
	private static String host;
	private static String username;
	private static String password;
	private static String database;
	private static String prefix;
	static Connection connect;
	Properties connectionProps = new Properties();

	private ULXSQL() {

	}

	public static void setHost(String host) {
		ULXSQL.host = host;
		refreshConnection();
	}

	public static void setUsername(String username) {
		ULXSQL.username = username;
		refreshConnection();
	}

	public static void setPassword(String password) {
		ULXSQL.password = password;
		refreshConnection();
	}

	public static void refreshConnection() {
		try {
			connect = DriverManager.getConnection("jdbc:sqlserver://" + host + ";database=" + database + ";user="
					+ username + ";password=" + password + ";encrypt=true;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void setUpSQL(String host, String username, String password) {
		ULXSQL.host = host;
		ULXSQL.username = username;
		ULXSQL.password = password;
		refreshConnection();
		createTables();
	}

	private static void createTables() {
		try {
			connect.prepareStatement("CREATE TABLE IF NOT EXISTS `" + prefix
					+ "bans` (`id` INT NOT NULL AUTO_INCREMENT,`date` TIMESTAMP NOT NULL,`sender` VARCHAR(36) DEFAULT NULL,`receiver` VARCHAR(36) NOT NULL,`reason` LONGTEXT NOT NULL,`expiration` TIMESTAMP,`unbanned` BOOLEAN NOT NULL DEFAULT FALSE,PRIMARY KEY (`id`));")
					.execute();
			connect.prepareStatement("CREATE TABLE IF NOT EXISTS `" + prefix
					+ "warns` (`id` INT NOT NULL AUTO_INCREMENT,`date` TIMESTAMP NOT NULL,`sender` VARCHAR(36) DEFAULT NULL,`receiver` VARCHAR(36) NOT NULL,`reason` LONGTEXT NOT NULL,`expiration` TIMESTAMP ,`unwarned` BOOLEAN NOT NULL DEFAULT FALSE,PRIMARY KEY (`id`));")
					.execute();
			connect.prepareStatement("CREATE TABLE IF NOT EXISTS `" + prefix
					+ "kicks` (`id` INT NOT NULL AUTO_INCREMENT,`date` TIMESTAMP  NOT NULL,`sender` VARCHAR(36) DEFAULT NULL,`receiver` VARCHAR(36) NOT NULL,`reason` LONGTEXT NOT NULL,PRIMARY KEY (`id`));")
					.execute();
			connect.prepareStatement("CREATE TABLE IF NOT EXISTS `" + prefix
					+ "names` (`name` VARCHAR NOT NULL,`uuid` VARCHAR(36) NOT NULL,PRIMARY KEY (`uuid`));").execute();
			connect.prepareStatement("CREATE TABLE IF NOT EXISTS `" + prefix
					+ "ips` (`ip` VARCHAR(15) NOT NULL,`uuid` VARCHAR(36) NOT NULL,PRIMARY KEY (`ip`,`uuid`));")
					.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean isBanned(UUID uuid) {
		try {
			PreparedStatement query = connect.prepareStatement(
					"SELECT COUNT(*) FROM `bans` WHERE `receiver` = \"?\" AND `expiration` > now() AND `unbanned` = FALSE;");
			query.setString(1, uuid.toString());
			ResultSet res = query.executeQuery();
			query.close();
			res.next();
			int count = res.getInt(1);
			return count > 0;
		} catch (SQLException e) {
			return false;
		}
	}
}
