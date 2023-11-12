import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ProjectManager {
    // Display existing project managers
    public static void displayExistingProjectManagers(Connection connection) throws SQLException {
        String query = "SELECT * FROM ProjectManager";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt("MAN_ID"));
            System.out.println("Name: " + resultSet.getString("MAN_NAME"));
            System.out.println("Email: " + resultSet.getString("MAN_EMAIL"));
            System.out.println("Telephone: " + resultSet.getString("MAN_TEL"));
            System.out.println("Address: " + resultSet.getString("MAN_ADDRESS"));
            System.out.println();
        }

        resultSet.close();
        preparedStatement.close();
    }

    // Check if the specified project manager exists
    public static boolean checkProjectManagerExists(Connection connection, int projectManagerId) throws SQLException {
        String query = "SELECT * FROM ProjectManager WHERE MAN_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, projectManagerId);
        ResultSet resultSet = preparedStatement.executeQuery();

        boolean exists = resultSet.next();

        resultSet.close();
        preparedStatement.close();

        return exists;
    }

    // Add a new project manager
    public static void addNewProjectManager(Scanner scanner, Connection connection) throws SQLException {
        System.out.print("Enter the project manager's name: ");
        scanner.nextLine(); 
        String managerName = scanner.nextLine();

        System.out.print("Enter the project manager's email: ");
        String managerEmail = scanner.nextLine();

        System.out.print("Enter the project manager's telephone number: ");
        String managerTel = scanner.nextLine();

        System.out.print("Enter the project manager's address: ");
        String managerAddress = scanner.nextLine();
        
        
        String insertQuery = "INSERT INTO ProjectManager (MAN_NAME, MAN_EMAIL, MAN_TEL, MAN_ADDRESS) VALUES (?, ?, ?, ?)";
        PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
        insertStatement.setString(1, managerName);
        insertStatement.setString(2, managerEmail);
        insertStatement.setString(3, managerTel);
        insertStatement.setString(4, managerAddress);

        int rowsAffected = insertStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Project Manager added successfully.");
        } else {
            System.out.println("Failed to add Project Manager.");
        }

        insertStatement.close();
    }

    // Get the ID of the last inserted project manager
    public static int getLastInsertedId(Connection connection) throws SQLException {
        String query = "SELECT MAX(MAN_ID) FROM ProjectManager";
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
