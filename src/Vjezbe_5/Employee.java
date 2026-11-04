package Sedmica4;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;

public class Employee implements Comparable<Employee>, Cloneable {
    private String position;
    private String name;
    private int id;
    private double salary;
    private LocalDate hireDay;

    // Comparatori za sortiranje po salary i hireDay (compareTo sortira po id)
    public static final Comparator<Employee> BY_ID = Comparator.comparingInt(Employee::getId);
    public static final Comparator<Employee> BY_SALARY = Comparator.comparingDouble(Employee::getSalary);
    public static final Comparator<Employee> BY_HIRE_DAY = Comparator.comparing(Employee::getHireDay);

    public Employee(String position, String name, int id, double salary, LocalDate hireDay) {
        this.position = position;
        this.name = name;
        this.id = id;
        this.salary = salary;
        this.hireDay = hireDay;
    }

    public String getName() { return name; }
    public String getPosition() { return position; }
    public int getId() { return id; }
    public double getSalary() { return salary; }
    public LocalDate getHireDay() { return hireDay; }

    // Koliko je pune godina zaposlenik u firmi
    public int getYearsOfService() {
        return Period.between(hireDay, LocalDate.now()).getYears();
    }

    public void work() {
        System.out.println("Employee " + name + " is working.");
    }

    public String getDetails() {
        return "Position: " + position + " ID: " + id + ", Name: " + name + ", Salary: " + salary
                + ", Hired: " + hireDay + " (" + getYearsOfService() + " years of service)";
    }

    // Prirodni poredak: po ID-u
    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.id, other.id);
    }

    // Duboka kopija: super.clone() kopira primitive i reference, a zatim
    // svako mutabilno/referentno polje kopiramo ručno.
    // String i LocalDate su nepromjenjivi (immutable), ali kopiramo ih
    // eksplicitno da obrazac vrijedi i ako se doda mutabilno polje (npr. java.util.Date, List).
    @Override
    public Employee clone() {
        try {
            Employee copy = (Employee) super.clone();
            copy.hireDay = LocalDate.of(hireDay.getYear(), hireDay.getMonth(), hireDay.getDayOfMonth());
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e); // ne može se desiti jer implementiramo Cloneable
        }
    }
}
