package edu.ufp.esof.EngSoftPro.repositories;

import edu.ufp.esof.EngSoftPro.models.Curso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepo extends CrudRepository<Curso,Long> {
}