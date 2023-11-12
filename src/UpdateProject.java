// 3. Update projects

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateProject {

    // method to display the update menu and get the user's selection
    private static int displayUpdateMenu(Scanner scanner) {
        System.out.println("Select fields to update:");
        System.out.println("1. Project Name");
        System.out.println("2. Building Type");
        System.out.println("3. Project Address");
        System.out.println("4. ERF Number");
        System.out.println("5. Total Fee");
        System.out.println("6. Total Paid");
        System.out.println("7. Deadline");
        System.out.println("8. Engineer ID");
        System.out.println("9. Project Manager ID");
        System.out.println("10. Architect ID");
        System.out.println("11. Contractor ID");
        System.out.println("12. Customer ID");
        System.out.println("0. Done");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        return choice;
    }

    // Method to update a project
    static void updateProject(Scanner scanner, Connection connection) throws SQLException {
        System.out.println("Update Project\n");

        System.out.print("Enter the project number: ");
        int projectNumber = scanner.nextInt();
        scanner.nextLine();

        // Execute the query to find the project by project number
        String query = "SELECT * FROM Project WHERE PROJ_NO = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, projectNumber);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            // Project found
            System.out.println("Project details:");
            // Project details
            System.out.println("Project Number: " + resultSet.getInt("PROJ_NO"));
            System.out.println("Project Name: " + resultSet.getString("PROJ_NAME"));
            System.out.println("Building Type: " + resultSet.getString("BUILDING_TYPE"));
            System.out.println("Project Address: " + resultSet.getString("PROJ_ADDRESS"));
            System.out.println("ERF Number: " + resultSet.getString("ERF_NO"));
            System.out.println("Total Fee: " + resultSet.getDouble("TOTAL_FEE"));
            System.out.println("Total Paid: " + resultSet.getDouble("TOTAL_PAID"));
            System.out.println("Deadline: " + resultSet.getString("PROJ_DEADLINE"));
            System.out.println("Engineer ID: " + resultSet.getInt("engineer_id"));
            System.out.println("Project Manager ID: " + resultSet.getInt("manager_id"));
            System.out.println("Architect ID: " + resultSet.getInt("architect_id"));
            System.out.println("Contractor ID: " + resultSet.getInt("contractor_id"));
            System.out.println("Customer ID: " + resultSet.getInt("customer_id"));
            System.out.println();

            // Prompt the user to select what to update
            int choice;
            while ((choice = displayUpdateMenu(scanner)) != 0) {
                switch (choice) {
                
                case 1:
                    System.out.print("Enter the updated project name: ");
                    String projectName = scanner.nextLine();

                    // Construct the update query
                    String updateQuery = "UPDATE Project SET PROJ_NAME = ? WHERE PROJ_NO = ?";
                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);

                    // Update project name and project number
                    updateStatement.setString(1, projectName);
                    updateStatement.setInt(2, projectNumber);

                    // Execute the update statement
                    int rowsAffected = updateStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Project details updated successfully.");
                    } else {
                        System.out.println("Failed to update project details.");
                    }

                    updateStatement.close();
                    break;


                case 2:
                    System.out.print("Enter the updated building type: ");
                    String buildingType = scanner.nextLine();

                    // Construct the update query
                    String updateQueryBuildingType = "UPDATE Project SET BUILDING_TYPE = ? WHERE PROJ_NO = ?";
                    PreparedStatement updateStatementBuildingType = connection.prepareStatement(updateQueryBuildingType);

                    // Update building type and project number 
                    updateStatementBuildingType.setString(1, buildingType);
                    updateStatementBuildingType.setInt(2, projectNumber);

                    // Execute the update statement
                    int rowsAffectedBuildingType = updateStatementBuildingType.executeUpdate();

                    if (rowsAffectedBuildingType > 0) {
                        System.out.println("Project details updated successfully.");
                    } else {
                        System.out.println("Failed to update project details.");
                    }

                    updateStatementBuildingType.close();
                    break;

                case 3:
                    System.out.print("Enter the updated project address: ");
                    String projectAddress = scanner.nextLine();

                    // Construct the update query
                    String updateQueryProjectAddress = "UPDATE Project SET PROJ_ADDRESS = ? WHERE PROJ_NO = ?";
                    PreparedStatement updateStatementProjectAddress = connection.prepareStatement(updateQueryProjectAddress);

                    // Update project address and project number 
                    updateStatementProjectAddress.setString(1, projectAddress);
                    updateStatementProjectAddress.setInt(2, projectNumber);

                    // Execute the update statement
                    int rowsAffectedProjectAddress = updateStatementProjectAddress.executeUpdate();

                    if (rowsAffectedProjectAddress > 0) {
                        System.out.println("Project details updated successfully.");
                    } else {
                        System.out.println("Failed to update project details.");
                    }

                    updateStatementProjectAddress.close();
                    break;

                case 4:
                    System.out.print("Enter the updated ERF number: ");
                    String erfNumber = scanner.nextLine();

                    // Construct the update query
                    String updateQueryErfNumber = "UPDATE Project SET ERF_NO = ? WHERE PROJ_NO = ?";
                    PreparedStatement updateStatementErfNumber = connection.prepareStatement(updateQueryErfNumber);

                    // Updated ERF number and project number
                    updateStatementErfNumber.setString(1, erfNumber);
                    updateStatementErfNumber.setInt(2, projectNumber);

                    // Execute the update statement
                    int rowsAffectedErfNumber = updateStatementErfNumber.executeUpdate();

                    if (rowsAffectedErfNumber > 0) {
                        System.out.println("Project details updated successfully.");
                    } else {
                        System.out.println("Failed to update project details.");
                    }

                    updateStatementErfNumber.close();
                    break;

                    case 5:
                        System.out.print("Enter the updated total fee: ");
                        double totalFee = scanner.nextDouble();
                        scanner.nextLine(); 

                        // Construct the update query
                        String updateQueryTotalFee = "UPDATE Project SET TOTAL_FEE = ? WHERE PROJ_NO = ?";
                        PreparedStatement updateStatementTotalFee = connection.prepareStatement(updateQueryTotalFee);

                        // Updated total fee and project number
                        updateStatementTotalFee.setDouble(1, totalFee);
                        updateStatementTotalFee.setInt(2, projectNumber);

                        // Execute the update statement
                        int rowsAffectedTotalFee = updateStatementTotalFee.executeUpdate();

                        if (rowsAffectedTotalFee > 0) {
                            System.out.println("Project details updated successfully.");
                        } else {
                            System.out.println("Failed to update project details.");
                        }

                        updateStatementTotalFee.close();

                        break;

                    case 6:
                        System.out.print("Enter the updated total paid: ");
                        double totalPaid = scanner.nextDouble();
                        scanner.nextLine(); 

                        // Construct the update query
                        String updateQueryTotalPaid = "UPDATE Project SET TOTAL_PAID = ? WHERE PROJ_NO = ?";
                        PreparedStatement updateStatementTotalPaid = connection.prepareStatement(updateQueryTotalPaid);

                        // Updated total paid and project number 
                        updateStatementTotalPaid.setDouble(1, totalPaid);
                        updateStatementTotalPaid.setInt(2, projectNumber);

                        // Execute the update statement
                        int rowsAffectedTotalPaid = updateStatementTotalPaid.executeUpdate();

                        if (rowsAffectedTotalPaid > 0) {
                            System.out.println("Project details updated successfully.");
                        } else {
                            System.out.println("Failed to update project details.");
                        }

                        updateStatementTotalPaid.close();

                        break;

                    case 7:
                        System.out.print("Enter the updated deadline (YYYY-MM-DD: ");
                        String deadline = scanner.nextLine();

                        // Construct the update query
                        String updateQueryDeadline = "UPDATE Project SET PROJ_DEADLINE = ? WHERE PROJ_NO = ?";
                        PreparedStatement updateStatementDeadline = connection.prepareStatement(updateQueryDeadline);

                        // Updated deadline and project number 
                        updateStatementDeadline.setString(1, deadline);
                        updateStatementDeadline.setInt(2, projectNumber);

                        // Execute the update statement
                        int rowsAffectedDeadline = updateStatementDeadline.executeUpdate();

                        if (rowsAffectedDeadline > 0) {
                            System.out.println("Project details updated successfully.");
                        } else {
                            System.out.println("Failed to update project details.");
                        }

                        updateStatementDeadline.close();

                        break;

                    case 8:
                        System.out.print("Enter the updated Structural engineer ID: ");
                        int engineerId = scanner.nextInt();
                        scanner.nextLine(); 
                        
                        // Check if user wants to add a new Engineer
                        if (!UpdateProject.checkStructuralEngineerExists(connection, engineerId)) {
                            System.out.print("Engineer with ID " + engineerId + " does not exist. Do you want to add a new Engineer? (Y/N): ");
                            String addNewEngineer = scanner.nextLine();
                            if (addNewEngineer.equalsIgnoreCase("Y")) {
                                UpdateProject.addNewEngineer(scanner, connection);
                                engineerId = UpdateProject.getLastInsertedIdEngineer(connection);
                            } else {
                                return; // Exit the method if the user doesn't want to add a new Engineer
                            }
                        }
                        
                        // Update the engineer ID
                        String updateQueryEngineer = "UPDATE Project SET engineer_id = ? WHERE PROJ_NO = ?";
                        PreparedStatement updateStatementEngineer = connection.prepareStatement(updateQueryEngineer);

                        // Updated engineer ID and project number 
                        updateStatementEngineer.setInt(1, engineerId);
                        updateStatementEngineer.setInt(2, projectNumber);

                        // Execute the update statement
                        int rowsAffectedEngineer = updateStatementEngineer.executeUpdate();

                        if (rowsAffectedEngineer > 0) {
                            System.out.println("Project details updated successfully.");
                        } else {
                            System.out.println("Failed to update project details.");
                        }

                        updateStatementEngineer.close();

                        break;

                    case 9:
                    	System.out.print("Enter the updated Project Manager ID: ");
                        int managerId = scanner.nextInt();
                        scanner.nextLine(); 
                     // Check if user wants to add a new Manager
                        if (!UpdateProject.checkProjectManagerExists(connection, managerId)) {
                            System.out.print("Project Manager with ID " + managerId + " does not exist. Do you want to add a new Project Manager? (Y/N): ");
                            String addNewManager = scanner.nextLine();
                            if (addNewManager.equalsIgnoreCase("Y")) {
                                UpdateProject.addNewProjectManager(scanner, connection);
                                managerId = UpdateProject.getLastInsertedIdManager(connection);
                            } else {
                                return; // Exit the method if the user doesn't want to add a new manager
                            }
                        }
                        
                        // Update the manager ID
                        String updateQueryManager = "UPDATE Project SET manager_id = ? WHERE PROJ_NO = ?";
                        PreparedStatement updateStatementManager = connection.prepareStatement(updateQueryManager);

                        // Updated manager ID and project number 
                        updateStatementManager.setInt(1, managerId);
                        updateStatementManager.setInt(2, projectNumber);

                        // Execute the update statement
                        int rowsAffectedManager = updateStatementManager.executeUpdate();

                        if (rowsAffectedManager > 0) {
                            System.out.println("Project details updated successfully.");
                        } else {
                            System.out.println("Failed to update project details.");
                        }

                        updateStatementManager.close();

                        break;

                    case 10:
                    	System.out.print("Enter the updated Architect ID: ");
                        int architectId = scanner.nextInt();
                        scanner.nextLine(); 
                        
                        // Check if user wants to add a new Architect
                        if (!UpdateProject.checkProjectManagerExists(connection, architectId)) {
                            System.out.print("Architect with ID " + architectId + " does not exist. Do you want to add a new Architect? (Y/N): ");
                            String addNewArchitect = scanner.nextLine();
                            if (addNewArchitect.equalsIgnoreCase("Y")) {
                                UpdateProject.addNewArchitect(scanner, connection);
                                architectId = UpdateProject.getLastInsertedIdArchitect(connection);
                            } else {
                                return; // Exit the method if the user doesn't want to add a new architect
                            }
                        }
                        
                        // Update the architect ID
                        String updateQueryArchitect = "UPDATE Project SET architect_id = ? WHERE PROJ_NO = ?";
                        PreparedStatement updateStatementArchitect = connection.prepareStatement(updateQueryArchitect);

                        // Updated Architect ID and project number 
                        updateStatementArchitect.setInt(1, architectId);
                        updateStatementArchitect.setInt(2, projectNumber);

                        // Execute the update statement
                        int rowsAffectedArchitect = updateStatementArchitect.executeUpdate();

                        if (rowsAffectedArchitect > 0) {
                            System.out.println("Project details updated successfully.");
                        } else {
                            System.out.println("Failed to update project details.");
                        }

                        updateStatementArchitect.close();

                        break;
                        
                    case 11:
                    	System.out.print("Enter the updated Contractor ID: ");
                        int contractorId = scanner.nextInt();
                        scanner.nextLine(); 
                        
                        if (!UpdateProject.checkContractorExists(connection, contractorId)) {
                            System.out.print("Contractor with ID " + contractorId + " does not exist. Do you want to add a new Contractor? (Y/N): ");
                            String addNewContractor = scanner.nextLine();
                            
                            // Check if user wants to add a new Contractor
                            if (addNewContractor.equalsIgnoreCase("Y")) {
                                UpdateProject.addNewContractor(scanner, connection);
                                contractorId = UpdateProject.getLastInsertedIdContractor(connection);
                            } else {
                                return; // Exit the method if the user doesn't want to add a new Contractor
                            }
                        }
                        
                        // Update the Contractor ID
                        String updateQueryContractor = "UPDATE Project SET contractor_id = ? WHERE PROJ_NO = ?";
                        PreparedStatement updateStatementContractor = connection.prepareStatement(updateQueryContractor);

                        // Updated Contractor ID and project number
                        updateStatementContractor.setInt(1, contractorId);
                        updateStatementContractor.setInt(2, projectNumber);

                        // Execute the update statement
                        int rowsAffectedContractor = updateStatementContractor.executeUpdate();

                        if (rowsAffectedContractor > 0) {
                            System.out.println("Project details updated successfully.");
                        } else {
                            System.out.println("Failed to update project details.");
                        }

                        updateStatementContractor.close();

                        break;
                        
                    case 12:
                    	System.out.print("Enter the updated Customer ID: ");
                        int customerId = scanner.nextInt();
                        scanner.nextLine(); 
                        
                     // Check if user wants to add a new customer
                        if (!UpdateProject.checkCustomerExists(connection, customerId)) {
                            System.out.print("Customer with ID " + customerId + " does not exist. Do you want to add a new Customer? (Y/N): ");
                            String addNewCustomer = scanner.nextLine();
                            if (addNewCustomer.equalsIgnoreCase("Y")) {
                                UpdateProject.addNewCustomer(scanner, connection);
                                customerId = UpdateProject.getLastInsertedIdCustomer(connection);
                            } else {
                                return; // Exit the method if the user doesn't want to add a new Customer
                            }
                        }
                        
                        // Update the Customer ID
                        String updateQueryCustomer = "UPDATE Project SET customer_id = ? WHERE PROJ_NO = ?";
                        PreparedStatement updateStatementCustomer = connection.prepareStatement(updateQueryCustomer);

                        // Update Customer ID and project number 
                        updateStatementCustomer.setInt(1, customerId);
                        updateStatementCustomer.setInt(2, projectNumber);

                        // Execute the update statement
                        int rowsAffectedCustomer = updateStatementCustomer.executeUpdate();

                        if (rowsAffectedCustomer > 0) {
                            System.out.println("Project details updated successfully.");
                        } else {
                            System.out.println("Failed to update project details.");
                        }

                        updateStatementCustomer.close();

                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }

        } else {
            System.out.println("No project found with the specified project number.");
        }

        resultSet.close();
        preparedStatement.close();
    }

 // -------------------------------------------------------
    
    // Methods to check and/or add engineer, project manager, architect, contractor, customer
    
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
        String engineerName = scanner.nextLine().trim();

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
    public static int getLastInsertedIdEngineer(Connection connection) throws SQLException {
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
    public static int getLastInsertedIdManager(Connection connection) throws SQLException {
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
    public static int getLastInsertedIdArchitect(Connection connection) throws SQLException {
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
        String contractorName = scanner.nextLine();

        System.out.print("Enter the contractor's email: ");
        String contractorEmail = scanner.nextLine();

        System.out.print("Enter the contractor's telephone number: ");
        String contractorTel = scanner.nextLine();
        
        System.out.print("Enter the contractor's address: ");
        String contractorAddress = scanner.nextLine();

        String insertQuery = "INSERT INTO Contractor (CONTR_NAME, CONTR_EMAIL, CONTR_TEL, CONT_ADDRESS) VALUES (?, ?, ?, ?)";
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
    public static int getLastInsertedIdContractor(Connection connection) throws SQLException {
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
    public static int getLastInsertedIdCustomer(Connection connection) throws SQLException {
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
    
    
    
    
    
    
    


