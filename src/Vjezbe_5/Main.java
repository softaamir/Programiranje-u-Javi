package Sedmica4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {

        ArrayList<Employee> employees = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("employees.txt"))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");
                String type = parts[0];
                String name = parts[1];
                int id = Integer.parseInt(parts[2]);
                double salary = Double.parseDouble(parts[3]);

                if (type.equals("EMPLOYEE")) {
                    employees.add(new Employee("EMPLOYEE",name, id, salary));
                } else if (type.equals("MANAGER")) {
                    double bonus = Double.parseDouble(parts[4]);
                    employees.add(new Manager("MANAGER",name, id, salary, bonus));
                } else if (type.equals("INTERN")) {
                    String school = parts[4];
                    String mentor = parts[5];
                    employees.add(new Intern("INTERN",name, id, salary, school, mentor));
                }
            }
        } catch (IOException e) {
            System.out.println("Greska u čitanju file-a " + e.getMessage());
        }

        System.out.println("---------------------------------------------------------");

        for (Employee e : employees) {
            System.out.println(e.getDetails());
            e.work();
        }

        System.out.println("---------------------------------------------------------");
        System.out.println("Total salary for all employees " + getTotalSalaryRounded(employees));
        System.out.println("---------------------------------------------------------");

        System.out.println("Sorted employees by salary ");
        ArrayList<Employee>employeesSorted=Sorter(employees);
        for (Employee e : employeesSorted) {
            System.out.println(e.getDetails());
        }
        System.out.println("---------------------------------------------------------");

        System.out.println("Top employee by salary ");
        System.out.println(employeesSorted.get(0).getDetails());
        System.out.println("---------------------------------------------------------");

        writeToFile(employeesSorted,"employeesSorted.txt");
        System.out.println("Employee list saved in employeesSorted.txt");
        System.out.println("---------------------------------------------------------");

    }

    public static int getTotalSalaryRounded(ArrayList<Employee> employees) {

        double totalSalary = 0;

        if (employees.isEmpty()) {
            throw new IllegalArgumentException("Employee list is empty");
        } else {
            for (Employee e : employees) {
                totalSalary += e.getSalary();
            }
        }

        int totalSalaryRounded = (int) Math.round(totalSalary);
        return totalSalaryRounded;

    }

    public static ArrayList<Employee> Sorter(ArrayList<Employee> employees) {

        if (employees.isEmpty()) {
            throw new IllegalArgumentException("Employee list is empty");
        } else {
            employees.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
            return employees;
        }
    }

    public static void writeToFile(ArrayList<Employee> employees,String fileName) {

        try (FileWriter fw = new FileWriter(fileName)) {
            for (Employee e : employees) {
                switch(e.getClass().getSimpleName()){
                    case "Employee":
                        fw.write(e.getPosition()+","+e.getName()+","+e.getId()+","+e.getSalary()+"\n");
                        break;
                    case "Manager":
                        Manager m = (Manager) e;
                        fw.write(m.getPosition()+","+m.getName()+","+m.getId()+","+m.getSalaryWithoutBonus()+","+m.getBonus()+"\n");
                        break;
                    case "Intern":
                        Intern i = (Intern) e;
                        fw.write(i.getPosition()+","+i.getName()+","+i.getId()+","+i.getSalary()+","+i.getSchool()+","+i.getMentorName()+"\n");
                        break;
                    default:
                        fw.write(e.getPosition()+","+e.getName()+","+e.getId()+","+e.getSalary()+"\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Greska u pisanju file-a " + e.getMessage());
        }
    }
}

