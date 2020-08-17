package edu.northeastern.cs5200.repository;
import edu.northeastern.cs5200.models.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Integer> {
}
