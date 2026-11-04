package Sedmica4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        ArrayList<Employee> employees = new ArrayList<>();

        // Format linija:
        // EMPLOYEE,name,id,salary,hireDay
        // MANAGER,name,id,salary,bonus,hireDay
        // INTERN,name,id,salary,school,mentor,duration,hireDay
        try (BufferedReader br = new BufferedReader(new FileReader("employees.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                String name = parts[1];
                int id = Integer.parseInt(parts[2]);
                double salary = Double.parseDouble(parts[3]);

                if (type.equals("EMPLOYEE")) {
                    employees.add(new Employee("EMPLOYEE", name, id, salary, LocalDate.parse(parts[4])));
                } else if (type.equals("MANAGER")) {
                    double bonus = Double.parseDouble(parts[4]);
                    employees.add(new Manager("MANAGER", name, id, salary, bonus, LocalDate.parse(parts[5])));
                } else if (type.equals("INTERN")) {
                    employees.add(new Intern("INTERN", name, id, salary, parts[4], parts[5], Integer.parseInt(parts[6]), LocalDate.parse(parts[7])));
                }
            }
        } catch (IOException e) {
            System.out.println("Greska u čitanju file-a " + e.getMessage());
        }

        String line = "---------------------------------------------------------";
        System.out.println(line);

        for (Employee e : employees) {
            System.out.println(e.getDetails());
            e.work();
        }

        System.out.println(line);
        System.out.println("Total salary for all employees " + getTotalSalaryRounded(employees));
        System.out.println(line);

        // Sortiranje: Comparable (po ID-u) i comparatori (salary, hireDay)
        ArrayList<Employee> byId = new ArrayList<>(employees);
        Collections.sort(byId); // koristi compareTo -> po ID-u
        System.out.println("Sorted by ID (compareTo):");
        byId.forEach(e -> System.out.println(e.getDetails()));

        ArrayList<Employee> byHireDay = new ArrayList<>(employees);
        byHireDay.sort(Employee.BY_HIRE_DAY);
        System.out.println(line);
        System.out.println("Sorted by hire day (oldest first):");
        byHireDay.forEach(e -> System.out.println(e.getDetails()));

        System.out.println(line);
        System.out.println("Sorted employees by salary ");
        ArrayList<Employee> employeesSorted = Sorter(employees);
        for (Employee e : employeesSorted) {
            System.out.println(e.getDetails());
        }
        System.out.println(line);

        System.out.println("Top employee by salary ");
        System.out.println(employeesSorted.get(0).getDetails());
        System.out.println(line);

        // Pretraživanje
        System.out.println("Search by name 'a':");
        searchByName(employees, "a");
        System.out.println("Search by ID 103:");
        searchById(employees, 103);
        System.out.println("Search by ID 999:");
        searchById(employees, 999);
        System.out.println(line);

        // Demonstracija kloniranja
        Employee original = employees.get(0);
        Employee copy = original.clone();
        System.out.println("Clone is different object: " + (original != copy)
                + ", same class: " + (original.getClass() == copy.getClass())
                + ", same data: " + copy.getDetails().equals(original.getDetails()));
        System.out.println(line);

        writeToFile(employeesSorted, "employeesSorted.txt");
        System.out.println("Employee list saved in employeesSorted.txt");
        System.out.println(line);
    }

    // Traži po imenu (dio imena, bez obzira na velika/mala slova)
    public static void searchByName(ArrayList<Employee> employees, String name) {
        boolean found = false;
        for (Employee e : employees) {
            if (e.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(e.getDetails());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No employee found with name containing '" + name + "'");
        }
    }

    // Traži po tačnom ID-u
    public static void searchById(ArrayList<Employee> employees, int id) {
        for (Employee e : employees) {
            if (e.getId() == id) {
                System.out.println(e.getDetails());
                return;
            }
        }
        System.out.println("No employee found with ID " + id);
    }

    public static int getTotalSalaryRounded(ArrayList<Employee> employees) {
        double totalSalary = 0;
        if (employees.isEmpty()) {
            throw new IllegalArgumentException("Employee list is empty");
        }
        for (Employee e : employees) {
            totalSalary += e.getSalary();
        }
        return (int) Math.round(totalSalary);
    }

    public static ArrayList<Employee> Sorter(ArrayList<Employee> employees) {
        if (employees.isEmpty()) {
            throw new IllegalArgumentException("Employee list is empty");
        }
        employees.sort(Employee.BY_SALARY.reversed());
        return employees;
    }

    public static void writeToFile(ArrayList<Employee> employees, String fileName) {
        try (FileWriter fw = new FileWriter(fileName)) {
            for (Employee e : employees) {
                switch (e.getClass().getSimpleName()) {
                    case "Manager":
                        Manager m = (Manager) e;
                        fw.write(m.getPosition() + "," + m.getName() + "," + m.getId() + "," + m.getSalaryWithoutBonus()
                                + "," + m.getBonus() + "," + m.getHireDay() + "\n");
                        break;
                    case "Intern":
                        Intern i = (Intern) e;
                        fw.write(i.getPosition() + "," + i.getName() + "," + i.getId() + "," + i.getSalary()
                                + "," + i.getSchool() + "," + i.getMentorName() + "," + i.getDuration() + "," + i.getHireDay() + "\n");
                        break;
                    default:
                        fw.write(e.getPosition() + "," + e.getName() + "," + e.getId() + "," + e.getSalary()
                                + "," + e.getHireDay() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Greska u pisanju file-a " + e.getMessage());
        }
    }
}