import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("EmployeeData.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0].trim();
                String name = parts[1].trim();
                int id = Integer.parseInt(parts[2].trim());
                double salary = Double.parseDouble(parts[3].trim());

                if (type.equalsIgnoreCase("EMPLOYEE")) {
                    employees.add(new Employee(name, id, salary));
                } else if (type.equalsIgnoreCase("MANAGER")) {
                    double bonus = Double.parseDouble(parts[4].trim());
                    employees.add(new Manager(name, id, salary, bonus));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        for (Employee emp : employees) {
            System.out.println(emp.getDetails());
            emp.work();
            System.out.println();
        }
    }
}