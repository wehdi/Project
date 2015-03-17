package Projet.Bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class StartBdd {

	private final String url = "jdbc:mysql://localhost:3306/mydb";
	private final String login = "root";
	private final String mdp = "hdime";
	// private String sql = "SELECT Jour FROM planning";
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

	public ArrayList<String> getDay() throws SQLException {
		String sql = "SELECT Jour FROM planning";
		this.stat = connection.createStatement();
		this.rs = this.stat.executeQuery(sql);
		ArrayList<String> data = new ArrayList<>();
		while (this.rs.next()) {
			String prof = rs.getString("Jour");
			data.add(prof);
		}
		return data;
	}
	
	public ArrayList<String> getHeur() throws SQLException {
		String sql = "SELECT Heur FROM planning";
		this.stat = connection.createStatement();
		this.rs = this.stat.executeQuery(sql);
		ArrayList<String> data = new ArrayList<>();
		while (this.rs.next()) {
			String prof = rs.getString("Heur");
			data.add(prof);
		}
		return data;
	}
	
	public ArrayList<String> getModule() throws SQLException {
		String sql = "SELECT Module FROM planning";
		this.stat = connection.createStatement();
		this.rs = this.stat.executeQuery(sql);
		ArrayList<String> data = new ArrayList<>();
		while (this.rs.next()) {
			String prof = rs.getString("Module");
			data.add(prof);
		}
		return data;
	}

}
