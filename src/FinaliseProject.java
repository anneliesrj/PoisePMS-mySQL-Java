// 7. Finalise a project

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class FinaliseProject {
    public void finaliseProject(Scanner scanner, Connection connection) {
    	
        // Ask for the project number
        System.out.print("Enter the project number: ");
        int projectNumber = scanner.nextInt();

        // Check if the project exists
        if (!projectExists(projectNumber, connection)) {
            System.out.println("Project not found.");
            return;
        }

        // Ask for confirmation
        System.out.print("Are you sure you want to finalise the project? (Y/N): ");
        String confirmation = scanner.next();

        if (confirmation.equalsIgnoreCase("Y")) {
            // Ask for the completion date
            System.out.print("Enter the completion date (YYYY-MM-DD): ");
            String completionDate = scanner.next();

            // Update the project in the database
            try {
                String updateQuery = "UPDATE Project SET CompletionDate = ? WHERE PROJ_NO = ?";
                PreparedStatement statement = connection.prepareStatement(updateQuery);
                statement.setString(1, completionDate);
                statement.setInt(2, projectNumber);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Project successfully finalised.");
                } else {
                    System.out.println("Failed to finalise the project.");
                }

                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Finalisation cancelled.");
        }
    }

    private boolean projectExists(int projectNumber, Connection connection) {
        try {
            String selectQuery = "SELECT * FROM Project WHERE PROJ_NO = ?";
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setInt(1, projectNumber);

            // Execute the query and check if any results are returned
            return statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
