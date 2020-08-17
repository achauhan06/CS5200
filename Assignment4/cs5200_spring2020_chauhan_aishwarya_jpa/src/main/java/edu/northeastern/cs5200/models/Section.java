package edu.northeastern.cs5200.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="section")
public class Section {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private int seats;
    private String title;
    @ManyToOne
    private Course course;
    @OneToMany(mappedBy = "section", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Enrollment> enrollments;

    public Section() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
