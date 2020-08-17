package edu.northeastern.cs5200.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Student extends Person{

    @Column(name = "grad_year")
    private int gradYear;
    private long scholarship;
    @OneToMany(mappedBy="student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Answer> answers;
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Enrollment> enrollments;

    public Student() {
    }

    public int getGradYear() {
        return gradYear;
    }

    public void setGradYear(int gradYear) {
        this.gradYear = gradYear;
    }

    public long getScholarship() {
        return scholarship;
    }

    public void setScholarship(long scholarship) {
        this.scholarship = scholarship;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
