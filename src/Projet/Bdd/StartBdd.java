package Projet.Bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class StartBdd {

	private final String url = "jdbc:mysql://localhost:3306/mydb";
	private final String login = "root";
	private final String mdp = "hdime";
	//private String sql = "SELECT Jour FROM planning";
	private Connection connection = null;
	private java.sql.Statement stat = null;
	private java.sql.ResultSet rs = null;

	// private final String serviceName = "BDD";

	public StartBdd() {
	}

	public void openConecction() {
		// load driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Erreur Driver" + e);
		}

		// Recuperation de la connexion
		try {
			connection = DriverManager.getConnection(url, login, mdp);
			System.out.println("Connexion etablie");

		} catch (SQLException e) {
			System.err.println("Erreur dans la recup de connexion" + e);
		}

		// Creation d'un statement
		try {
			this.stat = connection.createStatement();

			System.out.println("Connexion ok");
			// read("me");

		} catch (SQLException e) {
			System.err.println("Erreur dans la creation du statement" + e);
		}
	}

	public void closeConnection() throws SQLException {
		connection.close();
		stat.close();
	}

	public ArrayList read(String sql) throws SQLException {
		this.stat = connection.createStatement();
		this.rs = this.stat.executeQuery(sql);
		ArrayList<String> data = new ArrayList<>();
		// String prof = "mehdi";
		while (this.rs.next()) {
			String prof = rs.getString("Jour");
			//System.out.println("depuis star : " + data);
			data.add(prof);

		}
		return data;

	}

}
