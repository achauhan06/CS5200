package edu.northeastern.cs5200.repository;
import edu.northeastern.cs5200.models.Course;
import edu.northeastern.cs5200.models.Faculty;
import edu.northeastern.cs5200.models.Section;
import edu.northeastern.cs5200.models.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {


    @Query("SELECT course FROM Course course WHERE course.faculty = :faculty")
    public List<Course> findCourseByFaculty(@Param("faculty") Faculty faculty);

}
