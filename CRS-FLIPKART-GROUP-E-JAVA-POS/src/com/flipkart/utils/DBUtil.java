package com.flipkart.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
private static Connection connection = null;
	
	public static Connection getConnection() {
		
        if (connection != null)
            return connection;
        else {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crsapp","root","Bai4ish4!@#%");
            } catch (SQLException e) {
                e.printStackTrace();
            } 
            return connection;
        }
	}
	
	public static boolean closeConnection() {
        try {
            if (connection != null)
                connection.close();
        } 
        catch (SQLException se) {
        	return false;
        }
        return true;
	}
}
