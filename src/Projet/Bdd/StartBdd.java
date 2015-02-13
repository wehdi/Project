package Projet.Bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StartBdd {
	
	private final String url ="jdbc:mysql://localhost:3306/test";
	private final String login ="root";
	private final String mdp ="hdime";
	private String sql = null;
	private Connection connection = null;
	private java.sql.Statement stat = null;
	
	private final String serviceName ="BDD";
	
	public StartBdd() {
	}
	
	
public void openConecction(){
		//load driver
		try {Class.forName("com.mysql.jdbc.Driver");} 
		catch (ClassNotFoundException e) {System.err.println("Erreur Driver" +e);}
		
		//Recuperation de la connexion
		try {connection = DriverManager.getConnection(url, login, mdp);
				System.out.println("Connexion etablie");;} 
		catch (SQLException e) {System.err.println("Erreur dans la recup de connexion"+e);}
		
		//Creation d'un statement
		try {stat = connection.createStatement();
		
		System.out.println("Connexion ok");} 
		catch (SQLException e) {System.err.println("Erreur dans la creation du statement"+e);}
		}
		


public void closeConnection() throws SQLException{
	connection.close(); 
	stat.close();
	}	

}
