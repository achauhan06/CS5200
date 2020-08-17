package edu.northeastern.cs5200.dao;

import edu.northeastern.cs5200.models.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UniversityDao {

    public void truncateDatabase();
    public Faculty createFaculty(Faculty faculty);
    public Student createStudent(Student student);
    public Course createCourse(Course course);
    public Section createSection(Section section);
    public Course addSectionToCourse(Section section, Course course);
    public Course setAuthorForCourse(Faculty faculty, Course course);
    public Boolean enrollStudentInSection(Student student, Section section);
    public List<Section> findAllSections();
    public List<Section> findSectionForCourse(Course course);
    public List<Section> findSectionsForStudent(Student student);
    public List<Course> findAllCourses();
    public List<Course> findCoursesForAuthor(Faculty faculty);
    public List<Person> findAllUsers();
    public List<Student> findAllStudents();
    public List<Student> findStudentsInSection(Section section);
    public List<Faculty> findAllFaculty();

}
