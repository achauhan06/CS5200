package edu.northeastern.cs5200.dao;

import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class UniversityDaoImpl implements UniversityDao {

    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    WidgetRepository widgetRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    SectionRepository sectionRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    EnrollmentRepository enrollmentRepository;


    @Override
    public void truncateDatabase() {
        enrollmentRepository.deleteAll();
        sectionRepository.deleteAll();
        studentRepository.deleteAll();
        courseRepository.deleteAll();
        facultyRepository.deleteAll();
        personRepository.deleteAll();
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Section createSection(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public Course addSectionToCourse(Section section, Course course) {
        section.setCourse(course);
        sectionRepository.save(section);
        return course;
    }

    @Override
    public Course setAuthorForCourse(Faculty faculty, Course course) {
        course.setFaculty(faculty);
        return courseRepository.save(course);
    }

    @Override
    public Boolean enrollStudentInSection(Student student, Section section) {
        if(student!=null && section!=null && section.getSeats()>0){
            Enrollment enrollment = new Enrollment();
            section.setSeats(section.getSeats()-1);
            sectionRepository.save(section);
            enrollment.setSection(section);
            enrollment.setStudent(student);
            enrollmentRepository.save(enrollment);
            return true;
        }
        return false;
    }

    @Override
    public List<Section> findAllSections() {
        return (List<Section>)sectionRepository.findAll();
    }

    @Override
    public List<Section> findSectionForCourse(Course course) {
        return sectionRepository.findSectionByCourse(course);
    }

    @Override
    public List<Section> findSectionsForStudent(Student student) {
        List<Enrollment> enrollments = enrollmentRepository.findEnrollmentByStudent(student);
        List<Section> sections = new ArrayList<>();
        Iterator<Enrollment> eIter = enrollments.iterator();
        while (eIter.hasNext()){
            Enrollment enrollment = (Enrollment) eIter.next();
            sections.add(enrollment.getSection());
        }

        return sections;
    }

    @Override
    public List<Course> findAllCourses() {
        return (List<Course>)courseRepository.findAll();
    }

    @Override
    public List<Course> findCoursesForAuthor(Faculty faculty) {
        return courseRepository.findCourseByFaculty(faculty);
    }

    @Override
    public List<Person> findAllUsers() {
        return (List<Person>)personRepository.findAll();
    }

    @Override
    public List<Student> findAllStudents() {
        return (List<Student>)studentRepository.findAll();
    }

    @Override
    public List<Student> findStudentsInSection(Section section) {
        List<Enrollment> enrollments = enrollmentRepository.findEnrollmentBySection(section);
        List<Student> students = new ArrayList<>();
        Iterator<Enrollment> eIter = enrollments.iterator();
        while (eIter.hasNext()){
            Enrollment enrollment = (Enrollment) eIter.next();
            students.add(enrollment.getStudent());
        }

        return students;
    }

    @Override
    public List<Faculty> findAllFaculty() {
        return (List<Faculty>)facultyRepository.findAll();
    }
}
