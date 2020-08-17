package edu.northeastern.cs5200.repository;
import edu.northeastern.cs5200.models.TrueFalse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrueFalseRepository extends CrudRepository<TrueFalse, Integer> {
}
