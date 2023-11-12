// 5. Show not yet finalised projects past deadline

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PastDeadline {

    public static void showPastDeadlineProjects(Connection connection) throws SQLException {
        LocalDate currentDate = LocalDate.now();

        // Search database for projects that have no completion date but have past the deadline
        String query = "SELECT * FROM Project WHERE CompletionDate IS NULL AND PROJ_DEADLINE < ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setObject(1, currentDate);

        ResultSet resultSet = statement.executeQuery();

        List<Project> pastDeadlineProjects = new ArrayList<>();
        while (resultSet.next()) {
            int projectNumber = resultSet.getInt("PROJ_NO");
            String projectName = resultSet.getString("PROJ_NAME");
            String buildingType = resultSet.getString("BUILDING_TYPE");

            Project project = new Project(projectNumber, projectName, buildingType);
            pastDeadlineProjects.add(project);
        }

        resultSet.close();
        statement.close();

        // Display the past deadline projects
        System.out.println("Past Deadline Projects:");
        for (Project project : pastDeadlineProjects) {
            System.out.println(project);
        }
    }

    // Class for holding project information
    static class Project {
        private int projectNumber;
        private String projectName;
        private String buildingType;

        public Project(int projectNumber, String projectName, String buildingType) {
            this.projectNumber = projectNumber;
            this.projectName = projectName;
            this.buildingType = buildingType;
        }

        @Override
        public String toString() {
            return "Project Number: " + projectNumber +
                    ", Project Name: " + projectName +
                    ", Building Type: " + buildingType;
        }
    }
}
