package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {
    private static Connection connection;

    
    /**
     * Establishes a connection to the database using the provided connection details.
     * 
     * This method attempts to connect to a database using the JDBC `DriverManager` class. It checks if a connection already exists 
     * and if it is still open. If not, it creates a new connection using the provided URL, username, and password. 
     * The method logs a success message if the connection is established successfully.
     * 
     * @param url The JDBC URL of the database to connect to. This URL specifies the database server, database name, and any 
     *            connection parameters (e.g., `jdbc:mysql://localhost:3306/mydb`).
     * @param username The username used to authenticate the database connection.
     * @param password The password corresponding to the provided username for authentication.
     * @return A Connection object representing the connection to the database.
     * @throws RuntimeException If the connection fails, a RuntimeException is thrown with an appropriate error message.
     * @throws SQLException If there is an issue while establishing the connection, such as invalid credentials or a network problem.
     */
    
    
    // Establish database connection
    public static Connection connectToDatabase(String url, String username, String password) throws RuntimeException{
        try {
        	System.out.println("Database Name : " + url);
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Database connection established successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database: " + e.getMessage());
        }
        return connection;
    }

    
    /**
     * Executes the provided SQL query on the established database connection and returns the result.
     * 
     * This method uses the  Statement object to execute a query that returns a result set (such as a `SELECT` query).
     * It checks whether the database connection is established and open before attempting to execute the query. If the connection
     * is not available or closed, it throws a {@link RuntimeException}. If an error occurs while executing the query, it throws
     * a RuntimeException with the relevant error message.
     * 
     * @param query The SQL query string to be executed. Typically, this would be a `SELECT` query, but any valid SQL query can
     *              be used.
     * @return A ResultSet object containing the data returned by the query.
     * @throws RuntimeException If the connection is not established or if an error occurs while executing the query.
     * @throws SQLException If an error occurs while executing the SQL query, such as a syntax error or other database-related issue.
     */
    
    // Execute a query and return the ResultSet
    public static ResultSet executeQuery(String query) {
        ResultSet resultSet = null;
        try {
            if (connection == null || connection.isClosed()) {
                throw new RuntimeException("Database connection is not established.");
            }
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to execute query: " + e.getMessage());
        }
        return resultSet;
    }

    
    /**
     * Closes the database connection if it is open.
     * 
     * This method checks whether the current database connection is valid (not null and open). 
     * If the connection is open, it is closed using the Connection#close()} method. 
     * After closing, a message is printed to indicate that the connection has been closed successfully.
     * 
     * If any error occurs during the process of closing the connection, the exception is caught 
     * and its stack trace is printed for debugging purposes.
     * 
     * @return None
     * @throws SQLException If there is an error while closing the database connection.
     */
    
    // Close the database connection
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
}