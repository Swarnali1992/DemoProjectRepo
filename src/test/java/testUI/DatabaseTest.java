package testUI;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.annotations.Test;

import base.BaseTest;
import utils.ConfigReaderUtils;
import utils.DatabaseUtils;

public class DatabaseTest {
	//System.out.println("Database");
	/**
	 * Executes a database test to connect to a MySQL database, execute a query,
	 * and process the query result.
	 * 
	 * This method performs the following steps:
	 * 
	 *   Connects to a MySQL database using the specified URL, username, and password.
	 *   Executes a SQL query to fetch user information from the `users` table where the username is 'testuser'.
	 *   Processes and logs the query result (username and email) to the console.
	 *   Closes the database connection after processing the query result.
	 * 
	 * 
	 * The method handles SQLException if any database-related errors occur during the process.
	 * 
	 * @throws SQLException If a database access error occurs, such as issues with connecting to the database or executing the query.
	 * @return None
	 */
	@Test
    public void dbTest() {
    	System.out.println("We are now in Database test");
    	System.out.println("Database Name : " + BaseTest.dbName);
    	
        // Example database credentials
        String dbUrl = "jdbc:mysql://localhost:3306/yourdatabase";
        String dbUsername = "yourusername";
        String dbPassword = "yourpassword";

        try {
            // Connect to the database
            DatabaseUtils.connectToDatabase(dbUrl, dbUsername, dbPassword);

            // Execute query
            String query = "SELECT * FROM users WHERE username = 'testuser'";
            ResultSet resultSet = DatabaseUtils.executeQuery(query);

            // Process and store query result
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                System.out.println("Username: " + username + ", Email: " + email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection
            DatabaseUtils.closeConnection();
            }
    }
}
