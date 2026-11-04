package Sedmica4;

import java.time.LocalDate;

public class Manager extends Employee {
    private double bonus;

    public Manager(String position, String name, int id, double salary, double bonus, LocalDate hireDay) {
        super(position, name, id, salary, hireDay);
        this.bonus = bonus;
    }

    public double getBonus() { return bonus; }

    public double getSalaryWithoutBonus() { return super.getSalary(); }

    @Override
    public double getSalary() { return super.getSalary() + bonus; }

    @Override
    public void work() {
        System.out.println(getName() + " is managing the team");
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " and has bonus " + bonus + " for a total of " + getSalary();
    }

    // bonus je primitivni double, pa super.clone() već daje potpunu kopiju;
    // override služi da povratni tip bude Manager.
    @Override
    public Manager clone() {
        return (Manager) super.clone();
    }
}
