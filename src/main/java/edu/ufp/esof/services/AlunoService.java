package edu.ufp.esof.services;

import edu.ufp.esof.models.Aluno;
import edu.ufp.esof.repositories.AlunoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlunoService {

    private AlunoRepo alunoRepo;

    @Autowired
    public AlunoService(AlunoRepo alunoRepo) {
        this.alunoRepo=alunoRepo;
    }

    public Iterable<Aluno> findAll() {
        return this.alunoRepo.findAll();
    }

    public Optional<Aluno> findById(Long id) {
        return this.alunoRepo.findById(id);
    }

    public Optional<Aluno> createAluno(Aluno aluno) {
        Optional<Aluno> optionalAluno=this.alunoRepo.findByName(aluno.getName());
        if(optionalAluno.isPresent()){
            return Optional.empty();
        }
        Aluno createdAluno=this.alunoRepo.save(aluno);
        return Optional.of(createdAluno);
    }

    public Optional<Aluno> findByName(String name) {
        return this.alunoRepo.findByName(name);
    }
}
