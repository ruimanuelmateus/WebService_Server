package edu.ufp.esof.EngSoftPro.repositories;

import edu.ufp.esof.EngSoftPro.models.Cadeira;
import edu.ufp.esof.EngSoftPro.models.Curso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CadeiraRepo extends CrudRepository<Cadeira,Long> {
    Optional<Cadeira> findByName(String name);
}
