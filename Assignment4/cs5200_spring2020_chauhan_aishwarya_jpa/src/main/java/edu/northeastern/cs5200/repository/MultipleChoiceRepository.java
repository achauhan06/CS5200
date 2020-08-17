package edu.northeastern.cs5200.repository;
import edu.northeastern.cs5200.models.MultipleChoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultipleChoiceRepository extends CrudRepository<MultipleChoice, Integer> {
}
