// 2. Add Project

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class AddProject {
	
	 // Method to add a project
    void addProject(Scanner scanner, Connection connection) {
        System.out.println("Adding a New Project\n");

        try {
            // Prompt the user for project details
            System.out.print("Enter the project number: ");
            int projectNumber = scanner.nextInt();
            scanner.nextLine(); 


            System.out.print("Enter the building type: ");
            String buildingType = scanner.nextLine();

            System.out.print("Enter the physical address: ");
            String address = scanner.nextLine();

            System.out.print("Enter the ERF number: ");
            String erfNumber = scanner.nextLine();

            System.out.print("Enter the total fee for the project: ");
            double totalFee = scanner.nextDouble();

            System.out.print("Enter the total paid to date: ");
            double totalPaid = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter the deadline (YYYY-MM-DD): ");
            String deadline = scanner.nextLine();

            // Show existing engineers
            System.out.println("\nExisting Structural Engineers:\n");
            StructuralEngineer.displayExistingStructuralEngineers(connection);

            // Prompt the user to select one
            System.out.print("\nEnter the ID of the Structural Engineer for this project: ");
            int engineerId = scanner.nextInt();

            // Check if the selected engineer exists
            boolean engineerExists = StructuralEngineer.checkStructuralEngineerExists(connection, engineerId);

            if (!engineerExists) {
                System.out.println("Structural Engineer does not exist. Add a new Structural Engineer:");

                // Add a new Structural Engineer
                StructuralEngineer.addNewEngineer(scanner, connection);

                // Get the newly added Structural Engineer's ID
                engineerId = StructuralEngineer.getLastInsertedId(connection);
            }

            // Show existing project managers
            System.out.println("\nExisting Project Managers:\n");
            ProjectManager.displayExistingProjectManagers(connection);

            // Prompt the user to select a project manager
            System.out.print("\nEnter the ID of the project manager for this project: ");
            int projectManagerId = scanner.nextInt();

            // Check if the selected project manager ID exists
            boolean projectManagerExists = ProjectManager.checkProjectManagerExists(connection, projectManagerId);

            if (!projectManagerExists) {
                System.out.println("Project Manager does not exist. Add a new Project Manager:");

                // Add a new project manager
                ProjectManager.addNewProjectManager(scanner, connection);

                // Get the newly added project manager's ID
                projectManagerId = ProjectManager.getLastInsertedId(connection);
            }

            // Show existing architects
            System.out.println("\nExisting Architects:\n");
            Architect.displayExistingArchitects(connection);

            // Prompt the user to select one
            System.out.print("\nEnter the ID of the Architect for this project: ");
            int architectId = scanner.nextInt();

            // Check if the selected architect exists
            boolean architectExists = Architect.checkArchitectExists(connection, architectId);

            if (!architectExists) {
                System.out.println("Architect does not exist. Add a new Architect:");

                // Add a new Architect
                Architect.addNewArchitect(scanner, connection);

                // Get the newly added Architect's ID
                architectId = Architect.getLastInsertedId(connection);
            }

            // Show existing contractors
            System.out.println("\nExisting Contractors:\n");
            Contractor.displayExistingContractor(connection);

            // Prompt the user to select one
            System.out.print("\nEnter the ID of the Contractor for this project: ");
            int contractorId = scanner.nextInt();

            // Check if the selected contractor exists
            boolean contractorExists = Contractor.checkContractorExists(connection, contractorId);

            if (!contractorExists) {
                System.out.println("Contractor does not exist. Add a new Contractor:");

                // Add a new Contractor
                Contractor.addNewContractor(scanner, connection);

                // Get the newly added Contractor's ID
                contractorId = Contractor.getLastInsertedId(connection);
            }

            // Show existing customers
            System.out.println("\nExisting Customers:\n");
            Customer.displayExistingCustomers(connection);

            // Prompt the user to select one
            System.out.print("\nEnter the ID of the Customer for this project: ");
            int customerId = scanner.nextInt();

            // Check if the selected customer exists
            boolean customerExists = Customer.checkCustomerExists(connection, customerId);

            if (!customerExists) {
                System.out.println("Customer does not exist. Add a new Customer:");

                // Add a new Customer
                Customer.addNewCustomer(scanner, connection);

                // Get the newly added Customer's ID
                customerId = Customer.getLastInsertedId(connection);
            }
            
            System.out.print("Enter the project name (if unknown, leave empty to generate a name): ");
            String projectName = scanner.nextLine();

            if (projectName.isBlank()) {
                // Consume the newline character
                scanner.nextLine();

                System.out.print("Enter the customer's last name: ");
                String customerName = scanner.nextLine();

                // Generate the project name based on building type and customer name
                projectName = generateProjectName(buildingType, customerName);
            }

            

            // Insert the project details into the database
            String insertQuery = "INSERT INTO Project (PROJ_NO, PROJ_NAME, BUILDING_TYPE, PROJ_ADDRESS, ERF_NO, TOTAL_FEE, TOTAL_PAID, PROJ_DEADLINE, engineer_id, manager_id, architect_id, contractor_id, customer_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setInt(1, projectNumber);
            insertStatement.setString(2, projectName);
            insertStatement.setString(3, buildingType);
            insertStatement.setString(4, address);
            insertStatement.setString(5, erfNumber);
            insertStatement.setDouble(6, totalFee);
            insertStatement.setDouble(7, totalPaid);
            insertStatement.setString(8, deadline);
            insertStatement.setInt(9, engineerId);
            insertStatement.setInt(10, projectManagerId);
            insertStatement.setInt(11, architectId);
            insertStatement.setInt(12, contractorId);
            insertStatement.setInt(13, customerId);

            int rowsAffected = insertStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Project added successfully.");
            } else {
                System.out.println("Failed to add project.");
            }

            insertStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static String generateProjectName(String buildingType, String customerName) {
        return buildingType + " - " + customerName;
    }
    

}