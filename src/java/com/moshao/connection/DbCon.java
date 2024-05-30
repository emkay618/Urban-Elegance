package com.moshao.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbCon {

    // Static variable to hold the database connection
    private static Connection connection = null;

    // Method to get the database connection
    public static Connection getConnection(String path) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
        // Check if the connection is null or closed
        if (connection == null || connection.isClosed()) {
            // Load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connect to the MySQL server without specifying a database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            // Create the database if it doesn't exist
            createDatabase(connection);
            // Set the current database to 'urbanelegance2128084'
            connection.setCatalog("urbanelegance2128084");
            // Import the database dump file
            importDatabaseDump(path);
        }
        // Return the database connection
        return connection;
    }

    // Method to create the database
    private static void createDatabase(Connection conn) throws SQLException {
        try ( Statement stmt = conn.createStatement()) {
            // Execute the SQL command to create the database if it doesn't exist
            stmt.execute("CREATE DATABASE IF NOT EXISTS urbanelegance2128084");
        }
    }
    // Method to import the database dump file

    private static void importDatabaseDump(String path) throws IOException, InterruptedException {
        // Full path to the mysql executable
        String mysqlPath = "C:\\xampp\\mysql\\bin\\mysql.exe";
        // Command to execute the mysql client and import the dump file
        String[] executeCmd = new String[]{mysqlPath, "--user=root", "--password=", "urbanelegance2128084", "-e", "source " + path};
        // Execute the command
        Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
        // Wait for the process to complete
        int processComplete = runtimeProcess.waitFor();
        // Check if the import was successful
        if (processComplete == 0) {
            System.out.println("Backup restored successfully");
        } else {
            System.out.println("Could not restore the backup");
        }
    }

}
