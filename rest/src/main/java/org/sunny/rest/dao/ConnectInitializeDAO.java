package org.sunny.rest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class ConnectInitializeDAO {

	private Connection connect;
	private final String user;
	private final String password;
	private final String host;
	
	public ConnectInitializeDAO(String username, String password) {
		this(username, password, "localhost");
	}

	public ConnectInitializeDAO(String username, String password, String host) {
		this.user = Objects.requireNonNull(username);
		this.password = Objects.requireNonNull(password);
		this.host = Objects.requireNonNull(host);
	}

	public void connect() throws SQLException, ClassNotFoundException {
		// This will load the MySQL driver, each DB has its own driver
		Class.forName("com.mysql.jdbc.Driver");

		// Setup the connection with the DB
		String url = "jdbc:mysql://" + this.host + "/feedback?user=" + this.user + "&password=" + this.password;
		connect = DriverManager.getConnection(url);
	}

	public void disconnect() throws SQLException {
		if (connect != null) {
			connect.close();
		}
	}
}