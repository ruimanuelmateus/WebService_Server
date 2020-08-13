package edu.ufp.esof.repositories;

import edu.ufp.esof.models.Faculdade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FaculdadeRepo extends CrudRepository<Faculdade,Long> {
    Optional<Faculdade> findByName(String name);
}
