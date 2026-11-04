package Sedmica4;

public class Employee {
    private String position;
    private String name;
    private int id;
    private double salary;

    public Employee(String position,String name, int id, double salary) {
        this.position = position;
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public void work(){
        System.out.println("Employee " + name + " is working.");
    }

    public String getDetails(){
        return "Position: "+ position +" ID: " + id + ", Name: " + name + ", Salary: " + salary;
    }

}
