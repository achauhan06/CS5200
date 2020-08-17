package edu.northeastern.cs5200.repository;
import edu.northeastern.cs5200.models.ParagraphWidget;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParagraphWidgetRepository extends CrudRepository<ParagraphWidget, Integer> {
}
