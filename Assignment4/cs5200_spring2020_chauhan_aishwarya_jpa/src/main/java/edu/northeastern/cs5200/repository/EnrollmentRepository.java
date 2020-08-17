package edu.northeastern.cs5200.repository;
import edu.northeastern.cs5200.models.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends CrudRepository<Enrollment, Integer> {

    @Query("SELECT enrollment FROM Enrollment enrollment WHERE enrollment.section = :section")
    public List<Enrollment> findEnrollmentBySection(@Param("section") Section section);

    @Query("SELECT enrollment FROM Enrollment enrollment WHERE enrollment.student = :student")
    public List<Enrollment> findEnrollmentByStudent(@Param("student") Student student);
}
