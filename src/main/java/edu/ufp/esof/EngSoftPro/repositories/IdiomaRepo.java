package edu.ufp.esof.EngSoftPro.repositories;

import edu.ufp.esof.EngSoftPro.models.Idioma;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdiomaRepo extends CrudRepository<Idioma,Long> {
}
