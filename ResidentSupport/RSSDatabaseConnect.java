/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Kyle Ranaghan
 */
public class RSSDatabaseConnect {
    private Connection conn;
    
    public RSSDatabaseConnect(){
        conn = null;
    }
    
    public boolean connect(String filename){
        try{
            String url = "jdbc:sqlite:" + filename;
            conn = DriverManager.getConnection(url);
            //System.out.println("Connection to database has been established.");
            
        }
        catch(SQLException error){
            System.out.println("Failed to connect to database.");
            System.out.println(error.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean runSQL(String sql){
        if(conn == null){
            System.out.println("There is no database connection. Cannot run SQL");
            return false;
        }
        try{
            Statement sqlStatement = conn.createStatement();
            sqlStatement.execute(sql);
        }
        catch(SQLException error){
            System.out.println("Failed to execute SQL.");
            System.out.println(error.getMessage());
            return false;
        }
        return true;
    }
    
    public ResultSet runSQLQuery(String sql){
        ResultSet result;
        if(conn == null){
            System.out.println("There is no database connection. Cannot run SQL");
            return null;
        }
        try{
            Statement sqlStatement = conn.createStatement();
            result = sqlStatement.executeQuery(sql);
        }
        catch(SQLException e){
	   System.out.println("Failed to execute SQL");
           System.out.println(e.getMessage());
           return null;
        }
        return result;
        
    }
    
    public Connection getConnection(){
        if(conn == null){
            System.out.println("No database connection.");
            return null;
        }
        return conn;
    }
    
}