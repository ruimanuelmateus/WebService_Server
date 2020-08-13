package edu.ufp.esof.controllers;

import edu.ufp.esof.models.Curso;
import edu.ufp.esof.services.CursoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/curso")
public class CursoController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService){this.cursoService=cursoService;}

    @PostMapping(value="/{faculdade}", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Curso> createCurso(@RequestBody Curso curso, @PathVariable("faculdade")String faculdadeName){
        this.logger.info("Received a post request to add curso to universidade");
        Optional<Curso> optionalCurso=this.cursoService.createAndAssociate(curso,faculdadeName);
       if(optionalCurso.isPresent()){
           return ResponseEntity.ok(optionalCurso.get());
       }
       throw new CursoController.CursoAlreadyExistsExcpetion(curso.getName());

    }



    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Curso already exists")
    private static class CursoAlreadyExistsExcpetion extends RuntimeException {

        public CursoAlreadyExistsExcpetion(String name) {
            super("A curso with name: "+name+" already exists");
        }
    }
}