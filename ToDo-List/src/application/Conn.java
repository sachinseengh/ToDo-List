package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conn {
	public Connection connection;
	public  Statement s;
	public Conn(){
		  String databaseURL = "jdbc:ucanaccess://C:/Users/sachi/OneDrive - Kathmandu BernHardt College/Documents/Eclipse Git/ToDo-List/ToDoList.accdb";

	        try { 
	        	
	        	
	        	connection = DriverManager.getConnection(databaseURL);
	            s = connection.createStatement();
	        	
	        
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}

    public static void main(String[] args) {
           new Conn();
    }
}
