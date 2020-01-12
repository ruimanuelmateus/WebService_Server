package edu.ufp.esof.EngSoftPro.repositories;

import edu.ufp.esof.EngSoftPro.models.Explicacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExplicacaoRepo extends CrudRepository<Explicacao,Long> {
    //Optional<Explicacao> findById(Long id);
}
