package edu.northeastern.cs5200.repository;
import edu.northeastern.cs5200.models.Section;
import edu.northeastern.cs5200.models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {





}
