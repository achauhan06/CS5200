package edu.northeastern.cs5200.repository;
import edu.northeastern.cs5200.models.ListWidget;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListWidgetRepository extends CrudRepository<ListWidget, Integer> {
}
