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
     * Executes a SQL query using the provided database connection and returns the resulting {@link ResultSet}.
     *
     * <p>This method checks if the provided {@link Connection} is valid and open. If the connection is closed or null, a {@link RuntimeException} is thrown.
     * It then creates a {@link Statement} to execute the query and returns the result as a {@link ResultSet}.
     * 
     * <p>If there is an error during query execution, a {@link RuntimeException} is thrown with a relevant message.
     *
     * @param query The SQL query to be executed. The query must be a valid SQL query in string format.
     * @param connection The {@link Connection} object representing the active database connection. 
     *                   It cannot be null or closed.
     * @return A {@link ResultSet} object containing the result of the executed query.
     * @throws RuntimeException if the database connection is not established, or if the query execution fails.
     */
    
    // Execute a query and return the ResultSet
    
    public static ResultSet executeQuery(String query,Connection connection) throws RuntimeException {
        ResultSet resultSet = null;
        try {
            if (connection == null || connection.isClosed()) {
                throw new RuntimeException("Database connection is not established.");
            }
         // Statement object to send the SQL statement to the Database
    		 Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to execute query: " + e.getMessage());
        }
        return resultSet;
    }

    
    /**
     * Closes the provided database {@link Connection} if it is open.
     * 
     * This method checks if the provided {@link Connection} is not {@code null} and is not already closed.
     * If the connection is open, it is closed, and a success message is printed to the console.
     * If an exception occurs during closing the connection, it is caught and printed, but no exception is thrown.
     *
     *
     * @param connection The Connection object representing the active database connection to be closed.
     *                   If the connection is {@code null} or already closed, it will not attempt to close it.
     *                   If the connection is closed successfully, a success message is printed to the console
     *                   If there is an issue while closing the connection, such as a network problem or invalid connection,
     *                   the exception is caught and printed to the console.
     * @throws RuntimeException if there is an issue while closing the connection, such as a network problem or invalid connection
     * @return None
     */
    
    // Close the database connection
    public static void closeConnection(Connection connection) {
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