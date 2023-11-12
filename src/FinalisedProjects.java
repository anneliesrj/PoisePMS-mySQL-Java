// 6. Show finalised projects

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FinalisedProjects {
    
    public static void showFinalisedProjects(Connection connection) throws SQLException {
        System.out.println("Finalised Projects:\n");

        // Execute the query to retrieve finalised projects
        String query = "SELECT * FROM Project WHERE CompletionDate IS NOT NULL";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        // Display the finalised projects
        while (resultSet.next()) {
            System.out.println("Project Number: " + resultSet.getInt("PROJ_NO"));
            System.out.println("Project Name: " + resultSet.getString("PROJ_NAME"));
            System.out.println("Building Type: " + resultSet.getString("BUILDING_TYPE"));
            System.out.println("Project Address: " + resultSet.getString("PROJ_ADDRESS"));
            System.out.println("ERF Number: " + resultSet.getString("ERF_NO"));
            System.out.println("Total Fee: " + resultSet.getDouble("TOTAL_FEE"));
            System.out.println("Total Paid: " + resultSet.getDouble("TOTAL_PAID"));
            System.out.println("Deadline: " + resultSet.getString("PROJ_DEADLINE"));
            System.out.println("Project Manager ID: " + resultSet.getInt("manager_id"));
            System.out.println("Architect ID: " + resultSet.getInt("architect_id"));
            System.out.println("Contractor ID: " + resultSet.getInt("contractor_id"));
            System.out.println("Customer ID: " + resultSet.getInt("customer_id"));
            System.out.println("Completion Date: " + resultSet.getString("CompletionDate"));
            System.out.println();
        }

        // Close the result set and prepared statement
        resultSet.close();
        preparedStatement.close();
    }
}
