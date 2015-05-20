package Projet.Bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

public class StartBdd {
	/**
	 * Classe de la gestion de la base donnees
	 */
	private final String url = "jdbc:mysql://localhost:3306/mydb";
	private final String login = "root";
	private final String mdp = "hdime";
	private Connection connection = null;
	private java.sql.Statement stat = null;
	private java.sql.ResultSet rs = null;

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

		} catch (SQLException e) {
			System.err.println("Erreur dans la creation du statement" + e);
		}
	}

	public void closeConnection() throws SQLException {
		connection.close();
		stat.close();
	}
	
	
	public int getUserName(String name) throws SQLException {
		int x = 0;
		String sql = "SELECT * FROM users WHERE Nom = '" + name + "'";
		System.out.println(name);
		
		this.stat = connection.createStatement();
		this.rs = this.stat.executeQuery(sql);

		while (this.rs.next()) {
			
			x = rs.getRow();
			
		}
		return x;
	}

	public int getPassword(String pass) throws SQLException {
		System.out.println(pass);
		String sql = "SELECT * FROM users WHERE mdp = '" + pass + "'";
		int x = 0;
		this.stat = connection.createStatement();
		this.rs = this.stat.executeQuery(sql);

		while (this.rs.next()) {
			
			x = rs.getRow();
		}
		return x;
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
		String sql = "SELECT * FROM planning";
		this.stat = connection.createStatement();
		this.rs = this.stat.executeQuery(sql);
		ArrayList<String> data = new ArrayList<>();
		while (this.rs.next()) {
			String s = rs.getString("Module");
			String t = rs.getString("Type");
			// String n = rs.getString("Prof");
			data.add(s + " -" + t.toUpperCase());
		}
		return data;
	}

	/**
	 * 
	 * @param day
	 * @param module
	 * @param type
	 * @param heur
	 * @throws Exception
	 */
	public void insetInPlanning(String day, String module, String type,
			String heur) throws Exception {

		String sql = "INSERT INTO planning (Jour,Heur,Module,Type,Prof,Groupe,Salle)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement preparedStatement = (PreparedStatement) this.connection
				.prepareStatement(sql);
		preparedStatement.setString(1, day);
		preparedStatement.setString(2, heur);
		preparedStatement.setString(3, module);
		preparedStatement.setString(4, type);
		preparedStatement.setString(5, "");
		preparedStatement.setString(6, "Test3");
		preparedStatement.setString(7, "Test3");
		preparedStatement.executeUpdate();
		// this.stat = connection.createStatement();
		// this.rs = this.stat.executeUpdate(sql);
	}

}
