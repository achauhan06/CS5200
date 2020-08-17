package edu.northeastern.cs5200.repository;
import edu.northeastern.cs5200.models.ImageWidget;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageWidgetRepository extends CrudRepository<ImageWidget, Integer> {
}
