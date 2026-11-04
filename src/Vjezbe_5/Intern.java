package Sedmica4;

import java.time.LocalDate;

public class Intern extends Employee {
    private String school;
    String mentorName;
    private int duration; // trajanje pripravničkog staža u mjesecima

    public Intern(String position, String name, int id, double salary, String school, String mentorName, int duration, LocalDate hireDay) {
        super(position, name, id, salary, hireDay);
        this.school = school;
        this.mentorName = mentorName;
        this.duration = duration;
    }

    public String getSchool() { return school; }
    public String getMentorName() { return mentorName; }
    public int getDuration() { return duration; }

    @Override
    public void work() {
        System.out.println(getName() + " is working and being mentored by " + mentorName);
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " and attends " + school + ", internship duration: " + duration + " months";
    }

    // school i mentorName su Stringovi (immutable), a duration je primitivni int, pa je kopija sigurna.
    @Override
    public Intern clone() {
        return (Intern) super.clone();
    }
}