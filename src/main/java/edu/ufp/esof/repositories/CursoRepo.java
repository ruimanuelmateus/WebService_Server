package edu.ufp.esof.repositories;

import edu.ufp.esof.models.Curso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepo extends CrudRepository<Curso,Long> {
    Optional<Curso> findByName(String name);
}