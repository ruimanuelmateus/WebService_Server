package edu.ufp.esof.controllers;

import edu.ufp.esof.models.Explicador;
import edu.ufp.esof.services.ExplicadorService;
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
@RequestMapping("/explicador")
public class ExplicadorController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private ExplicadorService explicadorService;

    @Autowired
    public ExplicadorController(ExplicadorService explicadorService){this.explicadorService=explicadorService;}

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Explicador>> getAllExplicadores(){
        this.logger.info("Received a get request");

        return ResponseEntity.ok(this.explicadorService.findAll());
    }
/*
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Explicador> getExplicadorById(@PathVariable("id") Long id) throws NoExplicadorException {
        this.logger.info("Received a get request");

        Optional<Explicador> optionalExplicador=this.explicadorService.findById(id);
        if(optionalExplicador.isPresent()) {
            return ResponseEntity.ok(optionalExplicador.get());
        }
        throw new NoExplicadorException(id);
    }

 */

    @GetMapping(value="/search",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Explicador>> searchExplicadores(@RequestParam Map<String,String> query){
        this.logger.info("Received a get request for mapping explicadores");
        return ResponseEntity.ok(this.explicadorService.filterExplicadores(query));
    }

    @GetMapping(value="/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explicador> searchExplicadorName(@PathVariable("name")String explicadorName){
        this.logger.info("Received a get request for name explicador");
        Optional<Explicador> optionalExplicador=this.explicadorService.searchByName(explicadorName);
        if(optionalExplicador.isPresent()){
            return ResponseEntity.ok(optionalExplicador.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explicador> createExplicador(@RequestBody Explicador explicador){
        this.logger.info("Received a post request to create explicador");
        Optional<Explicador> explicadorOptional=this.explicadorService.createExplicador(explicador);
        if(explicadorOptional.isPresent()){
            return ResponseEntity.ok(explicadorOptional.get());
        }
        throw new ExplicadorAlreadyExistsExcpetion(explicador.getName());

    }

    @PutMapping(value="/{curso}", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explicador> addCursoToExplicador(@RequestBody Explicador explicador,@PathVariable("curso")String cursoName){
        this.logger.info("Received a put request to define curso to explicador");
        Optional<Explicador> optionalExplicador=this.explicadorService.modifyCurso(explicador, cursoName);
        if(optionalExplicador.isPresent()){
            return ResponseEntity.ok(optionalExplicador.get());
        }
        throw new CursoDoesNotExistsExcpetion(cursoName);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explicador> addDisponibilidade(@RequestBody Explicador explicador){
        this.logger.info("Received a put request to add disponibilidade");
        Optional<Explicador> optionalExplicador=this.explicadorService.addDisponibilidade(explicador);
        if(optionalExplicador.isPresent()){
            return ResponseEntity.ok(optionalExplicador.get());
        }
        throw new ErroExcpetion();
    }


    @PostMapping(value="/{faculdade}", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explicador> addExplicadorToFaculdade(@RequestBody Explicador explicador, @PathVariable("faculdade")String faculdadeName){
        this.logger.info("Received a post request to add explicador on universidade");
        Optional<Explicador> optionalExplicador=this.explicadorService.createAndAssociate(explicador,faculdadeName);
        if(optionalExplicador.isPresent()){
            return ResponseEntity.ok(optionalExplicador.get());
        }
        return ResponseEntity.badRequest().build();
        //throw new CadeiraController.CadeiraAlreadyExistsExcpetion(cadeira.getName());

    }


    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such explicador")
    private static class NoExplicadorException extends RuntimeException {
        public NoExplicadorException(Long id) {
            super("No such explicador with id: "+id);
        }
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such curso")
    private static class NoCursoException extends RuntimeException {

        public NoCursoException(String name) {
            super("No such curso with name: "+name);
        }
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Explicador already exists")
    private static class ExplicadorAlreadyExistsExcpetion extends RuntimeException {

        ExplicadorAlreadyExistsExcpetion(String name) {
            super("A explicador with name: "+name+" already exists");
        }
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Curso does not exists")
    private static class CursoDoesNotExistsExcpetion extends RuntimeException {

        CursoDoesNotExistsExcpetion(String name) {
            super("A curso with name: "+name+" does not exists");
        }
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Erro")
    private static class ErroExcpetion extends RuntimeException {

        ErroExcpetion() {
            super("Erro");
        }
    }
}
