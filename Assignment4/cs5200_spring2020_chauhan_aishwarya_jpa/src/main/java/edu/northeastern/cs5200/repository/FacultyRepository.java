package edu.northeastern.cs5200.repository;
import edu.northeastern.cs5200.models.Faculty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FacultyRepository extends CrudRepository<Faculty, Integer> {



}
