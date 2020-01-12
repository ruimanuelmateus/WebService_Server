package edu.ufp.esof.EngSoftPro.controllers;

import edu.ufp.esof.EngSoftPro.models.Aluno;
import edu.ufp.esof.EngSoftPro.models.Explicador;
import edu.ufp.esof.EngSoftPro.repositories.AlunoRepo;
import edu.ufp.esof.EngSoftPro.services.AlunoService;
import edu.ufp.esof.EngSoftPro.services.ExplicadorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private AlunoService alunoService;

    @Autowired
    public AlunoController(AlunoService alunoService){this.alunoService=alunoService;}

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Aluno>> getAllAlunos(){
        this.logger.info("Received a get request");

        return ResponseEntity.ok(this.alunoService.findAll());
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Aluno> getAlunoById(@PathVariable("id") Long id) throws AlunoController.NoAlunoExcpetion {
        this.logger.info("Received a get request");

        Optional<Aluno> optionalAluno=this.alunoService.findById(id);
        if(optionalAluno.isPresent()) {
            return ResponseEntity.ok(optionalAluno.get());
        }
        throw new NoAlunoExcpetion(id);
    }

    @GetMapping(value="/search",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Aluno>> searchAlunos(@RequestParam Map<String,String> query){
/*
        System.out.println(query.get("id"));
        System.out.println(query.get("name"));
        System.out.println(query.get("attr1"));
*/
        return ResponseEntity.ok().build();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Aluno> createAluno(@RequestBody Aluno aluno){
        Optional<Aluno> alunoOptional=this.alunoService.createAluno(aluno);
        if(alunoOptional.isPresent()){
            return ResponseEntity.ok(alunoOptional.get());
        }
        throw new AlunoAlreadyExistsExcpetion(aluno.getName());

    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such aluno")
    private class NoAlunoExcpetion extends RuntimeException {

        public NoAlunoExcpetion(Long id) {
            super("No such aluno with id: "+id);
        }
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Aluno already exists")
    private class AlunoAlreadyExistsExcpetion extends RuntimeException {

        public AlunoAlreadyExistsExcpetion(String name) {
            super("A aluno with name: "+name+" already exists");
        }
    }
}
