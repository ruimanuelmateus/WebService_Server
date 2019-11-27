package edu.ufp.esof.EngSoftPro.repositories;

import edu.ufp.esof.EngSoftPro.models.Aluno;
import edu.ufp.esof.EngSoftPro.models.Explicador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRepo extends CrudRepository<Aluno,Long> {
    Optional<Aluno> findByName(String name);
}
