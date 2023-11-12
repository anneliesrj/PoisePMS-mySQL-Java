import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Contractor {
    // Display existing contractors
    public static void displayExistingContractor(Connection connection) throws SQLException {
        String query = "SELECT * FROM Contractor";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt("CONT_ID"));
            System.out.println("Name: " + resultSet.getString("CONTR_NAME"));
            System.out.println("Email: " + resultSet.getString("CONTR_EMAIL"));
            System.out.println("Telephone: " + resultSet.getString("CONTR_TEL"));
            System.out.println("Address: " + resultSet.getString("CONTR_ADDRESS"));
            System.out.println();
        }

        resultSet.close();
        preparedStatement.close();
    }

    // Check if the specified contractor exists
    public static boolean checkContractorExists(Connection connection, int contractorId) throws SQLException {
        String query = "SELECT * FROM Contractor WHERE CONT_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, contractorId);
        ResultSet resultSet = preparedStatement.executeQuery();

        boolean exists = resultSet.next();

        resultSet.close();
        preparedStatement.close();

        return exists;
    }

    // Add a new contractor
    public static void addNewContractor(Scanner scanner, Connection connection) throws SQLException {
        System.out.print("Enter the contractor's name: ");
        scanner.nextLine(); 
        String contractorName = scanner.nextLine();

        System.out.print("Enter the contractor's email: ");
        String contractorEmail = scanner.nextLine();

        System.out.print("Enter the contractor's telephone number: ");
        String contractorTel = scanner.nextLine();
        
        System.out.print("Enter the contractor's address: ");
        String contractorAddress = scanner.nextLine();

        String insertQuery = "INSERT INTO Contractor (CONTR_NAME, CONTR_EMAIL, CONTR_TEL, CONTR_ADDRESS) VALUES (?, ?, ?, ?)";
        PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
        insertStatement.setString(1, contractorName);
        insertStatement.setString(2, contractorEmail);
        insertStatement.setString(3, contractorTel);
        insertStatement.setString(4, contractorAddress);

        int rowsAffected = insertStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Contractor added successfully.");
        } else {
            System.out.println("Failed to add Contractor.");
        }

        insertStatement.close();
    }

    // Get the ID of the last inserted contractor
    public static int getLastInsertedId(Connection connection) throws SQLException {
        String query = "SELECT MAX(CONT_ID) FROM Contractor";
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