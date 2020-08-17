package edu.northeastern.cs5200.repository;
import edu.northeastern.cs5200.models.Course;
import edu.northeastern.cs5200.models.Section;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends CrudRepository<Section, Integer> {


    @Query("SELECT section FROM Section section WHERE section.course = :course")
    public List<Section> findSectionByCourse(@Param("course") Course course);

}
