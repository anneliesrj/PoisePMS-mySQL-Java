// 1. Find a project

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FindAProject {
	
	// Method to find a project
    void findProject(Scanner scanner, Connection connection) {
        System.out.println("Find a Project\n");

        // Prompt user to select search option
        try {
            System.out.println("Search Options:");
            System.out.println("1. Find by project number");
            System.out.println("2. Find by project name");
            System.out.print("Enter your choice: ");

            int searchOption = scanner.nextInt();
            scanner.nextLine();

            switch (searchOption) {
                case 1:
                    System.out.print("Enter the project number: ");
                    int projectNumber = scanner.nextInt();
                    scanner.nextLine();

                    // Execute to find the project by project number
                    String queryByNumber = "SELECT * FROM Project WHERE PROJ_NO = ?";
                    PreparedStatement preparedStatementByNumber = connection.prepareStatement(queryByNumber);
                    preparedStatementByNumber.setInt(1, projectNumber);

                    ResultSet resultSetByNumber = preparedStatementByNumber.executeQuery();

                    // Process the query results
                    if (resultSetByNumber.next()) {
                        // Project found
                        displayProjectDetails(resultSetByNumber);
                    } else {
                        System.out.println("No project found with the specified project number.");
                    }

                    preparedStatementByNumber.close();
                    resultSetByNumber.close();

                    break;

                case 2:
                    System.out.print("Enter the project name: ");
                    String projectName = scanner.nextLine();

                    // Execute the query to find the project by project name
                    String queryByName = "SELECT * FROM Project WHERE PROJ_NAME LIKE ?";
                    PreparedStatement preparedStatementByName = connection.prepareStatement(queryByName);
                    preparedStatementByName.setString(1, "%" + projectName + "%");

                    ResultSet resultSetByName = preparedStatementByName.executeQuery();

                    // Process the query results
                    if (resultSetByName.next()) {
                        // Project found
                        System.out.println("Matching Projects:\n");
                        do {
                            displayProjectDetails(resultSetByName);
                        } while (resultSetByName.next());
                    } else {
                        System.out.println("No project found with the specified project name.");
                    }

                    preparedStatementByName.close();
                    resultSetByName.close();

                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Display project details
    private void displayProjectDetails(ResultSet resultSet) throws SQLException {
        System.out.println("Project Number: " + resultSet.getInt("PROJ_NO"));
        System.out.println("Project Name: " + resultSet.getString("PROJ_NAME"));
        System.out.println("Building Type: " + resultSet.getString("BUILDING_TYPE"));
        System.out.println("Address: " + resultSet.getString("PROJ_ADDRESS"));
        System.out.println("ERF Number: " + resultSet.getString("ERF_NO"));
        System.out.println("Total Fee: " + resultSet.getDouble("TOTAL_FEE"));
        System.out.println("Total Paid: " + resultSet.getDouble("TOTAL_PAID"));
        System.out.println("Deadline: " + resultSet.getString("PROJ_DEADLINE"));
        System.out.println("Structural Engineer ID: " + resultSet.getInt("engineer_id"));
        System.out.println("Project Manager ID: " + resultSet.getInt("manager_id"));
        System.out.println("Architect ID: " + resultSet.getInt("architect_id"));
        System.out.println("Contractor ID: " + resultSet.getInt("contractor_id"));
        System.out.println("Customer ID: " + resultSet.getInt("customer_id"));
        System.out.println();
    }


}
