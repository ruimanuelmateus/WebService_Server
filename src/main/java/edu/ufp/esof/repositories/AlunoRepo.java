package edu.ufp.esof.repositories;

import edu.ufp.esof.models.Aluno;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRepo extends CrudRepository<Aluno,Long> {
    Optional<Aluno> findByName(String name);
}
