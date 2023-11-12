import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Customer {
    // Display existing customers
    public static void displayExistingCustomers(Connection connection) throws SQLException {
        String query = "SELECT * FROM Customer";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt("CUST_ID"));
            System.out.println("Name: " + resultSet.getString("CUST_NAME"));
            System.out.println("Email: " + resultSet.getString("CUST_EMAIL"));
            System.out.println("Telephone: " + resultSet.getString("CUST_TEL"));
            System.out.println("Address: " + resultSet.getString("CUST_ADDRESS"));
            System.out.println();
        }

        resultSet.close();
        preparedStatement.close();
    }

 // Check if the specified customer exists
    public static boolean checkCustomerExists(Connection connection, int customerId) throws SQLException {
        String query = "SELECT * FROM Customer WHERE CUST_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, customerId);
        ResultSet resultSet = preparedStatement.executeQuery();

        boolean exists = resultSet.next();

        resultSet.close();
        preparedStatement.close();

        return exists;
    }


 // Add a new customer
    public static void addNewCustomer(Scanner scanner, Connection connection) throws SQLException {
        System.out.print("Enter the customer's name: ");
        scanner.nextLine(); 
        String customerName = scanner.nextLine();

        System.out.print("Enter the customer's email: ");
        String customerEmail = scanner.nextLine();

        System.out.print("Enter the customer's telephone number: ");
        String customerTel = scanner.nextLine();

        System.out.print("Enter the customer's address: ");
        String customerAddress = scanner.nextLine();

        String insertQuery = "INSERT INTO Customer (CUST_NAME, CUST_EMAIL, CUST_TEL, CUST_ADDRESS) VALUES (?, ?, ?, ?)";
        PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
        insertStatement.setString(1, customerName);
        insertStatement.setString(2, customerEmail);
        insertStatement.setString(3, customerTel);
        insertStatement.setString(4, customerAddress);

        int rowsAffected = insertStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Customer added successfully.");
        } else {
            System.out.println("Failed to add Customer.");
        }

        insertStatement.close();
    }


    // Get the ID of the last inserted customer
    public static int getLastInsertedId(Connection connection) throws SQLException {
        String query = "SELECT MAX(CUST_ID) FROM Customer";
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