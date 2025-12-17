import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            printMenu();
            System.out.print("Your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            try {
                switch (choice) {
                    case 1 -> showAllEmployees();
                    case 2 -> showAllTasks();
                    case 3 -> showEmployeesByDepartment();
                    case 4 -> addTaskForEmployee();
                    case 5 -> showTasksByEmployee();
                    case 6 -> deleteEmployee();
                    case 7 -> {
                        System.out.println("Program finished.");
                        return;
                    }
                    default -> System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                
                Choose an option:
                1. List all employees
                2. List all tasks
                3. List employees by department
                4. Add task for employee
                5. Show tasks of employee
                6. Delete employee
                7. Exit
                """);
    }

    private static void showAllEmployees() throws Exception {
        String sql = "SELECT * FROM employees";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            printEmployeeHeader();

            while (rs.next()) {
                System.out.printf(
                        "%-2d | %-12s | %-10s | %-15s | %-4d%n",
                        rs.getInt("employee_id"),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        rs.getString("position"),
                        rs.getInt("department_id")
                );
            }
        }
    }

    private static void showAllTasks() throws Exception {
        String sql = "SELECT * FROM tasks";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            printTaskHeader();

            while (rs.next()) {
                System.out.printf(
                        "%-2d | %-40s | %-4d%n",
                        rs.getInt("task_id"),
                        rs.getString("description"),
                        rs.getInt("employee_id")
                );
            }
        }
    }

    private static void showEmployeesByDepartment() throws Exception {
        System.out.print("Enter department ID: ");
        int depId = Integer.parseInt(scanner.nextLine());

        String sql = "SELECT * FROM employees WHERE department_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, depId);
            ResultSet rs = ps.executeQuery();

            printEmployeeHeader();

            while (rs.next()) {
                System.out.printf(
                        "%-2d | %-15s | %-15s | %-15s | %-4d%n",
                        rs.getInt("employee_id"),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        rs.getString("position"),
                        rs.getInt("department_id")
                );
            }
        }
    }

    private static void addTaskForEmployee() throws Exception {
        System.out.print("Task ID: ");
        int taskId = Integer.parseInt(scanner.nextLine());

        System.out.print("Task description: ");
        String description = scanner.nextLine();

        System.out.print("Employee ID: ");
        int empId = Integer.parseInt(scanner.nextLine());

        String sql = "INSERT INTO tasks VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, taskId);
            ps.setString(2, description);
            ps.setInt(3, empId);
            ps.executeUpdate();

            System.out.println("Task added successfully.");
        }
    }

    private static void showTasksByEmployee() throws Exception {
        System.out.print("Enter employee ID: ");
        int empId = Integer.parseInt(scanner.nextLine());

        String sql = "SELECT * FROM tasks WHERE employee_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();

            printTaskHeader();

            while (rs.next()) {
                System.out.printf(
                        "%-2d | %-40s | %-4d%n",
                        rs.getInt("task_id"),
                        rs.getString("description"),
                        rs.getInt("employee_id")
                );
            }
        }
    }

    private static void deleteEmployee() throws Exception {
        System.out.print("Enter employee ID to delete: ");
        int empId = Integer.parseInt(scanner.nextLine());

        try (Connection conn = DatabaseConnection.getConnection()) {

            // Delete tasks of employee
            String deleteTasks = "DELETE FROM tasks WHERE employee_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(deleteTasks)) {
                ps.setInt(1, empId);
                ps.executeUpdate();
            }

            // Delete employee
            String deleteEmployee = "DELETE FROM employees WHERE employee_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(deleteEmployee)) {
                ps.setInt(1, empId);
                int rows = ps.executeUpdate();

                if (rows > 0) {
                    System.out.println("Employee and related tasks deleted.");
                } else {
                    System.out.println("Employee not found.");
                }
            }
        }
    }

    private static void printEmployeeHeader() {
        System.out.printf(
                "%-2s | %-12s | %-10s | %-15s | %-4s%n",
                "ID", "Last Name", "First Name", "Position", "Dept"
        );
    }

    private static void printTaskHeader() {
        System.out.printf(
                "%-2s | %-40s | %-4s%n",
                "ID", "Task Description", "Emp ID"
        );
    }
}
