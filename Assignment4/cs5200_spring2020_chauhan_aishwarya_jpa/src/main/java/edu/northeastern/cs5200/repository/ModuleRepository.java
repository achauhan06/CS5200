package edu.northeastern.cs5200.repository;
import edu.northeastern.cs5200.models.Module;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends CrudRepository<Module, Integer> {
}
