import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Architect {
    // Display existing architects
    public static void displayExistingArchitects(Connection connection) throws SQLException {
        String query = "SELECT * FROM Architect";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt("ARCH_ID"));
            System.out.println("Name: " + resultSet.getString("ARCH_NAME"));
            System.out.println("Email: " + resultSet.getString("ARCH_EMAIL"));
            System.out.println("Telephone: " + resultSet.getString("ARCH_TEL"));
            System.out.println("Address: " + resultSet.getString("ARCH_ADDRESS"));
            System.out.println();
        }

        resultSet.close();
        preparedStatement.close();
    }

    // Check if the specified architect exists
    public static boolean checkArchitectExists(Connection connection, int architectId) throws SQLException {
        String query = "SELECT * FROM Architect WHERE ARCH_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, architectId);
        ResultSet resultSet = preparedStatement.executeQuery();

        boolean exists = resultSet.next();

        resultSet.close();
        preparedStatement.close();

        return exists;
    }

    // Add a new architect
    public static void addNewArchitect(Scanner scanner, Connection connection) throws SQLException {
        System.out.print("Enter the architect's name: ");
        scanner.nextLine(); 
        String architectName = scanner.nextLine();

        System.out.print("Enter the architect's email: ");
        String architectEmail = scanner.nextLine();

        System.out.print("Enter the architect's telephone number: ");
        String architectTel = scanner.nextLine();
        
        System.out.print("Enter the architect's address: ");
        String architectAddress = scanner.nextLine();

        String insertQuery = "INSERT INTO Architect (ARCH_NAME, ARCH_EMAIL, ARCH_TEL, ARCH_ADDRESS) VALUES (?, ?, ?, ?)";
        PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
        insertStatement.setString(1, architectName);
        insertStatement.setString(2, architectEmail);
        insertStatement.setString(3, architectTel);
        insertStatement.setString(4, architectAddress);

        int rowsAffected = insertStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Architect added successfully.");
        } else {
            System.out.println("Failed to add Architect.");
        }

        insertStatement.close();
    }

    // Get the ID of the last inserted architect
    public static int getLastInsertedId(Connection connection) throws SQLException {
        String query = "SELECT MAX(ARCH_ID) FROM Architect";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        int lastInsertedId = -1;

        if (resultSet.next()) {
            lastInsertedId = resultSet.getInt(1);
        }

        resultSet.close();
        statement.close();

        return lastInsertedId;
    }
}