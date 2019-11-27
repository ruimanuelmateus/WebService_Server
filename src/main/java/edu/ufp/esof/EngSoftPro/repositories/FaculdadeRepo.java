package edu.ufp.esof.EngSoftPro.repositories;

import edu.ufp.esof.EngSoftPro.models.Faculdade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaculdadeRepo extends CrudRepository<Faculdade,Long> {
}
