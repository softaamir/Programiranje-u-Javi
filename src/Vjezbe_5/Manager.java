package Sedmica4;

public class Manager extends Employee {
    private double bonus;

    public Manager(String position,String name, int id, double salary, double bonus) {
        super(position,name, id, salary);
        this.bonus = bonus;
    }

    public double getBonus(){
        return bonus;
    }

    public double getSalaryWithoutBonus(){
        return super.getSalary();
    }

    @Override
    public double getSalary(){
        return super.getSalary() + bonus;
    }

    @Override
    public void work(){
        System.out.println(getName() + " is managing the team");
    }

    @Override
    public String getDetails(){
        return super.getDetails() + " and has bonus " + bonus + " for a total of " + getSalary();
    }

}
