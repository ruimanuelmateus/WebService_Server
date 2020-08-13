package edu.ufp.esof.repositories;

import edu.ufp.esof.models.Idioma;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdiomaRepo extends CrudRepository<Idioma,Long> {
}
