package edu.ufp.esof.EngSoftPro.repositories;

import edu.ufp.esof.EngSoftPro.models.Curso;
import edu.ufp.esof.EngSoftPro.models.Faculdade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepo extends CrudRepository<Curso,Long> {
    Optional<Curso> findByName(String name);
}