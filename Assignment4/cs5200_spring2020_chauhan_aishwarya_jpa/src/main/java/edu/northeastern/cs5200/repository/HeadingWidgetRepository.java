package edu.northeastern.cs5200.repository;
import edu.northeastern.cs5200.models.HeadingWidget;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeadingWidgetRepository extends CrudRepository<HeadingWidget, Integer> {
}
