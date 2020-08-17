package edu.northeastern.cs5200.repository;
import edu.northeastern.cs5200.models.Widget;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WidgetRepository extends CrudRepository<Widget, Integer> {
}
