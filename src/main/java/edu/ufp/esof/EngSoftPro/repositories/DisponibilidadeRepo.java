package edu.ufp.esof.EngSoftPro.repositories;

import edu.ufp.esof.EngSoftPro.models.Disponibilidade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisponibilidadeRepo extends CrudRepository<Disponibilidade,Long> {
}
