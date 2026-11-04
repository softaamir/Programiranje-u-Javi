package Sedmica4;

public class Intern extends Employee{
    private String school;
    String mentorName;

    public Intern(String postion,String name, int id, double salary, String school, String mentorName) {
        super(postion,name, id, salary);
        this.school = school;
        this.mentorName = mentorName;
    }

    public String getSchool(){
        return school;
    }

    public String getMentorName(){
        return mentorName;
    }

    @Override
    public void work(){
        System.out.println(getName()+" is working and being mentored by "+mentorName);
    }

    @Override
    public String getDetails(){
        return super.getDetails() + " and attends " + school;
    }

}
