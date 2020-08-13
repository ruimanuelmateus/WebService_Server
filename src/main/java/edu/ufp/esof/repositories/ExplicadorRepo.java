package edu.ufp.esof.repositories;

import edu.ufp.esof.models.Explicador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExplicadorRepo extends CrudRepository<Explicador,Long> {
    Optional<Explicador> findByName(String name);
}