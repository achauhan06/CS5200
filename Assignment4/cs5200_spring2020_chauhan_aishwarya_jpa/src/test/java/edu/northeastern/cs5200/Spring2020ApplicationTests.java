package edu.northeastern.cs5200;

import edu.northeastern.cs5200.dao.UniversityDao;
import edu.northeastern.cs5200.models.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Spring2020ApplicationTests {

    @Autowired
    UniversityDao universityDao;

    @BeforeAll
    public void insert(){
        universityDao.truncateDatabase();

        Faculty faculty1 = new Faculty();
        faculty1.setFirstName("Alan");
        faculty1.setLastName("Turin");
        faculty1.setOffice("123A");
        faculty1.setTenured(true);
        faculty1.setUsername("alan");
        faculty1.setPassword("password");
        universityDao.createFaculty(faculty1);

        Faculty faculty2 = new Faculty();
        faculty2.setFirstName("Ada");
        faculty2.setLastName("Lovelace");
        faculty2.setOffice("123B");
        faculty2.setTenured(true);
        faculty2.setUsername("ada");
        faculty2.setPassword("password");
        universityDao.createFaculty(faculty2);

        Student student1 = new Student();
        student1.setFirstName("Alice");
        student1.setLastName("Wonderland");
        student1.setGradYear(2020);
        student1.setScholarship(12000);
        student1.setUsername("alice");
        student1.setPassword("password");
        universityDao.createStudent(student1);

        Student student2 = new Student();
        student2.setFirstName("Bob");
        student2.setLastName("Hope");
        student2.setGradYear(2021);
        student2.setScholarship(23000);
        student2.setUsername("bob");
        student2.setPassword("password");
        universityDao.createStudent(student2);

        Student student3 = new Student();
        student3.setFirstName("Charlie");
        student3.setLastName("Brown");
        student3.setGradYear(2019);
        student3.setScholarship(21000);
        student3.setUsername("charlie");
        student3.setPassword("password");
        universityDao.createStudent(student3);

        Student student4 = new Student();
        student4.setFirstName("Dan");
        student4.setLastName("Craig");
        student4.setGradYear(2019);
        student4.setScholarship(0);
        student4.setUsername("dan");
        student4.setPassword("password");
        universityDao.createStudent(student4);

        Student student5 = new Student();
        student5.setFirstName("Edward");
        student5.setLastName("Scissorhands");
        student5.setGradYear(2022);
        student5.setScholarship(11000);
        student5.setUsername("edward");
        student5.setPassword("password");
        universityDao.createStudent(student5);

        Student student6 = new Student();
        student6.setFirstName("Frank");
        student6.setLastName("Herbert");
        student6.setGradYear(2018);
        student6.setScholarship(0);
        student6.setUsername("frank");
        student6.setPassword("password");
        universityDao.createStudent(student6);

        Student student7 = new Student();
        student7.setFirstName("Gregory");
        student7.setLastName("Peck");
        student7.setGradYear(2023);
        student7.setScholarship(10000);
        student7.setUsername("gregory");
        student7.setPassword("password");
        universityDao.createStudent(student7);

        Course course1 = new Course();
        course1.setLabel("CS1234");
        course1.setFaculty(faculty1);
        universityDao.createCourse(course1);
        universityDao.setAuthorForCourse(faculty1, course1);


        Course course2 = new Course();
        course2.setLabel("CS2345");
        course2.setFaculty(faculty1);
        universityDao.createCourse(course2);
        universityDao.setAuthorForCourse(faculty1, course2);

        Course course3 = new Course();
        course3.setLabel("CS3456");
        course3.setFaculty(faculty2);
        universityDao.createCourse(course3);
        universityDao.setAuthorForCourse(faculty2, course3);

        Course course4 = new Course();
        course4.setLabel("CS4567");
        course4.setFaculty(faculty2);
        universityDao.createCourse(course4);
        universityDao.setAuthorForCourse(faculty2, course4);

        Section section1 = new Section();
        section1.setSeats(50);
        section1.setTitle("SEC4321");
        universityDao.createSection(section1);
        universityDao.addSectionToCourse(section1, course1);

        Section section2 = new Section();
        section2.setSeats(50);
        section2.setTitle("SEC5432");
        universityDao.createSection(section2);
        universityDao.addSectionToCourse(section2, course1);

        Section section3 = new Section();
        section3.setSeats(50);
        section3.setTitle("SEC6543");
        universityDao.createSection(section3);
        universityDao.addSectionToCourse(section3, course2);

        Section section4 = new Section();
        section4.setSeats(50);
        section4.setTitle("SEC7654");
        universityDao.createSection(section4);
        universityDao.addSectionToCourse(section4, course3);


        universityDao.enrollStudentInSection(student1, section1);
        universityDao.enrollStudentInSection(student1, section2);
        universityDao.enrollStudentInSection(student2, section2);
        universityDao.enrollStudentInSection(student3, section3);


    }




    @Test
    public void validateTotalNumberOfUsers(){
        List<Person> persons = universityDao.findAllUsers();
        assert(persons.size()==9);
    }

    @Test
    public void validateTotalNumberOfFaculty(){
        List<Faculty> faculties = universityDao.findAllFaculty();
        assert(faculties.size() == 2);
    }

    @Test
    public void validateTotalNumberOfStudents(){
        List<Student> students = universityDao.findAllStudents();
        assert(students.size() == 7);
    }

    @Test
    public void validateTotalNumberOfCourses(){
        List<Course> courses = universityDao.findAllCourses();
        assert(courses.size() == 4);
    }

    @Test
    public void validateTotalNumberOfSections(){
        List<Section> sections = universityDao.findAllSections();
        assert(sections.size() == 4);
    }

    @Test
    public void validateAllCourseByEachAuthor(){
        List<Faculty> faculties = universityDao.findAllFaculty();
        assert(faculties.size() == 2);
        Iterator<Faculty> fIter = faculties.iterator();
        while (fIter.hasNext()){
            Faculty faculty = (Faculty)fIter.next();
            int size = universityDao.findCoursesForAuthor(faculty).size();
            if(faculty.getUsername().equals("alan")){
                assert(size == 2);
            }else if(faculty.getUsername().equals("ada")){
                assert(size == 2);
            }
        }
    }

    @Test
    public void validateSectionPerCourse(){
        List<Course> courses = universityDao.findAllCourses();
        assert(courses.size() == 4);
        Iterator<Course> cIter = courses.iterator();
        while (cIter.hasNext()){
            Course course = (Course)cIter.next();
            int size = universityDao.findSectionForCourse(course).size();
            if(course.getLabel().equals("CS1234")){
                assert(size == 2);
            }else if(course.getLabel().equals("CS2345")){
                assert(size == 1);
            }else if(course.getLabel().equals("CS3456")){
                assert(size == 1);
            }

        }
    }

    @Test
    public void validateSectionSeats(){
        List<Section> sections = universityDao.findAllSections();
        assert(sections.size() == 4);
        Iterator<Section> sIter = sections.iterator();
        while (sIter.hasNext()){
            Section section = (Section)sIter.next();
            if(section.getTitle().equals("SEC4321")){
                assert(section.getSeats() == 49);
            }else if(section.getTitle().equals("SEC5432")){
                assert(section.getSeats() == 48);
            }else if(section.getTitle().equals("SEC6543")){
                assert(section.getSeats() == 49);
            }else if(section.getTitle().equals("SEC7654")){
                assert(section.getSeats() == 50);
            }

        }
    }

    @Test
    public void validateStudentsPerSection(){

        List<Section> sections = universityDao.findAllSections();
        assert(sections.size() == 4);
        Iterator<Section> sIter = sections.iterator();
        while (sIter.hasNext()){
            Section section = (Section)sIter.next();
            int size = universityDao.findStudentsInSection(section).size();
            if(section.getTitle().equals("SEC4321")){
                assert(size == 1);
            }else if(section.getTitle().equals("SEC5432")){
                assert(size == 2);
            }else if(section.getTitle().equals("SEC6543")){
                assert(size == 1);
            }else if(section.getTitle().equals("SEC7654")){
                assert(size == 0);
            }

        }
    }

    @Test
    public void validateSectionsPerStudent(){
        List<Student> students = universityDao.findAllStudents();
        assert(students.size() == 7);
        Iterator<Student> sIter = students.iterator();
        while (sIter.hasNext()){
            Student student = (Student)sIter.next();
            int size = universityDao.findSectionsForStudent(student).size();
            if(student.getUsername().equals("alice")){
                assert(size == 2);
            }else if(student.getUsername().equals("bob")){
                assert(size == 1);
            }else if(student.getUsername().equals("charlie")){
                assert(size == 1);
            }else if(student.getUsername().equals("dan")){
                assert(size == 0);
            }else if(student.getUsername().equals("edward")){
                assert(size == 0);
            }else if(student.getUsername().equals("frank")){
                assert(size == 0);
            }else if(student.getUsername().equals("gregory")){
                assert(size == 0);
            }

        }
    }


}


