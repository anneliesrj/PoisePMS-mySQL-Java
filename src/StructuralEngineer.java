import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StructuralEngineer {
    // Display existing structural engineers
    public static void displayExistingStructuralEngineers(Connection connection) throws SQLException {
        String query = "SELECT * FROM StructuralEngineer";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt("ENG_ID"));
            System.out.println("Name: " + resultSet.getString("ENG_NAME"));
            System.out.println("Email: " + resultSet.getString("ENG_EMAIL"));
            System.out.println("Telephone: " + resultSet.getString("ENG_TEL"));
            System.out.println("Address: " + resultSet.getString("ENG_ADDRESS"));
            System.out.println();
        }

        resultSet.close();
        preparedStatement.close();
    }

    // Check if the specified structural engineer exists
    public static boolean checkStructuralEngineerExists(Connection connection, int engineerId) throws SQLException {
        String query = "SELECT * FROM StructuralEngineer WHERE ENG_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, engineerId);
        ResultSet resultSet = preparedStatement.executeQuery();

        boolean exists = resultSet.next();

        resultSet.close();
        preparedStatement.close();

        return exists;
    }

 // Add a new structural engineer
    public static void addNewEngineer(Scanner scanner, Connection connection) throws SQLException {
        System.out.print("Enter the engineer's name: ");
        scanner.nextLine(); 
        String engineerName = scanner.nextLine();

        System.out.print("Enter the engineer's email: ");
        String engineerEmail = scanner.nextLine();

        System.out.print("Enter the engineer's telephone number: ");
        String engineerTel = scanner.nextLine();
        
        System.out.print("Enter the engineer's address: ");
        String engineerAddress = scanner.nextLine();

        String insertQuery = "INSERT INTO StructuralEngineer (ENG_NAME, ENG_EMAIL, ENG_TEL, ENG_ADDRESS) VALUES (?, ?, ?, ?)";
        PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
        insertStatement.setString(1, engineerName);
        insertStatement.setString(2, engineerEmail);
        insertStatement.setString(3, engineerTel);
        insertStatement.setString(4, engineerAddress);

        int rowsAffected = insertStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Structural Engineer added successfully.");
        } else {
            System.out.println("Failed to add Structural Engineer.");
        }

        insertStatement.close();
    }


    // Get the ID of the last inserted structural engineer
    public static int getLastInsertedId(Connection connection) throws SQLException {
        String query = "SELECT MAX(ENG_ID) FROM StructuralEngineer";
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
