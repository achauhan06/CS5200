package edu.northeastern.cs5200.repository;
import edu.northeastern.cs5200.models.YouTubeWidget;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YouTubeWidgetRepository extends CrudRepository<YouTubeWidget, Integer> {
}
