package edu.ufp.esof.EngSoftPro.controllers;

import edu.ufp.esof.EngSoftPro.models.Aluno;
import edu.ufp.esof.EngSoftPro.repositories.AlunoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepo alunoRepo;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Aluno>> getAllAlunos(){
        return ResponseEntity.ok(this.alunoRepo.findAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Aluno> getAlunoById(@PathVariable Long id){
        Optional<Aluno> alunoOptional=this.alunoRepo.findById(id);
        if(alunoOptional.isPresent()){
            return ResponseEntity.ok(alunoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Aluno> createAluno(@RequestBody Aluno aluno){
        System.out.println(aluno);
        Optional<Aluno> alunoOptional=this.alunoRepo.findByName(aluno.getName());
        if(alunoOptional.isPresent()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.alunoRepo.save(aluno));
    }
}
