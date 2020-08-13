package edu.ufp.esof.repositories;

import edu.ufp.esof.models.Cadeira;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CadeiraRepo extends CrudRepository<Cadeira,Long> {
    Optional<Cadeira> findByName(String name);
}
