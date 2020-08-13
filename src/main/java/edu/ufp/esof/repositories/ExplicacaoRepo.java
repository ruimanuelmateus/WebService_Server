package edu.ufp.esof.repositories;

import edu.ufp.esof.models.Explicacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExplicacaoRepo extends CrudRepository<Explicacao,Long> {
    //Optional<Explicacao> findById(Long id);
}
