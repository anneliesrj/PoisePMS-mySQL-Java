import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class PoisePMS {
    // Connect to the PoisePMS database
    private static final String DB_url = "jdbc:mysql://localhost/PoisePMS";
    private static final String DB_username = "engineer";
    private static final String DB_password = "password";

    public static void main(String[] args) {
        try {
            // Connection to the database
            Connection connection = DriverManager.getConnection(DB_url, DB_username, DB_password);

            Scanner scanner = new Scanner(System.in);

            // instance of the menu
            PoisePMSMenu menu = new PoisePMSMenu(scanner);

            // Display the menu and process user input
            menu.displayMenu(connection);

            // Close the database connection and scanner
            connection.close();
            scanner.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class PoisePMSMenu {
    private Scanner scanner;

    public PoisePMSMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMenu(Connection connection) throws SQLException {
        int choice = 0;

        // Menu loop
        while (choice != 8) {
            // Display menu options
            System.out.println("PoisePMS Menu:\n");
            System.out.println("1. Find a project");
            System.out.println("2. Add project");
            System.out.println("3. Update project");
            System.out.println("4. Show not yet finalised projects");
            System.out.println("5. Show not yet finalised projects past deadline");
            System.out.println("6. Show Finalised projects");
            System.out.println("7. Finalise project");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            // Read user input
            choice = scanner.nextInt();

            // Process user choice
            switch (choice) {
                case 1:
                    FindAProject findAProject = new FindAProject();
                    findAProject.findProject(scanner, connection);
                    break;
                case 2:
                    AddProject addProject = new AddProject();
                    addProject.addProject(scanner, connection);
                    break;
                case 3:
                    UpdateProject.updateProject(scanner, connection);
                    break;
                case 4:
                	NotYetFinalised.NotYetFinalised(connection);
                    break;
                case 5:
                    PastDeadline.showPastDeadlineProjects(connection);
                    break;
                case 6:
                    FinalisedProjects.showFinalisedProjects(connection);
                    break;
                case 7:
                    FinaliseProject finaliseProject = new FinaliseProject();
                    finaliseProject.finaliseProject(scanner, connection);
                    break;
                case 8:
                    // Exit the program
                    System.out.println("Exiting PoisePMS. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println(); 
        }
    }
}
