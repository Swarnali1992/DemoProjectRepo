package testUI;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.annotations.Test;

import base.BaseTest;
import utils.ConfigReaderUtils;
import utils.DatabaseUtils;

public class DatabaseTest {
	//System.out.println("Database");
	
	@Test
    public void dbTest() {
    	System.out.println("We are now in Database test");
    	System.out.println("Database Name : " + BaseTest.dbName);
    	
        // Example database credentials
        String dbUrl = "jdbc:mysql://localhost:3306/yourdatabase";
        String dbUsername = "yourusername";
        String dbPassword = "yourpassword";

//        try {
//            // Connect to the database
//            DatabaseUtils.connectToDatabase(dbUrl, dbUsername, dbPassword);
//
//            // Execute query
//            String query = "SELECT * FROM users WHERE username = 'testuser'";
//            ResultSet resultSet = DatabaseUtils.executeQuery(query);
//
//            // Process and store query result
//            while (resultSet.next()) {
//                String username = resultSet.getString("username");
//                String email = resultSet.getString("email");
//                System.out.println("Username: " + username + ", Email: " + email);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            // Close the database connection
//            DatabaseUtils.closeConnection();
//            }
    }
}
